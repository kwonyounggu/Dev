package com.sickkids.caliper.model
{		
	import com.ericfeminella.collections.HashMap;
	import com.sickkids.caliper.components.AppCommon;
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import org.robotlegs.mvcs.Actor;
	
	[Bindable]
	public class ApplicationModel extends Actor
	{	
		public var appMap:HashMap=null;
		public var appList:ArrayCollection=null;
		
		public function ApplicationModel()
		{		
			initialize();
			super();
		}
		/*
		private function initialize():void
		{
			//Retrieve the existing appMap object stored through getAppList <- onOperationResult(...) <- HomeRemoteObjectService.java
			trace("INFO: initialize() of ApplicationModel.as is started");
			registerClassAlias("com.ericfeminella.collections.HashMap", HashMap);
			if(AppCommon.app_sharedAppListObject.data.appMap!=null) 
				appMap=AppCommon.app_sharedAppListObject.data.appMap as HashMap;
			else 
				appMap=new HashMap();

			trace("INFO: initialize() of ApplicationModel.as is ended ...appList.size="+(appMap==null? 'none': appMap.size()));
		}*/
		private function initialize():void
		{
			//Retrieve the existing appMap object stored through getAppList <- onOperationResult(...) <- HomeRemoteObjectService.java
			trace("INFO: initialize() of ApplicationModel.as is started");
			appList=AppCommon.app_sharedAppListObject.data.appList;
			trace("INFO: initialize() of ApplicationModel.as is ended ...appList.size="+(appList==null? 'none': appList.length));
		}
	}
}