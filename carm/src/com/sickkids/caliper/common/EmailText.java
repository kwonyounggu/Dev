package com.sickkids.caliper.common;

import java.util.List;

import com.sickkids.caliper.dao.AllRegisteredUserBean;
import com.sickkids.caliper.service.MailInfo;

public class EmailText
{
    public static void emailCreateOrUpdatedPassword(String appName, AllRegisteredUserBean rb, String serverLink, String contextLink, boolean isCreation)
	{
		Utils.emailList.clear();
		Utils.nameList.clear();
		Utils.emailList.add(rb.getEmail());
		Utils.nameList.add(rb.getFirst_name()+" "+rb.getLast_name());
	
		try
		{
			if(isCreation)
				new MailInfo(Utils.csr_email_address,Utils.emailList,Utils.nameList,Utils.smtp,"New Login Information for "+appName+" Research", updateRegisterUserReport(appName, rb, serverLink, contextLink, isCreation));
			else
				new MailInfo(Utils.csr_email_address,Utils.emailList,Utils.nameList,Utils.smtp,"Updated Login Information for "+appName+" Research", updateRegisterUserReport(appName, rb, serverLink, contextLink, isCreation));
		}
		catch(Exception e)
		{
			Utils.emailList.clear();
			Utils.nameList.clear();
			Utils.emailList.add(Utils.csr_email_address);
			Utils.nameList.add("CSR ADMIN");
			new MailInfo(Utils.csr_email_address,Utils.emailList,Utils.nameList,Utils.smtp,"Emailing failed for "+rb.getFirst_name()+" "+rb.getLast_name(), "");
		}
	}
	//for the notification of password reset
    public static final String updateRegisterUserReport(String appName, AllRegisteredUserBean rb, String serverLink, String contextLink, boolean isCreation)
    {
    		String style=
	    		"<style type=text/css>"+ 		    	
	    			"body,td{font-size:9pt; font-family:arial; word-break:break-all;}"+
			    	".header2_td{text-align:center; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}"+ 
			    	".header3_td{text-align:center; background-color:#F7F7EA; border-bottom:1px solid #fdfdfd;}"+
			    	".dv_line{background-color:#f8f8f8; height:1px; padding:0;} "+ 
			    	".center_td{text-align:center;}"+ 
			    "</style>";

	    	String body="Hello, "+rb.getFirst_name()+" "+rb.getLast_name()+"!<br/><br/>"+
	    	     "Your login password is "+(isCreation ? "assigned": "reset")+" as follows:<br/>"+
	    	     "<ul><li>LoginID: "+rb.getUser_id()+"</li>"+
	    	         "<li>Password: "+rb.getPassword()+"</li></ul>"+
	    	     "<ul><li><a href='"+serverLink+contextLink+"'>Go to "+appName+" Research to login</a></ul>"+
	    	     (isCreation ? "When you are at first login, the system will ask you to reset your password.<br/>" : "")+
	    	     "If you have a difficulty in logging into the system, please email to <a href='mailto:"+Utils.c_manager_email_id+"'>"+Utils.c_manager_email_id+"</a><br/><br/>"+
	    	     "Have a great day!<br/><br/>"+
	    	     appName+" Manager, "+Utils.c_manager_name+"<br/>"+
	    	     "<a href='"+Utils.my_home_page+"'>"+Utils.my_home_page.substring(8)+"</a>";
	    	     
    	return "<htm><head>"+style+"</head><body style='font-size:9pt; font-family:arial; word-break:break-all;'>"+body+"</body></html>";
	}
	public static void emailForgottenPassword(String appName, List<AllRegisteredUserBean> idPasswordList, String serverLink, String contextLink)
	{
		AllRegisteredUserBean rb=(AllRegisteredUserBean)idPasswordList.get(0);
		Utils.emailList.clear();
		Utils.nameList.clear();
		Utils.emailList.add(rb.getEmail());
		Utils.nameList.add(rb.getFirst_name()+" "+rb.getLast_name());
	
		try
		{
			new MailInfo(Utils.csr_email_address,Utils.emailList, Utils.nameList,Utils.smtp,"Your Login Information for "+appName+" Research", emailForgottenPasswordContents(appName, idPasswordList, serverLink, contextLink));
		}
		catch(Exception e)
		{
			Utils.emailList.clear();
			Utils.nameList.clear();
			Utils.emailList.add(Utils.csr_email_address);
			Utils.nameList.add("CSR ADMIN");
			new MailInfo(Utils.csr_email_address, Utils.emailList, Utils.nameList,Utils.smtp,"Emailing failed for "+rb.getFirst_name()+" "+rb.getLast_name(), "");
		}
	}
    public static final String emailForgottenPasswordContents(String appName, List<AllRegisteredUserBean> idPasswordList, String serverLink, String contextLink)
    {
	    	String style=
	    		"<style type=text/css>"+ 		    	
	    			"body,td{font-size:9pt; font-family:arial; word-break:break-all;}"+
			    	".header2_td{text-align:center; background-color:#E4E4C0; border-bottom:1px solid #fdfdfd;}"+ 
			    	".header3_td{text-align:center; background-color:#F7F7EA; border-bottom:1px solid #fdfdfd;}"+
			    	".dv_line{background-color:#f8f8f8; height:1px; padding:0;} "+ 
			    	".center_td{text-align:center;}"+ 
			    "</style>";
	    		
	    	String body="";
	    	AllRegisteredUserBean rb=null;
	    	for(int i=0;i<idPasswordList.size();i++)
	    	{
	    		rb=(AllRegisteredUserBean)idPasswordList.get(i);
	    		body+="Hello, "+rb.getFirst_name()+" "+rb.getLast_name()+"!<br/><br/>"+
	    	     		"Your login account has been created as follows:<br/>"+
	    	     		"<ul><li>LoginID: "+rb.getUser_id()+"</li>"+
	    	     		"<li>Password: "+rb.getPassword()+"</li></ul><br/>";
	    	}
	    	body+=
	    	     "<ul><li><a href='"+serverLink+contextLink+"'>Go to "+appName+" to login</a></ul>"+
	    	     "When you are at first login, the system will ask you to reset your password.<br/>"+
	    	     "If you have a difficulty in logging into the system, please email to <a href='mailto:"+Utils.c_manager_email_id+"'>"+Utils.c_manager_email_id+"</a><br/><br/>"+
	    	     "Have a great day!<br/><br/>"+
	    	     appName+" Manager, "+Utils.c_manager_name+"<br/>"+
	    	     "<a href='"+Utils.my_home_page+"'>"+Utils.my_home_page.substring(8)+"</a>";
	    	     
    	return "<htm><head>"+style+"</head><body style='font-size:9pt; font-family:arial; word-break:break-all;'>"+body+"</body></html>";
	}
}
