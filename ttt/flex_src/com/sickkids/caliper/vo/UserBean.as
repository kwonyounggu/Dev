package com.sickkids.caliper.vo
{
	//This class is corresponding with UserBean.java containing a client side information connecting to the red5 server
	[Bindable] 
	[RemoteClass(alias="org.red5.dao.UserBean")]
	public class UserBean
	{
		
		public var roomId:int=-1;
		public var roomName:String="";
		public var userId:String="";
		public var email:String="";
		public var hospitalId:String="";
		public var hospitalName:String="";
		public var participantType:String="";	
		public var ipAddress:String="0.0.0.0";
		
		public function UserBean()
		{
		}
		public function toString():String
		{
			return	"roomId="+roomId+", "+"\n"+
				"roomName="+roomName+", "+"\n"+
				"userId="+userId+", "+"\n"+
				"email="+email+", "+"\n"+
				"hospitalId="+hospitalId+", "+"\n"+
				"hospitalName="+hospitalName+", "+"\n"+
				"participantType="+participantType+", "+"\n"+
				"ipAddress="+ipAddress+"\n";
		}
		public function toolTip():String
		{
			return	"Hospital: "+hospitalName+"\n"+
					"Class Name: "+roomName+"\n"+
					"User ID: "+userId+"\n"+
					"Position: "+position()+"\n"+
					"E-Mail: "+email+"\n"+
					"Ip-Address: "+ipAddress+"\n";
		}
		public function position():String
		{
			var retPosition:String="";
			switch(participantType)
			{
				case "LECTURER": retPosition="Main Conductor"; break;
				case "TEACHING_ASSISTANT": retPosition="Vice Conductor"; break;
				case "INTERACTIVE_VIEWER1": retPosition="Active Participant #1"; break;
				case "INTERACTIVE_VIEWER2":retPosition="Active Participant #2"; break;
				case "ONEWAY_VIEWER1": retPosition="Passive Participant #1"; break;
				case "ONEWAY_VIEWER2": retPosition="Passive Participant #2"; break;
				case "ONEWAY_VIEWER3": retPosition="Passive Participant #3"; break;
				case "ONEWAY_VIEWER4": retPosition="Passive Participant #4"; break;
				case "ONEWAY_VIEWER5": retPosition="Passive Participant #5"; break;
				case "ONEWAY_VIEWER6": retPosition="Passive Participant #6"; break;
				default: break;
			}
			return retPosition;
		}
	}
}