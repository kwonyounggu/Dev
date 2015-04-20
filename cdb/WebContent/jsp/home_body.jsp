<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>

<table border=0 cellpadding=0 cellspacing=0 bgcolor='#ffffff' align='center' width='100%' height='100%'> 
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
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
			<a href="<%= MenuLink.whatiscaliper %>" onfocus="this.blur()" >
				<img src="images/main_body/what_is_caliper.gif" height="240" width="240" style="vertical-align: middle;"/>
			</a>			
		</td>
		<td  bgcolor=#ffffff valign="top" align=""center"" width="33%" nowrap> 
			<a href="<%= MenuLink.about_reference_intervals %>" onfocus="this.blur()" >
				<img src="images/main_body/pediatric_reference_intervals.gif" height="240" width="240" style="vertical-align: middle;"/>
			</a>
		</td>
		<td  bgcolor=#ffffff valign="top" align=""center"" width="33%" nowrap> 
			<a href="<%= MenuLink.caliper_program %>" onfocus="this.blur()" >
				<img src="images/main_body/caliper_program.gif" height="240" width="240" style="vertical-align: middle;"/>
			</a>
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
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 	  
			<a href="<%= MenuLink.reference_intervals %>" onfocus="this.blur()" >
				<img src="images/main_body/caliper_table.gif" height="240" width="240" style="vertical-align: middle;"/>
			</a>
		</td>
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
			<a href="<%= MenuLink.custom_reference_intervals %>" onfocus="this.blur()" >
			<img src="images/main_body/caliper_database.gif" height="240" width="240" style="vertical-align: middle;"/>
		  </a>
		</td>
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
		   <a href="<%= MenuLink.caliper_publications_presentations %>" onfocus="this.blur()" >
			<img src="images/main_body/caliper_publications.gif" height="240" width="240" style="vertical-align: middle;"/>
		  </a>
		</td> 
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>


	<tr height="5">
		<td width=10 background='images/table/vertical_left.gif'></td>	
		<td colspan="3"></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
			
		</td>
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
		  
		</td>
		<td  bgcolor=#ffffff valign="top" align="center" width="33%" nowrap> 
			
		</td> 
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left" width="33%" height="10" nowrap></td>
		<td  bgcolor=#ffffff valign="top" align="left" width="33%" height="10" nowrap></td>
		<td  bgcolor=#ffffff valign="top" align="left" width="33%" height="10" nowrap></td> 
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