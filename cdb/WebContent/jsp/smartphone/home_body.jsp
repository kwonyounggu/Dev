<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>

<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 
	<tr height="15">
		<td width=10 background='images/table/vertical_left.gif'></td>	
		<td colspan="3"></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td colspan=3 bgcolor=#ffffff valign="top" align="left"  nowrap> 
			<img src="images/caliper_smartphone_home.png" style="vertical-align: middle;"/>
		</td>		
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr height="25">
		<td width=10 background='images/table/vertical_left.gif'></td>	
		<td colspan="3"></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td colspan=3 bgcolor=#ffffff valign="top" align="left" nowrap> 
			<a href="<%= MenuLink.smartphone_reference_intervals %>" onfocus="this.blur()" >
				<img src="images/main_body/goto_pediatric_reference_intervals.png" style="vertical-align: middle;"/>
			</a>
		</td>
		
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>



	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left" width="250" height="10" nowrap></td>
		<td  bgcolor=#ffffff valign="top" align="left" width="250" height="10" nowrap></td>
		<td  bgcolor=#ffffff valign="top" align="left" width="250" height="10" nowrap></td> 
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
	</tr> 
</table> 			