package com.sickkids.caliper.controller
{	
	import com.sickkids.caliper.events.RawDataRMIEvent;
	import com.sickkids.caliper.service.IRemoteObjectService;
	
	import org.robotlegs.mvcs.Command;
	
	public class RawDataRMICommand extends Command
	{
		[Inject] public var event:RawDataRMIEvent;
		[Inject] public var _remoteService:IRemoteObjectService;
		
		public function RawDataRMICommand()
		{
			super();
		}
		
		override public function execute():void
		{
			// TODO Auto Generated method stub
			trace("INFO: execute() is called in RawDataRMICommand.as, "+event.type+", "+event.arg1);
			switch(event.type)
			{
				case RawDataRMIEvent.GET_STRING:
						_remoteService.callService("getTestString", event.arg1);
						trace("INFO: "+RawDataRMIEvent.GET_STRING+" is called in RawDataRMICommand.as");
					break;
				case RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST:
						_remoteService.callService("getInstrumentNameList");
						trace("INFO: "+RawDataRMIEvent.GET_INSTRUMENT_NAME_LIST+" is called in RawDataRMICommand.as");
					  	break;
				case RawDataRMIEvent.GET_ANALYTE_NAME_LIST:
					_remoteService.callService("getAnalyteList", event.arg1, event.arg2); //arg1: instrument_name_id, arg2: system_metric
					trace("INFO: "+RawDataRMIEvent.GET_ANALYTE_NAME_LIST+" with "+event.arg1+", "+event.arg2+" is called in RawDataRMICommand.as");
					break;
				case RawDataRMIEvent.GET_RAW_DATA_LIST:
					_remoteService.callService("getRawDataList", event.arg1, event.arg2, event.arg3, event.arg4, event.arg5, event.arg6); //arg1: instrument_name_id, arg2: system_metric
					trace("INFO: "+RawDataRMIEvent.GET_RAW_DATA_LIST+" with "+event.arg1+", "+event.arg2+", "+event.arg3+", "+event.arg4+", "+event.arg5+", "+event.arg6+" is called in RawDataRMICommand.as");
					break;
				default: 
					trace("---2---");
					break;
			}
		}
	}
}