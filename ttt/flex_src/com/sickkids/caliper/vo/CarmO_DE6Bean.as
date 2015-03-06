package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DE6Bean")]
	public class CarmO_DE6Bean
	{
		public var DE6_ID:int=0;
		public var SITE:String="";
		public var PAT_NO:String="";
		public var EVENT_NO:int=0;
		public var WEEK_NO:int=0;
				
		public var chestImaging:String="";
		public var cnsImaging:String="";
		public var abdominalImaging:String="";
		public var otherImaging:String="";
		
		
		public var enteredBy:String="";
		public var updatedOn:Date=null;
		
		public function CarmO_DE6Bean()
		{
		}
	}
}