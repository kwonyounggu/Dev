package com.sickkids.caliper.view.ttt
{
	import com.sickkids.caliper.model.CaliperCustomReferenceIntervalModel;
	import com.sickkids.caliper.model.TttModel;
	import com.sickkids.caliper.view.CustomReferenceIntervalsView;
	
	import flash.media.Camera;
	
	import mx.events.FlexEvent;
	
	import spark.components.mediaClasses.DynamicStreamingVideoItem;
	import spark.components.mediaClasses.DynamicStreamingVideoSource;
	
	import org.osmf.net.StreamType;
	import org.robotlegs.mvcs.Mediator;
	
	public class ConductorPanelViewMediator extends Mediator
	{
		[Inject] public var view:ConductorPanel;
		[Inject] public var model:TttModel;
		
		public function ConductorPanelViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ConductorPanelViewMediator.as");
			
			view.localVideoDisplay.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationCompleteOfVideoDisplay);

			/*
			view.addEventListener(spark.events.ViewNavigatorEvent.VIEW_ACTIVATE, viewActivateHandler);
			view.addEventListener(spark.events.ViewNavigatorEvent.VIEW_DEACTIVATE, viewDeactivateHandler);
			//view.submitButton.addEventListener(MouseEvent.CLICK, onSubmitButton_clickHandler);
			this.addContextListener(CapRMIEvent.GOT_CLINICAL_SUMMARY_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_DIAGNOSES_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_PROBLEMS_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_LABOROTORY_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_VITALSIGN_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_MEDICATION_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_RADIOLOGY_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_QPOINTS_EVENT, onGotReviewData, CapRMIEvent);
			this.addContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			this.addContextListener(CapRMIEvent.GOT_ITEM_LIST_EVENT, onGotItemList, CapRMIEvent);
			
			view.itemImage.addEventListener(MouseEvent.CLICK, onItem_clickHandler);
			view.itemLabel.addEventListener(MouseEvent.CLICK, onItem_clickHandler);
			view.itemCallout.addEventListener(PopUpEvent.OPEN, onCallout_openHandler);
			view.itemCallout.addEventListener(PopUpEvent.CLOSE, onCallout_closeHandler);
			
			view.summaryPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.diagnosesPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.problemsPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.laborotoryPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.vitalSignPanel.downIcon.addEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.vitalSignPanel.upIcon.addEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.vitalSignPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.medicationPanel.downIcon.addEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.medicationPanel.upIcon.addEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.medicationPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.radiologyPanel.downIcon.addEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.radiologyPanel.upIcon.addEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.radiologyPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.reviewPanel.downIcon.addEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.reviewPanel.upIcon.addEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.reviewPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.forumPanel.downIcon.addEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			view.forumPanel.upIcon.addEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.forumPanel.controlBar.addEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.reviewHttpService.addEventListener(ResultEvent.RESULT, onHTTPService_resultHandler);
			//view.reviewHttpService.addEventListener(FaultEvent.FAULT, onHTTPService_faultHandler);
			*/
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ConductorPanelViewMediator.as");
			
			/*capModel.resetReviewViewData();
			
			view.removeEventListener(spark.events.ViewNavigatorEvent.VIEW_ACTIVATE, viewActivateHandler);
			view.removeEventListener(spark.events.ViewNavigatorEvent.VIEW_DEACTIVATE, viewDeactivateHandler);
			
			this.removeContextListener(CapRMIEvent.GOT_CLINICAL_SUMMARY_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_DIAGNOSES_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_PROBLEMS_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_LABOROTORY_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_VITALSIGN_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_MEDICATION_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_RADIOLOGY_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_QPOINTS_EVENT, onGotReviewData, CapRMIEvent);
			this.removeContextListener(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, onRemoteObjectFault, ApplicationRMIEvent);
			this.removeContextListener(CapRMIEvent.GOT_ITEM_LIST_EVENT, onGotItemList, CapRMIEvent);
			
			view.itemImage.removeEventListener(MouseEvent.CLICK, onItem_clickHandler);
			view.itemCallout.removeEventListener(PopUpEvent.OPEN, onCallout_openHandler);
			view.itemCallout.removeEventListener(PopUpEvent.CLOSE, onCallout_closeHandler);
			
			
			view.summaryPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.diagnosesPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.problemsPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.laborotoryPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.vitalSignPanel.downIcon.removeEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.vitalSignPanel.upIcon.removeEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.vitalSignPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.medicationPanel.downIcon.removeEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.medicationPanel.upIcon.removeEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.medicationPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.radiologyPanel.downIcon.removeEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.radiologyPanel.upIcon.removeEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.radiologyPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			//view.reviewPanel.downIcon.removeEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			//view.reviewPanel.upIcon.removeEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.reviewPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
			
			view.forumPanel.downIcon.removeEventListener(MouseEvent.CLICK, onDownIcon_clickHandler);
			view.forumPanel.upIcon.removeEventListener(MouseEvent.CLICK, onUpIcon_clickHandler);
			view.forumPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);*/
		}
		private function onCreationCompleteOfVideoDisplay(event:FlexEvent):void
		{
			var _cam:DynamicStreamingVideoSource =  new DynamicStreamingVideoSource();  
			
			
			var videoItems:Vector.<DynamicStreamingVideoItem>=new Vector.<DynamicStreamingVideoItem>();  
			videoItems[0]=new DynamicStreamingVideoItem(); 
			
			var dynVideoSource:DynamicStreamingVideoSource=new DynamicStreamingVideoSource();  
			
			dynVideoSource.host= "";  
			dynVideoSource.streamType=StreamType.LIVE;  
			dynVideoSource.streamItems=videoItems;  
			
			view.localVideoDisplay.source=dynVideoSource;
			model.localCamera=Camera.getCamera(); //Camera.names[0]); 
			view.localVideoDisplay.videoObject.attachCamera(model.localCamera);
			/*
			myVideo.source=_dynVideoSource;  
			
			if(myVideo.videoObject)
			{  
			Alert.show("Got Video Object: " + myVideo.videoObject, "Notification");  
			}  
			else 
			{  
			Alert.show("Unable To Get Video Object" + myVideo.videoObject, "Notification");  
			}  
			
			var cam:Camera = Camera.getCamera(); //Camera.names[0]);  
			cam.setMode(640, 480, 15);  
			cam.setQuality(0, 80);  
			myVideo.videoObject.attachCamera(cam);  
			*/
		}
	}
}