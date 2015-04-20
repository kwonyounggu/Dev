package com.sickkids.caliper.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.logging.*;
import java.io.*;

import java.text.DateFormat;
import java.util.Date;

import java.util.Iterator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sickkids.caliper.common.MenuLink;
import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.OnlineRegistrationBean;
import com.sickkids.caliper.dao.PersonsBean;
//import com.sickkids.caliper.dao.PersonsBean;




public class Utils
{	
		public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		public static final String[] alphanumeric={"0","1","2","3","4","5","6","7","8","9",
											   "A","B","C","D","E","F","G","H","I","J",
											   "K","L","M","N","O","P","Q","R","S","T",
											   "U","V","W","X","Y","Z"};

		//private static final String timeZone="Asia/Seoul";//America/Toronto
		private static final String timeZone="America/Toronto";
		public static String email_id;
		public static String email_password;
		public static String caliper_email_address;
		public static String error_email_address;
		public static String caliper_owner_email_address;
		public static String smtp;
		public static String smtp_debug;
		public static String caliper_project_coordinator_email;
		public static String caliper_project_coordinator_name;
		public static String caliper_system_admin_name="CALIPER SYSTEM ADMIN";

		public static int displayLimit=10;
		public static final int SUBJECT_LIMIT=85;
		public static final int MAX_LEN_FOR_TEXTAREA=2040;
		public static final int MAX_LEN_FOR_EVENT_SUMMARY=512;
		public static String log_dir_file;
		public static String log_file;
		public static String my_home_page;
		
		public static List<String> emailList=new ArrayList<String>();
		public static List<String> nameList=new ArrayList<String>();
		

		public static String getFormattedDate(Date date)
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        return dateFormat.format(date);
	    }
	    public static String now() 
	    {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	        return sdf.format(cal.getTime());
	    }

	    public static final Map<String, String> analyteMap = createMap();
	    public static final Map<String, String> ethnicMap = createEthnicMap();

	    private static Map<String, String> createMap() 
	    {
	        Map<String, String> result = new HashMap<String, String>();
	        result.put("albumin_g", "Albumin G");
	        result.put("albumin_p", "Albumin P");
	        result.put("alkaline_phosphatase", "Alkaline Phosphatase");
	        result.put("alt", "ALT");
	        result.put("alt_act", "ALT (ACT)");
	        result.put("amylase", "Amylase");
	        result.put("apo_a1", "Apo A1");
	        result.put("apo_b", "Apo B");
	        result.put("aso","ASO");
    		result.put("ast","AST");
    		result.put("ast_act","AST (ACT)");
    		result.put("bilirubin_direct","Bilirubin-Direct (D)");
    		result.put("bilirubin_total","Bilirubin-Total (T)");
    		result.put("c3","C3");
    		result.put("c4","C4");
    		result.put("calcium","Calcium");
    		result.put("che","ChE");
    		result.put("cholesterol","Cholesterol");
    		result.put("co2","CO2 (carbon dioxide)");
    		result.put("creatinine_enzymatic","Creatinine (enzymatic)");
    		result.put("creatinine_jaffee","Creatinine (Jaffee)");
    		result.put("crp","CRP");
    		result.put("ggt","GGT");
    		result.put("haptoglobin","Haptoglobin");
    		result.put("iga","IgA");
    		result.put("igg","IgG");
    		result.put("igm","IgM");
    		result.put("iron","Iron");
    		result.put("iron_plasma","Iron (plasma)");
    		result.put("ldh_ld","LDH (LD)");
    		result.put("lipase_lip","Lipase (lip)");
    		result.put("magnesium","Magnesium");
    		result.put("phosphorus","Phosphorus");
    		result.put("prealbumin","Prealbumin");
    		result.put("rf_rheumatoid_factor","RF (rheumatoid factor)");
    		result.put("total_protein","Total Protein");
    		result.put("transferrin_trf","Transferrin (TRF)");
    		result.put("triglycerides","Triglycerides");
    		result.put("uhdl_ultra_hdl","UHDL (Ultra HDL)");
    		result.put("urea","Urea");
    		result.put("uric_acid","Uric Acid");
	        
	        return Collections.unmodifiableMap(result);
	    }
	    
	    private static Map<String, String> createEthnicMap() 
	    {
	        Map<String, String> result = new HashMap<String, String>();
	        result.put("1", "Aboriginal");
	        result.put("2", "Arab");
	        result.put("3", "Black");
	        result.put("4", "Chinese");
	        result.put("5", "Fillipino");
	        result.put("6", "Japanese");
	        result.put("7", "Korean");
	        result.put("8", "Latin American");
	        result.put("9","South Asian");
    		result.put("10","South East Asian");
    		result.put("11","Caucasian");
    		result.put("12","All");
	        
	        return Collections.unmodifiableMap(result);
	    }
		public static final double roundOff(double x)
		{
			long val = Math.round(x * 100); // cents

			return val / 100.0;
		}
		public static void dumpMap(Map<?, ?> mp) 
		{
		    Iterator<?> it = mp.entrySet().iterator();
		    while (it.hasNext()) 
		    {
		        @SuppressWarnings("rawtypes")
				Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		    }
		}
		
			
		public static String getDateTime()
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static String getDateTimeForFileName()
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static String getDate(Timestamp ts)
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        return dateFormat.format(ts);
	    }
		public static String getCADate(Timestamp ts)
		{
	        DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
	        return dateFormat.format(ts);
	    }
		public static String getUSDate(Timestamp ts)
		{ 
			if(ts==null) return "";
	        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        return dateFormat.format(ts);
	    }
		public static String getDateTime(Timestamp ts)
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        return dateFormat.format(ts);
	    }
		public static String getDoublePrecision(double v)
		{
			DecimalFormat df=new DecimalFormat("###.##");
		    return df.format(v);
		}
		@SuppressWarnings("deprecation")
		public static Timestamp currentTimestamp()
	    {
	    	TimeZone tzseoul  = TimeZone.getTimeZone(timeZone);
	    	GregorianCalendar cal = new GregorianCalendar(tzseoul);
	    	
	    	Date date=new Date(); 
	    	date.setYear(cal.get(Calendar.YEAR)-1900);
	    	date.setMonth(cal.get(Calendar.MONTH));
	    	date.setDate(cal.get(Calendar.DAY_OF_MONTH));
	    	date.setHours(cal.get(Calendar.HOUR_OF_DAY));
	    	date.setMinutes(cal.get(Calendar.MINUTE));
	    	date.setSeconds(cal.get(Calendar.SECOND));
	    	
	    	return new Timestamp(date.getTime());	    	
	    }
	    static 
	    {
	        try 
	        {
	            ResourceBundle bundle = ResourceBundle.getBundle("general_information");            
	            email_id=bundle.getString("smtp_email_id");
	            email_password=bundle.getString("smtp_email_password");
	            smtp=bundle.getString("smtp");
	            smtp_debug=bundle.getString("smtp_debug");
	            caliper_email_address=bundle.getString("mycompany_email_address");
	            error_email_address=bundle.getString("error_email_address");
	            caliper_owner_email_address=bundle.getString("mycompany_owner_email_address");
	            log_dir_file=bundle.getString("log_dir_file");
	            log_file=bundle.getString("log_file");
	            caliper_project_coordinator_email=bundle.getString("caliper_project_coordinator_email");
	            caliper_project_coordinator_name=bundle.getString("caliper_project_coordinator_name");
	            my_home_page=bundle.getString("my_home_page");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    static 
	    {
	        try 
	        {
	            emailList.add(caliper_email_address);
	            nameList.add("admin");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    public static Logger logger;

	    static 
	    {
	        try 
	        {
	          boolean append = true;
	          FileHandler fh = new FileHandler(Utils.log_dir_file, append);
	          fh.setFormatter(new SimpleFormatter());
	          logger = Logger.getLogger(Utils.log_file);
	          logger.addHandler(fh);
	        }
	        catch (IOException e) 
	        {
	        	System.err.println("ERROR: "+e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    public static final String getSignupReport(PersonsBean pb)
	    {
	    	
		    	//String tempS=pb.getPhone2().equals("")?"":"&nbsp;(휴대용)";
		    	String style=
		    		"<style type=text/css>"+ 		    	
		    			"body,td{font-size:9pt; font-family:arial; word-break:break-all;}"+
				    	".header2_td{text-align:center; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}"+ 
				    	".header3_td{text-align:center; background-color:#F7F7EA; border-bottom:1px solid #fdfdfd;}"+
				    	".dv_line{background-color:#f8f8f8; height:1px; padding:0;} "+ 
				    	".center_td{text-align:center;}"+ 
				    "</style>";
		    	String body="Hello, "+pb.getEng_name()+"!"+"<br><br>"+
		    				"We welcome you to join our CALIPER, non-profit organization.<br>"+
		    				"Your signup information is as follows:<br><br>"+
			    	"<table border=0 cellspacing=2 cellpadding=4 width='775' style='padding: 0 0 0 20px;'>"+	
				    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+  
				    	"<tr>"+  
							"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Login Email:</td> "+ 
							"<td colspan=4 align='left'>&nbsp;"+pb.getEmail()+"</td>"+
						"</tr>"+     				
						"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+
							"<tr>"+  
							"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Login Password:</td> "+ 
							"<td colspan=4 align='left'>&nbsp;"+pb.getLogin_password()+"</td>"+
						"</tr>"+  
						"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+ 
				    	"<tr>"+  
			    		"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Hospital Name:</td>"+  
			    		"<td colspan=4 align='left'>&nbsp;"+pb.getOrg_name()+"</td>"+   
			    		"</tr>"+ 
				    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+  
				    	"<tr>"+  
				    		"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Address:</td>"+  
				    		"<td colspan=4 align='left'>&nbsp;"+pb.getAddress()+"</td>"+   
				    	"</tr>"+  
				    	 
			    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+ 
				    "</table>"+
				    "<br><br>"+
				    "<br>If you have any question, ask through <a href='"+Utils.my_home_page+"/"+MenuLink.community_q_and_a+"'>www.caliperdatabase.com>Community>Questions & Answers</a>."+
				    "<br><br>Best regards,<br> <a href='"+Utils.my_home_page+"'>Caliper</a> Administrator.";  
	    	return "<htm><head>"+style+"</head><body style='font-size:9pt; font-family:arial; word-break:break-all;'>"+body+"</body></html>";
		}
	    public static final String getRegisterToParticipate(OnlineRegistrationBean rb)
	    {
	    	
		    	String sex=((rb.getSex()==1) ? "Male":"Female");
		    	String style=
		    		"<style type=text/css>"+ 		    	
		    			"body,td{font-size:9pt; font-family:arial; word-break:break-all;}"+
				    	".header2_td{text-align:center; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}"+ 
				    	".header3_td{text-align:center; background-color:#F7F7EA; border-bottom:1px solid #fdfdfd;}"+
				    	".dv_line{background-color:#f8f8f8; height:1px; padding:0;} "+ 
				    	".center_td{text-align:center;}"+ 
				    "</style>";
		    	String body="Hello, "+rb.getName()+"!"+"<br><br>"+
		    				"We welcome you to join our CALIPERPROJECT, non-profit organization, at the hospital for SickKids,<br>located at 555 University Avenue, Toronto, Ontario, Canada M1S5C2.<br/><br/>"+
		    				"Your registration information is as follows:<br><br>"+
			    	"<table border=0 cellspacing=2 cellpadding=4 width='775' style='padding: 0 0 0 20px;'>"+	
				    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+  
				    	"<tr>"+  
							"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Your Name:</td> "+ 
							"<td colspan=4 align='left'>&nbsp;"+rb.getName()+"</td>"+
						"</tr>"+     				
						"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+
							"<tr>"+  
							"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Your Email:</td> "+ 
							"<td colspan=4 align='left'>&nbsp;"+rb.getEmail()+"</td>"+
						"</tr>"+  
						"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+ 
				    	"<tr>"+  
			    		"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Your Sex:</td>"+  
			    		"<td colspan=4 align='left'>&nbsp;"+sex+"</td>"+   
			    		"</tr>"+ 
				    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+  
				    	"<tr>"+  
				    		"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Your Phone:</td>"+  
				    		"<td colspan=4 align='left'>&nbsp;"+rb.getPhone()+"</td>"+   
				    	"</tr>"+ 
				    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+  
				    	"<tr>"+  
				    		"<td style='text-align: left; padding: 0 0 0 25px;'>◆&nbsp;Your School:</td>"+  
				    		"<td colspan=4 align='left'>&nbsp;"+rb.getSchool()+"</td>"+   
				    	"</tr>"+ 
				    	 
			    	"<tr><td colspan=5 height=1 style='background-color:#f8f8f8; height:1px; padding:0;'></td></tr>"+ 
				    "</table>"+
				    "<br><br>"+
				    "<br>If you have any question, ask our CALIPER PROJECT COORDINATOR, "+Utils.caliper_project_coordinator_name+" through <a href='mailto:"+Utils.caliper_project_coordinator_email+"'>"+Utils.caliper_project_coordinator_email+"</a>."+
				    "<br><br>Best regards,<br> <a href='www.caliperproject.com'>Caliper</a>&nbsp;System Administrator.";  
	    	return "<htm><head>"+style+"</head><body style='font-size:9pt; font-family:arial; word-break:break-all;'>"+body+"</body></html>";
		}
	    public static final String getEnrolmentReport(HttpServletRequest request, Object pb)
	    {
	    	return "temp";
	    }
	    
}
