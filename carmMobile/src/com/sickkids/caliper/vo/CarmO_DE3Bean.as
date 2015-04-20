package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DE3Bean")]
	public class CarmO_DE3Bean
	{
		public var DE3_ID:int=0;
		public var SITE:String="";
		public var PAT_NO:String="";
		public var EVENT_NO:int=0;
		public var WEEK_NO:int=0;
		
		
		public var hour:int=-10000;
		public var route:String="";
		public var medication:String="";
		
		public var enteredBy:String="";
		public var updatedOn:Date=null;
		
		public function CarmO_DE3Bean()
		{
		}
	}
}