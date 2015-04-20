package com.sickkids.caliper.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.service.MailInfo;

/**
 * Servlet implementation class SmartPhoneServlet
 * 240x320 is the new baseline for mobile phones. For high end units, 480 would be the correct width to aim for.
 */
public class SmartPhoneServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;
	

	public void init(ServletConfig config) 
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew()) 
				
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in SmartPhoneServlet.java");
		//check the sesssion if it is expired or not, by session.getAttribute("login_email");
		//send two ways of session expired-information; ajax and complete reloading.
		//if expired, then send one page saying 'session is expired' for ajax and normal reloading
		//Check the contents before inserting into db since post may have refreshed, means doing again--POST, not ajax
		//validate parameters in each case
		try
		{
			if(op==null || op.equals("home") || op.equals(""))
			{
				request.setAttribute("body_panel", "smartphone/home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/smartphone_page_template.jsp")).forward(request,response);
			}
			else if(op.equals("reference_intervals"))
			{
				request.setAttribute("body_panel", "smartphone/reference_intervals.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/smartphone_page_template.jsp")).forward(request,response);
			}
			else if(op.equals("display_partition_rf"))//ajax request for caliper, request from Data Manipulation Reference Intervals
			{
				System.err.println("display_partition_rf");
				((RequestDispatcher)request.getRequestDispatcher("jsp/smartphone/display_partition_rf.jsp")).forward(request,response);
				//response.getWriter().write("heelllk");
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from SmartPhoneServlet.java");
			new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+"(SmartPhone).");
			forwardErrorPage(request,response,e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	public void forwardWarningPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("warning_msg", msg+" at "+Utils.currentTimestamp()+"(SmartPhone)!!!");		
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"(SmartPhone)!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjax(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" at "+Utils.currentTimestamp()+"(SmartPhone)!!!<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=myESL 에러발생 통보' style='color: #FE5734;text-decoration: underline;'>이메일</a>");		
		((RequestDispatcher)request.getRequestDispatcher("jsp/error/servlet_error_page.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjaxDlg(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, msg+" Generated at "+Utils.currentTimestamp()+"(SmartPhone).");
		response.getWriter().write("false:"+Message.general_error_msg+"\n\n"+msg+"\n\n"+Message.inform_to_admin_about_exception2);
	}
}
