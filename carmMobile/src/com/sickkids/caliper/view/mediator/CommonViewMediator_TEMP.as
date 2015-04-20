package com.sickkids.caliper.view.mediator
{
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class CommonViewMediator extends Mediator
	{
		[Inject] public var abbottHomeView:AbbottHomeView;
		//[Inject] public var beckmanHomeView:BeckmanHomeView;
		
		[Inject] public var caliperModel:CaliperCustomReferenceIntervalModel;
		public function CommonViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in CommonViewMediator.as");
		}
		private function onGotAnalyteNameList(e:RawDataRMIEvent):void
		{
			trace("INFO: onGotAnalyteNameList() is called in CommonViewMediator.as");
			//view.analyteList.dataProvider=caliperModel.analyteNameList;	
			
			//view.submitButton.enabled=(caliperModel.analyteNameList!=null && caliperModel.instrumentNameList!=null &&
			//	caliperModel.analyteNameList.length>0  && caliperModel.instrumentNameList.length>0);
		}
	}
}