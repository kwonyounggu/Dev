package com.sickkids.caliper.controller
{	
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.service.INetConnectionService;
	import com.sickkids.caliper.vo.UserInfoBean;
	
	import org.robotlegs.mvcs.Command;
	
	public class TttNetConnectionCommand extends Command
	{
		[Inject] public var event:TttNetConnectionEvent;
		[Inject] public var _netConnectionService:INetConnectionService;
		
		public function TttNetConnectionCommand()
		{
			super();
		}
		
		override public function execute():void
		{
			// TODO Auto Generated method stub
			trace("INFO: execute() is called in TttNetConnectionCommand.as, "+event.type+", arg1="+event.arg1);
			switch(event.type)
			{
				case TttNetConnectionEvent.ROOM_CONNECT_EVENT:
					 _netConnectionService.roomConnect(event.arg1 as UserInfoBean);
					 break;
				/*
				case CapRMIEvent.CHECK_SIGNIN_EVENT:
					_netConnectionService.callService("checkSignIn", event.arg1, event.arg2, event.arg3);
					trace("INFO: "+CapRMIEvent.CHECK_SIGNIN_EVENT+", "+event.arg1+", "+event.arg2+", "+event.arg3+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.SAVE_PWD_EVENT:
					_netConnectionService.callService("savePwd", event.arg1, event.arg2, event.arg3);//appId, userId, pwd
					trace("INFO: "+CapRMIEvent.SAVE_PWD_EVENT+", "+event.arg1+", "+event.arg2+", "+event.arg3+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.SEND_LOGIN_INFO_EVENT:
					_netConnectionService.callService("sendLoginInfo", event.arg1, event.arg2);//appId, email
					trace("INFO: "+CapRMIEvent.SEND_LOGIN_INFO_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_ITEM_LIST_EVENT:
					_netConnectionService.callService("getItemList", event.arg1, event.arg2);//AllRegisteredUserBean, review_status=ALL
					trace("INFO: "+CapRMIEvent.GET_ITEM_LIST_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT:
					_netConnectionService.callService("getClinicalSummary", event.arg1, event.arg2);//AllRegisteredUserBean, itemId
					trace("INFO: "+CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_DIAGNOSES_EVENT:
					_netConnectionService.callService("getDiagnoses", event.arg1, event.arg2);//AllRegisteredUserBean, de2Id
					trace("INFO: "+CapRMIEvent.GET_DIAGNOSES_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_PROBLEMS_EVENT:
					_netConnectionService.callService("getProblems", event.arg1, event.arg2);//AllRegisteredUserBean, de4Id
					trace("INFO: "+CapRMIEvent.GET_PROBLEMS_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_LABOROTORY_EVENT:
					_netConnectionService.callService("getLaborotory", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_LABOROTORY_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_VITALSIGN_EVENT:
					_netConnectionService.callService("getVitalSign", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_VITALSIGN_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_MEDICATION_EVENT:
					_netConnectionService.callService("getMedications", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_MEDICATION_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_RADIOLOGY_EVENT:
					_netConnectionService.callService("getRadiologyRecord", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_RADIOLOGY_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				case CapRMIEvent.GET_QPOINTS_EVENT:
					_netConnectionService.callService("getAllPointDescList", event.arg1, event.arg2);//AllRegisteredUserBean, CarmDeMXBeanM
					trace("INFO: "+CapRMIEvent.GET_QPOINTS_EVENT+", "+event.arg1+", "+event.arg2+" is called in TttNetConnectionCommand.as");
					break;
				*/
				default: 
					trace("INFO: UNKWON REQUEST is called in TttNetConnectionCommand.as");
					break;
			}
		}
	}
}


