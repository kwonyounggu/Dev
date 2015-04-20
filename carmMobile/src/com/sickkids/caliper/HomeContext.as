package com.sickkids.caliper
{	

	import com.sickkids.caliper.controller.HomeRMICommand;
	import com.sickkids.caliper.events.HomeRMIEvent;
	import com.sickkids.caliper.model.HomeModel;
	import com.sickkids.caliper.service.HomeRemoteObjectService;
	import com.sickkids.caliper.service.IHomeRemoteObjectService;
	import com.sickkids.caliper.view.HomeView;
	import com.sickkids.caliper.view.mediator.HomeViewMediator;
	
	import flash.display.DisplayObjectContainer;
	
	import org.robotlegs.mvcs.Context;
	
	public class HomeContext extends Context
	{
		public function HomeContext(contextView:DisplayObjectContainer=null, autoStartup:Boolean=true)
		{
			trace("INFO: HomeContext(..) is called in HomeContext.as");
			super(contextView, autoStartup);
		}
		override public function startup():void
		{
			trace("INFO: startup() is called in HomeContext.as");
			injector.mapSingleton(HomeModel);
			//map views here
			mediatorMap.mapView(HomeView, HomeViewMediator);
			//map events
			commandMap.mapEvent(HomeRMIEvent.GET_APP_LIST, HomeRMICommand, HomeRMIEvent);
			
			injector.mapSingletonOf(IHomeRemoteObjectService, HomeRemoteObjectService);
			
			dispatchEvent(new HomeRMIEvent(HomeRMIEvent.GET_APP_LIST));//Call this at this stage
		}
		override public function shutdown():void
		{
			trace("INFO: shutdown() is called in HomeContext.as");
		}
	}
}