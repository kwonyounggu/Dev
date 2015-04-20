package com.sickkids.caliper
{	

	import com.sickkids.caliper.controller.ApplicationRMICommand;
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.model.ApplicationModel;
	import com.sickkids.caliper.service.ApplicationRemoteObjectService;
	import com.sickkids.caliper.service.IApplicationRemoteObjectService;
	import com.sickkids.caliper.view.HomeView;
	import com.sickkids.caliper.view.mediator.ApplicationMediator;
	import com.sickkids.caliper.view.mediator.HomeViewMediator;
	
	import flash.display.DisplayObjectContainer;
	
	import org.robotlegs.mvcs.Context;
	
	public class ApplicationContext extends Context
	{
		public function ApplicationContext(contextView:DisplayObjectContainer=null, autoStartup:Boolean=true)
		{
			trace("INFO: ApplicationContext(..) is called in ApplicationContext.as");
			super(contextView, autoStartup);
		}
		override public function startup():void
		{
			trace("INFO: startup() is called in ApplicationContext.as");
			injector.mapSingleton(ApplicationModel);
			//map views here
			mediatorMap.mapView(csrMobile, ApplicationMediator, null, true, false);
			mediatorMap.mapView(HomeView, HomeViewMediator);
			//map events
			commandMap.mapEvent(ApplicationRMIEvent.GET_APP_LIST_EVENT, ApplicationRMICommand, ApplicationRMIEvent);
			
			injector.mapSingletonOf(IApplicationRemoteObjectService, ApplicationRemoteObjectService);
			
			//Temporay blocking this statment
			//dispatchEvent(new ApplicationRMIEvent(ApplicationRMIEvent.GET_APP_LIST_EVENT));//Call this at this stage
		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in ApplicationContext.as");
		}
	}
}