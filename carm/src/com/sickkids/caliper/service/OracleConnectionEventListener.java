package com.sickkids.caliper.service;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;

import com.sickkids.caliper.common.Utils;

public class OracleConnectionEventListener implements ConnectionEventListener
{

	public void connectionClosed(ConnectionEvent event)
	{
		// TODO Auto-generated method stub
		Utils.logger.info("connectionClosed(..) is called in OracleConnectionEventListener.java");

	}

	public void connectionErrorOccurred(ConnectionEvent event)
	{
		// TODO Auto-generated method stub
		Utils.logger.info("connectionErrorOccurred(..) is called in OracleConnectionEventListener.java");
	}

}
