package com.sickkids.caliper.view.ttt
{

	import com.sickkids.caliper.events.TttNetCallEvent;
	import com.sickkids.caliper.events.TttNetConnectionEvent;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.service.INetConnectionService;
	
	import flash.display.StageDisplayState;
	import flash.events.ActivityEvent;
	import flash.events.FullScreenEvent;
	import flash.events.MouseEvent;
	import flash.events.NetStatusEvent;
	import flash.events.StatusEvent;
	import flash.media.Camera;
	import flash.media.Microphone;
	import flash.media.MicrophoneEnhancedMode;
	import flash.media.MicrophoneEnhancedOptions;
	import flash.media.Video;
	import flash.net.NetStream;
	import flash.net.Responder;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.managers.SystemManager;
	import mx.utils.StringUtil;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ConductorPanelMediator extends Mediator
	{
		[Inject] public var view:ConductorPanel;
		[Inject] public var model:TttModel;
		[Inject] public var netConnectionService:INetConnectionService;
		private var callServiceResponder:Responder=new Responder(okResult, faultResult);
		private var sm:SystemManager=FlexGlobals.topLevelApplication.systemManager;
		
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
			
			sm.stage.addEventListener (FullScreenEvent.FULL_SCREEN, onFullScreenHandler);
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ConductorPanelMediator.as");
		}
		private function attachPublishCamera():void
		{
			trace("INFO: attachPublishCamera("+model.userInfo.participantType+") is called in ConductorPanelMediator.as");

			if(model.userInfo.participantType=="LECTURER")
			{
				if(view.myObject=="DR")
				{
					setLocalCameraMicrophone();
					//play INTERACTIVE_VIEWER1
					//publish LECTURER
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;					
					//publish Dr.WebCam and Audio
					view.netStream1.attachAudio(model.localMic);
					view.netStream1.attachCamera(model.localCamera);
					view.netStream1.publish(model.userInfo.lecturerId);
					//***********************************************************************************************
					//On the panel title, put the first name of lecturerId, partially showing on the top of the video
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.activeParticipantId1);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId1
					//***********************************************************************************************
				}
				else if(view.myObject=="RN")//instance
				{
					//play INTERACTIVE_VIEWER2
					//play RN
					
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.teachingAssistantId);
					//***********************************************************************************************
					//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video
					//***********************************************************************************************
					
					//subcribe
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.activeParticipantId2);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId2
					//***********************************************************************************************
				}
				
			}
			else if(model.userInfo.participantType=="TEACHING_ASSISTANT")
			{
				if(view.myObject=="DR")
				{
					//small video at the right hand bottom corner
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.lecturerId);
					//***********************************************************************************************
					//On the panel title, put the first name of lecturerId, partially showing on the top of the video
					//***********************************************************************************************
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.activeParticipantId1);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId1
					//***********************************************************************************************
				}
				else if(view.myObject=="RN")
				{
					setLocalCameraMicrophone();
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;					
					//publish Dr.WebCam and Audio
					view.netStream1.attachAudio(model.localMic);
					view.netStream1.attachCamera(model.localCamera);
					view.netStream1.publish(model.userInfo.teachingAssistantId);
					//***********************************************************************************************
					//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.activeParticipantId2);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId2
					//***********************************************************************************************
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER1")
			{
				if(view.myObject=="DR")
				{
					setLocalCameraMicrophone();
					//play LECTURER
					//publish INTERACTIVE_VIEWER1
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;					
					//publish Dr.WebCam and Audio
					view.netStream1.attachAudio(model.localMic);
					view.netStream1.attachCamera(model.localCamera);
					view.netStream1.publish(model.userInfo.activeParticipantId1);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video --
					//***********************************************************************************************
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.lecturerId);
					//***********************************************************************************************
					//On the panel title, put the first name of lecturerId, partially showing on the top of the video --
					//***********************************************************************************************
				}
				else if(view.myObject=="RN")
				{
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.activeParticipantId2);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video --
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.teachingAssistantId);
					//***********************************************************************************************
					//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video --
					//***********************************************************************************************
				}
			}
			else if(model.userInfo.participantType=="INTERACTIVE_VIEWER2")
			{
				if(view.myObject=="DR")
				{
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.activeParticipantId1);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video --
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.lecturerId);
					//***********************************************************************************************
					//On the panel title, put the first name of lecturerId, partially showing on the top of the video --
					//***********************************************************************************************
				}
				else if(view.myObject=="RN")
				{
					setLocalCameraMicrophone();
					//play LECTURER
					//publish INTERACTIVE_VIEWER1
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;					
					//publish Dr.WebCam and Audio
					view.netStream1.attachAudio(model.localMic);
					view.netStream1.attachCamera(model.localCamera);
					view.netStream1.publish(model.userInfo.activeParticipantId2);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video--
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.teachingAssistantId);
					//***********************************************************************************************
					//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video --
					//***********************************************************************************************
				}
			}
			else
			{
				//left side then DR, right side then RN
				if(view.myObject=="DR")
				{
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.activeParticipantId1);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video--
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.lecturerId);
					//***********************************************************************************************
					//On the panel title, put the first name of lecturerId, partially showing on the top of the video--
					//***********************************************************************************************
				}
				else if(view.myObject=="RN")
				{
					view.netStream1=new NetStream(netConnectionService.netConnection());
					view.netStream1.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream1.client=this;
					view.localWebcamDisplay.video=new Video();
					view.localWebcamDisplay.video.attachNetStream(view.netStream1);
					view.netStream1.play(model.userInfo.activeParticipantId2);
					//***********************************************************************************************
					//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video--
					//***********************************************************************************************
					
					//subcribe Active#1. WebCam and Audio
					view.netStream2=new NetStream(netConnectionService.netConnection());
					view.netStream2.addEventListener(NetStatusEvent.NET_STATUS, onNetStreamStatus);
					view.netStream2.client=this;
					view.remoteWebcamDisplay.video=new Video();
					view.remoteWebcamDisplay.video.attachNetStream(view.netStream2);
					view.netStream2.play(model.userInfo.teachingAssistantId);
					//***********************************************************************************************
					//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video--
					//***********************************************************************************************
				}
			}
		}
		private function onCameraStatus(e:StatusEvent):void
		{
			var str:String = "[{0}] code:'{1}', level:'{2}'\n";

			log(StringUtil.substitute(str, e.type, e.code, e.level));
			switch (e.code) 
			{
				case "Camera.Muted":
					log("(Camera.Muted) User denied access to camera in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
				case "Camera.Unmuted":
					log("(Camera.Unmuted) User allowed access to camera in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
				default:
					log("Unkwon status="+e.code+" in onCameraStatus(.) of ConductorPanelMediator.as");
					break;
			}
		}
		private function onCameraActivity(e:ActivityEvent):void
		{
			var str:String = "[{0}] activating:{1}\n";
			var cam:Camera=e.currentTarget as Camera;
			log("Camera name="+cam.name+"\n"+
												"activity level="+cam.activityLevel+"\n"+
												"index="+cam.index+"\n"+
												"BW="+cam.bandwidth+"\n"+
												"FPS="+cam.currentFPS+"\n"+
												StringUtil.substitute(str, e.type, e.activating));
		}
		private function onMicrophoneonStatus(evt:StatusEvent):void 
		{
			var str:String = "[{0}] code:'{1}', level:'{2}'\n";

			log(StringUtil.substitute(str, evt.type, evt.code, evt.level));
			switch (evt.code) 
			{
				case "Microphone.Muted":
					log("(Microphone.Muted) User denied access to camera in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
				case "Microphone.Unmuted":
					log("(Microphone.Unmuted) User allowed access to camera in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
				default:
					log("Unkwon status="+evt.code+" in onMicrophoneonStatus(.) of ConductorPanelMediator.as");
					break;
			}
		}
		private function onMicrophoneonActivity(e:ActivityEvent):void
		{
			//By ActivityLevel, Activating and so on, you can activate recording
			var str:String = "[{0}] activating:{1}\n";
			var mic:Microphone=e.currentTarget as Microphone;
			log("Microphone name="+mic.name+
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
		public function setLocalCameraMicrophone():void
		{
			model.localCamera=Camera.getCamera(); //Camera.names[0]);
			if(model.localCamera)
			{				
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
				log("No webcam is found!");
			}
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
				log("No Microphone is found!");
			}
		}
		public function log(s:String):void
		{
			FlexGlobals.topLevelApplication.log(s);
			//Temporary block April-14-2015
			if(!(model.userInfo.participantType=="LECTURER" || model.userInfo.participantType=="TEACHING_ASSISTANT"))//only non-conductors
				this.dispatch(new TttNetCallEvent(TttNetCallEvent.CALL_TO_METHOD_EVENT, callServiceResponder, "clientLog", model.userInfo.participantType+"|"+s));
		}
		public function okResult(o:Object):void
		{	  
			/****************************************************************************
			 if(o is Boolean) trace("callResult(), return value="+o);//return value=false
			 else trace("callResult(), return="+o);
			 trace("event.type="+event.type);
			 *****************************************************************************/
			trace("INFO: okResult(o:Object) is called in ConductorPanelMediator.as");
			for(var p:String in o) 
			{
				trace("okResult(), "+p + " : " + o[p]);
			}	
		}
		public function faultResult(o:Object):void
		{
			
			trace("INFO: faultResult(o:Object) is called in ConductorPanelMediator.as");
			for(var p:String in o) 
			{
				trace("faultResult(), "+p + " : " + o[p]);
			}	
		}
		private function onFullScreenHandler(e:FullScreenEvent):void
		{
			if(e.fullScreen) //Do something specific here if swiched to the full screen
			{
				if(model.userInfo.participantType=="LECTURER")
				{
					if(view.myObject=="DR")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of lecturerId, partially showing on the top of the video
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId1
						//***********************************************************************************************
					}
					else if(view.myObject=="RN")//instance
					{
						//***********************************************************************************************
						//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId2
						//***********************************************************************************************
					}
					
				}
				else if(model.userInfo.participantType=="TEACHING_ASSISTANT")
				{
					if(view.myObject=="DR")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of lecturerId, partially showing on the top of the video
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId1
						//***********************************************************************************************
					}
					else if(view.myObject=="RN")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId2
						//***********************************************************************************************
					}
				}
				else if(model.userInfo.participantType=="INTERACTIVE_VIEWER1")
				{
					if(view.myObject=="DR")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video --
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of lecturerId, partially showing on the top of the video --
						//***********************************************************************************************
					}
					else if(view.myObject=="RN")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video --
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video --
						//***********************************************************************************************
					}
				}
				else if(model.userInfo.participantType=="INTERACTIVE_VIEWER2")
				{
					if(view.myObject=="DR")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video --
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of lecturerId, partially showing on the top of the video --
						//***********************************************************************************************
					}
					else if(view.myObject=="RN")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video--
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video --
						//***********************************************************************************************
					}
				}
				else
				{
					//left side then DR, right side then RN
					if(view.myObject=="DR")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId1, partially showing on the top of the video--
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of lecturerId, partially showing on the top of the video--
						//***********************************************************************************************
					}
					else if(view.myObject=="RN")
					{
						//***********************************************************************************************
						//On the panel title, put the first name of activeParticipantId2, partially showing on the top of the video--
						//***********************************************************************************************
						//***********************************************************************************************
						//On the panel title, put the first name of teachingAssistantId, partially showing on the top of the video--
						//***********************************************************************************************
					}
				}
			}
			else //Do something specific here if swiched to the normal screen
			{
				//this.dispatchEvent(e);
			}
		}
	}
	
}