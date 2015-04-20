package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmReviewResultBean implements Serializable
{
	  private static final long serialVersionUID = -2061097757327572639L;
	
	  private int carm_id=0;
	  private int group_no=0;
	  private String mediator_id="";
	  private String reviewer_id="";
	  private int r1_concordance_value=0;
	  private int r2_causation_value=0;
	  private int r3_preventability_scale_value=0;
	  private int r4_confidence_value=0;
	  private int r5_estimate_value=0;
	  private int r6_admit_hour_value=0;
	  private int r7_not_admin_hour_value=0;
	  private int r8_consult_hour_value=0;
	  private int r9_not_consult_hour_value=0;
	  private String r10_preventability_description="";
	  private String remarks="";
	  private Timestamp updated_time=null;
	  private String review_status="";
	  private int final_rate=0;
	  private boolean validity=true;
	  
	public String getReviewer_id()
	{
		return reviewer_id;
	}
	public void setReviewer_id(String reviewer_id)
	{
		this.reviewer_id = reviewer_id;
	}
	public int getCarm_id()
	{
		return carm_id;
	}
	public void setCarm_id(int carm_id)
	{
		this.carm_id = carm_id;
	}
	public int getGroup_no()
	{
		return group_no;
	}
	public void setGroup_no(int group_no)
	{
		this.group_no = group_no;
	}
	public String getMediator_id()
	{
		return mediator_id;
	}
	public void setMediator_id(String mediator_id)
	{
		this.mediator_id = mediator_id;
	}
	public int getR1_concordance_value()
	{
		return r1_concordance_value;
	}
	public void setR1_concordance_value(int r1_concordance_value)
	{
		this.r1_concordance_value = r1_concordance_value;
	}
	public int getR2_causation_value()
	{
		return r2_causation_value;
	}
	public void setR2_causation_value(int r2_causation_value)
	{
		this.r2_causation_value = r2_causation_value;
	}
	public int getR3_preventability_scale_value()
	{
		return r3_preventability_scale_value;
	}
	public void setR3_preventability_scale_value(int r3_preventability_scale_value)
	{
		this.r3_preventability_scale_value = r3_preventability_scale_value;
	}
	public int getR4_confidence_value()
	{
		return r4_confidence_value;
	}
	public void setR4_confidence_value(int r4_confidence_value)
	{
		this.r4_confidence_value = r4_confidence_value;
	}
	public int getR5_estimate_value()
	{
		return r5_estimate_value;
	}
	public void setR5_estimate_value(int r5_estimate_value)
	{
		this.r5_estimate_value = r5_estimate_value;
	}
	public int getR6_admit_hour_value()
	{
		return r6_admit_hour_value;
	}
	public void setR6_admit_hour_value(int r6_admit_hour_value)
	{
		this.r6_admit_hour_value = r6_admit_hour_value;
	}
	public int getR7_not_admin_hour_value()
	{
		return r7_not_admin_hour_value;
	}
	public void setR7_not_admin_hour_value(int r7_not_admin_hour_value)
	{
		this.r7_not_admin_hour_value = r7_not_admin_hour_value;
	}
	public int getR8_consult_hour_value()
	{
		return r8_consult_hour_value;
	}
	public void setR8_consult_hour_value(int r8_consult_hour_value)
	{
		this.r8_consult_hour_value = r8_consult_hour_value;
	}
	public int getR9_not_consult_hour_value()
	{
		return r9_not_consult_hour_value;
	}
	public void setR9_not_consult_hour_value(int r9_not_consult_hour_value)
	{
		this.r9_not_consult_hour_value = r9_not_consult_hour_value;
	}
	public String getR10_preventability_description()
	{
		return r10_preventability_description;
	}
	public void setR10_preventability_description(String r10_preventability_description)
	{
		this.r10_preventability_description = r10_preventability_description;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public Timestamp getUpdated_time()
	{
		return updated_time;
	}
	public void setUpdated_time(Timestamp updated_time)
	{
		this.updated_time = updated_time;
	}
	public String getReview_status()
	{
		return review_status;
	}
	public void setReview_status(String review_status)
	{
		this.review_status = review_status;
	}
	public int getFinal_rate()
	{
		return final_rate;
	}
	public void setFinal_rate(int final_rate)
	{
		this.final_rate = final_rate;
	}
	public boolean isValidity()
	{
		return validity;
	}
	public void setValidity(boolean validity)
	{
		this.validity = validity;
	}
}
