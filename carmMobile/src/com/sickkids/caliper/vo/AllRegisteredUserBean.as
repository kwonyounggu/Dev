package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.AllRegisteredUserBeanM")]
	public class AllRegisteredUserBean
	{
		public var appId:int=0;
		public var userId:String="first_name7.last_name9.01";
		public var firstName:String="";
		public var lastName:String="";
		public var loginLevel:int=-1;
		public var hospitalId:String="00";
		public var email:String="";
		public var password:String="";
		public var department:String="";
		public var phone:String="";
		public var resetPassword:int=1;//0=false, 1=true	
		public var position:String="";
		public var description:String="";
		public var accessibleIds:String="";//1001,1002...
		public var accessLimitTime:Date=null;
		public var practiceYear:String="<0(For Student)";
		public var healthDiscipline:String="";
		public var primayClinicalPractice:String="";
		public var seniority:int=0;	
		public var maxGroupsToReview:int=0;
		public var itemAssignmentType:String="";//ALL-ITEMS/EACH-ITEM
		public var myReviewConfirmatory:Boolean=true;
		public var creationTime:Date=null;
		public var creatorId:String="";
		public var remarks:String="";
		
		public static const USER_ID:String="first_name7.last_name9.01";//defaulted this from the server when there is no record from the table
		public function AllRegisteredUserBean()
		{
		}
	}
}