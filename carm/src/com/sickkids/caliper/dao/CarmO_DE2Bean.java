package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DE2Bean implements Serializable
{
	private static final long serialVersionUID = -3574087059878234194L;
	
	private int DE2_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	private String rsAirwayLower="";
	private String rsAirwayUpper="";
	private String rsChronicLungDisease="";
	private String rsOtherDisease="";
	private String rsOtherList="";
	private String hcStructuralDiseaseNotCyanotic="";
	private String hcStructuralDiseaseCyanotic="";
	private String hcPulmonary="";
	private String hcSystemic="";
	private String hcVascular="";
	private String hcVasculitis="";
	private String hcOtherDisease="";
	private String hcOtherList="";
	private String neuroSeizures="";
	private String neuroHydrocphalus="";
	private String neuroStatic="";
	private String neuroProgressive="";
	private String neuroMyopathy="";
	private String neuroOtherList="";
	private String gastroLiver="";
	private String gastroIntestinalBleeding="";
	private String gastroMalabsorbtion="";
	private String gastroIntestinalDisease="";
	private String gastroOtherList="";
	private String renalImpairment="";
	private String renalDisease="";
	private String renalEletrolyte="";
	private String renalOtherList="";
	private String musJoint="";
	private String musSytemic="";
	private String musOrthopaedic="";
	private String musScoliosis="";
	private String musCutaneous="";
	private String musOtherList="";
	private String haeMalignancyHae="";
	private String haeMalignancyOrgan="";
	private String haeNonMalignancy="";
	private String haePrimary="";
	private String haeAcquired="";
	private String haeBone="";
	private String haeSolidOrgan="";
	private String haeProthrombotic="";
	private String haeBleeding="";
	private String haeOtherList="";
	private String endoMetabolic="";
	private String endoDiabetes="";
	private String endoEndocrineDisease="";
	private String endoOtherList="";
	private String allKnown="";
	private String allOther="";
	private String allOtherList="";
	private String devicesOxygen="";
	private String devicesTrachNoVent="";
	private String devicesTrachVent="";
	private String devicesVentricularInternal="";
	private String devicesVentricularExternal="";
	private String devicesInternalPacemaker="";
	private String devicesWheelchair="";
	private String devicesGastro="";
	private String devicesIndwellingVas="";
	private String devicesIndwellingHamo="";
	private String devicesPeritoneal="";
	private String devicesOtherList="";
	private String enteredBy="";
	private Timestamp updatedOn=null;
	public int getDE2_ID()
	{
		return DE2_ID;
	}
	public void setDE2_ID(int de2_id)
	{
		DE2_ID = de2_id;
	}
	public String getSITE()
	{
		return SITE;
	}
	public void setSITE(String site)
	{
		SITE = site;
	}
	public String getPAT_NO()
	{
		return PAT_NO;
	}
	public void setPAT_NO(String pat_no)
	{
		PAT_NO = pat_no;
	}
	public int getEVENT_NO()
	{
		return EVENT_NO;
	}
	public void setEVENT_NO(int event_no)
	{
		EVENT_NO = event_no;
	}
	public int getWEEK_NO()
	{
		return WEEK_NO;
	}
	public void setWEEK_NO(int week_no)
	{
		WEEK_NO = week_no;
	}
	public String getRsAirwayLower()
	{
		return rsAirwayLower;
	}
	public void setRsAirwayLower(String rsAirwayLower)
	{
		this.rsAirwayLower = rsAirwayLower;
	}
	public String getRsAirwayUpper()
	{
		return rsAirwayUpper;
	}
	public void setRsAirwayUpper(String rsAirwayUpper)
	{
		this.rsAirwayUpper = rsAirwayUpper;
	}
	public String getRsChronicLungDisease()
	{
		return rsChronicLungDisease;
	}
	public void setRsChronicLungDisease(String rsChronicLungDisease)
	{
		this.rsChronicLungDisease = rsChronicLungDisease;
	}
	public String getRsOtherDisease()
	{
		return rsOtherDisease;
	}
	public void setRsOtherDisease(String rsOtherDisease)
	{
		this.rsOtherDisease = rsOtherDisease;
	}
	public String getRsOtherList()
	{
		return rsOtherList;
	}
	public void setRsOtherList(String rsOtherList)
	{
		this.rsOtherList = rsOtherList;
	}
	public String getHcStructuralDiseaseNotCyanotic()
	{
		return hcStructuralDiseaseNotCyanotic;
	}
	public void setHcStructuralDiseaseNotCyanotic(
			String hcStructuralDiseaseNotCyanotic)
	{
		this.hcStructuralDiseaseNotCyanotic = hcStructuralDiseaseNotCyanotic;
	}
	public String getHcStructuralDiseaseCyanotic()
	{
		return hcStructuralDiseaseCyanotic;
	}
	public void setHcStructuralDiseaseCyanotic(String hcStructuralDiseaseCyanotic)
	{
		this.hcStructuralDiseaseCyanotic = hcStructuralDiseaseCyanotic;
	}
	public String getHcPulmonary()
	{
		return hcPulmonary;
	}
	public void setHcPulmonary(String hcPulmonary)
	{
		this.hcPulmonary = hcPulmonary;
	}
	public String getHcSystemic()
	{
		return hcSystemic;
	}
	public void setHcSystemic(String hcSystemic)
	{
		this.hcSystemic = hcSystemic;
	}
	public String getHcVascular()
	{
		return hcVascular;
	}
	public void setHcVascular(String hcVascular)
	{
		this.hcVascular = hcVascular;
	}
	public String getHcVasculitis()
	{
		return hcVasculitis;
	}
	public void setHcVasculitis(String hcVasculitis)
	{
		this.hcVasculitis = hcVasculitis;
	}
	public String getHcOtherDisease()
	{
		return hcOtherDisease;
	}
	public void setHcOtherDisease(String hcOtherDisease)
	{
		this.hcOtherDisease = hcOtherDisease;
	}
	public String getHcOtherList()
	{
		return hcOtherList;
	}
	public void setHcOtherList(String hcOtherList)
	{
		this.hcOtherList = hcOtherList;
	}
	public String getNeuroSeizures()
	{
		return neuroSeizures;
	}
	public void setNeuroSeizures(String neuroSeizures)
	{
		this.neuroSeizures = neuroSeizures;
	}
	public String getNeuroHydrocphalus()
	{
		return neuroHydrocphalus;
	}
	public void setNeuroHydrocphalus(String neuroHydrocphalus)
	{
		this.neuroHydrocphalus = neuroHydrocphalus;
	}
	public String getNeuroStatic()
	{
		return neuroStatic;
	}
	public void setNeuroStatic(String neuroStatic)
	{
		this.neuroStatic = neuroStatic;
	}
	public String getNeuroProgressive()
	{
		return neuroProgressive;
	}
	public void setNeuroProgressive(String neuroProgressive)
	{
		this.neuroProgressive = neuroProgressive;
	}
	public String getNeuroMyopathy()
	{
		return neuroMyopathy;
	}
	public void setNeuroMyopathy(String neuroMyopathy)
	{
		this.neuroMyopathy = neuroMyopathy;
	}
	public String getNeuroOtherList()
	{
		return neuroOtherList;
	}
	public void setNeuroOtherList(String neuroOtherList)
	{
		this.neuroOtherList = neuroOtherList;
	}
	public String getGastroLiver()
	{
		return gastroLiver;
	}
	public void setGastroLiver(String gastroLiver)
	{
		this.gastroLiver = gastroLiver;
	}
	public String getGastroIntestinalBleeding()
	{
		return gastroIntestinalBleeding;
	}
	public void setGastroIntestinalBleeding(String gastroIntestinalBleeding)
	{
		this.gastroIntestinalBleeding = gastroIntestinalBleeding;
	}
	public String getGastroMalabsorbtion()
	{
		return gastroMalabsorbtion;
	}
	public void setGastroMalabsorbtion(String gastroMalabsorbtion)
	{
		this.gastroMalabsorbtion = gastroMalabsorbtion;
	}
	public String getGastroIntestinalDisease()
	{
		return gastroIntestinalDisease;
	}
	public void setGastroIntestinalDisease(String gastroIntestinalDisease)
	{
		this.gastroIntestinalDisease = gastroIntestinalDisease;
	}
	public String getGastroOtherList()
	{
		return gastroOtherList;
	}
	public void setGastroOtherList(String gastroOtherList)
	{
		this.gastroOtherList = gastroOtherList;
	}
	public String getRenalImpairment()
	{
		return renalImpairment;
	}
	public void setRenalImpairment(String renalImpairment)
	{
		this.renalImpairment = renalImpairment;
	}
	public String getRenalDisease()
	{
		return renalDisease;
	}
	public void setRenalDisease(String renalDisease)
	{
		this.renalDisease = renalDisease;
	}
	public String getRenalEletrolyte()
	{
		return renalEletrolyte;
	}
	public void setRenalEletrolyte(String renalEletrolyte)
	{
		this.renalEletrolyte = renalEletrolyte;
	}
	public String getRenalOtherList()
	{
		return renalOtherList;
	}
	public void setRenalOtherList(String renalOtherList)
	{
		this.renalOtherList = renalOtherList;
	}
	public String getMusJoint()
	{
		return musJoint;
	}
	public void setMusJoint(String musJoint)
	{
		this.musJoint = musJoint;
	}
	public String getMusSytemic()
	{
		return musSytemic;
	}
	public void setMusSytemic(String musSytemic)
	{
		this.musSytemic = musSytemic;
	}
	public String getMusOrthopaedic()
	{
		return musOrthopaedic;
	}
	public void setMusOrthopaedic(String musOrthopaedic)
	{
		this.musOrthopaedic = musOrthopaedic;
	}
	public String getMusScoliosis()
	{
		return musScoliosis;
	}
	public void setMusScoliosis(String musScoliosis)
	{
		this.musScoliosis = musScoliosis;
	}
	public String getMusCutaneous()
	{
		return musCutaneous;
	}
	public void setMusCutaneous(String musCutaneous)
	{
		this.musCutaneous = musCutaneous;
	}
	public String getMusOtherList()
	{
		return musOtherList;
	}
	public void setMusOtherList(String musOtherList)
	{
		this.musOtherList = musOtherList;
	}
	public String getHaeMalignancyHae()
	{
		return haeMalignancyHae;
	}
	public void setHaeMalignancyHae(String haeMalignancyHae)
	{
		this.haeMalignancyHae = haeMalignancyHae;
	}
	public String getHaeMalignancyOrgan()
	{
		return haeMalignancyOrgan;
	}
	public void setHaeMalignancyOrgan(String haeMalignancyOrgan)
	{
		this.haeMalignancyOrgan = haeMalignancyOrgan;
	}
	public String getHaeNonMalignancy()
	{
		return haeNonMalignancy;
	}
	public void setHaeNonMalignancy(String haeNonMalignancy)
	{
		this.haeNonMalignancy = haeNonMalignancy;
	}
	public String getHaePrimary()
	{
		return haePrimary;
	}
	public void setHaePrimary(String haePrimary)
	{
		this.haePrimary = haePrimary;
	}
	public String getHaeAcquired()
	{
		return haeAcquired;
	}
	public void setHaeAcquired(String haeAcquired)
	{
		this.haeAcquired = haeAcquired;
	}
	public String getHaeBone()
	{
		return haeBone;
	}
	public void setHaeBone(String haeBone)
	{
		this.haeBone = haeBone;
	}
	public String getHaeSolidOrgan()
	{
		return haeSolidOrgan;
	}
	public void setHaeSolidOrgan(String haeSolidOrgan)
	{
		this.haeSolidOrgan = haeSolidOrgan;
	}
	public String getHaeProthrombotic()
	{
		return haeProthrombotic;
	}
	public void setHaeProthrombotic(String haeProthrombotic)
	{
		this.haeProthrombotic = haeProthrombotic;
	}
	public String getHaeBleeding()
	{
		return haeBleeding;
	}
	public void setHaeBleeding(String haeBleeding)
	{
		this.haeBleeding = haeBleeding;
	}
	public String getHaeOtherList()
	{
		return haeOtherList;
	}
	public void setHaeOtherList(String haeOtherList)
	{
		this.haeOtherList = haeOtherList;
	}
	public String getEndoMetabolic()
	{
		return endoMetabolic;
	}
	public void setEndoMetabolic(String endoMetabolic)
	{
		this.endoMetabolic = endoMetabolic;
	}
	public String getEndoDiabetes()
	{
		return endoDiabetes;
	}
	public void setEndoDiabetes(String endoDiabetes)
	{
		this.endoDiabetes = endoDiabetes;
	}
	public String getEndoEndocrineDisease()
	{
		return endoEndocrineDisease;
	}
	public void setEndoEndocrineDisease(String endoEndocrineDisease)
	{
		this.endoEndocrineDisease = endoEndocrineDisease;
	}
	public String getEndoOtherList()
	{
		return endoOtherList;
	}
	public void setEndoOtherList(String endoOtherList)
	{
		this.endoOtherList = endoOtherList;
	}
	public String getAllKnown()
	{
		return allKnown;
	}
	public void setAllKnown(String allKnown)
	{
		this.allKnown = allKnown;
	}
	public String getAllOther()
	{
		return allOther;
	}
	public void setAllOther(String allOther)
	{
		this.allOther = allOther;
	}
	public String getAllOtherList()
	{
		return allOtherList;
	}
	public void setAllOtherList(String allOtherList)
	{
		this.allOtherList = allOtherList;
	}
	public String getDevicesOxygen()
	{
		return devicesOxygen;
	}
	public void setDevicesOxygen(String devicesOxygen)
	{
		this.devicesOxygen = devicesOxygen;
	}
	public String getDevicesTrachNoVent()
	{
		return devicesTrachNoVent;
	}
	public void setDevicesTrachNoVent(String devicesTrachNoVent)
	{
		this.devicesTrachNoVent = devicesTrachNoVent;
	}
	public String getDevicesTrachVent()
	{
		return devicesTrachVent;
	}
	public void setDevicesTrachVent(String devicesTrachVent)
	{
		this.devicesTrachVent = devicesTrachVent;
	}
	public String getDevicesVentricularInternal()
	{
		return devicesVentricularInternal;
	}
	public void setDevicesVentricularInternal(String devicesVentricularInternal)
	{
		this.devicesVentricularInternal = devicesVentricularInternal;
	}
	public String getDevicesVentricularExternal()
	{
		return devicesVentricularExternal;
	}
	public void setDevicesVentricularExternal(String devicesVentricularExternal)
	{
		this.devicesVentricularExternal = devicesVentricularExternal;
	}
	public String getDevicesInternalPacemaker()
	{
		return devicesInternalPacemaker;
	}
	public void setDevicesInternalPacemaker(String devicesInternalPacemaker)
	{
		this.devicesInternalPacemaker = devicesInternalPacemaker;
	}
	public String getDevicesWheelchair()
	{
		return devicesWheelchair;
	}
	public void setDevicesWheelchair(String devicesWheelchair)
	{
		this.devicesWheelchair = devicesWheelchair;
	}
	public String getDevicesGastro()
	{
		return devicesGastro;
	}
	public void setDevicesGastro(String devicesGastro)
	{
		this.devicesGastro = devicesGastro;
	}
	public String getDevicesIndwellingVas()
	{
		return devicesIndwellingVas;
	}
	public void setDevicesIndwellingVas(String devicesIndwellingVas)
	{
		this.devicesIndwellingVas = devicesIndwellingVas;
	}
	public String getDevicesIndwellingHamo()
	{
		return devicesIndwellingHamo;
	}
	public void setDevicesIndwellingHamo(String devicesIndwellingHamo)
	{
		this.devicesIndwellingHamo = devicesIndwellingHamo;
	}
	public String getDevicesPeritoneal()
	{
		return devicesPeritoneal;
	}
	public void setDevicesPeritoneal(String devicesPeritoneal)
	{
		this.devicesPeritoneal = devicesPeritoneal;
	}
	public String getDevicesOtherList()
	{
		return devicesOtherList;
	}
	public void setDevicesOtherList(String devicesOtherList)
	{
		this.devicesOtherList = devicesOtherList;
	}
	public String getEnteredBy()
	{
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy)
	{
		this.enteredBy = enteredBy;
	}
	public Timestamp getUpdatedOn()
	{
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn)
	{
		this.updatedOn = updatedOn;
	}
	
	/*
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String ="";
	private String haeMalignancyHae="";
	private String haeMalignancyOrgan="";
	private String haeNonMalignancy="";
	private String haePrimary="";
	private String haeAcquired="";
	private String haeBone="";
	private String haeSolidOrgan="";
	private String haeProthrombotic="";
	private String haeBleeding="";
	private String haeOtherList="";
	private String endoMetabolic="";
	private String endoDiabetes="";
	private String endoEndocrineDisease="";
	private String endoOtherList="";
	private String allKnown="";
	private String allOther="";
	private String allOtherList="";
	private String devicesOxygen="";
	private String devicesTrachNoVent="";
	private String devicesTrachVent="";
	private String devicesVentricularInternal="";
	private String devicesVentricularExternal="";
	private String devicesInternalPacemaker="";
	private String devicesWheelchair="";
	private String devicesGastro="";
	private String devicesIndwellingVas="";
	private String devicesIndwellingHamo="";
	private String devicesPeritoneal="";
	private String devicesOtherList="";
	 * */
	public String getDescOList(String s)
	{
		return s==null ? "Unknown" : s;
	}
	public boolean checkRespiratorySystem()
	{
		return  (rsAirwayLower!=null && rsAirwayLower.equals("Y")) ||
				(rsAirwayUpper!=null && rsAirwayUpper.equals("Y")) ||
				(rsChronicLungDisease!=null && rsChronicLungDisease.equals("Y")) ||
				(rsOtherDisease!=null && rsOtherDisease.equals("Y")) ||
				(rsOtherList!=null && rsOtherList.length()>0);
	}
	public boolean checkHeartCirculation()
	{
		return  (hcStructuralDiseaseNotCyanotic!=null && hcStructuralDiseaseNotCyanotic.equals("Y")) ||
				(hcStructuralDiseaseCyanotic!=null && hcStructuralDiseaseCyanotic.equals("Y")) ||
				(hcPulmonary!=null && hcPulmonary.equals("Y")) ||
				(hcSystemic!=null && hcSystemic.equals("Y")) ||
				(hcVascular!=null && hcVascular.equals("Y")) ||
				(hcVasculitis!=null && hcVasculitis.equals("Y")) ||
				(hcOtherDisease!=null && hcOtherDisease.equals("Y")) ||
				(hcOtherList!=null && hcOtherList.length()>0);
	}
	public boolean checkNeurologic()
	{
		return  (neuroSeizures!=null && neuroSeizures.equals("Y")) ||
				(neuroHydrocphalus!=null && neuroHydrocphalus.equals("Y")) ||
				(neuroStatic!=null && neuroStatic.equals("Y")) ||
				(neuroProgressive!=null && neuroProgressive.equals("Y")) ||
				(neuroMyopathy!=null && neuroMyopathy.equals("Y")) ||
				(neuroOtherList!=null && neuroOtherList.length()>0);
	}
	public boolean checkGastrointestinal()
	{
		return  (gastroLiver!=null && gastroLiver.equals("Y")) ||
				(gastroIntestinalBleeding!=null && gastroIntestinalBleeding.equals("Y")) ||
				(gastroMalabsorbtion!=null && gastroMalabsorbtion.equals("Y")) ||
				(gastroIntestinalDisease!=null && gastroIntestinalDisease.equals("Y")) ||
				(gastroOtherList!=null && gastroOtherList.length()>0);
	}
	public boolean checkRenal()
	{
		return  (renalImpairment!=null && renalImpairment.equals("Y")) ||
				(renalDisease!=null && renalDisease.equals("Y")) ||
				(renalEletrolyte!=null && renalEletrolyte.equals("Y")) ||
				(renalOtherList!=null && renalOtherList.length()>0);
	}
	public boolean checkMusculoskeletalCutaneous()
	{
		return  (musJoint!=null && musJoint.equals("Y")) ||
				(musSytemic!=null && musSytemic.equals("Y")) ||
				(musOrthopaedic!=null && musOrthopaedic.equals("Y")) ||
				(musScoliosis!=null && musScoliosis.equals("Y")) ||
				(musCutaneous!=null && musCutaneous.equals("Y")) ||
				(musOtherList!=null && musOtherList.length()>0);
	}
	public boolean checkHaematologicOncologic()
	{
		return  (haeMalignancyHae!=null && haeMalignancyHae.equals("Y")) ||
				(haeMalignancyOrgan!=null && haeMalignancyOrgan.equals("Y")) ||
				(haeNonMalignancy!=null && haeNonMalignancy.equals("Y")) ||
				(haePrimary!=null && haePrimary.equals("Y")) ||
				(haeAcquired!=null && haeAcquired.equals("Y")) ||
				(haeBone!=null && haeBone.equals("Y")) ||
				(haeSolidOrgan!=null && haeSolidOrgan.equals("Y")) ||
				(haeProthrombotic!=null && haeProthrombotic.equals("Y")) ||
				(haeBleeding!=null && haeBleeding.equals("Y")) ||
				(haeOtherList!=null && haeOtherList.length()>0);
	}
	public boolean checkEndocrineMetabolic()
	{
		return  (endoMetabolic!=null && endoMetabolic.equals("Y")) ||
				(endoDiabetes!=null && endoDiabetes.equals("Y")) ||
				(endoEndocrineDisease!=null && endoEndocrineDisease.equals("Y")) ||
				(endoOtherList!=null && endoOtherList.length()>0);
	}
	public boolean checkAllergiesContraIndications()
	{
		return  (allKnown!=null && allKnown.equals("Y")) ||
				(allOther!=null && allOther.equals("Y")) ||
				(allOtherList!=null && allOtherList.length()>0);
	}
	public boolean checkDevices()
	{
		return  (devicesOxygen!=null && devicesOxygen.equals("Y")) ||
				(devicesTrachNoVent!=null && devicesTrachNoVent.equals("Y")) ||
				(devicesTrachVent!=null && devicesTrachVent.equals("Y")) ||
				(devicesVentricularInternal!=null && devicesVentricularInternal.equals("Y")) ||
				(devicesVentricularExternal!=null && devicesVentricularExternal.equals("Y")) ||
				(devicesInternalPacemaker!=null && devicesInternalPacemaker.equals("Y")) ||
				(devicesWheelchair!=null && devicesWheelchair.equals("Y")) ||
				(devicesGastro!=null && devicesGastro.equals("Y")) ||
				(devicesIndwellingVas!=null && devicesIndwellingVas.equals("Y")) ||
				(devicesIndwellingHamo!=null && devicesIndwellingHamo.equals("Y")) ||
				(devicesPeritoneal!=null && devicesPeritoneal.equals("Y")) ||
				(devicesOtherList!=null && devicesOtherList.length()>0);
	}
	public String getLHS()
	{
		String sRespiratorySystem="<tr><td align='left' colspan='2'></td></tr>";
		if(checkRespiratorySystem())
		{
			sRespiratorySystem=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Respiratory System</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((rsAirwayLower!=null && rsAirwayLower.equals("Y")) ? "<li>Airway obstruction (lower) - Asthma</li>":"")+
								((rsAirwayUpper!=null && rsAirwayUpper.equals("Y")) ? "<li>Airway obstruction - upper eg. croup</li>":"")+
								((rsChronicLungDisease!=null && rsChronicLungDisease.equals("Y")) ? "<li>Chronic Lung disease</li>":"")+
								((rsOtherDisease!=null && rsOtherDisease.equals("Y")) ? "<li>Other Respiratory disease</li>":"")+
								((rsOtherList!=null && rsOtherList.length()>0) ? "<li>"+getDescOList(rsOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		
		String sHeartCirculation="";
		if(checkHeartCirculation())
		{
			sHeartCirculation=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Heart & Circulation</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((hcStructuralDiseaseNotCyanotic!=null && hcStructuralDiseaseNotCyanotic.equals("Y")) ? "<li>Structural Heart Disease - not cyanotic</li>":"")+
								((hcStructuralDiseaseCyanotic!=null && hcStructuralDiseaseCyanotic.equals("Y")) ? "<li>Structural Heart Disease cyanotic</li>":"")+
								((hcPulmonary!=null && hcPulmonary.equals("Y")) ? "<li>Pulmonary Hypertension</li>":"")+
								((hcSystemic!=null && hcSystemic.equals("Y")) ? "<li>Systemic Hypertension</li>":"")+
								((hcVascular!=null && hcVascular.equals("Y")) ? "<li>Vascular injury (traumatic)</li>":"")+
								((hcVasculitis!=null && hcVasculitis.equals("Y")) ? "<li>Vasculitis (including KD)</li>":"")+
								((hcOtherDisease!=null && hcOtherDisease.equals("Y")) ? "<li>Other disease affecting major vessells</li>":"")+
								((hcOtherList!=null && hcOtherList.length()>0) ? "<li>"+getDescOList(hcOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sNeurologic="";
		if(checkNeurologic())
		{
			sNeurologic=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Neurologic</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((neuroSeizures!=null && neuroSeizures.equals("Y")) ? "<li>Seizures</li>":"")+
								((neuroHydrocphalus!=null && neuroHydrocphalus.equals("Y")) ? "<li>Hydrocephalus</li>":"")+
								((neuroStatic!=null && neuroStatic.equals("Y")) ? "<li>Static Encephalopathy (incl. Cerebral Palsy)</li>":"")+
								((neuroProgressive!=null && neuroProgressive.equals("Y")) ? "<li>Progressive Encepahalopathy</li>":"")+
								((neuroMyopathy!=null && neuroMyopathy.equals("Y")) ? "<li>Myopathy / Neuropathy</li>":"")+
								((neuroOtherList!=null && neuroOtherList.length()>0) ? "<li>"+getDescOList(neuroOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sGastrointestinal="";
		if(checkGastrointestinal())
		{
			sGastrointestinal=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Gastrointestinal</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((gastroLiver!=null && gastroLiver.equals("Y")) ? "<li>Liver disease</li>":"")+
								((gastroIntestinalBleeding!=null && gastroIntestinalBleeding.equals("Y")) ? "<li>Gastro-Intestinal bleeding</li>":"")+
								((gastroMalabsorbtion!=null && gastroMalabsorbtion.equals("Y")) ? "<li>Malabsorbtion syndrome with FTT</li>":"")+
								((gastroIntestinalDisease!=null && gastroIntestinalDisease.equals("Y")) ? "<li>Gastrointestinal disease (not liver)</li>":"")+
								((gastroOtherList!=null && gastroOtherList.length()>0) ? "<li>"+getDescOList(gastroOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sRenal="";
		if(checkRenal())
		{
			sRenal=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Renal</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((renalImpairment!=null && renalImpairment.equals("Y")) ? "<li>Renal impairment - no dialysis</li>":"")+
								((renalDisease!=null && renalDisease.equals("Y")) ? "<li>Renal disease on dialysis (haemo/peritoneal)</li>":"")+
								((renalEletrolyte!=null && renalEletrolyte.equals("Y")) ? "<li>Electrolyte Disorder</li>":"")+
								((renalOtherList!=null && renalOtherList.length()>0) ? "<li>"+getDescOList(renalOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sMusculoskeletalCutaneous="";
		if(checkMusculoskeletalCutaneous())
		{
			sMusculoskeletalCutaneous=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Musculoskeletal & Cutaneous</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((musJoint!=null && musJoint.equals("Y")) ? "<li>Joint disease</li>":"")+
								((musSytemic!=null && musSytemic.equals("Y")) ? "<li>Systemic Rheumatologic disease</li>":"")+
								((musOrthopaedic!=null && musOrthopaedic.equals("Y")) ? "<li>Orthopaedic</li>":"")+
								((musScoliosis!=null && musScoliosis.equals("Y")) ? "<li>Scoliosis (severe)</li>":"")+
								((musCutaneous!=null && musCutaneous.equals("Y")) ? "<li>Cutaneous disease (severe)</li>":"")+
								((musOtherList!=null && musOtherList.length()>0) ? "<li>"+getDescOList(musOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		return 
			"<table cellpadding='0' cellspacing='0' width='100%'>"+
				sRespiratorySystem+
				sHeartCirculation+
				sNeurologic+
				sGastrointestinal+
				sRenal+
				sMusculoskeletalCutaneous+
			"</table>";
	}
	//Next week do here for the RHS
	public String getRHS()
	{
		String sHaematologicOncologic="<tr><td align='left' colspan='2'></td></tr>";
		if(checkHaematologicOncologic())
		{
			sHaematologicOncologic=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Haematologic - Oncologic</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((haeMalignancyHae!=null && haeMalignancyHae.equals("Y")) ? "<li>Malignancy haematologic</li>":"")+
								((haeMalignancyOrgan!=null && haeMalignancyOrgan.equals("Y")) ? "<li>Malignancy solid organ</li>":"")+
								((haeNonMalignancy!=null && haeNonMalignancy.equals("Y")) ? "<li>Non-malignant symptomatic tumour</li>":"")+
								((haePrimary!=null && haePrimary.equals("Y")) ? "<li>Priamry Immunodeficiency</li>":"")+								
								((haeAcquired!=null && haeAcquired.equals("Y")) ? "<li>Acquired Immunodeficiency</li>":"")+
								((haeBone!=null && haeBone.equals("Y")) ? "<li>Bone Marrow Transplant Recipient</li>":"")+
								((haeSolidOrgan!=null && haeSolidOrgan.equals("Y")) ? "<li>Solid Organ Transplant Recipient</li>":"")+
								((haeProthrombotic!=null && haeProthrombotic.equals("Y")) ? "<li>Pro-thrombotic disease</li>":"")+
								((haeBleeding!=null && haeBleeding.equals("Y")) ? "<li>Bleeding diasthesis</li>":"")+								
								((haeOtherList!=null && haeOtherList.length()>0) ? "<li>"+getDescOList(haeOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		
		String sEndocrineMetabolic="";
		if(checkEndocrineMetabolic())
		{
			sEndocrineMetabolic=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Endocrine - Metabolic</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((endoMetabolic!=null && endoMetabolic.equals("Y")) ? "<li>Metabolic disease</li>":"")+
								((endoDiabetes!=null && endoDiabetes.equals("Y")) ? "<li>Diabetes mellitus</li>":"")+
								((endoEndocrineDisease!=null && endoEndocrineDisease.equals("Y")) ? "<li>Endocrine disease (not diabetes)</li>":"")+
								((endoOtherList!=null && endoOtherList.length()>0) ? "<li>"+getDescOList(endoOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sAllergiesContraIndications="";
		if(checkAllergiesContraIndications())
		{
			sAllergiesContraIndications=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Allergies & Contra-Indications</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((allKnown!=null && allKnown.equals("Y")) ? "<li>Known severe drug allergy</li>":"")+
								((allOther!=null && allOther.equals("Y")) ? "<li>Other contra-indication to medication</li>":"")+
								((allOtherList!=null && allOtherList.length()>0) ? "<li>"+getDescOList(allOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sDevices="";
		if(checkDevices())
		{
			sDevices=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Devices</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((devicesOxygen!=null && devicesOxygen.equals("Y")) ? "<li>Oxygen (O2 > lmonth)</li>":"")+
								((devicesTrachNoVent!=null && devicesTrachNoVent.equals("Y")) ? "<li>Tracheostomy no ventilation</li>":"")+
								((devicesTrachVent!=null && devicesTrachVent.equals("Y")) ? "<li>Tracheostomy + Ventilation</li>":"")+
								((devicesVentricularInternal!=null && devicesVentricularInternal.equals("Y")) ? "<li>Ventricular Drain (internal)</li>":"")+
								((devicesVentricularExternal!=null && devicesVentricularExternal.equals("Y")) ? "<li>Ventricular Drain (external)</li>":"")+
								((devicesInternalPacemaker!=null && devicesInternalPacemaker.equals("Y")) ? "<li>Internal pacemaker / defibrillator</li>":"")+
								((devicesWheelchair!=null && devicesWheelchair.equals("Y")) ? "<li>Wheelchair</li>":"")+
								((devicesGastro!=null && devicesGastro.equals("Y")) ? "<li>Gastro / enterostomy tube</li>":"")+
								((devicesIndwellingVas!=null && devicesIndwellingVas.equals("Y")) ? "<li>Indwelling Vascular Access (not dialysis)</li>":"")+
								((devicesIndwellingHamo!=null && devicesIndwellingHamo.equals("Y")) ? "<li>Indwelling Haemo Dialysis catheter</li>":"")+
								((devicesPeritoneal!=null && devicesPeritoneal.equals("Y")) ? "<li>Peritoneal Dialysis catheter</li>":"")+								
								((devicesOtherList!=null && devicesOtherList.length()>0) ? "<li>"+getDescOList(devicesOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		return 
				"<table cellpadding='0' cellspacing='0' width='100%'>"+
					sHaematologicOncologic+
					sEndocrineMetabolic+
					sAllergiesContraIndications+
					sDevices+
				"</table>";
	}
	public String displayDiagnoses()
	{
		return 
		 "<table cellpadding='0' cellspacing='2' width='100%'>"+
		 "<tr><td width='50%' valign='top'>"+getLHS()+"</td><td width='50%' valign='top'>"+getRHS()+"</td></tr>"+
		 "</table>";
	}
}
