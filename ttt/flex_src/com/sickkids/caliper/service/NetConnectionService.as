package com.sickkids.caliper.service
{
	import com.sickkids.caliper.components.AppCommon;
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.vo.UserInfoBean;
	
	import flash.events.AsyncErrorEvent;
	import flash.events.NetStatusEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.NetConnection;
	import flash.net.Responder;
	
	import mx.core.FlexGlobals;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.robotlegs.mvcs.Actor;
	
	public class NetConnectionService extends Actor implements INetConnectionService
	{
		[Inject] public var tttModel:TttModel;
		
		private var _netConnection:NetConnection=null;
		private var _url:String="rtmp://localhost/ttt";
		
		public function NetConnectionService(dest:String="carmProxy", endpoint:String="{FlexGlobals.topLevelApplication._host}/carm/messagebroker/amf")
		{
			trace("INFO: NetConnectionService() is called in NetConnectionService.as, endpoint="+endpoint);
			//_remoteObject=new RemoteObject();
			//_remoteObject.concurrency="last";
			//_remoteObject.showBusyCursor=true;
			//_remoteObject.destination=dest;
			//_remoteObject.endpoint=endpoint;
			//_remoteObject.endpoint="http://localhost:8080/carm/messagebroker/amf";
			//_remoteObject.endpoint="https://www.centerforsafetyresearch.com/carm/messagebroker/amf";
			
			_netConnection=new NetConnection();
			_netConnection.client=this;
			
			_netConnection.addEventListener(NetStatusEvent.NET_STATUS, onNetStatusResult);
			_netConnection.addEventListener(SecurityErrorEvent.SECURITY_ERROR, onSecurityError);
			_netConnection.addEventListener(AsyncErrorEvent.ASYNC_ERROR, onAsyncError);
			
			super();
		}
		public function roomConnect(userInfo:UserInfoBean):void
		{
			if(_netConnection.connected) 
			{	
				_netConnection.close();//send disconnect event for all open connection(s)
			}
			//dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_CONNECT_EVENT));//display the spin image
			_netConnection.connect(_url+"/"+userInfo.courseNumber,
									userInfo.courseNumber, //roomNumber
									userInfo.courseName, //roomName
									userInfo.userId,
									userInfo.email,
									userInfo.hospitalId,
									userInfo.hopitalName,
									userInfo.participantType);
		} 
		public function netConnection():NetConnection
		{
			return _netConnection;
		}
		//Here, operation is a method defined by the given argument called method.
		//public function callService(method:String, arg1:Object=null, arg2:Object=null, arg3:Object=null, arg4:Object=null, arg5:Object=null, arg6:Object=null) : void 
		/*public function callService_org(... args) : void 
		{   
			var operation:AbstractOperation=_remoteObject.getOperation(args.shift() as String);//method name for the 1st argument
			if(!operation.hasEventListener(ResultEvent.RESULT)) 
			{
				operation.addEventListener(ResultEvent.RESULT, onOperationResult);
			}
			if(!operation.hasEventListener(FaultEvent.FAULT)) 
			{
				operation.addEventListener(FaultEvent.FAULT, onOperationFault);
			}
			//Cancel if there exists the same operation on working,			
			operation.cancel();
			operation.arguments = args;//all the arguemnts are assigned to it

			operation.send();			
		}*/
		/*
		public function callService(... args) : void 
		{   
			var operation:AbstractOperation=_remoteObject.getOperation(args.shift() as String);//method name for the 1st argument
			if(!operation.hasEventListener(ResultEvent.RESULT)) 
			{
				operation.addEventListener(ResultEvent.RESULT, onOperationResult);
			}
			if(!operation.hasEventListener(FaultEvent.FAULT)) 
			{
				operation.addEventListener(FaultEvent.FAULT, onOperationFault);
			}
			//Cancel if there exists the same operation on working,			
			operation.cancel();
			operation.arguments = args;//all the arguemnts are assigned to it
			
			operation.send();		
			
			if(!_netConnection.hasEventListener(NetStatusEvent.NET_STATUS))
			{
				_netConnection.addEventListener(NetStatusEvent.NET_STATUS, onOperationResult);
			}
			if(!_netConnection.hasEventListener(SecurityErrorEvent.SECURITY_ERROR))
			{
				_netConnection.addEventListener(SecurityErrorEvent.SECURITY_ERROR, onOperationFault);
			}
			if(!_netConnection.hasEventListener(AsyncErrorEvent.ASYNC_ERROR))
			{
				_netConnection.addEventListener(AsyncErrorEvent.ASYNC_ERROR, onAsyncError);
			}
		}*/
		//Check the Responder later before using to find out the result status
		public function callService(method:String, res:Responder,arg1:Object=null,arg2:Object=null, arg3:Object=null):void
		{	
			//args=args as Array; //from (...args): <== this is not working			
			if(arg3!=null) _netConnection.call(method,res,arg1,arg2,arg3);
			else if(arg2!=null) _netConnection.call(method,res,arg1,arg2);
			else if(arg1!=null) _netConnection.call(method,res,arg1);
			else _netConnection.call(method,res);			
		}	
		/*****************************************************************************************************************
		 *  for Room Connect
		 ****************************************************************************************************************/
		public function setSoService(soName:String):void
		{	
			/*trace("INFO: sharedObject name through setSoService("+soName+")");
			if(model.soChat!=null)
			{
				model.soChat.clear();
				model.soChat.close();
				model.soChat=null;
			}
			model.soChat = SharedObject.getRemote(soName, nc.uri, false );
			if(!model.soChat.hasEventListener(NetStatusEvent.NET_STATUS))
				model.soChat.addEventListener( NetStatusEvent.NET_STATUS, responder.result );
			
			if(!model.soChat.hasEventListener(AsyncErrorEvent.ASYNC_ERROR))
				model.soChat.addEventListener( AsyncErrorEvent.ASYNC_ERROR, asyncErrorHandler );
			
			if(!model.soChat.hasEventListener(SyncEvent.SYNC))
				model.soChat.addEventListener( SyncEvent.SYNC, sharedObjectSyncHandler );
			
			if(model.soChat.client!=null)
				model.soChat.client = responder;
			
			model.soChat.connect( nc );  
			*/
		}
		
		public function sendSoMsg(method:String, msg:String):void
		{
			/*
			if(model.soChat!=null) 
			{
				trace("INFO:: sendSoMsg(method:"+method+", msg:"+msg+") >>>"+model.soChat.data.previousMsg+"<<<");
				model.soChat.send(method, msg);
			}
			*/
		}
		public function sendSoObject(method:String, o:Object):void
		{
			/*
			trace("INFO:: sendSoObject(method:"+method+", o:"+o+")");
			if(model.soChat!=null) 
			{
				model.soChat.send(method, o);
			}
			*/
		}
		/*****************************************************************************************************************
		 *  for Room Connect and App Connect
		 ****************************************************************************************************************/
		public function close():void
		{
			if(_netConnection.connected) _netConnection.close();
			dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT));
		}	
		/*****************************************************************************************************************
		 *  for APP remote shared object
		 ****************************************************************************************************************/
		public function setSoAppService(soName:String):void
		{	
			/*
			if(model.soApp!=null)
			{
				model.soApp.clear();
				model.soApp.close();
				model.soApp=null;
			}
			model.soApp= SharedObject.getRemote(soName, nc.uri, false );
			
			if(!model.soApp.hasEventListener(NetStatusEvent.NET_STATUS))
				model.soApp.addEventListener( NetStatusEvent.NET_STATUS, responder.result );
			
			if(!model.soApp.hasEventListener(AsyncErrorEvent.ASYNC_ERROR))
				model.soApp.addEventListener( AsyncErrorEvent.ASYNC_ERROR, asyncErrorHandler );
			
			if(!model.soApp.hasEventListener(SyncEvent.SYNC))
				model.soApp.addEventListener( SyncEvent.SYNC, sharedObjectSyncHandler );
			
			if(model.soApp.client!=null)
				model.soApp.client = responder;
			model.soApp.connect( nc );
			*/
		}
		public function onNetStatusResult(e:NetStatusEvent):void
		{
				switch (e.info.code) 
				{
					case "NetConnection.Connect.AppShutdown":
							trace("INFO: The server-side application is shutting down.");//error
							break;
					case "NetConnection.Connect.Success" ://status
						  trace("INFO: The connection attempt succeeded.");
						  dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_CONNECTED_EVENT));//display the connected image
						  break;
					case "NetConnection.Connect.Closed" :
						//Use "NetConnection.Connect.Closed" to implement your NetConnection reconnect logic.
						//Here is to show that the remote connection/app server is closed, appropriate action needs to be taken
						//if it is closed from server, then let the user know using Alert.show("please try again to connect to the server since it may be due to a hight network traffic at the current moment.");					
						trace("INFO: The connection was closed successfully.");//status	
						dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT));//display the disconnected image
						break;
					case "NetConnection.Connect.Failed" :
						trace("INFO: The connection attempt failed.");//error
						dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT));
						break;
					case "NetConnection.Connect.Rejected" ://error
						//The connection attempt did not have permission to access the application.
						trace("INFO: Remote server rejected a room connection request.");
						break;
					case "NetConnection.Connect.InvalidApp": //error
						trace("INFO: The application name specified in the call to NetConnection.connect() is invalid.");
						break;
					case "NetConnection.Connect.IdleTimeout": //status
						//On Flash Media Server, <AutoCloseIdleClients> is disabled by default. When enabled, the default timeout value is 3600 seconds (1 hour). 
						//For more information, see Close idle connections.
						trace("INFO: The server disconnected the client because the client was idle longer than the configured value for <MaxIdleTime>.");
						break; 
					case "NetConnection.Connect.NetworkChange": //status
						//Use this event to check for a network interface change. Don't use this event to implement your NetConnection reconnect logic. 
						trace("INFO: Flash Player has detected a network change, for example, a dropped wireless connection, a successful wireless connection,or a network cable loss.");
						break; 
					
					//************* NetStream ************/
					
					case "NetStream.Publish.Start":
						trace("INFO: NetStream.Publish.Start.");
						break;
					case "NetStream.Publish.BadName":
						trace("INFO: The stream name is already used.");
						break;
					case "NetStream.Play.Start":
						trace("INFO: NetStream.Play.Start.");
						break;
					case "NetStream.Play.UnpublishNotify": 
						//NetStream(event.currentTarget).close(); <-----Use this Feb 5, 2013
						trace("INFO: NetStream.Play.UnpublishNotify.");
						break;
					case "NetStream.Unpublish.Success": 
						 trace("INFO: NetStream.Unpublish.Success."); 
							break;
					case "NetStream.Play.Stop":
						trace("INFO: NetStream.Play.Stop.");
						break;	
					case "NetStream.Failed":	trace("INFO: NetStream.Failed.");
						 break;
					case "NetStream.Play.Failed": trace("INFO: NetStream.Play.Failed.");
						  break;
					case "NetStream.Play.StreamNotFound": // These get routed to the same handler as NetConnection
						trace("INFO: NetStream.Play.StreamNotFound.");
						break;
					
					case "NetStream.Buffer.Full":              		  
						//netStream.bufferTime=AppModel.VOD_EXPANED_BUFFER_TIME;	              		  	
						trace("INFO: NetStream.Buffer.Full.");           		  
						break;
					case "NetStream.Buffer.Empty":			  		  
						//netStream.bufferTime=AppModel.VOD_START_BUFFER_TIME;
						trace("INFO: NetStream.Buffer.Empty.");
						break;
					case "NetStream.Unpause.Notify":
						trace("INFO: NetStream.Unpause.Notify.");
						break;
					case "NetStream.Play.InsufficientBW":
						//netStream.bufferTime=AppModel.VOD_START_BUFFER_TIME;
						trace("INFO: NetStream.Unpause.Notify/NetStream.Play.InsufficientBW.");
						break;
					default :
						trace("INFO: Unknown event code.");
						break;
				}	
		}
		private function onSecurityError(e:SecurityErrorEvent):void 
		{
			trace("INFO: securityErrorHandler: " + e);
		}
		
		private function onAsyncError(e:AsyncErrorEvent):void 
		{
			trace("INFO: onAsyncError: " + e);
		}
		public function onBWCheck(...rest):Number
		{ 
			trace("INFO: onBWCheck: " + rest);
			return 0; 
		} 
		
		public function onBWDone(...rest):void
		{ 
			var p_bw:Number; 
			if (rest.length > 0){
				p_bw = rest[0]; 
			}
			trace("INFO: bandwidth = " + p_bw + " Kbps in onBWDone(...)."); 
		}
		public function onOperationResult(e:ResultEvent):void
		{		
			e.target.removeEventListener(ResultEvent.RESULT, onOperationResult);
			
			var op:String=e.token.message["operation"];
			trace("INFO: onOperationResult() is called in NetConnectionService.as, op="+op+", e.result="+e.result);
			if(e.token.message.destination=="carmProxy")
			{	
				switch(op)
				{
					case "getAppList": 
							//appModel.appList=e.result as ArrayCollection;
							//AppCommon.app_setSharedAppListObject(appModel.appList);//save as a shared object
							break;
					/*
					case "getTestString":
						break;
					case "getInstrumentNameList":
							//Clean up if there exists a list of data.
							if(caliperModel.instrumentNameList!=null) caliperModel.instrumentNameList.removeAll();
							caliperModel.instrumentNameList=e.result as ArrayCollection;
							dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST));
							break;
					case "getAnalyteList":
						//Clean up if there exists a list of data.
						if(caliperModel.analyteNameList!=null) caliperModel.analyteNameList.removeAll();
						caliperModel.analyteNameList=e.result as ArrayCollection;
						dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST));
						break;
					case "getRawDataList":
						//Clean up if there exists a list of data.
						if(caliperModel.rawDataList!=null) caliperModel.rawDataList.removeAll();
						caliperModel.rawDataList=e.result as ArrayCollection;
						dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_RAW_DATA_LIST));
						break;
					*/
					default:
						trace("INFO: unknwon operation, "+op+", is back.");
						break;
				}
				/*
				switch(op)
				{
					case "getOPENQuestionList":	
						if(model.questionList!=null) model.questionList.removeAll();
						model.questionList=e.result as ArrayCollection; 						
						trace("INFO: records received from getOPENQuestionList(): "+model.questionList.length);
						break;
					case "insertAQuestionForESC":	
						if(model.questionList==null) model.questionList=new ArrayCollection();
						model.questionList.addItemAt(e.result as EpochEscQuestionBean, 0); 	
						Alert.show("The question is successfully registered.","Information");
						model.createSuccess=!model.createSuccess;					
						trace("INFO: a record received from insertAQuestionForESC() with question_id=: "+EpochEscQuestionBean(e.result).question_id);
						break;
					case "updateAQuestionForESC":	
						if(Utils.replaceAnObject(model.questionList, rmiEvent.arg1 as EpochEscQuestionBean))
							Alert.show("The question is successfully updated ("+e.result+").","Information");	
						else
							Alert.show("The question is successfully updated ("+e.result+") but its values are not updated into the data grid.","Warning");				
						trace("INFO: The record is successfully updated through updateAQuestionForESC() with rows=: "+e.result);
						break;
					case "voteResultForESC":	
						if(model.voteResultList!=null) model.voteResultList.removeAll();
						model.voteResultList=e.result as ArrayCollection; 						
						trace("INFO: records received from voteResultForESC(): "+model.voteResultList.length);
						break;
					case "myVoteDoneListForESC":	
						if(model.myVoteDoneList!=null) model.myVoteDoneList.removeAll();
						model.myVoteDoneList=e.result as ArrayCollection; 				
						trace("INFO: records received from myVoteDoneListForESC(): "+model.myVoteDoneList.length);
						break;
					case "updateInsertVoteForESC":	
						var qb:EpochEscQuestionBean=e.result as EpochEscQuestionBean;
						qb.myvotedone=true;
						qb.myvotecategory=EpochEscVoteBean(rmiEvent.arg1).vote_category;
						qb.myvotetime=new Date();
						qb.mycomment=EpochEscVoteBean(rmiEvent.arg1).remarks;
						if(!Utils.replaceAnObject(model.questionList, qb))
							Alert.show("The question is successfully updated ("+e.result+") but its values are not updated into the data grid.","Warning");	
						model.voteSuccess=!model.voteSuccess;//to trigger an event				
						trace("INFO: an object received from updateInsertVoteForESC(): "+e.result as EpochEscQuestionBean);
						break;
					case "inactivateAQuestionForESC":	
						if(Utils.deleteAnObject(model.questionList, rmiEvent.arg1 as int))
						{							
							Alert.show("The question is successfully deleted ("+e.result+").","Information");	
							model.deleteSuccess=!model.deleteSuccess;
						}
						else
							Alert.show("The question is successfully deleted ("+e.result+") but its deleted values are not updated from the data grid.","Warning");				
						trace("INFO: The record is successfully updated through inactivateAQuestionForESC() with rows=: "+e.result);
						break;
					default: break;
					
				}	
				*/
			} 		
		}
		
		public function onOperationFault(e:FaultEvent):void
		{		
			e.target.removeEventListener(ResultEvent.RESULT, onOperationResult);
			//INFO: onOperationFault() is called in NetConnectionService.as: [FaultEvent fault=[RPC Fault faultString="Cannot invoke method 'getStrings'." faultCode="Server.ResourceUnavailable" faultDetail="Method 'getStrings' not found."] messageId="6AB214F5-60CA-733E-67CC-64FAF5338CE2" type="fault" bubbles=false cancelable=true eventPhase=2]
			trace("INFO: onOperationFault() is called in NetConnectionService.as: "+e);
			//trace("\tfalut:"+e.fault);
			//trace("\theader:"+e.headers);
			//trace("\tmessage:"+e.message);
			if(AppCommon.contains(e.toString(), "NetConnection.Call.Failed"))
				FlexGlobals.topLevelApplication.showAlertMessage("No Network Connection!!!");
			else
				FlexGlobals.topLevelApplication.showAlertMessage(e.fault.faultString+"!!!");
			//dispatch(new ApplicationRMIEvent(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, e.fault.faultCode, e.fault.faultDetail, e.fault.faultString, e.fault.message));
			//dispatch(new ApplicationRMIEvent(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, e.fault.faultCode, e.fault.faultDetail, e.fault.faultString, e.fault.message));
			//Alert.show(e.fault.faultString)
			//made alert spot at the top of the panel
			
			//info==e now Jan 15, 2014
			/*if(String(info).indexOf("SESSION_TIMEOUT")!=-1)//found
			{
				Alert.show("Your session is expired.\nPlease login in again by clicking Logout.\n\n", "Session Expired");
			}	
			else switch(rmiEvent.type)
			{	
				case AppVoteController.RO_EPOCH_GET_ESC_QUESTION_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_ESC_QUESTION_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_CREATE_NEW_QUESTION_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_CREATE_NEW_QUESTION_REQUEST_EVENT) while creating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_UPDATE_A_QUESTION_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_UPDATE_A_QUESTION_REQUEST_EVENT) while updating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_GET_ESC_VOTE_RESULT_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_ESC_VOTE_RESULT_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_GET_MY_VOTE_DONE_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_MY_VOTE_DONE_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_UPDATE_OR_CREATE_MY_VOTE_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_UPDATE_OR_CREATE_MY_VOTE_REQUEST_EVENT) while inserting/updating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_INACTIVATE_A_QUESTION_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_INACTIVATE_A_QUESTION_EVENT) while deleting the selected question: "+info,"Error");
					break;
				default:
					Alert.show("Unknown Fault has been generated in RemoteObjectCommandForVote.as: "+info,"Error"); 
					break;
			}	
			*/
		}
		/*public function putApplications(appList:ArrayCollection):void
		{
			for(var i:int=0;i<appList.length;i++)
			{
				var o:AllApplicationBean=appList.getItemAt(i) as AllApplicationBean;
				appModel.appMap.put(o.app_id, o);
			}
			AppCommon.app_setSharedAppListObject(appModel.appMap);
			trace("INFO: after writting the app list into the shared obj with the size="+AppCommon.app_sharedAppListObject.data.appMap.size());
		}*/
	}
}