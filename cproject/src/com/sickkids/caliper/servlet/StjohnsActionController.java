package com.sickkids.caliper.servlet;

import java.io.IOException;

import java.util.Iterator;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.SecureCodeGenerator;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.service.MailInfo;

/**
 * Servlet implementation class ActionController
 * 
 * 
 * 
 * //1. Instantiate the object to do our testing with.
	<%@page import="com.handinteractive.mobile.UAgentInfo"%>

	<%
	    //2. Initialize the browser info variables
	    String userAgent = request.getHeader("User-Agent");
	    String httpAccept = request.getHeader("Accept");
	
	    //3. Create the UAgentInfo object
	    UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
	
	    //4. Detect whether the visitor is using a mobile device.
	    //   For example, if it's an iPhone, redirect them to the  
	    //   iPhone-optimized version of your web site.
	    if (detector.detectIphoneOrIpod()) {
	        response.sendRedirect("http://www.mycompany.com/iphone");
	    } else {
	        response.sendRedirect("http://www.mycompany.com/default");
	    }
	%>

 */
public class StjohnsActionController extends HttpServlet implements Servlet
{	static final long serialVersionUID = 1L;
	
	
	public void init(ServletConfig config) 
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in StjohnsActionController.java");
				
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew()) 
				
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in StjohnsActionController.java");
		//check the sesssion if it is expired or not, by session.getAttribute("login_email");
		//send two ways of session expired-information; ajax and complete reloading.
		//if expired, then send one page saying 'session is expired' for ajax and normal reloading
		//Check the contents before inserting into db since post may have refreshed, means doing again--POST, not ajax
		//validate parameters in each case
		try
		{
			if(op==null || op.equals("home_stjohns") || op.equals(""))
			{
				request.setAttribute("body_panel", "/local_hospitals/stjohns/jsp/home_body.jsp");				
			}
			else if(op.equals("about_caliper_stjohns"))
			{
				request.setAttribute("body_panel", "/local_hospitals/stjohns/jsp/about_caliper_body.jsp");				
			}
			else if(op.equals("forms_stjohns"))
			{
				request.setAttribute("body_panel", "/local_hospitals/stjohns/jsp/forms.jsp");				
			}
			else if(op.equals("contactus_stjohns"))
			{
				request.setAttribute("body_panel", "/local_hospitals/stjohns/jsp/contactus.jsp");		
			}
			((RequestDispatcher)request.getRequestDispatcher("/local_hospitals/stjohns/jsp/page_template.jsp")).forward(request,response);
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from StjohnsActionController.java");
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
		((RequestDispatcher)request.getRequestDispatcher("/local_hospitals/stjohns/jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("/local_hospitals/stjohns/jsp/page_template.jsp")).forward(request,response);
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
