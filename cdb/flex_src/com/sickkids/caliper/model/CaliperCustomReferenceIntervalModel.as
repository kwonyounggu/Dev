package com.sickkids.caliper.model
{
	import com.sickkids.caliper.vo.CustomizedRefIntervalInfoBean;
	
	import mx.collections.ArrayCollection;
	
	import org.robotlegs.mvcs.Actor;
	
	public class CaliperCustomReferenceIntervalModel extends Actor
	{
		public static var MAX_OUT_RECORDS:uint=10;
		public var instrumentNameList:ArrayCollection=null;
		public var analyteNameList:ArrayCollection=null;
		public var rawDataList:ArrayCollection=null;
		public var refIntervals:CustomizedRefIntervalInfoBean=null;
		
		public var metric_system:int=1;//default 1 for SI, 2 for Conventional
		public var instrument_name_id:int=1; //1 for Abbott, 2
		public var analyte_name_id:int=1;
		public var gender_id:int=3; //default both=3
		public var age_from:Number=0.0;
		public var age_to:Number=0.0;
				
		public var ageListArray:Array=["0-30D","31D-12M","1 YR","2 YRS","3 YRS","4 YRS","5 YRS","6 YRS","7 YRS","8 YRS","9 YRS","10 YRS","11 YRS","12 YRS","13 YRS","14 YRS","15 YRS","16 YRS","17 YRS","18 YRS"];
		public var ageList:ArrayCollection=new ArrayCollection(ageListArray);
		
		public function CaliperCustomReferenceIntervalModel()
		{
			super();
		}
	}
}