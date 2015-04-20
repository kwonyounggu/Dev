package com.sickkids.caliper.service;



import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.SQLDao;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.database.DriverUtilities;


public final class ContextListener implements ServletContextListener
{
	private ServletContext context = null;
	private ConnectionPool connectionPool=null;
	private SQLDao sqlDao=null;
	
	public void contextInitialized(ServletContextEvent event)
	{
		Utils.logger.info("Caliper Project -- ContextListener started initializing ... in ContextListener.java of cproject");
		context = event.getServletContext();
		
	    int vendor = DriverUtilities.MYSQL;
	    String driver = DriverUtilities.getDriver(vendor);
	    String database_name=DriverUtilities.getDbName();
	    String user_name=DriverUtilities.getUserName();
	    String password=DriverUtilities.getPassword();
	    String host_name=DriverUtilities.getHostName();

	    String url = DriverUtilities.makeURL(host_name, database_name, vendor);

	    try
	    {	//5: initial number of connection, 50: max number of connection
	    	connectionPool = new ConnectionPool(driver, url, user_name, password,5,50,true);
	    	context.setAttribute("connectionPool", connectionPool);
	    	sqlDao=new SQLDao(connectionPool);
	    	context.setAttribute("sqlDao", sqlDao);
	    }
	    catch(SQLException e)
	    {	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();			    	
	    	connectionPool = null;    	 
	    	
	    	Utils.logger.severe(e.getMessage());
	    }
	    catch (Exception ex)
		{	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();
			connectionPool = null;	
			Utils.logger.severe(ex.getMessage());
		}	
	    Utils.logger.info("Caliper Project -- ContextListener ended initializing ... in ContextListener.java of cproject");
	}
	public void contextDestroyed(ServletContextEvent event)
	{		
		context = event.getServletContext();
		
		if(connectionPool!=null) connectionPool.closeAllConnections();
				
    	context.removeAttribute("connectionPool");
    	context.removeAttribute("sqlDao");	
	
    	//The following code is to prevent memory leaking from my sql connections
    	//http://stackoverflow.com/questions/11872316/tomcat-guice-jdbc-memory-leak
    	try 
    	{
    	    Class<?> cls=Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread");
    	    Method   mth=(cls==null ? null : cls.getMethod("shutdown"));
    	    if(mth!=null) 
    	    {
    	    	Utils.logger.info("MySQL connection cleanup thread shutdown in ContextListener.java of cproject");
    	        mth.invoke(null);
    	        Utils.logger.info("MySQL connection cleanup thread shutdown successful in ContextListener.java of cproject");
    	    }
    	}
    	catch (Throwable thr) 
    	{
    		Utils.logger.info("[ER] Failed to shutdown SQL connection cleanup thread: " + thr.getMessage()+" in ContextListener.java of cproject");
    	    thr.printStackTrace();
    	}
    	
    	Utils.logger.info("Caliper Project -- ContextListener is destroyed, in ContextListener.java of cproject");
	}

}

