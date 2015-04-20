package com.sickkids.caliper
{	
	//import com.sickkids.caliper.components.AlertMsg;
	import com.sickkids.caliper.controller.RawDataRMICommand;
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	import com.sickkids.caliper.service.IRemoteObjectService;
	import com.sickkids.caliper.service.RemoteObjectService;
	import com.sickkids.caliper.view.CustomReferenceIntervalsView;
	import com.sickkids.caliper.view.CustomReferenceIntervalsViewMediator;
	
	import flash.display.DisplayObjectContainer;
	
	import org.robotlegs.mvcs.Context;
	
	public class CaliperContext extends Context
	{
		public function CaliperContext(contextView:DisplayObjectContainer=null, autoStartup:Boolean=true)
		{
			trace("INFO: CaliperContext(..) is called in CaliperContext.as");
			super(contextView, autoStartup);
		}
		override public function startup():void
		{
			trace("INFO: startup() is called in CaliperContext.as");
			/*
			mediatorMap.mapView(ButtonContainerView, ButtonContainerViewMediator);
			mediatorMap.mapView(TextContainerView, TextContainerViewMediator);
			
			commandMap.mapEvent(SimpleAppEvent.CHOOSE_FILE, ChooseFileCommand, SimpleAppEvent);
			commandMap.mapEvent(FileResultEvent.FILE_RESULT, SetSelectedFileCommand, FileResultEvent);
			
			
			injector.mapSingletonOf(IFileService, FileService);
			injector.mapSingleton(SimpleModel);
			*/
			//Caliper from here 
			mediatorMap.mapView(CustomReferenceIntervalsView, CustomReferenceIntervalsViewMediator);
			
			commandMap.mapEvent(RawDataRMIEvent.GET_STRING, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_RAW_DATA_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_CUSTOMIZED_REF_INTERVALS, RawDataRMICommand, RawDataRMIEvent);
			
			injector.mapSingletonOf(IRemoteObjectService, RemoteObjectService);
			
			injector.mapSingleton(CaliperCustomReferenceIntervalModel);
			
			dispatchEvent(new RawDataRMIEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST));//Call this at this stage
		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in CaliperContext.as");
		}
	}
}