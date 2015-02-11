package org.red5.core;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.util.logging.Logger;



import org.red5.common.Utils;
import org.red5.dao.UserBean;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.adapter.StatefulScopeWrappingAdapter;
import org.red5.server.api.IClient;

import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;

import org.red5.server.api.so.ISharedObject; //load scheduling libraries...

import org.red5.server.api.scheduling.ISchedulingService;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;

import org.springframework.core.io.Resource;


import com.sickkids.caliper.manager.ConnectionPoolManager;
import com.sickkids.caliper.manager.SharedObjectListener;


public class MainApplication extends ApplicationAdapter  implements IPendingServiceCallback
{

	//protected static Log log = LogFactory.getLog(MainApplication.class.getName());
	protected static Logger log=Utils.logger;
	private IScope currentRoomScope=null;
	public IScope appScope=null;

	private HashMap<Integer, String> scopes;
	private Set<IScope> roomScopeSet=new HashSet<IScope>();
	private HashMap<String, UserBean> users=new HashMap<String, UserBean>();

	private int scope_id = 0;
	
	public static String APP_SCOPE_NAME="tttServer";
	public static int APP_CONNECTION_COUNT=0;
	public static int ROOM_CONNECTION_COUNT=0;
	
	private String jobId=null;
	
	private int downkbps=1024;
	private int upkbps=1024;

	/***************************************************************************************************************
	 * Application Level
	 ***************************************************************************************************************/
	@Override public boolean appStart(IScope app)
	{	
		appScope = app;
		APP_SCOPE_NAME=app.getName();
		log.info("------ appStart("+app.getName()+") is called in the level of "+APP_SCOPE_NAME+" ------");
		//The following statement is to call the ShutdownHook object when this object is being shutdown.
		Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));
		log.info("createSharedObject("+appScope.getName()+", so_app_level"+")="+createSharedObject(appScope,"so_app_level",false));
		ISharedObject so_app_level=(ISharedObject)getSharedObject(appScope,"so_app_level");
		if(so_app_level!=null) so_app_level.addSharedObjectListener(new SharedObjectListener());
		return super.appStart(app);
	}

	@Override public void appStop(IScope scope)
	{
		log.info("------ appStop("+scope.getName()+") is called ------");
		try
		{
			//log.info("-------------------------appStop is called in the level of "+APP_SCOPE_NAME+"-----------------------------------");
			//clean up all login
			//clean up all connection pool manager
			//clean up all atributes
			super.appStop(scope);
		}
		catch(Exception e)
		{
			//log.severe(e.toString()+"in appStop() of MainApplication.java");
		}
	}
	@Override public boolean appConnect(IConnection conn, Object[] params)
	{
		log.info("-------------------------appConnect("+conn.getClient().getId()+") is called-----------------------------------");
		try
		{
			if(conn.getPath().equals(APP_SCOPE_NAME)) ++APP_CONNECTION_COUNT;//this should be decremented later, Oct 28, 2013
			else ++ROOM_CONNECTION_COUNT;//this should be decremented later, Oct 28, 2013
			log.info("APP_CONNECTION_COUNT: "+APP_CONNECTION_COUNT+" in appConnect(clientID:"+conn.getClient().getId()+")");
			log.info("ROOM_CONNECTION_COUNT: "+ROOM_CONNECTION_COUNT+" in appConnect(clientID:"+conn.getClient().getId()+")");
		}
		catch(Exception e)
		{
			log.severe(e.toString()+" in appConnect(clientID:"+conn.getClient().getId()+")");
		}
		return super.appConnect(conn, params);
	}
	@Override public void appDisconnect(IConnection conn)
	{	log.info("-------------------------appDisconnect("+conn.getClient().getId()+") is called-----------------------------------");
		// destroy the transient shared objects...
		super.appDisconnect(conn);
	}
	/***************************************************************************************************************
	 * Room Level
	 ***************************************************************************************************************/
	//whenever a new room is created at the 1st time, this will be called.
	@Override public boolean roomStart(IScope room)
	{
		log.info("-------------------------roomStart(roomName: "+room.getName()+") is called-----------------------------------");
		try
		{
			this.currentRoomScope=room;
			roomScopeSet.add(room);
			
			createSharedObject(room, room.getName(), false);
			ISharedObject roomSo = getSharedObject(room, room.getName(),false);
			roomSo.registerServiceHandler(new RoomSharedObjectHandler());
		}
		catch(Exception e)
		{
			log.severe(e.toString()+" in roomStart(roomName: "+room.getName()+")");
		}
		return super.roomStart(room);
	}
	//whenever a new room is destroyed at the 1st time without no connections being remained, this will be called.
	@Override public void roomStop(IScope room)
	{
		log.info("-------------------------roomStop(roomName: "+room.getName()+") is called-----------------------------------");
		try
		{
			roomScopeSet.remove(room); 
			((StatefulScopeWrappingAdapter) room).removeAttributes();
		}
		catch(Exception e)
		{
			log.severe(e.toString()+" with room.getName()="+room.getName()+" in roomStop of MainApplication.java");
		}
		finally
		{
			super.roomStop(room);
		}
	}
	@Override public boolean roomConnect(IConnection conn, Object[] params)
	{
		boolean bAccepted=true;
		log.info("-------------------------roomConnect(clientID: "+conn.getClient().getId()+") is called from "+conn.getRemoteAddress()+" ----------------------------------");
		try
		{
			UserBean ub=new UserBean();
			
			ub.setRoom_id(params[0].toString());
			ub.setRoom_name(params[1].toString());
			ub.setHospital_id(params[2].toString());
			ub.setHospital_name(params[3].toString());
			ub.setUser_id(params[4].toString());
			ub.setEmail(params[5].toString());
			ub.setIs_owner((Boolean)params[6]);
			ub.setLogin_level(Integer.parseInt(params[7].toString()));
			ub.setIp_address(conn.getRemoteAddress());
			
			log.info("param[0]="+ub.getRoom_id());//room_id
			log.info("param[1]="+ub.getRoom_name());//room_name
			log.info("param[2]="+ub.getHospital_id());//hospital_id
			log.info("param[3]="+ub.getHospital_name());//hospital_name
			log.info("param[4]="+ub.getUser_id());//user_id
			log.info("param[5]="+ub.getEmail());//email
			log.info("param[6]="+ub.isIs_owner());//is_owner
			log.info("param[7]="+ub.getLogin_level());//login_level
			log.info("ub.getIp_address="+ub.getIp_address());//login_level
			log.info("param[8]="+params[8].toString());//model.bw.kbitDown
			log.info("param[9]="+params[9].toString());//model.bw.kbitUp
			log.info("param[10]="+params[10].toString());//model.bw.latencyDown
			log.info("param[11]="+params[11].toString());//model.bw.latencyUp
			
			//rejectUser(ub, conn.getScope().getClients());
			Set<IClient> clients=conn.getScope().getClients();
			log.info("The number of clients so far is "+clients.size()+", Utils.MAX_CLIENTS="+Utils.MAX_CLIENTS);
			if(clients.size()>=Utils.MAX_CLIENTS)
			{
				log.info("Now the room ("+ub.getRoom_id()+") is full with the maximum, "+Utils.MAX_CLIENTS+" connections.");
				this.rejectClient("Now the room ("+ub.getRoom_id()+") is full with the maximum, "+Utils.MAX_CLIENTS+" connections.");			
			}
			else
			{
				Iterator<IClient> client=clients.iterator();
				while(client.hasNext())
				{
					IClient c=client.next();
					UserBean ubAttr=(UserBean)c.getAttribute("userBean");
					//anyone connected from the same room more than one, then it is not allowed.
					//one from the center hospital and the other from the local hospital in each room session.
					if(ubAttr.isIs_owner() && ub.isIs_owner())
					{	
						log.info("There exists already one controller so that you can't be another.");
						rejectClient("There exists already one controller so that you can't be another.");
						break;
					}
					else if(ubAttr.getIp_address().equals(ub.getIp_address()))
					{
						log.info("Only one connection is allowed with the ip, '"+ub.getIp_address()+"'.");
						rejectClient("Only one connection is allowed with the ip, '"+ub.getIp_address()+"'.");						
						break;
					}
				}
			}
			
			conn.getClient().setAttribute("userBean", ub);
		}
		catch(Exception e)
		{
			bAccepted=false;
			log.severe(e.toString()+" roomConnect(clientID: "+conn.getClient().getId()+") of MainApplication.java");
		}
			
		return bAccepted;
	}
	@Override public void roomDisconnect(IConnection conn)
	{
		log.info("-------------------------roomDisconnect("+conn.getClient().getId()+") is called-----------------------------------");
		try
		{
			super.roomDisconnect(conn);
		}
		catch(Exception e)
		{
			log.severe(e.toString()+" with conn.getClient().getId()="+conn.getClient().getId()+" in roomDisconnect() of MainApplication.java");
		}
	}
	
	
	public void resultReceived(IPendingServiceCall arg0)
	{
		// TODO Auto-generated method stub
		
	}
	private void incrementPersistentSharedObject()
	{log.info("-------------------------incrementPersistentSharedObject is called-----------------------------------");
		Integer counter = 0;

		// createSharedObject internally checks to see if SO is already created.
		// If it is not...
		// ...it is created, else nothing happens...
		this.createSharedObject(this.appScope, "counterSO", true);
		// set to true to indicate that it is a persistent Shared Object...
		ISharedObject counterSO = this
				.getSharedObject(scope, "counterSO", true);
		counter = (Integer) counterSO.getAttribute("counterValue", 0) + 1;
		counterSO.setAttribute("counterValue", counter);
		System.err.println("The new CONNECTION counter value is: " + counter);
	}
	public class RoomSharedObjectHandler 
	{   
		/*
		private Queue msgQueue = new Queue();
		private int count=0;
		final int MAX=30;
		
		private DrawItemBean item=null;
		private DrawItemsBean itemsList=null;
		*/
		public RoomSharedObjectHandler() 
		{
			
		}
		//Shared Object for chatting message
		public void newMessage(String msg)
	    {
			/*
			if(++count>MAX)
			{				
				msgQueue.dequeue();
				count=MAX;
				
			}
			msgQueue.enqueue(msg);
			log.info(Red5.getConnectionLocal().getScope().getName()+":"+msg);
			IScope roomScope=Red5.getConnectionLocal().getScope();
			getSharedObject(roomScope,roomScope.getName()).setAttribute("previousMsg", msgQueue.toString());
			*/
	    }
		
		public void rccReceiver(Object o)
		{
			String s=o.toString();
			IScope roomScope=Red5.getConnectionLocal().getScope();
			System.out.println(s+" in rccReceiver(Object o)");
			//FileMgrTabInfo fileMgr=(FileMgrTabInfo)getSharedObject(roomScope,roomScope.getName()).getAttribute("getFileMgrTabInfo");
			//SynchronizationBean synchBean=(SynchronizationBean)getSharedObject(roomScope,roomScope.getName()).getAttribute("getSynchronization");
			try
			{
				//s=s.substring(1, s.indexOf("}"));
				//String[] sToken=s.split(",");
				//String[] token=sToken[0].trim().split("=");
				//System.out.println(token[0]+"="+token[1]+" "+s); 
				
				/*** SYNCHRONIZATION ***/
				/*
				if(token[1].equals("RCC_MENU"))
				{
					token=sToken[1].trim().split("=");
					synchBean.setCurrentMenu(Short.parseShort(token[1]));
					//System.out.println("current menu="+synchBean.getCurrentMenu());
				}
				else if(token[1].equals("RCC_ZOOM_CHANGE"))
				{
					token=sToken[1].trim().split("=");
					//System.out.println("pre-current scale="+Float.parseFloat(token[1]));
					synchBean.setCurrentScale(Float.parseFloat(token[1]));
					//System.out.println("post-current scale="+synchBean.getCurrentScale());
				}
				else if(token[1].equals("RCC_HSCROLL_CHANGE"))
				{
					token=sToken[1].trim().split("=");
					synchBean.setHorizontalPosition(Integer.parseInt(token[1]));
					//System.out.println("horizontal position="+synchBean.getHorizontalPosition());
				}
				else if(token[1].equals("RCC_VSCROLL_CHANGE"))
				{
					token=sToken[1].trim().split("=");
					synchBean.setVerticalPosition(Integer.parseInt(token[1]));
					//System.out.println("vertical position="+synchBean.getVerticalPosition());
				}
				else if(token[1].equals("RCC_IMAGE"))
				{
					token=sToken[1].trim().split("=");
					synchBean.setTextFilePath(token[1]);
					//System.out.println("current text image path="+synchBean.getTextFilePath());
				}
				else if(token[1].equals("RCC_VIDEO"))
				{
					token=sToken[1].trim().split("=");
					synchBean.setVideoFilePath(token[1]);
					//System.out.println("current vieo path="+synchBean.getVideoFilePath());
				}
				else if(token[1].equals("RCC_DRAW"))
				{
					//find the current menu selection index
					//System.out.println("current menu="+synchBean.getCurrentMenu());
					token=sToken[2].trim().split("=");
					if(token[2].equals("0"))//TextBook
					{
						itemsList=(DrawItemsBean)getSharedObject(roomScope,roomScope.getName()).getAttribute("drawOnTextBook");
						if(itemsList.getMyMenu()!=0) System.err.println("ERROR: sementic error in current menu checking of TextBook");
					}
					else if(token[2].equals("1"))//Video
					{
						itemsList=(DrawItemsBean)getSharedObject(roomScope,roomScope.getName()).getAttribute("drawOnVideo");
						if(itemsList.getMyMenu()!=1) System.err.println("ERROR: sementic error in current menu checking of Video");
					}
					else if(token[2].equals("2"))//White Board
					{
						itemsList=(DrawItemsBean)getSharedObject(roomScope,roomScope.getName()).getAttribute("drawOnWhiteBoard");
						if(itemsList.getMyMenu()!=2) System.err.println("ERROR: sementic error in current menu checking of WhiteBoard");
					}
					
					token=sToken[1].trim().split("=");
					//System.out.println("next command="+token[2]);
					if(token[2].equals("RCC_DRAW_POINT"))
					{
						//find the current state
						token=sToken[4].trim().split("=");
						if(token[1].equals("mouseDown"))
						{
							item=new DrawItemBean();
							//System.out.println("state=mouseDown");
							token=sToken[3].trim().split("=");
							item.setPenSize(Short.parseShort(token[1]));
							
							token=sToken[5].trim().split("=");
							item.setPenColor(Short.parseShort(token[1]));
							
							Point point=new Point();
							token=sToken[6].trim().split("=");							
							point.setX(Integer.parseInt(token[1]));
							
							token=sToken[7].trim().split("=");
							point.setY(Integer.parseInt(token[1]));
							
							item.getPointList().add(point);
						}
						else if(token[1].equals("mouseMove"))
						{
							//System.out.println("state=mouseMove");
							Point point=new Point();
							token=sToken[6].trim().split("=");							
							point.setX(Integer.parseInt(token[1]));
							
							token=sToken[7].trim().split("=");
							point.setY(Integer.parseInt(token[1]));
							
							item.getPointList().add(point);
						}
						else if(token[1].equals("mouseUp"))
						{
							//System.out.println("state=mouseUp");
							Point point=new Point();
							token=sToken[6].trim().split("=");							
							point.setX(Integer.parseInt(token[1]));
							
							token=sToken[7].trim().split("=");
							point.setY(Integer.parseInt(token[1]));
							
							item.getPointList().add(point);
							itemsList.getItems().add(item);							
							printItemList(itemsList);
						}
					}
					else if(token[2].equals("RCC_DRAW_ERASE"))
					{
						//This command will delete the contents of array
						itemsList.clearAll();
						printItemList(itemsList);
					}
					
				}*/

			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.severe(e.toString());
			}
		}
		/*
		public void printItemList(DrawItemsBean itemsList)
		{
			//System.out.println("****************** Item List Begin *******************************");
			//System.out.println(itemsList);
			//System.out.println("****************** Item List End *******************************");
		}
		*/
	}
}