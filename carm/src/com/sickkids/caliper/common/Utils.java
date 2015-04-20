package com.sickkids.caliper.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import java.util.List;
import java.util.ResourceBundle;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.logging.*;
import java.io.*;

import java.text.DateFormat;
import java.util.Date;

import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.service.MailInfo;


public class Utils
{	
		public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

		public static String csr_email_address;
		public static String error_email_address;
		public static String csr_owner_email_address;
		
		public static String smtp;
		public static String smtp_debug;
		public static boolean debug;
		
		public static String smtp_email_id;
		public static String smtp_email_password;
		
		public static String	chief_pi_email_id;
		public static String	chief_pi_name;
		
		
		public static String	c_manager_email_id;
		public static String	c_manager_name;

		public static final int SUBJECT_LIMIT=90;
		public static final int MAX_LEN_FOR_TEXTAREA=2040;
		public static final int MAX_LEN_FOR_COMMENTS=1024;
		public static final int MAX_LEN_FOR_H_NAME=32;//Hospital name
		
		public static String log_dir_file;
		public static String log_file;
		public static String my_home_page;
		public static Timestamp login_access_limit_date=null;
		public static int group_review_timeout_offset=0;
		
		public static int c_senior_seniority=0;//will be changed by general_information.properties
		
		public static List<String> emailList=new ArrayList<String>();
		public static List<String> nameList=new ArrayList<String>();
		
		public static final int fileSize=5*1024*1024;
		
		public static final String ORALCE_DB="BPEWS_EX";
		
		
		public static void delay(int sec)
		{
			try 
			{
			    Thread.sleep(sec*1000);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}
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
	    public static String nowUS() 
	    {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
	        return sdf.format(cal.getTime());
	    }
	    public static long nowPlus(int day)
	    {
	    	Calendar c = Calendar.getInstance(); 
	    	c.add(Calendar.DATE, day);
	    	return c.getTimeInMillis();
	    }
		public static final double roundOff(double x)
		{
			long val = Math.round(x * 100); // cents

			return val / 100.0;
		}		
		public static String getDateTime()
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static String getMMMDateTime()
		{
	        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static synchronized String getDateTimeForFileName()
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static synchronized String getDate(Timestamp ts)
		{ 
			if(ts==null) return "";
	        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        return dateFormat.format(ts);
	    }
		public static synchronized String getMMMDate(Timestamp ts)
		{ 
			if(ts.equals(Timestamp.valueOf("1969-01-01 00:00:00"))) return "NA";
	        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
	        return dateFormat.format(ts);
	    }
		public static synchronized String getDetailedMMMDate(Timestamp ts)
		{ 
			if(ts.equals(Timestamp.valueOf("1969-01-01 00:00:00"))) return "NA";
	        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
	        return dateFormat.format(ts);
	    }
		//Provide ts+wk
		public static synchronized String getSpecifiedDate(Timestamp ts, int wk)
		{ 
			if(ts.equals(Timestamp.valueOf("1969-01-01 00:00:00"))) return "NA";
			DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(ts);
	        cal.add(Calendar.DAY_OF_WEEK, wk*7);
	        return dateFormat.format(cal.getTime());
	    }
		public static synchronized String getOffsetDate(int wk)
		{ 
			DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(currentTimestamp());
	        cal.add(Calendar.DAY_OF_WEEK, wk*7);
	        return dateFormat.format(cal.getTime());
	    }
		public static synchronized Timestamp getSpecifiedTimestamp(Timestamp ts, int wk)
		{ 
			Calendar cal = Calendar.getInstance();
	        cal.setTime(ts);
	        cal.add(Calendar.DAY_OF_WEEK, wk*7);
	        return new Timestamp(cal.getTimeInMillis());
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
		public static String getChoppedString(String s, int n)
		{
			return (s.length()>n? s.substring(0, n): s);
		}
		public static synchronized String getFirstCapitalString(String s)
		{
			char[] stringArray = s.toLowerCase().toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);

			return String.valueOf(stringArray);
		}

		public static synchronized Timestamp currentTimestamp()
	    {
	    	return new Timestamp(new Date().getTime());	    	
	    }
		//leading zeros in the given 'num' parameter
		//eg: intToString(2, 2) will return 02
		public static synchronized String intToString(int num, int digits) 
		{
		    assert digits > 0 : "Invalid number of digits";

		    // create variable length array of zeros
		    char[] zeros = new char[digits];
		    Arrays.fill(zeros, '0');
		    // format number as String
		    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

		    return df.format(num);
		}
	    static 
	    {
	        try 
	        {
	            ResourceBundle bundle = ResourceBundle.getBundle("general_information");            
	            smtp_email_id=bundle.getString("smtp_email_id");
	            smtp_email_password=bundle.getString("smtp_email_password");
	            smtp=bundle.getString("smtp");
	            smtp_debug=bundle.getString("smtp_debug");
	            csr_email_address=bundle.getString("mycompany_email_address");
	            error_email_address=bundle.getString("error_email_address");
	            csr_owner_email_address=bundle.getString("mycompany_owner_email_address");
	            log_dir_file=bundle.getString("log_dir_file");
	            log_file=bundle.getString("log_file");
	            my_home_page=bundle.getString("my_home_page");
	            debug=(bundle.getString("debug").equals("true")? true: false);
	            
	            
            	chief_pi_email_id=bundle.getString("chief_pi_email_id");
            	chief_pi_name=bundle.getString("chief_pi_name");
            	        	
            	c_manager_email_id=bundle.getString("c_manager_email_id");
            	c_manager_name=bundle.getString("c_manager_name");          	
            	
            	c_senior_seniority=Integer.parseInt(bundle.getString("c_senior_seniority"));
            	login_access_limit_date=Timestamp.valueOf(bundle.getString("login_access_limit_date"));
            	
            	group_review_timeout_offset=Integer.parseInt(bundle.getString("group_review_timeout_offset"));
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
	            emailList.add(csr_email_address);
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
	    public static boolean isNumeric(String str)
	    {
	        for (char c : str.toCharArray())
	        {
	            if (!Character.isDigit(c)) return false;
	        }
	        return true;
	    }
}
