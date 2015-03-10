package com.sickkids.caliper.view.ttt
{
	
	import com.sickkids.caliper.model.TttModel;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class AdminPanelMediator extends Mediator
	{
		[Inject] public var view:AdminPanel;
		[Inject] public var model:TttModel;
		public function AdminPanelMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in AdminPanelMediator.as");
			
			//view.localVideoDisplay.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationCompleteOfVideoDisplay);
			//controlContainer.settings.addEventListener(MouseEvent.CLICK, onSettingsClick);
			view.log(model.userInfo.toString());
			//view.adminAccordion.addEventListener(
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in AdminPanelMediator.as");
		}
		
	}
}