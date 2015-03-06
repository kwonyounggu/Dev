package com.sickkids.caliper.controller
{
	import com.sickkids.caliper.service.IFileService;
	
	import org.robotlegs.mvcs.Command;
	
	public class ChooseFileCommand extends Command
	{
		[Inject] public var fileService:IFileService;
		
		public function ChooseFileCommand()
		{
			trace("INFO: ChooseFileCommand(...) is called in ChooseFileCommand.as");
			super();
		}
		override public function execute():void
		{
			trace("INFO: execute(...) is called in ChooseFileCommand.as");
			fileService.promptToChooseFile();
		}
	}
}