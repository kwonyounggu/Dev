package com.sickkids.caliper.view.cap
{
	import com.sickkids.caliper.components.AppCommon;
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.model.CapModel;
	import com.sickkids.caliper.view.navigator.CapViewNavigator;
	import com.sickkids.caliper.vo.AllPointDescBean;
	import com.sickkids.caliper.vo.CarmClinicalSummaryBean;
	import com.sickkids.caliper.vo.CarmDeMXBeanM;
	import com.sickkids.caliper.vo.CarmO_DE2Bean;
	import com.sickkids.caliper.vo.CarmO_DE4Bean;
	import com.sickkids.caliper.vo.CarmO_DE5Bean;
	import com.sickkids.caliper.vo.CarmO_DE6Bean;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	
	import spark.components.Label;
	import spark.components.Panel;
	import spark.events.PopUpEvent;
	import spark.events.ViewNavigatorEvent;
	
	import flashx.textLayout.conversion.TextConverter;
	
	import org.robotlegs.mvcs.Mediator;
	
	public class ReviewViewMediator extends Mediator
	{
		[Inject] public var view:ReviewView;
		[Inject] public var capModel:CapModel;
		
		public function ReviewViewMediator()
		{
			super();
		}
		override public function onRegister():void
		{
			trace("INFO: onRegister() is called in ReviewViewMediator.as");
			
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
		}
		override public function onRemove():void
		{
			trace("INFO: onRemove() is called in ReviewViewMediator.as");
			
			capModel.resetReviewViewData();
			
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
			view.forumPanel.controlBar.removeEventListener(MouseEvent.CLICK, onControlBar_clickHandler);
		}
		private function displayErrorMessage(ui:Object, msg:String):void
		{
			ui.alertContainerBorderContainer.visible=ui.alertContainerBorderContainer.includeInLayout=true;
			ui.alertMessage.textFlow=TextConverter.importToFlow("<p>"+msg+"</p>", TextConverter.TEXT_FIELD_HTML_FORMAT);
		}
		private function onRemoteObjectFault(e:ApplicationRMIEvent):void
		{
			trace("INFO: onRemoteObjectFault() is called in ReviewViewMediator.as");	
			var errorMsg:String=e.arg3 as String;
			if(AppCommon.contains(errorMsg, "session_timeout"))
				CapViewNavigator(this.view.navigator).onLogoutClick(null);
			else 
				displayErrorMessage(this.view, errorMsg);//error fault string
		}
		protected function onItem_clickHandler(event:MouseEvent):void
		{
			// TODO Auto-generated method stub
			view.itemCallout.itemList=capModel.itemList;
			view.itemCallout.open(view.itemLabel as Label, true);
			
		}
		protected function onCallout_openHandler(e:PopUpEvent):void
		{
			//var o:Label=e.currentTarget.owner as Label;
			trace("INFO: onCallout_openHandler(e:PopUpEvent) is called in ReviewViewMediator.mxml");
		}
		protected function onCallout_closeHandler(e:PopUpEvent):void
		{
			trace("INFO: onCallout_closeHandler() is called ..."+e.commit+", "+e.data+" in ReviewViewMediator.mxml");			
			if(e.commit)
			{
				var o:Label=e.currentTarget.owner as Label;
				//e.data <- spinnerList.selectedItem
				o.text=e.data.carmId;				
				view.itemNumber=e.data.carmId as Number;
				capModel.selectedItemBean=e.data as CarmDeMXBeanM;
				
				resetAccordion();
			}
		}
		protected function resetAccordion():void
		{
			view.summaryPanel.currentState=
			view.diagnosesPanel.currentState=	
			view.problemsPanel.currentState=
			view.laborotoryPanel.currentState=
			view.vitalSignPanel.currentState=
			view.medicationPanel.currentState=
			view.radiologyPanel.currentState=
			view.reviewPanel.currentState=
			view.forumPanel.currentState="downIcon"; 			
		}
		protected function visibleOtherThan(p:Panel, b:Boolean):void
		{
			if(p is LaborotoryPanel)
			{
				view.helpContainer.visible=view.helpContainer.includeInLayout=
				view.summaryPanel.visible=view.summaryPanel.includeInLayout=
				view.diagnosesPanel.visible=view.diagnosesPanel.includeInLayout=	
				view.problemsPanel.visible=view.problemsPanel.includeInLayout=
				view.vitalSignPanel.visible=view.vitalSignPanel.includeInLayout=
				view.medicationPanel.visible=view.medicationPanel.includeInLayout=
				view.radiologyPanel.visible=view.radiologyPanel.includeInLayout=
				view.reviewPanel.visible=view.reviewPanel.includeInLayout=
				view.forumPanel.visible=view.forumPanel.includeInLayout=b;
			}
			else if(p is VitalSignPanel)
			{
				view.helpContainer.visible=view.helpContainer.includeInLayout=
				view.summaryPanel.visible=view.summaryPanel.includeInLayout=
					view.diagnosesPanel.visible=view.diagnosesPanel.includeInLayout=	
					view.problemsPanel.visible=view.problemsPanel.includeInLayout=
					view.laborotoryPanel.visible=view.laborotoryPanel.includeInLayout=
					view.medicationPanel.visible=view.medicationPanel.includeInLayout=
					view.radiologyPanel.visible=view.radiologyPanel.includeInLayout=
					view.reviewPanel.visible=view.reviewPanel.includeInLayout=
					view.forumPanel.visible=view.forumPanel.includeInLayout=b;
			}
		}
		protected function onDownIcon_clickHandler(event:MouseEvent):void
		{
			trace("INFO: onDownIcon_clickHandler(event:MouseEvent) is called  in ReviewViewMediator.mxml");
			// TODO Auto-generated method stub
			if(view.itemNumber>0)
			{
				view.alertContainerBorderContainer.visible=view.alertContainerBorderContainer.includeInLayout=false;
				switch(event.currentTarget)
				{
					case view.summaryPanel.controlBar:
						 view.summaryPanel.alertContainerBorderContainer.visible=view.summaryPanel.alertContainerBorderContainer.includeInLayout=false;
						 dispatch(new CapRMIEvent(CapRMIEvent.GET_CLINICAL_SUMMARY_EVENT, capModel.crbm, view.itemLabel.text));
						 break; 
					case view.diagnosesPanel.controlBar: 
						view.diagnosesPanel.alertContainerBorderContainer.visible=view.diagnosesPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_DIAGNOSES_EVENT, capModel.crbm, String(capModel.selectedItemBean.de2Id)));
						break;
					case view.problemsPanel.controlBar: 
						view.problemsPanel.alertContainerBorderContainer.visible=view.problemsPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_PROBLEMS_EVENT, capModel.crbm, String(capModel.selectedItemBean.de4Id)));
						break;
					case view.laborotoryPanel.controlBar: 
						view.laborotoryPanel.alertContainerBorderContainer.visible=view.laborotoryPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_LABOROTORY_EVENT, capModel.crbm, capModel.selectedItemBean));
						break;
					//case view.vitalSignPanel.downIcon: 
					case view.vitalSignPanel.controlBar: 
						view.vitalSignPanel.alertContainerBorderContainer.visible=view.vitalSignPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_VITALSIGN_EVENT, capModel.crbm, capModel.selectedItemBean));
						break;
					//case view.medicationPanel.downIcon: 
					case view.medicationPanel.controlBar: 
						view.medicationPanel.alertContainerBorderContainer.visible=view.medicationPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_MEDICATION_EVENT, capModel.crbm, capModel.selectedItemBean));
						break;
					//case view.radiologyPanel.downIcon: 
					case view.radiologyPanel.controlBar: 
						view.radiologyPanel.alertContainerBorderContainer.visible=view.radiologyPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_RADIOLOGY_EVENT, capModel.crbm, capModel.selectedItemBean));
						break;
					//case view.reviewPanel.downIcon: 
					case view.reviewPanel.controlBar: 
						view.reviewPanel.alertContainerBorderContainer.visible=view.reviewPanel.alertContainerBorderContainer.includeInLayout=false;
						dispatch(new CapRMIEvent(CapRMIEvent.GET_QPOINTS_EVENT, capModel.crbm, capModel.selectedItemBean));
						break;
					case view.forumPanel.downIcon: 
					case view.forumPanel.controlBar: 
						view.forumPanel.alertContainerBorderContainer.visible=view.forumPanel.alertContainerBorderContainer.includeInLayout=false;
						//view.forumPanel.currentState="upIcon"; 
						//displayErrorMessage(view.forumPanel, "display error message 1...");
						break;
					default:
						trace("INFO: onDownIcon_clickHandler(Unknown event) is called  in ReviewViewMediator.mxml");
						break;
				}
			}
			
		}
		protected function onUpIcon_clickHandler(event:MouseEvent):void
		{
			// TODO Auto-generated method stub
			if(view.itemNumber>0)
				switch(event.currentTarget)
				{
					//case view.summaryPanel.upIcon: 
					case view.summaryPanel.controlBar: 
						 view.summaryPanel.currentState="downIcon"; 					
						break;
					//case view.diagnosesPanel.upIcon: 
					case view.diagnosesPanel.controlBar:
						 view.diagnosesPanel.currentState="downIcon"; 						
						break;
					//case view.problemsPanel.upIcon: 
					case view.problemsPanel.controlBar:
						 view.problemsPanel.currentState="downIcon"; 
						break;
					//case view.laborotoryPanel.upIcon: 
					case view.laborotoryPanel.controlBar: 
						 view.laborotoryPanel.currentState="downIcon"; 
						break;
					//case view.vitalSignPanel.upIcon: 
					case view.vitalSignPanel.controlBar: 
						 view.vitalSignPanel.currentState="downIcon"; 
						 break;
					//case view.medicationPanel.upIcon: 
					case view.medicationPanel.controlBar: 
						 view.medicationPanel.currentState="downIcon"; 
						 break;
					//case view.radiologyPanel.upIcon: 
					case view.radiologyPanel.controlBar: 
						 view.radiologyPanel.currentState="downIcon"; 
						 break;
					//case view.reviewPanel.upIcon: 
					case view.reviewPanel.controlBar: 
						 view.reviewPanel.currentState="downIcon"; 
						 break;
					case view.forumPanel.upIcon: 
					case view.forumPanel.controlBar: 
						 view.forumPanel.currentState="downIcon"; 
						 break;
					default: 
						trace("INFO: onUpIcon_clickHandler(Unknown event) is called  in ReviewViewMediator.mxml");
						break;
				}
			trace("INFO: onUpIcon_clickHandler(event:MouseEvent) is called  in ReviewViewMediator.mxml");
		}
		protected function onControlBar_clickHandler(event:MouseEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: onControlBar_clickHandler(event:MouseEvent) is called  in ReviewViewMediator.mxml");
			if(view.itemNumber>0)
				switch(event.currentTarget)
				{
					case view.summaryPanel.controlBar: 
							if(view.summaryPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.diagnosesPanel.controlBar: 
							if(view.diagnosesPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.problemsPanel.controlBar: 
							if(view.problemsPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.laborotoryPanel.controlBar: 
							if(view.laborotoryPanel.currentState=="upIcon") 
							{
								//back to the original views
								view.laborotoryPanel.width=view.problemsPanel.width;
								view.navigator.actionBar.visible=view.navigator.actionBar.includeInLayout=true;
								view.laborotoryPanel.screenRotation.angleBy=90;
								view.laborotoryPanel.screenRotation.play();
								visibleOtherThan(view.laborotoryPanel, true);
								view.validateNow();
								onUpIcon_clickHandler(event);
							}
							else onDownIcon_clickHandler(event);
						break;
					case view.vitalSignPanel.controlBar: 
							if(view.vitalSignPanel.currentState=="upIcon")
							{
								//back to the original views
								view.vitalSignPanel.width=view.problemsPanel.width;
								view.navigator.actionBar.visible=view.navigator.actionBar.includeInLayout=true;
								view.vitalSignPanel.screenRotation.angleBy=90;
								view.vitalSignPanel.screenRotation.play();
								visibleOtherThan(view.vitalSignPanel, true);
								view.validateNow();
								onUpIcon_clickHandler(event);
							}
							else onDownIcon_clickHandler(event);				
						break;
					case view.medicationPanel.controlBar: 
							if(view.medicationPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.radiologyPanel.controlBar: 
							if(view.radiologyPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.reviewPanel.controlBar: 
							if(view.reviewPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					case view.forumPanel.controlBar: 
							if(view.forumPanel.currentState=="upIcon") onUpIcon_clickHandler(event);
							else onDownIcon_clickHandler(event);
						break;
					default: 
						trace("INFO: onControlBar_clickHandler(Unknown event) is called  in ReviewViewMediator.mxml");
						break;
				}		
		}
		private function onGotReviewData(e:CapRMIEvent):void
		{
			switch(e.type)
			{
				case CapRMIEvent.GOT_CLINICAL_SUMMARY_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_CLINICAL_SUMMARY_EVENT+" in ReviewViewMediator.mxml");
					view.summaryPanel.currentState="upIcon";
					var cb:CarmClinicalSummaryBean=e.arg1 as CarmClinicalSummaryBean;
					view.summaryPanel.summaryText.textFlow=TextConverter.importToFlow("<p>"+(cb.edited? cb.new_clinical_summary : cb.old_clinical_summary)+"</p>", TextConverter.TEXT_FIELD_HTML_FORMAT);
					break;
				case CapRMIEvent.GOT_DIAGNOSES_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_DIAGNOSES_EVENT+" in ReviewViewMediator.mxml");
					view.diagnosesPanel.currentState="upIcon";
					var de2b:CarmO_DE2Bean=e.arg1 as CarmO_DE2Bean;
					trace("INFO: ["+de2b.displayDiagnoses()+"] in ReviewViewMediator.mxml");
					view.diagnosesPanel.summaryText.textFlow=TextConverter.importToFlow(de2b.displayDiagnoses(), TextConverter.TEXT_FIELD_HTML_FORMAT);				
					break;
				case CapRMIEvent.GOT_PROBLEMS_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_PROBLEMS_EVENT+" in ReviewViewMediator.mxml");
					view.problemsPanel.currentState="upIcon";
					var de4b:CarmO_DE4Bean=e.arg1 as CarmO_DE4Bean;
					trace("INFO: ["+de4b.displayProblems()+"] in ReviewViewMediator.mxml");
					view.problemsPanel.summaryText.textFlow=TextConverter.importToFlow(de4b.displayProblems(), TextConverter.TEXT_FIELD_HTML_FORMAT);				
					break;
				case CapRMIEvent.GOT_LABOROTORY_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_LABOROTORY_EVENT+" in ReviewViewMediator.mxml");
					view.laborotoryPanel.currentState="upIcon";
					var de5bList:ArrayCollection=e.arg1 as ArrayCollection;
					trace("INFO: ["+de5bList.length+"] in ReviewViewMediator.mxml");
					view.laborotoryPanel.lab48Bean=de5bList.getItemAt(0) as CarmO_DE5Bean;
					view.laborotoryPanel.lab24Bean=de5bList.getItemAt(1) as CarmO_DE5Bean;
					view.laborotoryPanel.lab12Bean=de5bList.getItemAt(2) as CarmO_DE5Bean;
					view.laborotoryPanel.lab1dBean=de5bList.getItemAt(3) as CarmO_DE5Bean;
					view.laborotoryPanel.labLowerBean=de5bList.getItemAt(4) as CarmO_DE5Bean;
					view.laborotoryPanel.labUpperBean=de5bList.getItemAt(5) as CarmO_DE5Bean;
					
					//make all others invisible
					visibleOtherThan(view.laborotoryPanel, false);
					view.laborotoryPanel.width=view.navigator.height;
					view.navigator.actionBar.visible=view.navigator.actionBar.includeInLayout=false;
					view.laborotoryPanel.screenRotation.angleBy=-90;
					view.laborotoryPanel.screenRotation.play();
					//Validate and update the properties and layout of this object and redraw it, if necessary.
					view.validateNow();
					break;
				case CapRMIEvent.GOT_VITALSIGN_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_VITALSIGN_EVENT+" in ReviewViewMediator.mxml");
					view.vitalSignPanel.currentState="upIcon";
					capModel.vatalSignList=e.arg1 as ArrayCollection;
					view.vitalSignPanel.vitalSignDg.dataProvider=capModel.vatalSignList;
					view.vitalSignPanel.vitalSignDg.requestedRowCount=(capModel.vatalSignList==null ? 0 :capModel.vatalSignList.length);
					//make all others invisible
					visibleOtherThan(view.vitalSignPanel, false);
					view.vitalSignPanel.width=view.navigator.height;
					view.navigator.actionBar.visible=view.navigator.actionBar.includeInLayout=false;
					view.vitalSignPanel.screenRotation.angleBy=-90;
					view.vitalSignPanel.screenRotation.play();
					//Validate and update the properties and layout of this object and redraw it, if necessary.
					view.validateNow();
					
					break;
				case CapRMIEvent.GOT_MEDICATION_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_MEDICATION_EVENT+" in ReviewViewMediator.mxml");
					view.medicationPanel.currentState="upIcon";
					capModel.medicationList=e.arg1 as ArrayCollection;
					view.medicationPanel.medicationDg.dataProvider=capModel.medicationList;
					view.medicationPanel.medicationDg.requestedRowCount=(capModel.medicationList==null ? 0 :capModel.medicationList.length);
					break;
				case CapRMIEvent.GOT_RADIOLOGY_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_RADIOLOGY_EVENT+" in ReviewViewMediator.mxml");
					view.radiologyPanel.currentState="upIcon";
					capModel.radiologyRecord=e.arg1 as CarmO_DE6Bean;
					
					view.radiologyPanel.chestImageText.cellText=capModel.radiologyRecord.chestImaging;
					view.radiologyPanel.cnsImageText.cellText=capModel.radiologyRecord.chestImaging;
					view.radiologyPanel.abdominalImageText.cellText=capModel.radiologyRecord.abdominalImaging;
					view.radiologyPanel.otherImageText.cellText=capModel.radiologyRecord.otherImaging;
					break;
				case CapRMIEvent.GOT_QPOINTS_EVENT: 
					trace("INFO: got "+CapRMIEvent.GOT_QPOINTS_EVENT+" in ReviewViewMediator.mxml");
					view.reviewPanel.currentState="upIcon";
					capModel.qPointsList=e.arg1 as ArrayCollection;
					view.reviewPanel.q1.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(0)).pointExplanation;
					view.reviewPanel.q2.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(1)).pointExplanation;
					view.reviewPanel.q3.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(2)).pointExplanation;
					view.reviewPanel.q4.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(3)).pointExplanation;
					view.reviewPanel.q5.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(4)).pointExplanation;
					view.reviewPanel.q6.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(5)).pointExplanation;
					view.reviewPanel.q7.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(6)).pointExplanation;
					view.reviewPanel.q8.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(7)).pointExplanation;
					view.reviewPanel.q9.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(8)).pointExplanation;
					view.reviewPanel.q10.cellText=AllPointDescBean(capModel.qPointsList.getItemAt(9)).pointExplanation;
					
					break;
				default: break;
			}
		}
		protected function viewActivateHandler(event:ViewNavigatorEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: viewActivateHandler(...) is called in ReviewViewMediator.as");
			view.tabBarVisible=false;
			if(capModel.itemList==null)
				dispatch(new CapRMIEvent(CapRMIEvent.GET_ITEM_LIST_EVENT, capModel.crbm, "ALL"));
			else
				trace("INFO: capModel.itemList is not null with length="+capModel.itemList.length+" from the shared object.");

		}
		
		protected function viewDeactivateHandler(event:ViewNavigatorEvent):void
		{
			// TODO Auto-generated method stub
			trace("INFO: viewDeactivateHandler(...) is called in ReviewViewMediator.as");
		}
		private function onGotItemList(e:CapRMIEvent):void
		{			
			capModel.itemList=e.arg1 as ArrayCollection;
			trace("INFO: onGotItemList() is called and "+(capModel.itemList==null)+", "+((capModel.itemList==null) ? "NULL":capModel.itemList.length)+" in LoginViewMediator.as");
			
			if(capModel.itemList==null || capModel.itemList.length==0) displayErrorMessage(this.view, "You do not have any item to manipulate. Please contact the project manager then try again!");
			else AppCommon.cap_setSharedItemListObject(capModel.itemList);
		}
		/*protected function onHTTPService_resultHandler(event:ResultEvent):void
		{
			// TODO Auto-generated method stub
			var rawData:String = String(event.result);
			//AlertMsg.show("RawData is: "+rawData);
			trace("INFO: rawData="+rawData+" in onHTTPService_resultHandler()");
			CursorManager.removeBusyCursor();
		} 
		protected function onHTTPService_faultHandler(event:FaultEvent):void
		{
			// TODO Auto-generated method stub
			var rawData:String = String(event.fault);
			//AlertMsg.show("RawData is: "+rawData);
			trace("INFO: rawData="+rawData+" in onHTTPService_faultHandler()");
			CursorManager.removeBusyCursor();
		} 
		*/
	}
}