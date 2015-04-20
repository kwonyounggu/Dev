package com.sickkids.caliper.servlet;

import java.io.IOException;

import java.util.Iterator;

import java.util.Map;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sickkids.caliper.common.Message;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.dao.AnalyteNameBean;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.hamcrest.Matchers;
import static ch.lambdaj.Lambda.*;
/**
 * Servlet implementation class SmartPhoneServlet
 * 240x320 is the new baseline for mobile phones. For high end units, 480 would be the correct width to aim for.
 */
public class HttpServiceServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private ServletConfig config=null;
	private ServletContext app=null;
	private ConnectionPool cp = null;
	private SQLDao sqlDao = null;

	public void init(ServletConfig config) 
	{
	    this.config = config;
	    app = config.getServletContext();
	    cp = (ConnectionPool)app.getAttribute("connectionPool");
	    sqlDao = (SQLDao)app.getAttribute("sqlDao");
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");//put this otherwise Korean characters will be broken.
		response.setContentType("text/html; charset=UTF-8");
		//HttpSession session = request.getSession(true);//if(session.isNew()) 
				
		String op=(String)request.getParameter("op");
		//Utils.logger.info("op="+op+" in HttpServiceServlet.java");
		
		try
		{
			
				//response.getWriter().write("heelllk");
				
				//String outputXML = “”;
				String analyte=request.getParameter("analyte");
				String instrument=request.getParameter("instrument");
				String metric=request.getParameter("metric");
				
				System.out.println(analyte+", "+instrument+", "+metric);
				//String outputXML="<op><analyte>"+analyte+"</analyte>"+
				//				 "<instrument>"+analyte+"</instrument>"+
				//				 "<metric>"+analyte+"</metric></op>";
				

				response.getWriter().println(getAnalyteXML(1,1));
				response.getWriter().flush();
				response.getWriter().close();
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
			Utils.logger.severe("(op="+op+"): msg="+e+",\nCustomer IP: "+request.getRemoteAddr()+",\n"+all_params+"from HttpServiceServlet.java");
			//new MailInfo(Utils.caliper_email_address,Utils.emailList,Utils.nameList,Utils.smtp,Message.error_report_subject, "(op="+op+"): "+e+"<br><br>"+all_params+" Generated at "+Utils.currentTimestamp()+"(SmartPhone).");
			//forwardErrorPage(request,response,e.toString());
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
	private String getAnalyteXML(int instrumentId, int metricSystem)
	{
		String outputXML = "<list>";
		List<AnalyteNameBean> analyteList = getAnalyteList(instrumentId, metricSystem);
		
		//System.out.println("size of the list = "+analyteList.size());
		XStream x = new XStream(new DomDriver());
		x.alias("analyte", AnalyteNameBean.class);
		x.aliasField("id", AnalyteNameBean.class, "analyte_name_id");
		x.aliasField("name", AnalyteNameBean.class, "analyte_name");
		x.aliasField("instrument", AnalyteNameBean.class, "instrument_name_id");
		x.aliasField("metric", AnalyteNameBean.class, "metric_system");
		x.aliasField("unitValue", AnalyteNameBean.class, "value_unit");
		x.aliasField("chartPath", AnalyteNameBean.class, "v2_chart_path");
		//outputXML =  x.toXML((AnalyteNameBean)analyteList.get(0));
		for(AnalyteNameBean ab : analyteList) outputXML += x.toXML(ab);
		outputXML += "</list>";
		
		return outputXML;
	}
	private List<AnalyteNameBean> getAnalyteList(int instrumentId, int metricSystem)
	{
		@SuppressWarnings("unchecked")
		List<AnalyteNameBean> analyteList = (List<AnalyteNameBean>)app.getAttribute("analyteList");
		List<AnalyteNameBean> analyteListFromInstrumentId = filter(Matchers.both(having(on(AnalyteNameBean.class).getInstrument_name_id(), Matchers.equalTo(instrumentId))).and(having(on(AnalyteNameBean.class).getMetric_system(), Matchers.equalTo(metricSystem))), analyteList);
		return analyteListFromInstrumentId;
	}
}
