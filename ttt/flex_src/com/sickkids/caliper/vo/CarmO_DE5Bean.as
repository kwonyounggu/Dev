package com.sickkids.caliper.vo
{
	[Bindable] 
	[RemoteClass(alias="com.sickkids.caliper.dao.CarmO_DE5Bean")]
	public class CarmO_DE5Bean
	{
		public var DE5_ID:int=0;
		public var SITE:String="";
		public var PAT_NO:String="";
		public var EVENT_NO:int=0;
		public var WEEK_NO:int=0;
		
		
		public var eventHourLimit:String="";//48, 12, 1, tests_24, l_limit, u_limit
		
		public var haeHaemoglobin:Number=-10000;
		public var haeWhiteCellCount:Number=-10000;
		public var haeBands:Number=-10000;
		public var haePlateletCount:Number=-10000;
		
		public var eleSodium:Number=-10000;
		public var elePotassium:Number=-10000;
		public var eleCalciumTotal:Number=-10000;
		public var eleCalciumIonized:Number=-10000;
		public var eleMagnesium:Number=-10000;
		
		public var bloPaO2:Number=-10000;
		public var bloPaCO2:Number=-10000;
		public var bloHCO3:Number=-10000;
		public var bloPh:Number=-10000;
		
		public var venPmvO2:Number=-10000;
		public var venHCO3:Number=-10000;
		
		public var metAmmonia:Number=-10000;
		public var metLactate:Number=-10000;
		public var metGlucose:Number=-10000;
		
		public var liverAST:Number=-10000;
		public var liverALT:Number=-10000;
		public var liverBilirubinCon:Number=-10000;
		public var liverBilirubinUncon:Number=-10000;
		public var liverLDH:Number=-10000;
		public var liverINR:Number=-10000;
		public var liverPTT:Number=-10000;
		
		public var haeHaemoglobinUnit:String="";
		public var haeWhiteCellCountUnit:String="";
		public var haeBandsUnit:String="";
		public var haePlateletCountUnit:String="";
		
		public var eleSodiumUnit:String="";
		public var elePotassiumUnit:String="";
		public var eleCalciumTotalUnit:String="";
		public var eleCalciumIonizedUnit:String="";
		public var eleMagnesiumUnit:String="";
		
		public var bloPaO2Unit:String="";
		public var bloPaCO2Unit:String="";
		public var bloHCO3Unit:String="";
		public var bloPhUnit:String="";
		
		public var venPmvO2Unit:String="";
		public var venHCO3Unit:String="";
		
		public var metAmmoniaUnit:String="";
		public var metLactateUnit:String="";
		public var metGlucoseUnit:String="";
		
		public var liverASTUnit:String="";
		public var liverALTUnit:String="";
		public var liverBilirubinConUnit:String="";
		public var liverBilirubinUnconUnit:String="";
		public var liverLDHUnit:String="";
		public var liverINRUnit:String="";
		public var liverPTTUnit:String="";
		public function CarmO_DE5Bean()
		{
		}
		public function getHaemoglobin():String
		{
			return haeHaemoglobin==-10000? "":String(haeHaemoglobin);
		}
		public function getWhiteCellCount():String
		{
			return haeWhiteCellCount==-10000? "":String(haeWhiteCellCount);
		}
		public function getBands():String
		{
			return haeBands==-10000? "":String(haeBands);
		}
		public function getPlatgettCount():String
		{
			return haePlateletCount==-10000? "":String(haePlateletCount);
		}
		
		public function getSodium():String
		{
			return eleSodium==-10000? "":String(eleSodium);
		}
		public function getPotassium():String
		{
			return elePotassium==-10000? "":String(elePotassium);
		}
		public function getCalciumTotal():String
		{
			return eleCalciumTotal==-10000? "":String(eleCalciumTotal);
		}
		public function getCalciumIonized():String
		{
			return eleCalciumIonized==-10000? "":String(eleCalciumIonized);
		}
		public function getMagnesium():String
		{
			return eleMagnesium==-10000? "":String(eleMagnesium);
		}
		public function getPaO2():String
		{
			return bloPaO2==-10000? "":String(bloPaO2);
		}
		public function getPaCO2():String
		{
			return bloPaCO2==-10000? "":String(bloPaCO2);
		}
		public function getBloHCO3():String
		{
			return bloHCO3==-10000? "":String(bloHCO3);
		}
		public function getPh():String
		{
			return bloPh==-10000? "":String(bloPh);
		}
		public function getPmvO2():String
		{
			return venPmvO2==-10000? "":String(venPmvO2);
		}
		public function getVenHCO3():String
		{
			return venHCO3==-10000? "":String(venHCO3);
		}
		
		public function getAmmonia():String
		{
			return metAmmonia==-10000? "":String(metAmmonia);
		}
		public function getLactate():String
		{
			return metLactate==-10000? "":String(metLactate);
		}
		public function getGlucose():String
		{
			return metGlucose==-10000? "":String(metGlucose);
		}
		public function getAST():String
		{
			return liverAST==-10000? "":String(liverAST);
		}
		public function getALT():String
		{
			return liverALT==-10000? "":String(liverALT);
		}
		public function getBilirubinCon():String
		{
			return liverBilirubinCon==-10000? "":String(liverBilirubinCon);
		}
		public function getBilirubinUncon():String
		{
			return liverBilirubinUncon==-10000? "":String(liverBilirubinUncon);
		}
		public function getLDH():String
		{
			return liverLDH==-10000? "":String(liverLDH);
		}
		public function getINR():String
		{
			return liverINR==-10000? "":String(liverINR);
		}
		public function getPTT():String
		{
			return liverPTT==-10000? "":String(liverPTT);
		}
		
		public function getLDHUnit():String
		{
			if(liverLDHUnit.indexOf("micro")>0) liverLDHUnit="umol/L"
			return liverLDHUnit;
		}
		public function getBilirubinConUnit():String
		{
			if(liverBilirubinConUnit.indexOf("micro")>0) liverBilirubinConUnit="umol/L"
			return liverBilirubinConUnit;
		}
		public function getAmmoniaUnit():String
		{
			if(metAmmoniaUnit.indexOf("micro")>0) metAmmoniaUnit="umol/L"
			return metAmmoniaUnit;
		}
		public function getBilirubinUnconUnit():String
		{
			if(liverBilirubinUnconUnit.indexOf("micro")>0) liverBilirubinUnconUnit="umol/L"
			return liverBilirubinUnconUnit;
		}
		public function getPlateletCountUnit():String
		{
			if(haePlateletCountUnit.indexOf("<sup>")>0)
			{
				haePlateletCountUnit=haePlateletCountUnit.replace("<sup>","^");
				haePlateletCountUnit=haePlateletCountUnit.replace("</sup>","");
			}
			return haePlateletCountUnit;
		}
		public function getBandsUnit():String
		{
			if(haeBandsUnit.indexOf("<sup>")>0)
			{
				haeBandsUnit=haeBandsUnit.replace("<sup>","^");
				haeBandsUnit=haeBandsUnit.replace("</sup>","");
			}
			return haeBandsUnit;
		}
		public function getWhiteCellCountUnit():String
		{
			if(haeWhiteCellCountUnit.indexOf("<sup>")>0)
			{
				haeWhiteCellCountUnit=haeWhiteCellCountUnit.replace("<sup>","^");
				haeWhiteCellCountUnit=haeWhiteCellCountUnit.replace("</sup>","");
			}
			return haeWhiteCellCountUnit;
		}
		
	}
}