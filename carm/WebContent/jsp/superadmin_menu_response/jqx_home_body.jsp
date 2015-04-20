<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="org.apache.commons.collections4.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %> 
<%
 	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");

 	List<AllApplicationBean> appList=csqlDao.getListFromAllApplicationTable("order by app_symbol asc");//change this if this is EXEMPLAR	
 	AllRegisteredUserBean sab=(AllRegisteredUserBean)session.getAttribute("sab");
 %>
<script language="Javascript" type="text/javascript">
/*
 * Note: autofill = confirmatory in group level and point level
 */
	$(document).ready(function () 
	{		
		var selectedRowIndex=-1;
		var appId=-1;
		var selectedDataRecord;
		var action_command;//add, edit, delete
		var data=[
			 		<%	
			 			for(int i=0;i<appList.size();i++)
			 			{
			 				AllApplicationBean ab=appList.get(i);
			 				out.print("{"); 
			 				out.print("\"purpose_of_app\": \""+ab.getPurpose_of_app()+"\",");	
			 				out.print("\"app_symbol\": \""+ab.getApp_symbol()+"\","); 
		 					out.print("\"number_of_groups\": \""+ab.getNumber_of_groups()+"\","); 
		 					out.print("\"reviewers_per_item\": \""+ab.getSReviewers_per_item()+"\",");
		 					out.print("\"reviewer_assignment_type\": \""+ab.getReviewer_assignment_type()+"\","); 
		 					out.print("\"confirmatory\": \""+ab.isConfirmatory()+"\","); 
		 					out.print("\"disagreement_process\": \""+ab.getDisagreement_process()+"\",");
		 					out.print("\"reviewers_per_item_int\": \""+ab.getReviewers_per_item()+"\",");
		 					out.print("\"app_id_revision\": \""+ab.getApp_id_revision()+"\",");
		 					out.print("\"app_id\": \""+ab.getApp_id()+"\"");
			 				
							out.print(((i+1)==appList.size() ? "}" : "},"));				
			 			}
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "purpose_of_app", type: "string" },
                { name: "app_symbol", type: "number" },
                { name: "number_of_groups", type: "number" },
                { name: "reviewers_per_item", type: "string"},
                { name: "reviewer_assignment_type", type: "string" },
                { name: "confirmatory", type: "bool"},
                { name: "disagreement_process", type: "string" },
                { name: "reviewers_per_item_int", type: "number"},
                { name: "app_id_revision", type: "string" },
                { name: "app_id", type: "number" }
                
                
            ],
            addrow: function (rowid, rowdata, position, commit) {commit(true);},
            deleterow: function (rowid, commit) {commit(true); },
            updaterow: function (rowid, newdata, commit) {commit(true);},
            sortcolumn: 'app_symbol',
            sortdirection: 'asc'
        };

        var dataAdapter = new $.jqx.dataAdapter(source);
        
        $("#jqxgrid").jqxGrid(
        {
        	width: '100%',
        	//theme: 'energyblue',
        	pageable: true,
            source: dataAdapter,
            columnsresize: true,
            autoheight: true,
            sortable: true,
            showtoolbar: true,
            //altrows: true,
            toolbarheight: 35,
            
            rendertoolbar: function(toolBar)
            {
            	 var toTheme = function (className) 
            	 {
                     if (this.theme == "") return className;
                     return className + " " + className + "-" + this.theme;
                 }
                // appends buttons to the status bar.
                var container = $("<div style='overflow: hidden; position: relative; height: 100%; width: 100%;'></div>");
                var buttonTemplate = "<div style='float: left; padding: 3px; margin: 2px;'><div style='margin: 4px; width: 16px; height: 16px;'></div></div>";
                //var addButton = "<div style='float: left; padding: 3px; margin: 2px;'><div style='margin: 4px; width: 16px; height: 16px;'><span class='ui-icon ui-icon-plus'></span></div></div>";
                var addButton= $(buttonTemplate);
                var editButton = $(buttonTemplate);
                var deleteButton = $(buttonTemplate);

                container.append(addButton);
                container.append(editButton);
                container.append(deleteButton);                
                toolBar.append(container);
                
                addButton.jqxButton({cursor: "pointer", enableDefault: false, disabled: false, height: 25, width: 25 });
                addButton.find('div:first').addClass(toTheme('jqx-icon-plus'));
                addButton.jqxTooltip({ position: 'bottom', content: "Add"});
                
                editButton.jqxButton({ cursor: "pointer", enableDefault: false, disabled: false, height: 25, width: 25 });
                editButton.find('div:first').addClass(toTheme('jqx-icon-edit'));
                editButton.jqxTooltip({ position: 'bottom', content: "Edit"});
                
                deleteButton.jqxButton({ cursor: "pointer", enableDefault: false, disabled: false, height: 25, width: 25 });
                deleteButton.find('div:first').addClass(toTheme('jqx-icon-delete'));
                deleteButton.jqxTooltip({ position: 'bottom', content: "Delete"});
                
                addButton.click(function (event) 
                {               
                	var offset = $("#jqxgrid").offset();
                    $("#popupWindow").jqxWindow({title: 'add', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
                    $("#popupHeader").html("<img src='images/common/plus_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Add");
                    
                    $("#jqxgrid").jqxGrid('clearselection');
                    $("#app_purpose").val("");
                    $("#app_symbol").val("");
                    $("#number_of_groups").val("1");
                    $("#reviewers_per_item").val("1");
                    $("#assignment_type").val("by_group");
                    setRadioValue("confirmatoryRadio", ""+true+"");
                    setRadioValue("toMediatorRadio", "peer_to_mediator");
                    
                    
                    $("#popupWindow").jqxWindow('open');
                    disableComponents(false);
                });
                editButton.click(function (event) 
                {     
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in editButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		jAlert('You should select a row to edit!', 'Warning');
                	}
                	else
                	{
	                	var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    $("#app_purpose").val(selectedDataRecord.purpose_of_app);
	                    $("#app_symbol").val(selectedDataRecord.app_symbol);
	                    $("#number_of_groups").val(selectedDataRecord.number_of_groups);
	                    $("#reviewers_per_item").val(selectedDataRecord.reviewers_per_item_int);
	                    $("#assignment_type").val(selectedDataRecord.reviewer_assignment_type);
	                    setRadioValue("confirmatoryRadio", ""+selectedDataRecord.confirmatory+"");
	                    setRadioValue("toMediatorRadio", selectedDataRecord.disagreement_process);
	                    appId=selectedDataRecord.app_id;	                    
	                    
	                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit");
	                    
	                    $("#popupWindow").jqxWindow('open');
	                    disableComponents(false);
                	}
                });
                
                deleteButton.click(function (event) 
                {
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in deleteButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		jAlert('You should select a row to delete!', 'Warning');
                	}
                	else
                	{
	                	var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'delete', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    
	                    $("#app_purpose").val(selectedDataRecord.purpose_of_app);
	                    $("#app_symbol").val(selectedDataRecord.app_symbol);
	                    $("#number_of_groups").val(selectedDataRecord.number_of_groups);
	                    $("#reviewers_per_item").val(selectedDataRecord.reviewers_per_item_int);
	                    $("#assignment_type").val(selectedDataRecord.reviewer_assignment_type);
	                    setRadioValue("confirmatoryRadio", ""+selectedDataRecord.confirmatory+"");
	                    setRadioValue("toMediatorRadio", selectedDataRecord.disagreement_process);
	                    appId=selectedDataRecord.app_id;	                    
	                    
	                    $("#popupHeader").html("<img src='images/common/trash_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Delete");
	                    
	                    $("#popupWindow").jqxWindow('open');
	                    disableComponents(true);
                	}
                });
                           	
            },
            columns: 
            [
              { text: 'Application Purpose', dataField: 'purpose_of_app', align: 'center', width: '27%' },
              { text: 'Symbol', dataField: 'app_symbol', align: 'center', width: '10%' },
              { text: '# of Groups', dataField: 'number_of_groups', align: 'center', width: '10%' },
              { text: 'Reviewers/Item', dataField: 'reviewers_per_item', align: 'center', width: '13%' },
              { text: 'Assignment Type', dataField: 'reviewer_assignment_type', align: 'center', width: '15%'},
              { text: 'Confirmatory', dataField: 'confirmatory', align: 'center', columntype: 'checkbox', width: '10%' },
              { text: 'Dis-Process', dataField: 'disagreement_process', align: 'center', width: '15%' },
              { text: 'Reviewers/Item', dataField: 'reviewers_per_item_int', align: 'center', width: '0%' },
              { text: 'APP-ID-REVISION', dataField: 'app_id_revision', align: 'center', width: '0%' },
              { text: 'APP-ID', dataField: 'app_id', align: 'center', width: '0%' }
            ]
        });

        $("#jqxgrid").jqxGrid('hidecolumn', 'reviewers_per_item_int');
        $("#jqxgrid").jqxGrid('hidecolumn', 'app_id_revision');
        $("#jqxgrid").jqxGrid('hidecolumn', 'app_id');
     
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 450, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
        $("#popupWindow").on('open', function () 
        {
            //$("#firstName").jqxInput('selectAll');
            log("title="+$("#popupWindow").jqxWindow('title'));
        	//$("#popupHeader").html("<img src='images/common/plus_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Add");
        });
   
        //Add, Edit, Delete
        $("#popupWindowSubmit").click(function () 
        {
        	action_command=$("#popupWindow").jqxWindow('title');//add, edit, delete
        	var onsuccess=$("#application_form").jqxValidator('validate');
			if(!onsuccess) return;
						
			document.getElementById("spinner_img").className="spinner_unhidden"; 
			
			$.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/carm/admin?op=ajax_action_application",
		         data: 
		         {
		    	 		action: 		action_command,
		    	 		app_id:  		appId,
		    	 		app_purpose:		document.getElementById("app_purpose").value,
		    	 		app_symbol:		document.getElementById("app_symbol").value,
		    	 		number_of_groups:	document.getElementById("number_of_groups").value,
		    	 		reviewers_per_item:	document.getElementById("reviewers_per_item").value,
		    	 		assignment_type:	document.getElementById("assignment_type").value,
		    	 		confirmatory:		getRadioValue('confirmatoryRadio'),
		    	 		toMediator:		getRadioValue('toMediatorRadio')		    	 		
		     	 },
		         success: getAdminActionResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 alert(response.responseText);	
		             
		         }
		      }); 
			     	
        });     
        
        $("#app_purpose").jqxInput({placeHolder: "The purpose of the application", minLength: 3, width: '230px', height: '20px'});
        $("#app_symbol").jqxInput({placeHolder: "Symbolic word", minLength: 2, width: '130px', height: '20px'});

		$("#application_form").jqxValidator
		(
			{
				onError: function()
				{
					log("You have not filled the form correctly!");
				},
				rules:
				[	
					 {
							 input: '#app_purpose',
							 message: 'The purpose of the application with [a-zA-Z0-9] and space!',
							 rule: function(input, commit)
							 {
								 document.getElementById('app_purpose').value=trim(document.getElementById('app_purpose').value);
								 if(document.getElementById('app_purpose').value.length<3) return false;
								 return checkAlphanumericSpace(document.getElementById('app_purpose').value);
							 }
					  },
					  
					  {
						 input: '#app_symbol',
						 message: 'The symbolic name with [a-zA-Z0-9]!',
						 rule: function(input, commit)
						 {
						 	document.getElementById('app_symbol').value=trim(document.getElementById('app_symbol').value);
						 	if(document.getElementById('app_symbol').value.length<2) return false;
						 	return check_alphanumeric(document.getElementById('app_symbol').value);
						 }
					 },
					 
					 {
						 input: '#number_of_groups',
						 message: 'The number should be >= the current value',
						 rule: function(input, commit)
						 {
							 if(action_command=="edit")
							 {
								 if($("#number_of_groups").val()<Number(selectedDataRecord.number_of_groups)) return false
								 else return true;
							 }
							 else
							 {
								 return true;
							 }						 	
						 }
					 },	
					 
					 {
						 input: '#reviewers_per_item',
						 message: 'The number should be >= the current value',
						 rule: function(input, commit)
						 {
							 if(action_command=="edit")
							 {
								 if($("#reviewers_per_item").val()<Number(selectedDataRecord.reviewers_per_item_int)) return false
								 else return true;
							 }
							 else
							 {
								 return true;
							 }						 	
						 }
					 }
				]
			}
		);		
	});//$(document).ready
	
	function sReviewersPerItem(val)
	{
		var sReviewers_per_item="0";
		switch(Number(val))
		{
			case 1:
			case 2:
			case 3:
			case 4: sReviewers_per_item=val;
					break;
			case 10:sReviewers_per_item="<10";
					break;
			case 20:sReviewers_per_item="<20";
					break;
			case 10000: sReviewers_per_item=">=20";
					break;
			default: break;
		}
		return sReviewers_per_item;
	}
	function getSelectedIndexByValue(elementId, val)
	{
		var options=document.getElementById(elementId).options;
		for(var i=0;i<options.length;i++)
			if(options[i].value==val) return i;
		return Number(-1);//not found
	}
	function disableComponents(doIt)
	{
		$("#app_purpose").prop('disabled', doIt);
		$("#app_symbol").prop('disabled', doIt);
		$("#number_of_groups").prop('disabled', doIt);
		$("#reviewers_per_item").prop('disabled', doIt);
		$("#assignment_type").prop('disabled', doIt);
		$("#confirmatoryNo").prop('disabled', doIt);
		$("#confirmatoryYes").prop('disabled', doIt);
		$("#toMediatorNo").prop('disabled', doIt);
		$("#toMediatorYes").prop('disabled', doIt);
	}	
	function getAdminActionResponse(strResponse)
	{
		log("getAdminActionResponse("+strResponse+")");
		
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			jAlert("Your session is expired. Please login again.", "Warning", function(r) 
			{
				location.reload();
			});					
		}
		else if(strResponse.indexOf('false_ajax_action_application:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');
			var retArgs;
			var app_id;
			var app_id_revision;
			if(action_command=="add")
			{
				retArgs=strResponse.split("[À]");
				retArgs=retArgs[1].split("&");
				app_id=retArgs[0].split("=")[1];
				app_id_revision=retArgs[1].split("=")[1];
				var aRow={
						 "purpose_of_app":$("#app_purpose").val(),
						 "app_symbol":$("#app_symbol").val().toUpperCase(),
						 "number_of_groups":$("#number_of_groups").val(),
						 "reviewers_per_item":sReviewersPerItem($("#reviewers_per_item").val()),
						 "reviewer_assignment_type":$("#assignment_type").val(),
						 "confirmatory":getRadioValue('confirmatoryRadio'),
						 "disagreement_process":getRadioValue('toMediatorRadio'),
						 "reviewers_per_item_int":$("#reviewers_per_item").val(),
						 "app_id_revision":app_id_revision,
						 "app_id":app_id
						 };
				$('#jqxgrid').jqxGrid('addrow', null, aRow);
				$("#popupWindow").jqxWindow('close');
			}
			else if(action_command=="edit")
			{
				retArgs=strResponse.split("[À]");
				retArgs=retArgs[1].split("&");
				app_id=retArgs[0].split("=")[1];
				app_id_revision=retArgs[1].split("=")[1];
				var aRow={
						 "purpose_of_app":$("#app_purpose").val(),
						 "app_symbol":$("#app_symbol").val().toUpperCase(),
						 "number_of_groups":$("#number_of_groups").val(),
						 "reviewers_per_item":sReviewersPerItem($("#reviewers_per_item").val()),
						 "reviewer_assignment_type":$("#assignment_type").val(),
						 "confirmatory":getRadioValue('confirmatoryRadio'),
						 "disagreement_process":getRadioValue('toMediatorRadio'),
						 "reviewers_per_item_int":$("#reviewers_per_item").val(),
						 "app_id_revision":app_id_revision,
						 "app_id":app_id
						 };
				selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
				$('#jqxgrid').jqxGrid('updaterow', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex), aRow);
				$("#popupWindow").jqxWindow('close');
			}
			else if(action_command=="delete")
			{
				//not working with incorrect index value
				//selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex'); log("rowid="+ $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
				//$('#jqxgrid').jqxGrid('deleterow', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
				//$("#popupWindow").jqxWindow('close');
				//$("#jqxgrid").jqxGrid('clearselection');
				//$("#jqxgrid").jqxGrid('refreshdata');
				location.reload();
			}					
		}
		else if(strResponse.indexOf('all_application_app_symbol_key')>0)//found
		{
			jAlert('There exists a duplicate key value in Application Symbol!', 'Warning');
		}
		else 
		{
			jAlert(strResponse.substring("false_ajax_action_application:".length), 'Error');
		}
	}
</script>

<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top">
			
		</td>
	</tr>
	<tr><td align="center">
		<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; "> 	
			<tr>
			    <td colspan=4 align="left">
					<table height="40" width="100%">
						<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
								<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Application Management</span>
							</td>
							<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
								
							</td>
						</tr>	
						<tr><td colspan=2><hr/></td></tr>				
					</table>					
				</td>
			</tr>
			<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
				 <div id="jqxgrid"></div>

				 <div id="popupWindow">
			            <div id="popupHeader"></div>
			            <div style="overflow: hidden;">
			            	<form id='application_form' action='./'>
			                <table>
			                	<tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="43%">Application purpose:</td>
			                        <td align="left">
			                        	<input type='text' id="app_purpose"  maxlength="516" class=input_text style='padding-left: 2px;imemode:inactive'/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Symbolic word:</td>
			                        <td align="left">
			                        	<input type='text' id="app_symbol" maxlength="12" class=input_text style='padding-left: 2px;imemode:inactive'/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Number of groups:</td>
			                        <td align="left">
			                        	<select id="number_of_groups">		
			                        		<option value='1'>1</option>
			                        		<option value='10'>10</option>
			                        		<option value='100'>100</option>
			                        		<option value='500'>500</option>
										</select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Reviewers per item:</td>
			                        <td align="left">
			                        	<select id="reviewers_per_item">		
										  	<option value='1'>1</option>
										  	<option value='2'>2</option>
										  	<option value='3'>3</option>
										  	<option value='4'>4</option>
			                        		<option value='10'>&lt;10</option>
			                        		<option value='20'>&lt;20</option>
			                        		<option value='10000'>&ge;20</option>													 
										</select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Assignment type:</td>
			                        <td align="left">
			                        	<select id="assignment_type">		
										  	<option value='by_group'>By Group</option>
										  	<option value='by_item'>By Item</option>													 
										</select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%">Confirmatory:</td>
			                        <td align="left">
			                        			<input type='radio' id='confirmatoryNo' name='confirmatoryRadio' value='false'>No</input>
			                        			<input type='radio' id='confirmatoryYes' name='confirmatoryRadio' value='true' checked>Yes</input>		                        			
			                        </td>
			                    </tr>

								<tr>
			                        <td align="right" width="43%">Disagreement process:</td>
			                        <td align="left">
			                        			<input type='radio' id='toMediatorNo' name='toMediatorRadio' value='peer_to_peer' >Peer to Peer</input><br /><!--Peer to Peer then Peero to Mediator  -->		                        			
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="43%"></td>
			                        <td align="left">
			                        			<input type='radio' id='toMediatorYes' name='toMediatorRadio' value='peer_to_mediator' checked>Peer to Mediator</input><!--Peer to Mediator  -->		                        			
			                        </td>
			                    </tr>
			                    <tr height='15'><td colspan='2'></td></tr>
			                    
			                    <tr><td colspan='2' align='center'><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden"/></td></tr>
			                    <tr>
			                        <td colspan='2' style="padding-top: 10px;" align="center">
			                        	<input style="margin-right: 5px;" type="button" id="popupWindowSubmit" value="Submit" />
			                        	<input id="popupWindowCancel" type="button" value="Cancel" /></td>
			                    </tr>
			                    <tr height='15'><td colspan='2'></td></tr>
			                </table>
			                </form>
			            </div>
			       </div>
										
				</td>
			</tr>
					
		</table>
 	</td>
	</tr>
</table>  