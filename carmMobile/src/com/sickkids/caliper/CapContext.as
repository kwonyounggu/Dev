package com.sickkids.caliper
{	
	import com.sickkids.caliper.controller.CapRMICommand;
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.model.CapModel;
	import com.sickkids.caliper.service.CapRemoteObjectService;
	import com.sickkids.caliper.service.ICapRemoteObjectService;
	import com.sickkids.caliper.view.cap.LoginView;
	import com.sickkids.caliper.view.cap.LoginViewMediator;
	
	import flash.display.DisplayObjectContainer;
	
	import org.robotlegs.mvcs.Context;
	
	public class CapContext extends Context
	{
		public function CapContext(contextView:DisplayObjectContainer=null, autoStartup:Boolean=true)
		{
			trace("INFO: CapContext(..) is called in CapContext.as");
			super(contextView, autoStartup);
		}
		override public function startup():void
		{
			trace("INFO: startup() is called in CapContext.as");

			injector.mapSingleton(CapModel);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.LoginView, com.sickkids.caliper.view.cap.LoginViewMediator);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.GetLoginInfoView, com.sickkids.caliper.view.cap.GetLoginInfoViewMediator);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.ReviewView, com.sickkids.caliper.view.cap.ReviewViewMediator);
			//mediatorMap.mapView(com.sickkids.caliper.components.ItemCallout, com.sickkids.caliper.view.cap.ItemCallOutMediator);
			
			commandMap.mapEvent(CapRMIEvent.CHECK_SIGNIN_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.SAVE_PWD_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.SEND_LOGIN_INFO_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_ITEM_LIST_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_DIAGNOSES_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_PROBLEMS_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_LABOROTORY_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_VITALSIGN_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_MEDICATION_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_RADIOLOGY_EVENT, CapRMICommand, CapRMIEvent);
			commandMap.mapEvent(CapRMIEvent.GET_QPOINTS_EVENT, CapRMICommand, CapRMIEvent);
			
			injector.mapSingletonOf(ICapRemoteObjectService, CapRemoteObjectService);
			//We also want the injector itself to be available anywhere in our application:			
			
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
			trace("INFO: shutdown() is called in CapContext.as");
		}
	}
}