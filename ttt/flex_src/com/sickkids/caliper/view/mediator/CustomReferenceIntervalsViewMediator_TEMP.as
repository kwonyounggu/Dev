package com.sickkids.caliper.view.mediator
{

	import com.sickkids.caliper.components.AlertMsg;
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	import com.sickkids.caliper.vo.AnalyteNameBean;
	import com.sickkids.caliper.vo.InstrumentBean;
	
	import flash.events.MouseEvent;
	
	import mx.charts.chartClasses.Series;
	import mx.collections.ArrayCollection;
	import mx.events.ItemClickEvent;
	
	import spark.events.IndexChangeEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class CustomReferenceIntervalsViewMediator extends Mediator
	{
		[Inject] public var view:CustomReferenceIntervalsView;
		[Inject] public var caliperModel:CaliperCustomReferenceIntervalModel;
		//public var alertMsg:AlertMsg=null;
		
		public function CustomReferenceIntervalsViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in CustomReferenceIntervalsViewMediator.as");
			//this.addViewListener(SimpleAppEvent.CHOOSE_FILE, dispatch, SimpleAppEvent);//redirect the event to the event bus in the context
			//this.addViewListener(RawDataRMIEvent.GET_STRING, dispatch, RawDataRMIEvent);
			
			view.ageFromList.dataProvider=caliperModel.ageList;
			view.ageToList.dataProvider=caliperModel.ageList;
			
			view.instrumentList.addEventListener(IndexChangeEvent.CHANGE, onInstrumentList_changeHandler);
			view.analyteList.addEventListener(IndexChangeEvent.CHANGE, onAnalyteList_changeHandler);
			view.analyteList.labelFunction=analyteLabelFunction;
			
			view.gender.addEventListener(ItemClickEvent.ITEM_CLICK, onGender_itemClickHandler);
			view.metricSystem.addEventListener(ItemClickEvent.ITEM_CLICK, onMetric_itemClickHandler);
			view.submitButton.addEventListener(MouseEvent.CLICK, onSubmitButton_clickHandler);
			
			view.plotFemaleId.dataFunction=plotDataFunction;
			view.plotMaleId.dataFunction=plotDataFunction;
			
			this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_RAW_DATA_LIST, onGotRawDataList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
		}
		private function analyteLabelFunction(item:Object):String
		{								
			var pos:int=String(item.analyte_name).lastIndexOf("\u0028");//\u0028 is left paranthesis
			return (pos!=-1) ? String(item.analyte_name).substr(0, pos) : item.analyte_name;				
		}
		private function plotDataFunction(series:Series, item:Object, fieldName:String):Object 
		{
			//if(caliperModel.metric_system==1) 
			if(series.id=="plotFemaleId" && item.gender==1) //female
			{
				if(fieldName=="xValue") return item.age;
				else if(fieldName=="yValue") 
					return (caliperModel.metric_system==1 ? (item.si_unit_data==-1 ? null: item.si_unit_data) : (item.conventional_unit_data==-1 ? null : item.conventional_unit_data));
			}
			else if(series.id=="plotMaleId" && item.gender==2) //female
			{
					if(fieldName=="xValue") return item.age;
					else if(fieldName=="yValue") 
						return (caliperModel.metric_system==1 ? (item.si_unit_data==-1 ? null: item.si_unit_data) : (item.conventional_unit_data==-1 ? null : item.conventional_unit_data));
			}
			return null;
		}
		private function onGotInstrumentNameList(e:RawDataRMIEvent):void
		{
			view.instrumentList.dataProvider=caliperModel.instrumentNameList;
			//now call for the list of analytes
			dispatch(new RawDataRMIEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, caliperModel.instrument_name_id, caliperModel.metric_system));//abbott=1, si=1
		}
		private function onGotAnalyteNameList(e:RawDataRMIEvent):void
		{
			trace("INFO: onGotAnalyteNameList() is called in CustomReferenceIntervalsViewMediator.as");
			view.analyteList.dataProvider=caliperModel.analyteNameList;	
			
			view.submitButton.enabled=(caliperModel.analyteNameList!=null && caliperModel.instrumentNameList!=null &&
									   caliperModel.analyteNameList.length>0  && caliperModel.instrumentNameList.length>0);
		}
		private function onGotRawDataList(e:RawDataRMIEvent):void
		{
			trace("INFO: onGotRawDataList() is called in CustomReferenceIntervalsViewMediator.as, size="+caliperModel.rawDataList.length);
			view.xAxisId.minimum=caliperModel.age_from;
			view.xAxisId.maximum=caliperModel.age_to;
			view.yAxisId.title="Concentration ["+(caliperModel.rawDataList.length>0? (caliperModel.metric_system==1? caliperModel.rawDataList.getItemAt(0).si_unit:caliperModel.rawDataList.getItemAt(0).conventional_unit):"")+"]";
			view.plotChart.dataProvider=caliperModel.rawDataList;
			view.selectedAnalyteName.text=analyteLabelFunction(view.analyteList.selectedItem);
		}
		private function onRemoteObjectFault(e:RawDataRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in CustomReferenceIntervalsViewMediator.as");
			
			view.faultCodeLabel.text="Falut Code: "+e.arg1;
			view.faultDetailLabel.text="Fault Detail: "+e.arg2;
			view.faultStringLabel.text="Fault String: "+e.arg3;
			
			view.errorContainer.visible=view.errorContainer.includeInLayout=true;
			
		}
		protected function onInstrumentList_changeHandler(event:IndexChangeEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: onInstrumentList_changeHandler(event:IndexChangeEvent) is called  in CustomReferenceIntervalsViewMediator.as");
			caliperModel.instrument_name_id=InstrumentBean(view.instrumentList.selectedItem).instrument_name_id;
		}
		
		protected function onAnalyteList_changeHandler(event:IndexChangeEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: onAnalyteList_changeHandler(event:IndexChangeEvent) is called  in CustomReferenceIntervalsViewMediator.as");
			caliperModel.analyte_name_id=AnalyteNameBean(view.analyteList.selectedItem).analyte_name_id;
		}
		
		protected function onGender_itemClickHandler(e:ItemClickEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: onGender_itemClickHandler(e:ItemClickEvent) is called  in CustomReferenceIntervalsViewMediator.as");
			caliperModel.gender_id=e.currentTarget.selectedValue as Number;
		}
		
		protected function onMetric_itemClickHandler(e:ItemClickEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: onMetric_itemClickHandler(e:ItemClickEvent) is called  in CustomReferenceIntervalsViewMediator.as");
			caliperModel.metric_system=e.currentTarget.selectedValue as Number;
		}
		protected function setAgeRange():void
		{
			var index_from:int=0;
			var index_to:int=0;
			if(view.ageFromList.selectedIndex>view.ageToList.selectedIndex)
			{
				index_from=view.ageToList.selectedIndex;
				index_to=view.ageFromList.selectedIndex;
			}
			else
			{
				index_from=view.ageFromList.selectedIndex;
				index_to=view.ageToList.selectedIndex;
			}
			//caliperModel.age_from is inclusive, caliperModel.age_to is exclusibe
			if(index_from==1) caliperModel.age_from=0.09;
			else if(index_from>1) caliperModel.age_from=(index_from-1) as Number;
			else caliperModel.age_from=0;
			
			if(index_to==0) caliperModel.age_to=0.09;
			if(index_to==1) caliperModel.age_to=1;
			else if(index_to>1) caliperModel.age_to=index_to as Number;
		}
		protected function onSubmitButton_clickHandler(event:MouseEvent):void
		{
			setAgeRange();
			// TODO Auto-generated method stub
			trace("INFO: onSubmitButton_clickHandler(event:MouseEvent) is called  in CustomReferenceIntervalsViewMediator.as");
			trace("instrument id="+caliperModel.instrument_name_id);
			trace("analyte id="+caliperModel.analyte_name_id);
			trace("metric id="+caliperModel.metric_system);
			trace("gender id="+caliperModel.gender_id);
			trace("from age id="+caliperModel.age_from);
			trace("to age id="+caliperModel.age_to);
			
			view.errorContainer.visible=view.errorContainer.includeInLayout=false;//hide
			
			dispatch(new RawDataRMIEvent(RawDataRMIEvent.GET_RAW_DATA_LIST, 
										 caliperModel.instrument_name_id, 
										 caliperModel.analyte_name_id,
										 caliperModel.age_from,
										 caliperModel.age_to,
										 caliperModel.gender_id,
										 caliperModel.metric_system));
		}
	}
}