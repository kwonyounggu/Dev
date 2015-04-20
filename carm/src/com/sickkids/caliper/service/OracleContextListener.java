package com.sickkids.caliper.service;



import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.dao.FormTypeBean;

import com.sickkids.caliper.dao.OracleSQLDao;

import com.sickkids.caliper.dao.SubmissionTypeBean;
import com.sickkids.caliper.database.ConnectionPool;
import com.sickkids.caliper.database.DriverUtilities;


public final class OracleContextListener implements ServletContextListener
{
	private ServletContext context = null;
	private OracleConnectionPoolDataSource oracleConnectionPoolDataSource = null;

	public void contextInitialized(ServletContextEvent event)
	{
		Utils.logger.info("CARM OracleContextListener started initializing in OracleContextListener.java");
		context = event.getServletContext();
			    
	    int vendorOracle = DriverUtilities.ORACLE;
	    //String driverOracle = DriverUtilities.getDriver(vendorOracle);
	    String urlOracle=DriverUtilities.makeURL(DriverUtilities.getOracle_host_name(), DriverUtilities.getOracle_service_name(), vendorOracle);
	    try
	    {	
	    	oracleConnectionPoolDataSource=new OracleConnectionPoolDataSource();
	    	oracleConnectionPoolDataSource.setURL(urlOracle);
	    	oracleConnectionPoolDataSource.setUser(DriverUtilities.getOracle_user_name());
	    	oracleConnectionPoolDataSource.setPassword(DriverUtilities.getOracle_password());
	    	
	    	java.util.Properties prop = new java.util.Properties();
	    	prop.setProperty("MinLimit", "5");       //min pool size
	    	prop.setProperty("InitialLimit", "5");
	    	prop.setProperty("MaxLimit", "20");    //max pool size
	    	
	    	oracleConnectionPoolDataSource.setConnectionProperties(prop);//Not sure if this property is working.
    	
	    	context.setAttribute("oracleConnectionPoolDataSource", oracleConnectionPoolDataSource);
	    	context.setAttribute("oracleSqlDao", new OracleSQLDao(oracleConnectionPoolDataSource));	
	    }
	    catch(SQLException e)
	    {	
	    	Utils.logger.severe(" (for Oracle) "+e.getMessage()+" in OracleContextListener.java");
	    }
	    catch (Exception ex)
		{	
	    	Utils.logger.severe(" (for Oracle) "+ex.getMessage()+" in OracleContextListener.java");
		}
	    Utils.logger.info("CARM OracleContextListener ended initializing.");
	}
	public void contextDestroyed(ServletContextEvent event)
	{		
		context = event.getServletContext();
		
    	context.removeAttribute("oracleConnectionPoolDataSource");
    	context.removeAttribute("oracleSqlDao");
    	
    	Utils.logger.info("CARM OracleContextListener is destroyed");
	}
}

