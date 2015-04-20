package com.sickkids.caliper.service;



import java.sql.SQLException;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import com.sickkids.caliper.common.Utils;


import com.sickkids.caliper.dao.OracleSQLDao;

import com.sickkids.caliper.database.DriverUtilities;


public final class UPC_OracleContextListener implements ServletContextListener
{
	private ServletContext context = null;

	private PoolDataSource upcPds=null;

	public void contextInitialized(ServletContextEvent event)
	{
		Utils.logger.info("CARM OracleContextListener started initializing in OracleContextListener.java");
		context = event.getServletContext();
			    
	    int vendorOracle = DriverUtilities.ORACLE;
	    //String driverOracle = DriverUtilities.getDriver(vendorOracle);
	    String urlOracle=DriverUtilities.makeURL(DriverUtilities.getOracle_host_name(), DriverUtilities.getOracle_service_name(), vendorOracle);
	    try
	    {	
	    	upcPds=PoolDataSourceFactory.getPoolDataSource();
	    	upcPds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
	    	upcPds.setURL(urlOracle);
	    	upcPds.setUser(DriverUtilities.getOracle_user_name());
	    	upcPds.setPassword(DriverUtilities.getOracle_password());
	    	
	    	upcPds.setInitialPoolSize(5);
	    	upcPds.setMinPoolSize(5);
	    	upcPds.setMaxPoolSize(20);
	    	
	    	context.setAttribute("oracleSqlDao", new OracleSQLDao(upcPds));	
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
		
    	context.removeAttribute("oracleSqlDao");
    	
    	Utils.logger.info("CARM OracleContextListener is destroyed");
	}
}

