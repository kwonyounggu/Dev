<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>

<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %> 

<table cellpadding="0" cellspacing="0" width="100%"> 
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
			<tr><td colspan=4 align="center" style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
				 	<p><h4>You should have setting paramters for the group first!!!</h4></p>		
				 	<p><a href="<%= MenuLink.menu_carm_central_admin_group_management %>" style="color: #CB1A1A;">Go to here for the settings</a></p>						
				</td>
			</tr>			
		</table>
 	</td>
	</tr>
</table>  