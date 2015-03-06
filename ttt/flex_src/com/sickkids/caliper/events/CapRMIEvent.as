package com.sickkids.caliper.events
{
	import flash.events.Event;
	
	public class CapRMIEvent extends Event
	{
		public static const CHECK_SIGNIN_EVENT:String="CHECK_SIGNIN_EVENT";
		public static const CHECKED_SIGNIN_EVENT:String="CHECKED_SIGNIN_EVENT";
		public static const SAVE_PWD_EVENT:String="SAVE_PWD_EVENT";
		public static const SAVED_PWD_EVENT:String="SAVED_PWD_EVENT";
		public static const SEND_LOGIN_INFO_EVENT:String="SEND_LOGIN_INFO_EVENT";
		public static const SENT_LOGIN_INFO_EVENT:String="SENT_LOGIN_INFO_EVENT";
		public static const GET_ITEM_LIST_EVENT:String="GET_ITEM_LIST_EVENT";
		public static const GOT_ITEM_LIST_EVENT:String="GOT_ITEM_LIST_EVENT";
		public static const GET_CLINICAL_SUMMARY_EVENT:String="GET_CLINICAL_SUMMARY_EVENT";
		public static const GOT_CLINICAL_SUMMARY_EVENT:String="GOT_CLINICAL_SUMMARY_EVENT";
		public static const GET_DIAGNOSES_EVENT:String="GET_DIAGNOSES_EVENT";
		public static const GOT_DIAGNOSES_EVENT:String="GOT_DIAGNOSES_EVENT";
		public static const GET_PROBLEMS_EVENT:String="GET_PROBLEMS_EVENT";
		public static const GOT_PROBLEMS_EVENT:String="GOT_PROBLEMS_EVENT";
		public static const GET_LABOROTORY_EVENT:String="GET_LABOROTORY_EVENT";
		public static const GOT_LABOROTORY_EVENT:String="GOT_LABOROTORY_EVENT";
		public static const GET_VITALSIGN_EVENT:String="GET_VITALSIGN_EVENT";
		public static const GOT_VITALSIGN_EVENT:String="GOT_VITALSIGN_EVENT";
		public static const GET_MEDICATION_EVENT:String="GET_MEDICATION_EVENT";
		public static const GOT_MEDICATION_EVENT:String="GOT_MEDICATION_EVENT";				
		public static const GET_RADIOLOGY_EVENT:String="GET_RADIOLOGY_EVENT";
		public static const GOT_RADIOLOGY_EVENT:String="GOT_RADIOLOGY_EVENT";
		public static const GET_QPOINTS_EVENT:String="GET_QPOINTS_EVENT";
		public static const GOT_QPOINTS_EVENT:String="GOT_QPOINTS_EVENT";
		
		private var _arg1:Object=null;
		private var _arg2:Object=null;
		private var _arg3:Object=null;
		private var _arg4:Object=null;
		private var _arg5:Object=null;
		private var _arg6:Object=null;
		
		public function CapRMIEvent(type:String, arg1:Object=null, arg2:Object=null, arg3:Object=null, arg4:Object=null, arg5:Object=null, arg6:Object=null, bubbles:Boolean=false, cancelable:Boolean=false)
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
			return new CapRMIEvent(type, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, bubbles, cancelable);
		}
	}
}