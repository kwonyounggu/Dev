package com.sickkids.caliper.database;

import java.util.ResourceBundle;


public class DriverUtilities
{
	public static final int ORACLE = 1;
	public static final int SYBASE = 2;
	public static final int MYSQL = 3;
	public static final int AS400 = 4;
	public static final int MSSQL = 5;
	public static final int POSTGRESQL = 6;
	public static final int UNKNOWN = -1;

	public static String database_name;
	public static String user_name;
	public static String password;
	public static String host_name;
	
	public static String oracle_host_name;
	public static String oracle_service_name;
	public static String oracle_database_name;
	public static String oracle_user_name;
	public static String oracle_password;
	

    static 
    {
        try 
        {
            ResourceBundle bundle = ResourceBundle.getBundle("database");
            database_name=bundle.getString("database_name");
            user_name=bundle.getString("user_name");  
            password=bundle.getString("password");
            host_name=bundle.getString("host_name");
            
            oracle_host_name=bundle.getString("oracle_host_name");
            oracle_service_name=bundle.getString("oracle_service_name");
            oracle_database_name=bundle.getString("oracle_database_name");
            oracle_user_name=bundle.getString("oracle_user_name");
            oracle_password=bundle.getString("oracle_password");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	
	/**
	 * Build a URL in the format needed by the Oracle and Sybase drivers I am
	 * using.
	 */
    public static String getDbName()
    {
    	return database_name;
    }
    public static String getUserName()
    {
    	return user_name;
    }
    public static String getPassword()
    {
    	return password;
    }
    public static String getHostName()
    {
    	return host_name;
    }
    
	public static String getOracle_host_name()
	{
		return oracle_host_name;
	}
	public static void setOracle_host_name(String oracle_host_name)
	{
		DriverUtilities.oracle_host_name = oracle_host_name;
	}
	public static String getOracle_service_name()
	{
		return oracle_service_name;
	}
	public static void setOracle_service_name(String oracle_service_name)
	{
		DriverUtilities.oracle_service_name = oracle_service_name;
	}
	public static String getOracle_database_name()
	{
		return oracle_database_name;
	}
	public static void setOracle_database_name(String oracle_database_name)
	{
		DriverUtilities.oracle_database_name = oracle_database_name;
	}
	public static String getOracle_user_name()
	{
		return oracle_user_name;
	}
	public static void setOracle_user_name(String oracle_user_name)
	{
		DriverUtilities.oracle_user_name = oracle_user_name;
	}
	public static String getOracle_password()
	{
		return oracle_password;
	}
	public static void setOracle_password(String oracle_password)
	{
		DriverUtilities.oracle_password = oracle_password;
	}
	public static String makeURL(String host, String dbName, int vendor)
	{
		if (vendor == ORACLE)
		{
			//return ("jdbc:oracle:thin:@" + host + ":1521:" + dbName);
			//jdbc:oracle:thin:@//<host>:<port>/<service_name>
			return ("jdbc:oracle:thin:@" + host + ":1521"+ "/" + dbName);
		}
		else if (vendor == SYBASE)
		{
			return ("jdbc:sybase:Tds:" + host + ":1521" + "?SERVICENAME=" + dbName);
		}
		else if (vendor == MYSQL)
		{
			//return ("jdbc:mysql://" + host + ":3306" + "/" + dbName);
			return ("jdbc:mysql://" + host + ":3306" + "/" + dbName+"?autoReconnect=true");
		}
		else if (vendor == MSSQL)
		{
			return (host);
		}
		else if (vendor==POSTGRESQL)
		{
			return ("jdbc:postgresql://" + host + ":5432" + "/" + dbName);
		}
		else
		{
			return (null);
		}
	}

	/** Get the fully qualified name of a driver. */

	public static String getDriver(int vendor)
	{
		if (vendor == ORACLE)
		{
			return ("oracle.jdbc.driver.OracleDriver");
		}
		else if (vendor == SYBASE)
		{
			return ("com.sybase.jdbc.SybDriver");
		}
		else if (vendor == MYSQL)
		{
			return ("com.mysql.jdbc.Driver");
		}
		else if (vendor == MSSQL)
		{
			return ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		else if (vendor == AS400)
		{
			return ("com.ibm.as400.access.AS400JDBCDriver");
		}
		else if (vendor==POSTGRESQL)
		{
			return ("org.postgresql.Driver");
		}
		else
		{
			return (null);
		}
	}

	/** Map name to int value. */

	public static int getVendor(String vendorName)
	{
		if (vendorName.equalsIgnoreCase("oracle"))
		{
			return (ORACLE);
		}
		else if (vendorName.equalsIgnoreCase("sybase"))
		{
			return (SYBASE);
		}
		else if (vendorName.equalsIgnoreCase("mysql"))
		{
			return (MYSQL);
		}
		else if (vendorName.equalsIgnoreCase("postgresql"))
		{
			return (POSTGRESQL);
		}
		else
		{
			return (UNKNOWN);
		}
	}
}
