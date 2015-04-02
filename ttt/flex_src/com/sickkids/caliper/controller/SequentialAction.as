package com.sickkids.caliper.controller
{
	import mx.core.FlexGlobals;

	public class SequentialAction
	{
		private var _settingsOn:Boolean=false;
		private var _fullScreenOn:Boolean=false;
		public function SequentialAction()
		{
		}
		public function set settingsOn(b:Boolean):void
		{
			_settingsOn=b;
		}
		public function get settingsOn():Boolean
		{
			return _settingsOn;
		}
		
		public function synchronizeThis():void
		{
			if(_settingsOn)
			{
				FlexGlobals.topLevelApplication.videoSwfView.visible=FlexGlobals.topLevelApplication.videoSwfView.includeInLayout=false;
				FlexGlobals.topLevelApplication.adminPanel.visible=FlexGlobals.topLevelApplication.adminPanel.includeInLayout=true;
			}
			else
			{
				FlexGlobals.topLevelApplication.videoSwfView.visible=FlexGlobals.topLevelApplication.videoSwfView.includeInLayout=true;
				FlexGlobals.topLevelApplication.adminPanel.visible=FlexGlobals.topLevelApplication.adminPanel.includeInLayout=false;
			}
		}
		//This will send parameters to the remote clients so that it will be synchronized accordingly
		public function broadcastParams():void
		{
			
		}
	}
}