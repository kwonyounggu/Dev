package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DEBean")]
	public class CarmO_DEBean
	{
		private var DE_ID:int=0;
		private var SITE:String="";
		private var PAT_NO:String="";
		private var EVENT_NO:int=0;
		private var WEEK_NO:int=0;
		
		
		private var hrPreevent:int=-10000;
		private var heartRate:Number=-10000;
		private var compression:String="";
		private var respRate:Number=-10000;
		private var saturation:Number=-10000;	
		private var systolicBp:Number=-10000;
		private var diastolicBp:Number=-10000;
		private var capillaryRefill:int=-10000;
		private var temperature:Number=-10000;
		private var consciousnessAvpu:String="";	
		private var consciousnessGcs:Number=-10000;
		private var pupils:String="";
		private var o2Method:String="";
		private var o2AmountUnit:String="";
		private var o2Amount:Number=-10000;
		private var respEffort:String="";
		private var stridor:String="";
		private var totalIvFluid:Number=-10000;
		private var urineOutput:Number=-10000;	
		private var urineYesNo:String="";
		private var narcoticInfusion:String="";
		private var physicianReview:String="";
		private var statCall:String="";
		private var nursePatientRatio:String="";
		private var parentPresent:String="";
		
		private var enteredBy:String="";
		private var updatedOn:Date=null;
		
		public function CarmO_DEBean()
		{
		}
	}
}