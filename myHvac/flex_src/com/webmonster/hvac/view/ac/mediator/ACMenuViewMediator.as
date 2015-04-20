package com.webmonster.hvac.view.ac.mediator
{
	
	
	import com.webmonster.hvac.model.ACModel;
	import com.webmonster.hvac.view.ac.PsychrometricView;
	import com.webmonster.hvac.view.ac.ACMenuView;
	
	import flash.events.MouseEvent;
	
	import mx.core.FlexGlobals;
	
	import spark.components.SplitViewNavigator;
	import spark.components.ViewNavigator;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ACMenuViewMediator extends Mediator
	{
		[Inject] public var acMenuView:ACMenuView;
		[Inject] public var acModel:ACModel;
		//[Inject] public var topNavigator:ViewNavigator;
		
		public function ACMenuViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ACMenuViewMediator.as");
			acMenuView.menuList.dataProvider=acModel.menuList;
			acMenuView.menuList.selectedIndex=1;//Performance(P-H), so the 1st index is hight-lighted in default
			acMenuView.menuList.addEventListener(MouseEvent.CLICK, onACMenuListClickHandler);
			//this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			//this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);	
			//this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
			//this.addViewListener(
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ACMenuViewMediator.as");
			//this.removeContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList);
			//this.removeContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList);
			acMenuView.menuList.removeEventListener(MouseEvent.CLICK, onACMenuListClickHandler);
		}
		protected function onACMenuListClickHandler(e:MouseEvent):void
		{
			var acSplitNavigator:SplitViewNavigator=FlexGlobals.topLevelApplication.acSvn as SplitViewNavigator;
			var detailNavigator:ViewNavigator=acSplitNavigator.getViewNavigatorAt(1) as ViewNavigator;
			var obj:Object=e.currentTarget.selectedItem;
			detailNavigator.pushView(obj.view, obj.label);
			acSplitNavigator.hideViewNavigatorPopUp();
		}
	}
}