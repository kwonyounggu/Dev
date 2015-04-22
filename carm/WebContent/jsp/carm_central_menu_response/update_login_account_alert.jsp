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
								</td>
							<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
								<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.menu_carm_central_home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Admin&nbsp;&nbsp;>&nbsp;&nbsp;Update Login Account
							</td>
						</tr>	
						<tr><td colspan=2><hr/></td></tr>				
					</table>					
				</td>
			</tr>
			<tr><td colspan=4 align="center" style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">
				 	<p><h4>There is no login account to update</h4></p>							
				</td>
			</tr>			
		</table>
 	</td>
	</tr>
</table>  