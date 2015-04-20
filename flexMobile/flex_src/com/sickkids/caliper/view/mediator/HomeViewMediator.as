package com.sickkids.caliper.view.mediator
{
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.view.HomeView;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class HomeViewMediator extends Mediator
	{
		[Inject] public var view:HomeView;
		
		public function HomeViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in HomeViewMediator.as");
			this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in HomeViewMediator.as");
		}	
		
		private function onRemoteObjectFault(e:RawDataRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in HomeViewMediator.as");
			
			view.faultCodeLabel.text="Falut Code: "+e.arg1;
			view.faultDetailLabel.text="Fault Detail: "+e.arg2;
			view.faultStringLabel.text="Fault String: "+e.arg3;
			
			view.errorContainer.visible=view.errorContainer.includeInLayout=true;
			
		}
	}
}