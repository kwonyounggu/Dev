package org.red5.common;

import java.util.ArrayList;
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

public class Utils
{	
		public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		public static final String[] alphanumeric={"0","1","2","3","4","5","6","7","8","9",
											   "A","B","C","D","E","F","G","H","I","J",
											   "K","L","M","N","O","P","Q","R","S","T",
											   "U","V","W","X","Y","Z"};

		private static final String timeZone="Asia/Seoul";
		
		public static String email_id;
		public static String email_password;
		public static String csr_email_address;
		public static String error_email_address;
		public static String csr_owner_email_address;
		public static String smtp;
		public static String smtp_debug;
		public static boolean debug;
		
		//public static String 	epoch_scheduler_run_time;
		//public static String	chief_pi_email_id;
		//public static String	chief_pi_name;
		//public static String	epoch_manager_email_id;
		//public static String	epoch_manager_name;

		//public static int epochNewsDisplayLimit=15;//15 would be good
		//public static int bpewsNewsDisplayLimit=15;//15 would be good
		//public static final int SUBJECT_LIMIT=90;
		//public static final int MAX_LEN_FOR_TEXTAREA=2040;
		//public static final int MAX_LEN_FOR_COMMENTS=1024;
		//public static final int MAX_LEN_FOR_H_NAME=32;//Hospital name
		
		public static String log_dir_file;
		public static String log_file;
		public static String my_home_page;
		
		public static List<String> emailList=new ArrayList<String>();
		public static List<String> nameList=new ArrayList<String>();	
		
		//********** tttServer ***************************
		public static int MAX_CLIENTS=10;//will be assigned from max_clients_accessible, general_informatio.properties

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
		public static String getDateTimeForFileName()
		{
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
		public static String getDate(Timestamp ts)
		{ 
			if(ts==null) return "";
	        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        return dateFormat.format(ts);
	    }
		public static String getMMMDate(Timestamp ts)
		{ 
			//if(ts==null) return "NA";
			if(ts.equals(Timestamp.valueOf("1969-01-01 00:00:00"))) return "NA";
	        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
	        return dateFormat.format(ts);
	    }
		public static String getDetailedMMMDate(Timestamp ts)
		{ 
			if(ts.equals(Timestamp.valueOf("1969-01-01 00:00:00"))) return "NA";
	        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
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
		public static String getChoppedString(String s, int n)
		{
			return (s.length()>n? s.substring(0, n): s);
		}

		public static Timestamp currentTimestamp()
	    {
	    	return new Timestamp(new Date().getTime());	    	
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
	            csr_email_address=bundle.getString("mycompany_email_address");
	            error_email_address=bundle.getString("error_email_address");
	            csr_owner_email_address=bundle.getString("mycompany_owner_email_address");
	            log_dir_file=bundle.getString("log_dir_file");
	            log_file=bundle.getString("log_file");
	            my_home_page=bundle.getString("my_home_page");
	            debug=(bundle.getString("debug").equals("true")? true: false);
	            
	            //epoch_scheduler_run_time=bundle.getString("epoch_scheduler_run_time");
            	//chief_pi_email_id=bundle.getString("chief_pi_email_id");
            	//chief_pi_name=bundle.getString("chief_pi_name");
            	//epoch_manager_email_id=bundle.getString("epoch_manager_email_id");
            	//epoch_manager_name=bundle.getString("epoch_manager_name");
            	
            	MAX_CLIENTS=Integer.parseInt(bundle.getString("max_clients_accessible"));
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
}
