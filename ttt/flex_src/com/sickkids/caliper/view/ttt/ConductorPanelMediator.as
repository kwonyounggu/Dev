package com.sickkids.caliper.view.ttt
{

	import com.sickkids.caliper.model.TttModel;
	
	import flash.events.ActivityEvent;
	import flash.events.StatusEvent;
	import flash.media.Camera;
	import flash.media.Microphone;
	import flash.media.MicrophoneEnhancedMode;
	import flash.media.MicrophoneEnhancedOptions;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.events.FlexEvent;
	import mx.events.ResizeEvent;
	import mx.utils.StringUtil;
	
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
			view.localVideoDisplay.addEventListener(ResizeEvent.RESIZE, onVideoDisplayResize);
			
			
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
				model.localCamera=Camera.getCamera(); //Camera.names[0]);
				if(model.localCamera)
				{
					videoItems=new Vector.<DynamicStreamingVideoItem>();  
					videoItems[0]=new DynamicStreamingVideoItem(); 
					
					dynVideoSource=new DynamicStreamingVideoSource();  
					
					dynVideoSource.host= "";  
					dynVideoSource.streamType=StreamType.LIVE;  
					dynVideoSource.streamItems=videoItems;  
					
					view.localVideoDisplay.source=dynVideoSource;
					 
					view.localVideoDisplay.videoObject.attachCamera(model.localCamera);
					
					//settings
					model.localCamera.setQuality(0, 80);//bandwidth, quality
					model.localCamera.setMode(320,240,15); //width, height, fps
					model.localCamera.addEventListener(StatusEvent.STATUS, onCameraStatus);
					model.localCamera.addEventListener(ActivityEvent.ACTIVITY, onCameraActivity);
					
				}
				else
				{
					Alert.show("No Webcam is found!");
					FlexGlobals.topLevelApplication.log("No webcam is found!");
				}
				//Important: At any given time you can have only a single instance of enhanced microphone device. 
				//All other Microphone instances stop providing audio data and receive a StatusEvent with the code property Microphone.Unavailable. 
				//When enhanced audio fails to initialize, calls to this method return null, setting a value for Microphone.enhancedOptions has no effect, 
				//and all existing Microphone instances function as before.
				//To configure an enhanced Microphone object, set the Microphone.enhancedOptions property. The following code uses an enhanced Microphone object and full-duplex acoustic echo cancellation in a local test:
				//var mic:Microphone = Microphone.getEnhancedMicrophone();
				//var options:MicrophoneEnhancedOptions = new MicrophoneEnhancedOptions();
				//options.mode = MicrophoneEnhancedMode.FULL_DUPLEX;
				//mic.enhancedOptions = options;
				//mic.setLoopBack(true);
				//The setUseEchoSuppression() method is ignored when using acoustic echo cancellation. 
				//When a SWF file tries to access the object returned by Microphone.getEnhancedMicrophone() 
				//—for example, when you call NetStream.attachAudio()— Flash Player displays a Privacy dialog box that lets the user choose 
				//whether to allow or deny access to the microphone. (Make sure your Stage size is at least 215 x 138 pixels; this is the minimum size Flash Player requires to display the dialog box.) 
						
				model.localMic=Microphone.getEnhancedMicrophone();
				if(model.localMic)
				{
					var options:MicrophoneEnhancedOptions=new MicrophoneEnhancedOptions();
					options.mode=MicrophoneEnhancedMode.FULL_DUPLEX;
					model.localMic.enhancedOptions=options;
					model.localMic.setLoopBack(true);
					//set events
					//mic.addEventListener(ActivityEvent.ACTIVITY, this.onActivityHandlerForMicrophone);
					//mic.addEventListener(StatusEvent.STATUS, this.micStatus);
				}
				else
				{
					FlexGlobals.topLevelApplication.log("No Microphone is found!");
				}
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
		private function onVideoDisplayResize(e:ResizeEvent):void
		{
			trace("INFO: onVideoDisplayResize() is called in ConductorPanelMediator.as");
		}
		private function onCameraStatus(e:StatusEvent):void
		{
			var str:String = "[{0}] code:'{1}', level:'{2}'\n";

			FlexGlobals.topLevelApplication.log(StringUtil.substitute(str, e.type, e.code, e.level));
			switch (e.code) 
			{
				case "Camera.Muted":
					FlexGlobals.topLevelApplication.log("(Camera.Muted) User denied access to camera in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
				case "Camera.Unmuted":
					FlexGlobals.topLevelApplication.log("(Camera.Unmuted) User allowed access to camera in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
				default:
					FlexGlobals.topLevelApplication.log("Unkwon status="+e.code+" in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
			}
		}
		private function onCameraActivity(e:ActivityEvent):void
		{
			var str:String = "[{0}] activating:{1}\n";
			var cam:Camera=e.currentTarget as Camera;
			FlexGlobals.topLevelApplication.log("Camera name="+cam.name+" "+
												"activity level="+cam.activityLevel+" "+
												"index="+cam.index+" "+
												"BW="+cam.bandwidth+" "+
												"FPS="+cam.currentFPS+" "+
												StringUtil.substitute(str, e.type, e.activating));
		}
	}
	
}