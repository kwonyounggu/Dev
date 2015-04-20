<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>
<%@ page isELIgnored ="false" %>
<script language="Javascript" type="text/javascript">

	function submitFileUpload()
	{		
		var event_title=document.getElementById("event_title"); // form_event_title"
		var event_date=document.getElementById("calendar1_container"); //form_event_date
		var event_location=document.getElementById("event_location"); //form_event_location
		var event_summary=document.getElementById("event_summary"); //form_event_summary
		var pdf_file=document.getElementById("pdf_file");
		
		event_title.value=trim(event_title.value);
		event_location.value=trim(event_location.value);
		event_summary.value=trim(event_summary.value);
		event_summary.value=removeSpecialChars(event_summary.value);//this will remove all the ', ", special chars.
		
		if(event_title.value.length<9)
		{
			alert("Warning: \nYou should have an event title with at least 10 characters.");
			event_title.focus();
		}
		else if(event_date.value.length==0)
		{
			alert("Warning: \nYou should have an event date selected.");
			event_date.focus();
		}
		else if(event_location.value.length<9)
		{
			alert("Warning: \nYou should have an event location with at least 10 characters.");
			event_location.focus();
		}
		else if(event_summary.value.length<30)
		{
			alert("Warning: \nYou should have an event summary with over 30 characters.");
			event_summary.focus();
		}
		else if(pdf_file.value.length<2)
		{
			alert("You should select a proper pdf file to upload.");
		}
		else 
		{	//1. the ajax will check if the submission type is correct
			var file_name=extractFileName(pdf_file.value);
			
			if(file_name.indexOf(".pdf")==-1)
			{
				alert("Your file to upload does not contain the right pdf extension");
				return;
			}	
			var spinner_img = document.getElementById("spinner_img");	
			try
			{			
				this.document.file_upload_form.submit();
				if (spinner_img) spinner_img.className='spinner_unhidden';
			}
			catch(e)
			{
				spinner_img.className='spinner_hidden';
			}
		}	
	}
	function extractFileName(filePath)
	{
		var pieces = filePath.split('\\');
		var filename = pieces[pieces.length-1];
		return filename;
	}
</script> 	

<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff align="center" > 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 

	<tr height="15">
		<td width=10 background='images/table/vertical_left.gif'></td>	
		<td></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="center" style='padding-bottom: 0px;'> 
			<table align="center" border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 0px 20px 0px; "> 	
				<tr><td colspan='2' align="center">
						<table height="40" width="100%">
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
										<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
										<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Event Management</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
										<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.admin_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Event Management
								</td>
							</tr>	
							<tr><td colspan=2><hr/></td></tr>				
						</table>
					</td>
				</tr>		
				<tr>
				    <td colspan='2' align="center" valign="top" width='100%' style="padding-left: 80px; padding-right: 80px;">
						<span class="title">Please do the followings:</span> 
						<div class="box">
							<div id='file_upload_body'>									
								<table  id='file_upload_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 80px 20px 80px;"> 
							 		<form name="file_upload_form" enctype="multipart/form-data" method="post" action="/cproject/pdf_file_upload" >
									<tr><td align="right" width='30%'>Event Title: </td><td colspan=4  align="left" width='70%'><input type='text' id="event_title" name="event_title" size=60 value='' maxlength="80" class=input_text style='imemode:inactive' /></td></tr>	
									<tr><td align="right" width='30%'>Event Date: </td><td colspan=4  align="left" width='70%'><input id="calendar1_container" type="text" name="event_date" size='10' readonly="readonly"/></td></tr>	
									<tr><td align="right" width='30%'>Event Location: </td><td colspan=4  align="left" width='70%'><input type='text' id="event_location" name="event_location" size=60 value='' maxlength="80" class=input_text style='imemode:inactive' /></td></tr>	
									<tr><td align="right" width='30%'>Event Summary: </td><td colspan=4  align="left" rowspan='2' width='70%'><textarea id='event_summary' name='event_summary' rows='2' cols='62' style='background-color: #ffffff; border:1;' maxlength="<%=(Utils.MAX_LEN_FOR_EVENT_SUMMARY-10) %>"></textarea></td></tr>										
									<tr><td width='30%'></td></tr>
									<tr><td align="right" valign="top" width='20%'>Event PDF File: </td><td colspan=4  align="left" width='70%'>												
												  <input type='hidden' name='op' value='event_file_upload' />
												  <input type='hidden' id='submitter_id' name='submitter_id' value='${admin_name}' />
 												<input type='file' id="pdf_file" name="pdf_file" accept="application/pdf" size=65 value='' maxlength="512"  style='imemode:inactive' readyonly/>											
										</td>
									</tr>
									</form>	
									<tr><td width='30%'></td><td colspan=4 align="left" width='70%'><br/><input type=button style='cursor:pointer;' value='Submit' onclick="submitFileUpload();" /></td></tr>
									<tr><td colspan='5' width='100%' align="center">
										<img id='spinner_img' src='images/common/spinner.gif' width=32 height=32 valign='middle' class="spinner_hidden"/>
									</td></tr>											 										
								 </table>
							 </div>	
						   </div>																
						</td>
					</tr>				
				</table>
		</td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left"  height="10" ></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
	</tr> 
</table> 		