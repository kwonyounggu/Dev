package com.sickkids.caliper.view.cap
{
	import com.sickkids.caliper.components.Message;
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.model.CapModel;
	import com.sickkids.caliper.view.navigator.CapViewNavigator;
	
	import flash.events.MouseEvent;
	
	import flashx.textLayout.conversion.TextConverter;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class GetLoginInfoViewMediator extends Mediator
	{
		[Inject] public var view:GetLoginInfoView;
		[Inject] public var capModel:CapModel;
		
		public function GetLoginInfoViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in GetLoginInfoViewMediator.as");
			
			//CapViewNavigator(view.navigator).capMenuButton.visible=false;
			
			view.submitButton.addEventListener(MouseEvent.CLICK, onSubmitButton_clickHandler);
			this.addContextListener(CapRMIEvent.SENT_LOGIN_INFO_EVENT, onSentLoginInfo, CapRMIEvent);
			this.addContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in GetLoginInfoViewMediator.as");
			view.submitButton.removeEventListener(MouseEvent.CLICK, onSubmitButton_clickHandler);
			this.removeContextListener(CapRMIEvent.SENT_LOGIN_INFO_EVENT, onSentLoginInfo, CapRMIEvent);
			this.removeContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
		}
		private function displayErrorMessage(msg:String):void
		{
			view.alertContainerBorderContainer.visible=view.alertContainerBorderContainer.includeInLayout=true;
			view.alertMessage.textFlow=TextConverter.importToFlow("<p>"+msg+"</p>", TextConverter.TEXT_FIELD_HTML_FORMAT);
		}
		private function onRemoteObjectFault(e:ApplicationRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in GetLoginInfoViewMediator.as");			
			displayErrorMessage(e.arg3 as String);//error fault string
		}
		protected function onSubmitButton_clickHandler(event:MouseEvent):void
		{
			var reg:RegExp = /\w+?@\w+?\.\w{2}/g;//Email regular expression
			if(reg.test(view.emailInput.text)) 
			{
				trace("--- PASS EMAIL---");
				dispatch(new CapRMIEvent(CapRMIEvent.SEND_LOGIN_INFO_EVENT, capModel.appId, view.emailInput.text));
			}
			else 
			{
				trace("--- FAIL EMAIL---");
				displayErrorMessage(Message.EMAIL_INPUT_INCORRECT_MSG);
			}
		}
		private function onSentLoginInfo(e:CapRMIEvent):void
		{
			var bSent:Boolean=e.arg1 as Boolean;
			
			trace("onSentLoginInfo("+bSent.toString()+") in GetLoginInfoViewMediator.as");
			if(bSent) view.navigator.popToFirstView();
			else displayErrorMessage(Message.EMAIL_NOT_EXIST_MSG);
		}
	}
}