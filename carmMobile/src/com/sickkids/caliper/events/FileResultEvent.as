package com.sickkids.caliper.events
{
	import flash.events.Event;
	
	public class FileResultEvent extends Event
	{
		public static const FILE_RESULT:String="fileResult";
		private var _fileName:String;
		
		public function FileResultEvent(type:String, fileName:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			_fileName=fileName;
		}
		public function get fileName():String
		{
			return _fileName;
		}
		
	}
}