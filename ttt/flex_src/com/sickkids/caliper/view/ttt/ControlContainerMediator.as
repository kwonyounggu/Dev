package com.sickkids.caliper.view.ttt
{
	import com.sickkids.caliper.controller.SequentialAction;
	import com.sickkids.caliper.events.TttNetCallEvent;
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	import com.sickkids.caliper.vo.UserBean;
	
	import flash.display.StageDisplayState;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.Responder;
	
	import mx.binding.utils.ChangeWatcher;
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.events.PropertyChangeEvent;
	import mx.managers.SystemManager;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ControlContainerMediator extends Mediator
	{
		[Inject] public var controlContainer:ControlContainer;
		[Inject] public var model:TttModel;
		[Inject] public var netConnectionService:INetConnectionService;
		private var callServiceResponder:Responder=new Responder(okResult, faultResult);
		public var _watcher:ChangeWatcher;
		
		public function ControlContainerMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ControlContainerMediator.as");
			
			controlContainer.settings.addEventListener(MouseEvent.CLICK, onSettingsClick);
			controlContainer.connectedId.addEventListener(MouseEvent.CLICK, onDisconnectClick);
			controlContainer.disconnectedId.addEventListener(MouseEvent.CLICK, onConnectClick);
			
			
			
			this.addContextListener(TttNetConnectionEvent.ROOM_CONNECTED_EVENT, onNetConnectionStatus, TttNetConnectionEvent);
			this.addContextListener(TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT, onNetConnectionStatus, TttNetConnectionEvent);
			this.addContextListener(TttNetConnectionEvent.ROOM_CONNECT_FAILED_EVENT, onNetConnectionStatus, TttNetConnectionEvent);
			
			this.addContextListener(TttNetConnectionEvent.ROOM_JOIN_EVENT, onRoomJoin, TttNetConnectionEvent);
			this.addContextListener(TttNetConnectionEvent.ROOM_LEAVE_EVENT, onRoomLeave, TttNetConnectionEvent);
			
			
			
			onConnectClick(null);
			initialize();
			
			if(model.userInfo.participantType=="LECTURER" || model.userInfo.participantType=="TEACHING_ASSISTANT")
			{
				controlContainer.controlSwitch.addEventListener(Event.CHANGE, onControlClick);
				this.addContextListener(TttNetCallEvent.TOKEN_RECEIVED_EVENT, onTokenReceived, TttNetCallEvent);
				this.addContextListener(TttNetCallEvent.TOKEN_SEND_FAILED_EVENT, onTokenSendFailed, TttNetCallEvent);

				_watcher=ChangeWatcher.watch(model, ["userInfo", "bTokenPossesed"], onChangeTokenPossed);
				model.synchObj=new SequentialAction();	
			}
			controlContainer.fullScreen.addEventListener(MouseEvent.CLICK, onToggleFullScreen);//to everyone
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ControlContainerMediator.as");
		}
		private function initialize():void
		{
			switch(model.userInfo.participantType)
			{
				case "LECTURER": controlContainer.attenceLabel.visible=controlContainer.attenceLabel.includeInLayout=false; break;
				case "TEACHING_ASSISTANT":
					controlContainer.attenceLabel.visible=controlContainer.attenceLabel.includeInLayout=false;
					//controlContainer.fullScreen.visible=controlContainer.fullScreen.includeInLayout=false;
					//controlContainer.settings.visible=controlContainer.settings.includeInLayout=false;
					//controlContainer.controlSwitch.visible=controlContainer.controlSwitch.includeInLayout=true;
					break;
				case "INTERACTIVE_VIEWER1": 					
				case "INTERACTIVE_VIEWER2":
				case "ONEWAY_VIEWER1": 
				case "ONEWAY_VIEWER2": 
				case "ONEWAY_VIEWER3": 
				case "ONEWAY_VIEWER4": 
				case "ONEWAY_VIEWER5": 
				case "ONEWAY_VIEWER6":  
					//controlContainer.fullScreen.visible=controlContainer.fullScreen.includeInLayout=false;
					//controlContainer.settings.visible=controlContainer.settings.includeInLayout=false;
					controlContainer.controlSwitch.visible=controlContainer.controlSwitch.includeInLayout=false;
					break;
				default: break;
			}
		}
		private function onSettingsClick(event:MouseEvent):void
		{
			trace("INFO: onSettingsClick() is clicked in ControlContainerMediator.as (token="+model.userInfo.bTokenPossesed+")");
			//if(model.userInfo.bTokenPossesed)
			{
				this.controlContainer.parentDocument.toggleAdminPanelView();
				model.synchObj.settingsOn=FlexGlobals.topLevelApplication.videoSwfView.visible;
			}
			//else
			{
				//no event will be produced but ignored
			}
		}
		private function onDisconnectClick(event:MouseEvent):void
		{
			trace("INFO: onDisconnectClick() is clicked in ControlContainerMediator.as");
			netConnectionService.close();
		}
		private function onConnectClick(event:MouseEvent):void
		{
			trace("INFO: onConnectClick() to connect NetConnection is clicked in ControlContainerMediator.as");
			dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_CONNECT_EVENT, model.userInfo));//connection request
			onNetConnectionStatus(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_CONNECT_EVENT));
		}
		private function onControlClick(e:Event):void
		{
			trace("INFO: onControlClick("+controlContainer.controlSwitch.selected+") to transfer the control to other conductor is clicked in ControlContainerMediator.as");
			if(model.userInfo.participantType=="TEACHING_ASSISTANT" || model.userInfo.participantType=="LECTURER")
			{
			
				if(controlContainer.controlSwitch.selected)//to Control ON
				{
					model.userInfo.bTokenPossesed=true;
					this.dispatch(new TttNetCallEvent(TttNetCallEvent.CALL_TO_METHOD_EVENT, callServiceResponder, "tokenPass", model.userInfo.participantType+"|bTokenPossesed=false"));
				}
				else //to Control OFF
				{
					model.userInfo.bTokenPossesed=false;
					this.dispatch(new TttNetCallEvent(TttNetCallEvent.CALL_TO_METHOD_EVENT, callServiceResponder, "tokenPass", model.userInfo.participantType+"|bTokenPossesed=true"));
					
				}
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" onControlClick() of ControlContainerMediator.as");
		}
		private function onNetConnectionStatus(e:TttNetConnectionEvent):void
		{
			switch(e.type)
			{
				case TttNetConnectionEvent.ROOM_CONNECT_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_CONNECT_EVENT) is called in ControlContainerMediator.as");
					controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=false;
					controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=false;
					controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=true;
					controlContainer.simpleThrobber.start();
					resetLoginStatus();
					break;
				case TttNetConnectionEvent.ROOM_CONNECTED_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_CONNECTED_EVENT) is called in ControlContainerMediator.as");
					controlContainer.simpleThrobber.stop();
					controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=true;
					controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=false;
					controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=false;					
					break;
				case TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_DISCONNECTED_EVENT) is called in ControlContainerMediator.as");
					controlContainer.simpleThrobber.stop();
					controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=false;
					controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=true;
					controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=false;					
					break;
				case TttNetConnectionEvent.ROOM_CONNECT_FAILED_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_CONNECT_FAILED_EVENT) is called in ControlContainerMediator.as");
					controlContainer.simpleThrobber.stop();
					controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=false;
					controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=true;
					controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=false;
					break;
				default: break;
			}
			controlContainer.controlSwitch.enabled=controlContainer.connectedId.visible;
			//controlContainer.fullScreen.enabled=controlContainer.settings.enabled=controlContainer.controlSwitch.enabled=controlContainer.connectedId.visible;
		}
		private function onRoomJoin(e:TttNetConnectionEvent):void
		{
			var ub:UserBean=e.arg1 as UserBean;
			switch(ub.participantType)
			{
				case "LECTURER":
					controlContainer.lecturerIn.visible=controlContainer.lecturerIn.includeInLayout=true;
					controlContainer.lecturerOut.visible=controlContainer.lecturerOut.includeInLayout=false;
					controlContainer.lecturerIn.toolTip=ub.toolTip();
					
					//LECTURER just logged in, TA gives up the token if possesed
					if(model.userInfo.participantType=="TEACHING_ASSISTANT" && model.userInfo.bTokenPossesed) 
					{
						model.userInfo.bTokenPossesed=false;
						controlContainer.controlSwitch.selected=false;
					}
					break;
				case "TEACHING_ASSISTANT": 
					controlContainer.taIn.visible=controlContainer.taIn.includeInLayout=true;
					controlContainer.taOut.visible=controlContainer.taOut.includeInLayout=false;
					controlContainer.taIn.toolTip=ub.toolTip();
					
					//TA just logged in, LECTURER takes the token if not possesed
					if(model.userInfo.participantType=="LECTURER" && !model.userInfo.bTokenPossesed) 
					{
						model.userInfo.bTokenPossesed=true;
						controlContainer.controlSwitch.selected=true;
					}
					break;
				case "INTERACTIVE_VIEWER1": 
					controlContainer.active1In.visible=controlContainer.active1In.includeInLayout=true;
					controlContainer.active1Out.visible=controlContainer.active1Out.includeInLayout=false;
					controlContainer.active1In.toolTip=ub.toolTip();
					break;
				case "INTERACTIVE_VIEWER2":
					controlContainer.active2In.visible=controlContainer.active2In.includeInLayout=true;
					controlContainer.active2Out.visible=controlContainer.active2Out.includeInLayout=false;
					controlContainer.active2In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER1":
					controlContainer.passive1In.visible=controlContainer.passive1In.includeInLayout=true;
					controlContainer.passive1Out.visible=controlContainer.passive1Out.includeInLayout=false;
					controlContainer.passive1In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER2":
					controlContainer.passive2In.visible=controlContainer.passive2In.includeInLayout=true;
					controlContainer.passive2Out.visible=controlContainer.passive2Out.includeInLayout=false;
					controlContainer.passive2In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER3":
					controlContainer.passive3In.visible=controlContainer.passive3In.includeInLayout=true;
					controlContainer.passive3Out.visible=controlContainer.passive3Out.includeInLayout=false;
					controlContainer.passive3In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER4":
					controlContainer.passive4In.visible=controlContainer.passive4In.includeInLayout=true;
					controlContainer.passive4Out.visible=controlContainer.passive4Out.includeInLayout=false;
					controlContainer.passive4In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER5":
					controlContainer.passive5In.visible=controlContainer.passive5In.includeInLayout=true;
					controlContainer.passive5Out.visible=controlContainer.passive5Out.includeInLayout=false;
					controlContainer.passive5In.toolTip=ub.toolTip();
					break;
				case "ONEWAY_VIEWER6":
					controlContainer.passive6In.visible=controlContainer.passive6In.includeInLayout=true;
					controlContainer.passive6Out.visible=controlContainer.passive6Out.includeInLayout=false;
					controlContainer.passive6In.toolTip=ub.toolTip();
					break;
				default: break;
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" onRoomJoin() of ControlContainerMediator.as");
		}
		private function onRoomLeave(e:TttNetConnectionEvent):void
		{
			var ub:UserBean=e.arg1 as UserBean;
			switch(ub.participantType)
			{
				case "LECTURER":
					controlContainer.lecturerIn.visible=controlContainer.lecturerIn.includeInLayout=false;
					controlContainer.lecturerOut.visible=controlContainer.lecturerOut.includeInLayout=true;
					
					//LECTURER just logged out, TA takes the token if not possesed
					if(model.userInfo.participantType=="TEACHING_ASSISTANT" && !model.userInfo.bTokenPossesed) 
					{
						model.userInfo.bTokenPossesed=true;
						controlContainer.controlSwitch.selected=true;
					}
					break;
				case "TEACHING_ASSISTANT": 
					controlContainer.taIn.visible=controlContainer.taIn.includeInLayout=false;
					controlContainer.taOut.visible=controlContainer.taOut.includeInLayout=true;
					
					//TA just logged out, LECTURER takes the token if not possesed
					if(model.userInfo.participantType=="LECTURER" && !model.userInfo.bTokenPossesed) 
					{
						model.userInfo.bTokenPossesed=true;
						controlContainer.controlSwitch.selected=true;
					}
					break;
				case "INTERACTIVE_VIEWER1": 
					controlContainer.active1In.visible=controlContainer.active1In.includeInLayout=false;
					controlContainer.active1Out.visible=controlContainer.active1Out.includeInLayout=true;
					break;
				case "INTERACTIVE_VIEWER2":
					controlContainer.active2In.visible=controlContainer.active2In.includeInLayout=false;
					controlContainer.active2Out.visible=controlContainer.active2Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER1":
					controlContainer.passive1In.visible=controlContainer.passive1In.includeInLayout=false;
					controlContainer.passive1Out.visible=controlContainer.passive1Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER2":
					controlContainer.passive2In.visible=controlContainer.passive2In.includeInLayout=false;
					controlContainer.passive2Out.visible=controlContainer.passive2Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER3":
					controlContainer.passive3In.visible=controlContainer.passive3In.includeInLayout=false;
					controlContainer.passive3Out.visible=controlContainer.passive3Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER4":
					controlContainer.passive4In.visible=controlContainer.passive4In.includeInLayout=false;
					controlContainer.passive4Out.visible=controlContainer.passive4Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER5":
					controlContainer.passive5In.visible=controlContainer.passive5In.includeInLayout=false;
					controlContainer.passive5Out.visible=controlContainer.passive5Out.includeInLayout=true;
					break;
				case "ONEWAY_VIEWER6":
					controlContainer.passive6In.visible=controlContainer.passive6In.includeInLayout=false;
					controlContainer.passive6Out.visible=controlContainer.passive6Out.includeInLayout=true;
					break;
				default: break;
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" onRoomLeave() of ControlContainerMediator.as");
		}
		private function resetLoginStatus():void
		{
			
			//reset all the loggedIn into loggedOut in initial state
			controlContainer.lecturerIn.visible=controlContainer.lecturerIn.includeInLayout=false;
			controlContainer.lecturerOut.visible=controlContainer.lecturerOut.includeInLayout=true;
			controlContainer.taIn.visible=controlContainer.taIn.includeInLayout=false;
			controlContainer.taOut.visible=controlContainer.taOut.includeInLayout=true;
			
			controlContainer.active1In.visible=controlContainer.active1In.includeInLayout=false;
			controlContainer.active1Out.visible=controlContainer.active1Out.includeInLayout=true;
			controlContainer.active2In.visible=controlContainer.active2In.includeInLayout=false;
			controlContainer.active2Out.visible=controlContainer.active2Out.includeInLayout=true;
			controlContainer.passive1In.visible=controlContainer.passive1In.includeInLayout=false;
			controlContainer.passive1Out.visible=controlContainer.passive1Out.includeInLayout=true;
			controlContainer.passive2In.visible=controlContainer.passive2In.includeInLayout=false;
			controlContainer.passive2Out.visible=controlContainer.passive2Out.includeInLayout=true;
			controlContainer.passive3In.visible=controlContainer.passive3In.includeInLayout=false;
			controlContainer.passive3Out.visible=controlContainer.passive3Out.includeInLayout=true;
			controlContainer.passive4In.visible=controlContainer.passive4In.includeInLayout=false;
			controlContainer.passive4Out.visible=controlContainer.passive4Out.includeInLayout=true;
			controlContainer.passive5In.visible=controlContainer.passive5In.includeInLayout=false;
			controlContainer.passive5Out.visible=controlContainer.passive5Out.includeInLayout=true;
			controlContainer.passive6In.visible=controlContainer.passive6In.includeInLayout=false;
			controlContainer.passive6Out.visible=controlContainer.passive6Out.includeInLayout=true;
		}
		public function okResult(o:Object):void
		{	  
			/****************************************************************************
			 if(o is Boolean) trace("callResult(), return value="+o);//return value=false
			 else trace("callResult(), return="+o);
			 trace("event.type="+event.type);
			 *****************************************************************************/
			trace("INFO: okResult(o:Object) is called in ControlContainerMediator.as");
			//for(var p:String in o) 
			//{
			//	trace("okResult(), "+p + " : " + o[p]);
			//}	
			//update 
		}
		//**************************************************************
		//o contains application, code, description, level attributes
		//**************************************************************
		public function faultResult(o:Object):void
		{
			
			trace("INFO: faultResult(o:Object) is called in ControlContainerMediator.as");
			
			Alert.show("Error: Call failed with '"+o.code+"', please try again later!", "Error returned");
			if(o.code=="NetConnection.Call.Failed")
			{
				if(String(o.description).indexOf("tokenPass")>0) //found
				{
					controlContainer.controlSwitch.selected=!controlContainer.controlSwitch.selected;
					model.userInfo.bTokenPossesed=controlContainer.controlSwitch.selected;
				}
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" faultResult() of ControlContainerMediator.as");
		}
		protected function onTokenReceived(e:TttNetCallEvent):void
		{
			//e.arg1 would be LECTURER|bTokenPossesed=true
			if(e!=null)
			{
				var tokenReceived:String=e.arg1 as String;
				var senderType:String=tokenReceived.split("|")[0];
				tokenReceived=tokenReceived.split("|")[1];
	
				if((senderType=="LECTURER" && model.userInfo.participantType=="TEACHING_ASSISTANT") ||
					(senderType=="TEACHING_ASSISTANT" && model.userInfo.participantType=="LECTURER") )
				{
					tokenReceived=tokenReceived.split("=")[1];//bTokenPossed is true or false
					if(tokenReceived=="true")
					{
						controlContainer.controlSwitch.selected=true;
						model.userInfo.bTokenPossesed=true;
					}
					else //false
					{
						controlContainer.controlSwitch.selected=false;
						model.userInfo.bTokenPossesed=false;
					}
				}
				else
				{
					trace("ERROR: not matching in onTokenReceived() of ControlContainerMediator.as");
				}
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" onTokenReceived() of ControlContainerMediator.as");
		}
		protected function onTokenSendFailed(e:TttNetCallEvent):void
		{
			//e.arg1 would be LECTURER|bTokenPossesed=true
			if(e!=null)
			{
				var tokenPassed:String=e.arg1 as String;
				var senderType:String=tokenPassed.split("|")[0];
				tokenPassed=tokenPassed.split("|")[1];
				
				if((senderType=="LECTURER" && model.userInfo.participantType=="LECTURER") ||
					(senderType=="TEACHING_ASSISTANT" && model.userInfo.participantType=="TEACHING_ASSISTANT") )
				{
					tokenPassed=tokenPassed.split("=")[1];//bTokenPossed is true or false
					if(tokenPassed=="true")
					{
						//I am controling back since 
						controlContainer.controlSwitch.selected=true;
						model.userInfo.bTokenPossesed=true;
					}
					else //false
					{
						controlContainer.controlSwitch.selected=false;
						model.userInfo.bTokenPossesed=false;
					}
				}
				else
				{
					trace("ERROR: not matching in onTokenReceived() of ControlContainerMediator.as");
				}
			}
			log("bTokenPossesed: "+model.userInfo.bTokenPossesed+" onTokenSendFailed() of ControlContainerMediator.as");
		}
		public function onChangeTokenPossed(e:PropertyChangeEvent):void
		{
			if(model.userInfo.bTokenPossesed && controlContainer.controlSwitch.selected) 
			{
				trace("INFO: model.userInfo.bTokenPossesed is true from false");
				//if (i am a dr)
				//	if(rn is connected) i) get sychObject of RN's and ii) broadcast the sysnObject
				//	else broadcast my synchObject
				//else if(i am a rn)
				//	if(dr is connected) i) get sychObject of DR's and ii) broadcast the sysnObject
				//	else broadcast my synchObject
				//Alert.show("true");
			}
			else if(!model.userInfo.bTokenPossesed && !controlContainer.controlSwitch.selected) 
			{
				trace("INFO: model.userInfo.bTokenPossesed is false from true");
				//Alert.show("false");
			}
		}
		public function onToggleFullScreen(e:MouseEvent):void
		{
			try
			{
				var sm:SystemManager=FlexGlobals.topLevelApplication.systemManager;
				switch(sm.stage.displayState)
				{
					case StageDisplayState.FULL_SCREEN:
						sm.stage.displayState=StageDisplayState.NORMAL;
						break;
					default:
						sm.stage.displayState=StageDisplayState.FULL_SCREEN;
						break;
				}
			}
			catch(err:SecurityError)
			{
				//ignore
			}
		}
		public function log(s:String):void
		{
			FlexGlobals.topLevelApplication.log(s);
		}
	}
}