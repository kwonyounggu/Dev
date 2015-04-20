package com.webmonster.hvac.model
{
	import com.webmonster.hvac.view.ac.ChargeView;
	import com.webmonster.hvac.view.ac.ElectricConcernsView;
	import com.webmonster.hvac.view.ac.PerformancePHView;
	import com.webmonster.hvac.view.ac.PressureTemperatureTableView;
	import com.webmonster.hvac.view.ac.PsychrometricView;
	import com.webmonster.hvac.view.ac.RefrigerantView;
	import com.webmonster.hvac.view.ac.TroubleshootingView;
	import com.webmonster.hvac.vo.RefrigerantTypeBean;
	
	import mx.collections.ArrayCollection;
	
	import org.robotlegs.mvcs.Actor;
	
	public class ACModel extends Actor
	{
		public var menuListArray:Array=[
										{label: "Charge", view: ChargeView}, 
										{label: "Performance(P-H)", view: PerformancePHView}, 
										{label: "Performance(Psychrometric)", view: PsychrometricView},
										{label: "P-T Table", view: PressureTemperatureTableView},
										{label: "Refrigerant", view: RefrigerantView},
										{label: "Troubleshooting", view: TroubleshootingView},
										{label: "Electric Concerns", view: ElectricConcernsView}
									  ];
										
		public var menuList:ArrayCollection=new ArrayCollection(menuListArray);
		
		public var unitSettings:Object={pressure: "psig", temperature: "\u2109"}; //\u2109: F, \u2103: C
												 
		//public var refrigerantList:ArrayCollection = new ArrayCollection(
		//									[ 
		//									  {product:"R-22", url: "", minTf_PH: 20, maxTf_PH: 120, defaultTf_PH: 80, minTc_PH: -7, maxTc_PH: 50, defaultTc_PH: 30, minP_PH: 30, maxP_PH: 250, defaultP_PH: 75},
		//									  {product:"R-410A", url: "", minTf_PH: 40, maxTf_PH: 180, defaultTf_PH: 100, minTc_PH: -7, maxTc_PH: 80, defaultTc_PH: 70, minP_PH: 50, maxP_PH: 350, defaultP_PH: 116} 
		//									]);
		public var refrigerantList:ArrayCollection = new ArrayCollection
			(
				[	new RefrigerantTypeBean(String("R-22"), "", 20, 120, 80, -7, 50, 30, 30, 250, 75),
					new RefrigerantTypeBean("R-410A", "", 40, 180, 100, -7, 80, 70, 50, 350, 116)
				]
			);
			
		public function ACModel()
		{
			super();
		}
	}
}