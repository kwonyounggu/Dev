package com.sickkids.caliper.vo
{
	import mx.core.FlexGlobals;

	public class UserInfoBean
	{		
		//The default values based on Conductor
		public var userId:String="steven.bloom.01";
		public var firstName:String="Steven";
		public var lastName:String="Bloom";
		public var loginLevel:int=1;//admin
		public var hospitalId:String="02";
		public var email:String="younggu.kwon@sickkids.ca";
		public var hopitalName:String="The Hospital for Sick Children";
		public var courseNumber:int=6;
		public var courseName:String="Brain Diease Advanced";
		public var participantType:String="LECTURER";
				
		public function UserInfoBean()
		{
		}
		
		public function setExternalVars():void
		{
			if(FlexGlobals.topLevelApplication.parameters.userId!=null) //application is called from swfobject.embedSWF
			{
				userId=FlexGlobals.topLevelApplication.parameters.userId;
				firstName=FlexGlobals.topLevelApplication.parameters.firstName;
				lastName=FlexGlobals.topLevelApplication.parameters.lastName;
				loginLevel=Number(FlexGlobals.topLevelApplication.parameters.loginLevel);
				hospitalId=FlexGlobals.topLevelApplication.parameters.hospitalId;
				email=FlexGlobals.topLevelApplication.parameters.email;
				hopitalName=FlexGlobals.topLevelApplication.parameters.hopitalName;
				courseNumber=Number(FlexGlobals.topLevelApplication.parameters.courseNumber);
				courseName=FlexGlobals.topLevelApplication.parameters.courseName;
				participantType=FlexGlobals.topLevelApplication.parameters.participantType;
			}
		}
		public function toString():String
		{
			return	"userId="+userId+", "+
					"firstName="+firstName+", "+
					"lastName="+lastName+", "+
					"loginLevel="+loginLevel+", "+
					"hospitalId="+hospitalId+", "+
					"email="+email+", "+
					"hopitalName="+hopitalName+", "+
					"courseNumber="+courseNumber+", "+
					"courseName="+courseName+", "+
					"participantType="+participantType;
		}
	}
}