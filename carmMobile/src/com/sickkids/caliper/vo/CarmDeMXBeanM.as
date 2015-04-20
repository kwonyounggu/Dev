package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmDeMXBeanM")]
	public class CarmDeMXBeanM
	{
		public var carmId:int=0;
		public var site:String="00";
		public var patNo:String="0000";
		public var eventNo:int=0;
		public var deIds:String="";
		public var de1Id:int=0;
		public var de2Id:int=0;
		public var de3Ids:String="";
		public var de4Id:int=0;
		public var de5Ids:String="";
		public var de6Id:int=0;
		public var remarks:String="";
		public var createdTime:Date=null;
		public var flag:Boolean=false;
		public var reviewStatus:String="not used";//This field is not used
		public function CarmDeMXBeanM()
		{
		}
	}
}