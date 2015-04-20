<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page isELIgnored ="false" %>

<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left"> 

			<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding-top: 0px; padding-right: 20px; padding-bottom: 0px; padding-left: 20px;"> 	
				<tr>
				    <td colspan=4 align="left">
						<table height="40" width="100%">
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
									<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Event Management</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
									<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.admin_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Event Management&nbsp;&nbsp;>&nbsp;&nbsp;Event File Upload&nbsp;&nbsp;>&nbsp;&nbsp;Success
								</td>
							</tr>	
							<tr><td colspan=2><hr/></td></tr>				
						</table>					
					</td>
				</tr>
				<tr>
				    <td colspan=4 align="center">
						<p><b>Welcome to 'Event File Upload'</b></p>
						<p><b>You have successfully uploaded the information:</b></p>
					</td>
				</tr>
				<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word; padding-top: 20px; padding-right: 100px; padding-bottom: 20px; padding-left: 100px;"><p></p>
					<span class="title">Summary of File Upload</span> 
					   <div class="box">					
						<table  id='file_upload_success_table' cellpadding='2' cellspacing="2" width='100%' style="padding-top: 20px; padding-right: 0px; padding-bottom: 20px; padding-left: 0px;"> 
							<tr><td align="right">Event Title: </td><td colspan=3 align="left">${eb.event_subject}</td></tr>
							<tr><td align="right">Event Date: </td>
								<td colspan=3 align="left">
								<%
									EventManagementBean eb=(EventManagementBean)request.getAttribute("eb");
								 	out.print(Utils.getUSDate(eb.getEvent_date()));
								%>
								</td></tr> 		
							<tr><td align="right">Event Location: </td><td colspan=3  align="left">${eb.event_location}</td></tr>	
							<tr><td align="right" valign="top">Event Summary: </td><td colspan=3  align="left" valign="top"><textarea rows="2" cols="60" readonly>${eb.event_summary}</textarea></td></tr>
							<tr><td align="right" valign="top">Event PDF file: </td><td colspan=3  align="left">&nbsp;&nbsp;<a href='${eb.event_file_location}' onfocus='this.blur()'>Click here to view</a></td></tr>
							<tr><td align="right" valign="top">Event Submitter: </td><td colspan=3  align="left">${eb.admin_id}</td></tr>
																	 										
						</table>	
					  </div>														
					</td>
				</tr>				
			</table>


		</td> 
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
	</tr> 
</table> 
