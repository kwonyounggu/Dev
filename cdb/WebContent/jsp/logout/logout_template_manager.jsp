<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>	
<table width=205 height=135 border=0 cellspacing=0 cellpadding=0 id="logoutTable" STYLE="background-image: url('images/login/login_out.gif');">
	<tr height=40><td colspan=2></td></tr>
	<tr>
		<td colspan=2 style="padding: 0px 0px 0px 10px;">
			<font color='grey' ><span style="color: #ffffff; font-weight: normal;font-size:9pt;">${login_eng_name}</span></font>
		</td>
	</tr>
		<tr>
		<td colspan=2 style="padding: 0px 0px 0px 10px;"><font color='grey' >You are welcome to CALIPER!!! <span style="color: #FF3600; font-weight: normal;font-size:9pt;"></span></font></td>
	</tr>
	<tr height=45>
		<td align="center" valign="top" style="padding: 2px 0px 0px 0px;" colspan=2>
			<a onfocus=this.blur() href="#" onclick="javascript:logout_button()"><IMG SRC="images/login/logout_button.png"></a>
		</td>
	</tr>
</table>
