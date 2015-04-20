package com.sickkids.caliper.dao;

import java.io.Serializable;

public class EthnicGenderAgeBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int patient_id=0;
	private int sex=0;
	private double age=0.0d;
	private int ethnic_group=0;
	public int getPatient_id()
	{
		return patient_id;
	}
	public void setPatient_id(int patient_id)
	{
		this.patient_id = patient_id;
	}
	public int getSex()
	{
		return sex;
	}
	public void setSex(int sex)
	{
		this.sex = sex;
	}
	public double getAge()
	{
		return age;
	}
	public void setAge(double age)
	{
		this.age = age;
	}
	public int getEthnic_group()
	{
		return ethnic_group;
	}
	public void setEthnic_group(int ethnic_group)
	{
		this.ethnic_group = ethnic_group;
	}

}
