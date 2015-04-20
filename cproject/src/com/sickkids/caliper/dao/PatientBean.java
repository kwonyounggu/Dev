package com.sickkids.caliper.dao;

import java.io.Serializable;

public class PatientBean implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int patient_id=0;
	private String sex="";
	private double age=0.0d;
	private String ethnic_group="";
	private String methodologies="";
	private int diagnostic_test_result_id=0;
	public int getPatient_id()
	{
		return patient_id;
	}
	public void setPatient_id(int patient_id)
	{
		this.patient_id = patient_id;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
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
	public String getEthnic_group()
	{
		return ethnic_group;
	}
	public void setEthnic_group(String ethnic_group)
	{
		this.ethnic_group = ethnic_group;
	}
	public String getMethodologies()
	{
		return methodologies;
	}
	public void setMethodologies(String methodologies)
	{
		this.methodologies = methodologies;
	}
	public int getDiagnostic_test_result_id()
	{
		return diagnostic_test_result_id;
	}
	public void setDiagnostic_test_result_id(int diagnostic_test_result_id)
	{
		this.diagnostic_test_result_id = diagnostic_test_result_id;
	}

}
