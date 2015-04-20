package com.webmonster.hvac.view.ac.mediator
{
	import com.webmonster.hvac.common.PressureEnthalpyComputation;
	import com.webmonster.hvac.model.ACModel;
	import com.webmonster.hvac.view.ac.PerformancePHView;
	import com.webmonster.hvac.vo.RefrigerantTypeBean;
	
	import flash.events.MouseEvent;
	
	import spark.events.IndexChangeEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class PerformancePHViewMediator extends Mediator
	{
		[Inject] public var performancePHView:PerformancePHView;
		[Inject] public var acModel:ACModel;
		
		public function PerformancePHViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in PerformancePHViewMediator.as");
			performancePHView.refrigerantList.dataProvider=acModel.refrigerantList;
			performancePHView.refrigerantList.selectedIndex=0;
			performancePHView.highSidePressureUnit.text=performancePHView.lowSidePressureUnit.text="("+acModel.unitSettings.pressure+")";
			performancePHView.highSideTemperatureUnit.text=performancePHView.lowSideTemperatureUnit.text="("+acModel.unitSettings.temperature+")";
			
			initNumericStepper(acModel.unitSettings.temperature);
			//if(acModel.unitSettings.temperature=="\u2109") //F
			
			performancePHView.refrigerantList.addEventListener(IndexChangeEvent.CHANGE, onRefrigerantChangeHandler);
			performancePHView.computeButton.addEventListener(MouseEvent.CLICK, onComputeButton);
			//this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			//this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);	
			//this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in PerformancePHViewMediator.as");
			//this.removeContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList);
			//this.removeContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList);
			
			performancePHView.refrigerantList.removeEventListener(IndexChangeEvent.CHANGE, onRefrigerantChangeHandler);
			performancePHView.computeButton.removeEventListener(MouseEvent.CLICK, onComputeButton);
		}	
		private function onRefrigerantChangeHandler(e:IndexChangeEvent):void
		{
			initNumericStepper(acModel.unitSettings.temperature);		
			performancePHView.chartTitle.text=performancePHView.refrigerantList.selectedItem.product+" Pressure-Enthalpy Chart";
		}
		
		private function changeSectionVisibilty():void
		{
			
		}
		private function onComputeButton(e:MouseEvent):void
		{
			if(!performancePHView.chartSection.visible)
			{
				performancePHView.chartSection.includeInLayout=true;
				performancePHView.chartSection.visible=true;
				
				var o:RefrigerantTypeBean=acModel.refrigerantList.getItemAt(performancePHView.refrigerantList.selectedIndex) as RefrigerantTypeBean;
				
				performancePHView.phChart.dataProvider=PressureEnthalpyComputation.compute(o,
																						   performancePHView.highPressureNS.value,
																						   performancePHView.highTemperatureNS.value,
																						   performancePHView.lowPressureNS.value,
																						   performancePHView.lowTemperatureNS.value,
																						   acModel.unitSettings.temperature);
				performancePHView.yAxisId.maximum=PressureEnthalpyComputation.psigToPsia(performancePHView.highPressureNS.value)+10.00;//+ 10.00 space offset
				performancePHView.yAxisId.minimum=PressureEnthalpyComputation.psigToPsia(performancePHView.lowPressureNS.value)-10.00;//-10.00 space offset
				performancePHView.drawPHData();
																						   
			}
		}
		//Change the numericstepper values at the initial stage and when the refrigerant changed.
		private function initNumericStepper(temperatureUnit:String):void
		{
			var o:RefrigerantTypeBean=acModel.refrigerantList.getItemAt(performancePHView.refrigerantList.selectedIndex) as RefrigerantTypeBean;
			
			if(temperatureUnit=="\u2109") //F
			{
				performancePHView.highTemperatureNS.minimum=performancePHView.lowTemperatureNS.minimum=o.minTemperatureFahrenheit;
				performancePHView.highTemperatureNS.maximum=performancePHView.lowTemperatureNS.maximum=o.maxTemperatureFahrenheit;
				performancePHView.highTemperatureNS.value=performancePHView.lowTemperatureNS.value=o.defaultTemperatureFahrenheit;
			}
			else //C
			{
				performancePHView.highTemperatureNS.minimum=performancePHView.lowTemperatureNS.minimum=o.minTemperatureCelsius;
				performancePHView.highTemperatureNS.maximum=performancePHView.lowTemperatureNS.maximum=o.maxTemperatureCelsius;
				performancePHView.highTemperatureNS.value=performancePHView.lowTemperatureNS.value=o.defaultTemperatureCelsius;
			}
			performancePHView.highPressureNS.minimum=performancePHView.lowPressureNS.minimum=o.minPressure;
			performancePHView.highPressureNS.maximum=performancePHView.lowPressureNS.maximum=o.maxPressure;
			performancePHView.highPressureNS.value=performancePHView.lowPressureNS.value=o.defaultPressure;
		}
	}
}