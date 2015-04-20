package com.sickkids.caliper.view.mediator
{
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.model.ApplicationModel;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ApplicationMediator extends Mediator
	{
		[Inject] public var appModel:ApplicationModel;
		[Inject] public var app:csrMobile;
		
		public function ApplicationMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ApplicationMediator.as");
			this.addContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			this.addContextListener(ApplicationRMIEvent.WANING_MESSAGE_EVENT, onWarningMessage, ApplicationRMIEvent);
			this.addContextListener(ApplicationRMIEvent.ERROR_MESSAGE_EVENT, onErrorMessage, ApplicationRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ApplicationMediator.as");
			this.removeContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			this.removeContextListener(ApplicationRMIEvent.WANING_MESSAGE_EVENT, onWarningMessage, ApplicationRMIEvent);
			this.removeContextListener(ApplicationRMIEvent.ERROR_MESSAGE_EVENT, onErrorMessage, ApplicationRMIEvent);
		}		
		private function onRemoteObjectFault(e:ApplicationRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in ApplicationMediator.as");
			trace("INFO: Falut Code: "+e.arg1);
			trace("INFO: Fault Detail: "+e.arg2);
			trace("INFO: Fault String: "+e.arg3);
			app.showAlertMessage("No Network Connection!!!");
		}
		private function onWarningMessage(e:ApplicationRMIEvent):void
		{
			app.showAlertMessage(e.arg1 as String);
		}
		private function onErrorMessage(e:ApplicationRMIEvent):void
		{
			app.showAlertMessage(e.arg1 as String);
		}
	}
}