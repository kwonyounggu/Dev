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
		public var hospitalName:String="The Hospital for Sick Children";
		public var courseNumber:int=6;
		public var courseName:String="Brain Diease Advanced";
		public var participantType:String="LECTURER";
		
		//The followings will be used for publishing and subcribing. 
		//Remove the values (Temporary assigment) later
		public var lecturerId:String="LECTURER_steven.bloom.01";		
		public var teachingAssistantId:String="TEACHING_ASSISTANT_young.kwon.08";
		public var activeParticipantId1:String="INTERACTIVE_VIEWER1_kristen.middaugh.01";
		public var activeParticipantId2:String="INTERACTIVE_VIEWER2_chris.parshuram.01";
		public var passiveParticipantId1:String="ONEWAY_VIEWER1_user1.kwon.01";
		public var passiveParticipantId2:String="";
		public var passiveParticipantId3:String="ONEWAY_VIEWER3_passive.viewer.01";
		public var passiveParticipantId4:String="";
		public var passiveParticipantId5:String="";
		public var passiveParticipantId6:String="";
		
		public var trainingTime:Date=null;//given from the flashvar parameter
		
		public var bTokenPossesed:Boolean=true; //change it to false later
		//public var bTokenPossesed:Boolean=false;//it can be possesed to anyone by the lecturer so that the owner of the token can control all
		//The token can only be possed to two people in the room (LECTURER and or other)
		//It can be enabled by the LECTURER any time but LECTURER's event is in higher priority
		/*
		[Bindable] public var bLecturerLoggedIn:Boolean=false;
		[Bindable] public var bTaLoggedIn:Boolean=false;
		[Bindable] public var bActiveParticipant1LoggedIn:Boolean=false;
		[Bindable] public var bActiveParticipant2LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant1LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant2LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant3LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant4LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant5LoggedIn:Boolean=false;
		[Bindable] public var bPassiveParticipant6LoggedIn:Boolean=false;
		*/		
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
				hospitalName=FlexGlobals.topLevelApplication.parameters.hospitalName;
				courseNumber=Number(FlexGlobals.topLevelApplication.parameters.courseNumber);
				courseName=FlexGlobals.topLevelApplication.parameters.courseName;
				participantType=FlexGlobals.topLevelApplication.parameters.participantType;
				
				
				if(participantType=="LECTURER")
				{
					bTokenPossesed=true;
					lecturerId=participantType+"_"+userId;
				}
				else if(participantType=="TEACHING_ASSISTANT")
				{
					teachingAssistantId=participantType+"_"+userId;
				}
				else if(participantType=="INTERACTIVE_VIEWER1")
				{
					activeParticipantId1=participantType+"_"+userId;
				}
				else if(participantType=="INTERACTIVE_VIEWER2")
				{
					activeParticipantId2=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER1")
				{
					passiveParticipantId1=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER2")
				{
					passiveParticipantId2=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER3")
				{
					passiveParticipantId3=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER4")
				{
					passiveParticipantId4=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER5")
				{
					passiveParticipantId5=participantType+"_"+userId;
				}
				else if(participantType=="ONEWAY_VIEWER6")
				{
					passiveParticipantId6=participantType+"_"+userId;
				}
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
					"hospitalName="+hospitalName+", "+"\n"+
					"courseNumber="+courseNumber+", "+"\n"+
					"courseName="+courseName+", "+"\n"+
					"participantType="+participantType+", "+"\n"+
					"lecturerId="+lecturerId+", "+"\n"+
					"teachingAssistantId="+teachingAssistantId+", "+"\n"+
					"activeParticipantId1="+activeParticipantId1+", "+"\n"+
					"activeParticipantId2="+activeParticipantId2+", "+"\n"+
					"passiveParticipantId1="+passiveParticipantId1+", "+"\n"+
					"passiveParticipantId2="+passiveParticipantId2+", "+"\n"+
					"passiveParticipantId3="+passiveParticipantId3+", "+"\n"+
					"passiveParticipantId4="+passiveParticipantId4+", "+"\n"+
					"passiveParticipantId5="+passiveParticipantId5+", "+"\n"+
					"passiveParticipantId6="+passiveParticipantId6+", "+"\n"+
					"bTokenPossesed="+bTokenPossesed+"\n";
		}
	}
}