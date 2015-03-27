package com.sickkids.caliper.view.ttt
{
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	import com.sickkids.caliper.vo.UserBean;
	
	import flash.events.MouseEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ControlContainerMediator extends Mediator
	{
		//[Inject] public var condutorPanel:ConductorPanel;
		[Inject] public var controlContainer:ControlContainer;
		[Inject] public var model:TttModel;
		[Inject] public var netConnectionService:INetConnectionService;
		
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
				case "INTERACTIVE_VIEWER1": 					
				case "INTERACTIVE_VIEWER2":
				case "ONEWAY_VIEWER1": 
				case "ONEWAY_VIEWER2": 
				case "ONEWAY_VIEWER3": 
				case "ONEWAY_VIEWER4": 
				case "ONEWAY_VIEWER5": 
				case "ONEWAY_VIEWER6":  
					controlContainer.fullScreen.visible=controlContainer.fullScreen.includeInLayout=false;
					controlContainer.settings.visible=controlContainer.settings.includeInLayout=false;
					controlContainer.controlSwitch.visible=controlContainer.controlSwitch.includeInLayout=false;
					break;
				default: break;
			}
		}
		private function onSettingsClick(event:MouseEvent):void
		{
			trace("INFO: onSettingsClick() is clicked in ControlContainerMediator.as (token="+model.userInfo.bTokenPossesed+")");
			if(model.userInfo.bTokenPossesed)
			{
				this.controlContainer.parentDocument.toggleAdminPanelView();
			}
			else
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
					break;
				case "TEACHING_ASSISTANT": 
					controlContainer.taIn.visible=controlContainer.taIn.includeInLayout=true;
					controlContainer.taOut.visible=controlContainer.taOut.includeInLayout=false;
					controlContainer.taIn.toolTip=ub.toolTip();
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
		}
		private function onRoomLeave(e:TttNetConnectionEvent):void
		{
			var ub:UserBean=e.arg1 as UserBean;
			switch(ub.participantType)
			{
				case "LECTURER":
					controlContainer.lecturerIn.visible=controlContainer.lecturerIn.includeInLayout=false;
					controlContainer.lecturerOut.visible=controlContainer.lecturerOut.includeInLayout=true;
					break;
				case "TEACHING_ASSISTANT": 
					controlContainer.taIn.visible=controlContainer.taIn.includeInLayout=false;
					controlContainer.taOut.visible=controlContainer.taOut.includeInLayout=true;
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
	}
}