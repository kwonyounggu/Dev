package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmClinicalSummaryEditBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929406747571777142L;
	
	private int carm_id=0;
	private String old_clinical_summary="";
	private String new_clinical_summary="";
	private boolean edited=false;
	private Timestamp edit_date=null;
	private String editor_id="";
	
	public int getCarm_id()
	{
		return carm_id;
	}
	public void setCarm_id(int carm_id)
	{
		this.carm_id = carm_id;
	}
	public String getOld_clinical_summary()
	{
		return old_clinical_summary;
	}
	public String getOld_clinical_summary_break()
	{
		return old_clinical_summary.replaceAll("(\r\n|\n)", "<br />");
	}
	public void setOld_clinical_summary(String old_clinical_summary)
	{
		this.old_clinical_summary = old_clinical_summary;
	}
	public String getNew_clinical_summary()
	{
		return new_clinical_summary;
	}
	public void setNew_clinical_summary(String new_clinical_summary)
	{
		this.new_clinical_summary = new_clinical_summary;
	}
	public boolean isEdited()
	{
		return edited;
	}
	public void setEdited(boolean edited)
	{
		this.edited = edited;
	}
	public Timestamp getEdit_date()
	{
		return edit_date;
	}
	public void setEdit_date(Timestamp edit_date)
	{
		this.edit_date = edit_date;
	}
	public String getEditor_id()
	{
		return editor_id;
	}
	public void setEditor_id(String editor_id)
	{
		this.editor_id = editor_id;
	}
	//@Override public String toString()
    //{
    //  return &quot;ClinicalSummaryEdit [id=&quot; + id + &quot;, firstName=&quot; + firstName + &quot;, &quot; +
    //        &quot;lastName=&quot; + lastName + &quot;]&quot;;
    //}
}
