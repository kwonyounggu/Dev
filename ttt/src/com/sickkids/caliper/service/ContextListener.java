package com.sickkids.caliper.service;



import it.sauronsoftware.cron4j.Scheduler;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.AllLoginLevelBean;
import com.sickkids.caliper.dao.SQLDao;

import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.database.DriverUtilities;


public final class ContextListener implements ServletContextListener
{
	private ServletContext context = null;
	private ConnectionPool connectionPool=null;
		
	private SQLDao tttsqlDao=null;
	private Scheduler tttScheduler=null;
	
	private Map<String, HttpSession> allSessions=new HashMap<String, HttpSession>();
	
	public void contextInitialized(ServletContextEvent event)
	{
		Utils.logger.info("TTT ContextListener started initializing ...");
		context = event.getServletContext();
		
	    int vendor = DriverUtilities.POSTGRESQL;
	    String driver = DriverUtilities.getDriver(vendor);
	    String database_name=DriverUtilities.getDbName();
	    String user_name=DriverUtilities.getUserName();
	    String password=DriverUtilities.getPassword();
	    String host_name=DriverUtilities.getHostName();
	    System.out.println("INFO: password="+password);
	    String url = DriverUtilities.makeURL(host_name, database_name, vendor);
	    try
	    {	//5: initial number of connection, 50: max number of connection
	    	connectionPool = new ConnectionPool(driver, url, user_name, password,5,80,true);
	    	
	    	context.setAttribute("connectionPool", connectionPool);

	    	tttsqlDao=new SQLDao(connectionPool);
	    	context.setAttribute("tttsqlDao", tttsqlDao);
	    	context.setAttribute("tttloginLevelList", (List<AllLoginLevelBean>)tttsqlDao.getLoginLevelList());
	    	context.setAttribute("allSessions", allSessions);
	    	context.setAttribute("countryList", tttsqlDao.getCountryList());
	    	
	    	tttScheduler = new Scheduler();
	    	//The following will be executed once a day at 0:00AM
	    	//tttScheduler.schedule("0 0 * * *", new CarmScheduledTask(psqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//"0 10 * * *" at 00:10am
	    	//tttScheduler.schedule("*/2 * * * *", new CarmScheduledTask(psqlDao, (OracleSQLDao)context.getAttribute("oracleSqlDao")));//every 2 minute for testing.
	    	tttScheduler.start();
	    }
	    catch(SQLException e)
	    {	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();			    	
	    	connectionPool = null;    	 
	    	
	    	Utils.logger.severe("[Message]="+e.getMessage()+", [SQLState]="+e.getSQLState()+" inside SQLException.");
	    }
	    catch (Exception ex)
		{	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();
			connectionPool = null;	
			Utils.logger.severe("[Message]="+ex.getMessage()+" inside Exception.");
		}	
	}
	public void contextDestroyed(ServletContextEvent event)
	{		
		context = event.getServletContext();
		
		if(connectionPool!=null) connectionPool.closeAllConnections();
				
    	context.removeAttribute("connectionPool");
    	
    	context.removeAttribute("tttsqlDao");	
    	context.removeAttribute("tttloginLevelList");

    	allSessions.clear();
    	context.removeAttribute("allSessions");
    	context.removeAttribute("countryList");
    	
    	if(tttScheduler!=null && tttScheduler.isStarted()) tttScheduler.stop();
    	Utils.logger.info("TTT ContextListener is destroyed");
	}

}

