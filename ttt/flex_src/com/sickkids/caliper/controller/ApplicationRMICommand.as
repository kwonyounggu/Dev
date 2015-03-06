package com.sickkids.caliper.controller
{	
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.service.IApplicationRemoteObjectService;
	
	import org.robotlegs.mvcs.Command;
	
	public class ApplicationRMICommand extends Command
	{
		[Inject] public var event:ApplicationRMIEvent;
		[Inject] public var _remoteService:IApplicationRemoteObjectService;
		
		public function ApplicationRMICommand()
		{
			super();
		}
		
		override public function execute():void
		{
			// TODO Auto Generated method stub
			trace("INFO: execute() is called in ApplicationRMICommand.as");
			switch(event.type)
			{
				case ApplicationRMIEvent.GET_APP_LIST_EVENT:
						_remoteService.callService("getAppList");
						trace("INFO: "+ApplicationRMIEvent.GET_APP_LIST_EVENT+" is called in ApplicationRMICommand.as");
						break;
				/*
				case RawDataRMIEvent.GET_STRING:
						_remoteService.callService("getTestString", event.arg1);
						trace("INFO: "+RawDataRMIEvent.GET_STRING+" is called in ApplicationRMICommand.as");
					break;
				case RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST:
						_remoteService.callService("getInstrumentNameList");
						trace("INFO: "+RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST+" is called in ApplicationRMICommand.as");
					  	break;
				case RawDataRMIEvent.GET_ANALYTE_NAME_LIST:
					_remoteService.callService("getAnalyteList", event.arg1, event.arg2); //arg1: instrument_name_id, arg2: system_metric
					trace("INFO: "+RawDataRMIEvent.GET_ANALYTE_NAME_LIST+" with "+event.arg1+", "+event.arg2+" is called in ApplicationRMICommand.as");
					break;
				case RawDataRMIEvent.GET_RAW_DATA_LIST:
					_remoteService.callService("getRawDataList", event.arg1, event.arg2, event.arg3, event.arg4, event.arg5, event.arg6); //arg1: instrument_name_id, arg2: system_metric
					trace("INFO: "+RawDataRMIEvent.GET_RAW_DATA_LIST+" with "+event.arg1+", "+event.arg2+", "+event.arg3+", "+event.arg4+", "+event.arg5+", "+event.arg6+" is called in ApplicationRMICommand.as");
					break;*/
				default: 
					trace("---2---");
					break;
			}
		}
	}
}