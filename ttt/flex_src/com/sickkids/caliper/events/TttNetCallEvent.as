package com.sickkids.caliper.events
{
	import flash.events.Event;
	import flash.net.Responder;
	
	public class TttNetCallEvent extends Event
	{
		public static const CALL_TO_METHOD_EVENT:String="CALL_TO_METHOD_EVENT";
		public static const LOG_CLIENTS_RETURNED_EVENT:String="LOG_CLIENTS_RETURNED_EVENT";
		
		private var _res:Responder=null;
		private var _method:Object=null;
		private var _arg1:Object=null;
		private var _arg2:Object=null;
		private var _arg3:Object=null;
		private var _arg4:Object=null;
		private var _arg5:Object=null;
		private var _arg6:Object=null;
		
		public function TttNetCallEvent(type:String, res:Responder, method:Object, arg1:Object=null, arg2:Object=null, arg3:Object=null, arg4:Object=null, arg5:Object=null, arg6:Object=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			_res=res;
			_method=method;
			_arg1=arg1;
			_arg2=arg2;
			_arg3=arg3;
			_arg4=arg4;
			_arg5=arg5;
			_arg6=arg6;
			
			super(type, bubbles, cancelable);
		}
		
		public function get res():Responder { return _res;}
		public function get method():Object { return _method;}
		public function get arg1():Object { return _arg1;}
		public function get arg2():Object { return _arg2;}
		public function get arg3():Object { return _arg3;}
		public function get arg4():Object { return _arg4;}
		public function get arg5():Object { return _arg5;}
		public function get arg6():Object { return _arg6;}
		
		override public function clone():Event
		{
			return new TttNetCallEvent(type, _res, _method, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, bubbles, cancelable);
		}
	}
}


