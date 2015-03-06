package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DE4Bean")]
	public class CarmO_DE4Bean
	{
		public var DE4_ID:int=0;
		public var SITE:String="<br>";
		public var PAT_NO:String="<br>";
		public var EVENT_NO:int=0;
		public var WEEK_NO:int=0;
		
		public var rsUpperAirwayObstruction:String="<br>";
		public var rsBronchospasm:String="<br>";
		public var rsConsolidation:String="<br>";
		public var rsPneumothorax:String="<br>";	
		public var rsPleuralEffusion:String="<br>";
		public var rsPulmonaryOedema:String="<br>";
		public var rsPulmonaryHemorrhage:String="<br>";
		public var rsThoracicSurgery:String="<br>";
		public var rsThoracicTrauma:String="<br>";
		public var rsOtherList:String="<br>";
		
		
		public var hcArrhythmia:String="<br>";
		public var hcCardiomypathy:String="<br>";
		public var hcCardiacSurgery:String="<br>";
		public var hcHaemorrhagicShock:String="<br>";
		public var hcPericardialEffusion:String="<br>";
		public var hcSepticShock:String="<br>";
		public var hcSystemicHypertension:String="<br>";
		public var hcPulmonaryHypertension:String="<br>";
		public var hcBTshunt:String="<br>";
		public var hcArterialThrombosis:String="<br>";
		public var hcVenousThrombosis:String="<br>";
		public var hcOtherList:String="<br>";
		
		
		public var abAbdominalSepsis:String="<br>";
		public var abAbdominalTrauma:String="<br>";
		public var abBleedingIntraabdominal:String="<br>";
		public var abSevereDiarrhoea:String="<br>";
		public var abBowelPerforation:String="<br>";
		public var abBowelObstruction:String="<br>";
		public var abAbominalSurgery:String="<br>";
		public var abAcuteLiverDysfunction:String="<br>";
		public var abOtherList:String="<br>";
		
		public var renalAcuteRenalDysfunction:String="<br>";
		public var renalHyponatraemia:String="<br>";
		public var renalElectrolyteDisorder:String="<br>";
		public var renalUrologicSurgery:String="<br>";
		public var renalOtherList:String="<br>";
		
		public var haeMalignancyNotInRemission:String="<br>";
		public var haeChemotherapy:String="<br>";
		public var haeAnaemia:String="<br>";
		public var haeThrombocytopenia:String="<br>";
		public var haeLeucopeniaNeutropaenia:String="<br>";
		public var haeCoagulopathy:String="<br>";
		public var haeGraftVsHostDisease:String="<br>";
		public var haeTransplantRejection:String="<br>";
		public var haeSystemicInflammatoryDisease:String="<br>";
		public var haeSevereCutaneousDisease:String="<br>";
		public var haeOtherList:String="<br>";
		
		
		public var endoAdrenalInsufficiency:String="<br>";
		public var endoHyperAmmonemia:String="<br>";
		public var endoHyperGlycaemia:String="<br>";
		public var endoHypoGlycaemia:String="<br>";
		public var endoKetoAcidosis:String="<br>";
		public var endoOtherList:String="<br>";
		
		
		public var neuroAcuteEncephalopathy:String="<br>";
		public var neuroBulbarDysfunction:String="<br>";
		public var neuroHydrocephalus:String="<br>";
		public var neuroIntracranialHaemorrhage:String="<br>";
		public var neuroParesisOfLimbs:String="<br>";
		public var neuroParesisOfDiaphragm:String="<br>";
		public var neuroNeurologicSurgery:String="<br>";
		public var neuroSeizures:String="<br>";
		public var neuroTraumaticBrainInjury:String="<br>";
		public var neuroOtherList:String="<br>";
		
		
		public var infectionCentralNervousSystem:String="<br>";
		public var infectionSofttissue:String="<br>";
		public var infectionRespiratory:String="<br>";
		public var infectionIntraabdominal:String="<br>";
		public var infectionUrinary:String="<br>";
		public var infectionBlood:String="<br>";
		public var infectionOtherPrimarySite:String="<br>";
		public var infectionOtherList:String="<br>";
		
		public var enteredBy:String="<br>";
		public var updatedOn:Date=null;
		
		public var eOthSpec:String="<br>";
		public function CarmO_DE4Bean()
		{
		}
		public function getDescOList(s:String):String
		{
			return s==null ? "Unknown" : s;
		}
		public function checkRespiratorySystem():Boolean
		{
			return  (rsUpperAirwayObstruction!=null && rsUpperAirwayObstruction=="Y") ||
				(rsBronchospasm!=null && rsBronchospasm=="Y") ||
				(rsConsolidation!=null && rsConsolidation=="Y") ||
				(rsPneumothorax!=null && rsPneumothorax=="Y") ||			
				(rsPleuralEffusion!=null && rsPleuralEffusion=="Y") ||
				(rsPulmonaryOedema!=null && rsPulmonaryOedema=="Y") ||
				(rsPulmonaryHemorrhage!=null && rsPulmonaryHemorrhage=="Y") ||
				(rsThoracicSurgery!=null && rsThoracicSurgery=="Y") ||
				(rsThoracicTrauma!=null && rsThoracicTrauma=="Y") ||
				
				(rsOtherList!=null && rsOtherList.length>0);
		}
		public function checkHeartCirculation():Boolean
		{
			return  (hcArrhythmia!=null && hcArrhythmia=="Y") ||
				(hcCardiomypathy!=null && hcCardiomypathy=="Y") ||
				(hcCardiacSurgery!=null && hcCardiacSurgery=="Y") ||
				(hcHaemorrhagicShock!=null && hcHaemorrhagicShock=="Y") ||
				(hcPericardialEffusion!=null && hcPericardialEffusion=="Y") ||
				(hcSepticShock!=null && hcSepticShock=="Y") ||
				(hcSystemicHypertension!=null && hcSystemicHypertension=="Y") ||
				(hcPulmonaryHypertension!=null && hcPulmonaryHypertension=="Y") ||
				(hcBTshunt!=null && hcBTshunt=="Y") ||
				(hcArterialThrombosis!=null && hcArterialThrombosis=="Y") ||
				(hcVenousThrombosis!=null && hcVenousThrombosis=="Y") ||
				(hcOtherList!=null && hcOtherList.length>0);
		}
		public function checkAbdominal():Boolean
		{
			return  (abAbdominalSepsis!=null && abAbdominalSepsis=="Y") ||
				(abAbdominalTrauma!=null && abAbdominalTrauma=="Y") ||
				(abBleedingIntraabdominal!=null && abBleedingIntraabdominal=="Y") ||
				(abSevereDiarrhoea!=null && abSevereDiarrhoea=="Y") ||
				(abBowelPerforation!=null && abBowelPerforation=="Y") ||
				(abBowelObstruction!=null && abBowelObstruction=="Y") ||
				(abAbominalSurgery!=null && abAbominalSurgery=="Y") ||
				(abAcuteLiverDysfunction!=null && abAcuteLiverDysfunction=="Y") ||
				(abOtherList!=null && abOtherList.length>0);
		}
		public function checkRenal():Boolean
		{
			return  (renalAcuteRenalDysfunction!=null && renalAcuteRenalDysfunction=="Y") ||
				(renalHyponatraemia!=null && renalHyponatraemia=="Y") ||
				(renalElectrolyteDisorder!=null && renalElectrolyteDisorder=="Y") ||
				(renalUrologicSurgery!=null && renalUrologicSurgery=="Y") ||
				(renalOtherList!=null && renalOtherList.length>0);
		}
		public function checkHaematologic():Boolean
		{
			return  (haeMalignancyNotInRemission!=null && haeMalignancyNotInRemission=="Y") ||
				(haeChemotherapy!=null && haeChemotherapy=="Y") ||
				(haeAnaemia!=null && haeAnaemia=="Y") ||
				(haeThrombocytopenia!=null && haeThrombocytopenia=="Y") ||
				(haeLeucopeniaNeutropaenia!=null && haeLeucopeniaNeutropaenia=="Y") ||
				(haeCoagulopathy!=null && haeCoagulopathy=="Y") ||
				(haeGraftVsHostDisease!=null && haeGraftVsHostDisease=="Y") ||
				(haeTransplantRejection!=null && haeTransplantRejection=="Y") ||
				(haeSystemicInflammatoryDisease!=null && haeSystemicInflammatoryDisease=="Y") ||
				(haeSevereCutaneousDisease!=null && haeSevereCutaneousDisease=="Y") ||
				(haeOtherList!=null && haeOtherList.length>0);
		}
		public function checkEndocrine():Boolean
		{
			return  (endoAdrenalInsufficiency!=null && endoAdrenalInsufficiency=="Y") ||
				(endoHyperAmmonemia!=null && endoHyperAmmonemia=="Y") ||
				(endoHyperGlycaemia!=null && endoHyperGlycaemia=="Y") ||
				(endoHypoGlycaemia!=null && endoHypoGlycaemia=="Y") ||
				(endoKetoAcidosis!=null && endoKetoAcidosis=="Y") ||
				(endoOtherList!=null && endoOtherList.length>0);
		}
		public function checkNeurologic():Boolean
		{
			return  (neuroAcuteEncephalopathy!=null && neuroAcuteEncephalopathy=="Y") ||
				(neuroBulbarDysfunction!=null && neuroBulbarDysfunction=="Y") ||
				(neuroHydrocephalus!=null && neuroHydrocephalus=="Y") ||
				(neuroIntracranialHaemorrhage!=null && neuroIntracranialHaemorrhage=="Y") ||
				(neuroParesisOfLimbs!=null && neuroParesisOfLimbs=="Y") ||
				(neuroParesisOfDiaphragm!=null && neuroParesisOfDiaphragm=="Y") ||
				(neuroNeurologicSurgery!=null && neuroNeurologicSurgery=="Y") ||
				(neuroSeizures!=null && neuroSeizures=="Y") ||
				(neuroTraumaticBrainInjury!=null && neuroTraumaticBrainInjury=="Y") ||
				(neuroOtherList!=null && neuroOtherList.length>0);
		}
		public function checkInfection():Boolean
		{
			return (infectionCentralNervousSystem!=null) ||
				(infectionSofttissue!=null) ||
				(infectionRespiratory!=null) ||
				(infectionIntraabdominal!=null) ||
				(infectionUrinary!=null) ||
				(infectionBlood!=null) ||
				(infectionOtherPrimarySite!=null) ||
				(infectionOtherList!=null && infectionOtherList.length>0);
		}
		public function getLHS():String
		{
			var sRespiratorySystem:String="";
			if(checkRespiratorySystem())
			{
				sRespiratorySystem=
					"<b>Respiratory System</b>"+
					""+
					((rsUpperAirwayObstruction!=null && rsUpperAirwayObstruction=="Y") ? "<li>Upper airway obstruction</li>":"")+
					((rsBronchospasm!=null && rsBronchospasm=="Y") ? "<li>Bronchospasm</li>":"")+
					((rsConsolidation!=null && rsConsolidation=="Y") ? "<li>Consolidation/collapse of lung parenchyma</li>":"")+
					((rsPneumothorax!=null && rsPneumothorax=="Y") ? "<li>Pneumothorax</li>":"")+
					((rsPleuralEffusion!=null && rsPleuralEffusion=="Y") ? "<li>Pleural effusion</li>":"")+
					((rsPulmonaryOedema!=null && rsPulmonaryOedema=="Y") ? "<li>Pulmonary oedema</li>":"")+
					((rsPulmonaryHemorrhage!=null && rsPulmonaryHemorrhage=="Y") ? "<li>Pulmonary haemorrhage</li>":"")+
					((rsThoracicSurgery!=null && rsThoracicSurgery=="Y") ? "<li>Recent thoracic surgery (96 hours)</li>":"")+
					((rsThoracicTrauma!=null && rsThoracicTrauma=="Y") ? "<li>Thoracic trauma</li>":"")+							
					((rsOtherList!=null && rsOtherList.length>0) ? "<li>"+getDescOList(rsOtherList)+"</li>":"")+
					"<br>";
			}
			
			var sHeartCirculation:String="";
			if(checkHeartCirculation())
			{
				sHeartCirculation=
					"<b>Heart & Circulation</b>"+
					""+
					((hcArrhythmia!=null && hcArrhythmia=="Y") ? "<li>Arrhythmia</li>":"")+
					((hcCardiomypathy!=null && hcCardiomypathy=="Y") ? "<li>Cardiomypathy/cardiogenic shock</li>":"")+
					((hcCardiacSurgery!=null && hcCardiacSurgery=="Y") ? "<li>Recent cardiac surgery (96 hours)</li>":"")+
					((hcHaemorrhagicShock!=null && hcHaemorrhagicShock=="Y") ? "<li>Haemorrhagic shock</li>":"")+
					((hcPericardialEffusion!=null && hcPericardialEffusion=="Y") ? "<li>Pericardial effusion</li>":"")+
					((hcSepticShock!=null && hcSepticShock=="Y") ? "<li>Septic shock</li>":"")+
					((hcSystemicHypertension!=null && hcSystemicHypertension=="Y") ? "<li>Systemic hypertension</li>":"")+
					((hcPulmonaryHypertension!=null && hcPulmonaryHypertension=="Y") ? "<li>Pulmonary hypertension</li>":"")+
					((hcBTshunt!=null && hcBTshunt=="Y") ? "<li>BT-shunt or duct-dependent circulation</li>":"")+
					((hcArterialThrombosis!=null && hcArterialThrombosis=="Y") ? "<li>Arterial thrombosis</li>":"")+
					((hcVenousThrombosis!=null && hcVenousThrombosis=="Y") ? "<li>Venous thrombosis</li>":"")+						
					((hcOtherList!=null && hcOtherList.length>0) ? "<li>"+getDescOList(hcOtherList)+"</li>":"")+
					"<br>";
			}
			var sAbdominal:String="";
			if(checkAbdominal())
			{
				sAbdominal=
					"<b>Abdominal</b>"+
					""+
					((abAbdominalSepsis!=null && abAbdominalSepsis=="Y") ? "<li>Abdominal sepsis</li>":"")+
					((abAbdominalTrauma!=null && abAbdominalTrauma=="Y") ? "<li>Abdominal trauma</li>":"")+
					((abBleedingIntraabdominal!=null && abBleedingIntraabdominal=="Y") ? "<li>Bleeding intra-abdominal/GI tract</li>":"")+
					((abSevereDiarrhoea!=null && abSevereDiarrhoea=="Y") ? "<li>Severe diarrhoea/vomiting</li>":"")+
					((abBowelPerforation!=null && abBowelPerforation=="Y") ? "<li>Bowel perforation</li>":"")+
					((abBowelObstruction!=null && abBowelObstruction=="Y") ? "<li>Bowel obstruction</li>":"")+
					((abAbominalSurgery!=null && abAbominalSurgery=="Y") ? "<li>Recent abominal surgery (96 hours)</li>":"")+
					((abAcuteLiverDysfunction!=null && abAcuteLiverDysfunction=="Y") ? "<li>Acute liver dysfunction</li>":"")+
					((abOtherList!=null && abOtherList.length>0) ? "<li>"+getDescOList(abOtherList)+"</li>":"")+
					"<br>";
			}		
			var sRenal:String="";
			if(checkRenal())
			{
				sRenal=
					"<b>Renal</b>"+	
					""+
					((renalAcuteRenalDysfunction!=null && renalAcuteRenalDysfunction=="Y") ? "<li>Acute renal dysfunction</li>":"")+
					((renalHyponatraemia!=null && renalHyponatraemia=="Y") ? "<li>Hyponatraemia</li>":"")+
					((renalElectrolyteDisorder!=null && renalElectrolyteDisorder=="Y") ? "<li>Other electrolyte disorder</li>":"")+
					((renalUrologicSurgery!=null && renalUrologicSurgery=="Y") ? "<li>Recent renal/urologic surgery</li>":"")+
					((renalOtherList!=null && renalOtherList.length>0) ? "<li>"+getDescOList(renalOtherList)+"</li>":"")+
					"<br>";
			}
			
			return sRespiratorySystem+
				sHeartCirculation+
				sAbdominal+
				sRenal;
		}
		
		//Next week do here for the RHS
		public function getRHS():String
		{
			var sHaematologic:String="";
			if(checkHaematologic())
			{
				sHaematologic=
					"<b>Haematologic - Oncologic</b>"+	
					""+
					((haeMalignancyNotInRemission!=null && haeMalignancyNotInRemission=="Y") ? "<li>Malignancy not in remission</li>":"")+
					((haeChemotherapy!=null && haeChemotherapy=="Y") ? "<li>Chemotherapy radiotherapy effects</li>":"")+
					((haeAnaemia!=null && haeAnaemia=="Y") ? "<li>Anaemia</li>":"")+
					((haeThrombocytopenia!=null && haeThrombocytopenia=="Y") ? "<li>Thrombocytopenia</li>":"")+
					((haeLeucopeniaNeutropaenia!=null && haeLeucopeniaNeutropaenia=="Y") ? "<li>Leucopenia/neutropaenia&lt;1x10<sup style='font-size:9px;'>9</sup>/mm<sup style='font-size:9px;'>3</sup></li>":"")+
					((haeCoagulopathy!=null && haeCoagulopathy=="Y") ? "<li>Coagulopathy</li>":"")+
					((haeGraftVsHostDisease!=null && haeGraftVsHostDisease=="Y") ? "<li>Graft versus host disease</li>":"")+
					((haeTransplantRejection!=null && haeTransplantRejection=="Y") ? "<li>Transplant rejection</li>":"")+
					((haeSystemicInflammatoryDisease!=null && haeSystemicInflammatoryDisease=="Y") ? "<li>Systemic inflammatory disease</li>":"")+
					((haeSevereCutaneousDisease!=null && haeSevereCutaneousDisease=="Y") ? "<li>Severe cutaneous disease</li>":"")+							
					((haeOtherList!=null && haeOtherList.length>0) ? "<li>"+getDescOList(haeOtherList)+"</li>":"")+
					"<br>";
			}
			
			var sEndocrine:String ="";
			if(checkEndocrine ())
			{
				sEndocrine =
					"<b>Endocrine - Metabolic</b>"+
					""+
					((endoAdrenalInsufficiency!=null && endoAdrenalInsufficiency=="Y") ? "<li>Adrenal insufficiency</li>":"")+
					((endoHyperAmmonemia!=null && endoHyperAmmonemia=="Y") ? "<li>HyperAmmonemia</li>":"")+
					((endoHyperGlycaemia!=null && endoHyperGlycaemia=="Y") ? "<li>HyperGlycaemia</li>":"")+
					((endoHypoGlycaemia!=null && endoHypoGlycaemia=="Y") ? "<li>HypoGlycaemia</li>":"")+
					((endoKetoAcidosis!=null && endoKetoAcidosis=="Y") ? "<li>keto-Acidosis</li>":"")+
					((endoOtherList!=null && endoOtherList.length>0) ? "<li>"+getDescOList(endoOtherList)+"</li>":"")+
					"<br>";
			}
			var sNeurologic:String="";
			if(checkNeurologic())
			{
				sNeurologic=
					"<b>Neurologic</b>"+
					""+
					((neuroAcuteEncephalopathy!=null && neuroAcuteEncephalopathy=="Y") ? "<li>Acute encephalopathy</li>":"")+
					((neuroBulbarDysfunction!=null && neuroBulbarDysfunction=="Y") ? "<li>Bulbar dysfunction</li>":"")+
					((neuroHydrocephalus!=null && neuroHydrocephalus=="Y") ? "<li>Hydrocephalus</li>":"")+
					((neuroIntracranialHaemorrhage!=null && neuroIntracranialHaemorrhage=="Y") ? "<li>Intracranial haemorrhage</li>":"")+
					((neuroParesisOfLimbs!=null && neuroParesisOfLimbs=="Y") ? "<li>Paresis of limbs</li>":"")+
					((neuroParesisOfDiaphragm!=null && neuroParesisOfDiaphragm=="Y") ? "<li>Paresis of diaphragm</li>":"")+
					((neuroNeurologicSurgery!=null && neuroNeurologicSurgery=="Y") ? "<li>Recent neurologic surgery</li>":"")+
					((neuroSeizures!=null && neuroSeizures=="Y") ? "<li>Seizures</li>":"")+
					((neuroTraumaticBrainInjury!=null && neuroTraumaticBrainInjury=="Y") ? "<li>Traumatic brain injury</li>":"")+
					((neuroOtherList!=null && neuroOtherList.length>0) ? "<li>"+getDescOList(neuroOtherList)+"</li>":"")+
					"<br>";
			}
			var sInfection:String ="";
			if(checkInfection ())
			{
				sInfection =
					"<b>Infection (B, V, F, P)</b><br>"+
					"<font size='11'>(B:Baccterial V:Viral F:Fungal P:Protozoal)</font>"+
					""+
					((infectionCentralNervousSystem!=null && infectionCentralNervousSystem.length>0) ? "<li>["+infectionCentralNervousSystem.charAt(0)+"] Central nervous system</li>":"")+
					((infectionSofttissue!=null && infectionSofttissue.length>0) ? "<li>["+infectionSofttissue.charAt(0)+"] Soft-tissue/musculoskeltal</li>":"")+
					((infectionRespiratory!=null && infectionRespiratory.length>0) ? "<li>["+infectionRespiratory.charAt(0)+"] Respiratory tract infection</li>":"")+
					((infectionIntraabdominal!=null && infectionIntraabdominal.length>0) ? "<li>["+infectionIntraabdominal.charAt(0)+"] Intra-abdominal infection</li>":"")+
					((infectionUrinary!=null && infectionUrinary.length>0) ? "<li>["+infectionUrinary.charAt(0)+"] Urinary tract infection</li>":"")+
					((infectionBlood!=null && infectionBlood.length>0) ? "<li>["+infectionBlood.charAt(0)+"] Blood stream infection</li>":"")+
					((infectionOtherPrimarySite!=null && infectionOtherPrimarySite.length>0) ? "<li>["+infectionOtherPrimarySite.charAt(0)+"] Other primary site</li>":"")+						
					((infectionOtherList!=null && infectionOtherList.length>0) ? "<li>["+infectionOtherList.charAt(0)+"] "+getDescOList(infectionOtherList)+"</li>":"")+
					"<br>";
			}
			return sHaematologic+
				sEndocrine+
				sNeurologic+
				sInfection;
		}
		public function displayProblems():String
		{
			return ""+getLHS()+""+getRHS()+"";
		}
	}
}