package com.sickkids.caliper.service;



import it.sauronsoftware.cron4j.Scheduler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sickkids.caliper.common.Utils;

import com.sickkids.caliper.dao.AllLoginLevelBean;
import com.sickkids.caliper.dao.CarmSQLDao;
import com.sickkids.caliper.dao.OracleSQLDao;

import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.database.DriverUtilities;


public final class ContextListener implements ServletContextListener
{
	private ServletContext context = null;
	private ConnectionPool connectionPool=null;
		
	private CarmSQLDao csqlDao=null;
	private Scheduler carmScheduler=null;
	private Scheduler allScheduler=null;
	
	public void contextInitialized(ServletContextEvent event)
	{
		Utils.logger.info("CARM ContextListener started initializing of ContextListener.java...");
		context = event.getServletContext();
		
	    int vendor = DriverUtilities.POSTGRESQL;
	    String driver = DriverUtilities.getDriver(vendor);
	    String database_name=DriverUtilities.getDbName();
	    String user_name=DriverUtilities.getUserName();
	    String password=DriverUtilities.getPassword();
	    String host_name=DriverUtilities.getHostName();
	    
	    String url = DriverUtilities.makeURL(host_name, database_name, vendor);
	    try
	    {	//15: initial number of connection, 80: max number of connection
	    	connectionPool = new ConnectionPool(driver, url, user_name, password,15,80,true);
	    	
	    	context.setAttribute("connectionPool", connectionPool);

	    	//
	    	csqlDao=new CarmSQLDao(connectionPool);
	    	context.setAttribute("c_senior_seniority", Utils.c_senior_seniority);
	    	context.setAttribute("csqlDao", csqlDao);
	    	context.setAttribute("cloginLevelList", (List<AllLoginLevelBean>)csqlDao.getLoginLevelList());
	    	
	    	//This is to get additional DEs if being existed due to the addition from remote hospitals
	    	carmScheduler = new Scheduler();
	    	//The following will be executed once a day at 0:00AM
	    	carmScheduler.schedule("10 0 * * *", new CarmScheduledTask(csqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//"10 0 * * *" at 00:10am
	    	//carmScheduler.schedule("*/2 * * * *", new CarmScheduledTask(csqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//every 2 minute for testing.
	    	carmScheduler.start();
	    	
	    	allScheduler = new Scheduler();
	    	allScheduler.schedule("20 0 * * *", new TimeoutStatusChangeScheduledTask(csqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//"20 0 * * *" at 00:20am
	    	//allScheduler.schedule("*/2 * * * *", new TimeoutStatusChangeScheduledTask(csqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//every 2 minute for testing.
	    	allScheduler.start();
	    }
	    catch(SQLException e)
	    {	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();			    	
	    	connectionPool = null;    	 
	    	
	    	Utils.logger.severe("[Message]="+e.getMessage()+", [SQLState]="+e.getSQLState()+" inside SQLException of ContextListener.java.");
	    }
	    catch (Exception ex)
		{	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();
			connectionPool = null;	
			Utils.logger.severe("[Message]="+ex.getMessage()+" inside Exception of ContextListener.java.");
		}	
	}
	public void contextDestroyed(ServletContextEvent event)
	{		
		context = event.getServletContext();
		
		if(connectionPool!=null) connectionPool.closeAllConnections();
				
    	context.removeAttribute("connectionPool");
    	
    	context.removeAttribute("csqlDao");	
    	context.removeAttribute("cloginLevelList");
    	context.removeAttribute("c_senior_seniority");
    	    	
    	if(carmScheduler!=null && carmScheduler.isStarted()) carmScheduler.stop();
    	if(allScheduler!=null && allScheduler.isStarted()) allScheduler.stop();
    	Utils.logger.info("CARM ContextListener is destroyed of ContextListener.java");
	}

}

