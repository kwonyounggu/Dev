<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>

<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %> 

<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
 	AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
 	
 	List<AllItemReviewerBean> reviewersItemsList=(List<AllItemReviewerBean>)request.getAttribute("reviewers_to_items_list");
 	List<CarmDeMX> itemList=csqlDao.getCarmDeMXList("order by carm_id");//change this if this is EXEMPLAR

 	List<AllRegisteredUserBean> reviewerList=(List<AllRegisteredUserBean>)csqlDao.getListFromRegisteredUserTable("where app_id="+crb.getApp_id()+" and login_level=4 order by user_id asc;");
 	List<AllRegisteredUserBean> mediatorList=(List<AllRegisteredUserBean>)csqlDao.getListFromRegisteredUserTable("where app_id="+crb.getApp_id()+" and login_level=3 order by user_id asc;");
 	List<AllReviewGroupBean> groupList=csqlDao.getListFromAllReviewGroupTable("where app_id="+crb.getApp_id());
 	//int num_groups=Integer.parseInt(csqlDao.getGenericString("select number_of_groups from all_application where app_id="+crb.getApp_id()));
 	AllReviewGroupBean first_gb=null;
 	
 	for(CarmDeMX cb : itemList)
 		cb.setCurrent_reviewers_per_item((int)csqlDao.getGenericLong("SELECT count(item_id) FROM all_item_reviewer_mx where item_id="+cb.getCarm_id()));
 %>
<%!
//About assignment type, in Group the assignment type=by_group, assigned people (in by_group) and later in Group the assignemtn type=by_item, then in edit can you accept the same person with different assignment type
public String assignmentType(String a)
{
	if(a.equals("by_item")) return "By Item";
	return "By Group";
}
public String truncateString(String a)
{
	if(a.length()>26) return a.substring(0, 23)+"...";
	return a;
}
%>

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
								<!--<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Edit Clinical Summary</span>
							--></td>
							<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
								<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Map Event to Reviewer
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
			            	<form id='reviewer_mapping_form' action='./'>
			                <table>
			                	<tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="35%">Group Name:</td>
			                        <td align="left">
			                        	<select id="group_name" onchange="onChangeGroupName(this.value);">	
			                        	<%			                        		
			                        		for(int i=0;i<groupList.size();i++)
											{
			                        			AllReviewGroupBean gb=groupList.get(i);
			                        			if(i==0)
			                        			{
													out.print("<option value='"+gb.getGroup_id()+"' selected>"+gb.getGroup_name()+"</option>");
													first_gb=gb;//selected gb
			                        			}
			                        			else
			                        				out.print("<option value='"+gb.getGroup_id()+"' >"+gb.getGroup_name()+"</option>");
											}							 
										%>				 												 
										</select>
			                        </td>
			                    </tr>
			                    <tr style='display:none;'><!-- do not display until it is needed later -->
			                        <td align="right" width="35%">Assignment Type:</td>
			                        <td align="left">
			                        	<input type='hidden' id='group_assignment_type' value='<%=first_gb.getReviewer_assignment_type()%>'/><%=assignmentType(first_gb.getReviewer_assignment_type()) %>&nbsp;(in Group Level)
			                        </td>
			                    </tr>
			                    <tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="35%">Item ID:</td>
			                        <td align="left">
			                        	<select id="item_id">		
										  <%
										  	for(CarmDeMX cb : itemList)												
													out.print("<option value='"+cb.getCarm_id()+"'>"+cb.getCarm_id()+" - assigned ("+cb.getCurrent_reviewers_per_item()+"/"+first_gb.getReviewers_per_item()+")</option>");
										  %>													 
										</select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="35%">Reviewer ID:</td>
			                        <td align="left">
			                        	<select id="reviewer_id">
											<%
												for(AllRegisteredUserBean ab : reviewerList)
													//if(ab.getItem_assignment_type().equals(first_gb.getReviewer_assignment_type()))
														//out.print("<option value='"+ab.getUser_id()+"'>"+ab.getUser_id()+" ("+assignmentType(ab.getItem_assignment_type())+")</option>"); <!-- do not display until it is needed later -->
														out.print("<option value='"+ab.getUser_id()+"' title='"+ab.getUser_id()+"'>"+truncateString(ab.getUser_id())+"</option>");
											%>
										</select>
										<input type='hidden' id='reviewer_id_hidden' value='none.none.04'/>
										&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_admin_create_login_account %>" style="color: #CB1A1A;">New</a>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="35%">Mediator ID:</td>
			                        <td align="left">
			                        	<select id="mediator_id">
											<%
												for(AllRegisteredUserBean ab : mediatorList)
													//if(ab.getItem_assignment_type().equals(first_gb.getReviewer_assignment_type()))
														//out.print("<option value='"+ab.getUser_id()+"'>"+ab.getUser_id()+" ("+assignmentType(ab.getItem_assignment_type())+")</option>"); <!-- do not display until it is needed later -->
														out.print("<option value='"+ab.getUser_id()+"' title='"+ab.getUser_id()+"'>"+truncateString(ab.getUser_id())+"</option>");
											%>
										</select>
										&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_admin_create_login_account %>" style="color: #CB1A1A;">New</a>
			                        </td>
			                    </tr>
			                    <tr height='15'><td colspan='2'></td></tr>
			                    <tr>
			                        <td align="right" width="35%">Confirmatory:</td>
			                        <td align="left">
			                        		<input type='radio' id='confirmatoryNo' name='confirmatoryRadio' value='false' <%=first_gb.isConfirmatory()? "":"checked" %> >No</input>
			                        		<input type='radio' id='confirmatoryYes' name='confirmatoryRadio' value='true' <%=first_gb.isConfirmatory()? "checked":"" %> >Yes</input>			                        		                       			
			                        </td>
			                    </tr>
			                    <tr>
			                        <td align="right" width="35%">Time-Out:</td>
			                        <td align="left"> <div id='jqx_timeout_date'/>										  
			                        </td>
			                    </tr>
			                    <tr><td colspan='2' align='center'></td></tr>
			                    <tr>
			                        <td colspan='2' style="padding-top: 10px;" align="center">
			                        	<input style="margin-right: 5px;" type="button" id="popupWindowSubmit" value="Submit" />
			                        	<input id="popupWindowCancel" type="button" value="Cancel" />			                        	
			                        </td>
			                    </tr>
			                    <tr height='45'><td colspan='2' align="center"><img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden"/></td></tr>
			                </table>
			                </form>
			            </div>
			       </div>
										
				</td>
			</tr>
			<tr><td colspan="4" height="10" ></td></tr>
			
		</table>
 	</td>
	</tr>
</table>  

<script language="Javascript" type="text/javascript">
/*
 * Note: autofill = confirmatory in group level and point level
 */
	$(document).ready(function () 
	{
		var selectedRowIndex=-1;
		//var appId=-1;
		var selectedDataRecord;
		var action_command;//add, edit, delete
		
		$("#jqx_timeout_date").jqxDateTimeInput({width: '110px', height: '20px', formatString: 'dd MMM yyyy', allowKeyboardDelete: false});
		<%
			out.print("$('#jqx_timeout_date').jqxDateTimeInput('val',new Date("+first_gb.getTime_out().getTime()+"));");
		%>
		
		var data=[
			 		<%	
			 			
			 			for(int i=0;i<reviewersItemsList.size();i++)
			 			{
			 				AllItemReviewerBean cb=reviewersItemsList.get(i);
			 				out.print("{"); 
			 				out.print("\"group_name\": \""+cb.getGroup_name()+"\",");	
			 				out.print("\"item_id\": "+cb.getItem_id()+","); 
		 					out.print("\"reviewer_id\": \""+cb.getReviewer_id()+"\","); 
		 					out.print("\"review_time_out\": \""+cb.getTime_out()+"\",");
		 					out.print("\"reviewer_action_status\": \""+cb.getReviewer_action_status()+"\","); 
		 					out.print("\"mediator_id\": \""+cb.getMediator_id()+"\",");
		 					
		 					out.print("\"group_id\": "+cb.getGroup_id()+",");
		 					out.print("\"review_confirmatory\": "+cb.isConfirmatory()+",");
		 					out.print("\"group_time_out\": \""+cb.getGroup_time_out()+"\","); 
		 					out.print("\"group_reviewers_per_item\": "+cb.getGroup_reviewers_per_item()+",");
		 					out.print("\"group_reviewer_assignment_type\": \""+cb.getGroup_reviewer_assignment_type()+"\",");
		 					out.print("\"group_confirmatory\": "+cb.isGroup_confirmatory()+",");
		 					out.print("\"group_disagreement_process\": \""+cb.getGroup_disagreement_process()+"\""); 
			 				
							out.print(((i+1)==reviewersItemsList.size() ? "}" : "},"));				
			 			}
						
			 		%>
			 	  ];
		
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "group_name", type: "string" },
                { name: "item_id", type: "number" },
                { name: "reviewer_id", type: "string" },
                { name: "review_time_out", type: "date", format: 'MM/dd/yyyy' },
                { name: "reviewer_action_status", type: "string" },
                { name: "mediator_id", type: "string" },
                { name: "group_id", type: "number" },
                { name: "review_confirmatory", type: "bool" },
                { name: "group_time_out", type: "date", format: 'MM/dd/yyyy' },
                { name: "group_reviewers_per_item", type: "number" },
                { name: "group_reviewer_assignment_type", type: "string" },
                { name: "group_confirmatory", type: "bool" },
                { name: "group_disagreement_process", type: "string" }
                
                
            ],
            sortcolumn: 'group_name',
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
                    $("#popupWindow").jqxWindow({title: 'add', position: { x: parseInt(offset.left) + 260, y: parseInt(offset.top) + 65 } });
                    $("#popupHeader").html("<img src='images/common/plus_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Add");
                    
                    //$("#jqxgrid").jqxGrid('clearselection');                    
                    
                    $("#group_name").prop("selectedIndex", 0);
                    $("#item_id").prop("selectedIndex", 0);
                    $("#reviewer_id").prop("selectedIndex", 0);
                    $("#mediator_id").prop("selectedIndex", 0);
                    setRadioValue("confirmatoryRadio", ""+<%=first_gb.isConfirmatory() %>+"");
                    $('#jqx_timeout_date').jqxDateTimeInput('val',new Date(<%=first_gb.getTime_out().getTime()%>));
                                       
                    $("#popupWindow").jqxWindow('open');
                    disableComponents(false, false, false, false, false, false, false);
                });
                editButton.click(function (event) 
                {     
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in editButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		//jAlert('You should select a row to edit!', 'Warning');
                		alert('You should select a row to edit!');
                	}
                	else
                	{
                		//This edition is possible if time-out is not over.
                		//It won't allow to go ahead via validation of time-out if the time-out is over.
                		//The time-out won't go over the group-timeout in validation.
                		var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'edit', position: { x: parseInt(offset.left) + 260, y: parseInt(offset.top) + 65 } });
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    
	                    if(selectedDataRecord.reviewer_action_status.indexOf("_NOT_EDITABLE")==-1) //check if the record is timed-out
	                    {
		                    $("#group_name").val(selectedDataRecord.group_id);
		                    $("#item_id").val(selectedDataRecord.item_id);
		                    $("#reviewer_id").val(selectedDataRecord.reviewer_id);
		                    $("#reviewer_id_hidden").val(selectedDataRecord.reviewer_id);//to provide the old reviewer (originally selected reviewer)
		                    $("#mediator_id").val(selectedDataRecord.mediator_id);
		                    
		                    setRadioValue("confirmatoryRadio", ""+selectedDataRecord.review_confirmatory+"");
		                    $('#jqx_timeout_date').jqxDateTimeInput('val', parseDate(selectedDataRecord.review_time_out));	 	 
		                    $("#popupHeader").html("<img src='images/common/edit_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Edit");
		                    $("#popupWindow").jqxWindow('open');
		                    disableComponents(true, true, false, false, false, false, false);
	                    }
	                    else
	                    {
	                    	alert("You can't edit the record since it is not editable!");
	                    }
                	}
                });
                
                deleteButton.click(function (event) 
                {
                	selectedRowIndex=$("#jqxgrid").jqxGrid('getselectedrowindex');
                	log("selectedRowIndex in deleteButton="+selectedRowIndex);
                	if(selectedRowIndex<0)
                	{
                		//jAlert('You should select a row to delete!', 'Warning');
                		alert('You should select a row to delete!');
                	}
                	else
                	{
                		
	                	var offset = $("#jqxgrid").offset();
	                    $("#popupWindow").jqxWindow({ title: 'delete', position: { x: parseInt(offset.left) + 260, y: parseInt(offset.top) + 65 } });
	                    // get the clicked row's data and initialize the input fields.
	                    selectedDataRecord = $("#jqxgrid").jqxGrid('getrowdata', $('#jqxgrid').jqxGrid('getrowid', selectedRowIndex));
	                    //you can't delete if the selected record is in the status (complete_not_editable (by time_out) or incomplete_not_editable (by time_out))
	                    //so check its status in either complete_not_editable (by time_out) or incomplete_not_editable (by time_out)
	                    //if true then give warning "you can't delete"
	                    //else show it into the dialog box.
	                    if(selectedDataRecord.reviewer_action_status.indexOf("_NOT_EDITABLE")==-1) //check if the record is timed-out
	                    {
		                    $("#group_name").val(selectedDataRecord.group_id);
		                    $("#item_id").val(selectedDataRecord.item_id);
		                    $("#reviewer_id").val(selectedDataRecord.reviewer_id);
		                    $("#reviewer_id_hidden").val(selectedDataRecord.reviewer_id);//to provide the old reviewer (originally selected reviewer)
		                    $("#mediator_id").val(selectedDataRecord.mediator_id);
		                    setRadioValue("confirmatoryRadio", ""+selectedDataRecord.review_confirmatory+"");
		                    $('#jqx_timeout_date').jqxDateTimeInput('val',parseDate(selectedDataRecord.review_time_out));	 	  
		                    
		                    $("#popupHeader").html("<img src='images/common/trash_16.png' width=16 height=16 valign='middle' style='margin-right: 15px;'/>Delete");
		                    
		                    $("#popupWindow").jqxWindow('open');
		                    disableComponents(true, true, true, true, true, true, true);
	                    }
	                    else
	                    {
	                    	alert("You can't delete the record since it is not editable!");
	                    }
                	}
                });
                           	
            },
 
            columns: 
            [
              { text: 'Group Name', dataField: 'group_name', align: 'center', width: '22%' },
              { text: 'Item ID', dataField: 'item_id', align: 'center', width: '10%' },
              { text: 'Reviewer ID', dataField: 'reviewer_id', align: 'center', width: '20%' },
              { text: 'Time-Out', dataField: 'review_time_out', align: 'center', cellsformat: 'd', width: '13%' },
              { text: 'Review Status', dataField: 'reviewer_action_status', align: 'center', width: '20%'},              
              { text: 'Mediator ID', dataField: 'mediator_id', align: 'center', width: '15%'},
              
              { text: 'A', dataField: 'group_id', align: 'center', width: '0%' },
              { text: 'B', dataField: 'review_confirmatory', columntype: 'checkbox', align: 'center', width: '0%' },
              { text: 'C', dataField: 'group_time_out', align: 'center', cellsformat: 'd', width: '0%'},
              { text: 'D', dataField: 'group_reviewers_per_item', align: 'center', width: '0%' },
              { text: 'E', dataField: 'group_reviewer_assignment_type', align: 'center', width: '0%' },
              { text: 'F', dataField: 'group_confirmatory', columntype: 'checkbox', align: 'center', width: '0%' },
              { text: 'G', dataField: 'group_disagreement_process', align: 'center', width: '0%' }
            ]
        });

        $("#jqxgrid").jqxGrid('hidecolumn', 'group_id');
        $("#jqxgrid").jqxGrid('hidecolumn', 'review_confirmatory');
        $("#jqxgrid").jqxGrid('hidecolumn', 'group_time_out');
        $("#jqxgrid").jqxGrid('hidecolumn', 'group_reviewers_per_item');
        $("#jqxgrid").jqxGrid('hidecolumn', 'group_reviewer_assignment_type');
        $("#jqxgrid").jqxGrid('hidecolumn', 'group_confirmatory');
        $("#jqxgrid").jqxGrid('hidecolumn', 'group_disagreement_process');
        
        // initialize the popup window and buttons.
        $("#popupWindow").jqxWindow({width: 390, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#popupWindowCancel"), modalOpacity: 0.01});
        $("#popupWindow").on('open', function () 
        {
            //$("#firstName").jqxInput('selectAll');
        });
     
        //Add, Edit, Delete
        $("#popupWindowSubmit").click(function () 
        {
        	action_command=$("#popupWindow").jqxWindow('title');//add, edit, delete
        	var onsuccess=$("#reviewer_mapping_form").jqxValidator('validate');
			if(!onsuccess) return;
						
			document.getElementById("spinner_img").className="spinner_unhidden"; 
			
			$.ajax
		     ({
		         type: "post",
		         dataType: "",
		         url: "/carm/carm_controller?op=ajax_carm_central_action_item_reviewer_mx",
		         data: 
		         {
		    	 		action: 		action_command,
		    	 		app_id:  		"${app_obj.app_id}",
		    	 		group_id:		$("#group_name").val(),
		    	 		item_id:		$("#item_id").val(),
		    	 		reviewer_id:	$("#reviewer_id").val(),
		    	 		reviewer_id_old:	$("#reviewer_id_hidden").val(),
		    	 		mediator_id:	$("#mediator_id").val(),
		    	 		reviewer_action_status:	"NOT_STARTED",
		    	 		time_out:		Date.parse($("#jqx_timeout_date").jqxDateTimeInput('getText')),
		    	 		confirmatory:	getRadioValue('confirmatoryRadio')		    	 		
		     	 },
		         success: getActionResponse,
		         error: function(response) //called for 404 error, etc
		         {
		        	 alert(response.responseText);	
		             
		         }
		      }); 					     	
        });  
        
		$("#reviewer_mapping_form").jqxValidator
		(
			{
				onError: function()
				{
					log("You have not filled the form correctly!");
				},
				rules:
				[	
					 {
							 input: '#group_name',
							 message: 'The Group Name should be selected!',
							 rule: function(input, commit)
							 {
							 	return Number($("#group_name").prop("selectedIndex"))>-1;
							 }
					 },
					 
					 {
							 input: '#item_id',
							 message: 'The Item ID should be selected!',
							 rule: function(input, commit)
							 {
							 	return Number($("#item_id").prop("selectedIndex"))>-1;
							 }
					 },
					 
					 {
							 input: '#reviewer_id',
							 message: 'The reviewer ID should be selected!',
							 rule: function(input, commit)
							 {
								 return Number($("#reviewer_id").prop("selectedIndex"))>-1;
							 }
					  },					  
					 
					  {
						 input: '#mediator_id',
						 message: 'The mediator ID should be selected!',
						 rule: function(input, commit)
						 {
						 	return Number($("#mediator_id").prop("selectedIndex"))>-1;
						 }
					 },
					 
					 {
						 input: '#jqx_timeout_date',
						 message: 'Time-Out is too tight to complete or over the group time-out!',
						 rule: function(input, commit)
						 {
							 return (Date.parse($("#jqx_timeout_date").jqxDateTimeInput('getText'))>Number(<%=Utils.nowPlus(1)%>)) &&           //check it with the current time
							 		(Date.parse($("#jqx_timeout_date").jqxDateTimeInput('getText'))<=<%=first_gb.getTime_out().getTime()%>);	//check it with group-time			        					 	
						 }
					 }
				]
			}
		);		
             
	});//$(document).ready

	function getActionResponse(strResponse)
	{
		log("getActionResponse("+strResponse+")");
		
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();				
		}
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			location.reload();//so it will re-read the updated tables
		}
		else 
		{
			alert(strResponse.substring("false:".length), 'Error');//<== edit
		}
	}
	function disableComponents(group_name, item_id, reviewer_id, mediator_id, confirmatoryNo, confirmatoryYes, jqx_timeout_date)
	{
		$("#group_name").prop('disabled', group_name);
		$("#item_id").prop('disabled', item_id);
		$("#reviewer_id").prop('disabled', reviewer_id);
		$("#mediator_id").prop('disabled', mediator_id);
		$("#confirmatoryNo").prop('disabled', confirmatoryNo);
		$("#confirmatoryYes").prop('disabled', confirmatoryYes);
		$("#jqx_timeout_date").jqxDateTimeInput({ disabled: jqx_timeout_date });
	}	
	function onChangeGroupName(selected_group_id)
	{
		//call the selected group record through ajax and put it in a hidden place
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
	
</script>