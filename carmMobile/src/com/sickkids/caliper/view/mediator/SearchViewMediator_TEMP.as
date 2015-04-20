package com.sickkids.caliper.view
{
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	
	import mx.charts.chartClasses.Series;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class SearchViewMediator extends Mediator
	{
		[Inject] public var searchView:SearchView;
		[Inject] public var caliperModel:CaliperCustomReferenceIntervalModel;
		
		public function SearchViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in SearchViewMediator.as");
			//this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);
			//this.addContextListener(RawDataRMIEvent.GOT_RAW_DATA_LIST, onGotRawDataList, RawDataRMIEvent);
			//this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
			
			if(caliperModel.analyteNameList==null) onGotInstrumentNameList(null);
				this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in SearchViewMediator.as");
		}		
		private function analyteLabelFunction(item:Object):String
		{								
			var pos:int=String(item.analyte_name).lastIndexOf("\u0028");//\u0028 is left paranthesis
			return (pos!=-1) ? String(item.analyte_name).substr(0, pos) : item.analyte_name;				
		}
		
		private function onGotInstrumentNameList(e:RawDataRMIEvent):void
		{
			//view.instrumentList.dataProvider=caliperModel.instrumentNameList;
			//now call for the list of analytes
			dispatch(new RawDataRMIEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, caliperModel.instrument_name_id, caliperModel.metric_system));//abbott=1, si=1
		}
		private function onGotAnalyteNameList(e:RawDataRMIEvent):void
		{
			trace("INFO: onGotAnalyteNameList() is called in SearchViewMediator.as");
			searchView.analyteList.dataProvider=caliperModel.analyteNameList;	
			
			//view.submitButton.enabled=(caliperModel.analyteNameList!=null && caliperModel.instrumentNameList!=null &&
			//	caliperModel.analyteNameList.length>0  && caliperModel.instrumentNameList.length>0);
		}
		private function onGotRawDataList(e:RawDataRMIEvent):void
		{
			/*trace("INFO: onGotRawDataList() is called in CustomReferenceIntervalsViewMediator.as, size="+caliperModel.rawDataList.length);
			view.xAxisId.minimum=caliperModel.age_from;
			view.xAxisId.maximum=caliperModel.age_to;
			view.yAxisId.title="Concentration ["+(caliperModel.rawDataList.length>0? (caliperModel.metric_system==1? caliperModel.rawDataList.getItemAt(0).si_unit:caliperModel.rawDataList.getItemAt(0).conventional_unit):"")+"]";
			view.plotChart.dataProvider=caliperModel.rawDataList;
			view.selectedAnalyteName.text=analyteLabelFunction(view.analyteList.selectedItem);*/
		}
		private function onRemoteObjectFault(e:RawDataRMIEvent):void
		{
			/*trace("INFO: onRemoteObjectFault() is called in CustomReferenceIntervalsViewMediator.as");
			
			view.faultCodeLabel.text="Falut Code: "+e.arg1;
			view.faultDetailLabel.text="Fault Detail: "+e.arg2;
			view.faultStringLabel.text="Fault String: "+e.arg3;
			
			view.errorContainer.visible=view.errorContainer.includeInLayout=true;*/
			
		}
	}
}