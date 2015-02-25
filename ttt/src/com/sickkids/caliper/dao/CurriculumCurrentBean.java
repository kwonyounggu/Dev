package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import com.sickkids.caliper.common.ParticipantType;

public class CurriculumCurrentBean implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = -6874257874690598741L;
	private int courseNumber=0;
	private String courseName="";
	private String lecturerId="";
	private String taId="";
	private String interactiveSiteViewer1Id="";
	private String interactiveSiteViewer2Id="";
	private String onewaySiteViewer1Id="";
	private String onewaySiteViewer2Id="";
	private String onewaySiteViewer3Id="";
	private String onewaySiteViewer4Id="";
	private String onewaySiteViewer5Id="";
	private String onewaySiteViewer6Id="";
	private int courseDataFileNumber=0;
	private int courseTimeTableNumber=0;
	private String creatorId="";
	private Timestamp creationTime=null;
	private String remarks="";
	private boolean valid=true;
	public int getCourseNumber()
	{
		return courseNumber;
	}
	public void setCourseNumber(int courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	public String getLecturerId()
	{
		return lecturerId;
	}
	public void setLecturerId(String lecturerId)
	{
		this.lecturerId = lecturerId;
	}
	public String getTaId()
	{
		return taId;
	}
	public void setTaId(String taId)
	{
		this.taId = taId;
	}
	public String getInteractiveSiteViewer1Id()
	{
		return interactiveSiteViewer1Id;
	}
	public void setInteractiveSiteViewer1Id(String interactiveSiteViewer1Id)
	{
		this.interactiveSiteViewer1Id = interactiveSiteViewer1Id;
	}
	public String getInteractiveSiteViewer2Id()
	{
		return interactiveSiteViewer2Id;
	}
	public void setInteractiveSiteViewer2Id(String interactiveSiteViewer2Id)
	{
		this.interactiveSiteViewer2Id = interactiveSiteViewer2Id;
	}
	public String getOnewaySiteViewer1Id()
	{
		return onewaySiteViewer1Id;
	}
	public void setOnewaySiteViewer1Id(String onewaySiteViewer1Id)
	{
		this.onewaySiteViewer1Id = onewaySiteViewer1Id;
	}
	public String getOnewaySiteViewer2Id()
	{
		return onewaySiteViewer2Id;
	}
	public void setOnewaySiteViewer2Id(String onewaySiteViewer2Id)
	{
		this.onewaySiteViewer2Id = onewaySiteViewer2Id;
	}
	public String getOnewaySiteViewer3Id()
	{
		return onewaySiteViewer3Id;
	}
	public void setOnewaySiteViewer3Id(String onewaySiteViewer3Id)
	{
		this.onewaySiteViewer3Id = onewaySiteViewer3Id;
	}
	public String getOnewaySiteViewer4Id()
	{
		return onewaySiteViewer4Id;
	}
	public void setOnewaySiteViewer4Id(String onewaySiteViewer4Id)
	{
		this.onewaySiteViewer4Id = onewaySiteViewer4Id;
	}
	public String getOnewaySiteViewer5Id()
	{
		return onewaySiteViewer5Id;
	}
	public void setOnewaySiteViewer5Id(String onewaySiteViewer5Id)
	{
		this.onewaySiteViewer5Id = onewaySiteViewer5Id;
	}
	public String getOnewaySiteViewer6Id()
	{
		return onewaySiteViewer6Id;
	}
	public void setOnewaySiteViewer6Id(String onewaySiteViewer6Id)
	{
		this.onewaySiteViewer6Id = onewaySiteViewer6Id;
	}
	public int getCourseDataFileNumber()
	{
		return courseDataFileNumber;
	}
	public void setCourseDataFileNumber(int courseDataFileNumber)
	{
		this.courseDataFileNumber = courseDataFileNumber;
	}
	public int getCourseTimeTableNumber()
	{
		return courseTimeTableNumber;
	}
	public void setCourseTimeTableNumber(int courseTimeTableNumber)
	{
		this.courseTimeTableNumber = courseTimeTableNumber;
	}
	public String getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}
	public Timestamp getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public ParticipantType getParticipantType(String id)
	{
		if(id.equals(lecturerId)) return ParticipantType.LECTURER;
		else if(id.equals(taId)) return ParticipantType.TEACHING_ASSISTANT;
		else if(id.equals(interactiveSiteViewer1Id)) return ParticipantType.INTERACTIVE_VIEWER1;
		else if(id.equals(interactiveSiteViewer2Id)) return ParticipantType.INTERACTIVE_VIEWER2;
		else if(id.equals(onewaySiteViewer1Id)) return ParticipantType.ONEWAY_VIEWER1;
		else if(id.equals(onewaySiteViewer2Id)) return ParticipantType.ONEWAY_VIEWER2;
		else if(id.equals(onewaySiteViewer3Id)) return ParticipantType.ONEWAY_VIEWER3;
		else if(id.equals(onewaySiteViewer4Id)) return ParticipantType.ONEWAY_VIEWER4;
		else if(id.equals(onewaySiteViewer5Id)) return ParticipantType.ONEWAY_VIEWER5;
		else if(id.equals(onewaySiteViewer6Id)) return ParticipantType.ONEWAY_VIEWER6;
		else return ParticipantType.NONE;
	}
	public String toString()
	{
		return 
		"courseNumber="+courseNumber+", "+
		"courseName="+courseName+", "+
		"lecturerId="+lecturerId+", "+
		"taId="+taId+", "+
		"interactiveSiteViewer1Id="+interactiveSiteViewer1Id+", "+
		"interactiveSiteViewer2Id="+interactiveSiteViewer2Id+", "+
		"onewaySiteViewer1Id="+onewaySiteViewer1Id+", "+
		"onewaySiteViewer2Id="+onewaySiteViewer2Id+", "+
		"onewaySiteViewer3Id="+onewaySiteViewer3Id+", "+
		"onewaySiteViewer4Id="+onewaySiteViewer4Id+", "+
		"onewaySiteViewer5Id="+onewaySiteViewer5Id+", "+
		"onewaySiteViewer6Id="+onewaySiteViewer6Id+", "+
		"courseDataFileNumber="+courseDataFileNumber+", "+
		"courseTimeTableNumber="+courseTimeTableNumber+", "+
		"creatorId="+creatorId+", "+
		"creationTime="+creationTime+", "+
		"remarks="+remarks+", "+
		"valid="+valid;
	}

	public String getInsertStmt()
	{
		return "insert into curriculum_current values("+courseNumber+", "+
				   "'"+StringEscapeUtils.escapeSql(courseName)+"', "+
				   "'"+lecturerId+"', "+
				   "'"+taId+"', "+
				   "'"+interactiveSiteViewer1Id+"', "+
				   "'"+interactiveSiteViewer2Id+"', "+
				   "'"+onewaySiteViewer1Id+"', "+
				   "'"+onewaySiteViewer2Id+"', "+
				   "'"+onewaySiteViewer3Id+"', "+
				   "'"+onewaySiteViewer4Id+"', "+
				   "'"+onewaySiteViewer5Id+"', "+
				   "'"+onewaySiteViewer6Id+"', "+
				   "'"+courseDataFileNumber+"', "+
				   "'"+courseTimeTableNumber+"', "+
				   "'"+creatorId+"', "+
				   "'"+creationTime+"', "+
				   "'"+remarks+"', "+
				   ""+valid+")";
	}
	public String getUpdateSomeFieldsStmt()
	{
		return "update curriculum_current set "+
					"course_name='"+StringEscapeUtils.escapeSql(courseName)+"', "+
					"lecturer_id='"+lecturerId+"', "+
					"ta_id='"+taId+"', "+	
					"interactive_site_viewer1_id='"+interactiveSiteViewer1Id+"', "+
					"interactive_site_viewer2_id='"+interactiveSiteViewer2Id+"', "+
					"oneway_site_viewer1_id='"+onewaySiteViewer1Id+"', "+	
					"oneway_site_viewer2_id='"+onewaySiteViewer2Id+"', "+
					"oneway_site_viewer3_id='"+onewaySiteViewer3Id+"', "+
					"oneway_site_viewer4_id='"+onewaySiteViewer4Id+"', "+	
					"oneway_site_viewer5_id='"+onewaySiteViewer5Id+"', "+
					"oneway_site_viewer6_id='"+onewaySiteViewer6Id+"', "+
					//"course_data_file_number='"+courseDataFileNumber+"', "+	
					//"course_time_table_number='"+courseTimeTableNumber+"', "+
					//"creator_id='"+creatorId+"', "+
					//"creation_time='"+creationTime+"', "+		
					"remarks='"+remarks+"', "+
					"valid="+valid+" "+
					"where course_number='"+courseNumber+"'";		
		
	}
	public boolean isDuplicatedIds()
	{
		Set<String> set=new HashSet<String>();
		String[] list={lecturerId, taId, interactiveSiteViewer1Id, interactiveSiteViewer2Id,
						onewaySiteViewer1Id, onewaySiteViewer2Id, onewaySiteViewer3Id, onewaySiteViewer4Id, onewaySiteViewer5Id, onewaySiteViewer6Id
					  };
		//for(String s : list) System.out.println(s+" ");
		for(String s : list)
			if(!s.equals(""))
			{
				if(set.contains(s)) { set.clear(); return true;}
				else set.add(s);
			}
		set.clear();
		return false;
	}
}
