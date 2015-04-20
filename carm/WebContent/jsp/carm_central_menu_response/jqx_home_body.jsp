<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style type="text/css">
	.content-container
    {
        padding-left: 15px;
        padding-top: 15px;
        padding-right: 15px;
    }
    textarea
	{
	    width:100%;
	    resize: none;
	   
	}
	.textwrapper
	{
	    border:0px solid #999999;
		margin-left: -3px;
		margin-top: -3px;
		margin-bottom: -3px;
	    padding:0px 3px 0px 0px;
	}
	
	hr
	{	
		border-top: 1px dashed #C1C2FD;
		background-color: #E8E8E8;
		height: 0px;
		width: 100%;
	}
</style>
<% AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb"); %>
<script language="Javascript" type="text/javascript">
	var event_source=null;
	var data_adapter=null;
	var loginLevel=Number('${crb.login_level}');
	log("The loginLevel="+loginLevel);
	$(document).ready(function () 
	{
		event_source = 
		{
            datatype: "array",
            datafields:
            [
				{ name: "display_item", type: "string" },
                { name: "carm_id", type: "number" },
                { name: "site", type: "string" },
                { name: "pat_no", type: "string" },
                { name: "event_no", type: "number" },
                { name: "de_ids", type: "string" },
                { name: "de1_id", type: "number" },
                { name: "de2_id", type: "number" },
                { name: "de3_ids", type: "string" },
                { name: "de4_id", type: "number" },
                { name: "de5_ids", type: "string" },
                { name: "de6_id", type: "number" },           
                { name: "flag", type: "bool" },
                { name: "review_status", type: "String" }
            ]
        };
		$('#jqxEventList').jqxDropDownList({autoOpen: false, width: '155px', height: '25px', displayMember: "display_item", valueMember: "carm_id", 
											//theme: 'energyblue', 
											placeHolder: "Pick an Item Number..."});


		$("#jqxRadioButtonAll").jqxRadioButton({ width: 40, height: 25, checked: true});
        $("#jqxRadioButtonNotStarted").jqxRadioButton({ width: 85, height: 25});
        $("#jqxRadioButtonStarted").jqxRadioButton({ width: 65, height: 25});
        $("#jqxRadioButtonCompleteEditable").jqxRadioButton({ width: 105, height: 25});
        $("#jqxRadioButtonCompleteNotEditable").jqxRadioButton({ width: 122, height: 25});
        $("#jqxRadioButtonIncomplete").jqxRadioButton({ width: 129, height: 25});
        $("#jqxRadioButtonDisagree").jqxRadioButton({ width: 66, height: 25});
        $("#jqxRadioButtonFinalize").jqxRadioButton({ width: 70, height: 25});

        $("#jqxRadioButtonAll").on('change', function (event) 
        {
            if(event.args.checked)
            {
            	cearAllTabs();
            	httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=ALL", "getEventListResponse");           	
            }
        });
        $("#jqxRadioButtonNotStarted").on('change', function (event) 
        {
            if(event.args.checked)
            {
            	cearAllTabs();
            	httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=NOT_STARTED", "getEventListResponse");
            }
        });
        $("#jqxRadioButtonStarted").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=STARTED", "getEventListResponse");
             }
         });
         $("#jqxRadioButtonCompleteEditable").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=COMPLETE_EDITABLE", "getEventListResponse");
             }
         });
         $("#jqxRadioButtonCompleteNotEditable").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=COMPLETE_NOT_EDITABLE", "getEventListResponse");
             }
         });
         $("#jqxRadioButtonIncomplete").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=INCOMPLETE_NOT_EDITABLE", "getEventListResponse");
             }
         });
         $("#jqxRadioButtonDisagree").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=DISAGREE", "getEventListResponse");
             }
         });
         $("#jqxRadioButtonFinalize").on('change', function (event) 
         {
             if(event.args.checked)
             {
            	 cearAllTabs();
            	 httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=FINALIZE", "getEventListResponse");
             }
         });
         
         //Default calling
         httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_event_obj_list&review_status=ALL", "getEventListResponse");
       
         $('#jqxReviewTabs').jqxTabs({width: '970'});
         //$('#jqxReviewTabs').jqxTabs({width: '100%' ,
        	 							//theme: 'energyblue'
        //	 						});
         $('#summaryVerticalSplitter').jqxSplitter({width: '100%', height: 600, orientation: 'horizontal', resizable: false, panels: [{ collapsible: false, size: '30%' }, { size: '70%' }] });
         $('#summaryHorizontalSplitter').jqxSplitter({ width: '100%', orientation: 'vertical',resizable: false,  panels: [{ size: "50%" }] });
         $("#clinicalSummaryEditor").jqxEditor
         (
             {
                 height: '100%', width: '100%', 
                 //theme: 'energyblue',
                 tools: ""
                 
         	}
         );
         $('#jqxEventList').on('select', function(event)
		 {
        	 $('#jqxReviewTabs').jqxTabs('enable');
			
			var args=event.args;
			if(args)
			{
				callAjaxAccordingly($('#jqxReviewTabs').jqxTabs('selectedItem'), args.item);
			}
		 });
         disEnableTabs(0);//initially only for the 1st tab enabled
		//reviewer=4, admin=1,2, mediator=3, viewer=5
		if(loginLevel==4)  
		{
		}
         
		$('#jqxReviewTabs').on('selected', function (event) 
        {   
			var item = $("#jqxEventList").jqxDropDownList('getSelectedItem'); 
			//log("[When a tab selected] selected value="+item.value+", index="+item.index+", de2_id="+data_adapter.records[item.index].de2_id+", de4_id="+data_adapter.records[item.index].de4_id+" tab selected="+$('#jqxReviewTabs').jqxTabs('selectedItem'));
            //log("[When a tab selected] selected:"+event.type+", "+event.args.item);  
            callAjaxAccordingly(event.args.item, item);
        });
        
        $('#jqxReviewTabs').on('unselected', function (event) 
        {
        	//log("unselected:"+event.type+", "+event.args.item);   	
        	//if(Number(event.args.item)==1) $("#updateAdeTabDiv").html("");
        	//else if(Number(event.args.item)==2) $("#reviewAdeTabDiv").html("");
        });
        
	});
	
	function callAjaxAccordingly(tabIndex, itemObject)
	{
		if(itemObject.index<0)
		{
			log("An item number is not selected, callAjaxAccordingly(..)");
			return;
		}
		switch(tabIndex)
		{
			case 0: httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_clinical_summary&carm_id="+itemObject.value, "getClincalSummaryResponse");
					httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_diagnose_record&carm_id="+itemObject.value+"&de2_id="+data_adapter.records[itemObject.index].de2_id, "getDiagnoseResponse");
					httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_problem_reocord&carm_id="+itemObject.value+"&de4_id="+data_adapter.records[itemObject.index].de4_id, "getProblemResponse");
					break;
			case 1: httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_laboratory_record"+
																		   "&carm_id="+itemObject.value+
																		   "&de5_ids="+data_adapter.records[itemObject.index].de5_ids+
																		   "&site="+data_adapter.records[itemObject.index].site+
																		   "&pat_no="+data_adapter.records[itemObject.index].pat_no+
																		   "&event_no="+data_adapter.records[itemObject.index].event_no, "getLaboratoryResponse");
					break;//item clicked when laborotory is tabbed
			case 2: 
					httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_vital_sign_medication_records"+
							   "&carm_id="+itemObject.value+
							   "&de_ids="+data_adapter.records[itemObject.index].de_ids+
							   "&de3_ids="+data_adapter.records[itemObject.index].de3_ids+
							   "&site="+data_adapter.records[itemObject.index].site+
							   "&pat_no="+data_adapter.records[itemObject.index].pat_no+
							   "&event_no="+data_adapter.records[itemObject.index].event_no, "getVitalSignMedicationResponse");
					break;//item clicked when the tab called vital sign and medication  is tabbed
			case 3: httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_radiology_record"+
					   "&carm_id="+itemObject.value+
					   "&de6_id="+data_adapter.records[itemObject.index].de6_id+
					   "&site="+data_adapter.records[itemObject.index].site+
					   "&pat_no="+data_adapter.records[itemObject.index].pat_no+
					   "&event_no="+data_adapter.records[itemObject.index].event_no, "getRadiologyResponse");
					break;//item clicked when the tab called vital sign and medication  is tabbed
			case 4: httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_review_records"+
					   "&carm_id="+itemObject.value+
					   "&de6_id="+data_adapter.records[itemObject.index].de6_id+
					   "&site="+data_adapter.records[itemObject.index].site+
					   "&pat_no="+data_adapter.records[itemObject.index].pat_no+
					   "&event_no="+data_adapter.records[itemObject.index].event_no, "getReviewResponse");
					break;
			case 5: httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_get_forum_records"+
					   "&carm_id="+itemObject.value+
					   "&de6_id="+data_adapter.records[itemObject.index].de6_id+
					   "&site="+data_adapter.records[itemObject.index].site+
					   "&pat_no="+data_adapter.records[itemObject.index].pat_no+
					   "&event_no="+data_adapter.records[itemObject.index].event_no, "getForumResponse");
					break;
			default: break;
		}			
	}
	function cearAllTabs()
	{
		//log("start - cearAllTabs()");
		$("#jqxEventList").jqxDropDownList('clear');//so that selected tab 0 won't be activated for ajax calling
		$("#clinicalSummaryEditor").jqxEditor('val', '');
		$("#diagnoseContent").html("");
		$("#problemContent").html("");
		
		//choose the 1st tab
		$('#jqxReviewTabs').jqxTabs('select', 0); 
		
		disEnableTabs(0);
		
		//log("end - cearAllTabs()");
	}
	function disEnableTabs(enableIndex)
	{
		$('#jqxReviewTabs').jqxTabs('disable'); 
		$('#jqxReviewTabs').jqxTabs('enableAt', enableIndex); 
	}
	
	function getEventListResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{	
			event_source.localdata=eval(strResponse);
			data_adapter = new $.jqx.dataAdapter(event_source);
			$("#jqxEventList").jqxDropDownList('clear');
			$('#jqxEventList').jqxDropDownList('source', data_adapter);	
		}
		else 
		{
			alert(strResponse.substring(6));//false, then display
		}
	}
	function getClincalSummaryResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_clinical_summary:')==-1)//not found, means true
		{	
			$("#clinicalSummaryEditor").jqxEditor('val', strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_clinical_summary:".length));//false, then display
		}
	}
	function getDiagnoseResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_dignose_record:')==-1)//not found, means true
		{	
			$("#diagnoseContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_dignose_record:".length));//false, then display
		}
	}
	function getProblemResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_problem_reocord:')==-1)//not found, means true
		{	
			$("#problemContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_problem_reocord:".length));//false, then display
		}
	}
	function getLaboratoryResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_laboratory_reocord:')==-1)//not found, means true
		{	
			$("#laboratoryContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_laboratory_reocord:".length));//false, then display
		}
	}
	function getVitalSignMedicationResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_vital_sign_medication_records:')==-1)//not found, means true
		{	
			$("#vitalSignMedicationContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_vital_sign_medication_records:".length));//false, then display
		}
	}
	function getRadiologyResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_radiology_record:')==-1)//not found, means true
		{	
			$("#radiologyContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_radiology_record:".length));//false, then display
		}
	}
	function getReviewResponse(strResponse)
	{	//log("getReviewResponse(...)");
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_review_records:')==-1)//not found, means true
		{	
			$("#reviewContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_review_records:".length));//false, then display
		}
	}
	function getForumResponse(strResponse)
	{	//log("getForumResponse(...)");
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_get_forum_records:')==-1)//not found, means true
		{	
			$("#forumContent").html(strResponse);
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_get_forum_records:".length));//false, then display
		}
	}
</script>
 
<table border=0 cellpadding=0 cellspacing=0 bgcolor='#ffffff' width="100%" > 
	
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			 	<div class='jqx-hideborder'>									
					<img src='images/common/item_icon.png' style='float: left; margin-left: 5px; margin-right: 5px;'/>
					<div  id='jqxEventList' style='float: left; padding-left: 0px;'></div>	
					<div style='float: left; margin-left: 10px; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonAll'><span style='font-size: 9px;'>All</span></div><!-- before and after time_out -->
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonNotStarted'><span style='font-size: 9px;'>Not Started</span></div><!-- before time_out -->
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonStarted'><span style='font-size: 9px;'>Started</span></div><!-- before time_out -->
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonCompleteEditable'><span style='font-size: 9px;'>Complete (Edit)</span></div><!-- before time_out -->
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonCompleteNotEditable'><span style='font-size: 9px;'>Complete (No Edit)</span></div><!-- after time_out -->	
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonIncomplete'><span style='font-size: 9px;'>Incomplete (No Edit)</span></div><!-- after time_out -->	
					<span style='font-size: 14px; color: #888BFB; float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>'>&nbsp;|&nbsp;</span>
					<div style='float: left; <%=(crb.getLogin_level()<3? "display:none;":"") %>' id='jqxRadioButtonDisagree'><span style='font-size: 9px; color: #888BFB;'>Disagree</span></div><!-- before time_out -->
					<span style='font-size: 14px; color: #888BFB; float: left; <%=(crb.getLogin_level()!=3? "display:none;":"") %>'>&nbsp;|&nbsp;</span>
					<div style='float: left; <%=(crb.getLogin_level()!=3? "display:none;":"") %>' id='jqxRadioButtonFinalize'><span style='font-size: 9px; color: #888BFB;'>Confirm</span></div><!-- after time_out -->		
				</div>
			</td>
		</tr>
		<tr><td colspan="4" height="10" ></td></tr>
		<tr>
			<td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
			 	<div id='jqxReviewTabs' >
				    <ul style='margin-left: 0px;'>
						<li>Summary/Diagnoses/Problems</li> 
				        <li>Laboratory</li> 
						<li>Vital Sign/Medication</li>
						<li>Radiology</li>
						<li>Review</li>
						<li>Forum</li>
				    </ul>
					<div id='summaryDiagnosesProblemsDiv'>
						<div class="jqx-hideborder" id="summaryVerticalSplitter" >
			                <div id="summaryContent">
								<textarea id="clinicalSummaryEditor"><p style="padding-left: 20px; padding-top: 20px;">Please select one of the item numbers</p></textarea>	
			                </div>
			                <div>
			                    <div class="jqx-hideborder" id="summaryHorizontalSplitter">
									<div id="diagnoseContent"><p style="padding-left: 20px; padding-top: 20px;">Diagnose information</p></div>
									<div id="problemContent"><p style="padding-left: 20px; padding-top: 20px;">Problem information</p></div>
								</div>
			                </div>
			            </div>
					</div>
				    <div class="content-container" id='laboratoryDiv'>
				    	<div id="laboratoryContent">Laboratory information</div>
				    </div>
					<div class="content-container" id='vitalSignMedicationDiv'>
						<div id="vitalSignMedicationContent">Vital sign and medication information</div>
					</div>
					<div class="content-container" id='radiologyDiv'>
						<div id="radiologyContent">Radiology</div>
					</div>
					<div class="content-container" id='reviewDiv'>
						<div id="reviewContent">Review</div>
					</div>
					<div class="content-container" id='forumDiv'>
						<div id="forumContent">Forum</div>
					</div>
					
				</div>
			</td>
		</tr>
</table> 			