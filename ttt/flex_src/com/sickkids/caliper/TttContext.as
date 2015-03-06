package com.sickkids.caliper
{	
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.view.ttt.ConductorPanel;
	import com.sickkids.caliper.view.ttt.ConductorPanelMediator;
	import com.sickkids.caliper.view.ttt.ControlBorderContainer;
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
			mediatorMap.mapView(ControlBorderContainer, ControlContainerMediator);
			
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

		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in TttContext.as");
		}
	}
}