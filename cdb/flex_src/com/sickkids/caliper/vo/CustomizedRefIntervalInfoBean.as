package com.sickkids.caliper.vo
{
	import mx.collections.ArrayCollection;

	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CustomizedRefIntervalInfoBean")]
	
	public class CustomizedRefIntervalInfoBean
	{
		public var lowerLimit:Number=-10000.0;
		public var upperLimit:Number=-10000.0;
		public var samples:int=0;
		public var lowerCI5:Number=-10000.0;
		public var lowerCI95:Number=-10000.0;
		public var upperCI5:Number=-10000.0;
		public var upperCI95:Number=-10000.0;
		
		public var lowerLimitWithLT:Number=-10000.0;
		public var upperLimitWithLT:Number=-10000.0;
		public var samplesWithLT:int=0;
		public var lowerCI5WithLT:Number=-10000.0;
		public var lowerCI95WithLT:Number=-10000.0;
		public var upperCI5WithLT:Number=-10000.0;
		public var upperCI95WithLT:Number=-10000.0;
		
		public var pValue1:Number=0.0;//skewness before transformation
		public var pValue2:Number=0.0;//skewness after transformation
		
		//The followings are used for additional information
		public var age:String="1 YR";
		public var gender:String="FM";
		public var metricSystem:String="U/L";
		public var rawDataList:ArrayCollection=null;
		
		public var age_from:Number=0.0;
		public var age_to:Number=0.0;
			
		public function CustomizedRefIntervalInfoBean()
		{
		}
		
		public function toString():String
		{
			return lowerLimit+", "+upperLimit+", "+samples+", ["+lowerCI5+" to "+lowerCI95+"], ["+upperCI5+" to "+upperCI95+"]"+", "+pValue1+", "+pValue2;
		}
	}
}