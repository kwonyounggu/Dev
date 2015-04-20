package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllRegisteredUserBeanM implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3266256943031987903L;
	private int appId=0;
	private String userId="first_name7.last_name9.01";//don't change this default value
	private String firstName="";
	private String lastName="";
	private int loginLevel=-1;
	private String hospitalId="00";
	private String email="";
	private String password="";
	private String department="";
	private String phone="";
	private int resetPassword=1;//0=false, 1=true	
	private String position="";
	private String description="";
	private String accessibleIds="";//1001,1002...
	private Timestamp accessLimitTime=null;
	private String practiceYear="<0(For Student)";
	private String healthDiscipline="";
	private String primayClinicalPractice="";
	private int seniority=0;	
	private int maxGroupsToReview=0;
	private String itemAssignmentType="";//ALL-ITEMS/EACH-ITEM
	private boolean myReviewConfirmatory=true;
	private Timestamp creationTime=null;
	private String creatorId="";
	private String remarks="";
	public int getAppId()
	{
		return appId;
	}
	public void setAppId(int appId)
	{
		this.appId = appId;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public int getLoginLevel()
	{
		return loginLevel;
	}
	public void setLoginLevel(int loginLevel)
	{
		this.loginLevel = loginLevel;
	}
	public String getHospitalId()
	{
		return hospitalId;
	}
	public void setHospitalId(String hospitalId)
	{
		this.hospitalId = hospitalId;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public int getResetPassword()
	{
		return resetPassword;
	}
	public void setResetPassword(int resetPassword)
	{
		this.resetPassword = resetPassword;
	}
	public String getPosition()
	{
		return position;
	}
	public void setPosition(String position)
	{
		this.position = position;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getAccessibleIds()
	{
		return accessibleIds;
	}
	public void setAccessibleIds(String accessibleIds)
	{
		this.accessibleIds = accessibleIds;
	}
	public Timestamp getAccessLimitTime()
	{
		return accessLimitTime;
	}
	public void setAccessLimitTime(Timestamp accessLimitTime)
	{
		this.accessLimitTime = accessLimitTime;
	}
	public String getPracticeYear()
	{
		return practiceYear;
	}
	public void setPracticeYear(String practiceYear)
	{
		this.practiceYear = practiceYear;
	}
	public String getHealthDiscipline()
	{
		return healthDiscipline;
	}
	public void setHealthDiscipline(String healthDiscipline)
	{
		this.healthDiscipline = healthDiscipline;
	}
	public String getPrimayClinicalPractice()
	{
		return primayClinicalPractice;
	}
	public void setPrimayClinicalPractice(String primayClinicalPractice)
	{
		this.primayClinicalPractice = primayClinicalPractice;
	}
	public int getSeniority()
	{
		return seniority;
	}
	public void setSeniority(int seniority)
	{
		this.seniority = seniority;
	}
	public int getMaxGroupsToReview()
	{
		return maxGroupsToReview;
	}
	public void setMaxGroupsToReview(int maxGroupsToReview)
	{
		this.maxGroupsToReview = maxGroupsToReview;
	}
	public String getItemAssignmentType()
	{
		return itemAssignmentType;
	}
	public void setItemAssignmentType(String itemAssignmentType)
	{
		this.itemAssignmentType = itemAssignmentType;
	}
	public boolean isMyReviewConfirmatory()
	{
		return myReviewConfirmatory;
	}
	public void setMyReviewConfirmatory(boolean myReviewConfirmatory)
	{
		this.myReviewConfirmatory = myReviewConfirmatory;
	}
	public Timestamp getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public AllRegisteredUserBeanM copy()
	{
		AllRegisteredUserBeanM temp=new AllRegisteredUserBeanM();
		temp.appId=this.appId;
		temp.userId=this.userId;
		temp.firstName=this.firstName;
		temp.lastName=this.lastName;
		temp.loginLevel=this.loginLevel;
		temp.hospitalId=this.hospitalId;
		temp.email=this.email;
		temp.password=this.password;
		temp.department=this.department;
		temp.phone=this.phone;
		temp.resetPassword=this.resetPassword;
		temp.creationTime=this.creationTime;
		temp.position=this.position;
		temp.description=this.description;
		temp.remarks=this.remarks;
		temp.accessibleIds=this.accessibleIds;
		temp.accessLimitTime=this.accessLimitTime;
		temp.practiceYear=this.practiceYear;
		temp.healthDiscipline=this.healthDiscipline;
		temp.primayClinicalPractice=this.primayClinicalPractice;
		temp.seniority=this.seniority;
				
		return temp;
	}
	public AllRegisteredUserBean copyM()
	{
		AllRegisteredUserBean temp=new AllRegisteredUserBean();
		temp.setApp_id(this.appId);
		temp.setUser_id(this.userId);
		temp.setFirst_name(this.firstName);
		temp.setLast_name(this.lastName);
		temp.setLogin_level(this.loginLevel);
		temp.setHostpital_id(this.hospitalId);
		temp.setEmail(this.email);
		temp.setPassword(this.password);
		temp.setDepartment(this.department);
		temp.setPhone(this.phone);
		temp.setReset_password(this.resetPassword);
		temp.setCreation_time(this.creationTime);
		temp.setPosition(this.position);
		temp.setDescription(this.description);
		temp.setRemarks(this.remarks);
		temp.setAccessible_ids(this.accessibleIds);
		temp.setAccess_limit_time(this.accessLimitTime);
		temp.setPractice_year(this.practiceYear);
		temp.setHealth_discipline(this.healthDiscipline);
		temp.setPrimay_clinical_practice(this.primayClinicalPractice);
		temp.setSeniority(this.seniority);
				
		return temp;
	}
	public String toString()
	{
		return 
		"appId="+appId+", "+
		"user_id="+userId+", "+
		"first_name="+firstName+", "+
		"last_name="+lastName+", "+
		"hospital_id="+hospitalId+", "+
		"department="+department+", "+
		"position="+position+", "+
		"email="+email+", "+
		"phone="+phone+", "+
		"login_level="+loginLevel+", "+
		"description="+description+", "+
		"remarks="+remarks+", "+
		"accessible_ids="+accessibleIds+", "+
		"access_limit_time="+accessLimitTime+", "+
		"practice_year="+practiceYear+", "+
		"health_discipline="+healthDiscipline+", "+
		"primay_clinical_practice="+primayClinicalPractice+", "+
		"seniority="+seniority;
	}
}
