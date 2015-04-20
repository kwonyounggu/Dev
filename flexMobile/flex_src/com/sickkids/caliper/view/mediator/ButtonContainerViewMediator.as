package com.sickkids.caliper.view.mediator
{
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.events.SimpleAppEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ButtonContainerViewMediator extends Mediator
	{
		public function ButtonContainerViewMediator()
		{
			trace("INFO: ButtonContainerViewMediator() is called in ButtonContainerViewMediator.mxml");
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ButtonContainerViewMediator.mxml");
			//this.addViewListener(SimpleAppEvent.CHOOSE_FILE, chooseFileHandler, SimpleAppEvent);
			this.addViewListener(SimpleAppEvent.CHOOSE_FILE, dispatch, SimpleAppEvent);//redirect the event to the event bus in the context
			this.addViewListener(RawDataRMIEvent.GET_STRING, dispatch, RawDataRMIEvent);
		}
		public function chooseFileHandler(e:SimpleAppEvent):void
		{
			trace("INFO: chooseFileHandler("+e.type+") is called in ButtonContainerViewMediator.mxml");
		}
		public function getStringEventHandler(e:RawDataRMIEvent):void
		{
			trace("INFO: getStringEventHandler() is called in ButtonContainerViewMediator.as");
		}
	}
}