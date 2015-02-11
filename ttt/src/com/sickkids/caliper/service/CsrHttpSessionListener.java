package com.sickkids.caliper.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.SQLDao;


public class CsrHttpSessionListener implements HttpSessionListener
{

	@Override public void sessionCreated(HttpSessionEvent arg0)
	{
	}

	@Override public void sessionDestroyed(HttpSessionEvent arg0)
	{	
		Utils.logger.info("sessionDestroyed() is called in CsrHttpSessionListener.java.");
		try
		{	
			//Enumeration e=arg0.getSession().getAttributeNames();
			//while(e.hasMoreElements()) System.out.println(e.nextElement());
			//here arg0.getSession() returns for a session about to be destroyed
			synchronized (this)
			{
				Map<String, HttpSession> allSessions=(Map<String, HttpSession>)arg0.getSession().getServletContext().getAttribute("allSessions");
				if(allSessions.containsKey(arg0.getSession().getId())) allSessions.remove(arg0.getSession().getId());
				
				SQLDao tttsqlDao=(SQLDao)arg0.getSession().getServletContext().getAttribute("tttsqlDao");
				if(tttsqlDao!=null)
				{
					AllLoginHistoryBean ab=(AllLoginHistoryBean)arg0.getSession().getAttribute("all_login_hist_bean");
					
					if(ab!=null)
					{
						tttsqlDao.updateInsertGenericSqlCmd("update all_login_hist set logout_time=now(), remarks='session-timeout' where login_hist_index="+ab.getLogin_hist_index());
						Utils.logger.info("User: "+ab.getUser_id()+" is session-out, sessionDestroyed(..) in CsrHttpSessionListener.java.");
					}
				}
			}
		}
		catch(Exception e)//SQLException e changed to Exception e on Feb-25-2014
		{
			Utils.logger.severe("SQLException, "+e.getMessage()+" sessionDestroyed() in CsrHttpSessionListener.java.");
		}
	}
}
