package com.sickkids.caliper.view
{
	import com.sickkids.caliper.events.SimpleModelEvent;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class TextContainerViewMediator extends Mediator
	{
		[Inject] public var view:TextContainerView;
		
		public function TextContainerViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			this.addContextListener(SimpleModelEvent.FILE_NAME_CHANGE, onFileNameChange, SimpleModelEvent)
		}
		private function onFileNameChange(e:SimpleModelEvent):void
		{
			view.setFileName(e.value);
		}
	}
}