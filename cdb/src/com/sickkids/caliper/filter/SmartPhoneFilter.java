package com.sickkids.caliper.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sickkids.caliper.common.UAgentInfo;
import com.sickkids.caliper.common.Utils;


public class SmartPhoneFilter implements Filter
{

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest hr=(HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		try
		{	//System.err.println("here--0");
			/*String userAgent = hr.getHeader("User-Agent");
		    String httpAccept = hr.getHeader("Accept");
		    
		    UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
		    
		    if (detector.detectAndroidPhone()||
		    	detector.detectBlackBerry()||
		    	detector.detectIphoneOrIpod()||
		    	detector.detectSmartphone()) 
		    {
		    	System.err.println("here--1");
		        res.sendRedirect("/caliperdatabase/smartphone");
		    } 
		    System.err.println("here--2");	*/		
			chain.doFilter(request, response);//going to the servlets
			/*System.err.println("here--3");
			*/
								
		}
		catch(Exception e)
		{
			Utils.logger.severe("ERROR: Exception in doFilter(..) of SmartPhoneFilter, msg="+e);
		}

	}

	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
