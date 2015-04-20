package com.sickkids.caliper.service;

import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.PooledConnection;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AllLoginHistoryBean;
import com.sickkids.caliper.dao.CarmSQLDao;
//import com.sickkids.caliper.dao.SPIRELoginHistoryBean;
//import com.sickkids.caliper.dao.SPIRESQLDao;

public class CsrHttpSessionListener implements HttpSessionListener
{

	public void sessionCreated(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void sessionDestroyed(HttpSessionEvent arg0)
	{	
		Utils.logger.info("sessionDestroyed() is called in CsrHttpSessionListener.java.");
		try
		{	
			//Enumeration e=arg0.getSession().getAttributeNames();
			//while(e.hasMoreElements()) System.out.println(e.nextElement());
			//here arg0.getSession() returns for a session about to be destroyed
			
			CarmSQLDao csqlDao=(CarmSQLDao)arg0.getSession().getServletContext().getAttribute("csqlDao");
			if(csqlDao!=null)
			{
				AllLoginHistoryBean ab=(AllLoginHistoryBean)arg0.getSession().getAttribute("all_login_hist_bean");
				
				if(ab!=null)
				{
					csqlDao.updateInsertGenericSqlCmd("update all_login_hist set logout_time=now(), remarks='session-timeout' where login_hist_index="+ab.getLogin_hist_index());
					Utils.logger.info("User: "+ab.getUser_id()+" is session-out, sessionDestroyed(..) in CsrHttpSessionListener.java.");
				}
			}
		}
		catch(Exception e)//SQLException e changed to Exception e on Feb-25-2014
		{
			Utils.logger.severe("SQLException, "+e.getMessage()+" sessionDestroyed() in CsrHttpSessionListener.java.");
		}
	}

}
