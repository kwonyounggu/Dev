package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.RawDataVersionNBean")]
	public class RawDataVersionNBean
	{
		
		public var  whole_record_index:Number=0L;
		public var  long participant_id:Number=0L;
		public var  gender:int=0;
		public var  double age::Number=0.0d;
		public var  double si_unit_data::Number=0.0d;
		public var  double conventional_unit_data:Number=0.0d;
		public var  si_unit:String="";
		public var  conventional_unit:String="";
		public var  ethnic_group:int=0;
		public var  instrument_name_id:int=0;
		public var  analyte_name_id:int=0;
		public var  version:int=0;
		public var  insertion_time:Date=null;
			
		public function RawDataVersionNBean()
		{
		}
	}
}