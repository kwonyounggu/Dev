package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DE2Bean")]
	public class CarmO_DE2Bean
	{
		public var DE2_ID:int=0;
		public var SITE:String="";
		public var PAT_NO:String="";
		public var EVENT_NO:int=0;
		public var WEEK_NO:int=0;
		public var rsAirwayLower:String="";
		public var rsAirwayUpper:String="";
		public var rsChronicLungDisease:String="";
		public var rsOtherDisease:String="";
		public var rsOtherList:String="";
		public var hcStructuralDiseaseNotCyanotic:String="";
		public var hcStructuralDiseaseCyanotic:String="";
		public var hcPulmonary:String="";
		public var hcSystemic:String="";
		public var hcVascular:String="";
		public var hcVasculitis:String="";
		public var hcOtherDisease:String="";
		public var hcOtherList:String="";
		public var neuroSeizures:String="";
		public var neuroHydrocphalus:String="";
		public var neuroStatic:String="";
		public var neuroProgressive:String="";
		public var neuroMyopathy:String="";
		public var neuroOtherList:String="";
		public var gastroLiver:String="";
		public var gastroIntestinalBleeding:String="";
		public var gastroMalabsorbtion:String="";
		public var gastroIntestinalDisease:String="";
		public var gastroOtherList:String="";
		public var renalImpairment:String="";
		public var renalDisease:String="";
		public var renalEletrolyte:String="";
		public var renalOtherList:String="";
		public var musJoint:String="";
		public var musSytemic:String="";
		public var musOrthopaedic:String="";
		public var musScoliosis:String="";
		public var musCutaneous:String="";
		public var musOtherList:String="";
		public var haeMalignancyHae:String="";
		public var haeMalignancyOrgan:String="";
		public var haeNonMalignancy:String="";
		public var haePrimary:String="";
		public var haeAcquired:String="";
		public var haeBone:String="";
		public var haeSolidOrgan:String="";
		public var haeProthrombotic:String="";
		public var haeBleeding:String="";
		public var haeOtherList:String="";
		public var endoMetabolic:String="";
		public var endoDiabetes:String="";
		public var endoEndocrineDisease:String="";
		public var endoOtherList:String="";
		public var allKnown:String="";
		public var allOther:String="";
		public var allOtherList:String="";
		public var devicesOxygen:String="";
		public var devicesTrachNoVent:String="";
		public var devicesTrachVent:String="";
		public var devicesVentricularInternal:String="";
		public var devicesVentricularExternal:String="";
		public var devicesInternalPacemaker:String="";
		public var devicesWheelchair:String="";
		public var devicesGastro:String="";
		public var devicesIndwellingVas:String="";
		public var devicesIndwellingHamo:String="";
		public var devicesPeritoneal:String="";
		public var devicesOtherList:String="";
		public var enteredBy:String="";
		public var updatedOn:Date=null;
		
		public function CarmO_DE2Bean()
		{
		}
		public function getDescOList(s:String):String
		{
			return s==null ? "<li>Unknown" : s;
		}
		public function checkRespiratorySystem():Boolean
		{
			return  (rsAirwayLower!=null && rsAirwayLower=="Y") ||
				(rsAirwayUpper!=null && rsAirwayUpper=="Y") ||
				(rsChronicLungDisease!=null && rsChronicLungDisease=="Y") ||
				(rsOtherDisease!=null && rsOtherDisease=="Y") ||
				(rsOtherList!=null && rsOtherList.length>0);
		}
		public function checkHeartCirculation():Boolean
		{
			return  (hcStructuralDiseaseNotCyanotic!=null && hcStructuralDiseaseNotCyanotic=="Y") ||
				(hcStructuralDiseaseCyanotic!=null && hcStructuralDiseaseCyanotic=="Y") ||
				(hcPulmonary!=null && hcPulmonary=="Y") ||
				(hcSystemic!=null && hcSystemic=="Y") ||
				(hcVascular!=null && hcVascular=="Y") ||
				(hcVasculitis!=null && hcVasculitis=="Y") ||
				(hcOtherDisease!=null && hcOtherDisease=="Y") ||
				(hcOtherList!=null && hcOtherList.length>0);
		}
		public function checkNeurologic():Boolean
		{
			return  (neuroSeizures!=null && neuroSeizures=="Y") ||
				(neuroHydrocphalus!=null && neuroHydrocphalus=="Y") ||
				(neuroStatic!=null && neuroStatic=="Y") ||
				(neuroProgressive!=null && neuroProgressive=="Y") ||
				(neuroMyopathy!=null && neuroMyopathy=="Y") ||
				(neuroOtherList!=null && neuroOtherList.length>0);
		}
		public function checkGastrointestinal():Boolean
		{
			return  (gastroLiver!=null && gastroLiver=="Y") ||
				(gastroIntestinalBleeding!=null && gastroIntestinalBleeding=="Y") ||
				(gastroMalabsorbtion!=null && gastroMalabsorbtion=="Y") ||
				(gastroIntestinalDisease!=null && gastroIntestinalDisease=="Y") ||
				(gastroOtherList!=null && gastroOtherList.length>0);
		}
		public function checkRenal():Boolean
		{
			return  (renalImpairment!=null && renalImpairment=="Y") ||
				(renalDisease!=null && renalDisease=="Y") ||
				(renalEletrolyte!=null && renalEletrolyte=="Y") ||
				(renalOtherList!=null && renalOtherList.length>0);
		}
		public function checkMusculoskeletalCutaneous():Boolean
		{
			return  (musJoint!=null && musJoint=="Y") ||
				(musSytemic!=null && musSytemic=="Y") ||
				(musOrthopaedic!=null && musOrthopaedic=="Y") ||
				(musScoliosis!=null && musScoliosis=="Y") ||
				(musCutaneous!=null && musCutaneous=="Y") ||
				(musOtherList!=null && musOtherList.length>0);
		}
		public function checkHaematologicOncologic():Boolean
		{
			return  (haeMalignancyHae!=null && haeMalignancyHae=="Y") ||
				(haeMalignancyOrgan!=null && haeMalignancyOrgan=="Y") ||
				(haeNonMalignancy!=null && haeNonMalignancy=="Y") ||
				(haePrimary!=null && haePrimary=="Y") ||
				(haeAcquired!=null && haeAcquired=="Y") ||
				(haeBone!=null && haeBone=="Y") ||
				(haeSolidOrgan!=null && haeSolidOrgan=="Y") ||
				(haeProthrombotic!=null && haeProthrombotic=="Y") ||
				(haeBleeding!=null && haeBleeding=="Y") ||
				(haeOtherList!=null && haeOtherList.length>0);
		}
		public function checkEndocrineMetabolic():Boolean
		{
			return  (endoMetabolic!=null && endoMetabolic=="Y") ||
				(endoDiabetes!=null && endoDiabetes=="Y") ||
				(endoEndocrineDisease!=null && endoEndocrineDisease=="Y") ||
				(endoOtherList!=null && endoOtherList.length>0);
		}
		public function checkAllergiesContraIndications():Boolean
		{
			return  (allKnown!=null && allKnown=="Y") ||
				(allOther!=null && allOther=="Y") ||
				(allOtherList!=null && allOtherList.length>0);
		}
		public function checkDevices():Boolean
		{
			return  (devicesOxygen!=null && devicesOxygen=="Y") ||
				(devicesTrachNoVent!=null && devicesTrachNoVent=="Y") ||
				(devicesTrachVent!=null && devicesTrachVent=="Y") ||
				(devicesVentricularInternal!=null && devicesVentricularInternal=="Y") ||
				(devicesVentricularExternal!=null && devicesVentricularExternal=="Y") ||
				(devicesInternalPacemaker!=null && devicesInternalPacemaker=="Y") ||
				(devicesWheelchair!=null && devicesWheelchair=="Y") ||
				(devicesGastro!=null && devicesGastro=="Y") ||
				(devicesIndwellingVas!=null && devicesIndwellingVas=="Y") ||
				(devicesIndwellingHamo!=null && devicesIndwellingHamo=="Y") ||
				(devicesPeritoneal!=null && devicesPeritoneal=="Y") ||
				(devicesOtherList!=null && devicesOtherList.length>0);
		}
		public function getLHS():String
		{
			var sRespiratorySystem:String="";
			if(checkRespiratorySystem())
			{
				sRespiratorySystem=
					"<b>Respiratory System</b>"+
					""+
					((rsAirwayLower!=null && rsAirwayLower=="Y") ? "<li>Airway obstruction (lower) - Asthma</li>":"")+
					((rsAirwayUpper!=null && rsAirwayUpper=="Y") ? "<li>Airway obstruction - upper eg. croup</li>":"")+
					((rsChronicLungDisease!=null && rsChronicLungDisease=="Y") ? "<li>Chronic Lung disease</li>":"")+
					((rsOtherDisease!=null && rsOtherDisease=="Y") ? "<li>Other Respiratory disease</li>":"")+
					((rsOtherList!=null && rsOtherList.length>0) ? "<li>"+getDescOList(rsOtherList)+"</li>":"")+
					"<br>";
			}
			
			var sHeartCirculation:String="";
			if(checkHeartCirculation())
			{
				sHeartCirculation=
					"<b>Heart & Circulation</b>"+
					""+
					((hcStructuralDiseaseNotCyanotic!=null && hcStructuralDiseaseNotCyanotic=="Y") ? "<li>Structural Heart Disease - not cyanotic</li>":"")+
					((hcStructuralDiseaseCyanotic!=null && hcStructuralDiseaseCyanotic=="Y") ? "<li>Structural Heart Disease cyanotic</li>":"")+
					((hcPulmonary!=null && hcPulmonary=="Y") ? "<li>Pulmonary Hypertension</li>":"")+
					((hcSystemic!=null && hcSystemic=="Y") ? "<li>Systemic Hypertension</li>":"")+
					((hcVascular!=null && hcVascular=="Y") ? "<li>Vascular injury (traumatic)</li>":"")+
					((hcVasculitis!=null && hcVasculitis=="Y") ? "<li>Vasculitis (including KD)</li>":"")+
					((hcOtherDisease!=null && hcOtherDisease=="Y") ? "<li>Other disease affecting major vessells</li>":"")+
					((hcOtherList!=null && hcOtherList.length>0) ? "<li>"+getDescOList(hcOtherList)+"</li>":"")+
					"<br>";
			}
			var sNeurologic:String="";
			if(checkNeurologic())
			{
				sNeurologic=
					"<b>Neurologic</b>"+
					""+
					((neuroSeizures!=null && neuroSeizures=="Y") ? "<li>Seizures</li>":"")+
					((neuroHydrocphalus!=null && neuroHydrocphalus=="Y") ? "<li>Hydrocephalus</li>":"")+
					((neuroStatic!=null && neuroStatic=="Y") ? "<li>Static Encephalopathy (incl. Cerebral Palsy)</li>":"")+
					((neuroProgressive!=null && neuroProgressive=="Y") ? "<li>Progressive Encepahalopathy</li>":"")+
					((neuroMyopathy!=null && neuroMyopathy=="Y") ? "<li>Myopathy / Neuropathy</li>":"")+
					((neuroOtherList!=null && neuroOtherList.length>0) ? "<li>"+getDescOList(neuroOtherList)+"</li>":"")+
					"<br>";
			}
			var sGastrointestinal:String="";
			if(checkGastrointestinal())
			{
				sGastrointestinal=
					"<b>Gastrointestinal</b>"+
					""+
					((gastroLiver!=null && gastroLiver=="Y") ? "<li>Liver disease</li>":"")+
					((gastroIntestinalBleeding!=null && gastroIntestinalBleeding=="Y") ? "<li>Gastro-Intestinal bleeding</li>":"")+
					((gastroMalabsorbtion!=null && gastroMalabsorbtion=="Y") ? "<li>Malabsorbtion syndrome with FTT</li>":"")+
					((gastroIntestinalDisease!=null && gastroIntestinalDisease=="Y") ? "<li>Gastrointestinal disease (not liver)</li>":"")+
					((gastroOtherList!=null && gastroOtherList.length>0) ? "<li>"+getDescOList(gastroOtherList)+"</li>":"")+
					"<br>";
			}
			var sRenal:String="";
			if(checkRenal())
			{
				sRenal=
					"<b>Renal</b>"+
					""+
					((renalImpairment!=null && renalImpairment=="Y") ? "<li>Renal impairment - no dialysis</li>":"")+
					((renalDisease!=null && renalDisease=="Y") ? "<li>Renal disease on dialysis (haemo/peritoneal)</li>":"")+
					((renalEletrolyte!=null && renalEletrolyte=="Y") ? "<li>Electrolyte Disorder</li>":"")+
					((renalOtherList!=null && renalOtherList.length>0) ? "<li>"+getDescOList(renalOtherList)+"</li>":"")+
					"<br>";
			}
			var sMusculoskeletalCutaneous:String="";
			if(checkMusculoskeletalCutaneous())
			{
				sMusculoskeletalCutaneous=
					"<b>Musculoskeletal & Cutaneous</b>"+
					""+
					((musJoint!=null && musJoint=="Y") ? "<li>Joint disease</li>":"")+
					((musSytemic!=null && musSytemic=="Y") ? "<li>Systemic Rheumatologic disease</li>":"")+
					((musOrthopaedic!=null && musOrthopaedic=="Y") ? "<li>Orthopaedic</li>":"")+
					((musScoliosis!=null && musScoliosis=="Y") ? "<li>Scoliosis (severe)</li>":"")+
					((musCutaneous!=null && musCutaneous=="Y") ? "<li>Cutaneous disease (severe)</li>":"")+
					((musOtherList!=null && musOtherList.length>0) ? "<li>"+getDescOList(musOtherList)+"</li>":"")+
					"<br>";
			}
			return sRespiratorySystem+
				sHeartCirculation+
				sNeurologic+
				sGastrointestinal+
				sRenal+
				sMusculoskeletalCutaneous;
		}
		//Next week do here for the RHS
		public function getRHS():String
		{
			var sHaematologicOncologic:String="";
			if(checkHaematologicOncologic())
			{
				sHaematologicOncologic=
					"<b>Haematologic - Oncologic</b>"+
					""+
					((haeMalignancyHae!=null && haeMalignancyHae=="Y") ? "<li>Malignancy haematologic</li>":"")+
					((haeMalignancyOrgan!=null && haeMalignancyOrgan=="Y") ? "<li>Malignancy solid organ</li>":"")+
					((haeNonMalignancy!=null && haeNonMalignancy=="Y") ? "<li>Non-malignant symptomatic tumour</li>":"")+
					((haePrimary!=null && haePrimary=="Y") ? "<li>Priamry Immunodeficiency</li>":"")+								
					((haeAcquired!=null && haeAcquired=="Y") ? "<li>Acquired Immunodeficiency</li>":"")+
					((haeBone!=null && haeBone=="Y") ? "<li>Bone Marrow Transplant Recipient</li>":"")+
					((haeSolidOrgan!=null && haeSolidOrgan=="Y") ? "<li>Solid Organ Transplant Recipient</li>":"")+
					((haeProthrombotic!=null && haeProthrombotic=="Y") ? "<li>Pro-thrombotic disease</li>":"")+
					((haeBleeding!=null && haeBleeding=="Y") ? "<li>Bleeding diasthesis</li>":"")+								
					((haeOtherList!=null && haeOtherList.length>0) ? "<li>"+getDescOList(haeOtherList)+"</li>":"")+
					"<br>";
			}
			
			var sEndocrineMetabolic:String="";
			if(checkEndocrineMetabolic())
			{
				sEndocrineMetabolic=
					"<b>Endocrine - Metabolic</b>"+
					""+
					((endoMetabolic!=null && endoMetabolic=="Y") ? "<li>Metabolic disease</li>":"")+
					((endoDiabetes!=null && endoDiabetes=="Y") ? "<li>Diabetes mellitus</li>":"")+
					((endoEndocrineDisease!=null && endoEndocrineDisease=="Y") ? "<li>Endocrine disease (not diabetes)</li>":"")+
					((endoOtherList!=null && endoOtherList.length>0) ? "<li>"+getDescOList(endoOtherList)+"</li>":"")+
					"<br>";
			}
			var sAllergiesContraIndications:String="";
			if(checkAllergiesContraIndications())
			{
				sAllergiesContraIndications=
					"<b>Allergies & Contra-Indications</b>"+
					""+
					((allKnown!=null && allKnown=="Y") ? "<li>Known severe drug allergy</li>":"")+
					((allOther!=null && allOther=="Y") ? "<li>Other contra-indication to medication</li>":"")+
					((allOtherList!=null && allOtherList.length>0) ? "<li>"+getDescOList(allOtherList)+"</li>":"")+
					"<br>";
			}
			var sDevices:String="";
			if(checkDevices())
			{
				sDevices=
					"<b>Devices</b>"+
					""+
					((devicesOxygen!=null && devicesOxygen=="Y") ? "<li>Oxygen (O2 > lmonth)</li>":"")+
					((devicesTrachNoVent!=null && devicesTrachNoVent=="Y") ? "<li>Tracheostomy no ventilation</li>":"")+
					((devicesTrachVent!=null && devicesTrachVent=="Y") ? "<li>Tracheostomy + Ventilation</li>":"")+
					((devicesVentricularInternal!=null && devicesVentricularInternal=="Y") ? "<li>Ventricular Drain (internal)</li>":"")+
					((devicesVentricularExternal!=null && devicesVentricularExternal=="Y") ? "<li>Ventricular Drain (external)</li>":"")+
					((devicesInternalPacemaker!=null && devicesInternalPacemaker=="Y") ? "<li>Internal pacemaker / defibrillator</li>":"")+
					((devicesWheelchair!=null && devicesWheelchair=="Y") ? "<li>Wheelchair</li>":"")+
					((devicesGastro!=null && devicesGastro=="Y") ? "<li>Gastro / enterostomy tube</li>":"")+
					((devicesIndwellingVas!=null && devicesIndwellingVas=="Y") ? "<li>Indwelling Vascular Access (not dialysis)</li>":"")+
					((devicesIndwellingHamo!=null && devicesIndwellingHamo=="Y") ? "<li>Indwelling Haemo Dialysis catheter</li>":"")+
					((devicesPeritoneal!=null && devicesPeritoneal=="Y") ? "<li>Peritoneal Dialysis catheter</li>":"")+								
					((devicesOtherList!=null && devicesOtherList.length>0) ? "<li>"+getDescOList(devicesOtherList)+"</li>":"")+
					"<br>";
			}
			return sHaematologicOncologic+
				sEndocrineMetabolic+
				sAllergiesContraIndications+
				sDevices;
		}
		public function displayDiagnoses():String
		{
			return ""+getLHS()+""+getRHS()+"";
		}
	}
}