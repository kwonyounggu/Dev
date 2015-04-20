package com.sickkids.caliper
{	
	import com.sickkids.caliper.controller.RawDataRMICommand;
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	import com.sickkids.caliper.model.CapModel;
	import com.sickkids.caliper.service.IRemoteObjectService;
	import com.sickkids.caliper.service.RemoteObjectService;
	import com.sickkids.caliper.view.HomeView;
	import com.sickkids.caliper.view.cap.LoginView;
	import com.sickkids.caliper.view.cap.LoginViewMediator;
	import com.sickkids.caliper.view.mediator.ApplicationMediator;
	import com.sickkids.caliper.view.mediator.HomeViewMediator;
	
	import flash.display.DisplayObjectContainer;
	
	import org.robotlegs.mvcs.Context;
	
	public class TttContext extends Context
	{
		public function TttContext(contextView:DisplayObjectContainer=null, autoStartup:Boolean=true)
		{
			trace("INFO: TttContext(..) is called in TttContext.as");
			super(contextView, autoStartup);
		}
		override public function startup():void
		{
			trace("INFO: startup() is called in TttContext.as");

			injector.mapSingleton(CapModel);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.LoginView, com.sickkids.caliper.view.cap.LoginViewMediator);

			//Caliper from here 
			//mediatorMap.mapView(CustomReferenceIntervalsView, CustomReferenceIntervalsViewMediator);
			/*injector.mapSingleton(CaliperCustomReferenceIntervalModel);
			
			mediatorMap.mapView(pediatricReferenceIntervals, ApplicationMediator);
			mediatorMap.mapView(HomeView, HomeViewMediator);
			//mediatorMap.mapView(SearchView, SearchViewMediator);
			commandMap.mapEvent(RawDataRMIEvent.GET_STRING, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_ANALYTE_NAME_LIST, RawDataRMICommand, RawDataRMIEvent);
			commandMap.mapEvent(RawDataRMIEvent.GET_RAW_DATA_LIST, RawDataRMICommand, RawDataRMIEvent);
			
			injector.mapSingletonOf(IRemoteObjectService, RemoteObjectService);
						
			dispatchEvent(new RawDataRMIEvent(RawDataRMIEvent.GET_STRING, "HELLO"));//Call this at this stage
			//dispatchEvent(new RawDataRMIEvent(RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST));//Call this at this stage
			*/
		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in TttContext.as");
		}
	}
}