package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.sickkids.caliper.common.EmailText;
import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AllApplicationBean;
import com.sickkids.caliper.dao.AllApplicationHistBean;
import com.sickkids.caliper.dao.AllItemReviewerBean;
import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.AllLoginHistoryDao;
import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.dao.AllReviewGroupBean;
import com.sickkids.caliper.dao.CarmSQLDao;
import com.sickkids.caliper.dao.OracleSQLDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.service.MailInfo;

/**
 * Servlet implementation class SuperAdminServlet
 */
public class SuperAdminServlet extends HttpServlet implements Servlet
{
	private static final long serialVersionUID = 1L;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;

	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();

	private CarmSQLDao csqlDao=null;
	private AllLoginHistoryDao cloginHistoryDao=null;

	public void init(ServletConfig config)
	{
	    this.config = config;
	    this.app = config.getServletContext();
	    this.cp=(ConnectionPool)app.getAttribute("connectionPool");
	    this.csqlDao=(CarmSQLDao)app.getAttribute("csqlDao");
	    this.cloginHistoryDao=new AllLoginHistoryDao(cp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in SuperAdminServlet.java");

		request.setCharacterEncoding("UTF-8");//put this otherwise UNI-Code characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew())
		
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in SuperAdminServlet.java");

		try
		{
			//Here, first check if the person is login, otherwise go back to the public home
			AllRegisteredUserBean sab=(AllRegisteredUserBean)session.getAttribute("sab");
			
			if(sab==null)//no logged in but op is not null (session out, hacking, look for password and ID)
			{
				if(op!=null && op.indexOf("ajax")!=-1)
				{				
					if(op.equals("ajax_login_check"))
					{
						try
						{
							String sa_login_id=(String)request.getParameter("sa_login_id");
							String sa_login_password=(String)request.getParameter("sa_login_password");
							System.out.println(op+": "+sa_login_id+" "+sa_login_password);
							if(sa_login_id!=null && !sa_login_id.equals("") && sa_login_password!=null && !sa_login_password.equals(""))
							{	
								sab=csqlDao.getOneRecordFromRegisteredUserTable("where user_id='"+sa_login_id+"'");
								if(sab.getUser_id().equalsIgnoreCase(sa_login_id))
								{
									if(sab.getLogin_level()>1 && sab.getLogin_level()<6 && sab.getAccess_limit_time().before(Utils.currentTimestamp()))
									{
										response.getWriter().write("false:We are sorry to say that your access time is over.\nPlease contact Dr. Chris at chris@sickkids.ca!");
									}
									else if(sab.getPassword().equals(sa_login_password))
									{
										//every thing is OK, ie: login is successful, set login session variables
										session.setAttribute("sa_login_id", sab.getUser_id());
										session.setAttribute("sa_logn_first_name", sab.getFirst_name());
										session.setAttribute("sa_login_last_name", sab.getLast_name());
										session.setAttribute("sa_login_password", sab.getPassword());
										session.setAttribute("sab", sab);
																			
										AllLoginHistoryBean lb=new AllLoginHistoryBean(0L, sab.getApp_id(), sab.getUser_id(), Utils.currentTimestamp(), request.getRemoteAddr());
										lb=cloginHistoryDao.create(lb);
										session.setAttribute("all_login_hist_bean", lb);
										//System.out.println("temp, getlogin_hist_index assigned="+lb.getLogin_hist_index()+" for me, "+lb.getUser_id());
										response.getWriter().write("true:"+MenuLink.SUPERADMIN_CONTEXT);
									}
									else response.getWriter().write("false:"+Message.incorrect_password);
								}
								else response.getWriter().write("false:Your LoginID is not in our database.\nPlease try again after checking.");
							}
							else response.getWriter().write("false:"+Message.invalid_loginID);
						}
						catch(Exception e)
						{
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from SuperAdminServlet.java");
							response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
						}
					}
					else if(op.equals("ajax_forgot_pwd"))
					{
						((RequestDispatcher)request.getRequestDispatcher("jsp/superadmin_menu_response/ajax_login_forgot_password.jsp")).forward(request,response);
					}
					else if(op.equals("ajax_forgot_pwd_check"))//ajax request
					{	
						try
						{
							String email=(String)request.getParameter("sa_login_email");
							if(email!=null && !email.equals(""))
							{
								//one email and more than one id and password
								List<AllRegisteredUserBean> idPasswordList=csqlDao.getListFromRegisteredUserTable("where email='"+email+"'");
								
								if(idPasswordList.size()>0) 
								{							
									EmailText.emailForgottenPassword("SUPERADMIN", idPasswordList, request.getScheme()+"://"+request.getServerName()+":"+Integer.toString(request.getServerPort()), MenuLink.SUPERADMIN_CONTEXT );
									response.getWriter().write("true:Please check your email for your LoginID/Password!");
								}
								else response.getWriter().write("false:"+Message.non_existing_email);
							}
							else response.getWriter().write("false:"+Message.incorrect_email);
						}
						catch(Exception e)
						{	
							Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from SuperAdminServlet.java");
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
					request.setAttribute("body_panel", "/jsp/superadmin_menu_response/login_body.jsp");
					((RequestDispatcher)request.getRequestDispatcher("jsp/superadmin_menu_response/login_page_template.jsp")).forward(request,response);
				}
			}
			//****** LOGIN SESSION AVAILABLE ********** from here, sab is not null and op=null or op!=null
			else if(op==null || op.equals("central_home") || op.equals(""))//public->central->local
			{
				request.setAttribute("body_panel", "/jsp/superadmin_menu_response/jqx_home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/superadmin_menu_response/jqx_central_page_template.jsp")).forward(request,response);
			}
			else if(op.indexOf("ajax")!=-1)
			{				
				if(op.equals("ajax_action_application")) 
				{
					try
					{
						String action=(String)request.getParameter("action");//add, edit, delete
						AllApplicationBean ab=new AllApplicationBean();
						ab.setApp_id(Integer.parseInt(request.getParameter("app_id")));
						ab.setPurpose_of_app(request.getParameter("app_purpose"));
						ab.setApp_symbol(request.getParameter("app_symbol"));
						ab.setNumber_of_groups(Integer.parseInt(request.getParameter("number_of_groups")));
						ab.setReviewers_per_item(Integer.parseInt(request.getParameter("reviewers_per_item")));
						ab.setReviewer_assignment_type(request.getParameter("assignment_type"));
						ab.setConfirmatory(request.getParameter("confirmatory").equals("true")? true: false);
						ab.setDisagreement_process(request.getParameter("toMediator"));
						ab.setCreation_time(Utils.currentTimestamp());
						ab.setCreator_id(sab.getUser_id());
						ab.setRemarks(action+" by "+sab.getUser_id()+" at "+Utils.currentTimestamp());
						
						if(action.equals("add"))
						{
							System.out.println("add application is called here");
							ab.setApp_id_revision(1);//since it is the first one
							csqlDao.updateInsertGenericSqlCmd(ab.getInsertStmt());
							ab.setApp_id((int)csqlDao.getCurrentSequence("all_application_id_seq"));
							AllApplicationHistBean abh=new AllApplicationHistBean(ab);
							csqlDao.updateInsertGenericSqlCmd(abh.getInsertStmt());
							
							response.getWriter().write("true:add[À]app_id="+ab.getApp_id()+"&app_id_revision="+csqlDao.getGenericLong("select app_id_revision from all_application where app_id="+ab.getApp_id()));
						}
						else if(action.equals("edit"))
						{
							System.out.println("edit application is called here");
							csqlDao.updateInsertGenericSqlCmd(ab.getUpdateStmt());
							csqlDao.updateInsertGenericSqlCmd(AllApplicationHistBean.getInsertStmtFromAllApplication(ab.getApp_id()));
							response.getWriter().write("true:edit[À]app_id="+ab.getApp_id()+"&app_id_revision="+ab.getApp_id_revision());						
						}
						else if(action.equals("delete"))
						{
							System.out.println("delete application is called here");
							csqlDao.updateInsertGenericSqlCmd(ab.getUpdateStmt());//is to increase the current revision number
							csqlDao.updateInsertGenericSqlCmds(AllApplicationHistBean.getInsertStmtFromAllApplication(ab.getApp_id()), "delete from all_application where app_id="+ab.getApp_id());
							response.getWriter().write("true:delete[À]app_id="+ab.getApp_id()+"&app_id_revision="+ab.getApp_id_revision());
						}
					}
					catch(Exception e)
					{
						Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from SuperAdminServlet.java");
						response.getWriter().write("false_ajax_action_application:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.csr_email_address);
					}
				}
				else if(true)
				{
					System.out.println("******************100******************");
				}
			}
			else if(op.equals("superadmin_logout"))//same as local_logout
			{
				System.out.println("op=carm_central_logout is called from SuperAdminServlet.java");
				AllLoginHistoryBean ab=(AllLoginHistoryBean)session.getAttribute("all_login_hist_bean");
				
				if(ab!=null)
				{
					csqlDao.updateInsertGenericSqlCmd("update all_login_hist set logout_time=now(), remarks='logged-out' where login_hist_index="+ab.getLogin_hist_index());
				}
				for (Enumeration<Object> e = session.getAttributeNames(); e.hasMoreElements(); )
				{
				    String attribName = (String) e.nextElement();
				    if(attribName.startsWith("sa") || attribName.equals("sab"))
				    {
				    	System.out.println("[SA] session attribute: "+attribName);
				    	session.removeAttribute(attribName);
				    }
				}
				response.sendRedirect(response.encodeRedirectURL(MenuLink.SUPERADMIN_CONTEXT));
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from SuperAdminServlet.java");
			emailList.clear();
			nameList.clear();
			emailList.add(Utils.csr_email_address);
			nameList.add("ADMIN");
			new MailInfo(Utils.csr_email_address,emailList, nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+".");
			forwardErrorPage(request,response,e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
}
