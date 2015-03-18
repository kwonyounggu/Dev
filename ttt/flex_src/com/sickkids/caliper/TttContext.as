package com.sickkids.caliper
{	
	import com.sickkids.caliper.controller.TttNetCallCommand;
	import com.sickkids.caliper.controller.TttNetConnectionCommand;
	import com.sickkids.caliper.events.TttNetCallEvent;
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	import com.sickkids.caliper.service.NetConnectionService;
	import com.sickkids.caliper.view.ttt.AdminPanel;
	import com.sickkids.caliper.view.ttt.AdminPanelMediator;
	import com.sickkids.caliper.view.ttt.ConductorPanel;
	import com.sickkids.caliper.view.ttt.ConductorPanelMediator;
	import com.sickkids.caliper.view.ttt.ControlContainer;
	import com.sickkids.caliper.view.ttt.ControlContainerMediator;
	
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

			injector.mapSingleton(TttModel);
			mediatorMap.mapView(ConductorPanel, ConductorPanelMediator);
			mediatorMap.mapView(ControlContainer, ControlContainerMediator);
			mediatorMap.mapView(AdminPanel, AdminPanelMediator);
			
			//mediatorMap.mapView(ControlBorderContainer, ControlContainerMediator);
			/*mediatorMap.mapView(com.sickkids.caliper.view.cap.LoginView, com.sickkids.caliper.view.cap.LoginViewMediator);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.GetLoginInfoView, com.sickkids.caliper.view.cap.GetLoginInfoViewMediator);
			mediatorMap.mapView(com.sickkids.caliper.view.cap.ReviewView, com.sickkids.caliper.view.cap.ReviewViewMediator);			
			
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
			*/
			
			commandMap.mapEvent(TttNetConnectionEvent.ROOM_CONNECT_EVENT, TttNetConnectionCommand, TttNetConnectionEvent);
			commandMap.mapEvent(TttNetCallEvent.CALL_TO_METHOD_EVENT, TttNetCallCommand, TttNetCallEvent);
			trace("INFO: injector.mapSingletonOf(INetConnectionService, NetConnectionService) is set in TttContext.as");
			injector.mapSingletonOf(INetConnectionService, NetConnectionService);

		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in TttContext.as");
		}
	}
}