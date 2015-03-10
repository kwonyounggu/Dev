package org.red5.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import com.sickkids.caliper.common.ParticipantType;

public class UserBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7015953661855061123L;
	
	
	private int roomId=-1;
	private String roomName="";
	private String userId="";
	private String email="";
	private String hospitalId="";
	private String hospitalName="";
	private ParticipantType participantType=ParticipantType.NONE;	
	private String ipAddress = "0.0.0.0";
	public int getRoomId()
	{
		return roomId;
	}
	public void setRoomId(int roomId)
	{
		this.roomId = roomId;
	}
	public String getRoomName()
	{
		return roomName;
	}
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getHospitalId()
	{
		return hospitalId;
	}
	public void setHospitalId(String hospitalId)
	{
		this.hospitalId = hospitalId;
	}
	public String getHospitalName()
	{
		return hospitalName;
	}
	public void setHospitalName(String hospitalName)
	{
		this.hospitalName = hospitalName;
	}
	public ParticipantType getParticipantType()
	{
		return participantType;
	}
	public void setParticipantType(ParticipantType participantType)
	{
		this.participantType = participantType;
	}
	public void setParticipantType(String participantType)
	{
		if(participantType.equals("LECTURER")) this.participantType=ParticipantType.LECTURER;
		else if(participantType.equals("TEACHING_ASSISTANT")) this.participantType=ParticipantType.TEACHING_ASSISTANT;
		else if(participantType.equals("INTERACTIVE_VIEWER1")) this.participantType=ParticipantType.INTERACTIVE_VIEWER1;
		else if(participantType.equals("INTERACTIVE_VIEWER2")) this.participantType=ParticipantType.INTERACTIVE_VIEWER2;
		else if(participantType.equals("ONEWAY_VIEWER1")) this.participantType=ParticipantType.ONEWAY_VIEWER1;
		else if(participantType.equals("ONEWAY_VIEWER2")) this.participantType=ParticipantType.ONEWAY_VIEWER2;
		else if(participantType.equals("ONEWAY_VIEWER3")) this.participantType=ParticipantType.ONEWAY_VIEWER3;
		else if(participantType.equals("ONEWAY_VIEWER4")) this.participantType=ParticipantType.ONEWAY_VIEWER4;
		else if(participantType.equals("ONEWAY_VIEWER5")) this.participantType=ParticipantType.ONEWAY_VIEWER5;
		else if(participantType.equals("ONEWAY_VIEWER6")) this.participantType=ParticipantType.ONEWAY_VIEWER6;
		else this.participantType=ParticipantType.NONE;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public String toString()
	{
		return
		"roomId="+roomId+"\n"+
		"roomName="+roomName+"\n"+
		"userId="+userId+"\n"+
		"email="+email+"\n"+
		"hospitalId="+hospitalId+"\n"+
		"hospitalName="+hospitalName+"\n"+
		"participantType="+participantType+"\n"+
		"clientIp="+ipAddress+"\n";
		
	}
	
	
}
