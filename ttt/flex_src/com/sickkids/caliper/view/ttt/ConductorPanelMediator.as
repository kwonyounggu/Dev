package com.sickkids.caliper.view.ttt
{

	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	
	import flash.events.ActivityEvent;
	import flash.events.NetStatusEvent;
	import flash.events.StatusEvent;
	import flash.media.Camera;
	import flash.media.Microphone;
	import flash.media.MicrophoneEnhancedMode;
	import flash.media.MicrophoneEnhancedOptions;
	import flash.media.Video;
	import flash.net.NetStream;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;

	import mx.utils.StringUtil;
	
	import spark.components.mediaClasses.DynamicStreamingVideoItem;
	import spark.components.mediaClasses.DynamicStreamingVideoSource;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ConductorPanelMediator extends Mediator
	{
		[Inject] public var view:ConductorPanel;
		[Inject] public var model:TttModel;
		[Inject] public var netConnectionService:INetConnectionService;
		
		public function ConductorPanelMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ConductorPanelMediator.as");
			//view.localWebcamDisplay.addEventListener(ResizeEvent.RESIZE, onVideoDisplayResize);
			this.addContextListener(TttNetConnectionEvent.ROOM_CONNECTED_EVENT, onNetConnectionStatus, TttNetConnectionEvent);
			this.addContextListener(TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT, onNetConnectionStatus, TttNetConnectionEvent);			
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
					/*videoItems=new Vector.<DynamicStreamingVideoItem>();  
					videoItems[0]=new DynamicStreamingVideoItem(); 
					
					dynVideoSource=new DynamicStreamingVideoSource();  
					
					dynVideoSource.host= "";  
					dynVideoSource.streamType=StreamType.LIVE;  
					dynVideoSource.streamItems=videoItems;  
					
					view.localWebcamDisplay.source=dynVideoSource;
					 
					view.localWebcamDisplay.videoObject.attachCamera(model.localCamera);
					*/
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachCamera(model.localCamera);
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
					model.localMic.addEventListener(ActivityEvent.ACTIVITY, onMicrophoneonActivity);
					model.localMic.addEventListener(StatusEvent.STATUS, onMicrophoneonStatus);
				}
				else
				{
					FlexGlobals.topLevelApplication.log("No Microphone is found!");
				}
			}
			
			if(model.userInfo.participantType=="LECTURER")
			{
				if(view.myObject=="DR")
				{
					//play INTERACTIVE_VIEWER1
					//publish LECTURER
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;					
					//publish Dr.WebCam and Audio
					view.netStream1.attachAudio(model.localMic);
					view.netStream1.attachCamera(model.localCamera);
					view.netStream1.publish(model.userInfo.lecturerId);
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.activeParticipantId1);
				}
				else if(view.myObject=="RN")//instance
				{
					//play INTERACTIVE_VIEWER2
					//publish RN
				}
				
			}
			else if(model.userInfo.participantType=="TEACHING_ASSISTANT")
			{
				if(view.myObject=="DR")
				{
					
				}
				else if(view.myObject=="RN")
				{
					
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER1")
			{
				if(view.myObject=="DR")
				{
					
				}
				else if(view.myObject=="RN")
				{
					
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER2")
			{
				if(view.myObject=="DR")
				{
					
				}
				else if(view.myObject=="RN")
				{
					
				}
			}
			else
			{
				//left side then DR, right side then RN
				if(view.myObject=="DR")
				{
					
				}
				else if(view.myObject=="RN")
				{
					
				}
			}
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
			FlexGlobals.topLevelApplication.log("Camera name="+cam.name+"\n"+
												"activity level="+cam.activityLevel+"\n"+
												"index="+cam.index+"\n"+
												"BW="+cam.bandwidth+"\n"+
												"FPS="+cam.currentFPS+"\n"+
												StringUtil.substitute(str, e.type, e.activating));
		}
		private function onMicrophoneonStatus(evt:StatusEvent):void 
		{
			var str:String = "[{0}] code:'{1}', level:'{2}'\n";

			FlexGlobals.topLevelApplication.log(StringUtil.substitute(str, evt.type, evt.code, evt.level));
			switch (evt.code) 
			{
				case "Microphone.Muted":
					FlexGlobals.topLevelApplication.log("(Microphone.Muted) User denied access to camera in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
				case "Microphone.Unmuted":
					FlexGlobals.topLevelApplication.log("(Microphone.Unmuted) User allowed access to camera in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
				default:
					FlexGlobals.topLevelApplication.log("Unkwon status="+evt.code+" in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
			}
		}
		private function onMicrophoneonActivity(e:ActivityEvent):void
		{
			//By ActivityLevel, Activating and so on, you can activate recording
			var str:String = "[{0}] activating:{1}\n";
			var mic:Microphone=e.currentTarget as Microphone;
			FlexGlobals.topLevelApplication.log("Microphone name="+mic.name+
												"activity level="+mic.activityLevel+"\n"+
												"codec="+mic.codec+"\n"+
												"index="+mic.index+"\n"+
												"gain="+mic.gain+"\n"+
												"rate="+mic.rate+"\n"+
												StringUtil.substitute(str, e.type, e.activating));
		}
		private function onNetConnectionStatus(e:TttNetConnectionEvent):void
		{
			switch(e.type)
			{
				case TttNetConnectionEvent.ROOM_CONNECT_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_CONNECT_EVENT) is called in ConductorPanelMediator.as----------");
					//controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=false;
					//controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=false;
					//controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=true;
					//controlContainer.simpleThrobber.start();
					break;
				case TttNetConnectionEvent.ROOM_CONNECTED_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_CONNECTED_EVENT) is called in ConductorPanelMediator.as [participantType="+model.userInfo.participantType+", myObject="+view.myObject+"]");
					attachPublishCamera();
					//controlContainer.simpleThrobber.stop();
					//controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=true;
					//controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=false;
					//controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=false;
					break;
				case TttNetConnectionEvent.ROOM_DISCONNECTED_EVENT:
					trace("INFO: onNetConnectionStatus(ROOM_DISCONNECTED_EVENT) is called in ConductorPanelMediator.as [participantType="+model.userInfo.participantType+", myObject="+view.myObject+"]");
					//controlContainer.simpleThrobber.stop();
					//controlContainer.connectedId.visible=controlContainer.connectedId.includeInLayout=false;
					//controlContainer.disconnectedId.visible=controlContainer.disconnectedId.includeInLayout=true;
					//controlContainer.connectingId.visible=controlContainer.connectingId.includeInLayout=false;
					break;
				default: break;
			}
		}
		public function onNetStreamStatus(e:NetStatusEvent):void
		{
			switch (e.info.code) 
			{
				case "NetStream.Publish.Start":
					trace("INFO: NetStream.Publish.Start in ConductorPanelMediator.as");
					break;
				case "NetStream.Publish.BadName":
					trace("INFO: The stream name is already used in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.Start":
					trace("INFO: NetStream.Play.Start in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.UnpublishNotify": 
					//NetStream(event.currentTarget).close(); <-----Use this Feb 5, 2013
					trace("INFO: NetStream.Play.UnpublishNotify in ConductorPanelMediator.as");
					break;
				case "NetStream.Unpublish.Success": 
					trace("INFO: NetStream.Unpublish.Success in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.Stop":
					trace("INFO: NetStream.Play.Stop in ConductorPanelMediator.as");
					break;	
				case "NetStream.Failed":	trace("INFO: NetStream.Failed in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.Failed": trace("INFO: NetStream.Play.Failed in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.StreamNotFound": // These get routed to the same handler as NetConnection
					trace("INFO: NetStream.Play.StreamNotFound in ConductorPanelMediator.as");
					break;
				
				case "NetStream.Buffer.Full":              		  
					//netStream.bufferTime=AppModel.VOD_EXPANED_BUFFER_TIME;	              		  	
					trace("INFO: NetStream.Buffer.Full in ConductorPanelMediator.as");         		  
					break;
				case "NetStream.Buffer.Empty":			  		  
					//netStream.bufferTime=AppModel.VOD_START_BUFFER_TIME;
					trace("INFO: NetStream.Buffer.Empty in ConductorPanelMediator.as");
					break;
				case "NetStream.Unpause.Notify":
					trace("INFO: NetStream.Unpause.Notify in ConductorPanelMediator.as");
					break;
				case "NetStream.Play.InsufficientBW":
					//netStream.bufferTime=AppModel.VOD_START_BUFFER_TIME;
					trace("INFO: NetStream.Unpause.Notify/NetStream.Play.InsufficientBW in ConductorPanelMediator.as");
					break;
				default :
					trace("INFO: Unknown event code in ConductorPanelMediator.as");
					break;
			}	
		}
		public function onMetaData(info:Object):void 
		{
			trace("INFO metadata: duration=" + info.duration + " width=" + info.width + " height=" + info.height + " framerate=" + info.framerate);
		}
		public function onCuePoint(info:Object):void 
		{
			trace("INFO cuepoint: time=" + info.time + " name=" + info.name + " type=" + info.type);
		}
	}
	
}