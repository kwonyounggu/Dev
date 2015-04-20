package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.util.ArrayList;
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


import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.LoginHistoryBean;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.dao.UsersBean;
import com.sickkids.caliper.dao.UsersDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.service.MailInfo;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet implements Servlet
{
	private static final long serialVersionUID = 1L;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;

	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();

	private SQLDao sqlDao=null;

	
	public void init(ServletConfig config) 
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");
	    sqlDao = (SQLDao) app.getAttribute("sqlDao");
	  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in AdminController.java");
				
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew()) 
				
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in AdminController.java");
		
		try
		{
			//Here, first check if the person is login, otherwise go back to the public home
			String admin_email=(String)session.getAttribute("admin_email");
			if(admin_email==null && op!=null && op.indexOf("ajax_admin_login_check")!=-1)
			{
				try
				{
					String admin_name=(String)request.getParameter("admin_name");
					admin_email=(String)request.getParameter("admin_email");
					if(admin_name!=null && !admin_name.equals("") && admin_email!=null && !admin_email.equals(""))
					{
						UsersBean ub=new UsersDao(cp).getARecord("where email='"+admin_email.toLowerCase()+"'");
						System.out.println(">>>>Email("+admin_email+")="+ub.getEmail()+", Name("+admin_name+")="+ub.getFirst_name()+", owner="+ub.isIs_owner());
						if(ub.getEmail().equalsIgnoreCase(admin_email) && ub.getFirst_name().equalsIgnoreCase(admin_name) && ub.isIs_owner()) 
						{
							session.setAttribute("admin_email", admin_email.toLowerCase());
							session.setAttribute("admin_name", admin_name);
							response.getWriter().write("true:"+"/cproject/admin");
						}
						else response.getWriter().write("false: Please check your name and email one more time.\n\nYou are not allowed to gain an admin privilege.");
					}
					else response.getWriter().write("false: Null value(s) for your name and or email to be checked.");			
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from AdminController.java");
					response.getWriter().write("false:Error has been generated while preceeding.\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception_en+"\nEmail Address: "+Utils.caliper_email_address);
				}		
			}
			else if(admin_email==null)//session out or hacking, no login
			{
				if(op!=null && op.indexOf("ajax")!=-1)
					response.getWriter().write("session_timeout");//goback to where it calls
				else
				{
					request.setAttribute("body_panel", "/admin/jsp/login.jsp");	
					((RequestDispatcher)request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request,response);
				}
			}
			//-----------------------------------------------------------------
			else if(op==null || op.equals("admin_home") || op.equals(""))//
			{
				request.setAttribute("body_panel", "/admin/jsp/home_body.jsp");	
				((RequestDispatcher)request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request,response);
			}
			/*else if(op.equals("admin_logout"))//same as local_logout
			{
				//invalidate session about login
				session.removeAttribute("admin_email");
				session.removeAttribute("admin_name");

				response.sendRedirect(response.encodeRedirectURL(MenuLink.home));
			}*/
			else if(op.equals("admin_input_events"))//ajax request
			{
				request.setAttribute("body_panel", "/admin/jsp/manage_events.jsp");	
				((RequestDispatcher)request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("ajax_admin_logout"))//ajax request
			{
				session.removeAttribute("admin_email");
				session.removeAttribute("admin_name");
				response.getWriter().write("true");
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from AdminController.java");
			new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+".");
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
		request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("/admin/jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjax(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" at "+Utils.currentTimestamp()+"!!!<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=ERROR REPORT' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>");		
		((RequestDispatcher)request.getRequestDispatcher("jsp/error/servlet_error_page.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjaxDlg(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, msg+" Generated at "+Utils.currentTimestamp()+".");
		response.getWriter().write("false:"+Message.general_error_msg+"\n\n"+msg+"\n\n"+Message.inform_to_admin_about_exception2);
	}


}
