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
	List<FileLibraryBean> fileList=sqlDao.getFileList("order by file_name_formal asc");
 %>
<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
		var selectedRowIndex=-1;

		var selectedDataRecord;
		var action_command;//add, edit, delete
		var data=[
			 		<%	
			 			for(int i=0;i<fileList.size();i++)
			 			{
			 				FileLibraryBean ab=fileList.get(i);
			 				out.print("{"); 
			 				
			 				//Visible
			 				out.print("\"fileNameFormal\": \""+ab.getFileNameFormal()+"\","); 
			 				out.print("\"fileType\": \""+ab.getFileType()+"\","); 
			 				out.print("\"description\": \""+ab.getDescription()+"\","); 
			 				out.print("\"submitterId\": \""+ab.getSubmitterId()+"\","); 
			 				out.print("\"submissionTime\": \""+ab.getSubmissionTime()+"\","); 
			 				out.print("\"fileSize\": \""+ab.getFileSize()+"\","); 
			 				
			 				//Invisible
			 				out.print("\"fileId\": \""+ab.getFileId()+"\",");	
			 				out.print("\"fileLocationPath\": \""+ab.getFileLocationPath()+"\","); 
			 				out.print("\"fileVersion\": \""+ab.getFileVersion()+"\","); 
			 				out.print("\"fileNameSubmitted\": \""+ab.getFileNameSubmitted()+"\","); 
			 				out.print("\"fileNameGenerated\": \""+ab.getFileNameGenerated()+"\","); 
			 				out.print("\"remarks\": \""+ab.getRemarks()+"\","); 
			 				
			 				//Visible
		 					out.print("\"valid\": \""+ab.isValid()+"\"");
			 				
							out.print(((i+1)==fileList.size() ? "}" : "},"));				
			 			}
	
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "fileNameFormal", type: "string" },
                { name: "fileType", type: "string" },
                { name: "description", type: "string" },
                { name: "submitterId", type: "string"},
                { name: "submissionTime", type: "date", format: 'MM/dd/yyyy'},
                { name: "fileSize", type: "number"},
                { name: "fileId", type: "number" },
                { name: "fileLocationPath", type: "string" },
                { name: "fileVersion", type: "number"},
                { name: "fileNameSubmitted", type: "string" },
                { name: "fileNameGenerated", type: "string" },
                { name: "remarks", type: "string"},
                { name: "valid", type: "bool" }             
            ],
            addrow: function (rowid, rowdata, position, commit) {commit(true);},
            deleterow: function (rowid, commit) {commit(true); },
            updaterow: function (rowid, newdata, commit) {commit(true);},
            sortcolumn: 'fileNameFormal',
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
                    $("#fileName").val("");
                    $("#descriptionInput").val("");
                    $("#fileInputId").val(""); 
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
                	else
                	{                	
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    $("#fileName").val(selectedDataRecord.fileNameFormal);
	                    $("#fileType").val(selectedDataRecord.fileType);
	                    $("#descriptionInput").val(selectedDataRecord.description);
	                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);
	                    		        	    	
	                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit");
	                    var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    $("#popupWindow").jqxWindow('open');
                	}
                });
                
                deleteButton.click(function (event) 
                {	//this is required
                	alert("Deleting a record is not allowed!");
                	
                });
                           	
            },
            columns: 
            [
              { text: 'File Name', dataField: 'fileNameFormal', align: 'center', width: '20%' },
              { text: 'Type', dataField: 'fileType', align: 'center', width: '6%' },
              { text: 'Description', dataField: 'description', align: 'center', width: '30%' },
              { text: 'Submitter ID', dataField: 'submitterId', align: 'center', width: '14%' },
              { text: 'Uploaded at', dataField: 'submissionTime', align: 'center', cellsformat: 'd', width: '10%'},
              { text: 'File Size', dataField: 'fileSize', align: 'center',  width: '10%' },

              { text: 'File ID', dataField: 'fileId', align: 'center', width: '0%' },
              { text: 'File Location Path', dataField: 'fileLocationPath', align: 'center', width: '0%' },
              { text: 'File Version', dataField: 'fileVersion', align: 'center', width: '0%' },
              { text: 'File Name Submitted', dataField: 'fileNameSubmitted', align: 'center', width: '0%'},
              { text: 'File Name Generated', dataField: 'fileNameGenerated', align: 'center', width: '0%' },
              { text: 'Remarks', dataField: 'remarks', align: 'center', width: '0%' },

              { text: 'Valid', dataField: 'valid', align: 'center', columntype: 'checkbox', width: '10%' }
            ]
        });
   
        //Invisible columns
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileId');
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileLocationPath');
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileVersion');
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileNameSubmitted');
        $("#jqxgrid").jqxGrid('hidecolumn', 'fileNameGenerated');
        $("#jqxgrid").jqxGrid('hidecolumn', 'remarks');
        
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 480, height: '100%', resizable: false,  isModal: true, autoOpen: false, showCloseButton: true, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
        $("#popupWindow").on('open', function () 
        {
            $('#errorMsg').html("");
            $("#fileuploadPercent").html("(0%) ");
            $("#ajaxPercent").html("(0%) ");
            
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
            	disableComponents(false);
            }
            else
            {           	
            }
        });
   
        //Add, Edit, Delete
        $("#popupWindowSubmit").click(function () 
        {	//$("#jqxgrid").jqxGrid('showloadelement');$("#jqxgrid").jqxGrid('hideloadelement');
        	action_command=$("#popupWindow").jqxWindow('title');//add, edit, delete
        	var onsuccess=$("#fileupload_form").jqxValidator('validate');
			if(!onsuccess) return;
	  
			var formdata = new FormData();//html5 and ie10
	        formdata.append("action", action_command);
	        formdata.append("fileName", trim($("#fileName").val()));
	        formdata.append("fileType", $("#fileType").val());
	        formdata.append("description", trim($("#descriptionInput").val()));
	        formdata.append("valid", $("#validBox").jqxCheckBox('checked'));
	        formdata.append("docFile", $("#fileInputId")[0].files[0]);
	        
	        httpRequestFileUpload("/ttt/file_upload",formdata, "ajaxFileUploadDocResponse");
	        
        });     
        
        /**********************************************************************************/
        /*** Initialize the given components **********************************************/
        /**********************************************************************************/
        $("#spinner").hide();

        $("#validBox").jqxCheckBox({ width: 120, height: 25, checked: true });
        
        /*Validate the given form*/
		$("#fileupload_form").jqxValidator
		(
			{
				onError: function()
				{
					log("You have not filled the form correctly!");
				},
				rules:
				[	
					{ input: '#fileName', message: 'Descriptive File Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#fileName', message: 'File name must contain only letters, number and space!', action: 'keyup', 
						 rule: function(input, commit)
						 {
							 return checkAlphanumericSpace(document.getElementById('fileName').value);
						 } 
					},
                    { input: '#fileName', message: 'File name must be between 5 and 45 characters!', action: 'keyup', rule: 'length=5,45' },
                    
                    { input: '#descriptionInput', message: 'File description is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#descriptionInput', message: 'File description must contain only letters, number, space and apostrophe!', action: 'keyup', 
                    	rule: function(input, commit)
						 {
							 return checkAlphanumericSpaceApostrophe(document.getElementById('descriptionInput').value);
						 }  
                    },
                    { input: '#descriptionInput', message: 'Description must be between 8 and 80 characters!', action: 'keyup', rule: 'length=8,80' },
                    
                    { input: '#fileInputId', message: 'File to upload is required!', action: 'keyup, blur', 
                    	rule: function(input, commit)
						 {
                    		return ($("#popupWindow").jqxWindow('title')!="add" && input.val().length<=0) ? true : input.val().length>0;
						 } 
                    },
                    { input: '#fileInputId', message: 'File extention must match with the selected file type!', action: 'keyup', 
                    	rule: function(input, commit)
						 {
							 return ($("#popupWindow").jqxWindow('title')!="add" && input.val().length<=0) ? true : checkFileExtension(input.val());
						 }  
                    }
				]
			}
		);		
		
	});//$(document).ready
	function checkFileExtension(filename) 
	{
		var file_extension=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename) : undefined;
		var retVal=false;
		if(file_extension!=undefined)
		{
			var ext = filename.substr(filename.lastIndexOf('.') + 1);
			log("File ext="+ext);
			switch(ext.toLowerCase())
			{
				case "gif":
				case "jpg":
				case "jpeg":
				case "png":
				case "tif": retVal=($("#fileType").val()==="image"); break;
				case "htm":
				case "html": retVal=($("#fileType").val()==="html"); break;
				case "swf": retVal=($("#fileType").val()==="swf"); break;
				case "txt": retVal=($("#fileType").val()==="txt"); break;
				case "flv":
				case "f4v":
				case "mp4": retVal=($("#fileType").val()==="video"); log(" video retVal="+retVal);  break;
				default: break;
			}
		}
			
		return retVal;
	}
	function ajaxFileUploadDocResponse(strResponse)
	{
		log("ajaxFileUploadDocResponse("+strResponse+")");

		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();			
		}
		else if(strResponse.indexOf('ajax_action_file_upload:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');

			if(action_command=="add")
			{	
				var eachField=strResponse.split(", ");
				
				var aRow={
						 "fileId":eachField[0].split("=")[1],
						 "fileNameFormal":eachField[1].split("=")[1],
						 "description":eachField[2].split("=")[1],
						 "fileType":eachField[3].split("=")[1],
						 "fileLocationPath":eachField[4].split("=")[1],
						 "fileVersion":eachField[5].split("=")[1],
						 "fileNameSubmitted":eachField[6].split("=")[1],
						 "fileNameGenerated":eachField[7].split("=")[1],
						 "submitterId":eachField[8].split("=")[1],
						 "submissionTime":new Date(),
						 "fileSize":eachField[10].split("=")[1],
						 "remarks":eachField[11].split("=")[1],
						 "valid":$("#validBox").jqxCheckBox('checked')
						 };
				$('#jqxgrid').jqxGrid('addrow', null, aRow);
				
				$("#popupWindow").jqxWindow('close');				
			}
			else if(action_command=="edit")
			{
				/*
				selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
				selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
				var aRow={
						 "hospitalId":$("#editHospitalId").val(),
						 "hospitalName":$("#hospitalName").val(),
						 "country":$("#country option[value='"+$("#country").val()+"']").text(),
						 "phone":$('#phone').jqxMaskedInput('val'),
						 "creatorId":selectedDataRecord.creatorId,
						 "creationTime":selectedDataRecord.creationTime,
						 "valid":$("#validBox").jqxCheckBox('checked')
						 };			
				$('#jqxgrid').jqxGrid('updaterow', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex), aRow);
				$("#popupWindow").jqxWindow('close');
				*/
			}
			else if(action_command=="delete") //this won't be called
			{
				location.reload();
			}					
		}
		else if(strResponse.indexOf('duplicate key value')>0)//found
		{
			$('#errorMsg').html("<span style='color:red;'>There exists a duplicate key value in the given file name!</span>");
		}
		else 
		{
			$('#errorMsg').html("<span style='color:red;'>"+strResponse.substring("ajax_action_file_upload:".length)+"</span>");
		}
	}
	function disableComponents(doIt)
	{
		$("#hospitalId").prop('disabled', doIt);
		$("#firstName").prop('disabled', doIt);
		$("#lastName").prop('disabled', doIt);
	}
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
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;File Upload</span>
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
			            	<form id='fileupload_form' action='./'>
			                <table style='padding-top: 20px; padding-right: 10px; padding-bottom: 30px; padding-left: 10px;'>
			                	
			                    <tr>
			                        <td align="right" width="40%">Descriptive File Name:</td>
			                        <td align="left">
			                        	<input type='text' id="fileName" size='30' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive'/>			                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">File Type:</td>
			                        <td align="left">
			                        	<select id="fileType">
											<option value="html" >Html (htm, html)</option>
											<option value="image" >Image (gif, jpg, png, tif)</option>
											<option value="swf" >Swf (Converted from Pdf)</option>
											<option value="txt" >Text (txt)</option>
											<option value="video" >Video (flv, f4v, mp4)</option>								
										</select>		                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Description:</td>
			                        <td align="left">
			                        	<input type='text' id="descriptionInput" size=34 value='' maxlength="255" class=input_text style='padding-left: 2px;imemode:inactive'/>			                        	
			                        </td>
			                    </tr>

			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td align="right" width="40%">Valid:</td>
			                        <td align="left"><div id='validBox'></div>
			                        </td>
			                    </tr>
			                    
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td colspan='2' align='center'>
			                        File:<span id='fileuploadPercent'>(0%)&nbsp;</span><input type='file' id="fileInputId" name="fileInputName" size=40 value='' maxlength="256"  style='imemode:inactive'/>
			                        </td>
			                    </tr>

			                    <tr height='10'><td colspan='2'></td></tr>
			                    
			                    <tr><td colspan='2' align='center' style='vertical-align:middle;' id='spinner'><img id='spinner_img' style='vertical-align:middle;' src='images/common/spinner.gif' width=32 height=32 /><span id='ajaxPercent' style='vertical-align:middle;'>(0%)</span></td></tr>
			                    
			                    <tr><td colspan='2' align='left' id='errorMsg'></td></tr>
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