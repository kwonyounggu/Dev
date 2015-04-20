package com.sickkids.caliper.dao;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarmO_DE4Bean implements Serializable
{	
	private static final long serialVersionUID = -2389096320493109106L;
	
	private int DE4_ID=0;
	private String SITE="";
	private String PAT_NO="";
	private int EVENT_NO=0;
	private int WEEK_NO=0;
	
	private String rsUpperAirwayObstruction="";
	private String rsBronchospasm="";
	private String rsConsolidation="";
	private String rsPneumothorax="";	
	private String rsPleuralEffusion="";
	private String rsPulmonaryOedema="";
	private String rsPulmonaryHemorrhage="";
	private String rsThoracicSurgery="";
	private String rsThoracicTrauma="";
	private String rsOtherList="";
	
	
	private String hcArrhythmia="";
	private String hcCardiomypathy="";
	private String hcCardiacSurgery="";
	private String hcHaemorrhagicShock="";
	private String hcPericardialEffusion="";
	private String hcSepticShock="";
	private String hcSystemicHypertension="";
	private String hcPulmonaryHypertension="";
	private String hcBTshunt="";
	private String hcArterialThrombosis="";
	private String hcVenousThrombosis="";
	private String hcOtherList="";
	
	
	private String abAbdominalSepsis="";
	private String abAbdominalTrauma="";
	private String abBleedingIntraabdominal="";
	private String abSevereDiarrhoea="";
	private String abBowelPerforation="";
	private String abBowelObstruction="";
	private String abAbominalSurgery="";
	private String abAcuteLiverDysfunction="";
	private String abOtherList="";
	
	private String renalAcuteRenalDysfunction="";
	private String renalHyponatraemia="";
	private String renalElectrolyteDisorder="";
	private String renalUrologicSurgery="";
	private String renalOtherList="";
	
	private String haeMalignancyNotInRemission="";
	private String haeChemotherapy="";
	private String haeAnaemia="";
	private String haeThrombocytopenia="";
	private String haeLeucopeniaNeutropaenia="";
	private String haeCoagulopathy="";
	private String haeGraftVsHostDisease="";
	private String haeTransplantRejection="";
	private String haeSystemicInflammatoryDisease="";
	private String haeSevereCutaneousDisease="";
	private String haeOtherList="";
	
	
	private String endoAdrenalInsufficiency="";
	private String endoHyperAmmonemia="";
	private String endoHyperGlycaemia="";
	private String endoHypoGlycaemia="";
	private String endoKetoAcidosis="";
	private String endoOtherList="";
	
	
	private String neuroAcuteEncephalopathy="";
	private String neuroBulbarDysfunction="";
	private String neuroHydrocephalus="";
	private String neuroIntracranialHaemorrhage="";
	private String neuroParesisOfLimbs="";
	private String neuroParesisOfDiaphragm="";
	private String neuroNeurologicSurgery="";
	private String neuroSeizures="";
	private String neuroTraumaticBrainInjury="";
	private String neuroOtherList="";
	
	
	private String infectionCentralNervousSystem="";
	private String infectionSofttissue="";
	private String infectionRespiratory="";
	private String infectionIntraabdominal="";
	private String infectionUrinary="";
	private String infectionBlood="";
	private String infectionOtherPrimarySite="";
	private String infectionOtherList="";
	
	private String enteredBy="";
	private Timestamp updatedOn=null;
	
	private String eOthSpec="";
	
	public int getDE4_ID()
	{
		return DE4_ID;
	}
	public void setDE4_ID(int dE4_ID)
	{
		DE4_ID = dE4_ID;
	}
	public String getSITE()
	{
		return SITE;
	}
	public void setSITE(String sITE)
	{
		SITE = sITE;
	}
	public String getPAT_NO()
	{
		return PAT_NO;
	}
	public void setPAT_NO(String pAT_NO)
	{
		PAT_NO = pAT_NO;
	}
	public int getEVENT_NO()
	{
		return EVENT_NO;
	}
	public void setEVENT_NO(int eVENT_NO)
	{
		EVENT_NO = eVENT_NO;
	}
	public int getWEEK_NO()
	{
		return WEEK_NO;
	}
	public void setWEEK_NO(int wEEK_NO)
	{
		WEEK_NO = wEEK_NO;
	}
	public String getRsUpperAirwayObstruction()
	{
		return rsUpperAirwayObstruction;
	}
	public void setRsUpperAirwayObstruction(String rsUpperAirwayObstruction)
	{
		this.rsUpperAirwayObstruction = rsUpperAirwayObstruction;
	}
	public String getRsBronchospasm()
	{
		return rsBronchospasm;
	}
	public void setRsBronchospasm(String rsBronchospasm)
	{
		this.rsBronchospasm = rsBronchospasm;
	}
	public String getRsConsolidation()
	{
		return rsConsolidation;
	}
	public void setRsConsolidation(String rsConsolidation)
	{
		this.rsConsolidation = rsConsolidation;
	}
	public String getRsPneumothorax()
	{
		return rsPneumothorax;
	}
	public void setRsPneumothorax(String rsPneumothorax)
	{
		this.rsPneumothorax = rsPneumothorax;
	}
	public String getRsPleuralEffusion()
	{
		return rsPleuralEffusion;
	}
	public void setRsPleuralEffusion(String rsPleuralEffusion)
	{
		this.rsPleuralEffusion = rsPleuralEffusion;
	}
	public String getRsPulmonaryOedema()
	{
		return rsPulmonaryOedema;
	}
	public void setRsPulmonaryOedema(String rsPulmonaryOedema)
	{
		this.rsPulmonaryOedema = rsPulmonaryOedema;
	}
	public String getRsPulmonaryHemorrhage()
	{
		return rsPulmonaryHemorrhage;
	}
	public void setRsPulmonaryHemorrhage(String rsPulmonaryHemorrhage)
	{
		this.rsPulmonaryHemorrhage = rsPulmonaryHemorrhage;
	}
	public String getRsThoracicSurgery()
	{
		return rsThoracicSurgery;
	}
	public void setRsThoracicSurgery(String rsThoracicSurgery)
	{
		this.rsThoracicSurgery = rsThoracicSurgery;
	}
	public String getRsThoracicTrauma()
	{
		return rsThoracicTrauma;
	}
	public void setRsThoracicTrauma(String rsThoracicTrauma)
	{
		this.rsThoracicTrauma = rsThoracicTrauma;
	}
	public String getRsOtherList()
	{
		return rsOtherList;
	}
	public void setRsOtherList(String rsOtherList)
	{
		this.rsOtherList = rsOtherList;
	}
	public String getHcArrhythmia()
	{
		return hcArrhythmia;
	}
	public void setHcArrhythmia(String hcArrhythmia)
	{
		this.hcArrhythmia = hcArrhythmia;
	}
	public String getHcCardiomypathy()
	{
		return hcCardiomypathy;
	}
	public void setHcCardiomypathy(String hcCardiomypathy)
	{
		this.hcCardiomypathy = hcCardiomypathy;
	}
	public String getHcCardiacSurgery()
	{
		return hcCardiacSurgery;
	}
	public void setHcCardiacSurgery(String hcCardiacSurgery)
	{
		this.hcCardiacSurgery = hcCardiacSurgery;
	}
	public String getHcHaemorrhagicShock()
	{
		return hcHaemorrhagicShock;
	}
	public void setHcHaemorrhagicShock(String hcHaemorrhagicShock)
	{
		this.hcHaemorrhagicShock = hcHaemorrhagicShock;
	}
	public String getHcPericardialEffusion()
	{
		return hcPericardialEffusion;
	}
	public void setHcPericardialEffusion(String hcPericardialEffusion)
	{
		this.hcPericardialEffusion = hcPericardialEffusion;
	}
	public String getHcSepticShock()
	{
		return hcSepticShock;
	}
	public void setHcSepticShock(String hcSepticShock)
	{
		this.hcSepticShock = hcSepticShock;
	}
	public String getHcSystemicHypertension()
	{
		return hcSystemicHypertension;
	}
	public void setHcSystemicHypertension(String hcSystemicHypertension)
	{
		this.hcSystemicHypertension = hcSystemicHypertension;
	}
	public String getHcPulmonaryHypertension()
	{
		return hcPulmonaryHypertension;
	}
	public void setHcPulmonaryHypertension(String hcPulmonaryHypertension)
	{
		this.hcPulmonaryHypertension = hcPulmonaryHypertension;
	}
	public String getHcBTshunt()
	{
		return hcBTshunt;
	}
	public void setHcBTshunt(String hcBTshunt)
	{
		this.hcBTshunt = hcBTshunt;
	}
	public String getHcArterialThrombosis()
	{
		return hcArterialThrombosis;
	}
	public void setHcArterialThrombosis(String hcArterialThrombosis)
	{
		this.hcArterialThrombosis = hcArterialThrombosis;
	}
	public String getHcVenousThrombosis()
	{
		return hcVenousThrombosis;
	}
	public void setHcVenousThrombosis(String hcVenousThrombosis)
	{
		this.hcVenousThrombosis = hcVenousThrombosis;
	}
	public String getHcOtherList()
	{
		return hcOtherList;
	}
	public void setHcOtherList(String hcOtherList)
	{
		this.hcOtherList = hcOtherList;
	}
	public String getAbAbdominalSepsis()
	{
		return abAbdominalSepsis;
	}
	public void setAbAbdominalSepsis(String abAbdominalSepsis)
	{
		this.abAbdominalSepsis = abAbdominalSepsis;
	}
	public String getAbAbdominalTrauma()
	{
		return abAbdominalTrauma;
	}
	public void setAbAbdominalTrauma(String abAbdominalTrauma)
	{
		this.abAbdominalTrauma = abAbdominalTrauma;
	}
	public String getAbBleedingIntraabdominal()
	{
		return abBleedingIntraabdominal;
	}
	public void setAbBleedingIntraabdominal(String abBleedingIntraabdominal)
	{
		this.abBleedingIntraabdominal = abBleedingIntraabdominal;
	}
	public String getAbSevereDiarrhoea()
	{
		return abSevereDiarrhoea;
	}
	public void setAbSevereDiarrhoea(String abSevereDiarrhoea)
	{
		this.abSevereDiarrhoea = abSevereDiarrhoea;
	}
	public String getAbBowelPerforation()
	{
		return abBowelPerforation;
	}
	public void setAbBowelPerforation(String abBowelPerforation)
	{
		this.abBowelPerforation = abBowelPerforation;
	}
	public String getAbBowelObstruction()
	{
		return abBowelObstruction;
	}
	public void setAbBowelObstruction(String abBowelObstruction)
	{
		this.abBowelObstruction = abBowelObstruction;
	}
	public String getAbAbominalSurgery()
	{
		return abAbominalSurgery;
	}
	public void setAbAbominalSurgery(String abAbominalSurgery)
	{
		this.abAbominalSurgery = abAbominalSurgery;
	}
	public String getAbAcuteLiverDysfunction()
	{
		return abAcuteLiverDysfunction;
	}
	public void setAbAcuteLiverDysfunction(String abAcuteLiverDysfunction)
	{
		this.abAcuteLiverDysfunction = abAcuteLiverDysfunction;
	}
	public String getAbOtherList()
	{
		return abOtherList;
	}
	public void setAbOtherList(String abOtherList)
	{
		this.abOtherList = abOtherList;
	}
	public String getRenalAcuteRenalDysfunction()
	{
		return renalAcuteRenalDysfunction;
	}
	public void setRenalAcuteRenalDysfunction(String renalAcuteRenalDysfunction)
	{
		this.renalAcuteRenalDysfunction = renalAcuteRenalDysfunction;
	}
	public String getRenalHyponatraemia()
	{
		return renalHyponatraemia;
	}
	public void setRenalHyponatraemia(String renalHyponatraemia)
	{
		this.renalHyponatraemia = renalHyponatraemia;
	}
	
	public String getRenalElectrolyteDisorder()
	{
		return renalElectrolyteDisorder;
	}
	public void setRenalElectrolyteDisorder(String renalElectrolyteDisorder)
	{
		this.renalElectrolyteDisorder = renalElectrolyteDisorder;
	}
	public String getRenalUrologicSurgery()
	{
		return renalUrologicSurgery;
	}
	public void setRenalUrologicSurgery(String renalUrologicSurgery)
	{
		this.renalUrologicSurgery = renalUrologicSurgery;
	}
	public String getRenalOtherList()
	{
		return renalOtherList;
	}
	public void setRenalOtherList(String renalOtherList)
	{
		this.renalOtherList = renalOtherList;
	}
	public String getHaeMalignancyNotInRemission()
	{
		return haeMalignancyNotInRemission;
	}
	public void setHaeMalignancyNotInRemission(String haeMalignancyNotInRemission)
	{
		this.haeMalignancyNotInRemission = haeMalignancyNotInRemission;
	}
	public String getHaeChemotherapy()
	{
		return haeChemotherapy;
	}
	public void setHaeChemotherapy(String haeChemotherapy)
	{
		this.haeChemotherapy = haeChemotherapy;
	}
	public String getHaeAnaemia()
	{
		return haeAnaemia;
	}
	public void setHaeAnaemia(String haeAnaemia)
	{
		this.haeAnaemia = haeAnaemia;
	}
	public String getHaeThrombocytopenia()
	{
		return haeThrombocytopenia;
	}
	public void setHaeThrombocytopenia(String haeThrombocytopenia)
	{
		this.haeThrombocytopenia = haeThrombocytopenia;
	}
	public String getHaeLeucopeniaNeutropaenia()
	{
		return haeLeucopeniaNeutropaenia;
	}
	public void setHaeLeucopeniaNeutropaenia(String haeLeucopeniaNeutropaenia)
	{
		this.haeLeucopeniaNeutropaenia = haeLeucopeniaNeutropaenia;
	}
	public String getHaeCoagulopathy()
	{
		return haeCoagulopathy;
	}
	public void setHaeCoagulopathy(String haeCoagulopathy)
	{
		this.haeCoagulopathy = haeCoagulopathy;
	}
	public String getHaeGraftVsHostDisease()
	{
		return haeGraftVsHostDisease;
	}
	public void setHaeGraftVsHostDisease(String haeGraftVsHostDisease)
	{
		this.haeGraftVsHostDisease = haeGraftVsHostDisease;
	}
	public String getHaeTransplantRejection()
	{
		return haeTransplantRejection;
	}
	public void setHaeTransplantRejection(String haeTransplantRejection)
	{
		this.haeTransplantRejection = haeTransplantRejection;
	}
	public String getHaeSystemicInflammatoryDisease()
	{
		return haeSystemicInflammatoryDisease;
	}
	public void setHaeSystemicInflammatoryDisease(
			String haeSystemicInflammatoryDisease)
	{
		this.haeSystemicInflammatoryDisease = haeSystemicInflammatoryDisease;
	}
	public String getHaeSevereCutaneousDisease()
	{
		return haeSevereCutaneousDisease;
	}
	public void setHaeSevereCutaneousDisease(String haeSevereCutaneousDisease)
	{
		this.haeSevereCutaneousDisease = haeSevereCutaneousDisease;
	}
	public String getHaeOtherList()
	{
		return haeOtherList;
	}
	public void setHaeOtherList(String haeOtherList)
	{
		this.haeOtherList = haeOtherList;
	}
	public String getEndoAdrenalInsufficiency()
	{
		return endoAdrenalInsufficiency;
	}
	public void setEndoAdrenalInsufficiency(String endoAdrenalInsufficiency)
	{
		this.endoAdrenalInsufficiency = endoAdrenalInsufficiency;
	}
	public String getEndoHyperAmmonemia()
	{
		return endoHyperAmmonemia;
	}
	public void setEndoHyperAmmonemia(String endoHyperAmmonemia)
	{
		this.endoHyperAmmonemia = endoHyperAmmonemia;
	}
	public String getEndoHyperGlycaemia()
	{
		return endoHyperGlycaemia;
	}
	public void setEndoHyperGlycaemia(String endoHyperGlycaemia)
	{
		this.endoHyperGlycaemia = endoHyperGlycaemia;
	}
	public String getEndoHypoGlycaemia()
	{
		return endoHypoGlycaemia;
	}
	public void setEndoHypoGlycaemia(String endoHypoGlycaemia)
	{
		this.endoHypoGlycaemia = endoHypoGlycaemia;
	}
	public String getEndoKetoAcidosis()
	{
		return endoKetoAcidosis;
	}
	public void setEndoKetoAcidosis(String endoKetoAcidosis)
	{
		this.endoKetoAcidosis = endoKetoAcidosis;
	}
	public String getEndoOtherList()
	{
		return endoOtherList;
	}
	public void setEndoOtherList(String endoOtherList)
	{
		this.endoOtherList = endoOtherList;
	}
	public String getNeuroAcuteEncephalopathy()
	{
		return neuroAcuteEncephalopathy;
	}
	public void setNeuroAcuteEncephalopathy(String neuroAcuteEncephalopathy)
	{
		this.neuroAcuteEncephalopathy = neuroAcuteEncephalopathy;
	}
	public String getNeuroBulbarDysfunction()
	{
		return neuroBulbarDysfunction;
	}
	public void setNeuroBulbarDysfunction(String neuroBulbarDysfunction)
	{
		this.neuroBulbarDysfunction = neuroBulbarDysfunction;
	}
	public String getNeuroHydrocephalus()
	{
		return neuroHydrocephalus;
	}
	public void setNeuroHydrocephalus(String neuroHydrocephalus)
	{
		this.neuroHydrocephalus = neuroHydrocephalus;
	}
	public String getNeuroIntracranialHaemorrhage()
	{
		return neuroIntracranialHaemorrhage;
	}
	public void setNeuroIntracranialHaemorrhage(String neuroIntracranialHaemorrhage)
	{
		this.neuroIntracranialHaemorrhage = neuroIntracranialHaemorrhage;
	}
	public String getNeuroParesisOfLimbs()
	{
		return neuroParesisOfLimbs;
	}
	public void setNeuroParesisOfLimbs(String neuroParesisOfLimbs)
	{
		this.neuroParesisOfLimbs = neuroParesisOfLimbs;
	}
	public String getNeuroParesisOfDiaphragm()
	{
		return neuroParesisOfDiaphragm;
	}
	public void setNeuroParesisOfDiaphragm(String neuroParesisOfDiaphragm)
	{
		this.neuroParesisOfDiaphragm = neuroParesisOfDiaphragm;
	}
	public String getNeuroNeurologicSurgery()
	{
		return neuroNeurologicSurgery;
	}
	public void setNeuroNeurologicSurgery(String neuroNeurologicSurgery)
	{
		this.neuroNeurologicSurgery = neuroNeurologicSurgery;
	}
	public String getNeuroSeizures()
	{
		return neuroSeizures;
	}
	public void setNeuroSeizures(String neuroSeizures)
	{
		this.neuroSeizures = neuroSeizures;
	}
	public String getNeuroTraumaticBrainInjury()
	{
		return neuroTraumaticBrainInjury;
	}
	public void setNeuroTraumaticBrainInjury(String neuroTraumaticBrainInjury)
	{
		this.neuroTraumaticBrainInjury = neuroTraumaticBrainInjury;
	}
	public String getNeuroOtherList()
	{
		return neuroOtherList;
	}
	public void setNeuroOtherList(String neuroOtherList)
	{
		this.neuroOtherList = neuroOtherList;
	}
	public String getInfectionCentralNervousSystem()
	{
		return infectionCentralNervousSystem;
	}
	public void setInfectionCentralNervousSystem(
			String infectionCentralNervousSystem)
	{
		this.infectionCentralNervousSystem = infectionCentralNervousSystem;
	}
	public String getInfectionSofttissue()
	{
		return infectionSofttissue;
	}
	public void setInfectionSofttissue(String infectionSofttissue)
	{
		this.infectionSofttissue = infectionSofttissue;
	}
	public String getInfectionRespiratory()
	{
		return infectionRespiratory;
	}
	public void setInfectionRespiratory(String infectionRespiratory)
	{
		this.infectionRespiratory = infectionRespiratory;
	}
	public String getInfectionIntraabdominal()
	{
		return infectionIntraabdominal;
	}
	public void setInfectionIntraabdominal(String infectionIntraabdominal)
	{
		this.infectionIntraabdominal = infectionIntraabdominal;
	}
	public String getInfectionUrinary()
	{
		return infectionUrinary;
	}
	public void setInfectionUrinary(String infectionUrinary)
	{
		this.infectionUrinary = infectionUrinary;
	}
	public String getInfectionBlood()
	{
		return infectionBlood;
	}
	public void setInfectionBlood(String infectionBlood)
	{
		this.infectionBlood = infectionBlood;
	}
	public String getInfectionOtherPrimarySite()
	{
		return infectionOtherPrimarySite;
	}
	public void setInfectionOtherPrimarySite(String infectionOtherPrimarySite)
	{
		this.infectionOtherPrimarySite = infectionOtherPrimarySite;
	}
	public String getInfectionOtherList()
	{
		return infectionOtherList;
	}
	public void setInfectionOtherList(String infectionOtherList)
	{
		this.infectionOtherList = infectionOtherList;
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
	public String geteOthSpec()
	{
		return eOthSpec;
	}
	public void seteOthSpec(String eOthSpec)
	{
		this.eOthSpec = eOthSpec;
	}

	
	
	public String getDescOList(String s)
	{
		return s==null ? "Unknown" : s;
	}
	public boolean checkRespiratorySystem()
	{
		return  (rsUpperAirwayObstruction!=null && rsUpperAirwayObstruction.equals("Y")) ||
				(rsBronchospasm!=null && rsBronchospasm.equals("Y")) ||
				(rsConsolidation!=null && rsConsolidation.equals("Y")) ||
				(rsPneumothorax!=null && rsPneumothorax.equals("Y")) ||			
				(rsPleuralEffusion!=null && rsPleuralEffusion.equals("Y")) ||
				(rsPulmonaryOedema!=null && rsPulmonaryOedema.equals("Y")) ||
				(rsPulmonaryHemorrhage!=null && rsPulmonaryHemorrhage.equals("Y")) ||
				(rsThoracicSurgery!=null && rsThoracicSurgery.equals("Y")) ||
				(rsThoracicTrauma!=null && rsThoracicTrauma.equals("Y")) ||
				
				(rsOtherList!=null && rsOtherList.length()>0);
	}
	public boolean checkHeartCirculation()
	{
		return  (hcArrhythmia!=null && hcArrhythmia.equals("Y")) ||
				(hcCardiomypathy!=null && hcCardiomypathy.equals("Y")) ||
				(hcCardiacSurgery!=null && hcCardiacSurgery.equals("Y")) ||
				(hcHaemorrhagicShock!=null && hcHaemorrhagicShock.equals("Y")) ||
				(hcPericardialEffusion!=null && hcPericardialEffusion.equals("Y")) ||
				(hcSepticShock!=null && hcSepticShock.equals("Y")) ||
				(hcSystemicHypertension!=null && hcSystemicHypertension.equals("Y")) ||
				(hcPulmonaryHypertension!=null && hcPulmonaryHypertension.equals("Y")) ||
				(hcBTshunt!=null && hcBTshunt.equals("Y")) ||
				(hcArterialThrombosis!=null && hcArterialThrombosis.equals("Y")) ||
				(hcVenousThrombosis!=null && hcVenousThrombosis.equals("Y")) ||
				(hcOtherList!=null && hcOtherList.length()>0);
	}
	public boolean checkAbdominal()
	{
		return  (abAbdominalSepsis!=null && abAbdominalSepsis.equals("Y")) ||
				(abAbdominalTrauma!=null && abAbdominalTrauma.equals("Y")) ||
				(abBleedingIntraabdominal!=null && abBleedingIntraabdominal.equals("Y")) ||
				(abSevereDiarrhoea!=null && abSevereDiarrhoea.equals("Y")) ||
				(abBowelPerforation!=null && abBowelPerforation.equals("Y")) ||
				(abBowelObstruction!=null && abBowelObstruction.equals("Y")) ||
				(abAbominalSurgery!=null && abAbominalSurgery.equals("Y")) ||
				(abAcuteLiverDysfunction!=null && abAcuteLiverDysfunction.equals("Y")) ||
				(abOtherList!=null && abOtherList.length()>0);
	}
	public boolean checkRenal()
	{
		return  (renalAcuteRenalDysfunction!=null && renalAcuteRenalDysfunction.equals("Y")) ||
				(renalHyponatraemia!=null && renalHyponatraemia.equals("Y")) ||
				(renalElectrolyteDisorder!=null && renalElectrolyteDisorder.equals("Y")) ||
				(renalUrologicSurgery!=null && renalUrologicSurgery.equals("Y")) ||
				(renalOtherList!=null && renalOtherList.length()>0);
	}
	public boolean checkHaematologic()
	{
		return  (haeMalignancyNotInRemission!=null && haeMalignancyNotInRemission.equals("Y")) ||
				(haeChemotherapy!=null && haeChemotherapy.equals("Y")) ||
				(haeAnaemia!=null && haeAnaemia.equals("Y")) ||
				(haeThrombocytopenia!=null && haeThrombocytopenia.equals("Y")) ||
				(haeLeucopeniaNeutropaenia!=null && haeLeucopeniaNeutropaenia.equals("Y")) ||
				(haeCoagulopathy!=null && haeCoagulopathy.equals("Y")) ||
				(haeGraftVsHostDisease!=null && haeGraftVsHostDisease.equals("Y")) ||
				(haeTransplantRejection!=null && haeTransplantRejection.equals("Y")) ||
				(haeSystemicInflammatoryDisease!=null && haeSystemicInflammatoryDisease.equals("Y")) ||
				(haeSevereCutaneousDisease!=null && haeSevereCutaneousDisease.equals("Y")) ||
				(haeOtherList!=null && haeOtherList.length()>0);
	}
	public boolean checkEndocrine()
	{
		return  (endoAdrenalInsufficiency!=null && endoAdrenalInsufficiency.equals("Y")) ||
				(endoHyperAmmonemia!=null && endoHyperAmmonemia.equals("Y")) ||
				(endoHyperGlycaemia!=null && endoHyperGlycaemia.equals("Y")) ||
				(endoHypoGlycaemia!=null && endoHypoGlycaemia.equals("Y")) ||
				(endoKetoAcidosis!=null && endoKetoAcidosis.equals("Y")) ||
				(endoOtherList!=null && endoOtherList.length()>0);
	}
	public boolean checkNeurologic()
	{
		return  (neuroAcuteEncephalopathy!=null && neuroAcuteEncephalopathy.equals("Y")) ||
				(neuroBulbarDysfunction!=null && neuroBulbarDysfunction.equals("Y")) ||
				(neuroHydrocephalus!=null && neuroHydrocephalus.equals("Y")) ||
				(neuroIntracranialHaemorrhage!=null && neuroIntracranialHaemorrhage.equals("Y")) ||
				(neuroParesisOfLimbs!=null && neuroParesisOfLimbs.equals("Y")) ||
				(neuroParesisOfDiaphragm!=null && neuroParesisOfDiaphragm.equals("Y")) ||
				(neuroNeurologicSurgery!=null && neuroNeurologicSurgery.equals("Y")) ||
				(neuroSeizures!=null && neuroSeizures.equals("Y")) ||
				(neuroTraumaticBrainInjury!=null && neuroTraumaticBrainInjury.equals("Y")) ||
				(neuroOtherList!=null && neuroOtherList.length()>0);
	}
	public boolean checkInfection()
	{
		/*
		return  (infectionCentralNervousSystem!=null && infectionCentralNervousSystem.equals("Y")) ||
				(infectionSofttissue!=null && infectionSofttissue.equals("Y")) ||
				(infectionRespiratory!=null && infectionRespiratory.equals("Y")) ||
				(infectionIntraabdominal!=null && infectionIntraabdominal.equals("Y")) ||
				(infectionUrinary!=null && infectionUrinary.equals("Y")) ||
				(infectionBlood!=null && infectionBlood.equals("Y")) ||
				(infectionOtherPrimarySite!=null && infectionOtherPrimarySite.equals("Y")) ||
				(infectionOtherList!=null && infectionOtherList.length()>0);
		*/
	 return (infectionCentralNervousSystem!=null) ||
			(infectionSofttissue!=null) ||
			(infectionRespiratory!=null) ||
			(infectionIntraabdominal!=null) ||
			(infectionUrinary!=null) ||
			(infectionBlood!=null) ||
			(infectionOtherPrimarySite!=null) ||
			(infectionOtherList!=null && infectionOtherList.length()>0);
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
								((rsUpperAirwayObstruction!=null && rsUpperAirwayObstruction.equals("Y")) ? "<li>Upper airway obstruction</li>":"")+
								((rsBronchospasm!=null && rsBronchospasm.equals("Y")) ? "<li>Bronchospasm</li>":"")+
								((rsConsolidation!=null && rsConsolidation.equals("Y")) ? "<li>Consolidation/collapse of lung parenchyma</li>":"")+
								((rsPneumothorax!=null && rsPneumothorax.equals("Y")) ? "<li>Pneumothorax</li>":"")+
								((rsPleuralEffusion!=null && rsPleuralEffusion.equals("Y")) ? "<li>Pleural effusion</li>":"")+
								((rsPulmonaryOedema!=null && rsPulmonaryOedema.equals("Y")) ? "<li>Pulmonary oedema</li>":"")+
								((rsPulmonaryHemorrhage!=null && rsPulmonaryHemorrhage.equals("Y")) ? "<li>Pulmonary haemorrhage</li>":"")+
								((rsThoracicSurgery!=null && rsThoracicSurgery.equals("Y")) ? "<li>Recent thoracic surgery (96 hours)</li>":"")+
								((rsThoracicTrauma!=null && rsThoracicTrauma.equals("Y")) ? "<li>Thoracic trauma</li>":"")+							
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
								((hcArrhythmia!=null && hcArrhythmia.equals("Y")) ? "<li>Arrhythmia</li>":"")+
								((hcCardiomypathy!=null && hcCardiomypathy.equals("Y")) ? "<li>Cardiomypathy/cardiogenic shock</li>":"")+
								((hcCardiacSurgery!=null && hcCardiacSurgery.equals("Y")) ? "<li>Recent cardiac surgery (96 hours)</li>":"")+
								((hcHaemorrhagicShock!=null && hcHaemorrhagicShock.equals("Y")) ? "<li>Haemorrhagic shock</li>":"")+
								((hcPericardialEffusion!=null && hcPericardialEffusion.equals("Y")) ? "<li>Pericardial effusion</li>":"")+
								((hcSepticShock!=null && hcSepticShock.equals("Y")) ? "<li>Septic shock</li>":"")+
								((hcSystemicHypertension!=null && hcSystemicHypertension.equals("Y")) ? "<li>Systemic hypertension</li>":"")+
								((hcPulmonaryHypertension!=null && hcPulmonaryHypertension.equals("Y")) ? "<li>Pulmonary hypertension</li>":"")+
								((hcBTshunt!=null && hcBTshunt.equals("Y")) ? "<li>BT-shunt or duct-dependent circulation</li>":"")+
								((hcArterialThrombosis!=null && hcArterialThrombosis.equals("Y")) ? "<li>Arterial thrombosis</li>":"")+
								((hcVenousThrombosis!=null && hcVenousThrombosis.equals("Y")) ? "<li>Venous thrombosis</li>":"")+						
								((hcOtherList!=null && hcOtherList.length()>0) ? "<li>"+getDescOList(hcOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sAbdominal="";
		if(checkAbdominal())
		{
			sAbdominal=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Abdominal</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((abAbdominalSepsis!=null && abAbdominalSepsis.equals("Y")) ? "<li>Abdominal sepsis</li>":"")+
								((abAbdominalTrauma!=null && abAbdominalTrauma.equals("Y")) ? "<li>Abdominal trauma</li>":"")+
								((abBleedingIntraabdominal!=null && abBleedingIntraabdominal.equals("Y")) ? "<li>Bleeding intra-abdominal/GI tract</li>":"")+
								((abSevereDiarrhoea!=null && abSevereDiarrhoea.equals("Y")) ? "<li>Severe diarrhoea/vomiting</li>":"")+
								((abBowelPerforation!=null && abBowelPerforation.equals("Y")) ? "<li>Bowel perforation</li>":"")+
								((abBowelObstruction!=null && abBowelObstruction.equals("Y")) ? "<li>Bowel obstruction</li>":"")+
								((abAbominalSurgery!=null && abAbominalSurgery.equals("Y")) ? "<li>Recent abominal surgery (96 hours)</li>":"")+
								((abAcuteLiverDysfunction!=null && abAcuteLiverDysfunction.equals("Y")) ? "<li>Acute liver dysfunction</li>":"")+
								((abOtherList!=null && abOtherList.length()>0) ? "<li>"+getDescOList(abOtherList)+"</li>":"")+
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
								((renalAcuteRenalDysfunction!=null && renalAcuteRenalDysfunction.equals("Y")) ? "<li>Acute renal dysfunction</li>":"")+
								((renalHyponatraemia!=null && renalHyponatraemia.equals("Y")) ? "<li>Hyponatraemia</li>":"")+
								((renalElectrolyteDisorder!=null && renalElectrolyteDisorder.equals("Y")) ? "<li>Other electrolyte disorder</li>":"")+
								((renalUrologicSurgery!=null && renalUrologicSurgery.equals("Y")) ? "<li>Recent renal/urologic surgery</li>":"")+
								((renalOtherList!=null && renalOtherList.length()>0) ? "<li>"+getDescOList(renalOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		
		return 
			"<table cellpadding='0' cellspacing='0' width='100%'>"+
				sRespiratorySystem+
				sHeartCirculation+
				sAbdominal+
				sRenal+
			"</table>";
	}
	
	//Next week do here for the RHS
	public String getRHS()
	{
		String sHaematologic="<tr><td align='left' colspan='2'></td></tr>";
		if(checkHaematologic())
		{
			sHaematologic=
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Haematologic - Oncologic</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((haeMalignancyNotInRemission!=null && haeMalignancyNotInRemission.equals("Y")) ? "<li>Malignancy not in remission</li>":"")+
								((haeChemotherapy!=null && haeChemotherapy.equals("Y")) ? "<li>Chemotherapy radiotherapy effects</li>":"")+
								((haeAnaemia!=null && haeAnaemia.equals("Y")) ? "<li>Anaemia</li>":"")+
								((haeThrombocytopenia!=null && haeThrombocytopenia.equals("Y")) ? "<li>Thrombocytopenia</li>":"")+
								((haeLeucopeniaNeutropaenia!=null && haeLeucopeniaNeutropaenia.equals("Y")) ? "<li>Leucopenia/neutropaenia&lt;1x10<sup style='font-size:9px;'>9</sup>/mm<sup style='font-size:9px;'>3</sup></li>":"")+
								((haeCoagulopathy!=null && haeCoagulopathy.equals("Y")) ? "<li>Coagulopathy</li>":"")+
								((haeGraftVsHostDisease!=null && haeGraftVsHostDisease.equals("Y")) ? "<li>Graft versus host disease</li>":"")+
								((haeTransplantRejection!=null && haeTransplantRejection.equals("Y")) ? "<li>Transplant rejection</li>":"")+
								((haeSystemicInflammatoryDisease!=null && haeSystemicInflammatoryDisease.equals("Y")) ? "<li>Systemic inflammatory disease</li>":"")+
								((haeSevereCutaneousDisease!=null && haeSevereCutaneousDisease.equals("Y")) ? "<li>Severe cutaneous disease</li>":"")+							
								((haeOtherList!=null && haeOtherList.length()>0) ? "<li>"+getDescOList(haeOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		
		String sEndocrine ="";
		if(checkEndocrine ())
		{
			sEndocrine =
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Endocrine - Metabolic</b></td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((endoAdrenalInsufficiency!=null && endoAdrenalInsufficiency.equals("Y")) ? "<li>Adrenal insufficiency</li>":"")+
								((endoHyperAmmonemia!=null && endoHyperAmmonemia.equals("Y")) ? "<li>HyperAmmonemia</li>":"")+
								((endoHyperGlycaemia!=null && endoHyperGlycaemia.equals("Y")) ? "<li>HyperGlycaemia</li>":"")+
								((endoHypoGlycaemia!=null && endoHypoGlycaemia.equals("Y")) ? "<li>HypoGlycaemia</li>":"")+
								((endoKetoAcidosis!=null && endoKetoAcidosis.equals("Y")) ? "<li>keto-Acidosis</li>":"")+
								((endoOtherList!=null && endoOtherList.length()>0) ? "<li>"+getDescOList(endoOtherList)+"</li>":"")+
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
								((neuroAcuteEncephalopathy!=null && neuroAcuteEncephalopathy.equals("Y")) ? "<li>Acute encephalopathy</li>":"")+
								((neuroBulbarDysfunction!=null && neuroBulbarDysfunction.equals("Y")) ? "<li>Bulbar dysfunction</li>":"")+
								((neuroHydrocephalus!=null && neuroHydrocephalus.equals("Y")) ? "<li>Hydrocephalus</li>":"")+
								((neuroIntracranialHaemorrhage!=null && neuroIntracranialHaemorrhage.equals("Y")) ? "<li>Intracranial haemorrhage</li>":"")+
								((neuroParesisOfLimbs!=null && neuroParesisOfLimbs.equals("Y")) ? "<li>Paresis of limbs</li>":"")+
								((neuroParesisOfDiaphragm!=null && neuroParesisOfDiaphragm.equals("Y")) ? "<li>Paresis of diaphragm</li>":"")+
								((neuroNeurologicSurgery!=null && neuroNeurologicSurgery.equals("Y")) ? "<li>Recent neurologic surgery</li>":"")+
								((neuroSeizures!=null && neuroSeizures.equals("Y")) ? "<li>Seizures</li>":"")+
								((neuroTraumaticBrainInjury!=null && neuroTraumaticBrainInjury.equals("Y")) ? "<li>Traumatic brain injury</li>":"")+
								((neuroOtherList!=null && neuroOtherList.length()>0) ? "<li>"+getDescOList(neuroOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		String sInfection ="";
		if(checkInfection ())
		{
			sInfection =
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2;'><b>Infection (B, V, F, P)</b></td></tr>"+
					"<tr><td align='center' colspan='2' style='background-color:#F2F2F2; font-size: 10px;'>(B:Baccterial V:Viral F:Fungal P:Protozoal)</td></tr>"+
					"<tr>"+
						"<td align='left' colspan='2'>"+
							"<ul>"+
								((infectionCentralNervousSystem!=null && infectionCentralNervousSystem.length()>0) ? "<li>["+infectionCentralNervousSystem.charAt(0)+"] Central nervous system</li>":"")+
								((infectionSofttissue!=null && infectionSofttissue.length()>0) ? "<li>["+infectionSofttissue.charAt(0)+"] Soft-tissue/musculoskeltal</li>":"")+
								((infectionRespiratory!=null && infectionRespiratory.length()>0) ? "<li>["+infectionRespiratory.charAt(0)+"] Respiratory tract infection</li>":"")+
								((infectionIntraabdominal!=null && infectionIntraabdominal.length()>0) ? "<li>["+infectionIntraabdominal.charAt(0)+"] Intra-abdominal infection</li>":"")+
								((infectionUrinary!=null && infectionUrinary.length()>0) ? "<li>["+infectionUrinary.charAt(0)+"] Urinary tract infection</li>":"")+
								((infectionBlood!=null && infectionBlood.length()>0) ? "<li>["+infectionBlood.charAt(0)+"] Blood stream infection</li>":"")+
								((infectionOtherPrimarySite!=null && infectionOtherPrimarySite.length()>0) ? "<li>["+infectionOtherPrimarySite.charAt(0)+"] Other primary site</li>":"")+						
								((infectionOtherList!=null && infectionOtherList.length()>0) ? "<li>["+infectionOtherList.charAt(0)+"] "+getDescOList(infectionOtherList)+"</li>":"")+
							"</ul>"+
						"</td>"+
					"</tr>";
		}
		return 
				"<table cellpadding='0' cellspacing='0' width='100%'>"+
					sHaematologic+
					sEndocrine+
					sNeurologic+
					sInfection+
				"</table>";
	}
	public String displayProblems()
	{
		return 
		 "<table cellpadding='0' cellspacing='2' width='100%'>"+
		 "<tr><td width='50%' valign='top'>"+getLHS()+"</td><td width='50%' valign='top'>"+getRHS()+"</td></tr>"+
		 "</table>";
	}
	
}
