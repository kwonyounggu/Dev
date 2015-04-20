package com.sickkids.caliper.view.cap
{
	import com.sickkids.caliper.components.AppCommon;
	import com.sickkids.caliper.components.Message;
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.model.CapModel;

	import com.sickkids.caliper.vo.AllRegisteredUserBean;
	
	import flash.events.MouseEvent;
	

	import mx.collections.ArrayCollection;

	
	import flashx.textLayout.conversion.TextConverter;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class LoginViewMediator extends Mediator
	{
		[Inject] public var view:LoginView;
		[Inject] public var capModel:CapModel;
	
		public function LoginViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in LoginViewMediator.as");
			//this.addViewListener(SimpleAppEvent.CHOOSE_FILE, dispatch, SimpleAppEvent);//redirect the event to the event bus in the context
			//this.addViewListener(RawDataRMIEvent.GET_STRING, dispatch, RawDataRMIEvent);
			
			/*view.ageFromList.dataProvider=caliperModel.ageList;
			view.ageToList.dataProvider=caliperModel.ageList;
			
			view.instrumentList.addEventListener(IndexChangeEvent.CHANGE, onInstrumentList_changeHandler);
			view.analyteList.addEventListener(IndexChangeEvent.CHANGE, onAnalyteList_changeHandler);
			view.analyteList.labelFunction=analyteLabelFunction;
			
			view.gender.addEventListener(ItemClickEvent.ITEM_CLICK, onGender_itemClickHandler);
			view.metricSystem.addEventListener(ItemClickEvent.ITEM_CLICK, onMetric_itemClickHandler);
			view.submitButton.addEventListener(MouseEvent.CLICK, onSubmitButton_clickHandler);
			
			view.plotFemaleId.dataFunction=plotDataFunction;
			view.plotMaleId.dataFunction=plotDataFunction;
			
			this.addContextListener(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST, onGotInstrumentNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST, onGotAnalyteNameList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.GOT_RAW_DATA_LIST, onGotRawDataList, RawDataRMIEvent);
			this.addContextListener(RawDataRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, RawDataRMIEvent);
			*/
			//if(view.rememeber my information is true)
			//then the following two lines 
			//if(view.rememberCheckBox.selected)
			//{
			//	view.loginID.text=capModel.loginId;
			//	view.password.text=capModel.password;
			//}
			
			//CapViewNavigator(view.navigator).capMenuButton.visible=false;
				
			view.loginButton.addEventListener(MouseEvent.CLICK, onLoginButton_clickHanlder);
			view.newpasswordSubmitButton.addEventListener(MouseEvent.CLICK, onNewPasswordSubmitButton_clickHandler);
			this.addContextListener(CapRMIEvent.CHECKED_SIGNIN_EVENT, onCheckedSignIn, CapRMIEvent);
			this.addContextListener(CapRMIEvent.SAVED_PWD_EVENT, onSavedPwd, CapRMIEvent);
			this.addContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			//this.addContextListener(CapRMIEvent.GOT_ITEM_LIST_EVENT, onGotItemList, CapRMIEvent);
			
			trace("capModel is NULL="+(capModel==null)+", checked="+capModel.bRememberCheckBox);
			if(capModel.bRememberCheckBox)
			{
				view.rememberCheckBox.selected=true;
				view.loginID.text=capModel.loginId;
				view.passwordInput0.text=capModel.password;
			}
			else
			{
				view.rememberCheckBox.selected=false;
				view.loginID.text="";
				view.passwordInput0.text="";
			}
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in LoginViewMediator.as");
			view.loginButton.removeEventListener(MouseEvent.CLICK, onLoginButton_clickHanlder);
			view.newpasswordSubmitButton.removeEventListener(MouseEvent.CLICK, onNewPasswordSubmitButton_clickHandler);
			this.removeContextListener(CapRMIEvent.CHECKED_SIGNIN_EVENT, onCheckedSignIn, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.SAVED_PWD_EVENT, onSavedPwd, CapRMIEvent);
			this.removeContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			//this.removeContextListener(CapRMIEvent.GOT_ITEM_LIST_EVENT, onGotItemList, CapRMIEvent);
		}
		protected function onLoginButton_clickHanlder(event:MouseEvent):void
		{
			if(view.loginID.text.length<1 || view.passwordInput0.text.length<1)
				displayErrorMessage(Message.LOGIN_INPUT_INCORRECT_MSG);
			else
			{
				capModel.loginId=view.loginID.text;
				capModel.password=view.passwordInput0.text;
				capModel.bRememberCheckBox=view.rememberCheckBox.selected;
				AppCommon.cap_setSharedLoginObject(capModel.loginId, capModel.password, capModel.bRememberCheckBox);//save them in a local storage
				dispatch(new CapRMIEvent(CapRMIEvent.CHECK_SIGNIN_EVENT, capModel.appId, capModel.loginId, capModel.password));
			}
			
		}
		private function displayErrorMessage(msg:String):void
		{
			view.alertContainerBorderContainer.visible=view.alertContainerBorderContainer.includeInLayout=true;
			view.alertMessage.textFlow=TextConverter.importToFlow("<p>"+msg+"</p>", TextConverter.TEXT_FIELD_HTML_FORMAT);
		}
		protected function onNewPasswordSubmitButton_clickHandler(event:MouseEvent):void
		{
			capModel.password=view.passwordInput1.text;
			if(AppCommon.verify2Passwords(view.passwordInput1.text, view.passwordInput2.text))
			{	trace("--- PASS ---");
				AppCommon.cap_setSharedLoginObject(capModel.loginId, capModel.password, capModel.bRememberCheckBox);//save them in a local storage
				dispatch(new CapRMIEvent(CapRMIEvent.SAVE_PWD_EVENT, capModel.appId, capModel.loginId, capModel.password));
			}
			else
				displayErrorMessage(Message.PWD_INPUT_INCORRECT_MSG);
			
			
		}
		private function onCheckedSignIn(e:CapRMIEvent):void
		{
			//var ab:AllRegisteredUserBean=e.arg1 as AllRegisteredUserBean;
			capModel.crbm=e.arg1 as AllRegisteredUserBean;
			trace("INFO: appId="+capModel.crbm.appId+", resetPassword="+capModel.crbm.resetPassword+" onCheckedSignIn(...) in LoginViewMediator.as");
			if(capModel.crbm.userId!=AllRegisteredUserBean.USER_ID && capModel.crbm.password==capModel.password) //check accessLimitTime in addition
			{
				if(capModel.crbm.resetPassword==1)
				{
					view.alertContainerBorderContainer.visible=view.alertContainerBorderContainer.includeInLayout=false;
					view.loginBorderContainer.visible=view.loginBorderContainer.includeInLayout=false;
					view.newpasswordBorderContainer.visible=view.newpasswordBorderContainer.includeInLayout=true;
				}
				else
				{
					//login passed and now request an item list for this person
					//dispatch(new CapRMIEvent(CapRMIEvent.GET_ITEM_LIST_EVENT, capModel.crbm, "ALL"));
					//here call the 1st page of reviewView.mxml
					view.navigator.pushView(ReviewView);
				}
			}
			else
				displayErrorMessage(Message.LOGIN_FAILURE_MSG);
			
		}
		private function onSavedPwd(e:CapRMIEvent):void
		{
			var bSaved:Boolean=e.arg1 as Boolean;

			trace("onSavedPwd("+bSaved.toString()+") in LoginViewMediator.as");
			if(bSaved) 
			{
				//dispatch(new CapRMIEvent(CapRMIEvent.GET_ITEM_LIST_EVENT, capModel.crbm, "ALL"));//and now request an item list for this person
				//here call the 1st page of reviewView.mxml
				
				view.navigator.pushView(ReviewView);
			}
			else displayErrorMessage(Message.PWD_SAVING_FAILURE_MSG);
		}
		private function onRemoteObjectFault(e:ApplicationRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in LoginViewMediator.as");			
			displayErrorMessage(e.arg3 as String);//error fault string
		}
		/*private function onGotItemList(e:CapRMIEvent):void
		{			
			capModel.itemList=e.arg1 as ArrayCollection;
			trace("INFO: onGotItemList() is called and "+(capModel.itemList==null)+", "+((capModel.itemList==null) ? "NULL":capModel.itemList.length)+" in LoginViewMediator.as");
			
			if(capModel.itemList==null || capModel.itemList.length==0) displayErrorMessage("You do not have any item to manipulate. Please contact the project manager then try again!");
			else view.navigator.pushView(ReviewView);
		}*/
	}
}