package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringEscapeUtils;

public class FileLibraryBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int fileId=-1000;
	private String fileNameFormal="";
	private String description="";
	private String fileType="";
	private String fileLocationPath="";
	private int fileVersion=1;
	private String fileNameSubmitted="";
	private String fileNameGenerated="";
	private String submitterId="";
	private Timestamp submissionTime=null;
	private long fileSize=0L;
	private String remarks="";
	private boolean valid=true;
	
	public int getFileId()
	{
		return fileId;
	}
	public void setFileId(int fileId)
	{
		this.fileId = fileId;
	}
	public String getFileNameFormal()
	{
		return fileNameFormal;
	}
	public void setFileNameFormal(String fileNameFormal)
	{
		this.fileNameFormal = fileNameFormal;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getFileType()
	{
		return fileType;
	}
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}
	public String getFileLocationPath()
	{
		return fileLocationPath;
	}
	public void setFileLocationPath(String fileLocationPath)
	{
		this.fileLocationPath = fileLocationPath;
	}
	public int getFileVersion()
	{
		return fileVersion;
	}
	public void setFileVersion(int fileVersion)
	{
		this.fileVersion = fileVersion;
	}
	public String getFileNameSubmitted()
	{
		return fileNameSubmitted;
	}
	public void setFileNameSubmitted(String fileNameSubmitted)
	{
		this.fileNameSubmitted = fileNameSubmitted;
	}
	public String getFileNameGenerated()
	{
		return fileNameGenerated;
	}
	public void setFileNameGenerated(String fileNameGenerated)
	{
		this.fileNameGenerated = fileNameGenerated;
	}
	public String getSubmitterId()
	{
		return submitterId;
	}
	public void setSubmitterId(String submitterId)
	{
		this.submitterId = submitterId;
	}
	public Timestamp getSubmissionTime()
	{
		return submissionTime;
	}
	public void setSubmissionTime(Timestamp submissionTime)
	{
		this.submissionTime = submissionTime;
	}
	public long getFileSize()
	{
		return fileSize;
	}
	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
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
	public String toString()
	{
		return 
		"fileId="+fileId+", "+
		"fileNameFormal="+fileNameFormal+", "+
		"description="+description+", "+
		"fileType="+fileType+", "+
		"fileLocationPath="+fileLocationPath+", "+
		"fileVersion="+fileVersion+", "+
		"fileNameSubmitted="+fileNameSubmitted+", "+
		"fileNameGenerated="+fileNameGenerated+", "+
		"submitterId="+submitterId+", "+
		"submissionTime="+submissionTime+", "+
		"fileSize="+fileSize+", "+
		"remarks="+remarks+", "+
		"valid="+valid;
	}

	public String getInsertStmt()
	{
		return "insert into file_library values("+fileId+", "+
				   "'"+fileNameFormal+"', "+
				   "'"+StringEscapeUtils.escapeSql(description)+"', "+
				   "'"+fileType+"', "+
				   "'"+fileLocationPath+"', "+
				   "'"+fileVersion+"', "+
				   "'"+fileNameSubmitted+"', "+
				   "'"+fileNameGenerated+"', "+
				   "'"+submitterId+"', "+
				   "'"+submissionTime+"', "+
				   "'"+fileSize+"', "+
				   "'"+remarks+"', "+
				   ""+valid+")";
	}
	public String getUpdateStmt()
	{
		return "update file_library set "+
					"fileNameFormal='"+fileNameFormal+"', "+
					"description='"+description+"', "+
					"fileType='"+fileType+"', "+
					"fileLocationPath='"+fileLocationPath+"', "+
					"fileVersion='"+fileVersion+"', "+
					"fileNameSubmitted='"+fileNameSubmitted+"', "+
					"fileNameGenerated='"+fileNameGenerated+"', "+
					"submitterId='"+submitterId+"', "+
					"submissionTime='"+submissionTime+"', "+
					"fileSize='"+fileSize+"', "+
					"remarks='"+remarks+"', "+
					"valid="+valid+" "+
					"where fileId='"+fileId+"'";					
	}

}
