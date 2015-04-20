package com.sickkids.caliper.service
{
	import com.sickkids.caliper.events.FileResultEvent;
	
	import flash.events.Event;
	import flash.net.FileReference;
	
	import org.robotlegs.mvcs.Actor;

	public class FileService extends Actor implements IFileService
	{
		private var _fileRef:FileReference;
		
		public function FileService()
		{
			_fileRef=new FileReference();
			_fileRef.addEventListener(Event.SELECT, onFileSelect);
		}
		
		public function promptToChooseFile():void
		{
			_fileRef.browse();
		}
		private function onFileSelect(e:Event):void
		{
			trace("INFO: onFileSelect() with "+ _fileRef.name+", "+_fileRef.type+" is called in FileService.as");
			//event to the context event bus
			this.dispatch(new FileResultEvent(FileResultEvent.FILE_RESULT, _fileRef.name));
		}
	}
}