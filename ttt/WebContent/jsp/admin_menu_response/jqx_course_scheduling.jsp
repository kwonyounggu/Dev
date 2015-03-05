<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %> 

<%
	SQLDao sqlDao=(SQLDao)application.getAttribute("tttsqlDao");
	List<CourseTimeTableBean> sessionList=sqlDao.getCourseTimeTableList("select a.time_table_id, a.course_number, a.start_time, a.end_time, a.duration, a.email_alert_to, a.hist_record_path, a.session_status, a.session_description, a.submitter_id, a.submission_time, a.remarks, a.valid, a.file_ids, b.course_name from course_time_table as a, curriculum_current as b where a.course_number=b.course_number order by b.course_name, a.start_time asc");
	List<FileLibraryBean> fileList=sqlDao.getFileList("order by file_name_formal asc");
	List<CurriculumCurrentBean> courseList=sqlDao.getCurriculumCurrentList("where valid=true order by course_name asc");
 %>
<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
		var selectedRowIndex=-1;

		var selectedDataRecord;
		var action_command;//add, edit, delete
		var data=[
			 		<%	
			 			for(int i=0;i<sessionList.size();i++)
			 			{
			 				CourseTimeTableBean tb=sessionList.get(i);
			 				out.print("{"); 
			 				
			 				//Visible
			 				out.print("\"timeTableId\": \""+tb.getTimeTableId()+"\","); 
			 				out.print("\"courseNumber\": \""+tb.getCourseNumber()+"\","); 
			 				out.print("\"startTime\": \""+tb.getStartTime()+"\","); 
			 				out.print("\"endTime\": \""+tb.getEndTime()+"\","); 
			 				out.print("\"duration\": \""+tb.getDuration()+"\","); 
			 				out.print("\"emailAlertTo\": \""+tb.getEmailAlertTo()+"\","); 
			 				out.print("\"histRecordPath\": \""+tb.getHistRecordPath()+"\","); 
			 				out.print("\"sessionStatus\": \""+tb.getSessionStatus()+"\","); 
			 				out.print("\"sessionDescription\": \""+tb.getSessionDescription()+"\","); 
			 				out.print("\"submitterId\": \""+tb.getSubmitterId()+"\","); 
			 				out.print("\"submissionTime\": \""+tb.getSubmissionTime()+"\","); 			 				
			 				out.print("\"remarks\": \""+tb.getRemarks()+"\","); 
							out.print("\"valid\": \""+tb.isValid()+"\",");
		 					out.print("\"fileIds\": \""+tb.getFileIds()+"\",");
		 					out.print("\"courseName\": \""+tb.getCourseName()+"\"");
			 				
							out.print(((i+1)==sessionList.size() ? "}" : "},"));				
			 			}
	
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "timeTableId", type: "number" },
                { name: "courseNumber", type: "number" },
                { name: "startTime", type: "string", format: 'MM/dd/yyyy'},
                { name: "endTime", type: "string", format: 'MM/dd/yyyy'},
                { name: "duration", type: "string"},
                { name: "emailAlertTo", type: "string" },
                { name: "histRecordPath", type: "string" },
                { name: "sessionStatus", type: "string" },
                { name: "sessionDescription", type: "string" },
                { name: "submitterId", type: "string" },
                { name: "submissionTime", type: "string", format: 'MM/dd/yyyy'},
                { name: "remarks", type: "string" },               
                { name: "valid", type: "bool" },
                { name: "fileIds", type: "string" },
                { name: "courseName", type: "string" } 
            ],
            addrow: function (rowid, rowdata, position, commit) {commit(true);},
            deleterow: function (rowid, commit) {commit(true); },
            updaterow: function (rowid, newdata, commit) {commit(true);},
            sortcolumn: 'courseName',
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
                    $("#courseName").val("");
                    $("#sessionDescription").val("");
                    <%
                    	String options="";
                    	for(FileLibraryBean fb : fileList)
                    	{
                    		options+="<option value='"+fb.getFileId()+"'>"+fb.getFileNameFormal()+"</option>";
                    	}
                    	//out.print("$('#filesFrom').html(\""+options+"\");");
                    %>
                    $('#filesFrom').empty();
                    $('#filesFrom').html("<%=options%>");//OK
                    $('#filesTo').empty();
                    $('#validBox').jqxCheckBox('check');
                                       
                    $("#popupWindow").jqxWindow('open');
                });
                editButton.click(function (event) 
                {     
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in editButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		alert('You should select a row to edit!');
                	}
                	else //check if it is only in scheduled status otherwise no edit allowed
                	{                	
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    if(selectedDataRecord.sessionStatus!="SCHEDULED")
	                    {
	                    	alert("The record is not in the editable status (only 'SCHEDULED' editable)");
	                    	return;
	                    }
	                    $("#courseName").val(selectedDataRecord.courseNumber);
	                    $("#sessionDescription").val(selectedDataRecord.sessionDescription);
	                    $('#startTime').jqxDateTimeInput('val', parseDate(selectedDataRecord.startTime));
	                    $("#duration").val(selectedDataRecord.duration);
	                    
	                    $('#filesFrom').empty();
	                    $('#filesFrom').html("<%=options%>");
	                    $('#filesFrom').val(selectedDataRecord.fileIds.split(","));
	                    $('#filesTo').empty();
	                    $("#moveright").trigger("click");
	                    
	                    $("#timeTableId").val(selectedDataRecord.timeTableId);
	                    $("#sessionStatus").val(selectedDataRecord.sessionStatus);
	                    
	                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);	                   
	                    
	                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit <span style='color: #2D73BB; font-size: 11px;'>(Note: your update may interrupt the current operation if in use!)</span>");
	                    var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    $("#popupWindow").jqxWindow('open');
                	}
                });
                
                deleteButton.click(function (event) 
                {     
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in editButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		alert('You should select a row to edit!');
                	}
                	else //check if it is only in scheduled status otherwise no edit allowed
                	{                	
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    if(selectedDataRecord.sessionStatus!="SCHEDULED")
	                    {
	                    	alert("The record is not in the removable status (only 'SCHEDULED' removable)");
	                    	return;
	                    }
	                    $("#courseName").val(selectedDataRecord.courseNumber);
	                    $("#sessionDescription").val(selectedDataRecord.sessionDescription);
	                    $('#startTime').jqxDateTimeInput('val', parseDate(selectedDataRecord.startTime));
	                    $("#duration").val(selectedDataRecord.duration);
	                    
	                    $('#filesFrom').empty();
	                    $('#filesFrom').html("<%=options%>");
	                    $('#filesFrom').val(selectedDataRecord.fileIds.split(","));
	                    $('#filesTo').empty();
	                    $("#moveright").trigger("click");
	                    
	                    $("#timeTableId").val(selectedDataRecord.timeTableId);
	                    $("#sessionStatus").val(selectedDataRecord.sessionStatus);
	                    
	                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);	                   
	                    
	                    $("#popupHeader").html("<img src='images/common/trash_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Delete <span style='color: #2D73BB; font-size: 11px;'>(Note: your delete may interrupt the current operation if in use!)</span>");
	                    var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'delete', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    $("#popupWindow").jqxWindow('open');
                	}
                });
                           	
            },
            columns: 
            [
              { text: 'TimeTableId', dataField: 'timeTableId', align: 'center', width: '0%' },
              { text: 'CourseNumber', dataField: 'courseNumber', align: 'center', width: '0%' },
              { text: 'Course Name', dataField: 'courseName', align: 'center', width: '25%' },
              { text: 'About Session', dataField: 'sessionDescription', align: 'center', width: '30%'},
              { text: 'Start Date Time', dataField: 'startTime', align: 'center', width: '20%', cellsformat: 'd'},
              { text: 'EndTime', dataField: 'endTime', align: 'center', width: '0%' },
              { text: 'Duration', dataField: 'duration', align: 'center', width: '10%'},
              { text: 'EmailAlertTo', dataField: 'emailAlertTo', align: 'center', width: '0%' },             
              { text: 'HistRecordPath', dataField: 'histRecordPath', align: 'center', width: '0%'},
              { text: 'Status', dataField: 'sessionStatus', align: 'center', width: '10%' },
              { text: 'SubmitterId', dataField: 'submitterId', align: 'center', width: '0%'},
              { text: 'SubmissionTime', dataField: 'submissionTime', align: 'center', cellsformat: 'd', width: '0%' },
              { text: 'Remarks', dataField: 'remarks', align: 'center', width: '0%' },             
              { text: 'Valid', dataField: 'valid', align: 'center', columntype: 'checkbox', width: '5%' },
              { text: 'FileIds', dataField: 'fileIds', align: 'center', width: '0%' }
            ]
        });
   
        //Invisible columns
        $("#jqxgrid").jqxGrid('hidecolumn', 'timeTableId');
        $("#jqxgrid").jqxGrid('hidecolumn', 'courseNumber');
        $("#jqxgrid").jqxGrid('hidecolumn', 'endTime');
        $("#jqxgrid").jqxGrid('hidecolumn', 'emailAlertTo');
        $("#jqxgrid").jqxGrid('hidecolumn', 'histRecordPath');
        $("#jqxgrid").jqxGrid('hidecolumn', 'submitterId');
        $("#jqxgrid").jqxGrid('hidecolumn', 'submissionTime');
        $("#jqxgrid").jqxGrid('hidecolumn', 'remarks');
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileIds');
        
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 480, resizable: true,  isModal: true, autoOpen: false, showCloseButton: true, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
        $("#popupWindow").on('open', function () 
        {
            $('#errorMsg').html("");
            if($("#popupWindow").jqxWindow('title')=="delete")
            {       
            	disableComponents(true);
            }
            else
            {
            	disableComponents(false);
            }
        });
        $("#popupWindow").on('close', function () 
        {
        	disableComponents(false);
        });
   
        //Add, Edit, Delete
        $("#popupWindowSubmit").click(function () 
        {	log($("select#filesTo option").map(function() {return $(this).val();}).get().join());
        	action_command=$("#popupWindow").jqxWindow('title');//add, edit, delete
        	var onsuccess=$("#application_form").jqxValidator('validate');
			if(!onsuccess) return;
				

			$.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/ttt/controller?op=ajax_action_course_scheduling",
		         data: 
		         {
		    	 		
		    	 		action: 					action_command,
		    	 		timeTableId:				$("#timeTableId").val(),
		    	 		courseNumber:				$("#courseName").val(),
		    	 		startTime:					Date.parse($("#startTime").jqxDateTimeInput('getText')),
		    	 		duration:					$("#duration").val(),
		    	 		sessionStatus:				$("#sessionStatus").val(),
		    	 		sessionDescription:			$("#sessionDescription").val(),
		    	 		valid: 						$("#validBox").jqxCheckBox('checked'),
		    	 		fileIds:					$("select#filesTo option").map(function() {return $(this).val();}).get().join()
		     	 },
		     	 beforeSend: function()
		     	 {
                    $("#spinner_img").show();
                 },
		         success: getActionResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 //alert(response.responseText);	
		        	 alert("Error: the requested resouce is not available");
		        	 $("#spinner_img").hide();
		         }
		      }); 	 	
        });     
        
        /**********************************************************************************/
        /*** Initialize the given components **********************************************/
        /**********************************************************************************/
        $("#spinner_img").hide();
        $("#validBox").jqxCheckBox({ width: 120, height: 25, checked: true });
        $("#startTime").jqxDateTimeInput({width: '200px', height: '20px', formatString: 'MMM dd yyyy hh:mm tt', value: new Date(<%=Utils.login_access_limit_date.getTime()%>), min: new Date(), allowKeyboardDelete: false});
        /*Validate the given form*/
		$("#application_form").jqxValidator
		(
			{
				onError: function()
				{
					log("You have not filled the form correctly!");
				},
				rules:
				[	
					{ input: '#sessionDescription', message: 'Please briefly describe about the session!', action: 'keyup, blur', rule: 'required' },
					{ input: '#sessionDescription', message: 'Session must contain only letters, number, space and apostrophe!', action: 'keyup', 
						 rule: function(input, commit)
						 {
							 return checkAlphanumericSpaceApostrophe(document.getElementById('sessionDescription').value);
						 } 
					},
					{
						 input: '#courseName',
						 message: 'Course Name is required!',
						 rule: function(input, commit)
						 {	
							 if(input.val()==null || input.val()=="NA") return false
							 else return true;						 
						 }
				    },
					{
						 input: '#filesTo',
						 message: 'Training session files are required!',
						 rule: function(input, commit)
						 {	
							 return input.children('option').length>0;					 
						 }
				    }
                    
				]
			}
		);	
		$("#moveright").on('click' , function()
		{
		    var obj = ($("#filesFrom option:selected"));
		    $.each(obj, function(index , item)
		    { 
		         $("#filesTo").append($(this));
		    });
		   
		});

		$("#moveleft").on('click' , function()
		{		    
		    var obj = ($("#filesTo option:selected"));    
		    $.each(obj, function(index , item)
		    { 
		         $("#filesFrom").append($(this));
		    });
		});
	});//$(document).ready
	function disableComponents(doIt)
	{
		$("#timeTableId").prop('disabled', doIt);
		$("#courseName").prop('disabled', doIt);
		
		$('#startTime').jqxDateTimeInput({disabled: doIt});
		
		$("#duration").prop('disabled', doIt);
		$("#sessionDescription").prop('disabled', doIt);
		$("#moveright").prop('disabled', doIt);
		$("#moveleft").prop('disabled', doIt);
	}
	function parseDate(d)
	{
		log(d.toString()+"|"+isNaN(Date.parse(d)));
		if(isNaN(Date.parse(d)))//ie, firefox
		{	
			var sDate=d.match(/(\d+)-(\d+)-(\d+) (\d+):(\d+)/);
			//log(sDate);
			return new Date(sDate[1]+"/"+sDate[2]+"/"+sDate[3]+" "+sDate[4]+":"+sDate[5]);
		}
		return new Date(Date.parse(d));//chrome
	}
	//To avoid all jQuery version complications, recommend using one of these really simple javascript functions
	function setSelectByValue(eID, val)//Note: element.options[i].value.indexOf("value")!=-1, which is not exactly the same
	{ //Loop through sequentially//
	  var ele=document.getElementById(eID); 
	  for(var ii=0; ii<ele.length; ii++)
	    if(ele.options[ii].value.indexOf(val)==0)//found
	    { //Found!
	      ele.options[ii].selected=true;
	      return true;
	    }
	  return false;
	}

	function setSelectByText(eID, text)
	{ //Loop through sequentially//
	  var ele=document.getElementById(eID);
	  for(var ii=0; ii<ele.length; ii++)
	    if(ele.options[ii].text==text) 
	    { //Found!
	      ele.options[ii].selected=true;
	      return true;
	    }
	  return false;
	}
	function getSelectedIndexByValue(elementId, val)
	{
		var options=document.getElementById(elementId).options;
		for(var i=0;i<options.length;i++)
			if(options[i].value==val) return i;
		return Number(-1);//not found
	}
	function getOptionValueByText(elementId, text)
	{
		var options=document.getElementById(elementId).options;
		for(var i=0;i<options.length;i++)
			if(options[i].text==text) return Number(options[i].value);
		return Number(-1);//not found
	}
	function getOptionTextByValue(elementId, value)
	{
		var options=document.getElementById(elementId).options;
		for(var i=0;i<options.length;i++)
			if(options[i].value==value) return options[i].text;
		return Number(-1);//not found
	}
	function getActionResponse(strResponse)
	{
		log("getActionResponse("+strResponse+")");
		
		$("#spinner_img").hide();
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();			
		}
		else if(strResponse.indexOf('ajax_action_course_scheduling:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');

			if(action_command=="add")
			{		  
				var eachField=strResponse.split(", ");
				var aRow={
						"timeTableId": eachField[0].split("=")[1],
						"courseNumber": eachField[1].split("=")[1],
						"startTime": eachField[2].split("=")[1],
						"endTime": eachField[3].split("=")[1],
						"duration": eachField[4].split("=")[1],
						"emailAlertTo": eachField[5].split("=")[1],
						"histRecordPath": eachField[6].split("=")[1],
						"sessionStatus": eachField[7].split("=")[1],
						"sessionDescription": eachField[8].split("=")[1],
						"submitterId": eachField[9].split("=")[1],
						"submissionTime": new Date(),
						"remarks": eachField[11].split("=")[1],						
						"valid": $("#validBox").jqxCheckBox('checked'),
						"fileIds": eachField[13].split("=")[1],	
						"courseName": $('#courseName option:selected').text()
						 };
				$('#jqxgrid').jqxGrid('addrow', null, aRow);
				
				$("#popupWindow").jqxWindow('close');
			}
			else if(action_command=="edit")
			{
				selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
				selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
				var eachField=strResponse.split(", ");
				var aRow={
						"timeTableId": eachField[0].split("=")[1],
						"courseNumber": eachField[1].split("=")[1],
						"startTime": eachField[2].split("=")[1],
						"endTime": eachField[3].split("=")[1],
						"duration": eachField[4].split("=")[1],
						"emailAlertTo": eachField[5].split("=")[1],
						"histRecordPath": eachField[6].split("=")[1],
						"sessionStatus": selectedDataRecord.sessionStatus,
						"sessionDescription": eachField[8].split("=")[1],
						"submitterId": selectedDataRecord.submitterId,
						"submissionTime": selectedDataRecord.submissionTime,
						"remarks": eachField[11].split("=")[1],						
						"valid": $("#validBox").jqxCheckBox('checked'),
						"fileIds": eachField[13].split("=")[1],	
						"courseName": $('#courseName option:selected').text()
						 };
				$('#jqxgrid').jqxGrid('updaterow', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex), aRow);
				
				$("#popupWindow").jqxWindow('close');
			}
			else if(action_command=="delete")
			{
				location.reload();
			}					
		}
		else 
		{
			$('#errorMsg').html("<span style='color:red;'>"+strResponse.substring("ajax_action_course_scheduling:".length)+"</span>");
		}
	}
	function onUserSelect(o)
	{
		log(o.id+":"+o.value);
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
						<tr><td align="left" valign="middle" style="padding-left: 5px; font-size:13pt;">
								<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Course Session Scheduling</span>
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
			                <table style='padding-top: 20px; padding-right: 10px; padding-bottom: 30px; padding-left: 10px;'>
			                	<tr>
			                		<td colspan='2'>
			                			<table style='padding-top: 20px; padding-right: 10px; padding-bottom: 30px; padding-left: 10px;'>
			                				<tr>
			                					<td width='40%' align='right'>Course Name:</td>
			                					<td align='left'>
			                						<select id="courseName" style="width: 300px;">	
						                        		<option value="NA" selected></option>														 
														<%
															for(CurriculumCurrentBean cb : courseList)	
															{
																out.print("<option value='"+cb.getCourseNumber()+"'>"+cb.getCourseName()+"</option>");																
															}
														%>
													</select>
			                					</td>
			                				</tr>
			                				<tr>
			                					<td width='40%' align='right'>About Session:</td>
			                					<td align='left'>
			                						<input type='text' id="sessionDescription" size='38' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive'/>
			                					</td>
			                				</tr>
			                				<tr>
			                					<td width='40%' align='right'>Start Time:</td>
			                					<td align='left'>
			                						<div id='startTime'></div>
			                					</td>
			                				</tr>
			                				<tr>
			                					<td width='40%' align='right'>Duration (hr):</td>
			                					<td align='left'>
			                						<select id="duration">											
														<option value="0.5" >0.5</option>
														<option value="1" >1</option>
														<option value="1.5" >1.5</option>
														<option value="2" >2</option>
														<option value="2.5" >2.5</option>	
														<option value="3" >3</option>
														<option value="3.5" >3.5</option>
														<option value="4" >4</option>
														<option value="4.5" >4.5</option>
														<option value="5" >5</option>							
													</select>
			                					</td>
			                				</tr>
			                			</table>
			                		</td>
			                	</tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr><td colspan='2' align='center' style='color: #A17B79;'>Move files from left to right vs</td></tr>
			                    <tr>
			                        <td align="right" width='50%'>Available Files&nbsp;<input id="moveright"  type="button" value="  &gt  " style='padding-left: 5px;'/></td>
			                        <td align="left" valign="top"><input id="moveleft"  type="button" value="  &lt  "/>&nbsp;Files for the session</td>
			                    </tr>
			                    <tr>
			                        <td align="right" width='50%'>
			                        	<select id="filesFrom" name="filesFrom" multiple="multiple" style='height: 150px; width: 180px;' size='10'>
									    </select>
			                        </td>
			                        <td align="left" valign="top">
			                        	<select id="filesTo" multiple="multiple" name="filesTo"  style='height: 150px; width: 180px;' size='10'>
									    </select>
			                        </td>
			                    </tr>

			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td align="right" width="50%">Valid:</td>
			                        <td align="left"><div id='validBox'></div>
			                        </td>
			                    </tr>

			                    <tr height='3'><td colspan='2'></td></tr>
			                    
			                    <tr><td colspan='2' align='center'>
			                    		<img id='spinner_img' src='images/common/spinner.gif' width=32 height=32 />
			                    		<input type='hidden' id='timeTableId' value='0'/>
			                    		<input type='hidden' id='sessionStatus' value='0'/>
			                    	</td>
			                    </tr>
			                    <tr>
			                        <td colspan='2' style="padding-top: 10px; padding-bottom: 10px;" align="center">
			                        	<input style="margin-right: 5px;" type="button" id="popupWindowSubmit" value="Submit" />
			                        	<input id="popupWindowCancel" type="button" value="Cancel" />
			                        </td>
			                    </tr>
			                    <tr><td colspan='2' align='center' id='errorMsg'></td></tr>
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