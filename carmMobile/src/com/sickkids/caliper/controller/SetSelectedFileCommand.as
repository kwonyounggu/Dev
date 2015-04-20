package com.sickkids.caliper.controller
{
	import com.sickkids.caliper.events.FileResultEvent;
	import com.sickkids.caliper.model.SimpleModel;
	
	import org.robotlegs.mvcs.Command;
	
	public class SetSelectedFileCommand extends Command
	{
		[Inject] public var event:FileResultEvent;
		[Inject] public var simpleModel:SimpleModel;
		
		public function SetSelectedFileCommand()
		{
			super();
		}
		
		override public function execute():void
		{
			trace("INFO: execute(...) is called in SetSelectedFileCommand.as, event.fileName="+event.fileName);
			simpleModel.fileName=event.fileName;
		}
	}
}