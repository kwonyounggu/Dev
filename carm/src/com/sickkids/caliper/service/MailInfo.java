/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sickkids.caliper.service;
import java.security.Security;
import java.util.*;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;

import com.sickkids.caliper.common.Utils;


public class MailInfo extends Authenticator implements Runnable
{
	  protected static Logger log = Utils.logger;
      private String fromAddr;
      private List<String> toAddr;
      private List<String> name;
      private String serverAddr;
      private String subject;
      private String message;
      protected PasswordAuthentication authentication;
         
      public MailInfo(String from, List<String> to, List<String> name, String server, String subject, String message) 
      {
    	  	this.fromAddr = from;
	        this.toAddr = to;
	        this.name = name;
	        this.serverAddr = server;
	        this.subject = subject;
	        this.message = message;
	        	        
	        Thread t = new Thread(this);
	  		t.start();
      }
      public void sendMail() 
      {
        try 
        {
        	  //authentication = new PasswordAuthentication("misoon.lim@1mm.primus.ca", "misoon4m");
        	  authentication = new PasswordAuthentication(Utils.smtp_email_id, Utils.smtp_email_password);
		    
        	
        	  Properties prop = new Properties();
	          prop.put("mail.smtp.host", serverAddr);
	          Session session = Session.getDefaultInstance(prop, null);
	          session.setDebug(false);
	          // Create a message
	          MimeMessage mimeMsg = new MimeMessage(session);
	          // Set the from and to address
	          Address addressFrom = new InternetAddress(fromAddr,"CSR-ADMIN","UTF-8");
	          mimeMsg.setFrom(addressFrom);
	          Address[] to = new InternetAddress[toAddr.size()];
	          for(int i = 0; i < toAddr.size(); i++) to[i] = new InternetAddress((String) toAddr.get(i),name.get(i)+"","UTF-8");
	          mimeMsg.addRecipients(Message.RecipientType.TO,to);//OK
 	          
	          //The following two lines for information to csr administrator every time with every kind of email: added April 24 2013
	          Address[] bcc = {new InternetAddress("webmonster.ca@gmail.com", "CSR-ADMIN","UTF-8")};
	          mimeMsg.addRecipients(Message.RecipientType.BCC, bcc);
	          
	          mimeMsg.setSubject(subject,"UTF-8");
	          mimeMsg.setContent(message, "text/html;charset=UTF-8");
	          
	          Transport.send(mimeMsg);
	          log.info("EMail from "+fromAddr+" to "+toAddr+" was sent successfully.");
        } 
        catch (Exception e) 
        {
        	  log.severe("Unable to send email in MailInfo.java");
        	  throw new RuntimeException(e);
        }
      }

      public void googleSSLSMTP()
      {
    	  try
    	  {
	    	  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
	    	   
	    	  Properties props = new Properties();  
	    	  props.setProperty("mail.transport.protocol", "smtp");  
	    	     
	    	  props.setProperty("mail.host", Utils.smtp);  
	    	  props.put("mail.smtp.auth", "true");  
	    	  props.put("mail.smtp.port", "465");  
	    	  props.put("mail.debug", "false");  
	    	  props.put("mail.smtp.socketFactory.port", "465");  
	    	  props.put("mail.smtp.socketFactory.class",  "javax.net.ssl.SSLSocketFactory");  
	    	  props.put("mail.smtp.socketFactory.fallback", "false");
	    	  props.put("mail.smtp.quitwait", "false");
	    	    
	    	  Session session = Session.getDefaultInstance(props,  
		    	  new javax.mail.Authenticator() 
		    	  {  
		    		  protected PasswordAuthentication getPasswordAuthentication() 
			    	  {  
			    		  return new PasswordAuthentication(Utils.smtp_email_id, Utils.smtp_email_password);  //this will be return address for delivery failure
			    	  }  
		    	  }
		    	  );  
	    	     
	    	  session.setDebug(Utils.smtp_debug.equalsIgnoreCase("true"));  
	    	     	    	    
	    	  Transport transport = session.getTransport();  
	    	  
	    	  Address addressFrom = new InternetAddress(fromAddr,"CSR-ADMIN","UTF-8");
	    	     
	    	  MimeMessage mimeMsg = new MimeMessage(session);    
	    	  mimeMsg.setFrom(addressFrom);
	    	  
	    	  Address[] to = new InternetAddress[toAddr.size()];
	          for(int i = 0; i < toAddr.size(); i++) to[i] = new InternetAddress((String) toAddr.get(i),name.get(i)+"","UTF-8");
	          mimeMsg.addRecipients(Message.RecipientType.TO,to);//OK  
	          
	          //The following two lines for information to csr administrator every time with every kind of email: added April 18 2013
	          Address[] bcc = {new InternetAddress("webmonster.ca@gmail.com", "CSR-ADMIN","UTF-8")};
	          mimeMsg.addRecipients(Message.RecipientType.BCC, bcc);
	          
	          mimeMsg.setSubject(subject,"UTF-8");
	          mimeMsg.setContent(message, "text/html;charset=UTF-8");
	    	  
	    	  transport.connect();  
	    	  Transport.send(mimeMsg);  
	    	  transport.close();  
	    	  log.info("EMail from "+fromAddr+" to "+toAddr+" was sent successfully.");

 	     }
 	     catch (Exception e) 
 	     {
 	    	 log.severe("Unable to email to "+toAddr+" in MailInfo.java");
        	 throw new RuntimeException(e);
 	     }
      }
      public void run()
	  {
		  try
		  {	  
			  if(Utils.smtp.equalsIgnoreCase("smtp.gmail.com")) googleSSLSMTP();
			  else sendMail();
		  }
		  catch(Exception e)
		  {
			  log.severe("emailing to "+toAddr+" has been failed for subject:"+subject+".\nCause:"+e.getMessage());
		  }
		  
	  }
      public PasswordAuthentication getPasswordAuthentication()
	  {		
    	  return authentication;
	  }
}
