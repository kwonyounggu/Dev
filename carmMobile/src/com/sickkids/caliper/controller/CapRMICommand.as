package com.sickkids.caliper.controller
{	
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.service.ICapRemoteObjectService;
	
	import org.robotlegs.mvcs.Command;
	
	public class CapRMICommand extends Command
	{
		[Inject] public var event:CapRMIEvent;
		[Inject] public var _remoteService:ICapRemoteObjectService;
		
		public function CapRMICommand()
		{
			super();
		}
		
		override public function execute():void
		{
			// TODO Auto Generated method stub
			trace("INFO: execute() is called in CapRMICommand.as, "+event.type+", arg1="+event.arg1);
			switch(event.type)
			{
				case CapRMIEvent.CHECK_SIGNIN_EVENT:
						_remoteService.callService("checkSignIn", event.arg1, event.arg2, event.arg3);
						trace("INFO: "+CapRMIEvent.CHECK_SIGNIN_EVENT+", "+event.arg1+", "+event.arg2+", "+event.arg3+" is called in CapRMICommand.as");
						break;
				case CapRMIEvent.SAVE_PWD_EVENT:
					_remoteService.callService("savePwd", event.arg1, event.arg2, event.arg3);//appId, userId, pwd
					trace("INFO: "+CapRMIEvent.SAVE_PWD_EVENT+", "+event.arg1+", "+event.arg2+", "+event.arg3+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.SEND_LOGIN_INFO_EVENT:
					_remoteService.callService("sendLoginInfo", event.arg1, event.arg2);//appId, email
					trace("INFO: "+CapRMIEvent.SEND_LOGIN_INFO_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_ITEM_LIST_EVENT:
					_remoteService.callService("getItemList", event.arg1, event.arg2);//AllRegisteredUserBean, review_status=ALL
					trace("INFO: "+CapRMIEvent.GET_ITEM_LIST_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT:
					_remoteService.callService("getClinicalSummary", event.arg1, event.arg2);//AllRegisteredUserBean, itemId
					trace("INFO: "+CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_DIAGNOSES_EVENT:
					_remoteService.callService("getDiagnoses", event.arg1, event.arg2);//AllRegisteredUserBean, de2Id
					trace("INFO: "+CapRMIEvent.GET_DIAGNOSES_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_PROBLEMS_EVENT:
					_remoteService.callService("getProblems", event.arg1, event.arg2);//AllRegisteredUserBean, de4Id
					trace("INFO: "+CapRMIEvent.GET_PROBLEMS_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_LABOROTORY_EVENT:
					_remoteService.callService("getLaborotory", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_LABOROTORY_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_VITALSIGN_EVENT:
					_remoteService.callService("getVitalSign", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_VITALSIGN_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_MEDICATION_EVENT:
					_remoteService.callService("getMedications", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_MEDICATION_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_RADIOLOGY_EVENT:
					_remoteService.callService("getRadiologyRecord", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_RADIOLOGY_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				case CapRMIEvent.GET_QPOINTS_EVENT:
					_remoteService.callService("getAllPointDescList", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_QPOINTS_EVENT+", "+event.arg1+", "+event.arg2+" is called in CapRMICommand.as");
					break;
				
				default: 
					trace("INFO: UNKWON REQUEST is called in CapRMICommand.as");
					break;
			}
		}
	}
}