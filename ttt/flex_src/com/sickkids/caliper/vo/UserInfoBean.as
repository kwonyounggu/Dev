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
		
		public var bTokenPossesed:Boolean=false;//it can be possesed to anyone by the lecturer so that the owner of the token can control all
		//The token can only be possed to two people in the room (LECTURER and or other)
		//It can be enabled by the LECTURER any time but LECTURER's event is in higher priority
				
		public function UserInfoBean()
		{
		}
		
		public function setExternalVars():void
		{
			//bTokenPossesed=true;//remove later
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
				if(participantType=="LECTURER") bTokenPossesed=true;
				else bTokenPossesed=false;
			}
		}
		public function toString():String
		{
			return	"userId="+userId+", "+"\n"+
					"firstName="+firstName+", "+"\n"+
					"lastName="+lastName+", "+"\n"+
					"loginLevel="+loginLevel+", "+"\n"+
					"hospitalId="+hospitalId+", "+"\n"+
					"email="+email+", "+"\n"+
					"hopitalName="+hopitalName+", "+"\n"+
					"courseNumber="+courseNumber+", "+"\n"+
					"courseName="+courseName+", "+"\n"+
					"participantType="+participantType+", "+"\n"+
					"bTokenPossesed="+bTokenPossesed+"\n";
		}
	}
}