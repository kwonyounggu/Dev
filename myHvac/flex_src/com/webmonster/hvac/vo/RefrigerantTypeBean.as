package com.webmonster.hvac.vo
{
	public class RefrigerantTypeBean
	{
		public static const PSIA_OFFSET:Number=14.7
		
		public var product:String="-";
		public var url:String="-";
		public var minTemperatureFahrenheit:Number=0.0;
		public var maxTemperatureFahrenheit:Number=0.0;
		public var defaultTemperatureFahrenheit:Number=0.0;
		public var minTemperatureCelsius:Number=0.0;
		public var maxTemperatureCelsius:Number=0.0;
		public var defaultTemperatureCelsius:Number=0.0;
		public var minPressure:Number=0.0;//psig
		public var maxPressure:Number=0.0;//psig
		public var defaultPressure:Number=0.0;//psig
		
		public function RefrigerantTypeBean(product:String="",
											url:String="",
											minTemperatureFahrenheit:Number=0.0,
											maxTemperatureFahrenheit:Number=0.0,
											defaultTemperatureFahrenheit:Number=0.0,
											minTemperatureCelsius:Number=0.0,
											maxTemperatureCelsius:Number=0.0,
											defaultTemperatureCelsius:Number=0.0,
											minPressure:Number=0.0,
											maxPressure:Number=0.0,
											defaultPressure:Number=0.0)
		{
			this.product=product;
			this.url=url;
			this.minTemperatureFahrenheit=minTemperatureFahrenheit;
			this.maxTemperatureFahrenheit=maxTemperatureFahrenheit;
			this.defaultTemperatureFahrenheit=defaultTemperatureFahrenheit;
			this.minTemperatureCelsius=minTemperatureCelsius;
			this.maxTemperatureCelsius=maxTemperatureCelsius;
			this.defaultTemperatureCelsius=defaultTemperatureCelsius;
			this.minPressure=minPressure;
			this.maxPressure=maxPressure;
			this.defaultPressure=defaultPressure;
			
			//trace("product value="+product+" this.product="+this.product);
		}
		
		public function psigToPsia(psig:Number):Number
		{
			return psig+PSIA_OFFSET;
		}
		public function psiaToPsig(psia:Number):Number
		{
			return psia-PSIA_OFFSET;
		}
	}
}