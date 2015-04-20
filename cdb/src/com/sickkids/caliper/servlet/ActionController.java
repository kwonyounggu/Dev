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

import org.hamcrest.Matchers;
import static ch.lambdaj.Lambda.*;

import com.sickkids.caliper.dao.CommunityBean;
import com.sickkids.caliper.dao.CommunityDao;

import com.sickkids.caliper.dao.AnalyteNameBean;
import com.sickkids.caliper.dao.LoginHistoryBean;
import com.sickkids.caliper.dao.LoginHistoryDao;
import com.sickkids.caliper.dao.PersonsBean;
import com.sickkids.caliper.dao.PersonsDao;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.dao.StdReferenceIntervalBean;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.SecureCodeGenerator;
import com.sickkids.caliper.common.UAgentInfo;
import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.service.MailInfo;

@SuppressWarnings("unused")
public class ActionController extends HttpServlet implements Servlet
{	static final long serialVersionUID = 1L;
	static int count=0;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;
	private SQLDao sqlDao = null;
	
	public static List<String> emailList=new ArrayList<String>();
	public static List<String> nameList=new ArrayList<String>();
	
	public void init(ServletConfig config) 
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp=(ConnectionPool)app.getAttribute("connectionPool");
	    sqlDao = (SQLDao)app.getAttribute("sqlDao");
	  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utils.logger.info("doGet() is called in ActionController.java");
		String userAgent = request.getHeader("User-Agent");
	    String httpAccept = request.getHeader("Accept");
	
	    /* Commented on Nov 14, 2013
	    UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
	
	    if (detector.detectSmartphone()) 
	    {
	    	response.sendRedirect(response.encodeRedirectURL("/caliperdatabase/smartphone"));
	    	return;
	    }
	    */
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		
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
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("jobs"))
			{
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/jobs.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("signup"))
			{
				session.setAttribute("security_code", new SecureCodeGenerator(5).generate());
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/signup.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("reg_signup_complete"))
			{
				String email=(String)request.getParameter("reg_email");
				PersonsBean pb=new PersonsBean(email,//person_id
											   "",
											   request.getParameter("reg_eng_name"),
											   email,
											   request.getParameter("reg_password1"),
											   "",//dob
											   "",//sex
											   request.getParameter("reg_address"),
											   "",//city
											   "",//province
											   "",//country
											   "",//postal_code
											   "",//home_phone
											   "",//cell phone
											   false,//want email info?
											   true,//still member
											   "",//remarks
											   Utils.currentTimestamp(),//registered_time
											   "", //id_classification if teacher, student, etc
											   "",
											   request.getParameter("reg_org_name"));//hospital_name
				LoginHistoryBean lb=new LoginHistoryBean(0L,email,Utils.currentTimestamp(),"");
								
				new PersonsDao(cp).create(pb);
				new LoginHistoryDao(cp).create(lb);
				
				session.setAttribute("security_code","");//to prevent 2 times of posting
				session.setAttribute("login_email", request.getParameter("reg_email"));
				session.setAttribute("login_name", request.getParameter("reg_eng_name"));
				session.setAttribute("login_password", request.getParameter("reg_password1"));
				session.setAttribute("pb", pb);
				
				//send email based on the information provided, notify that what the next steps to be followed.
				List<String> to=new ArrayList<String>(); to.add(pb.getEmail());
				List<String> name=new ArrayList<String>(); name.add(pb.getEng_name());
				
				new MailInfo(Utils.caliper_email_address,to,name,Utils.smtp,Message.signup_email_subject, Utils.getSignupReport(pb));
				
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);			
			}
			else if(op.equals("update_reg_signup_complete"))//ajax
			{	
				try
				{
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
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/signin.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("login_check"))//ajax request
			{					
				try
				{
					String input_login_email=(String)request.getParameter("login_email");
					String input_login_password=(String)request.getParameter("login_password");
					
					if(input_login_email!=null && !input_login_email.equals("") && input_login_password!=null && !input_login_password.equals(""))
					{
						PersonsBean pb=new PersonsDao(cp).getARecord("where person_id='"+input_login_email+"'");
						
						if(pb.getPerson_id().equalsIgnoreCase(input_login_email)) 
						{
							if(pb.getLogin_password().equals(input_login_password))
							{
								//every thing is OK, ie: login is successful
								session.setAttribute("login_email", input_login_email);
								session.setAttribute("login_name", pb.getEng_name());
								session.setAttribute("login_password", input_login_password);
								session.setAttribute("pb", pb);
								//((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/signin_success.jsp")).forward(request,response);
								//provide the server url's home page after a successful login is done.
								response.getWriter().write("http://"+request.getServerName()+":"+Integer.toString(request.getServerPort())+"/caliperdatabase/controller");
							}
							else response.getWriter().write("false:"+Message.incorrect_password);
						}
						else response.getWriter().write("false:"+Message.incorrect_email);
					}
					else response.getWriter().write("false:"+Message.incorrect_email_password); 
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
				session.removeAttribute("login_name");
				session.removeAttribute("login_password");
				session.removeAttribute("pb");
				//((RequestDispatcher)request.getRequestDispatcher("jsp/login/login_template_manager.jsp")).forward(request,response);
				
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "home_body.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("forgot_pwd"))//ajax request
			{	
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/signin_forgot_password.jsp")).forward(request,response);
			}
			else if(op.equals("forgot_pwd_check"))//ajax request
			{	
				try
				{
					String value=(String)request.getParameter("login_email");
					if(value!=null && !value.equals(""))
					{
						PersonsBean pb=new PersonsDao(cp).getARecord("where person_id='"+value+"'");
						
						if(pb.getPerson_id().equalsIgnoreCase(value)) 
						{
							emailList.clear();
							nameList.clear();
							emailList.add(pb.getPerson_id());
							nameList.add(pb.getEng_name());
							new MailInfo(Utils.caliper_email_address,emailList,nameList,Utils.smtp,Message.email_password_subject, 
										 Message.password_information+pb.getLogin_password()+Message.msg_end+" Generated at "+Utils.currentTimestamp()+".");
							response.getWriter().write("true:"+Message.emailed_password);
						}
						else response.getWriter().write("false:"+Message.non_existing_email);
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
				//session.setAttribute("security_code", new SecureCodeGenerator(5).generate());
				((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/reply_message.jsp")).forward(request,response);
				//((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/write_message.jsp")).forward(request,response);
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
			else if(op.indexOf("ajax")!=-1)
			{
				if(op.equals("ajax_get_analyte_data_graph"))
				{
					try
					{
						int analyte_id = Integer.parseInt(request.getParameter("analyte_id"));
						int metric_unit_id = Integer.parseInt(request.getParameter("metric_unit_id"));
						int instrument_id = Integer.parseInt(request.getParameter("instrument_id"));
						   
						 session.setAttribute("analyte_name", request.getParameter("analyte_name"));//session used due to print
						 session.setAttribute("analyte_chart_path", request.getParameter("analyte_chart_path"));//session used due to print	
						 
						 String paper_link=request.getParameter("paper_link");
						 session.setAttribute("paper_link", paper_link);	
						 if(paper_link.equals("22371482")) 
						 {
							 request.setAttribute("paper_year", "2012");
							 request.setAttribute("abbott_architect", "c8000");
						 }
						 else if(paper_link.equals("23637248")) 
						 {
							 request.setAttribute("paper_year", "2013");
							 request.setAttribute("abbott_architect", "i2000");
						 }
						 else if(paper_link.equals("23637247")) 
						 {
							 request.setAttribute("paper_year", "2013");
							 request.setAttribute("abbott_architect", "i2000");
						 }
						 
						 session.setAttribute("femaleList", sqlDao.getListStdReferenceIntervals(analyte_id, instrument_id, metric_unit_id, 2, 2));
						 session.setAttribute("maleList", sqlDao.getListStdReferenceIntervals(analyte_id, instrument_id, metric_unit_id, 1, 2));
						((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/ajax_display_std_rererence_interval_data_graph.jsp")).forward(request,response);
					}
					catch(Exception e)
					{
						Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
						response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
					}
				}
				else if(op.equals("ajax_get_new_analyte_list"))
				{
					try
					{
						
						int instrument_id = Integer.parseInt((String)request.getParameter("instrument_id"));
						int metric_unit_id = Integer.parseInt((String)request.getParameter("metric_unit_id"));
						
						request.setAttribute("newAnalyteList", getAnalyteList(instrument_id, metric_unit_id));
						((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/ajax_get_new_analyte_name_list.jsp")).forward(request,response);
					}
					catch(Exception e)
					{
						Utils.logger.severe("(op="+op+" for ajax request): msg="+e+" from ActionController.java");
						response.getWriter().write("false:an error has been occurred\n\n"+e+"\n\n"+Message.inform_to_admin_about_exception+"\nE-Mail: "+Utils.caliper_email_address);
					}
				}
			}
			else if(op.equals("write_new_msg"))//from a new message writing
			{					
				String contents=(String)request.getParameter("qa_message");
				if(contents.length()>=Utils.MAX_LEN_FOR_TEXTAREA) contents=contents.substring(0, Utils.MAX_LEN_FOR_TEXTAREA-1);
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
				
				request.setAttribute("left_panel", "menu_response/community_submenu.jsp");
				request.setAttribute("body_panel", "menu_response/"+getPage(cb.getCommunity_class(),false));
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);//return to the default 1st page after				
			}
			else if(op.equals("write_reply_msg"))//from a reply message writing
			{					
				String sTopic_number=(String)request.getParameter("topic_number");
				String contents=(String)request.getParameter("qa_message");
				contents=contents.replace("\r\n", "<br>");
				CommunityBean cb=new CommunityBean(0,//main index
												   Integer.parseInt(request.getParameter("sm")),//0: faq, 1: 공지사항,  2: 질문 및 응답, 3: 자주하는질문
												   0,//0:일반, 1:수업료, 2:화상통화, 3:선생님, 4:캠프, 5:영어
												   (String)request.getParameter("qa_email"),//person_id
												   (String)request.getParameter("qa_name"),//korean_name or eng_name
												   (String)request.getParameter("qa_subject"),
												   contents,
												   Long.parseLong(sTopic_number),
												   (String)request.getParameter("community_index")+":"+(String)request.getParameter("in_response_to"),//in_response_to 0 means root parent
												   Utils.currentTimestamp(),//written date
												   0,//access number
												   "0000",//request.getParameter("qa_delete_password"),
												   0,//my page, initially 0
												   "");
				new CommunityDao(cp).create(cb); //create a reply record
				
				session.setAttribute("security_code","");//to prevent 2 times of posting
				
				request.setAttribute("left_panel", "menu_response/community_submenu.jsp");
				
				int topic_number=Integer.parseInt(sTopic_number);
				CommunityDao cDao=new CommunityDao(cp);
				List<CommunityBean> cbList=cDao.getCommunityList3("where community_class="+cb.getCommunity_class()+" and topic_number="+topic_number+" order by written_date asc");
				request.setAttribute("qa_contents_list", cbList);
				if(cbList.size()>0) request.setAttribute("topic_cb", cbList.get(0));
				request.setAttribute("body_panel", "menu_response/"+getPage(cb.getCommunity_class(),true));
				//String paramString="sm=2&search="+request.getParameter("search")+
				//						"&stext="+request.getParameter("stext")+
				//						"&scategory="+request.getParameter("scategory")+
				//						"&section="+request.getParameter("section")+
				//						"&page="+request.getParameter("page");
				//System.out.println("paramString="+paramString);
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
				
			}
			else if(op.equals("contactus"))
			{
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/contactus.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("photos"))
			{
				request.setAttribute("left_panel", "home_lhs.jsp");
				request.setAttribute("body_panel", "menu_response/photos.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("sitemap"))
			{
				request.setAttribute("left_panel", "home_lhs.jsp");
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
					request.setAttribute("left_panel", "menu_response/aboutcaliper_submenu.jsp");
					if(op.equals("menu_aboutcaliper_whatiscaliper"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_whatiscaliper.jsp");
					else if(op.equals("menu_aboutcaliper_moreaboutcaliper"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_moreaboutcaliper.jsp");
					else if(op.equals("menu_aboutcaliper_caliperacrosscanada"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_caliperacrosscanada.jsp");
					else if(op.equals("menu_aboutcaliper_calipermoment"))
						request.setAttribute("body_panel", "menu_response/aboutcaliper_calipermoment.jsp");
					
				}
				else if(op.equals("menu_caliper_study"))
					request.setAttribute("body_panel", "menu_response/caliper_study.jsp");
				else if(op.indexOf("menu_howtoparticipate")!=-1)
				{	
					request.setAttribute("left_panel", "menu_response/howtoparticipate_submenu.jsp");
					if(op.equals("menu_howtoparticipate_howtoparticipate"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_howtoparticipate.jsp");
					else if(op.equals("menu_howtoparticipate_volunteer"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_volunteer.jsp");
					else if(op.equals("menu_howtoparticipate_clinics"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_clinics.jsp");
					else if(op.equals("menu_howtoparticipate_storybook"))
						request.setAttribute("body_panel", "menu_response/howtoparticipate_storybook.jsp");
					
				}
				else if(op.equals("menu_about_reference_intervals"))
					request.setAttribute("body_panel", "menu_response/about_reference_intervals.jsp");
				else if(op.equals("menu_reference_intervals"))
				{
					request.setAttribute("newAnalyteList", getAnalyteList(1, 1)); //getAnalyteList(1: Abbott, 1: SI)
					request.setAttribute("reference_interval_v2_tab", "reference_intervals_v2.jsp");
					request.setAttribute("body_panel", "menu_response/reference_intervals.jsp");
				}
				else if(op.equals("menu_downloads_raw_data"))
					request.setAttribute("body_panel", "menu_response/downloads_raw_data.jsp");
				else if(op.equals("menu_custom_reference_intervals"))
					request.setAttribute("body_panel", "menu_response/custom_reference_intervals.jsp");				
				else if(op.indexOf("menu_community")!=-1)//고개센터
				{	
					request.setAttribute("left_panel", "menu_response/community_submenu.jsp");
					if(op.equals("menu_community_faq"))
					{	
						request.setAttribute("body_panel", "menu_response/community_faq.jsp");													
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
							
							List<CommunityBean> cbList=cDao.getCommunityList3("where community_class=1 and topic_number="+topic_number+" order by written_date asc");
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
								request.setAttribute("qa_msg_list", (List<CommunityBean>)cDao.getCommunityList2("where community_class=1"+sqlQuery2+"group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_news.jsp");
							}
							else //non-search mode
							{
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=1")));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List<CommunityBean>)cDao.getCommunityList2("where community_class=1 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
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
							
							List<CommunityBean> cbList=cDao.getCommunityList3("where community_class=2 and topic_number="+topic_number+" order by written_date asc");
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
								request.setAttribute("qa_msg_list", (List<CommunityBean>)cDao.getCommunityList2("where community_class=2"+sqlQuery2+"group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_q_and_a.jsp");
							}
							else //non-search mode
							{
								CommunityDao cDao=new CommunityDao(cp);
								request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=2")));
								int offset=(Integer.parseInt(request.getParameter("page"))-1)*Utils.displayLimit;
								request.setAttribute("qa_msg_list", (List<CommunityBean>)cDao.getCommunityList2("where community_class=2 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset));
								request.setAttribute("body_panel", "menu_response/community_q_and_a.jsp");								
							}
							
						}
					}					
				}
				else if(op.equals("menu_print_reference_intervals"))
				{
					((RequestDispatcher)request.getRequestDispatcher("jsp/menu_response/print_standard_rfi.jsp")).forward(request,response);
					return;//no more page_template is required
				}
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			
			else if(op.equals("caliper_program"))
			{
				request.setAttribute("body_panel", "menu_response/caliper_program.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("whatiscaliper"))
			{
				request.setAttribute("body_panel", "menu_response/whatiscaliper.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
			else if(op.equals("caliper_publications_presentations"))
			{
				request.setAttribute("body_panel", "menu_response/caliper_publications_presentations.jsp");
				((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
			}
		}
		catch(Exception e)
		{	
			Map<?, ?> params = request.getParameterMap();
		    Iterator<?> i = params.keySet().iterator();
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
	private List<AnalyteNameBean> getAnalyteList(int instrumentId, int metricSystem)
	{
		@SuppressWarnings("unchecked")
		List<AnalyteNameBean> analyteList = (List<AnalyteNameBean>)app.getAttribute("analyteList");
		List<AnalyteNameBean> analyteListFromInstrumentId = filter(Matchers.both(having(on(AnalyteNameBean.class).getInstrument_name_id(), Matchers.equalTo(instrumentId))).and(having(on(AnalyteNameBean.class).getMetric_system(), Matchers.equalTo(metricSystem))), analyteList);
		return analyteListFromInstrumentId;
	}
	public void forwardWarningPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("warning_msg", msg+" at "+Utils.currentTimestamp()+"!!!");		
		request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_warning_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPage(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" false: at "+Utils.currentTimestamp()+"!!!"+
							 "<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=CALIPER ERROR NOTIFICATION' style='color: #FE5734;text-decoration: underline;'>E-MAIL</a>"+
							 "<br><br>Your IP Address: "+request.getRemoteAddr());		
		request.setAttribute("left_panel", "home_lhs.jsp");
		request.setAttribute("body_panel", "error/servlet_error_page.jsp");
		((RequestDispatcher)request.getRequestDispatcher("jsp/page_template.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjax(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		request.setAttribute("error_msg", msg+" at "+Utils.currentTimestamp()+"!!!<br><br>"+Message.inform_to_admin_about_exception+"&nbsp;&nbsp;<a href='mailto:"+Utils.caliper_email_address+"?subject=myESL 에러발생 통보' style='color: #FE5734;text-decoration: underline;'>이메일</a>");		
		((RequestDispatcher)request.getRequestDispatcher("jsp/error/servlet_error_page.jsp")).forward(request,response);
	}
	public void forwardErrorPageForAjaxDlg(HttpServletRequest request,HttpServletResponse response,String msg)throws ServletException, IOException
	{	
		new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, msg+" Generated at "+Utils.currentTimestamp()+".");
		response.getWriter().write("false:"+Message.general_error_msg+"\n\n"+msg+"\n\n"+Message.inform_to_admin_about_exception2);
	}

}
