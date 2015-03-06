package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmClinicalSummaryEditBean")]
	public class CarmClinicalSummaryBean
	{
		public var carm_id:int=0;
		public var old_clinical_summary:String="";
		public var new_clinical_summary:String="";
		public var edited:Boolean=false;
		public var edit_date:Date=null;
		public var editor_id:String="";
		
		public function CarmClinicalSummaryBean()
		{
		}
	}
}