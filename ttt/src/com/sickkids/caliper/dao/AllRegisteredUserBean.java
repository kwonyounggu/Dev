package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringEscapeUtils;

public class AllRegisteredUserBean implements Serializable
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
	
	private Timestamp accessLimitTime=null;
	private String practiceYear="<0(For Student)";
	private String healthDiscipline="";
	private String primayClinicalPractice="";
	private int seniority=0;	
	
	private Timestamp creationTime=null;
	private String creatorId="";
	private String remarks="";
	
	private boolean valid=true;

	private String hopitalName="";
	
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
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	
	public String getHopitalName()
	{
		return hopitalName;
	}
	public void setHopitalName(String hopitalName)
	{
		this.hopitalName = hopitalName;
	}
	public AllRegisteredUserBean copy()
	{
		AllRegisteredUserBean temp=new AllRegisteredUserBean();
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

		temp.accessLimitTime=this.accessLimitTime;
		temp.practiceYear=this.practiceYear;
		temp.healthDiscipline=this.healthDiscipline;
		temp.primayClinicalPractice=this.primayClinicalPractice;
		temp.seniority=this.seniority;
		temp.valid=this.valid;
		
		temp.hopitalName=this.hopitalName;
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
		"valid="+valid+", "+
		"access_limit_time="+accessLimitTime+", "+
		"practice_year="+practiceYear+", "+
		"health_discipline="+healthDiscipline+", "+
		"primay_clinical_practice="+primayClinicalPractice+", "+
		"seniority="+seniority;
	}

	public String getInsertStmt()
	{
		return "insert into all_registered_user values("+appId+", "+
				   "'"+userId+"', "+
				   "'"+firstName+"', "+
				   "'"+lastName+"', "+
				   "'"+loginLevel+"', "+
				   "'"+hospitalId+"', "+
				   "'"+email+"', "+
				   "'"+password+"', "+
				   "'"+department+"', "+
				   "'"+phone+"', "+
				   "'"+resetPassword+"', "+
				   "'"+position+"', "+
				   "'"+description+"', "+
				   "'"+accessLimitTime+"', "+
				   "'"+practiceYear+"', "+
				   "'"+healthDiscipline+"', "+
				   "'"+primayClinicalPractice+"', "+
				   "'"+seniority+"', "+
				   "'"+creationTime+"', "+
				   "'"+creatorId+"', "+
				   "'"+remarks+"', "+
				   ""+valid+")";
	}
	public String getUpdateStmt()
	{
		return "update all_registered_user set "+
					"email='"+email+"', "+
					"login_level='"+loginLevel+"', "+
					"reset_password='"+resetPassword+"', "+
					"access_limit_time='"+accessLimitTime+"', "+
					"practice_year='"+practiceYear+"', "+
					"health_discipline='"+healthDiscipline+"', "+
					"primay_clinical_practice='"+primayClinicalPractice+"', "+
					"seniority='"+seniority+"', "+
					"remarks='"+remarks+"', "+
					"valid="+valid+" "+
					"where hospital_id='"+hospitalId+"' and user_id='"+userId+"'";
					
	}
}
