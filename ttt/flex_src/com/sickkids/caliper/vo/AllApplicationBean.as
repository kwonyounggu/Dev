package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.AllApplicationBean")]
	public class AllApplicationBean
	{
		public var app_id:int=0;
		public var app_id_revision:int=0;
		public var purpose_of_app:String="";
		public var app_symbol:String="";
		public var number_of_groups:int=0;
		public var reviewers_per_item:int=0;
		public var reviewer_assignment_type:String="";
		public var confirmatory:Boolean=true;
		public var disagreement_process:String="";
		public var creation_time:Date=null;
		public var creator_id:String="";
		public var remarks:String="";
			
		public function AllApplicationBean()
		{
		}
	}
}