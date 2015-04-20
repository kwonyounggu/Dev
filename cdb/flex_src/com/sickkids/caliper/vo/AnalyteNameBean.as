package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.AnalyteNameBean")]
	public class AnalyteNameBean
	{
		public var analyte_name_id:int=0;
		public var analyte_name:String="";
		public var instrument_name_id:int=0;
		public var metric_system:int=0;
		public var value_unit:String="";
		public var v2_chart_path:String="";
		public var v3_chart_path:String="";
		public var v4_chart_path:String="";
		public var v5_chart_path:String="";
			
		public function AnalyteNameBean()
		{
		}
	}
}