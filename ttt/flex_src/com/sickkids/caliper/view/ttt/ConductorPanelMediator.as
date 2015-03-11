package com.sickkids.caliper.view.ttt
{

	import com.sickkids.caliper.model.TttModel;

	
	import flash.media.Camera;
	
	import mx.events.FlexEvent;
	
	import spark.components.mediaClasses.DynamicStreamingVideoItem;
	import spark.components.mediaClasses.DynamicStreamingVideoSource;
	
	import org.osmf.net.StreamType;
	import org.robotlegs.mvcs.Mediator;
	
	public class ConductorPanelMediator extends Mediator
	{
		[Inject] public var view:ConductorPanel;
		[Inject] public var model:TttModel;
		
		public function ConductorPanelMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ConductorPanelMediator.as");
			
			attachPublishCamera();

		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ConductorPanelMediator.as");
		}
		private function attachPublishCamera():void
		{
			trace("INFO: attachPublishCamera("+model.userInfo.participantType+") is called in ConductorPanelMediator.as");
			
			var videoItems:Vector.<DynamicStreamingVideoItem>=null;
			var dynVideoSource:DynamicStreamingVideoSource=null;
			
			if(model.userInfo.participantType.indexOf("ONEWAY_VIEWER")==-1)
			{
				var _cam:DynamicStreamingVideoSource =  new DynamicStreamingVideoSource();  
				
				
				videoItems=new Vector.<DynamicStreamingVideoItem>();  
				videoItems[0]=new DynamicStreamingVideoItem(); 
				
				dynVideoSource=new DynamicStreamingVideoSource();  
				
				dynVideoSource.host= "";  
				dynVideoSource.streamType=StreamType.LIVE;  
				dynVideoSource.streamItems=videoItems;  
				
				view.localVideoDisplay.source=dynVideoSource;
				model.localCamera=Camera.getCamera(); //Camera.names[0]); 
				view.localVideoDisplay.videoObject.attachCamera(model.localCamera);
			}
			
			if(model.userInfo.participantType=="LECTURER")
			{
				if(view.position=="DR")
				{
					
				}
				else if(view.position=="RN")
				{
					
				}
				
			}
			else if(model.userInfo.participantType=="TEACHING_ASSISTANT")
			{
				if(view.position=="DR")
				{
					
				}
				else if(view.position=="RN")
				{
					
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER1")
			{
				if(view.position=="DR")
				{
					
				}
				else if(view.position=="RN")
				{
					
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER2")
			{
				if(view.position=="DR")
				{
					
				}
				else if(view.position=="RN")
				{
					
				}
			}
			else
			{
				//left side then DR, right side then RN
				if(view.position=="DR")
				{
					
				}
				else if(view.position=="RN")
				{
					
				}
			}
		}
	}
}