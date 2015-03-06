package com.sickkids.caliper.model
{		
	import com.ericfeminella.collections.HashMap;
	import com.ericfeminella.collections.IMap;

	import flash.net.SharedObject;
	
	import org.robotlegs.mvcs.Actor;
	
	[Bindable]
	public class HomeModel extends Actor
	{	
		public var appMap:IMap=null;;
		
		public function HomeModel()
		{		
			initialize();
			super();
		}
		
		private function initialize():void
		{
			//Retrieve the existing appMap object stored through getAppList <- onOperationResult(...) <- HomeRemoteObjectService.java
			var lso:SharedObject=SharedObject.getLocal("com.sickkids.caliper.model.appMap");
			var temp:HashMap=lso.data.appMap as HashMap;

			if(temp) 
			{
				appMap=lso.data.appMap as HashMap;
				///trace("size of appMap="+appMap.size());
				//Alert.show("size="+appMap.size());
			}
			else appMap=new HashMap();
		}
	}
}