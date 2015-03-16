package com.sickkids.caliper.view.ttt
{
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	
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
			
			onConnectClick(null);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ControlContainerMediator.as");
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
	}
}