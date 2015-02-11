package com.sickkids.caliper.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.red5.core.MainApplication;
import org.red5.server.api.IAttributeStore;
import org.red5.server.api.so.ISharedObjectBase;
import org.red5.server.api.so.ISharedObjectListener;

import com.sickkids.caliper.common.Utils;

public class SharedObjectListener implements ISharedObjectListener 
{

	@Override public void onSharedObjectClear(ISharedObjectBase arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override public void onSharedObjectConnect(ISharedObjectBase arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override public void onSharedObjectDelete(ISharedObjectBase arg0, String arg1) 
	{
		// TODO Auto-generated method stub
	}

	@Override public void onSharedObjectDisconnect(ISharedObjectBase arg0) 
	{	// TODO Auto-generated method stub
		try
		{
			List<Integer> paramList=new ArrayList<Integer>();
			paramList.add(new Integer(MainApplication.APP_CONNECTION_COUNT));
			paramList.add(new Integer(MainApplication.ROOM_CONNECTION_COUNT));
			//a etalk client in application level receives the current application counts
			arg0.sendMessage("connectionCountMethod", paramList);
		}
		catch(Exception e)
		{
			Utils.logger.severe(e.toString());
		}
	}

	@Override public void onSharedObjectSend(ISharedObjectBase arg0, String arg1,List<?> arg2) 
	{
		// TODO Auto-generated method stub

	}

	@Override public void onSharedObjectUpdate(ISharedObjectBase arg0,IAttributeStore arg1) 
	{
		// TODO Auto-generated method stub

	}

	@Override public void onSharedObjectUpdate(ISharedObjectBase arg0, Map<String, Object> arg1) 
	{
		// TODO Auto-generated method stub

	}

	@Override public void onSharedObjectUpdate(ISharedObjectBase arg0, String arg1,Object arg2) 
	{
		// TODO Auto-generated method stub

	}

}
