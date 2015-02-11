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
	List<HospitalBean> hospitalList=sqlDao.getHospitalList("order by hospital_id asc");
 %>
<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
		var selectedRowIndex=-1;

		var selectedDataRecord;
		var action_command;//add, edit, delete
		var data=[
			 		<%	
			 			for(int i=0;i<hospitalList.size();i++)
			 			{
			 				HospitalBean ab=hospitalList.get(i);
			 				out.print("{"); 
			 				out.print("\"hospitalId\": \""+ab.getHospitalId()+"\",");	
			 				out.print("\"hospitalName\": \""+ab.getHospitalName()+"\","); 
		 					out.print("\"country\": \""+ab.getCountry()+"\","); 
		 					out.print("\"phone\": \""+ab.getPhone()+"\",");
		 					out.print("\"creatorId\": \""+ab.getCreatorId()+"\","); 
		 					out.print("\"creationTime\": \""+ab.getCreationTime()+"\","); 
		 					out.print("\"valid\": \""+ab.isValid()+"\"");
			 				
							out.print(((i+1)==hospitalList.size() ? "}" : "},"));				
			 			}
	
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "hospitalId", type: "string" },
                { name: "hospitalName", type: "string" },
                { name: "country", type: "string" },
                { name: "phone", type: "string"},
                { name: "creatorId", type: "string" },
                { name: "creationTime", type: "date", format: 'M/d/yyyy'},
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
                    $("#hospitalName").val("");
                    $("#country").val("1");
                    $('#phone').jqxMaskedInput('clear'); 
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
	                	var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 200, y: parseInt(offset.top) + 65 } });
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    
	                    $("#hospitalName").val(selectedDataRecord.hospitalName);
	                    $("#editHospitalId").val(selectedDataRecord.hospitalId);
	                    $("#country").val(getOptionValueByText("country", selectedDataRecord.country));
	                    $('#phone').jqxMaskedInput('val', selectedDataRecord.phone); 
	                    $("#validBox").jqxCheckBox('val', selectedDataRecord.valid);
	                    	                   
	                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit");
	                    
	                    $("#popupWindow").jqxWindow('open');
                	}
                });
                
                deleteButton.click(function (event) 
                {
                	alert("Deleting a record is not allowed!");
                });
                           	
            },
            columns: 
            [
              { text: 'Hospital ID', dataField: 'hospitalId', align: 'center', width: '12%' },
              { text: 'Hospital Name', dataField: 'hospitalName', align: 'center', width: '25%' },
              { text: 'Country', dataField: 'country', align: 'center', width: '12%' },
              { text: 'Phone', dataField: 'phone', align: 'center', width: '13%' },
              { text: 'Creator ID', dataField: 'creatorId', align: 'center', width: '15%'},
              { text: 'Creation Time', dataField: 'creationTime', align: 'center', cellsformat: 'd', width: '13%' },
              { text: 'Alive', dataField: 'valid', align: 'center', columntype: 'checkbox', width: '10%' }
            ]
        });
   
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 480, height: '100%', resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
        $("#popupWindow").on('open', function () 
        {
            $('#errorMsg').html("");
            if($("#popupWindow").jqxWindow('title')=="edit")
            {
            	$("#hospitalId").hide();
            	$("#editHospitalId").show();
            }
            else
            {
            	$("#hospitalId").show();
            	$("#editHospitalId").hide();
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
		         url: "/ttt/controller?op=ajax_action_hospital_management",
		         data: 
		         {
		    	 		action: 		action_command,
		    	 		hospitalId:  	(action_command=="edit" ? document.getElementById("editHospitalId").value : document.getElementById("hospitalId").value),
		    	 		hospitalName:	document.getElementById("hospitalName").value,
		    	 		countryId:		document.getElementById("country").value,
		    	 		phone:			$('#phone').jqxMaskedInput('val'),
		    	 		alive:			$("#validBox").jqxCheckBox('checked')	    	 		
		     	 },
		     	 beforeSend: function()
		     	 {
                    $("#spinner_img").show();
                 },
		         success: getAdminHospitalActionResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 //alert(response.responseText);	
		        	 alert("Error: the requested resouce is not available");
		        	 $("#spinner_img").hide();
		         }
		      }); 
			     	
        });     
        
        /*Initialize the given components*/
        $("#spinner_img").hide();
        $("#phone").jqxMaskedInput({ mask: '(###)###-####', width: 150, height: 22  });
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
					 {
							 input: '#hospitalName',
							 message: 'The Hospital Name should be with [a-zA-Z0-9\'] and space!',
							 rule: function(input, commit)
							 {
								 document.getElementById('hospitalName').value=trim(document.getElementById('hospitalName').value);
								 if(document.getElementById('hospitalName').value.length<3) return false;
								 return checkAlphanumericSpaceApostrophe(document.getElementById('hospitalName').value);
							 }
					  },
					  {
						 input: '#phone',
						 message: 'Invalid phone number!',
						 action: 'valuechanged, blur', rule: 'phone'
					  }
				]
			}
		);		
	});//$(document).ready
	
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
	function getAdminHospitalActionResponse(strResponse)
	{
		log("getAdminHospitalActionResponse("+strResponse+")");
		
		$("#spinner_img").hide();
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();			
		}
		else if(strResponse.indexOf('ajax_action_hospital_management:')==-1)//not found
		{
			var action_command=$("#popupWindow").jqxWindow('title');

			if(action_command=="add")
			{			  
				var aRow={
						 "hospitalId":$("#hospitalId").val(),
						 "hospitalName":$("#hospitalName").val(),
						 "country":$("#country option[value='"+$("#country").val()+"']").text(),
						 "phone":$('#phone').jqxMaskedInput('val'),
						 "creatorId":"${trb.userId}",
						 "creationTime":new Date(),
						 "valid":$("#validBox").jqxCheckBox('checked')
						 };
				$('#jqxgrid').jqxGrid('addrow', null, aRow);
				
				$("#popupWindow").jqxWindow('close');
				$("#hospitalId option[value='"+$("#hospitalId").val()+"']").remove();				
			}
			else if(action_command=="edit")
			{
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
			}
			else if(action_command=="delete") //this won't be called
			{
				location.reload();
			}					
		}
		else if(strResponse.indexOf('duplicate key value')>0)//found
		{
			$('#errorMsg').html("<span style='color:red;'>There exists a duplicate key value in either Hospital Name or ID!</span>");
		}
		else 
		{
			$('#errorMsg').html("<span style='color:red;'>"+strResponse.substring("ajax_action_hospital_management:".length)+"</span>");
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
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Hospital ID Management</span>
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
			                <table style='padding-top: 30px; padding-right: 10px; padding-bottom: 30px; padding-left: 10px;'>
			                	<tr>
			                        <td align="right" width="40%">Hospital Name:</td>
			                        <td align="left">
			                        	<input type='text' id="hospitalName" size='30' maxlength="50" class=input_text style='padding-left: 2px;imemode:inactive'/>
			                        	   &nbsp;ID:
										  <select id="hospitalId">
											<%  //do error handler										
											List<String> unusedHospitalIDList=sqlDao.getNonCreatedHospitalCodeList();
											for(int i=0;i<unusedHospitalIDList.size();i++)
											{
												out.print("<option value='"+unusedHospitalIDList.get(i)+"'>"+unusedHospitalIDList.get(i)+"</option>");																
											}
										%>
										</select>
										<input type='text' id="editHospitalId" size='3' maxlength="2" class=input_text style='padding-left: 2px;imemode:inactive' readonly/>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Country:</td>
			                        <td align="left">
			                        	<select id="country">															 
										<%  //do error handler
											List<CountryBean> countryList=(List<CountryBean>)application.getAttribute("countryList");
											for(int i=0;i<countryList.size();i++)
											{
												CountryBean cb=(CountryBean)countryList.get(i);
												out.print("<option value='"+cb.getCountryId()+"'>"+cb.getCountry()+"</option>");																
											}
										%>
										</select>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="40%">Phone Number:</td>
			                        <td align="left">
			                        	<div id="phone"></div>
			                        </td>
			                    </tr>
			                    <tr height='3'><td colspan='2'></td></tr>
			                    <tr style='vertical-align: top;'>
			                        <td align="right" width="40%">Alive:</td>
			                        <td align="left"><div id='validBox'></div>
			                        </td>
			                    </tr>

			                    <tr height='10'><td colspan='2'></td></tr>
			                    
			                    <tr><td colspan='2' align='center'><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32 /></td></tr>
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