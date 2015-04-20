<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td align="center">
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top" align="left"> 
					<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0px 20px 20px 20px; "> 	
						<tr>
						    <td colspan=4 align="left">
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">Contact Us in SAINTE-JUSTINE HOSPITAL</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<a href="<%= MenuLink.contactus_montreal %>" style="color: #bf3a00;">To English</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home_montreal %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Contact Us
										</td>
									</tr>			
									<tr><td colspan='2'><hr/></td></tr>			
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td></tr>
						<tr><td colspan=4  style="padding-right: 0px; padding-bottom: 0px; padding-left: 0px; ">
								<img src='local_hospitals/montreal/doc/montreal_contactus_french.gif' width=900 height=751 valign="middle"/>
							</td>
						</tr>					
					</table>
					<br/>
					<table border=0 cellspacing=0 cellpadding=0 width='100%' height='0'> 	
						<tr><td></td></tr>
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
 	</td>
	</tr>
</table>  
