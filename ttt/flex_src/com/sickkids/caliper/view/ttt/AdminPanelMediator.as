package com.sickkids.caliper.view.ttt
{
	
	import com.sickkids.caliper.model.TttModel;
	
	import mx.events.FlexEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class AdminPanelMediator extends Mediator
	{
		[Inject] public var view:AdminPanel;
		[Inject] public var tttModel:TttModel;
		public function AdminPanelMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in AdminPanelMediator.as");
			view.log(tttModel.userInfo.toString()+"displayed in onRegister() of AdminPanelMediator.as");
			initializeAdminPanel();
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in AdminPanelMediator.as");
		}
		protected function initializeAdminPanel():void
		{
			trace("INFO: onCreationCompleteOfAdminPanel(outside) is called in AdminPanelMediator.as, I am "+tttModel.userInfo.userId+", "+tttModel.userInfo.participantType);

			if(!(tttModel.userInfo.participantType=="LECTURER" || tttModel.userInfo.participantType=="TEACHING_ASSISTANT"))
			{	
				trace("INFO: onCreationCompleteOfAdminPanel(inside) is called in AdminPanelMediator.as, I am "+tttModel.userInfo.userId+", "+tttModel.userInfo.participantType);
				
				for(var i:int=8;i>0;i--) view.logTab.removeChildAt(i);//visibility for the following tabs are not required
			}
		}
		
	}
}