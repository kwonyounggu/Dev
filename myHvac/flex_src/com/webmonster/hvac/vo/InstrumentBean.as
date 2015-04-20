package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.InstrumentBean")]
	public class InstrumentBean
	{
		public var instrument_name_id:int;
		public var instrument_name:String;
		public var instrument_link:String;
		
		public function InstrumentBean()
		{
		}
	}
}