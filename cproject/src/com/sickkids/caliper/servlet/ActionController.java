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

import com.sickkids.caliper.dao.CommunityBean;
import com.sickkids.caliper.dao.CommunityDao;
import com.sickkids.caliper.dao.LoginHistoryBean;
import com.sickkids.caliper.dao.LoginHistoryDao;
import com.sickkids.caliper.dao.OnlineRegistrationBean;
import com.sickkids.caliper.dao.OnlineRegistrationDao;
import com.sickkids.caliper.dao.PersonsBean;
import com.sickkids.caliper.dao.PersonsDao;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.dao.UsersBean;
import com.sickkids.caliper.dao.UsersDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.common.MenuLink;
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
public class ActionController extends HttpServlet implements Servlet
{	static final long serialVersionUID = 1L;
	static int count=0;

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
		Utils.logger.info("doGet() is called in ActionController.java");
				
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);//if(session.isNew()) 
			//session.setMaxInactiveInterval(30);
		
		//response.setHeader("Cache-Control","no-store,no-cache, must-revalidate, post-check=0, pre-check=0");
		//response.setHeader("Pragma", "no-cache");
		
		String op=(String)request.getParameter("op");
		Utils.logger.info("op="+op+" in ActionController.java");
		//check the sesssion if it is expired or not, by session.getAttribute("login_email");
		//send two ways of session expired-information; ajax and complete reloading.
		//if expired, then send one page saying 'session is expired' for ajax and normal reloading
		//Check the contents before inserting into db since post may have refreshed, means doing again--POST, not ajax
		//validate parameters in each case
		try
		{
			if(op==null || op.equals("home") || op.equals(""))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("jobs"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/jobs.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("signup"))
			{
				session.setAttribute("security_code", new SecureCodeGenerator(5).generate());
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/signup.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("reg_signup_complete"))
			{				
				OnlineRegistrationBean rb=new OnlineRegistrationBean(0L,
																	 request.getParameter("reg_name"),
																	 Integer.parseInt((String)request.getParameter("reg_sex")),
																	 request.getParameter("reg_email"),
																	 request.getParameter("reg_phone"),
																	 request.getParameter("reg_school"),
																	 Utils.currentTimestamp(),
																	 "" );//remarks
								
				new OnlineRegistrationDao(cp).create(rb);
				emailList.clear();
				nameList.clear();
				emailList.add(Utils.caliper_project_coordinator_email);
				nameList.add(Utils.caliper_project_coordinator_name);
				emailList.add(Utils.caliper_email_address);
				nameList.add(Utils.caliper_system_admin_name);
				emailList.add(rb.getEmail());
				nameList.add(rb.getName());
				new MailInfo(Utils.caliper_email_address,emailList,nameList,Utils.smtp,"Online registration in order to participate CALIPER PROJECT.", Utils.getRegisterToParticipate(rb));
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/howtoparticipate_register_completed.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);			
			}
			else if(op.equals("update_reg_signup_complete"))//ajax
			{	
				try
				{
					String email=(String)request.getParameter("reg_email");
					//myinfo_timetable is for display just my registered information
					((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/myinfo_timetable.jsp")).forward(request,response);
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					forwardErrorPageForAjaxDlg(request,response,"(op="+op+") msg="+e.toString()+" from ActionController.java.");
				}
			}
			else if(op.equals("login"))//request login screen
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/login.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("login_check"))//ajax request
			{	
				
				try
				{
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
				}
				
				
			}
			else if(op.equals("logout_check"))//ajax request
			{
				session.removeAttribute("login_email");
				session.removeAttribute("login_korean_name");
				session.removeAttribute("login_password");
				session.removeAttribute("pb");
				((RequestDispatcher)request.getRequestDispatcher("jsp/login/login_template_manager.jsp")).forward(request,response);								
			}
			else if(op.equals("forgot_pwd"))//ajax request
			{	
				try
				{
					String value=(String)request.getParameter("value");
					if(value!=null && !value.equals(""))
					{
					}
					else response.getWriter().write("false:"+Message.incorrect_email);			
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
				}
			}
			else if(op.equals("rf_request_header_table"))//ajax request for caliper, request from Data Manipulation Reference Intervals
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/custom_ref_interval_request_table.jsp")).forward(request,response);
			}
			else if(op.equals("display_custom_rf"))//ajax request for caliper, request from Data Manipulation Reference Intervals
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/display_custom_rf.jsp")).forward(request,response);
			}
			else if(op.equals("display_partition_rf"))//ajax request for caliper, request from Data Manipulation Reference Intervals
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/display_partition_rf.jsp")).forward(request,response);
			}
			else if(op.equals("display_rfi_chart"))//ajax request for caliper, request from Data Manipulation Reference Intervals->Custom Search->Chart
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/downloads_display_rfi_chart.jsp")).forward(request,response);
			} 
			else if(op.equals("display_rfi_table"))//ajax request for caliper, request from Data Manipulation Reference Intervals->Custom Search->Table
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/downloads_display_rfi_table.jsp")).forward(request,response);
			}
			else if(op.equals("display_rfi_table_ready"))//ajax request for caliper, radio button request from Data Manipulation->Custom Search->Table
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/downloads_custom_search_table.jsp")).forward(request,response);
			}
			else if(op.equals("display_rfi_chart_ready"))//ajax request for caliper, radio button request from Data Manipulation->Custom Search->Chart
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/downloads_custom_search_chart.jsp")).forward(request,response);
			}
			else if(op.equals("get_agreement"))//ajax request
			{
				((RequestDispatcher)request.getRequestDispatcher("html/signup_agreement.html")).forward(request,response);
			}
			else if(op.equals("get_subject_list"))//ajax request
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/subject_list.jsp")).forward(request,response);
			}
			else if(op.equals("get_tuition_fee_list"))//ajax request
			{
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/tuition_fee_list.jsp")).forward(request,response);
			}
			else if(op.equals("get_write_page"))//ajax request, for new message writing
			{	
				switch(Integer.parseInt(request.getParameter("sm")))
				{
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					default:
				}
				session.setAttribute("security_code", new SecureCodeGenerator(5).generate());
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/write_message.jsp")).forward(request,response);
			}
			else if(op.equals("get_reply_page"))//ajax request
			{	
				session.setAttribute("security_code", new SecureCodeGenerator(5).generate());
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/reply_message.jsp")).forward(request,response);
			}
			else if(op.equals("goto_etalk_video"))//ajax request
			{	
				try
				{
					String value=(String)request.getParameter("value");
					if(value!=null && !value.equals(""))
					{	
					}
					else response.getWriter().write("false: Null value for login email to be checked.");			
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					response.getWriter().write("false:An error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
				}
			}
			else if(op.equals("goto_check_admin_login"))//ajax request
			{	
				try
				{
					String teacher_name=(String)request.getParameter("teacher_name");
					String teacher_email=(String)request.getParameter("teacher_email");
					if(teacher_name!=null && !teacher_name.equals("") && teacher_email!=null && !teacher_email.equals(""))
					{
						UsersBean ub=new UsersDao(cp).getARecord("where email='"+teacher_email.toLowerCase()+"'");
						System.out.println(">>>>Email("+teacher_email+")="+ub.getEmail()+", Name("+teacher_name+")="+ub.getFirst_name()+", owner="+ub.isIs_owner());
						if(ub.getEmail().equalsIgnoreCase(teacher_email) && ub.getFirst_name().equalsIgnoreCase(teacher_name) && ub.isIs_owner()) 
						{
							session.setAttribute("admin_email", teacher_email.toLowerCase());
							session.setAttribute("admin_name", teacher_name);
							response.getWriter().write("true:"+MenuLink.etalk_video);
						}
						else response.getWriter().write("false: "+Message.classroom_accessibility_teacher);
					}
					else response.getWriter().write("false: Null value(s) for your name and or email to be checked.");			
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					response.getWriter().write("false:Error has been generated while preceeding.\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception_en+"\nEmail Address: "+Utils.caliper_email_address);
				}
			}
			else if(op.equals("check_admin_login"))//ajax request
			{
				response.getWriter().write((String)session.getAttribute("admin_email")!=null ? "true": "false");
			}
			else if(op.equals("admin_logout"))//ajax request
			{
				session.removeAttribute("admin_email");
				session.removeAttribute("admin_name");
				response.getWriter().write("true");
			}
			else if(op.equals("write_new_msg"))//from a new message writing
			{					
				String contents=(String)request.getParameter("qa_message");
				contents=contents.replace("\r\n", "<br>");
				CommunityBean cb=new CommunityBean(0,//main index
												   Integer.parseInt(request.getParameter("sm")),//0: faq, 1: news,  2: 	questions and answers, 
												   0,//0:일반, 1:수업료, 2:화상통화, 3:선생님, 4:캠프, 5:영어
												   (String)request.getParameter("qa_email"),//person_id
												   (String)request.getParameter("qa_name"),//korean_name or eng_name
												   (String)request.getParameter("qa_subject"),
												   contents,
												   0,//topic_number 0 means root parent, same as community_index
												   "0",//in_response_to 0 means root parent
												   Utils.currentTimestamp(),//written date
												   0,//access number
												   request.getParameter("qa_delete_password"),
												   0,//my page, initially 0
												   "");
				cb=new CommunityDao(cp).create(cb);
				
				session.setAttribute("security_code","");//to prevent 2 times of posting
				
				CommunityDao cDao=new CommunityDao(cp);
				//put the topic number with the community_index for the 1st subject, which is just created with a topic number 0
				cDao.updateTopicNumber(cb);
				request.setAttribute("total_count", (Integer)cDao.getCount("select count(distinct topic_number) from community where community_class="+cb.getCommunity_class()));
				int offset=(1-1)*Utils.displayLimit;
				request.setAttribute("qa_msg_list", (List<CommunityBean>)cDao.getCommunityList2("where community_class="+cb.getCommunity_class()+" group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
				
				//request.setAttribute("left_panel", "menu_response/community_submenu.jsp");
				request.setAttribute("body_panel", "menu_response/"+getPage(cb.getCommunity_class(),false));
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);//return to the default 1st page after				
			}
			else if(op.equals("write_reply_msg"))//from a reply message writing
			{					
				if(!((String)session.getAttribute("security_code")).equalsIgnoreCase(request.getParameter("qa_security_code"))) forwardWarningPage(request,response,Message.check_security_code2);
				else
				{
					String sTopic_number=(String)request.getParameter("topic_number");
					String contents=(String)request.getParameter("qa_message");
					contents=contents.replace("\r\n", "<br>");
					((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
				}
				
			}
			else if(op.equals("get_course_registration_step2"))//ajax request
			{
				try
				{	/*
					TuitionFeeBean tb=new TuitionFeeDao(cp).getARecord("where tuition_fee_index="+request.getParameter("tuition_fee_index"));
					SubjectsBean sb=new SubjectsDao(cp).getARecord("where subject_id='"+tb.getSubject_id()+"'");
					request.setAttribute("tb", tb);
					request.setAttribute("sb", sb);*/
					((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/classguide_course_registration_step2.jsp")).forward(request,response);
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+"): msg="+e+" from ActionController.java");
					forwardErrorPageForAjax(request,response,e.getMessage());
				}
			}
			else if(op.equals("get_course_registration_step3"))//ajax request
			{
				try
				{	
					if(session.getAttribute("login_email")==null)
					{
						((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/session_out.jsp")).forward(request,response);
						return;
					}
					
					((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/classguide_course_registration_step3.jsp")).forward(request,response);
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+"): msg="+e+" from ActionController.java");
					forwardErrorPageForAjax(request,response,e.getMessage());
				}
			}
			else if(op.equals("contactus"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/contactus.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("sitemap"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/sitemap.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.indexOf("verify")!=-1)//ajax check for registration information, signup
			{
				try
				{
					String value=(String)request.getParameter("value");
					if(op.equals("verify_email"))
					{
						
						if(value!=null && !value.equals(""))
						{
							PersonsBean pb=new PersonsDao(cp).getARecord("where person_id='"+value+"'");
							
							if(pb.getPerson_id().equalsIgnoreCase(value)) response.getWriter().write("false:"+Message.email_in_use);
							else response.getWriter().write(Message.correct_email);
						}
						else response.getWriter().write("false:"+Message.incorrect_email);
					}
					else if(op.equals("verify_eng_name"))
					{
						if(value!=null && !value.equals(""))
						{
						}
						else response.getWriter().write("false:"+Message.incorrect_eng_name);
					}
				}
				catch(Exception e)
				{	
					Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
					response.getWriter().write("false:An error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
				}
			}
			else if(op.indexOf("menu")!=-1)
			{
				if(op.indexOf("menu_aboutcaliper")!=-1)
				{	
					//request.setAttribute("left_panel", "menu_response/aboutcaliper_submenu.jsp");
					if(op.equals("menu_aboutcaliper_whatiscaliper"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_whatiscaliper.jsp");
					else if(op.equals("menu_aboutcaliper_moreaboutcaliper"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_moreaboutcaliper.jsp");
					else if(op.equals("menu_aboutcaliper_caliperacrosscanada"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_caliperacrosscanada.jsp");
					else if(op.equals("menu_aboutcaliper_calipermoment"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_calipermoment.jsp");
					
				}
				else if(op.indexOf("menu_howtoparticipate")!=-1)
				{	
					//request.setAttribute("left_panel", "menu_response/howtoparticipate_submenu.jsp");
					if(op.equals("menu_howtoparticipate_howtoparticipate"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_howtoparticipate.jsp");
					else if(op.equals("menu_howtoparticipate_volunteer"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_volunteer.jsp");
					else if(op.equals("menu_howtoparticipate_clinics"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_clinics.jsp");
					else if(op.equals("menu_howtoparticipate_storybook"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_storybook.jsp");
					else if(op.equals("menu_howtoparticipate_register"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_register.jsp");
					else if(op.equals("menu_howtoparticipate_schoolds_communities"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_schoolds_communities.jsp");
					
				}
				else if(op.indexOf("menu_labpro")!=-1)
				{	
					//request.setAttribute("left_panel", "menu_response/labpro_submenu.jsp");
					if(op.equals("menu_labpro_labpro"))
						request.setAttribute("body_panel", "menu_response/labpro_labpro.jsp");
					else if(op.equals("menu_labpro_samplecollection"))
						request.setAttribute("body_panel", "menu_response/labpro_samplecollection.jsp");
					else if(op.equals("menu_labpro_diagnostictests"))
						request.setAttribute("body_panel", "menu_response/labpro_diagnostictests.jsp");
					else if(op.equals("menu_labpro_studydesign"))
						request.setAttribute("body_panel", "menu_response/labpro_studydesign.jsp");
					else if(op.equals("menu_labpro_analysis"))
						request.setAttribute("body_panel", "menu_response/labpro_analysis.jsp");
					else if(op.equals("menu_labpro_publications"))
						request.setAttribute("body_panel", "menu_response/labpro_publications.jsp");
				}
				else if(op.indexOf("menu_downloads")!=-1)
				{	
					//request.setAttribute("left_panel", "menu_response/downloads_submenu.jsp");
					if(op.equals("menu_downloads_reference_intervals"))
						request.setAttribute("body_panel", "menu_response/downloads_referenceintervals.jsp");
					else if(op.equals("menu_downloads_raw_data"))
						request.setAttribute("body_panel", "menu_response/downloads_raw_data.jsp");
					else if(op.equals("menu_downloads_charts"))
						request.setAttribute("body_panel", "menu_response/downloads_charts.jsp");
				}
				else if(op.indexOf("menu_myinfo")!=-1) //modify my account information
				{	
					//request.setAttribute("left_panel", "menu_response/myinfo_submenu.jsp");
					if(op.equals("menu_myinfo_timetable"))
						request.setAttribute("body_panel", "menu_response/myinfo_timetable.jsp");
					else if(op.equals("menu_myinfo_modification"))
						request.setAttribute("body_panel", "menu_response/myinfo_modification.jsp");
					else if(op.equals("menu_myinfo_invoice"))
					{
						/*request.setAttribute("enrolmentList", new EnrolmentDao(cp).getEnrolmentList("where student_person_id='"+session.getAttribute("login_email")+"'"));*/
						request.setAttribute("body_panel", "menu_response/myinfo_invoice.jsp");		
					}
				}

				else if(op.indexOf("menu_community")!=-1)//고개센터
				{	
					//request.setAttribute("left_panel", "menu_response/community_submenu.jsp");
					if(op.equals("menu_community_faq"))
					{	
						String sTopic_number=(String)request.getParameter("topic_number");
						if(sTopic_number!=null)//display FAQ message that is requested
						{
							int topic_number=Integer.parseInt(sTopic_number);
							long community_index=Long.parseLong((String)request.getParameter("community_index"));
							
							CommunityDao cDao=new CommunityDao(cp);
							cDao.updateAccessNumber(community_index);//increment the access number by adding one
							
							List cbList=cDao.getCommunityList3("where community_class=0 and topic_number="+topic_number+" order by written_date asc");
							request.setAttribute("qa_contents_list", cbList);
							if(cbList.size()>0) request.setAttribute("topic_cb", cbList.get(0));
							request.setAttribute("body_panel", "menu_response/community_faq_display_contents.jsp");
						}
						else //display all the subject list of messages submitted, just for the page specified
						{	String search=(String)request.getParameter("search");
							if(search!=null && search.equals("1")) //search mode
							{	
								String sqlQuery1="", sqlQuery2="";
								String search_word=(String)request.getParameter("stext");
								if(search_word==null) search_word="";
								
								switch(Integer.parseInt(request.getParameter("scategory")))
								{
									case 0: //제목
											sqlQuery1=" where community_class=0 and topic_subject like '%"+search_word+"%' ";
											sqlQuery2=" and topic_subject like '%"+search_word+"%' "; break;
									case 1: //본문
											sqlQuery1=" where community_class=0 and contents like '%"+search_word+"%' ";
											sqlQuery2=" and contents like '%"+search_word+"%' "; break;
									case 2: //작성자
											sqlQuery1=" where community_class=0 and person_name like '%"+search_word+"%' ";
											sqlQuery2=" and person_name like '%"+search_word+"%' "; break;					
									default: break;
								}
								
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community"+sqlQuery1)));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=0"+sqlQuery2+"group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_faq.jsp");
							}
							else //non-search mode
							{
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=0")));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=0 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_faq.jsp");								
							}
							
						}
					}
					else if(op.equals("menu_community_news"))
					{
						String sTopic_number=(String)request.getParameter("topic_number");
						if(sTopic_number!=null)//display qa message that is requested
						{
							int topic_number=Integer.parseInt(sTopic_number);
							long community_index=Long.parseLong((String)request.getParameter("community_index"));
							
							CommunityDao cDao=new CommunityDao(cp);
							cDao.updateAccessNumber(community_index);//increment the access number by adding one
							
							List cbList=cDao.getCommunityList3("where community_class=1 and topic_number="+topic_number+" order by written_date asc");
							request.setAttribute("qa_contents_list", cbList);
							if(cbList.size()>0) request.setAttribute("topic_cb", cbList.get(0));
							request.setAttribute("body_panel", "menu_response/community_news_display_contents.jsp");
						}
						else //display all the subject list of messages submitted, just for the page specified
						{	String search=(String)request.getParameter("search");
							
							if(search!=null && search.equals("1")) //search mode
							{	
								String sqlQuery1="", sqlQuery2="";
								String search_word=(String)request.getParameter("stext");
								if(search_word==null) search_word="";
								
								switch(Integer.parseInt(request.getParameter("scategory")))
								{
									case 0: //제목
											sqlQuery1=" where community_class=1 and topic_subject like '%"+search_word+"%' ";
											sqlQuery2=" and topic_subject like '%"+search_word+"%' "; break;
									case 1: //본문
											sqlQuery1=" where  community_class=1 and contents like '%"+search_word+"%' ";
											sqlQuery2=" and contents like '%"+search_word+"%' "; break;
									case 2: //작성자
											sqlQuery1=" where  community_class=1 and person_name like '%"+search_word+"%' ";
											sqlQuery2=" and person_name like '%"+search_word+"%' "; break;					
									default: break;
								}
								
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community"+sqlQuery1)));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=1"+sqlQuery2+"group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_news.jsp");
							}
							else //non-search mode
							{
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=1")));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=1 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_news.jsp");								
							}							
						}
					}
					else if(op.equals("menu_community_q_and_a"))
					{
						String sTopic_number=(String)request.getParameter("topic_number");
						if(sTopic_number!=null)//display qa message that is requested
						{
							int topic_number=Integer.parseInt(sTopic_number);
							long community_index=Long.parseLong((String)request.getParameter("community_index"));
							
							CommunityDao cDao=new CommunityDao(cp);
							cDao.updateAccessNumber(community_index);//increment the access number by adding one
							
							List cbList=cDao.getCommunityList3("where community_class=2 and topic_number="+topic_number+" order by written_date asc");
							request.setAttribute("qa_contents_list", cbList);
							if(cbList.size()>0) request.setAttribute("topic_cb", cbList.get(0));
							request.setAttribute("body_panel", "menu_response/community_q_and_a_display_contents.jsp");
						}
						else //display all the subject list of messages submitted, just for the page specified
						{	String search=(String)request.getParameter("search");
							if(search!=null && search.equals("1")) //search mode
							{	
								String sqlQuery1="", sqlQuery2="";
								String search_word=(String)request.getParameter("stext");
								if(search_word==null) search_word="";
								
								switch(Integer.parseInt(request.getParameter("scategory")))
								{
									case 0: //제목
											sqlQuery1=" where community_class=2 and topic_subject like '%"+search_word+"%' ";
											sqlQuery2=" and topic_subject like '%"+search_word+"%' "; break;
									case 1: //본문
											sqlQuery1=" where  community_class=2 and contents like '%"+search_word+"%' ";
											sqlQuery2=" and contents like '%"+search_word+"%' "; break;
									case 2: //작성자
											sqlQuery1=" where  community_class=2 and person_name like '%"+search_word+"%' ";
											sqlQuery2=" and person_name like '%"+search_word+"%' "; break;					
									default: break;
								}
								
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community"+sqlQuery1)));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=2"+sqlQuery2+"group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_q_and_a.jsp");
							}
							else //non-search mode
							{
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=2")));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List)cDao.getCommunityList2("where community_class=2 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_q_and_a.jsp");								
							}
							
						}
					}					
				}
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			//promotional site
			else if(op.equals("children_and_families"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/children_and_families.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("volunteering_program"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/volunteering_program.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("donation"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/donation.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("whatiscaliper"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/whatiscaliper.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("caliperacrosscanada"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/caliperacrosscanada.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("moreaboutcaliper"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/moreaboutcaliper.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("storybook"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/storybook.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("socialmedia_youtube"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/socialmedia_youtube.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_toronto_sickkids"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_toronto_sickkids.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_toronto_sickkids.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_vancouver_childrens"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_vancouver_childrens.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_vancouver_childrens.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_saskatoon_healthregion"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_saskatoon_healthregion.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_saskatoon_healthregion.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_hamilton_health_sciences"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_hamilton_health_sciences.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_hamilton_health_sciences.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_montreal_ste_justine"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_montreal_ste_justine.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_montreal_ste_justine.jsp")).forward(request,response);
			}
			else if(op.equals("hospital_newfoundland_stjohns"))
			{
				//request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/hospital_newfoundland_stjohns.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template_hospital_newfoundland_stjohns.jsp")).forward(request,response);
			}
			else if(op.equals("display_event"))
			{
				request.setAttribute("body_panel", "menu_response/display_event.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from ActionController.java");
			new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+".");
			forwardErrorPage(request,response,e.toString());
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		doGet(request, response);
	}
	private String getPage(int sm, boolean reply)
	{
		String page="";
		switch(sm)
		{
			case 0: page=(reply? "community_faq_display_contents.jsp": "community_faq.jsp"); break;
			case 1: page=(reply? "community_news_display_contents.jsp": "community_news.jsp"); break;
			case 2: page=(reply? "community_q_and_a_display_contents.jsp": "community_q_and_a.jsp"); break;
			case 3:
			case 4:
			case 5:
			case 6: 
			default:page=(reply? "community_q_and_a_display_contents.jsp": "community_q_and_a.jsp"); break;
		}
		//System.out.println(">>> The page="+page+", sm="+sm);
		return page;
	}
	public void forwardWarningPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("warning_msg", msg+" at "+Utils.currentTimestamp()+"!!!");		
		//request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		//request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
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
