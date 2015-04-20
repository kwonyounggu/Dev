package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class SubmissionTypeBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3714358502587663380L;

	private String submissionType="";

	public String getSubmissionType()
	{
		return submissionType;
	}
	public void setSubmissionType(String submissionType)
	{
		this.submissionType = submissionType;
	}
	
}
