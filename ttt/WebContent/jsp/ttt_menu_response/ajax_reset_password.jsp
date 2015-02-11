<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>


<table  id='reset_password_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 0px 20px 60px;"> 
											 		
	<tr><td colspan=4>Please enter your new password <span style="font-size: .95em;"><br/>(Note: it should be more than 5 characters alphanumerically.)</span></td></tr> 		
												
		<tr> 
		<td colspan=4 align="left"> 
				<input type='password' id="login_password1" size=38 value='' maxlength="20" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) resetPasswordConfirm();"/> 						
		</td> 
	</tr> 

	<tr><td colspan=4>Re-type Password</td></tr> 													
		<tr> 
		<td colspan=4 align="left"> 
				<input type='password' id="login_password2" size=38 value='' maxlength="20" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) resetPasswordConfirm();"/> 						
		</td> 
	</tr> 					
						 
	<tr> 
		<td colspan=4 align="left"><br/> 
			<input type=button style='cursor:pointer;' value='Submit' onclick="resetPasswordConfirm();" />	
		</td> 
	</tr> 
 </table>