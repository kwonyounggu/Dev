package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.AllPointDescBeanM")]
	public class AllPointDescBean
	{
		public var appId:int=0;
		public var itemHierachy:String="";
		public var pointId:int=0;
		public var pointName:String="";
		public var pointExplanation:String="";
		public var pointValueString:String="";
		public var pointValueNumber:Number=0;
		public var pointValueMin:Number=0;
		public var pointValueMax:Number=0;
		public var pointValueUnits:String="";
		public var pointValueOptions:String="";
		public var confidenceLevel:int=0;
		public var creationTime:Date=null;
		public var creatorId:String="";
		public var remarks:String="";
		
		public function AllPointDescBean()
		{
		}
	}
}