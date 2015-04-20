package com.sickkids.caliper.model
{
	import com.sickkids.caliper.events.SimpleModelEvent;
	
	import org.robotlegs.mvcs.Actor;
	
	public class SimpleModel extends Actor
	{
		private var _fileName:String;
		
		public function SimpleModel()
		{
			super();
		}
		public function get fileName():String
		{
			return _fileName;
		}
		public function set fileName(value:String):void
		{
			_fileName=value;
			//to the context event bus
			dispatch(new SimpleModelEvent(SimpleModelEvent.FILE_NAME_CHANGE, _fileName));
		}
	}
}