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
							out.print("\"valid\": \""+tb.isValid()+"\"");
		 					out.print("\"fileIds\": \""+tb.getFileIds()+"\",");
		 					out.print("\"courseName\": \""+tb.getCourseName()+"\",");
			 				
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
                { name: "fileIds", type: "number" },
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
                    
                    /*$("#jqxgrid").jqxGrid('clearselection');
                    $("#courseName").val("");
                    $("#lecturerId").val("");
                    $("#taId").val(""); 
                    $("#interactiveSiteViewer1Id").val("");
                    $("#interactiveSiteViewer2Id").val("");
                    $("#onewaySiteViewer1Id").val(""); 
                    $("#onewaySiteViewer2Id").val("");
                    $("#onewaySiteViewer3Id").val("");
                    $("#onewaySiteViewer4Id").val(""); 
                    $("#onewaySiteViewer5Id").val("");
                    $("#onewaySiteViewer6Id").val(""); 
                    $('#validBox').jqxCheckBox('check');
                    */
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
                	else
                	{                	
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    $("#courseName").val(selectedDataRecord.courseName);
	                    setSelectByValue("lecturerId", selectedDataRecord.lecturerId);
	                    setSelectByValue("taId", selectedDataRecord.taId); 
	                    setSelectByValue("interactiveSiteViewer1Id", selectedDataRecord.interactiveSiteViewer1Id);
	                    setSelectByValue("interactiveSiteViewer2Id", selectedDataRecord.interactiveSiteViewer2Id);
	                    setSelectByValue("onewaySiteViewer1Id", selectedDataRecord.onewaySiteViewer1Id); 
	                    setSelectByValue("onewaySiteViewer2Id", selectedDataRecord.onewaySiteViewer2Id);
	                    setSelectByValue("onewaySiteViewer3Id", selectedDataRecord.onewaySiteViewer3Id);
	                    setSelectByValue("onewaySiteViewer4Id", selectedDataRecord.onewaySiteViewer4Id); 
	                    setSelectByValue("onewaySiteViewer5Id", selectedDataRecord.onewaySiteViewer5Id);
	                    setSelectByValue("onewaySiteViewer6Id", selectedDataRecord.onewaySiteViewer6Id); 
	                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);
	                    $("#courseNumber").val(selectedDataRecord.courseNumber);//hidden
	                    
	                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit <span style='color: #2D73BB; font-size: 11px;'>(Note: your update may interrupt the current operation if in use!)</span>");
	                    var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    $("#popupWindow").jqxWindow('open');
	                    
	                    //disableComponents(true);
                	}
                });
                
                deleteButton.click(function (event) 
                {
                	alert("Deleting a record is not allowed!");
                });
                           	
            },
            columns: 
            [
              { text: 'TimeTableId', dataField: 'timeTableId', align: 'center', width: '0%' },
              { text: 'CourseNumber', dataField: 'courseNumber', align: 'center', width: '0%' },
              { text: 'Course Name', dataField: 'courseName', align: 'center', width: '25%' },
              { text: 'Session Description', dataField: 'sessionDescription', align: 'center', width: '30%'},
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
            if($("#popupWindow").jqxWindow('title')=="edit")
            {            	
            }
            else
            {
            }
        });
        $("#popupWindow").on('close', function () 
        {
            if($("#popupWindow").jqxWindow('title')=="edit")
            {
            	//disableComponents(false);
            }
            else
            {           	
            }
        });
   
        //Add, Edit, Delete
        $("#popupWindowSubmit").click(function () 
        {	//log($("#onewaySiteViewer2Id")[0].selectedIndex);
        	action_command=$("#popupWindow").jqxWindow('title');//add, edit, delete
        	var onsuccess=$("#application_form").jqxValidator('validate');
			if(!onsuccess) return;
								
			$.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/ttt/controller?op=ajax_action_course_registration",
		         data: 
		         {
		    	 		
		    	 		action: 					action_command,
		    	 		courseNumber:				$("#courseNumber").val(),
		    	 		courseName:					$("#courseName").val(),
		    	 		lecturerId:					$("#lecturerId").val().split(":")[0],
		    	 		taId:						$("#taId").val().split(":")[0],
		    	 		interactiveSiteViewer1Id:	$("#interactiveSiteViewer1Id").val().split(":")[0],
		    	 		interactiveSiteViewer2Id:	$("#interactiveSiteViewer2Id").val().split(":")[0],
		    	 		onewaySiteViewer1Id:		$("#onewaySiteViewer1Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer1Id").val().split(":")[0],
		    	 		onewaySiteViewer2Id:		$("#onewaySiteViewer2Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer2Id").val().split(":")[0],
		    	 		onewaySiteViewer3Id:		$("#onewaySiteViewer3Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer3Id").val().split(":")[0],
		    	 		onewaySiteViewer4Id:		$("#onewaySiteViewer4Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer4Id").val().split(":")[0],
		    	 		onewaySiteViewer5Id:		$("#onewaySiteViewer5Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer5Id").val().split(":")[0],
		    	 		onewaySiteViewer6Id:		$("#onewaySiteViewer6Id")[0].selectedIndex<1 ? "":$("#onewaySiteViewer6Id").val().split(":")[0],
		    	 		valid: 						$("#validBox").jqxCheckBox('checked')
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
        $("#startDate").jqxDateTimeInput({width: '200px', height: '20px', formatString: 'MMM dd yyyy hh:mm tt', value: new Date(<%=Utils.login_access_limit_date.getTime()%>), min: new Date(), allowKeyboardDelete: false});
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
					{ input: '#courseName', message: 'Seminar Name is required!', action: 'keyup, blur', rule: 'required' },
					{ input: '#courseName', message: 'Seminar Name must contain only letters, number, space and apostrophe!', action: 'keyup', 
						 rule: function(input, commit)
						 {
							 return checkAlphanumericSpaceApostrophe(document.getElementById('courseName').value);
						 } 
					},
					{
						 input: '#lecturerId',
						 message: 'Previleged person for the position is required !',
						 rule: function(input, commit)
						 {	
							 if(input.val()==null || input.val()=="NA") return false
							 else 
							 {
								 return (Number(input.val().split(":")[1])<3 ? true : false);	
							 }
						 }
				    },
				    {
						 input: '#taId',
						 message: 'Previleged person for the position is required !',
						 rule: function(input, commit)
						 {	
							 if(input.val()==null || input.val()=="NA") return false
							 else 
							 {
								 return (Number(input.val().split(":")[1])<3 ? true : false);	
							 }
						 }
				    },
				    {
						 input: '#interactiveSiteViewer1Id',
						 message: 'Previleged person for the position is required !',
						 rule: function(input, commit)
						 {	
							 if(input.val()==null || input.val()=="NA") return false
							 else 
							 {
								 return (Number(input.val().split(":")[1])<3 ? true : false);	
							 }
						 }
				    },
				    {
						 input: '#interactiveSiteViewer2Id',
						 message: 'Previleged person for the position is required !',
						 rule: function(input, commit)
						 {	
							 if(input.val()==null || input.val()=="NA") return false
							 else 
							 {
								 return (Number(input.val().split(":")[1])<3 ? true : false);	
							 }
						 }
				    }
                    
				]
			}
		);		
	});//$(document).ready
	/*function disableComponents(doIt)
	{
		$("#hospitalId").prop('disabled', doIt);
		$("#firstName").prop('disabled', doIt);
		$("#lastName").prop('disabled', doIt);
	}*/
	function parseDate(d)
	{
		//log(d.toString()+"|"+isNaN(Date.parse(d)));
		if(isNaN(Date.parse(d)))//ie, firefox
		{	
			var sDate=d.match(/(\d+)-(\d+)-(\d+)/);
			//log(sDate);
			return new Date(sDate[1]+"/"+sDate[2]+"/"+sDate[3]);
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
		else if(strResponse.indexOf('ajax_action_course_registration:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');

			if(action_command=="add")
			{		  
				var eachField=strResponse.split(", ");
				var aRow={
						"courseNumber": eachField[0].split("=")[1],
						"courseName": eachField[1].split("=")[1],
						"lecturerId": eachField[2].split("=")[1],
						"taId": eachField[3].split("=")[1],
						"interactiveSiteViewer1Id": eachField[4].split("=")[1],
						"interactiveSiteViewer2Id": eachField[5].split("=")[1],
						"onewaySiteViewer1Id": eachField[6].split("=")[1],
						"onewaySiteViewer2Id": eachField[7].split("=")[1],
						"onewaySiteViewer3Id": eachField[8].split("=")[1],
						"onewaySiteViewer4Id": eachField[9].split("=")[1],
						"onewaySiteViewer5Id": eachField[10].split("=")[1],
						"onewaySiteViewer6Id": eachField[11].split("=")[1],
						"courseDataFileNumber": eachField[12].split("=")[1],
						"courseTimeTableNumber": eachField[13].split("=")[1],
						"creatorId": eachField[14].split("=")[1],
						"creationTime": new Date(),
						"remarks": eachField[16].split("=")[1],						
						"valid": $("#validBox").jqxCheckBox('checked')
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
						"courseNumber": eachField[0].split("=")[1],
						"courseName": eachField[1].split("=")[1],
						"lecturerId": eachField[2].split("=")[1],
						"taId": eachField[3].split("=")[1],
						"interactiveSiteViewer1Id": eachField[4].split("=")[1],
						"interactiveSiteViewer2Id": eachField[5].split("=")[1],
						"onewaySiteViewer1Id": eachField[6].split("=")[1],
						"onewaySiteViewer2Id": eachField[7].split("=")[1],
						"onewaySiteViewer3Id": eachField[8].split("=")[1],
						"onewaySiteViewer4Id": eachField[9].split("=")[1],
						"onewaySiteViewer5Id": eachField[10].split("=")[1],
						"onewaySiteViewer6Id": eachField[11].split("=")[1],
						"courseDataFileNumber": selectedDataRecord.courseDataFileNumber,
						"courseTimeTableNumber": selectedDataRecord.courseTimeTableNumber,
						"creatorId": selectedDataRecord.creatorId,
						"creationTime": selectedDataRecord.creationTime,
						"remarks": eachField[16].split("=")[1],						
						"valid": $("#validBox").jqxCheckBox('checked')
						 };
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
		else if(strResponse.indexOf('id_duplication')>0)//found
		{
			$('#errorMsg').html("<span style='color:red;'>Users registered for the seminar should be unique for each position!</span>");
		}
		else if(strResponse.indexOf('duplicate key value')>0)//found
		{
			$('#errorMsg').html("<span style='color:red;'>There already exists the same semiar name!</span>");
		}
		else 
		{
			$('#errorMsg').html("<span style='color:red;'>"+strResponse.substring("ajax_action_account_management:".length)+"</span>");
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
			                        <td align="left" colspan='2'>Course Name:			                  
										 <select id="courseName" style="width: 320px;">	
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
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="left" colspan='2'>Session Description:
			                        	<input type='text' id="sessionDescription" size='38' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive'/>
			                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="left" colspan='2'><div id='startDate'></div>									
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="left" colspan='2'>Duration (hr):
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
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width='50%'>Available Files&nbsp;<input id="moveright"  type="button" value="  &gt  " style='padding-left: 5px;'/></td>
			                        <td align="left" valign="top"><input id="moveleft"  type="button" value="  &lt  "/>&nbsp;Files for the session</td>
			                    </tr>
			                    <tr>
			                        <td align="right" width='50%'>
			                        	<select id="filesFrom" name="filesFrom" multiple="multiple" style='height: 100%; width: 180px;' size='10'>
									    </select>
			                        </td>
			                        <td align="left" valign="top">
			                        	<select id="filesTo" multiple="multiple" name="filesTo"  style='height: 100%; width: 180px;' size='10'>
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
			                    
			                    <tr><td colspan='2' align='center'><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32 /><input type='hidden' id='courseNumber' value='0'/></td></tr>
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