package com.sickkids.caliper.view.ttt
{
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	
	import flash.events.MouseEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ControlContainerMediator extends Mediator
	{
		//[Inject] public var condutorPanel:ConductorPanel;
		[Inject] public var controlContainer:ControlContainer;
		[Inject] public var model:TttModel;
		public function ControlContainerMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ControlContainerMediator.as");
			
			controlContainer.settings.addEventListener(MouseEvent.CLICK, onSettingsClick);
			
			trace("INFO: TttNetConnectionEvent.ROOM_CONNECT_EVENT onRegister() is called in ControlContainerMediator.as");
			dispatch(new TttNetConnectionEvent(TttNetConnectionEvent.ROOM_CONNECT_EVENT, model.userInfo));
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ControlContainerMediator.as");
		}
		private function onSettingsClick(event:MouseEvent):void
		{
			trace("INFO: onSettingsClick() is clicked in ControlContainerMediator.as");
			if(model.userInfo.bTokenPossesed)
			{
				this.controlContainer.parentDocument.toggleAdminPanelView();
			}
			else
			{
				//no event will be produced but ignored
			}
		}
	}
}