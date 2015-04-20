<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>
<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff > 
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
		<td  bgcolor=#ffffff valign="top" align="center" > 
			<table border=0 cellspacing=0 cellpadding=0 width='100%' align="center" style="padding: 0px 20px 20px 20px;"> 						 									
				<tr>
					<td align="center" style="padding-right: 20px; font-size:8pt; font-family: arial;">
						<table height="40" width="100%">
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
									<span style="font-weight:bold; font-size:12pt; font-family: arial;">About Caliper in Children's Hospital of Eastern Ontario</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
									<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%=MenuLink.home_ottawa %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>About Caliper
								</td>
							</tr>	
							<tr><td colspan='2'><hr/></td></tr>				
						</table>
					</td>
				</tr>
				<tr><td height=10></td></tr>
				<tr><td align='center'>
						<img src="local_hospitals/ottawa/doc/ottawa_about_caliper.gif" style="vertical-align: top;"  height="556" />					
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
			