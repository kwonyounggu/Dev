package org.red5.core;

import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;

public class ShutdownHook extends Thread
{
	MainApplication app=null;
	protected static Logger log=Utils.logger;
	public ShutdownHook(MainApplication app)
	{
		this.app = app;
	}

	public void run()
	{
		try
		{
			if(app==null)
				System.err.println("-------------------------ShutdownHook.run() is called with app==null");
			else
			{
				MainApplication.APP_CONNECTION_COUNT--;//just for testing
				//clean up all login
				//clean up all connection pool manager
				//clean up all atributes
				//Don't use log object, System object because there are already nulls.
			}		
		}
		catch(Exception e)
		{
			System.err.println(e.toString()+"in run() of ShutdownHook.java");
		}
	}
}
