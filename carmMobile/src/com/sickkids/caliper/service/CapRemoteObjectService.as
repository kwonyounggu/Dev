package com.sickkids.caliper.service
{
	import com.sickkids.caliper.components.AppCommon;
	import com.sickkids.caliper.events.ApplicationRMIEvent;
	import com.sickkids.caliper.events.CapRMIEvent;
	import com.sickkids.caliper.model.CapModel;
	import com.sickkids.caliper.vo.AllRegisteredUserBean;
	import com.sickkids.caliper.vo.CarmClinicalSummaryBean;
	import com.sickkids.caliper.vo.CarmO_DE2Bean;
	import com.sickkids.caliper.vo.CarmO_DE4Bean;
	import com.sickkids.caliper.vo.CarmO_DE6Bean;
	
	import mx.collections.ArrayCollection;
	import mx.core.FlexGlobals;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import org.robotlegs.mvcs.Actor;
	
	public class CapRemoteObjectService extends Actor implements ICapRemoteObjectService
	{
		private var _remoteObject:RemoteObject;
		[Inject] public var capModel:CapModel;
		
		public function CapRemoteObjectService(dest:String="carmProxy", endpoint:String="{FlexGlobals.topLevelApplication._host}/carm/messagebroker/amf")
		{
			trace("INFO: CapRemoteObjectService() is called in CapRemoteObjectService.as, endpoint="+endpoint);
			_remoteObject=new RemoteObject();
			_remoteObject.concurrency="last";
			_remoteObject.showBusyCursor=true;
			_remoteObject.destination=dest;
			//_remoteObject.endpoint=endpoint;
			//_remoteObject.endpoint="http://172.16.17.133:8080/carm/messagebroker/amf";
			//_remoteObject.endpoint="http://localhost:8080/carm/messagebroker/amf";
			_remoteObject.endpoint="http://www.centerforsafetyresearch.com/carm/messagebroker/amf";
			super();
		}
		//Here, operation is a method defined by the given argument called method.
		//public function callService(method:String, arg1:Object=null, arg2:Object=null, arg3:Object=null, arg4:Object=null, arg5:Object=null, arg6:Object=null) : void 
		public function callService(... args) : void 
		{   
			var operation:AbstractOperation=_remoteObject.getOperation(args.shift() as String);//method name for the 1st argument
			if(!operation.hasEventListener(ResultEvent.RESULT)) 
			{
				operation.addEventListener(ResultEvent.RESULT, onOperationResult);
			}
			if(!operation.hasEventListener(FaultEvent.FAULT)) 
			{
				operation.addEventListener(FaultEvent.FAULT, onOperationFault);
			}
			//Cancel if there exists the same operation on working,			
			operation.cancel();
			operation.arguments = args;//all the arguemnts are assigned to it

			operation.send();			
		}
		public function onOperationResult(e:ResultEvent):void
		{		
			e.target.removeEventListener(ResultEvent.RESULT, onOperationResult);
			
			var op:String=e.token.message["operation"];
			trace("INFO: onOperationResult() is called in CapRemoteObjectService.as, op="+op+", e.result="+e.result);
			if(e.token.message.destination=="carmProxy")
			{	
				switch(op)
				{
					case "checkSignIn": 
							dispatch(new CapRMIEvent(CapRMIEvent.CHECKED_SIGNIN_EVENT, e.result as AllRegisteredUserBean));
							break;
					case "savePwd": 
							dispatch(new CapRMIEvent(CapRMIEvent.SAVED_PWD_EVENT, e.result as Boolean));
							break;
					case "sendLoginInfo": 
							dispatch(new CapRMIEvent(CapRMIEvent.SENT_LOGIN_INFO_EVENT, e.result as Boolean));
							break;
					case "getItemList": 
							dispatch(new CapRMIEvent(CapRMIEvent.GOT_ITEM_LIST_EVENT, e.result as ArrayCollection));
							break;
					case "getClinicalSummary": 
							dispatch(new CapRMIEvent(CapRMIEvent.GOT_CLINICAL_SUMMARY_EVENT, e.result as CarmClinicalSummaryBean));
							break;
					case "getDiagnoses": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_DIAGNOSES_EVENT, e.result as CarmO_DE2Bean));
						break;
					case "getProblems": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_PROBLEMS_EVENT, e.result as CarmO_DE4Bean));
						break;
					case "getLaborotory": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_LABOROTORY_EVENT, e.result as ArrayCollection));
						break;
					case "getVitalSign": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_VITALSIGN_EVENT, e.result as ArrayCollection));
						break;
					case "getMedications": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_MEDICATION_EVENT, e.result as ArrayCollection));
						break;
					case "getRadiologyRecord": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_RADIOLOGY_EVENT, e.result as CarmO_DE6Bean));
						break;
					case "getAllPointDescList": 
						dispatch(new CapRMIEvent(CapRMIEvent.GOT_QPOINTS_EVENT, e.result as ArrayCollection));
						break;
					
					/*
					case "getTestString":
						break;
					case "getInstrumentNameList":
							//Clean up if there exists a list of data.
							if(caliperModel.instrumentNameList!=null) caliperModel.instrumentNameList.removeAll();
							caliperModel.instrumentNameList=e.result as ArrayCollection;
							dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_INSTRUMENT_NAME_LIST));
							break;
					case "getAnalyteList":
						//Clean up if there exists a list of data.
						if(caliperModel.analyteNameList!=null) caliperModel.analyteNameList.removeAll();
						caliperModel.analyteNameList=e.result as ArrayCollection;
						dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_ANALYTE_NAME_LIST));
						break;
					case "getRawDataList":
						//Clean up if there exists a list of data.
						if(caliperModel.rawDataList!=null) caliperModel.rawDataList.removeAll();
						caliperModel.rawDataList=e.result as ArrayCollection;
						dispatch(new RawDataRMIEvent(RawDataRMIEvent.GOT_RAW_DATA_LIST));
						break;
					*/
					default:
						trace("INFO: unknwon operation, "+op+", is back.");
						break;
				}
				/*
				switch(op)
				{
					case "getOPENQuestionList":	
						if(model.questionList!=null) model.questionList.removeAll();
						model.questionList=e.result as ArrayCollection; 						
						trace("INFO: records received from getOPENQuestionList(): "+model.questionList.length);
						break;
					case "insertAQuestionForESC":	
						if(model.questionList==null) model.questionList=new ArrayCollection();
						model.questionList.addItemAt(e.result as EpochEscQuestionBean, 0); 	
						Alert.show("The question is successfully registered.","Information");
						model.createSuccess=!model.createSuccess;					
						trace("INFO: a record received from insertAQuestionForESC() with question_id=: "+EpochEscQuestionBean(e.result).question_id);
						break;
					case "updateAQuestionForESC":	
						if(Utils.replaceAnObject(model.questionList, rmiEvent.arg1 as EpochEscQuestionBean))
							Alert.show("The question is successfully updated ("+e.result+").","Information");	
						else
							Alert.show("The question is successfully updated ("+e.result+") but its values are not updated into the data grid.","Warning");				
						trace("INFO: The record is successfully updated through updateAQuestionForESC() with rows=: "+e.result);
						break;
					case "voteResultForESC":	
						if(model.voteResultList!=null) model.voteResultList.removeAll();
						model.voteResultList=e.result as ArrayCollection; 						
						trace("INFO: records received from voteResultForESC(): "+model.voteResultList.length);
						break;
					case "myVoteDoneListForESC":	
						if(model.myVoteDoneList!=null) model.myVoteDoneList.removeAll();
						model.myVoteDoneList=e.result as ArrayCollection; 				
						trace("INFO: records received from myVoteDoneListForESC(): "+model.myVoteDoneList.length);
						break;
					case "updateInsertVoteForESC":	
						var qb:EpochEscQuestionBean=e.result as EpochEscQuestionBean;
						qb.myvotedone=true;
						qb.myvotecategory=EpochEscVoteBean(rmiEvent.arg1).vote_category;
						qb.myvotetime=new Date();
						qb.mycomment=EpochEscVoteBean(rmiEvent.arg1).remarks;
						if(!Utils.replaceAnObject(model.questionList, qb))
							Alert.show("The question is successfully updated ("+e.result+") but its values are not updated into the data grid.","Warning");	
						model.voteSuccess=!model.voteSuccess;//to trigger an event				
						trace("INFO: an object received from updateInsertVoteForESC(): "+e.result as EpochEscQuestionBean);
						break;
					case "inactivateAQuestionForESC":	
						if(Utils.deleteAnObject(model.questionList, rmiEvent.arg1 as int))
						{							
							Alert.show("The question is successfully deleted ("+e.result+").","Information");	
							model.deleteSuccess=!model.deleteSuccess;
						}
						else
							Alert.show("The question is successfully deleted ("+e.result+") but its deleted values are not updated from the data grid.","Warning");				
						trace("INFO: The record is successfully updated through inactivateAQuestionForESC() with rows=: "+e.result);
						break;
					default: break;
					
				}	
				*/
			} 		
		}
		
		public function onOperationFault(e:FaultEvent):void
		{		
			e.target.removeEventListener(ResultEvent.RESULT, onOperationResult);
			//INFO: onOperationFault() is called in CapRemoteObjectService.as: [FaultEvent fault=[RPC Fault faultString="Cannot invoke method 'getStrings'." faultCode="Server.ResourceUnavailable" faultDetail="Method 'getStrings' not found."] messageId="6AB214F5-60CA-733E-67CC-64FAF5338CE2" type="fault" bubbles=false cancelable=true eventPhase=2]
			trace("INFO: onOperationFault() is called in CapRemoteObjectService.as: "+e);
			trace("INFO: onOperationFault() is called in CapRemoteObjectService.as: "+e.fault.faultString);
			
			if(AppCommon.contains(e.toString(), "session_timeout"))
			{
				//implement logout link and invalidate all the data collected, through capModel
				//logout then flexsession needs to be invalidate
				FlexGlobals.topLevelApplication.showAlertMessage("Please login again!");
			}
			else if(AppCommon.contains(e.toString(), "NetConnection.Call.Failed"))
				FlexGlobals.topLevelApplication.showAlertMessage("No Network Connection!!!");
			else if(AppCommon.contains(e.fault.faultString, "Exception"))
				FlexGlobals.topLevelApplication.showAlertMessage(String(e.fault.faultString.split(":")[1]).substring(1)+"!!!");//eg). com.sickkids.caliper.exceptions.DAOException : test error
			else
				FlexGlobals.topLevelApplication.showAlertMessage(e.fault.faultString+"!!!");
			
			dispatch(new ApplicationRMIEvent(ApplicationRMIEvent.REMOTE_OBJECT_FAULT_EVENT, e.fault.faultCode, e.fault.faultDetail, e.fault.faultString, e.fault.message));
			//Alert.show(e.fault.faultString)
			//made alert spot at the top of the panel
			
			//info==e now Jan 15, 2014
			/*if(String(info).indexOf("SESSION_TIMEOUT")!=-1)//found
			{
				Alert.show("Your session is expired.\nPlease login in again by clicking Logout.\n\n", "Session Expired");
			}	
			else switch(rmiEvent.type)
			{	
				case AppVoteController.RO_EPOCH_GET_ESC_QUESTION_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_ESC_QUESTION_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_CREATE_NEW_QUESTION_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_CREATE_NEW_QUESTION_REQUEST_EVENT) while creating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_UPDATE_A_QUESTION_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_UPDATE_A_QUESTION_REQUEST_EVENT) while updating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_GET_ESC_VOTE_RESULT_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_ESC_VOTE_RESULT_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_GET_MY_VOTE_DONE_LIST_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_GET_MY_VOTE_DONE_LIST_REQUEST_EVENT) while querying data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_UPDATE_OR_CREATE_MY_VOTE_REQUEST_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_UPDATE_OR_CREATE_MY_VOTE_REQUEST_EVENT) while inserting/updating data: "+info,"Error");
					break;
				case AppVoteController.RO_EPOCH_INACTIVATE_A_QUESTION_EVENT: 
					Alert.show("Fault has been generated (RO_EPOCH_INACTIVATE_A_QUESTION_EVENT) while deleting the selected question: "+info,"Error");
					break;
				default:
					Alert.show("Unknown Fault has been generated in RemoteObjectCommandForVote.as: "+info,"Error"); 
					break;
			}	
			*/
		}
	}
}