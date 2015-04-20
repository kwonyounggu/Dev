package com.sickkids.caliper
{	
	import com.sickkids.caliper.controller.RawDataRMICommand;
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	import com.sickkids.caliper.service.IRemoteObjectService;
	import com.sickkids.caliper.service.RemoteObjectService;
	import com.sickkids.caliper.view.HomeView;
	import com.sickkids.caliper.view.mediator.ApplicationMediator;
	import com.sickkids.caliper.view.mediator.HomeViewMediator;
	
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
			
			//Caliper from here 
			//mediatorMap.mapView(CustomReferenceIntervalsView, CustomReferenceIntervalsViewMediator);
			injector.mapSingleton(CaliperCustomReferenceIntervalModel);
			
			mediatorMap.mapView(pediatricReferenceIntervals, ApplicationMediator);
			mediatorMap.mapView(HomeView, HomeViewMediator);
			//mediatorMap.mapView(SearchView, SearchViewMediator);
			commandMap.mapEvent(RawDataRMIEvent.GET_STRING, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_RAW_DATA_LIST, RawDataRMICommand, RawDataRMIEvent);
			
			injector.mapSingletonOf(IRemoteObjectService, RemoteObjectService);
						
			dispatchEvent(new RawDataRMIEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST));//Call this at this stage
		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in CaliperContext.as");
		}
	}
}