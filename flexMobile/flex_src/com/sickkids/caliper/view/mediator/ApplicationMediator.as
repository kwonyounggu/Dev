package com.sickkids.caliper.view.mediator
{
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ApplicationMediator extends Mediator
	{
		[Inject] public var caliperModel:CaliperCustomReferenceIntervalModel;
		[Inject] public var app:pediatricReferenceIntervals;
		
		public function ApplicationMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ApplicationMediator.as");
			this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);	
			//this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ApplicationMediator.as");
			this.removeContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList);
			this.removeContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList);
		}		

		private function onGotInstrumentNameList(e:RawDataRMIEvent):void
		{
			dispatch(new RawDataRMIEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, caliperModel.instrument_name_id, caliperModel.metric_system));//abbott=1, si=1
		}
		private function onGotAnalyteNameList(e:RawDataRMIEvent):void
		{
			app.abbottViewNavigator.enabled=
			app.beckmanViewNavigator.enabled=
			app.searchViewNavigator.enabled=true;
		}
		/*private function onRemoteObjectFault(e:RawDataRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in HomeViewMediator.as");
			
			view.faultCodeLabel.text="Falut Code: "+e.arg1;
			view.faultDetailLabel.text="Fault Detail: "+e.arg2;
			view.faultStringLabel.text="Fault String: "+e.arg3;
			
			view.errorContainer.visible=view.errorContainer.includeInLayout=true;
			
		}*/
	}
}