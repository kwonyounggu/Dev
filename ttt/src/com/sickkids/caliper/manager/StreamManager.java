package com.sickkids.caliper.manager;

import java.util.logging.Logger;

import org.red5.server.api.IConnection;
import org.red5.server.stream.ClientBroadcastStream;

import org.red5.core.MainApplication;

import com.sickkids.caliper.common.Utils;

public class StreamManager
{
	//private static final Log log = LogFactory.getLog(StreamManager.class);
	protected static Logger log = Utils.logger;
	// Application components
	private MainApplication app;
	
	/**
	 * Start recording the publishing stream for the specified
	 * <code>IConnection</code>.
	 * 
	 * @param conn
	 */
	public void recordShow(IConnection conn) 
	{
		log.info("Recording show for: " + conn.getScope().getContextPath());
		log.info("Recording show for: conn.getPath()=" + conn.getPath());
		log.info("Recording show for: conn.getHost()=" + conn.getHost());
		log.info("Recording show for: conn.getRemotePort()=" + conn.getRemotePort());
		//log.info("Recording show for: conn.getScope().getName()=" + conn.getScope().getName());
		String streamName = String.valueOf(System.currentTimeMillis());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream=(ClientBroadcastStream)app.getBroadcastStream(conn.getScope(), "hostStream");
		try 
		{
			// Save the stream to disk.
			stream.saveAs(streamName, false);
		} 
		catch (Exception e) 
		{
			System.err.println("Error while saving stream: " + streamName+", "+e);
		}
	}
	
	/**
	 * Stops recording the publishing stream for the specified
	 * <code>IConnection</code>.
	 * 
	 * @param conn
	 */
	public void stopRecordingShow(IConnection conn) 
	{
		System.out.println("Stop recording show for: " + conn.getScope().getContextPath());
		log.info("Stop show for: conn.getPath()=" + conn.getPath());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) app.getBroadcastStream(conn.getScope(), "hostStream");
		// Stop recording.
		stream.stopRecording();
	}
	/*
	public void notifyConnectionCount()
	{
		ArrayList<Integer> paramList=new ArrayList<Integer>();
		paramList.add(new Integer(Application.APP_CONNECTION_COUNT));
		paramList.add(new Integer(Application.ROOM_CONNECTION_COUNT));
		app.getSharedObject(app.appScope,"so_app").sendMessage("connectionCountMethod", paramList);
	}*/
	
	/* ----- Spring injected dependencies ----- */

	public void setApplication(MainApplication app) 
	{	log.info("setApplication() is called in StreamManager.java");
		this.app = app;
	}
}
