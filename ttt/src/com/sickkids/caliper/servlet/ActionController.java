package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.sql.PooledConnection;

import org.apache.commons.lang.StringEscapeUtils;


import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import com.sickkids.caliper.dao.AllApplicationBean;

import com.sickkids.caliper.dao.AllApplicationHistBean;
import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.AllLoginHistoryDao;
import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.dao.CurriculumCurrentBean;
import com.sickkids.caliper.dao.HospitalBean;
import com.sickkids.caliper.dao.SQLDao;


import com.sickkids.caliper.database.ConnectionPool;

import com.sickkids.caliper.common.EmailText;
import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.ParticipantType;
import com.sickkids.caliper.common.Randomizer;
import com.sickkids.caliper.common.SecureCodeGenerator;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.common.Randomizer;

import com.sickkids.caliper.service.CsrHttpSessionListener;
import com.sickkids.caliper.service.MailInfo;


public class ActionController extends HttpServlet implements Servlet
{	static final long serialVersionUID = 1L;
	static int count=0;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;
	private Map<String, HttpSession> allSessions=null;

	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();

	////////TTT /////////////
	private SQLDao tttsqlDao=null;
	private AllLoginHistoryDao tttloginHistoryDao=null;


	public void init(ServletConfig config)
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");

	    tttsqlDao=(SQLDao)app.getAttribute("tttsqlDao");
	    tttloginHistoryDao=new AllLoginHistoryDao(cp);

	    allSessions=(Map<String, HttpSession>)app.getAttribute("allSessions");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in ActionController.java of TTT");

		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew())
		if(!allSessions.containsKey(session.getId())) allSessions.put(session.getId(), session);

		//Specifies the time, in seconds, between client requests before the servlet container will invalidate this session.
		//A negative time indicates the session should never timeout.
		//session.setMaxInactiveInterval(30);
		//response.setHeader("Cache-Control","no-store,no-cache, must-revalidate, post-check=0, pre-check=0");
		//response.setHeader("Pragma", "no-cache");

		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in ActionController.java of TTT");

		try
		{
			AllRegisteredUserBean trb=(AllRegisteredUserBean)session.getAttribute("trb");//only for TTT

			if(trb==null)//no logged in but op is not null (session out, hacking, look for password and ID)
			{	System.out.println("INFO: ----- 0 0 ------");
				if(op!=null && op.indexOf("ajax")!=-1)
				{	System.out.println("INFO: ----- 0 1 ------");
					if(op.equals("ajax_ttt_login_check"))
					{
						try
						{
							String app_id=(String)request.getParameter("app_id");
							String login_id=(String)request.getParameter("login_id");
							String login_password=(String)request.getParameter("login_password");
							int course_number=Integer.parseInt((String)request.getParameter("course_number"));

							System.out.println(op+": "+login_id+" "+login_password+" "+course_number);
							if(login_id!=null && !login_id.equals("") && login_password!=null && !login_password.equals(""))
							{
								trb=tttsqlDao.getOneRecordFromRegisteredUserTable("where app_id="+app_id+" and user_id='"+login_id+"' and valid=true");
								if(trb.getUserId().equalsIgnoreCase(login_id))
								{
									if(trb.getLoginLevel()>1 && trb.getLoginLevel()<6 && trb.getAccessLimitTime().before(Utils.currentTimestamp()))
									{
										response.getWriter().write("false:We are sorry to say that your access time is over.\nPlease contact Dr. Chris at chris@sickkids.ca!");
									}
									else if(!trb.getPassword().equals(login_password))
									{
										response.getWriter().write("false:"+Message.incorrect_password);
									}
									else if(course_number>0 && (trb.getLoginLevel()==1 || trb.getLoginLevel()==2 || trb.getLoginLevel()==3))//Presenters, teaching assistants, interactive participants, oneway viewers
									{	System.out.println("INFO: ----- 0 non-admin ------");
										//1. check curriculum with course_number
										//2. if match
										//			-- set the record into a session
										//			-- set trb as others
										//			-- check resetting the password
										//	else return: "You are not registered in the Seminar/Course, 'seminar name'"

										List<CurriculumCurrentBean> courseList=tttsqlDao.getCurriculumCurrentList("where course_number="+course_number);
										if(courseList.size()!=1)
											response.getWriter().write("false:There is no requested course available!");
										else
										{
											CurriculumCurrentBean cb=courseList.get(0);
											if(cb.getParticipantType(login_id)==ParticipantType.NONE)
												response.getWriter().write("false:You are not registered in the requested Seminar!");
											else if(isAlreadyLoggedInNow(login_id, course_number, session))//only checking for non-admins
											{
												response.getWriter().write("false:You are not allowed to login more than one time for the same Seminar!");
											}
											else
											{
												//every thing is OK, ie: login is successful, set login session variables
												session.setAttribute("login_id", login_id);
												session.setAttribute("logn_first_name", trb.getFirstName());
												session.setAttribute("login_last_name", trb.getLastName());
												session.setAttribute("login_password", login_password);
												session.setAttribute("trb", trb);
												session.setAttribute("curriculum_bean", cb);
												AllLoginHistoryBean lb=new AllLoginHistoryBean(0L, trb.getAppId(), login_id, Utils.currentTimestamp(),request.getRemoteAddr());
												lb=tttloginHistoryDao.create(lb);
												session.setAttribute("all_login_hist_bean", lb);
												//session.setMaxInactiveInterval(30);//30 sec, set training time here later - FEB 3 -2015
												//if your password needs to be reset, redirect it to password reset
												if(trb.getResetPassword()==1)//0:false (dirty), 1:true (reset it)
													((RequestDispatcher)request.getRequestDispatcher("jsp/ttt_menu_response/ajax_reset_password.jsp")).forward(request,response);
												else //normal login which is not the first time login
													response.getWriter().write("true:"+MenuLink.TTT_CONTEXT);
											}
										}
									}
									else if(course_number<0 && trb.getLoginLevel()==1)//-10000 for admin course number
									{	System.out.println("INFO: ----- 0 admin ------");
										//every thing is OK, ie: login is successful, set login session variables
										session.setAttribute("login_id", login_id);
										session.setAttribute("logn_first_name", trb.getFirstName());
										session.setAttribute("login_last_name", trb.getLastName());
										session.setAttribute("login_password", login_password);
										session.setAttribute("trb", trb);
										AllLoginHistoryBean lb=new AllLoginHistoryBean(0L, trb.getAppId(), login_id, Utils.currentTimestamp(),request.getRemoteAddr());
										lb=tttloginHistoryDao.create(lb);
										session.setAttribute("all_login_hist_bean", lb);

										//if your password needs to be reset, redirect it to password reset
										if(trb.getResetPassword()==1)//0:false (dirty), 1:true (reset it)
											((RequestDispatcher)request.getRequestDispatcher("jsp/ttt_menu_response/ajax_reset_password.jsp")).forward(request,response);
										else //normal login which is not the first time login
											response.getWriter().write("true:"+MenuLink.TTT_CONTEXT);
									}
									else response.getWriter().write("false:You are not found in the requested Seminar!");
								}
								else response.getWriter().write("false:Your LoginID is not in our database.\nPlease try again after checking.");
							}
							else response.getWriter().write("false:"+Message.invalid_loginID);
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_ttt_forgot_pwd"))
					{
						((RequestDispatcher)request.getRequestDispatcher("jsp/ttt_menu_response/ajax_login_forgot_password.jsp")).forward(request,response);
					}
					else if(op.equals("ajax_ttt_forgot_pwd_check"))//ajax request
					{
						try
						{
							String app_id=(String)request.getParameter("app_id");
							String email=(String)request.getParameter("ttt_login_email");
							if(email!=null && !email.equals(""))
							{
								//one email and more than one id and password
								List<AllRegisteredUserBean> idPasswordList=tttsqlDao.getListFromRegisteredUserTable("where app_id="+app_id+" and email='"+email+"'");

								if(idPasswordList.size()>0)
								{

									EmailText.emailForgottenPassword(tttsqlDao.getGenericString("select app_symbol from all_application where app_id="+app_id), idPasswordList, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.TTT_CONTEXT );
									response.getWriter().write("true:Please check your email for your LoginID/Password!");
								}
								else response.getWriter().write("false:"+Message.non_existing_email);

							}
							else response.getWriter().write("false:"+Message.incorrect_email);
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else //op=ajax_... but no login session available, then session_timeout
					{
						response.getWriter().write("session_timeout");//goback to where it calls
					}
				}
				else
				{
					session.setAttribute("curriculum_current_list", tttsqlDao.getCurriculumCurrentList("where valid=true order by course_name asc"));
					request.setAttribute("body_panel", "login_ttt.jsp");
					((RequestDispatcher)request.getRequestDispatcher("jsp/ttt_menu_response/page_template.jsp")).forward(request,response);
				}
			}
			else if(op!=null && op.equals("ttt_logout")) //trb is not NULL but wants to logout
			{
				System.out.println("op=ttt_logout is called from ActionController.java");
				AllLoginHistoryBean ab=(AllLoginHistoryBean)session.getAttribute("all_login_hist_bean");

				if(ab!=null)
				{
					tttsqlDao.updateInsertGenericSqlCmd("update all_login_hist set logout_time=now(), remarks='logged-out' where login_hist_index="+ab.getLogin_hist_index());
				}

				if(allSessions.containsKey(session.getId())) allSessions.remove(session.getId());

				for (Enumeration<Object> e = session.getAttributeNames(); e.hasMoreElements(); )
				{
				    String attribName = (String) e.nextElement();
				    System.out.println("session attribute to be removed: "+attribName);
			    	session.removeAttribute(attribName);
				}
				response.sendRedirect(response.encodeRedirectURL(MenuLink.TTT_CONTEXT));
			}
			//****** LOGIN SESSION AVAILABLE ********** from here, trb is not null and op=null or op!=null
			else //trb is not NULL but wants to take some action
			{
				CurriculumCurrentBean curriculumBean=(CurriculumCurrentBean)session.getAttribute("curriculum_bean");
				if(curriculumBean==null)
				{
					if(trb.getLoginLevel()==1)//only admin jobs
					{
						if(op==null || op.equals(""))
						{
							request.setAttribute("body_panel", "jqx_home_body.jsp");
							((RequestDispatcher)request.getRequestDispatcher("jsp/admin_menu_response/jqx_page_template.jsp")).forward(request,response);
						}
						else if(op.equals("menu_ttt_admin_manage_hospitals"))
						{
							request.setAttribute("body_panel", "jqx_hospital_management.jsp");
							((RequestDispatcher)request.getRequestDispatcher("jsp/admin_menu_response/jqx_page_template.jsp")).forward(request,response);
						}
						else if(op.equals("menu_ttt_admin_manage_accounts"))
						{
							request.setAttribute("body_panel", "jqx_account_management.jsp");
							((RequestDispatcher)request.getRequestDispatcher("jsp/admin_menu_response/jqx_page_template.jsp")).forward(request,response);
						}
						else if(op.indexOf("ajax")!=-1)
						{
							if(op.equals("ajax_action_hospital_management")) //COMMON
							{
								try
								{
									String action=(String)request.getParameter("action");//add, edit, delete
									HospitalBean hb=new HospitalBean();
									hb.setHospitalId(request.getParameter("hospitalId"));
									hb.setHospitalName(request.getParameter("hospitalName"));
									hb.setCountry(tttsqlDao.getCountryName(Integer.parseInt((String)request.getParameter("countryId"))));
									hb.setPhone(request.getParameter("phone"));
									hb.setValid(request.getParameter("alive").equals("true")? true: false);
									hb.setCreationTime(Utils.currentTimestamp());
									hb.setCreatorId(trb.getUserId());
									hb.setRemarks(action+" by "+trb.getUserId()+" at "+Utils.currentTimestamp());
																
									//Utils.delay(5);

									if(action.equals("add"))
									{
										System.out.println("INFO: add hospital_management is called here");
										tttsqlDao.updateInsertGenericSqlCmd(hb.getInsertStmt());								
										response.getWriter().write("true:add[À]hospitalId="+hb.getHospitalId());
									}
									else if(action.equals("edit"))
									{
										System.out.println("INFO: edit hospital_management is called here");
										tttsqlDao.updateInsertGenericSqlCmd(hb.getUpdateStmt());
										response.getWriter().write("true:edit[À]hospitalId="+hb.getHospitalId());						
									}
								}
								catch(Exception e)
								{
									Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
									response.getWriter().write("ajax_action_hospital_management:Error: "+e.getMessage()+"<br/>"+Message.inform_to_admin_about_exception+"<br/>E-Mail: "+Utils.csr_email_address);
								}
							}
							else if(op.equals("ajax_action_account_management")) //COMMON
							{
								try
								{
									String action=(String)request.getParameter("action");//add, edit, delete
									AllRegisteredUserBean rb=new AllRegisteredUserBean();
									rb.setAppId(trb.getAppId());	
									System.out.println(request.getParameter("firstName")+" "+request.getParameter("lastName"));
									rb.setFirstName(Utils.getFirstCapitalString(request.getParameter("firstName")));
									rb.setLastName(Utils.getFirstCapitalString(request.getParameter("lastName")));
									rb.setHospitalId(request.getParameter("hospitalId"));
									rb.setUserId(rb.getFirstName().toLowerCase()+"."+rb.getLastName().toLowerCase()+"."+rb.getHospitalId());
									rb.setLoginLevel(Integer.parseInt(request.getParameter("loginLevel")));
									rb.setEmail(request.getParameter("emailId"));
									rb.setPassword(new SecureCodeGenerator(10).generate());//generate
									rb.setResetPassword(request.getParameter("resetPassword").equals("true")? 1: 0);
									rb.setAccessLimitTime(new Timestamp(Long.parseLong(request.getParameter("accessLimitDate"))));
									rb.setPracticeYear(request.getParameter("practiceYear"));
									rb.setHealthDiscipline(request.getParameter("healthDiscipline"));
									rb.setPrimayClinicalPractice(request.getParameter("primaryClinicalPractice"));
									rb.setSeniority(Integer.parseInt(request.getParameter("seniority")));
									rb.setCreationTime(Utils.currentTimestamp());
									rb.setCreatorId(trb.getUserId());
									rb.setRemarks(action+" by "+trb.getUserId()+" at "+Utils.currentTimestamp());
									rb.setValid(request.getParameter("validRecord").equals("true")? true: false);

									System.out.println(rb.toString());
									Utils.delay(5);

									if(action.equals("add"))
									{
										System.out.println("INFO: add account_management is called here");
										tttsqlDao.updateInsertGenericSqlCmd(rb.getInsertStmt());
										EmailText.emailCreateOrUpdatedPassword("TTT", rb, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.TTT_CONTEXT, true);
										response.getWriter().write("true:add[À]hospitalId="+rb.getHospitalId()+"&userId="+rb.getUserId());
									}
									else if(action.equals("edit"))
									{
										System.out.println("INFO: edit account_management is called here");
										tttsqlDao.updateInsertGenericSqlCmd(rb.getUpdateStmt());
										response.getWriter().write("true:edit[À]hospitalId="+rb.getHospitalId()+"&userId="+rb.getUserId());	
									}
									
								}
								catch(Exception e)
								{
									Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
									response.getWriter().write("ajax_action_account_management:Error: "+e.getMessage()+"<br/>"+Message.inform_to_admin_about_exception+"<br/>E-Mail: "+Utils.csr_email_address);
								}
							}
						}
					}
					else
					{
						//provide error page
						throw new Exception("No curriclum assigned, but logged in now and login level is not in Admin level (1)");
					}
				}
				else
				{
					ParticipantType pt=curriculumBean.getParticipantType(trb.getUserId());
					switch(pt)
					{
						case LECTURER:
						case TEACHING_ASSISTANT:
							if(op==null || op.equals(""))
							{
								request.setAttribute("body_panel", "jqx_home_body.jsp");
								((RequestDispatcher)request.getRequestDispatcher("jsp/ttt_central_menu_response/jqx_page_template.jsp")).forward(request,response);
							}
							break;
						case INTERACTIVE_VIEWER1:
						case INTERACTIVE_VIEWER2:
							if(op==null || op.equals(""))
							{
								request.setAttribute("body_panel", "jqx_home_body.jsp");
								((RequestDispatcher)request.getRequestDispatcher("jsp/interactive_viewer_menu_response/jqx_page_template.jsp")).forward(request,response);
							}
							break;
						case ONEWAY_VIEWER1:
						case ONEWAY_VIEWER2:
						case ONEWAY_VIEWER3:
						case ONEWAY_VIEWER4:
						case ONEWAY_VIEWER5:
						case ONEWAY_VIEWER6:
							if(op==null || op.equals(""))
							{
								request.setAttribute("body_panel", "jqx_home_body.jsp");
								((RequestDispatcher)request.getRequestDispatcher("jsp/oneway_viewer_menu_response/jqx_page_template.jsp")).forward(request,response);
							}
							break;
						default: break;
					}
				}

			}
			/*
			else if(op.indexOf("ajax")!=-1)
			{		System.out.println("INFO: ----- 3 0 ------");
					if(op.equals("ajax_ttt_reset_password")) //COMMON
					{
						try
						{
							String login_password1=(String)request.getParameter("login_password1");
							String login_password2=(String)request.getParameter("login_password2");
							String login_id=(String)session.getAttribute("login_id");
							//TEMP COMMENT JAN-26-2015

							trb=(AllRegisteredUserBean)session.getAttribute("trb");
							if(!login_password1.equals(login_password2)) response.getWriter().write("false:Two passwords are not identical.\nPlease type again!");
							else
							{
								String sqlCmd="update all_registered_user set password='"+login_password1+"', reset_password=0 where app_id="+trb.getAppId()+" and user_id='"+login_id+"';";

								int rows=tttsqlDao.updateRegisteredUserTable(sqlCmd);


								if(rows!=1) response.getWriter().write("false:Warning: the number of rows in the all_registered_user table has been updated more than one.");
								else
								{	//update login_hist table
									//AllLoginHistoryBean lb=new AllLoginHistoryBean(0L,trb.getAppId(), login_id, Utils.currentTimestamp(), request.getRemoteAddr());
									//lb=tttloginHistoryDao.create(lb);

									//session.setAttribute("all_login_hist_bean", lb);//use this when the session is expired

									//email
									AllRegisteredUserBean tempRegisteredUserBean=trb.copy();
									tempRegisteredUserBean.setPassword(login_password1);

									EmailText.emailCreateOrUpdatedPassword("TTT", tempRegisteredUserBean, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.TTT_CONTEXT, false );

									response.getWriter().write("true:"+MenuLink.TTT_CONTEXT);
								}
							}

						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_ttt_central_create_login_account"))//COMMON
					{
						try
						{
							//TEMP COMMENT JAN-26-2015

							AllRegisteredUserBean rbTemp=new AllRegisteredUserBean();
							int login_level=Integer.parseInt(request.getParameter("login_level"));

							String user_id=request.getParameter("first_name").toLowerCase()+"."+request.getParameter("last_name").toLowerCase()+".0"+login_level;

							rbTemp.setApp_id(trb.getApp_id());
							rbTemp.setUser_id(user_id);
							rbTemp.setFirst_name(request.getParameter("first_name"));
							rbTemp.setLast_name(request.getParameter("last_name"));
							rbTemp.setLogin_level(login_level);
							rbTemp.setEmail(request.getParameter("email"));
							rbTemp.setPassword(new SecureCodeGenerator(10).generate());//generate
							rbTemp.setReset_password(1);//true
							rbTemp.setCreation_time(Utils.currentTimestamp());
							//rbTemp.setAccessible_ids(request.getParameter("ttt_ids")==null? "": request.getParameter("ttt_ids"));//not used
							rbTemp.setPractice_year(request.getParameter("practice_year")==null? "": request.getParameter("practice_year"));
							rbTemp.setHealth_discipline(request.getParameter("health_discipline")==null? "": request.getParameter("health_discipline"));
							rbTemp.setPrimay_clinical_practice(request.getParameter("primay_clinical_practice")==null? "": request.getParameter("primay_clinical_practice"));
							rbTemp.setSeniority(request.getParameter("seniority")==null? 0: Integer.parseInt(request.getParameter("seniority")));

							rbTemp.setAccess_limit_time(new Timestamp(Long.parseLong(request.getParameter("access_limit_date"))));

							rbTemp.setMax_groups_to_review(request.getParameter("max_review_groups")==null? 1: Integer.parseInt(request.getParameter("max_review_groups")));
							rbTemp.setItem_assignment_type(request.getParameter("item_assignment_type")==null? "": request.getParameter("item_assignment_type"));
							rbTemp.setMy_review_confirmatory(request.getParameter("review_confirmatory")==null? false: (request.getParameter("review_confirmatory").equals("true")? true: false));

							rbTemp.setCreation_time(Utils.currentTimestamp());
							rbTemp.setCreator_id(trb.getUser_id());


							tttsqlDao.createRegisteredUserRecord(rbTemp);
							EmailText.emailCreateOrUpdatedPassword((String)session.getAttribute("app_symbol"), rbTemp, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.CARM_CENTRAL_CONTEXT, true);
							response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);

						}
						catch(Exception e)
						{
							//if insertion is failed then there will be no insertion to the database.
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}


					else if(op.equals("ajax_ttt_central_update_login_account_get_account_info"))//COMMON
					{
						try
						{
							//TEMP COMMENT JAN-26-2015
							String login_id=request.getParameter("login_id");
							AllRegisteredUserBean tempRb=tttsqlDao.getOneRecordFromRegisteredUserTable("where app_id="+trb.getApp_id()+" and user_id='"+login_id+"';");


							if(tempRb.getUser_id().equals(login_id))
								response.getWriter().write("true:"+tempRb.toStringParameters());
							else
								response.getWriter().write("false:Database query for given user_id, '"+login_id+"' didn't match with the given one. Database must be corrupted.");

						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_ttt_central_update_login_account"))//COMMON
					{
						try
						{
							//TEMP COMMENT JAN-26-2015

							AllRegisteredUserBean rbTemp=new AllRegisteredUserBean();
							int login_level=Integer.parseInt(request.getParameter("login_level"));

							rbTemp.setUser_id(request.getParameter("user_id"));

							rbTemp.setLogin_level(login_level);
							rbTemp.setEmail(request.getParameter("email"));

							rbTemp.setPractice_year(request.getParameter("practice_year")==null? "": request.getParameter("practice_year"));
							rbTemp.setHealth_discipline(request.getParameter("health_discipline")==null? "": request.getParameter("health_discipline"));
							rbTemp.setPrimay_clinical_practice(request.getParameter("primay_clinical_practice")==null? "": request.getParameter("primay_clinical_practice"));
							rbTemp.setSeniority(request.getParameter("seniority")==null? 0: Integer.parseInt(request.getParameter("seniority")));

							rbTemp.setAccess_limit_time(new Timestamp(Long.parseLong(request.getParameter("access_limit_date"))));

							rbTemp.setMax_groups_to_review(request.getParameter("max_review_groups")==null? 1: Integer.parseInt(request.getParameter("max_review_groups")));
							rbTemp.setItem_assignment_type(request.getParameter("item_assignment_type")==null? "": request.getParameter("item_assignment_type"));
							rbTemp.setMy_review_confirmatory(request.getParameter("review_confirmatory")==null? false: (request.getParameter("review_confirmatory").equals("true")? true: false));

							rbTemp.setCreation_time(Utils.currentTimestamp());
							rbTemp.setCreator_id(trb.getUser_id());

							String sqlCmd = "update all_registered_user set email='"+rbTemp.getEmail()+"', "+
							"login_level="+rbTemp.getLogin_level()+", "+
							"access_limit_time='"+rbTemp.getAccess_limit_time()+"', "+
							"practice_year='"+rbTemp.getPractice_year()+"', "+
							"health_discipline='"+rbTemp.getHealth_discipline()+"', "+
							"primay_clinical_practice='"+rbTemp.getPrimay_clinical_practice()+"', "+
							"seniority="+rbTemp.getSeniority()+", "+

							"max_groups_to_review="+rbTemp.getMax_groups_to_review()+", "+
							"item_assignment_type='"+rbTemp.getItem_assignment_type()+"', "+
							"my_review_confirmatory="+rbTemp.isMy_review_confirmatory()+", "+
							"remarks='updated at "+rbTemp.getCreation_time()+" by "+rbTemp.getCreator_id()+"' "+
							"where app_id="+trb.getApp_id()+" and user_id='"+rbTemp.getUser_id()+"';";

							//check max_groups_review with the existing groups if it is less that groups already assigned
							if((rbTemp.getLogin_level()==3 || rbTemp.getLogin_level()==4) &&
							   (rbTemp.getMax_groups_to_review()<tttsqlDao.getGenericLong("select count(distinct(group_id)) from all_item_reviewer_mx where app_id="+trb.getApp_id()+" and "+(rbTemp.getLogin_level()==3? "mediator_id":"reviewer_id")+"='"+rbTemp.getUser_id()+"'")))
									response.getWriter().write("false:The maximum groups to review should be greater than "+rbTemp.getMax_groups_to_review());
							else
							{
								tttsqlDao.updateRegisteredUserTable(StringEscapeUtils.escapeJava(sqlCmd));
								response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
							}

						}
						catch(Exception e)
						{
							//if insertion is failed then there will be no insertion to the database.
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
							response.getWriter().write("false:En error has been occurred\n\n"+e.getMessage()+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}

			}*/


		}
		catch(Exception e)
		{
			Map params = request.getParameterMap();
		    Iterator i = params.keySet().iterator();
		    String all_params="";
		    while ( i.hasNext() )
	        {
		    	String key = (String) i.next();
		    	String value = ((String[]) params.get( key ))[ 0 ];
		    	all_params+=key+"="+value+"<br>";
	        }
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from ActionController.java of TTT");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.csr_email_address);
			nameList.add("ADMIN");
			new MailInfo(Utils.csr_email_address,emailList,nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+".");
			forwardErrorPage(request,response,e.toString());
		}
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	public void forwardWarningPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{
		request.setAttribute("warning_msg", msg+" at "+Utils.currentTimestamp()+"!!!");
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/public_page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.csr_email_address+"?subject=CSR ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/public_page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjax(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{
		request.setAttribute("error_msg", msg+" at "+Utils.currentTimestamp()+"!!!<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.csr_email_address+"?subject=CSR ERROR REPORT' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>");
		((RequestDispatcher)request.getRequestDispatcher("jsp/error/servlet_error_page.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjaxDlg(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{
		emailList.clear();
		nameList.clear();
		emailList.add(Utils.csr_email_address);
		nameList.add("ADMIN");
		new MailInfo(Utils.csr_email_address,emailList,nameList,Utils.smtp,Message.error_report_subject, msg+" Generated at "+Utils.currentTimestamp()+".");
		response.getWriter().write("false:"+Message.general_error_msg+"\n\n"+msg+"\n\n"+Message.inform_to_admin_about_exception2);
	}

	private boolean isAlreadyLoggedInNow(String login_id, int course_number, HttpSession session)
	{
		System.out.println("map length="+allSessions.size()+" in isAlreadyLoggedInNow() of from ActionController.java of TTT");

		for (Map.Entry<String, HttpSession> entry : allSessions.entrySet())
		{
		    //String key = entry.getKey();
		    HttpSession eachSession = entry.getValue();
		    String loginId=(String)eachSession.getAttribute("login_id");
		    if(loginId!=null && loginId.equals(login_id))
		    {
		    	CurriculumCurrentBean cb=(CurriculumCurrentBean) eachSession.getAttribute("curriculum_bean");
		    	if(cb!=null && cb.getParticipantType(login_id)!=ParticipantType.NONE && cb.getCourseNumber()==course_number) return true;//aleady logged in
		    }

		}
		return false;
	}
}
