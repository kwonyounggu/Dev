package com.sickkids.caliper.events
{
	import flash.events.Event;
	
	public class SimpleModelEvent extends Event
	{
		public static const FILE_NAME_CHANGE:String="fileNameChange";
		private var _value:String;
		
		public function SimpleModelEvent(type:String, value:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			_value=value;
		}
		
		public function get value():String
		{
			return _value;
		}
		
		override public function clone():Event
		{
			return new SimpleModelEvent(type, _value, bubbles, cancelable);
		}
	}
}