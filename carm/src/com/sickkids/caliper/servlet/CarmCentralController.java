package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Enumeration;
import java.util.HashMap;
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

import org.apache.commons.lang.StringEscapeUtils;

import com.sickkids.caliper.common.EmailText;
import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.SecureCodeGenerator;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.CarmDeMX;

import com.sickkids.caliper.dao.AllApplicationBean;
import com.sickkids.caliper.dao.AllForumBean;
import com.sickkids.caliper.dao.AllItemBean;
import com.sickkids.caliper.dao.AllItemReviewerBean;
import com.sickkids.caliper.dao.AllReviewDataBean;
import com.sickkids.caliper.dao.AllReviewDataHistBean;
import com.sickkids.caliper.dao.AllReviewGroupBean;
import com.sickkids.caliper.dao.CarmAssayUnitBean;

import com.sickkids.caliper.dao.OracleSQLDao;


import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.AllLoginHistoryDao;
import com.sickkids.caliper.dao.AllRegisteredUserBean;

import com.sickkids.caliper.dao.CarmSQLDao;

import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.service.MailInfo;

/**
 * Servlet implementation class CarmCentralController
 */
public class CarmCentralController extends HttpServlet implements Servlet
{
	static final long serialVersionUID = 1L;
	static int count=0;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;

	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();

	private CarmSQLDao csqlDao=null;
	private OracleSQLDao oracleSqlDao=null;
	
	private AllLoginHistoryDao cloginHistoryDao=null;

	public void init(ServletConfig config)
	{
	    this.config = config;
	    this.app = config.getServletContext();
	    this.cp=(ConnectionPool)app.getAttribute("connectionPool");
	    this.csqlDao=(CarmSQLDao)app.getAttribute("csqlDao");
	    this.oracleSqlDao=(OracleSQLDao)app.getAttribute("oracleSqlDao");
	    this.cloginHistoryDao=new AllLoginHistoryDao(cp);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in CarmCentralController.java");

		request.setCharacterEncoding("UTF-8");//put this otherwise UNI-Code characters will be broken.
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-store,no-cache, must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		HttpSession session = request.getSession(true);//if(session.isNew())
		
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in CarmCentralController.java");

		try
		{
			//Here, first check if the person is login, otherwise go back to the public home
			AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
			
			if(crb==null)//no logged in but op is not null (session out, hacking, look for password and ID)
			{
				if(op!=null && op.indexOf("ajax")!=-1)
				{				
					if(op.equals("ajax_carm_central_login_check"))
					{
						try
						{
							String app_id=(String)request.getParameter("app_id");
							String carm_login_id=(String)request.getParameter("carm_login_id");
							String carm_login_password=(String)request.getParameter("carm_login_password");
							System.out.println(op+": "+carm_login_id+" "+carm_login_password);
							if(carm_login_id!=null && !carm_login_id.equals("") && carm_login_password!=null && !carm_login_password.equals(""))
							{	
								crb=csqlDao.getOneRecordFromRegisteredUserTable("where app_id="+app_id+" and user_id='"+carm_login_id+"'");
								if(!csqlDao.existSameKey("select app_id from all_application where app_id="+app_id))
								{
									response.getWriter().write("false:The application you are trying to login is no longer existing!");
								}
								else if(crb.getUser_id().equalsIgnoreCase(carm_login_id))
								{
									if(crb.getLogin_level()>1 && crb.getLogin_level()<6 && crb.getAccess_limit_time().before(Utils.currentTimestamp()))
									{
										response.getWriter().write("false:We are sorry to say that your access time is over.\nPlease contact Dr. Chris at chris@sickkids.ca!");
									}
									else if(crb.getPassword().equals(carm_login_password))
									{
										//every thing is OK, ie: login is successful, set login session variables
										session.setAttribute("carm_login_id", carm_login_id);
										session.setAttribute("carm_logn_first_name", crb.getFirst_name());
										session.setAttribute("carm_login_last_name", crb.getLast_name());
										session.setAttribute("carm_login_password", carm_login_password);
										session.setAttribute("app_symbol", csqlDao.getGenericString("select app_symbol from all_application where app_id="+crb.getApp_id()));
										session.setAttribute("app_obj",(AllApplicationBean)((List<AllApplicationBean>)csqlDao.getListFromAllApplicationTable("where app_id="+crb.getApp_id())).get(0));
										session.setAttribute("crb", crb);
																			
										//if your password needs to be reset, redirect it to password reset
										if(crb.getReset_password()==1)//0:false (dirty), 1:true (reset it)
											((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_reset_password.jsp")).forward(request,response);
										else //normal login which is not the first time login
										{
											AllLoginHistoryBean lb=new AllLoginHistoryBean(0L, crb.getApp_id(), carm_login_id,Utils.currentTimestamp(),request.getRemoteAddr());
											lb=cloginHistoryDao.create(lb);
											session.setAttribute("all_login_hist_bean", lb);
											response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
										}
									}
									else response.getWriter().write("false:"+Message.incorrect_password);
								}
								else response.getWriter().write("false:Your LoginID is not in our database.\nPlease try again after checking.");
							}
							else response.getWriter().write("false:"+Message.invalid_loginID);
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_carm_central_forgot_pwd"))
					{
						((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_login_forgot_password.jsp")).forward(request,response);
					}
					else if(op.equals("ajax_carm_central_forgot_pwd_check"))//ajax request
					{	
						try
						{
							String app_id=(String)request.getParameter("app_id");
							String email=(String)request.getParameter("carm_login_email");
							if(email!=null && !email.equals(""))
							{
								//one email and more than one id and password
								List<AllRegisteredUserBean> idPasswordList=csqlDao.getListFromRegisteredUserTable("where app_id="+app_id+" and email='"+email+"'");
								
								if(idPasswordList.size()>0) 
								{
									
									EmailText.emailForgottenPassword(csqlDao.getGenericString("select app_symbol from all_application where app_id="+app_id), idPasswordList, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.CARM_CENTRAL_CONTEXT );
									response.getWriter().write("true:Please check your email for your LoginID/Password!");
								}
								else response.getWriter().write("false:"+Message.non_existing_email);
							}
							else response.getWriter().write("false:"+Message.incorrect_email);
						}
						catch(Exception e)
						{	
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
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
					request.setAttribute("body_panel", "/jsp/carm_central_menu_response/login_body.jsp");
					((RequestDispatcher)request.getRequestDispatcher("jsp/login_page_template.jsp")).forward(request,response);
				}
			}
			//****** LOGIN SESSION AVAILABLE ********** from here, crb is not null and op=null or op!=null
			else if(op==null || op.equals("central_home") || op.equals(""))//public->central->local
			{
				request.setAttribute("body_panel", "/jsp/carm_central_menu_response/jqx_home_body.jsp");
				//request.setAttribute("my_event_list", getEventObjList(crb.getLogin_level(), crb.getAccessible_carm_ids(), ""));//"" mean ALL review_status
				((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
				
				//((RequestDispatcher)request.getRequestDispatcher("jsp/jqxTreeDemo.jsp")).forward(request,response);
			}
			else if(op.indexOf("menu")!=-1)
			{
				if(op.indexOf("menu_carm_central")!=-1)
				{


					//************** Create/Update Login Account from Admin *********************************
					if(op.equals("menu_carm_central_admin_create_login_account"))
					{
						if(crb.getLogin_level()==1 || crb.getLogin_level()==2)
						{
							request.setAttribute("body_panel", "/jsp/carm_central_menu_response/create_login_account.jsp");
							//request.setAttribute("carm_id_list", (List<CarmDeMX>)csqlDao.getCarmDeMXList(""));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
						}
					}
					else if(op.equals("menu_carm_central_admin_update_login_account"))
					{
						if(crb.getLogin_level()==1 || crb.getLogin_level()==2)
						{	
							List<AllRegisteredUserBean> accountList=(List<AllRegisteredUserBean>)csqlDao.getListFromRegisteredUserTable("where app_id="+crb.getApp_id()+" and login_level>1 order by user_id asc;");
							if(accountList.size()>0)
							{
								request.setAttribute("account_list", accountList);
								request.setAttribute("body_panel", "/jsp/carm_central_menu_response/update_login_account.jsp");
							}
							else
								request.setAttribute("body_panel", "/jsp/carm_central_menu_response/update_login_account_alert.jsp");
							
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
						}
					}
					else if(op.equals("menu_carm_central_admin_group_management"))//a single group not multiple groups
					{
						if(crb.getLogin_level()==1 || crb.getLogin_level()==2)
						{						
							request.setAttribute("body_panel", "/jsp/carm_central_menu_response/group_management.jsp");
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
						}
					}
					else if(op.equals("menu_carm_central_admin_map_event_group_reviewer"))
					{
						if(crb.getLogin_level()==1 || crb.getLogin_level()==2)
						{	
							if(Integer.parseInt(csqlDao.getGenericString("select count(group_id) from all_review_group where app_id="+crb.getApp_id()))<1)
								request.setAttribute("body_panel", "/jsp/carm_central_menu_response/map_reviewers_to_items_alert.jsp");					
							else
								request.setAttribute("body_panel", "/jsp/carm_central_menu_response/map_reviewers_to_items.jsp");
							request.setAttribute("reviewers_to_items_list", (List<AllItemReviewerBean>)csqlDao.getListFromAllItemReviewerMXTable("where a.app_id=b.app_id and a.group_id=b.group_id and a.app_id="+crb.getApp_id()+" order by a.group_id, a.item_id, a.reviewer_id asc"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
						}
					}
					else if(op.equals("menu_carm_central_admin_clinical_summary"))
					{
						if(crb.getLogin_level()==1 || crb.getLogin_level()==2)
						{						
							request.setAttribute("body_panel", "/jsp/carm_central_menu_response/edit_clinical_summary.jsp");
							//request.setAttribute("drugList", (List<MedicationSafetyDrugNamesBean>)csqlDao.getDrugList("select * from carm_drug_names order by drug_name asc"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/jqx_central_page_template.jsp")).forward(request,response);
						}
					}
				}
			}
			
			else if(op.indexOf("ajax")!=-1)
			{				
				if(op.indexOf("ajax_carm_central")!=-1)
				{
					if(op.equals("ajax_carm_central_reset_password")) //COMMON
					{
						try
						{
							String carm_login_password1=(String)request.getParameter("carm_login_password1");
							String carm_login_password2=(String)request.getParameter("carm_login_password2");
							String carm_login_id=(String)session.getAttribute("carm_login_id");
							crb=(AllRegisteredUserBean)session.getAttribute("crb");
							if(carm_login_id==null) response.getWriter().write("session_timeout");//test it out later
							else if(!carm_login_password1.equals(carm_login_password2)) response.getWriter().write("false:Two passwords are not identical.\nPlease type again!");
							else
							{	
								String sqlCmd="update all_registered_user set password='"+carm_login_password1+"', reset_password=0 where app_id="+crb.getApp_id()+" and user_id='"+carm_login_id+"';";
								
								int rows=csqlDao.updateRegisteredUserTable(sqlCmd);
								
								
								if(rows!=1) response.getWriter().write("false:Warning: the number of rows in the carm_registered_user table has been updated more than one.");
								else
								{	//update login_hist table
									AllLoginHistoryBean lb=new AllLoginHistoryBean(0L,crb.getApp_id(), carm_login_id,Utils.currentTimestamp(),request.getRemoteAddr());
									lb=cloginHistoryDao.create(lb);
									
									session.setAttribute("all_login_hist_bean", lb);//use this when the session is expired
									
									//email
									AllRegisteredUserBean tempRegisteredUserBean=crb.copy();
									tempRegisteredUserBean.setPassword(carm_login_password1);
									
									EmailText.emailCreateOrUpdatedPassword((String)session.getAttribute("app_symbol"), tempRegisteredUserBean, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()),MenuLink.CARM_CENTRAL_CONTEXT, false );
																	
									response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);								
								}
							}

						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_carm_central_create_login_account"))//COMMON
					{
						try
						{	
							//Utils.printRequest(request, response);
							AllRegisteredUserBean rbTemp=new AllRegisteredUserBean();
							int login_level=Integer.parseInt(request.getParameter("login_level"));
							
							String user_id=request.getParameter("first_name").toLowerCase()+"."+request.getParameter("last_name").toLowerCase()+".0"+login_level;
							
							rbTemp.setApp_id(crb.getApp_id());
							rbTemp.setUser_id(user_id);
							rbTemp.setFirst_name(request.getParameter("first_name"));
							rbTemp.setLast_name(request.getParameter("last_name"));
							rbTemp.setLogin_level(login_level);
							rbTemp.setEmail(request.getParameter("email"));
							rbTemp.setPassword(new SecureCodeGenerator(10).generate());//generate
							rbTemp.setReset_password(1);//true
							rbTemp.setCreation_time(Utils.currentTimestamp());
							//rbTemp.setAccessible_ids(request.getParameter("carm_ids")==null? "": request.getParameter("carm_ids"));//not used
							rbTemp.setPractice_year(request.getParameter("practice_year")==null? "": request.getParameter("practice_year"));
							rbTemp.setHealth_discipline(request.getParameter("health_discipline")==null? "": request.getParameter("health_discipline"));
							rbTemp.setPrimay_clinical_practice(request.getParameter("primay_clinical_practice")==null? "": request.getParameter("primay_clinical_practice"));
							rbTemp.setSeniority(request.getParameter("seniority")==null? 0: Integer.parseInt(request.getParameter("seniority")));
																					
							rbTemp.setAccess_limit_time(new Timestamp(Long.parseLong(request.getParameter("access_limit_date"))));
							
							rbTemp.setMax_groups_to_review(request.getParameter("max_review_groups")==null? 1: Integer.parseInt(request.getParameter("max_review_groups")));
							rbTemp.setItem_assignment_type(request.getParameter("item_assignment_type")==null? "": request.getParameter("item_assignment_type"));
							rbTemp.setMy_review_confirmatory(request.getParameter("review_confirmatory")==null? false: (request.getParameter("review_confirmatory").equals("true")? true: false));
							
							rbTemp.setCreation_time(Utils.currentTimestamp());
							rbTemp.setCreator_id(crb.getUser_id());
							
							//System.out.println("review_confirmatory"+request.getParameter("review_confirmatory"));
							
							//if(true) throw new Exception("testing ...");
							
							csqlDao.createRegisteredUserRecord(rbTemp);
							EmailText.emailCreateOrUpdatedPassword((String)session.getAttribute("app_symbol"), rbTemp, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.CARM_CENTRAL_CONTEXT, true);
							response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);													
						}
						catch(Exception e)
						{
							//if insertion is failed then there will be no insertion to the database.
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}


					else if(op.equals("ajax_carm_central_update_login_account_get_account_info"))//COMMON
					{
						try
						{
							String login_id=request.getParameter("login_id");
							AllRegisteredUserBean tempRb=csqlDao.getOneRecordFromRegisteredUserTable("where app_id="+crb.getApp_id()+" and user_id='"+login_id+"';");
							
							if(tempRb.getUser_id().equals(login_id))
								response.getWriter().write("true:"+tempRb.toStringParameters());
							else
								response.getWriter().write("false:Database query for given user_id, '"+login_id+"' didn't match with the given one. Database must be corrupted.");
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_carm_central_update_login_account"))//COMMON
					{
						try
						{	
							//Utils.printRequest(request, response);
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
							rbTemp.setCreator_id(crb.getUser_id());
							
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
							"where app_id="+crb.getApp_id()+" and user_id='"+rbTemp.getUser_id()+"';";

							//check max_groups_review with the existing groups if it is less that groups already assigned
							if((rbTemp.getLogin_level()==3 || rbTemp.getLogin_level()==4) &&
							   (rbTemp.getMax_groups_to_review()<csqlDao.getGenericLong("select count(distinct(group_id)) from all_item_reviewer_mx where app_id="+crb.getApp_id()+" and "+(rbTemp.getLogin_level()==3? "mediator_id":"reviewer_id")+"='"+rbTemp.getUser_id()+"'")))
									response.getWriter().write("false:The maximum groups to review should be greater than "+rbTemp.getMax_groups_to_review());
							else
							{
								csqlDao.updateRegisteredUserTable(StringEscapeUtils.escapeJava(sqlCmd));
								response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
							}																																							
						}
						catch(Exception e)
						{
							//if insertion is failed then there will be no insertion to the database.
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:En error has been occurred\n\n"+e.getMessage()+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}						
					}
					else if(op.equals("ajax_carm_central_action_item_reviewer_mx"))//COMMON
					{
						try
						{								
							AllItemReviewerBean ib=new AllItemReviewerBean();
							ib.setApp_id(Integer.parseInt(request.getParameter("app_id")));
							ib.setGroup_id(Integer.parseInt(request.getParameter("group_id")));
							ib.setItem_id(Integer.parseInt(request.getParameter("item_id")));
							ib.setReviewer_id(request.getParameter("reviewer_id"));
							ib.setMediator_id(request.getParameter("mediator_id"));
							ib.setReviewer_action_status(request.getParameter("reviewer_action_status"));
							ib.setAction_status_time(Utils.currentTimestamp());
							ib.setCreator_id(crb.getUser_id());
							ib.setCreation_time(Utils.currentTimestamp());
							ib.setTime_out(new Timestamp(Long.parseLong(request.getParameter("time_out"))));
							
							ib.setConfirmatory(request.getParameter("confirmatory").equals("true")? true: false);
							
							//Utils.delay(5);
							String action=(String)request.getParameter("action");//add, edit, delete
							String sqlCmd="select * from all_item_reviewer_mx where app_id="+ib.getApp_id()+" and group_id="+ib.getGroup_id()+" and item_id="+ib.getItem_id()+" and reviewer_id='"+ib.getReviewer_id()+"'";
							if(action.equals("add"))
							{			
								if(csqlDao.existSameKey(sqlCmd)) //check if the same reveiwer, item, group_id and app_id
									response.getWriter().write("false:The selected reviewer is already existing in the selected group and item!\n\nYou can't add but edit!");
								else if(!checkIfMaxGroupsOver(ib.getApp_id(), ib.getGroup_id(), ib.getReviewer_id(), 4))//check if the reviewer exceeds the max groups to review
									response.getWriter().write("false:The selected reviewer is exceeding the maximum groups to review!");
								else if(!checkIfMaxGroupsOver(ib.getApp_id(), ib.getGroup_id(), ib.getMediator_id(), 3))//check if the mediator exceeds the max groups to review
									response.getWriter().write("false:The selected mediator is exceeding the maximum groups to mediate!");
								else if(!checkIfReviewersPerItem(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id()))//check if the number of reviewers exceeds per item
									response.getWriter().write("false:The addition is exceeding the maximum number of reviewers per item!");
								else 
								{
									if(!csqlDao.existSameKey("select app_id, item_id from all_item where app_id="+ib.getApp_id()+" and item_id="+ib.getItem_id()))
									{
										AllItemBean ab=new AllItemBean();
										ab.setApp_id(ib.getApp_id());
										ab.setItem_id(ib.getItem_id());
										ab.setCreation_time(ib.getCreation_time());
										ab.setCreator_id(ib.getCreator_id());
										csqlDao.updateInsertGenericSqlCmd(ab.getInsertStmt());																				
									}
									csqlDao.updateInsertGenericSql3Cmds(ib.getInsertStmt(),
																		AllReviewDataBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), ib.getReviewer_id()),//just clean up
																		AllReviewDataHistBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), ib.getReviewer_id()) //just clean up
																		);
									response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
								}			
							}
							else if(action.equals("edit"))
							{
								System.out.println("edit is called here");
								//same reviewers between old and new
								if(request.getParameter("reviewer_id_old").equals(ib.getReviewer_id()))
								{
									 if(!checkIfMaxGroupsOver(ib.getApp_id(), ib.getGroup_id(), ib.getMediator_id(), 3))//check if the mediator exceeds the max groups to review
											response.getWriter().write("false:The selected mediator is exceeding the maximum groups to mediate!");
									 else
									 {
										 csqlDao.updateInsertGenericSqlCmd(ib.getUpdateStmt(request.getParameter("reviewer_id_old")));//old==new
										 response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
									 }
								} 
								//the selected new reviewer is different from the old reviewer, which is selected in the grid
								else if(csqlDao.existSameKey(sqlCmd)) //check if the same reveiwer, item, group_id and app_id
									response.getWriter().write("false:The selected reviewer is already existing in the selected group and item!");
								else if(!checkIfMaxGroupsOver(ib.getApp_id(), ib.getGroup_id(), ib.getReviewer_id(), 4))//check if the reviewer exceeds the max groups to review
									response.getWriter().write("false:The selected reviewer is exceeding the maximum groups to review!");
								else if(!checkIfMaxGroupsOver(ib.getApp_id(), ib.getGroup_id(), ib.getMediator_id(), 3))//check if the mediator exceeds the max groups to review
									response.getWriter().write("false:The selected mediator is exceeding the maximum groups to mediate!");
								else 
								{
									csqlDao.updateInsertGenericSql3Cmds(ib.getUpdateStmt(request.getParameter("reviewer_id_old")),//replacing with a new reviewer so any existing review_data/review_data_hist will be deleted
																		AllReviewDataBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), request.getParameter("reviewer_id_old")),
																		AllReviewDataHistBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), request.getParameter("reviewer_id_old"))
																		);//old != new
									response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
								}
							}
							else if(action.equals("delete"))
							{
								System.out.println("delete is called here");
								sqlCmd="select * from all_item_reviewer_mx where app_id="+ib.getApp_id()+" and group_id="+ib.getGroup_id()+" and item_id="+ib.getItem_id()+" and reviewer_id='"+ib.getReviewer_id()+"' and reviewer_action_status like '%_NOT_EDITABLE'";
								if(csqlDao.existSameKey(sqlCmd))
									response.getWriter().write("false:The selected record can't be removed since it is already timed-out!");
								else
								{
									sqlCmd="delete from all_item_reviewer_mx where app_id="+ib.getApp_id()+" and group_id="+ib.getGroup_id()+" and item_id="+ib.getItem_id()+" and reviewer_id='"+ib.getReviewer_id()+"'";
									csqlDao.updateInsertGenericSql3Cmds(sqlCmd,
																		AllReviewDataBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), ib.getReviewer_id()),
																		AllReviewDataHistBean.deleteStatement(ib.getApp_id(), ib.getGroup_id(), ib.getItem_id(), ib.getReviewer_id())
																		);
									
									//if there exists an item in the group of the app then no action
									//else remove the item from the item list.
									sqlCmd="select * from all_item_reviewer_mx where app_id="+ib.getApp_id()+" and item_id="+ib.getItem_id();//regardless of group_id
									if(!csqlDao.existSameKey(sqlCmd))
										csqlDao.updateInsertGenericSqlCmd("delete from all_item where app_id="+ib.getApp_id()+" and item_id="+ib.getItem_id());//regardless of group_id
									
									response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
								}
							}
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:En error has been occurred\n\n"+e.getMessage()+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}						
					}
					else if(op.equals("ajax_carm_central_submit_single_group_management"))
					{						
						try
						{								
							AllReviewGroupBean gb=new AllReviewGroupBean();
							gb.setApp_id(crb.getApp_id());
							gb.setGroup_id(1);
							gb.setGroup_name(request.getParameter("group_name"));							
							gb.setTime_out(new Timestamp(Long.parseLong(request.getParameter("time_out"))));
							gb.setReviewers_per_item(Integer.parseInt(request.getParameter("reviewers_per_item")));
							gb.setReviewer_assignment_type(request.getParameter("assignment_type"));
							gb.setConfirmatory(request.getParameter("confirmatory").equals("true")? true: false);
							gb.setDisagreement_process(request.getParameter("disagreement_process"));
							
							gb.setCreation_time(Utils.currentTimestamp());
							gb.setCreator_id(crb.getUser_id());
							
							if(csqlDao.existSameKey("select * from all_review_group where app_id="+gb.getApp_id()+" and group_id="+gb.getGroup_id()))
								csqlDao.updateInsertGenericSqlCmd(gb.getUpdateStmt());
							else //insert
								csqlDao.updateInsertGenericSqlCmd(gb.getInsertStmt());	
							response.getWriter().write("true:"+MenuLink.CARM_CENTRAL_CONTEXT);
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:En error has been occurred\n\n"+e.getMessage()+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}						
					}
					
					//*************************
					//carm specific
					//*************************
					else if(op.equals("ajax_carm_central_save_clinical_summary"))//carm
					{
						try
						{
							String selected_carm_id=request.getParameter("selected_carm_id");							
							String newClinicalSummary=request.getParameter("new_clinical_summary");
							String sqlCmd="update carm_clinical_summary_edit set new_clinical_summary='"+StringEscapeUtils.escapeSql(newClinicalSummary)+"', edited=true, edit_date='"+Utils.currentTimestamp()+"', editor_id='"+crb.getUser_id()+"' where carm_id="+selected_carm_id+";";
							csqlDao.updateInsertGenericSqlCmd(sqlCmd);
							response.getWriter().write("true:");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_carm_central_check_if_carm_id_involved"))//carm
					{
						try
						{
							String selected_carm_id=request.getParameter("carm_id");							
							String selected_user_id=request.getParameter("user_id");
							//create review table for each event - tomorrow
							//String sqlCmd="update carm_clinical_summary_edit set new_clinical_summary='"+StringEscapeUtils.escapeSql(newClinicalSummary)+"', edited=true, edit_date='"+Utils.currentTimestamp()+"', editor_id='"+crb.getUser_id()+"' where carm_id="+selected_carm_id+";";
							//csqlDao.updateInsertGenericSqlCmd(sqlCmd);
							response.getWriter().write("true:");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_carm_central_get_event_obj_list"))
					{
						try
						{
							String review_status=request.getParameter("review_status"); 
							String sqlCmd="select distinct(item_id) from all_item_reviewer_mx ";
							
							if(review_status.equals("FINALIZE")) //Disagreement is meaningful in ('STARTED','COMPLETE_EDITABLE')
							{
								List<String> finalizeItemList=new ArrayList<String>();
								if(crb.getLogin_level()==3)//mediators
								{
									sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+crb.getUser_id()+"' and reviewer_action_status in ('COMPLETE_NOT_EDITABLE','INCOMPLETE_EDITABLE') order by item_id asc;";
									List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
									AllReviewDataBean rb1=null, rb2=null;
									for(String itemId : myItemList)
									{
										List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('COMPLETE_NOT_EDITABLE','INCOMPLETE_EDITABLE')");
										if(reviewerList.size()==2)
										{
											rb1=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
											rb2=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
											
											if(rb1.getRemarks().startsWith("FINALIZED") && rb2.getRemarks().startsWith("FINALIZED")) continue;
											else if(rb1.getPoint_id_revision() != rb2.getPoint_id_revision()) finalizeItemList.add(itemId); //checked
											else if((int)rb1.getPoint_value_number() != (int)rb2.getPoint_value_number()) finalizeItemList.add(itemId);//checked
											else  //in case of a revision number which is greater than one, checked
											
												for(int i=1; i<rb1.getPoint_id_revision();i++)
												{
													AllReviewDataHistBean rhb1=csqlDao.getOneRecordFromAllReviewDataHistTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+rb1.getReviewer_id()+"' and point_id_revision="+i);
													AllReviewDataHistBean rhb2=csqlDao.getOneRecordFromAllReviewDataHistTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+rb2.getReviewer_id()+"' and point_id_revision="+i);
													
													if((int)rhb1.getPoint_value_number()!=(int)rhb2.getPoint_value_number())
													{	
														finalizeItemList.add(itemId);
														break;
													}
												}						
										}
										//else, do not allow to be finalized for zero, one or more
									}
								}
								request.setAttribute("my_event_list", getEventObjListForDisagreement(crb.getLogin_level(), finalizeItemList));
							}
							else if(review_status.equals("DISAGREE")) //Disagreement is meaningful in ('STARTED','COMPLETE_EDITABLE')
							{
								List<String> discordantItemList=new ArrayList<String>();
								if(crb.getLogin_level()==4)//reviewers
								{
									sqlCmd+="where app_id=1 and group_id=1 and reviewer_id='"+crb.getUser_id()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
									List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
									for(String itemId : myItemList)
									{
										List<String> peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_id!='"+crb.getUser_id()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
										AllReviewDataBean myBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+crb.getUser_id()+"'");
										if(myBean.getReview_time()!=null) for(String peerId : peerList)
										{
											AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
											if(peerBean.getReview_time()!=null && myBean.getReview_time()!=null && peerBean.getPoint_value_number()!=myBean.getPoint_value_number())
											{
												discordantItemList.add(itemId);
												break;
											}
										}
									}
								}
								else if(crb.getLogin_level()==3)//mediators
								{
									sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+crb.getUser_id()+"' and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
									List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
									for(String itemId : myItemList)
									{
										List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
										if(!reviewerList.isEmpty())
										{
											AllReviewDataBean firstBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
											if(firstBean.getReview_time()!=null) for(String peerId : reviewerList)
											{
												AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
												if(peerBean.getReview_time()!=null && peerBean.getPoint_value_number()!=firstBean.getPoint_value_number())
												{
													discordantItemList.add(itemId);
													break;
												}
											}
										}
									}
								}
								else
								{
									sqlCmd+="where app_id=1 and group_id=1 and reviewer_action_status ('STARTED','COMPLETE_EDITABLE') order by item_id asc;";
									List<String> myItemList=csqlDao.getGenericStringList(sqlCmd);
									for(String itemId : myItemList)
									{
										List<String> reviewerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+itemId+" and reviewer_action_status in ('STARTED','COMPLETE_EDITABLE')");
										if(!reviewerList.isEmpty())
										{
											AllReviewDataBean firstBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+reviewerList.remove(0)+"'");
											if(firstBean.getReview_time()!=null) for(String peerId : reviewerList)
											{
												AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+itemId+" and point_id=3 and reviewer_id='"+peerId+"'");
												if(peerBean.getReview_time()!=null && peerBean.getPoint_value_number()!=firstBean.getPoint_value_number())
												{
													discordantItemList.add(itemId);
													break;
												}
											}
										}
									}
								}
								
								request.setAttribute("my_event_list", getEventObjListForDisagreement(crb.getLogin_level(), discordantItemList));
							}
							else
							{
								review_status=(review_status.equals("ALL") ? "%": review_status);
								
								if(crb.getLogin_level()==4)//reviewers
									sqlCmd+="where app_id=1 and group_id=1 and reviewer_id='"+crb.getUser_id()+"' and reviewer_action_status like '"+review_status+"' order by item_id asc;";
								else if(crb.getLogin_level()==3)//mediators
									sqlCmd+="where app_id=1 and group_id=1 and mediator_id='"+crb.getUser_id()+"' and reviewer_action_status like '"+review_status+"' order by item_id asc;";
								else
									sqlCmd+="where app_id=1 and group_id=1 and reviewer_action_status like '"+review_status+"' order by item_id asc;";
								
								request.setAttribute("my_event_list", getEventObjList(crb.getLogin_level(), csqlDao.getGenericStringList(sqlCmd)));//"" mean ALL review_status
							}
					
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_event_list.jsp")).forward(request,response);						
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}

					}
					else if(op.equals("ajax_carm_central_get_clinical_summary"))
					{
						try
						{
							request.setAttribute("carm_clincal_summary_bean", csqlDao.getCarmClinicalSummaryEditList("where carm_id="+request.getParameter("carm_id")).get(0));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_clinical_summary.jsp")).forward(request,response);						
						}
						catch(IndexOutOfBoundsException e)
						{
							response.getWriter().write("<p style='padding-left: 5px;'>No record existing</p>");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_clinical_summary:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_clinical_summary 
					else if(op.equals("ajax_carm_central_get_diagnose_record"))
					{
						try
						{
							if(Integer.parseInt((String)request.getParameter("de2_id"))>0)
							{
								request.setAttribute("de2_bean", csqlDao.getDiagnoseRecord("where de2_id="+request.getParameter("de2_id")));
								((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_diagnose_record.jsp")).forward(request,response);	
							}
							else response.getWriter().write("<p style='padding-left: 5px;'>No record existing</p>");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_dignose_record:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_dignose_record
					else if(op.equals("ajax_carm_central_get_problem_reocord"))
					{
						try
						{
							if(Integer.parseInt((String)request.getParameter("de4_id"))>0)
							{
								request.setAttribute("de4_bean", csqlDao.getProblemRecord("where de4_id="+request.getParameter("de4_id")));
								((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_problem_record.jsp")).forward(request,response);		
							}
							else response.getWriter().write("<p style='padding-left: 5px;'>No record existing</p>");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_problem_reocord:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_problem_reocord
					else if(op.equals("ajax_carm_central_get_laboratory_record"))
					{
						try
						{
							request.setAttribute("de5_bean_1", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='1'"));
							request.setAttribute("de5_bean_12", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='12'"));
							request.setAttribute("de5_bean_48", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='48'"));
							request.setAttribute("de5_bean_tests_24", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='tests_24'"));
							request.setAttribute("de5_bean_l_limit", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='l_limit'"));
							request.setAttribute("de5_bean_u_limit", csqlDao.getLaboratoryRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' and de_time='u_limit'"));
							List<CarmAssayUnitBean> assayList=csqlDao.getListFromCarmAssayUnitTable("where site_id='"+request.getParameter("site")+"'");
							Map<String, String> assayMap=new HashMap<String, String>();
							for(CarmAssayUnitBean cb : assayList) assayMap.put(cb.getAssay_name(), cb.getAssay_unit());
							request.setAttribute("assay_unit_map", assayMap);
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_laboratory_record.jsp")).forward(request,response);		

						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_laboratory_reocord:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_laboratory_record
					else if(op.equals("ajax_carm_central_get_vital_sign_medication_records"))
					{
						try
						{
							request.setAttribute("vital_sign_list", csqlDao.getVitalSigns("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' order by week_no, de_time asc"));
							request.setAttribute("medication_list", csqlDao.getMedications("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"' order by week_no, hr asc"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_vital_sign_medication_records.jsp")).forward(request,response);		
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_vital_sign_medication_records:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_vital_sign_medication_records
					else if(op.equals("ajax_carm_central_get_radiology_record"))
					{
						try
						{
							request.setAttribute("radiology_record", csqlDao.getRadiologyRecord("where site='"+request.getParameter("site")+"' and pat_no='"+request.getParameter("pat_no")+"' and event_no='"+request.getParameter("event_no")+"'"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_display_radiology_record.jsp")).forward(request,response);		
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_radiology_record:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_radiology_record
					else if(op.equals("ajax_carm_central_get_review_records"))
					{
						try
						{
							request.setAttribute("point_desc_list", csqlDao.getAllPointDescList("where app_id="+crb.getApp_id()+" order by point_id asc"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_manage_review.jsp")).forward(request,response);		
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_review_records:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_review_record
					else if(op.equals("ajax_carm_central_save_review_data"))//review
					{
						try
						{
							System.out.println("app_id="+request.getParameter("app_id")+
									"group_id="+request.getParameter("group_id")+
									"item_id="+request.getParameter("item_id")+
									"q1to9="+request.getParameter("q1to9")+
									"q10="+request.getParameter("q10"));
													
							boolean fullPointsDone=true;
							String[] sAns=request.getParameter("q1to9").split(",");
							if(sAns.length!=9)
								throw new Exception("Question 1 to 9 has not been posting the right number of answers!");
							
							AllReviewDataBean arb=new AllReviewDataBean();
							arb.setApp_id(Integer.parseInt(request.getParameter("app_id")));
							arb.setGroup_id(Integer.parseInt(request.getParameter("group_id")));
							arb.setItem_id(Integer.parseInt(request.getParameter("item_id")));
							arb.setReviewer_id(crb.getUser_id());
							arb.setReview_time(Utils.currentTimestamp());
							for(int i=0;i<10;i++)
							{
								
								arb.setPoint_id(i+1);
								
								if(i>8)
								{
									arb.setPoint_value_number(-1);
									arb.setPoint_value_string(request.getParameter("q10"));
									fullPointsDone=(fullPointsDone && arb.getPoint_value_string().length()>0);//check all answered
								}
								else
								{
									arb.setPoint_value_string("");
									arb.setPoint_value_number(Double.parseDouble(sAns[i]));
									fullPointsDone=(fullPointsDone && arb.getPoint_value_number()>=0);//check all answered
								}
								
								if(csqlDao.existSameKey(arb.sameRecord()))
								{
									//insert the existing record to the history table
									csqlDao.updateInsertGenericSqlCmd(AllReviewDataHistBean.getInsertStmtFromAllReviewData(arb.getApp_id(), arb.getGroup_id(), arb.getItem_id(), arb.getPoint_id(), arb.getReviewer_id()));
									if(i>8)
									{
										if(csqlDao.existSameKey(arb.samePointValueString())) csqlDao.updateInsertGenericSqlCmd(arb.getUpdateWithoutRevisionIncrement());
										else csqlDao.updateInsertGenericSqlCmd(arb.getUpdateWithRevisionIncrement());										
									}
									else
									{
										
										if(csqlDao.existSameKey(arb.samePointValueNumber())) csqlDao.updateInsertGenericSqlCmd(arb.getUpdateWithoutRevisionIncrement());
										else csqlDao.updateInsertGenericSqlCmd(arb.getUpdateWithRevisionIncrement());									
									}
								}
								else //newly insert a record
								{
									arb.setPoint_id_revision(1);
									csqlDao.updateInsertGenericSqlCmd(arb.getInsertStmt());
								}
								
								
							}

							//update the status		
							csqlDao.updateInsertGenericSqlCmd("update all_item_reviewer_mx set "+
															  "reviewer_action_status='"+AllItemReviewerBean.reviewNextStatus(request.getParameter("review_status"), fullPointsDone)+"', "+
															  "action_status_time=CURRENT_TIMESTAMP "+
															  "where app_id="+arb.getApp_id()+" and group_id="+arb.getGroup_id()+" and item_id="+arb.getItem_id()+" and reviewer_id='"+arb.getReviewer_id()+"'");
							response.getWriter().write("true:ajax_carm_central_save_review_data");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_save_review_data:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_save_review_data
					else if(op.equals("ajax_carm_central_get_forum_records"))
					{
						try
						{
							request.setAttribute("forum_list", csqlDao.getListFromAllForumTable("where app_id="+crb.getApp_id()+" and group_id=1 and item_id="+request.getParameter("carm_id")+" and point_id=3 order by submission_time desc"));
							((RequestDispatcher)request.getRequestDispatcher("jsp/carm_central_menu_response/ajax_jqx_manage_forum.jsp")).forward(request,response);		
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_get_forum_records:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_get_review_record
					else if(op.equals("ajax_carm_central_save_forum_data"))
					{
						try
						{
							AllForumBean fb=new AllForumBean();
							fb.setApp_id(Integer.parseInt(request.getParameter("app_id")));
							fb.setGroup_id(Integer.parseInt(request.getParameter("group_id")));
							fb.setItem_id(Integer.parseInt(request.getParameter("item_id")));
							fb.setPoint_id(Integer.parseInt(request.getParameter("point_id")));
							fb.setbMediatorBtnChecked(request.getParameter("bFinalizeBtnChecked").equals("true"));
							fb.setMediatorValue(Integer.parseInt(request.getParameter("finalValue").equals("")? "-1":request.getParameter("finalValue")));
							fb.setSubmission_time(Utils.currentTimestamp());
							fb.setSubmitter_id(crb.getUser_id());
							fb.setSubmitter_opinion(request.getParameter("myOpinion"));
							fb.setReply_to_all_forum_index(-1);
							fb.setRemarks("");
							if(crb.getLogin_level()==3 && fb.isbMediatorBtnChecked())
							{
								//1. store the opinion into the forum table
								//2. update review data records which are up to the number of reviewers in the all_review_data table
								
								if(fb.getMediatorValue()>0)//1 to 6
								{
									//if mediator's final value is the same as the existing one, nothing to be changed but i) move it to the history table and just provide the remarks field: 'UPDATED by ... (not FINALIZED by ...)"
									//else different from the existing one, move it into the history table, then update the existing record with updating with new value, incremented revision and 'UPDATED by ...(not FINALIZED by ...)"
									String sqlCmd1="", sqlCmd2="";
									List<String> peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id="+fb.getApp_id()+" and group_id="+fb.getGroup_id()+" and item_id="+fb.getItem_id());
									for(String reviewerId : peerList)
									{
										AllReviewDataBean arb=new AllReviewDataBean();
										arb.setApp_id(fb.getApp_id());
										arb.setGroup_id(fb.getGroup_id());
										arb.setItem_id(fb.getItem_id());
										arb.setReviewer_id(reviewerId);
										arb.setPoint_id(fb.getPoint_id());
										arb.setPoint_value_number(fb.getMediatorValue());
										arb.setReview_time(Utils.currentTimestamp());
										arb.setRemarks("UPDATED by "+fb.getSubmitter_id()+" at "+Utils.now());//not put 'FINALIZED by' because the mediator(s) is able to keep updating data
										if(csqlDao.existSameKey(arb.sameRecord()))//just for one more verification
										{
											sqlCmd1=AllReviewDataHistBean.getInsertStmtFromAllReviewData(fb.getApp_id(), fb.getGroup_id(), fb.getItem_id(), fb.getPoint_id(), reviewerId);
											if(csqlDao.existSameKey(arb.samePointValueNumber())) sqlCmd2=arb.getUpdateWithoutRevisionIncrement();
											else sqlCmd2=arb.getUpdateWithRevisionIncrement();
											
											csqlDao.updateInsertGenericSqlCmds(sqlCmd1, sqlCmd2);		
										}
									}
								}
								else
								{
									throw new Exception("Your final value is not provided!");
								}
							}
							csqlDao.updateInsertGenericSqlCmd(fb.getInsertStmt());

							response.getWriter().write("true:ajax_carm_central_save_forum_data");
						}
						catch(Exception e)
						{						
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from CarmCentralController.java");
							response.getWriter().write("false_ajax_carm_central_forum_review_data:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}//ajax_carm_central_save_forum_data
				}
			}
			else if(op.equals("carm_central_logout"))//same as local_logout
			{
				System.out.println("op=carm_central_logout is called from CarmCentralController.java");
				AllLoginHistoryBean ab=(AllLoginHistoryBean)session.getAttribute("all_login_hist_bean");
				
				if(ab!=null)
				{
					csqlDao.updateInsertGenericSqlCmd("update all_login_hist set logout_time=now(), remarks='logged-out' where login_hist_index="+ab.getLogin_hist_index());
				}
				
				for (Enumeration<Object> e = session.getAttributeNames(); e.hasMoreElements(); )
				{
				    String attribName = (String) e.nextElement();
				    if(attribName.startsWith("ca") || attribName.startsWith("app_") || attribName.equals("crb"))
				    {
				    	System.out.println("[CA] session attribute: "+attribName);
				    	session.removeAttribute(attribName);
				    }
				}
				response.sendRedirect(response.encodeRedirectURL(MenuLink.CARM_CENTRAL_CONTEXT));
			}
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from CarmCentralController.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.csr_email_address);
			nameList.add("ADMIN");
			new MailInfo(Utils.csr_email_address,emailList, nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+".");
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
		((RequestDispatcher)request.getRequestDispatcher("jsp/login_page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.csr_email_address+"?subject=CSR ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/login_page_template.jsp")).forward(request,response);
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
	/*
	 * Total number of reviewers in a group should not exceed the threshold
	 * */
	public boolean checkIfMaxGroupsOver(int app_id, int group_id, String user_id, int user_type)//user_type: 3=mediator, 4=reviewer 
	{		
		int assigned_groups=(int)csqlDao.getGenericLong("select count(distinct(group_id)) from all_item_reviewer_mx where app_id="+app_id+" and "+(user_type==3? "mediator_id":"reviewer_id")+"='"+user_id+"'");
		int max_groups_to_review=(int)csqlDao.getGenericLong("select max_groups_to_review from all_registered_user where app_id="+app_id+" and user_id='"+user_id+"'");
		
		//Check: is the reviewer in the group?
		String sqlCmd="select * from all_item_reviewer_mx where app_id="+app_id+" and group_id="+group_id+" and "+(user_type==3? "mediator_id":"reviewer_id")+"='"+user_id+"'";
		if(!csqlDao.existSameKey(sqlCmd)) assigned_groups++;
		
		/*boolean bRetVal=false;
		switch(max_groups_to_review)
		{
			case 1: 
			case 2:
			case 3:
			case 4: bRetVal=assigned_groups<=max_groups_to_review; break;
			default: bRetVal=assigned_groups<max_groups_to_review; break;			
		}*/
		return assigned_groups<=max_groups_to_review;
	}
	public boolean checkIfReviewersPerItem(int app_id, int group_id, int item_id)//user_type: 3=mediator, 4=reviewer 
	{		
		int assigned_reviewers=(int)csqlDao.getGenericLong("select count(reviewer_id) from all_item_reviewer_mx where app_id="+app_id+" and group_id="+group_id+" and item_id="+item_id);
		int reviewers_per_item=(int)csqlDao.getGenericLong("select reviewers_per_item from all_review_group where app_id="+app_id+" and group_id="+group_id);
		assigned_reviewers++;//add the current reviewer about to be assigned
		boolean bRetVal=false;
		switch(reviewers_per_item)
		{
			case 1: 
			case 2:
			case 3:
			case 4: bRetVal=assigned_reviewers<=reviewers_per_item; break;
			default: bRetVal=assigned_reviewers<reviewers_per_item; break;			
		}
		return bRetVal;
	}
	public String getDeselectedEvents(String user_id, String newEventsAssigned) throws Exception
	{
		String[] oldCarmIdsAssigned=csqlDao.getOneRecordFromRegisteredUserTable("where user_id='"+user_id+"';").getAccessible_ids().split(",");//record for the selected user
		String[] newCarmIdsAssigned=newEventsAssigned.split(",");
		String deselectedCarmIds="";
		for(int i=0;i<oldCarmIdsAssigned.length;i++)
			for(int j=0;j<newCarmIdsAssigned.length;j++)
				if(newCarmIdsAssigned[j].equals(oldCarmIdsAssigned[i])) break;
				else if((j+1)==newCarmIdsAssigned.length) 
				{
					deselectedCarmIds+=oldCarmIdsAssigned[i]+",";
				}
		if(deselectedCarmIds.length()>0)
			deselectedCarmIds=deselectedCarmIds.substring(0, deselectedCarmIds.length()-1);
		return deselectedCarmIds;
	}
	
	public Timestamp getTime(String strTime)//in the form of mm/dd/yyyy
	{
		if(strTime.length()!=0)
		{	String temp[]=strTime.split("/");
			return Timestamp.valueOf(temp[2]+"-"+temp[0]+"-"+temp[1]+" 00:00:00");
		}
		return null;
	}
	public void updateConditionsOrIndications(String data, String creator_id, String table_name) throws Exception
	{
		if(data.length()>0 && !data.equals("-1"))
		{
			String[] temp=data.split(",");
			if(table_name.equals("carm_study_conditions"))
				for(int i=0;i<temp.length;i++)
				{
					csqlDao.updateInsertGenericSqlCmd("insert into carm_study_conditions (condition_index, condition, creator_id, creation_time, valid) "+
													   "(select nextval('carm_condition_index_seq'), '"+temp[i]+"', '"+creator_id+"', CURRENT_TIMESTAMP, true where '"+temp[i]+"' not in (select condition from carm_study_conditions))");
				}
			else if(table_name.equals("carm_study_indications"))
				for(int i=0;i<temp.length;i++)
				{
					csqlDao.updateInsertGenericSqlCmd("insert into carm_study_indications (indication_index, indication, creator_id, creation_time, valid) "+
													   "(select nextval('carm_indication_index_seq'), '"+temp[i]+"', '"+creator_id+"', CURRENT_TIMESTAMP, true where '"+temp[i]+"' not in (select indication from carm_study_indications))");
				}
			
		}
	}
	public List<CarmDeMX> getEventObjList(int loginLevel, List<String> conditionedItemList)
	{
		String sqlWhereStatement="";
		if(loginLevel==3 || loginLevel==4)
		{
			if(conditionedItemList.size()>0) 
			{
				sqlWhereStatement="where carm_id in (";
				for(int i=0;i<conditionedItemList.size();i++) 
				{
					sqlWhereStatement+=conditionedItemList.get(i);
					sqlWhereStatement+=((i+1)==conditionedItemList.size() ? ")" : ",");
				}
			}
			else return new ArrayList<CarmDeMX>();//no items selected for the given review status for the given reviewer or mediator
		}
		sqlWhereStatement+=" order by carm_id asc";
		
		List<CarmDeMX> eventObjlist=(List<CarmDeMX>)csqlDao.getCarmDeMXList(sqlWhereStatement);
		return eventObjlist;
	}
	public List<CarmDeMX> getEventObjListForDisagreement(int loginLevel, List<String> conditionedItemList)
	{
		String sqlWhereStatement="";
		if(conditionedItemList.size()>0) 
		{
			sqlWhereStatement="where carm_id in (";
			for(int i=0;i<conditionedItemList.size();i++) 
			{
				sqlWhereStatement+=conditionedItemList.get(i);
				sqlWhereStatement+=((i+1)==conditionedItemList.size() ? ")" : ",");
			}
			sqlWhereStatement+=" order by carm_id asc";
			
			return (List<CarmDeMX>)csqlDao.getCarmDeMXList(sqlWhereStatement);
		}
		return new ArrayList<CarmDeMX>();//no items selected for the given review status for the given reviewer or mediator
	}

}
