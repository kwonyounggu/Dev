package com.sickkids.caliper.events
{
	import flash.events.Event;
	
	public class SimpleAppEvent extends Event
	{
		public static const CHOOSE_FILE:String="chooseFile";
		
		public function SimpleAppEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			trace("INFO: SimpleAppEvent(...) is called in SimpleAppEvent.as");
			super(type, bubbles, cancelable);
		}
		override public function clone():Event
		{
			return new SimpleAppEvent(type, bubbles, cancelable);
		}
	}
}