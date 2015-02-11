package com.sickkids.caliper.manager;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;

public class ConnectionPoolManager
{
	protected static Logger log = Utils.logger;
	/*
	protected static Logger log = GenUtilities.logger;
	public static ConnectionPool connectionPool=null;

	public static void initialize()	
	{	
		log.info("ConnectionPoolManager is being initialized");
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
	    }
	    catch(SQLException e)
	    {	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();			    	
	    	connectionPool = null;    	 
	    	log.info(e.getMessage());
	    }
	    catch (Exception ex)
		{	
	    	if(connectionPool!=null) connectionPool.closeAllConnections();
			connectionPool = null;				
	    	log.info(ex.getMessage());
		}
	}
	public static void cleanup()
	{
		log.info("cleanup() is called");
		if(connectionPool!=null) connectionPool.closeAllConnections();
	}
	*/
	public static void cleanup()
	{
		//log.info("cleanup() is called");
	}
}
