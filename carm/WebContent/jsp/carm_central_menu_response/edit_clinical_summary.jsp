<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>  
<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
  	List<CarmClinicalSummaryEditBean> summaryList=csqlDao.getCarmClinicalSummaryEditList("");
  %>
<script language="Javascript" type="text/javascript">
	var editorChanged=false;
	$(document).ready(function () 
	{
		var data=[
			 		<%
			 			for(int i=0; i<summaryList.size();i++)
			 			{
			 				CarmClinicalSummaryEditBean csb=summaryList.get(i);
			 				out.print("{"); 
			 				out.print("\"count\": "+(i+1)+",");		
			 				out.print("\"carm_id\": "+csb.getCarm_id()+",");	
			 				out.print("\"clinical_summary\": \""+Utils.getChoppedString(StringEscapeUtils.escapeJava(csb.getOld_clinical_summary()), 200)+"\",");
			 				if(csb.getNew_clinical_summary().length()<=0)
			 					out.print("\"new_clinical_summary\": \""+StringEscapeUtils.escapeJavaScript(csb.getOld_clinical_summary_break())+"\",");
			 				else
			 					out.print("\"new_clinical_summary\": \""+StringEscapeUtils.escapeJavaScript(csb.getNew_clinical_summary())+"\",");
			 				out.print("\"edited\": "+csb.isEdited()+",");
			 				if(csb.getEdit_date()==null) out.print("\"edit_date\": null,");
			 				else out.print("\"edit_date\": \""+csb.getEdit_date()+"\",");
			 				out.print("\"editor_id\": \""+csb.getEditor_id()+"\"");
							out.print(((i+1)==summaryList.size() ? "}" : "},"));				
			 			}
			 		%>
			 	  ];
		var source = 
		{
            localdata: data,
            datatype: "array",
            datafields:
            [
                { name: "count", type: "number" },
                { name: "carm_id", type: "number" },
                { name: "clinical_summary", type: "string" },
                { name: "new_clinical_summary", type: "string" },
                { name: "edited", type: "bool" },
                { name: "edit_date", type: "date", format: 'MM/dd/yyyy' },
                { name: "editor_id", type: "String" }
            ]
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
            columns: 
            [
              { text: 'No', dataField: 'count', align: 'center', width: '5%' },
              { text: 'Events', dataField: 'carm_id', align: 'center', width: '10%' },
              { text: 'Clinical Summary', dataField: 'clinical_summary', align: 'center', width: '50%' },
              { text: 'NewCSummary', dataField: 'new_clinical_summary', align: 'center', width: '0%' },
              { text: 'Edited', dataField: 'edited', columntype: 'checkbox', align: 'center', width: '10%' },
              { text: 'Edit Date', dataField: 'edit_date', align: 'center', cellsformat: 'd', width: '10%'},
              { text: 'Editor ID', dataField: 'editor_id', align: 'center', width: '15%' }
            ]
        });

        $("#jqxgrid").jqxGrid('hidecolumn', 'new_clinical_summary');
        $("#jqxgrid").on('rowselect', function (event) 
   		{
   		    var clinicalSummaryString=$("#jqxgrid").jqxGrid('getcellvalue', event.args.rowindex, 'new_clinical_summary');
   		 	$("#clinicalSummaryEditor").jqxEditor('val', clinicalSummaryString);
   		 	
   		 	editorChanged=false;
   		});
        //Clinical Summary Editor
        $("#clinicalSummaryEditor").jqxEditor
        (
            {
                height: '400px', width: '100%', 
                //theme: 'energyblue',
                tools: "bold italic underline | format font size | color background | left center right | outdent indent | ul ol | image | link | clean | html"
                
        	}
        );
        $("#clinicalSummaryEditor").on('change', function (event) 
        {
        	if($('#jqxgrid').jqxGrid('getselectedrowindex')>=0) editorChanged=true;
        });
        $("#jqxSaveButton").jqxButton({ width: '100', theme: 'energyblue', disabled: false});

        $("#jqxSaveButton").on('click', function () 
        {
            if(editorChanged)
            {
                 //alert("submit now with\n"+$("#clinicalSummaryEditor").jqxEditor('val'));
                 saveClinicalSummary();
            }
            else alert("No content change detected yet!");
        });
        
	});
	function saveClinicalSummary()
	{
		var login_level=parseInt("${crb.login_level}");
		var seniority=parseInt("${crb.seniority}");
	
		if(!(login_level==1 || login_level==2 ))		
		{
			alert("You are not allowed to edit this summary!!!.");
			return;
		}
		
		var saveString=$("#clinicalSummaryEditor").jqxEditor('val');
		if(saveString.length<=1)
			alert("Not a right clinical summary to save!");
		else
		{
			var selected_row_index=$('#jqxgrid').jqxGrid('getselectedrowindex');			
			httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_save_clinical_summary&new_clinical_summary="+escape(saveString)+"&selected_carm_id="+$("#jqxgrid").jqxGrid('getcellvalue', selected_row_index, 'carm_id'), "saveClinicalSummaryResponse");
		}
	}
	function saveClinicalSummaryResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false:')==-1)//not found, means true
		{
			//location.reload();
			//update checked, edited_date, editor_id in the selected row
			var selected_row_index=$('#jqxgrid').jqxGrid('getselectedrowindex');
			$('#jqxgrid').jqxGrid('setcellvalue',selected_row_index, 'new_clinical_summary', $("#clinicalSummaryEditor").jqxEditor('val'));//set new clincal summary
			$('#jqxgrid').jqxGrid('setcellvalue',selected_row_index, 'edited', true);
			$('#jqxgrid').jqxGrid('setcellvalue',selected_row_index, 'edit_date', new Date());
			$('#jqxgrid').jqxGrid('setcellvalue',selected_row_index, 'editor_id', '${carm_login_id}');

			alert("Successfully save!");
		}
		else 
		{
			alert(strResponse.substring(6));//false, then display
			//location.reload();
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
								<!--<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
								<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Edit Clinical Summary</span>
							--></td>
							<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
								<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Edit Clinical Summary
							</td>
						</tr>	
						<tr><td colspan=2><hr/></td></tr>				
					</table>					
				</td>
			</tr>
			<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
				 <div id="jqxgrid">
		        	</div>
										
				</td>
			</tr>
			<tr><td colspan="4" height="10" ></td></tr>
			<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
					<textarea id="clinicalSummaryEditor">
    				</textarea>										
				</td>
			</tr>
			<tr><td colspan="4" height="10" ></td></tr>
			<tr>
			    <td colspan="4" bgcolor="#ffffff" valign="top" align="right" >	
					<input type="button" value="Save" id='jqxSaveButton' />		
				</td>
			</tr>

				
		</table>
 	</td>
	</tr>
</table>  