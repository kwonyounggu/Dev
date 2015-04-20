package com.sickkids.caliper.events
{
	import flash.events.Event;
	
	public class RawDataRMIEvent extends Event
	{
		
		public static const GET_STRING:String="GET_STRING";
		public static const GET_INSTRUMENT_NAME_LIST:String="GET_INSTRUMENT_NAME_LIST";
		public static const GOT_INSTRUMENT_NAME_LIST:String="GOT_INSTRUMENT_NAME_LIST";
		public static const GET_ANALYTE_NAME_LIST:String="GET_ANALYTE_NAME_LIST";
		public static const GOT_ANALYTE_NAME_LIST:String="GOT_ANALYTE_NAME_LIST";
		public static const GET_RAW_DATA_LIST:String="GET_RAW_DATA_LIST";
		public static const GOT_RAW_DATA_LIST:String="GOT_RAW_DATA_LIST";
		
		public static const REMOTE_OBJECT_FAULT_EVENT:String="REMOTE_OBJECT_FAULT_EVENT";
		
		private var _arg1:Object=null;
		private var _arg2:Object=null;
		private var _arg3:Object=null;
		private var _arg4:Object=null;
		private var _arg5:Object=null;
		private var _arg6:Object=null;
		
		public function RawDataRMIEvent(type:String, arg1:Object=null, arg2:Object=null, arg3:Object=null, arg4:Object=null, arg5:Object=null, arg6:Object=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			_arg1=arg1;
			_arg2=arg2;
			_arg3=arg3;
			_arg4=arg4;
			_arg5=arg5;
			_arg6=arg6;
			
			super(type, bubbles, cancelable);
		}
		
		public function get arg1():Object { return _arg1;}
		public function get arg2():Object { return _arg2;}
		public function get arg3():Object { return _arg3;}
		public function get arg4():Object { return _arg4;}
		public function get arg5():Object { return _arg5;}
		public function get arg6():Object { return _arg6;}
		
		override public function clone():Event
		{
			return new RawDataRMIEvent(type, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, bubbles, cancelable);
		}
	}
}