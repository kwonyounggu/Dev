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
	List<HospitalBean> hospitalList=sqlDao.getHospitalList("where valid=true order by hospital_name asc");
	List<AllRegisteredUserBean> userList=sqlDao.getListUserTableAndHospital();
 %>
<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
		var selectedRowIndex=-1;

		var selectedDataRecord;
		var action_command;//add, edit, delete
		var data=[
			 		<%	
			 			for(int i=0;i<userList.size();i++)
			 			{
			 				AllRegisteredUserBean ab=userList.get(i);
			 				out.print("{"); 
			 				
			 				//Visible
			 				out.print("\"hospitalName\": \""+ab.getHospitalName()+"\","); 
			 				out.print("\"name\": \""+ab.getFirstName()+" "+ab.getLastName()+"\","); 
			 				out.print("\"userId\": \""+ab.getUserId()+"\","); 
			 				out.print("\"email\": \""+ab.getEmail()+"\","); 
			 				out.print("\"loginLevel\": \""+ab.getLoginLevel()+"\","); 
			 				out.print("\"accessLimitDate\": \""+ab.getAccessLimitTime()+"\","); 
			 				out.print("\"resetPassword\": \""+(ab.getResetPassword()==1 ? true: false)+"\",");
			 				
			 				//Invisible
			 				out.print("\"hospitalId\": \""+ab.getHospitalId()+"\",");	
			 				out.print("\"firstName\": \""+ab.getFirstName()+"\","); 
			 				out.print("\"lastName\": \""+ab.getLastName()+"\","); 
			 				out.print("\"practiceYear\": \""+ab.getPracticeYear()+"\","); 
			 				out.print("\"healthDiscipline\": \""+ab.getHealthDiscipline()+"\","); 
			 				out.print("\"primaryClinicalPractice\": \""+ab.getPrimayClinicalPractice()+"\","); 
			 				out.print("\"seniority\": \""+ab.getSeniority()+"\","); 
			 				
			 				//Visible
		 					out.print("\"valid\": \""+ab.isValid()+"\"");
			 				
							out.print(((i+1)==userList.size() ? "}" : "},"));				
			 			}
	
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "hospitalName", type: "string" },
                { name: "name", type: "string" },
                { name: "userId", type: "string" },
                { name: "email", type: "string"},
                { name: "loginLevel", type: "number" },
                { name: "accessLimitDate", type: "date", format: 'MM/dd/yyyy'},
                { name: "hospitalId", type: "string" },
                { name: "firstName", type: "string" },
                { name: "lastName", type: "string"},
                { name: "practiceYear", type: "string" },
                { name: "healthDiscipline", type: "string" },
                { name: "primaryClinicalPractice", type: "string"},
                { name: "seniority", type: "string" },
                { name: "resetPassword", type: "bool" },
                { name: "valid", type: "bool" }             
            ],
            addrow: function (rowid, rowdata, position, commit) {commit(true);},
            deleterow: function (rowid, commit) {commit(true); },
            updaterow: function (rowid, newdata, commit) {commit(true);},
            sortcolumn: 'hospitalId',
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
                    $("#firstName").val("");
                    $("#lastName").val("");
                    $("#emailInput").val(""); 
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
	                    $("#hospitalId").val(selectedDataRecord.hospitalId);
	                    if(Number($("#hospitalId").prop("selectedIndex"))<0)
	                    {
	                    	alert("You have to make the Hospital valid before editing this!");	       
	                    }
	                    else
	                    {
		                    $("#firstName").val(selectedDataRecord.firstName);
		                    $("#lastName").val(selectedDataRecord.lastName);
		                    $("#emailInput").val(selectedDataRecord.email);
		                    $("#loginLevel").val(selectedDataRecord.loginLevel);
		                    $('#accessLimitDate').jqxDateTimeInput('val', parseDate(selectedDataRecord.accessLimitDate));
		                    $("#practiceYear").val(selectedDataRecord.practiceYear); //log("practiceYear="+selectedDataRecord.practiceYear);
		                    $("#healthDiscipline").val(selectedDataRecord.healthDiscipline); //log("healthDiscipline="+selectedDataRecord.healthDiscipline);
		                    $("#primaryClinicalPractice").val(selectedDataRecord.primaryClinicalPractice); //log("primaryClinicalPractice="+selectedDataRecord.primaryClinicalPractice);
		                    $("#seniority").val(selectedDataRecord.seniority); log("seniority="+selectedDataRecord.seniority);
		                    $("#resetPasswordBox").jqxCheckBox('val', selectedDataRecord.resetPassword);
		                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);
		                    
		                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit");
		                    var offset = $("#jqxgrid").offset();
		                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
		                    $("#popupWindow").jqxWindow('open');
		                    disableComponents(true);
	                    }
                	}
                });
                
                deleteButton.click(function (event) 
                {
                	alert("Deleting a record is not allowed!");
                });
                           	
            },
            columns: 
            [
              { text: 'Hospital Name', dataField: 'hospitalName', align: 'center', width: '18%' },
              { text: 'Name', dataField: 'name', align: 'center', width: '15%' },
              { text: 'Login ID', dataField: 'userId', align: 'center', width: '16%' },
              { text: 'E-Mail', dataField: 'email', align: 'center', width: '19%' },
              { text: 'Level', dataField: 'loginLevel', align: 'center', width: '6%'},
              { text: 'Access Limit', dataField: 'accessLimitDate', align: 'center', cellsformat: 'd', width: '10%' },
              { text: 'Reset PWD', dataField: 'resetPassword', align: 'center', columntype: 'checkbox', width: '10%' },
              
              { text: 'Hospital ID', dataField: 'hospitalId', align: 'center', width: '0%' },
              { text: 'First Name', dataField: 'firstName', align: 'center', width: '0%' },
              { text: 'Last Name', dataField: 'lastName', align: 'center', width: '0%' },
              { text: 'Practive Year', dataField: 'practiceYear', align: 'center', width: '0%'},
              { text: 'Health Discipline', dataField: 'healthDiscipline', align: 'center', width: '0%' },
              { text: 'Primary Clinical Practice', dataField: 'primaryClinicalPractice', align: 'center', width: '0%' },
              { text: 'Seniority', dataField: 'seniority', align: 'center', width: '0%' },

              { text: 'Valid', dataField: 'valid', align: 'center', columntype: 'checkbox', width: '6%' }
            ]
        });
   
        //Invisible columns
        $("#jqxgrid").jqxGrid('hidecolumn', 'hospitalId');
        $("#jqxgrid").jqxGrid('hidecolumn', 'firstName');
        $("#jqxgrid").jqxGrid('hidecolumn', 'lastName');
        $("#jqxgrid").jqxGrid('hidecolumn', 'practiceYear');
        $("#jqxgrid").jqxGrid('hidecolumn', 'healthDiscipline');
        $("#jqxgrid").jqxGrid('hidecolumn', 'primaryClinicalPractice');
        $("#jqxgrid").jqxGrid('hidecolumn', 'seniority');
        
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 480, height: '100%', resizable: false,  isModal: true, autoOpen: false, showCloseButton: true, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
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
        	var onsuccess=$("#application_form").jqxValidator('validate');
			if(!onsuccess) return;
									
			$.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/ttt/controller?op=ajax_action_account_management",
		         data: 
		         {
		    	 		action: 			action_command,
		    	 		hospitalId:  		document.getElementById("hospitalId").value,
		    	 		firstName:			document.getElementById("firstName").value,
		    	 		lastName:			document.getElementById("lastName").value,
		    	 		emailId:				document.getElementById("emailInput").value.toLowerCase(),
		    	 		loginLevel:			document.getElementById("loginLevel").value,
		    	 		accessLimitDate:	Date.parse($("#accessLimitDate").jqxDateTimeInput('getText')),
		    	 		practiceYear:		document.getElementById("practiceYear").value,
		    	 		healthDiscipline:	document.getElementById("healthDiscipline").value,
		    	 		primaryClinicalPractice: document.getElementById("primaryClinicalPractice").value,
		    	 		seniority:			document.getElementById("seniority").value,	
		    	 		resetPassword:		$("#resetPasswordBox").jqxCheckBox('checked'),
		    	 		validRecord:		$("#validBox").jqxCheckBox('checked')	    	 		
		     	 },
		     	 beforeSend: function()
		     	 {
                    $("#spinner_img").show();
                 },
		         success: getAdminAccountActionResponse,
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
        $("#accessLimitDate").jqxDateTimeInput({width: '110px', height: '20px', formatString: 'MMM dd yyyy', value: new Date(<%=Utils.login_access_limit_date.getTime()%>), min: new Date(), allowKeyboardDelete: false});
        $("#resetPasswordBox").jqxCheckBox({ width: 120, height: 25, checked: true, locked: false });
        $("#validBox").jqxCheckBox({ width: 120, height: 25, checked: true });
        
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
					{ input: '#firstName', message: 'First Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#firstName', message: 'Your first name must contain only letters!', action: 'keyup', 
						 rule: function(input, commit)
						 {
							 return isAlphabet(document.getElementById('firstName'));
						 } 
					},
                    { input: '#firstName', message: 'Your first name must be between 3 and 15 characters!', action: 'keyup', rule: 'length=3,15' },
                    { input: '#lastName', message: 'Last Name is required!', action: 'keyup, blur', rule: 'required' },
                    { input: '#lastName', message: 'Your last name must contain only letters!', action: 'keyup', 
                    	rule: function(input, commit)
						 {
							 return isAlphabet(document.getElementById('lastName'));
						 }  
                    },
                    { input: '#lastName', message: 'Your last name must be between 3 and 15 characters!', action: 'keyup', rule: 'length=3,15' },
					{ input: '#emailInput', message: 'E-mail is required!', action: 'keyup, blur', rule: 'required' },
	                { input: '#emailInput', message: 'Invalid e-mail!', action: 'keyup', rule: 'email' }
				]
			}
		);		
	});//$(document).ready
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
	function getAdminAccountActionResponse(strResponse)
	{
		log("getAdminAccountActionResponse("+strResponse+")");
		
		$("#spinner_img").hide();
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();			
		}
		else if(strResponse.indexOf('ajax_action_account_management:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');

			if(action_command=="add")
			{			  
				var aRow={
						 "hospitalName": $("#hospitalId option[value='"+$("#hospitalId").val()+"']").text(),
						 "name":capitaliseFirstLetter($('#firstName').val())+" "+capitaliseFirstLetter($('#lastName').val()),
						 "userId":$('#firstName').val().toLowerCase()+"."+$('#lastName').val().toLowerCase()+"."+$("#hospitalId").val(),
						 "email":$('#emailInput').val().toLowerCase(),
						 "loginLevel":$("#loginLevel").val(),
						 "accessLimitDate":$("#accessLimitDate").jqxDateTimeInput('getDate'),
						 "hospitalId":$("#hospitalId").val(),
						 "firstName":$('#firstName').val(),
						 "lastName":$('#lastName').val(),
						 "practiceYear":$('#practiceYear').val(),
						 "healthDiscipline":$('#healthDiscipline').val(),
						 "primaryClinicalPractice":$('#primaryClinicalPractice').val(),
						 "seniority":$('#seniority').val(),
						 "resetPassword":$("#resetPasswordBox").jqxCheckBox('checked'),
						 "valid":$("#validBox").jqxCheckBox('checked')
						 };
				$('#jqxgrid').jqxGrid('addrow', null, aRow);
				
				$("#popupWindow").jqxWindow('close');				
			}
			else if(action_command=="edit")
			{
				selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
				selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
				var aRow={
						 "hospitalName": $("#hospitalId option[value='"+$("#hospitalId").val()+"']").text(),
						 "name":capitaliseFirstLetter($('#firstName').val())+" "+capitaliseFirstLetter($('#lastName').val()),
						 "userId":$('#firstName').val().toLowerCase()+"."+$('#lastName').val().toLowerCase()+"."+$("#hospitalId").val(),
						 "email":$('#emailInput').val().toLowerCase(),
						 "loginLevel":$("#loginLevel").val(),
						 "accessLimitDate":$("#accessLimitDate").jqxDateTimeInput('getDate'),
						 "hospitalId":$("#hospitalId").val(),
						 "firstName":$('#firstName').val(),
						 "lastName":$('#lastName').val(),
						 "practiceYear":$('#practiceYear').val(),
						 "healthDiscipline":$('#healthDiscipline').val(),
						 "primaryClinicalPractice":$('#primaryClinicalPractice').val(),
						 "seniority":$('#seniority').val(),
						 "resetPassword":$("#resetPasswordBox").jqxCheckBox('checked'),
						 "valid":$("#validBox").jqxCheckBox('checked')
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
		else if(strResponse.indexOf('duplicate key value')>0)//found
		{
			$('#errorMsg').html("<span style='color:red;'>There exists a duplicate key value not allowed with the same User Name in the hopital!</span>");
		}
		else 
		{
			$('#errorMsg').html("<span style='color:red;'>"+strResponse.substring("ajax_action_account_management:".length)+"</span>");
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
						<tr><td align="left" valign="middle" style="padding-left: 5px; font-size:13pt;">
								<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Login Account Management</span>
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
			                        <td align="right" width="40%">Hospital Name:</td>
			                        <td align="left">			                  
										  <select id="hospitalId" style="width: 280px">
											<%  //do error handler										
											for(HospitalBean hb : hospitalList)
											{
												out.print("<option value='"+hb.getHospitalId()+"'>"+hb.getHospitalName()+"</option>");																
											}
										%>
										</select>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">First Name:</td>
			                        <td align="left">
			                        	<input type='text' id="firstName" size='30' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive'/>			                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Last Name:</td>
			                        <td align="left">
			                        	<input type='text' id="lastName" size='30' maxlength="80" class=input_text style='padding-left: 2px;imemode:inactive'/>			                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">E-Mail:</td>
			                        <td align="left">
			                        	<input type='text' id="emailInput" size=34 value='' maxlength="255" class=input_text style='padding-left: 2px;imemode:inactive'/>			                        	
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr><td align="right" width="40%">Login Level:</td>
			                    	<td align="left">
										<select id="loginLevel">															 
											<%
												List<AllLoginLevelBean> loginLievelList=(List<AllLoginLevelBean>)application.getAttribute("tttloginLevelList");
												for(AllLoginLevelBean cb : loginLievelList)	
												{
													out.print("<option value='"+cb.getLoginLevel()+"'>"+cb.getLoginLevelDescription()+"</option>");																
												}
											%>
										</select>
									</td>			
								</tr>
								<tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Access Limit Date:</td>
			                        <td align="left">
			                        	<div id="accessLimitDate"/>
			                        </td>
			                    </tr>								
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Practice Year:</td>
			                        <td align="left">
			                        	<select id="practiceYear">
											<option value="<0" >&lt;0</option>
											<option value="0-2" >0-2</option>
											<option value="3-5" >3-5</option>
											<option value="6-10" >6-10</option>
											<option value=">10" >&gt;10</option>								
										</select>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Health Discipline:</td>
			                        <td align="left">
			                        	<select id="healthDiscipline">
											<option value="Pharmacy" >Pharmacy</option>
											<option value="Pharmacology" >Pharmacology</option>
											<option value="Nursing" >Nursing</option>
											<option value="Respiratory therapy" >Respiratory therapy</option>
											<option value="Medicine (physician)" >Medicine (physician)</option>	
											<option value="Medical record abstractor" >Medical record abstractor</option>	
											<option value="Other (outside healthcare profession)" >Other (outside healthcare profession)</option>								
										</select>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Primary Clinical Practice:</td>
			                        <td align="left">
			                        	<select id="primaryClinicalPractice">
											<option value="Adult" >Adult</option>
											<option value="<18 years paediatric" ><18 years paediatric</option>
											<option value="Mixed adult-geriatric" >Mixed adult-geriatric</option>
											<option value="Adult-paediatric" >Adult-paediatric</option>
										</select>&nbsp;(not in practice)
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Seniority:</td>
			                        <td align="left">
			                        	<select id="seniority">
											<option value="0" >0 (Lowest junior level)</option>
											<option value="1" >1</option>
											<option value="2" >2</option>
											<option value="3" >3</option>
											<option value="4" >4</option>
											<option value="5" >5 (Lowest senior level)</option>	
											<option value="6" >6</option>
											<option value="7" >7</option>
											<option value="8" >8</option>
											<option value="9" >9</option>
											<option value="10" >10 (Highest senior level)</option>							
										</select>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td align="right" width="40%">Reset Password:</td>
			                        <td align="left"><div id='resetPasswordBox'></div>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td align="right" width="40%">Valid:</td>
			                        <td align="left"><div id='validBox'></div>
			                        </td>
			                    </tr>

			                    <tr height='10'><td colspan='2'></td></tr>
			                    
			                    <tr><td colspan='2' align='center'><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32 /></td></tr>
			                    <tr>
			                        <td colspan='2' style="padding-top: 10px; padding-bottom: 10px;" align="center">
			                        	<input style="margin-right: 5px;" type="button" id="popupWindowSubmit" value="Submit" />
			                        	<input id="popupWindowCancel" type="button" value="Cancel" /></td>
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