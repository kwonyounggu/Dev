package com.webmonster.hvac.common
{
	import com.webmonster.hvac.vo.RefrigerantTypeBean;
	
	import flash.geom.Point;

	public class PressureEnthalpyComputation
	{
		public static const PSIA_OFFSET:Number=14.7
		private static var pointList:Array=[];
		private var subCooling:Number=0;
		private var superHeat:Number=0;
		
		public function PressureEnthalpyComputation()
		{
		}
		public static function psigToPsia(psig:Number):Number
		{
			return psig+PSIA_OFFSET;
		}
		public static function psiaToPsig(psia:Number):Number
		{
			return psia-PSIA_OFFSET;
		}
		public static function compute(r:RefrigerantTypeBean, hsPressure:Number, hsTemp:Number, lsPressure:Number, lsTemp:Number, unitTemp:String="\u2109"):Array
		{
			hsPressure=psigToPsia(hsPressure);
			lsPressure=psigToPsia(lsPressure);
			
			pointList[0]=new Point(90.1, lsPressure);
			pointList[1]=new Point(100.2, lsPressure);
			pointList[2]=new Point(110.3, lsPressure);
			pointList[3]=new Point(130.4, hsPressure);
			pointList[4]=new Point(100.9, hsPressure);
			pointList[5]=new Point(30.1, hsPressure);
			pointList[6]=new Point(20.1, hsPressure);
			pointList[7]=new Point(20.1, lsPressure+10);
			pointList[8]=new Point(20.1, lsPressure);
			
			return pointList;
		}
	}
}