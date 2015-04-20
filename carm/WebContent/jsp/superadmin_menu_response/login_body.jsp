<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>

<script language="Javascript" type="text/javascript">
	var login_email=null;
	function loginConfirm()
	{		
		var login_id=document.getElementById("login_id");
		var login_password=document.getElementById("login_password");
	
		login_id.value=trim(login_id.value);
	
		if(!isLoginID(login_id))
		{ 	
			alert("<%= Message.invalid_loginID %>"); 
			login_id.focus();
		}
		else if(!validatePassword(login_password.value))
		{
			alert("<%= Message.incorrect_password %>"); 
			login_password.focus();
		}
		else 
		{	
			httpRequestPost("<%= MenuLink.SUPERADMIN_CONTEXT %>","op=ajax_login_check&app_id=1&sa_login_id="+login_id.value+"&sa_login_password="+login_password.value,"loginResponse");	
		}		
	}
	
	function resetPasswordConfirm()
	{
		var login_password1=document.getElementById("login_password1");
		var login_password2=document.getElementById("login_password2");

		if(!verify2Passwords(login_password1.value, login_password2.value)) 
		{
			alert("Please check your typed password.\nBoth are not identical, empty or less than 5 characters.");
		}
		else 
		{	
			httpRequestPost("<%= MenuLink.SUPERADMIN_CONTEXT %>","op=ajax_reset_password&sa_login_password1="+login_password1.value+"&sa_login_password2="+login_password2.value,"resetPasswordResponse");	
		}
	}
	
	function forgot_password_confirm()
	{   
		login_email=document.getElementById("login_email");

		login_email.value=trim(login_email.value);

		if(!validateEmail(login_email.value))
		{ 	alert("You should have a proper email."); 
			login_email.focus();
		}
		else 
		{	
			httpRequestPost("<%= MenuLink.SUPERADMIN_CONTEXT %>","op=ajax_forgot_pwd_check&app_id=1&sa_login_email="+login_email.value,"forgot_emailPasswordResponse");	
		}		
	}
	function forgot_emailPasswordResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			login_email.value="";
			alert(strResponse.substring(5));			
			location.replace("<%= MenuLink.SUPERADMIN_CONTEXT %>");
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
	function get_forgot_pwd_page()
	{
		httpRequestPost("<%= MenuLink.SUPERADMIN_CONTEXT %>","op=ajax_forgot_pwd","forgotPasswordResponse");
	}
	function forgotPasswordResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else 
		{
			//removeRowFromTable("login_table");
			document.getElementById('login_body').innerHTML = strResponse;
		}
	}
	function loginResponse(strResponse)
	{	
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else if(strResponse.indexOf('true:')==0)//call a central/local page
		{			
			location.replace(strResponse.substring(5));//remove true: prefix
		}
		else if(strResponse.indexOf('false:')==0)
		{	
			alert(strResponse.substring(6));
			//focus to the login input box
		}
		else //display a reset password table
		{	
			//removeRowFromTable("login_table");
			document.getElementById('login_body').innerHTML = strResponse;
		}
	}
	function resetPasswordResponse(strResponse)
	{	
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else if(strResponse.indexOf('false:')==0) //test it later by commenting javascript validation
		{	
			alert(strResponse.substring(6));
			//focus to the login input box
		}
		else if(strResponse.indexOf('true:')==0)//redirect to the corresponding context, central or local
		{	
			//alert(strResponse);
			location.replace(strResponse.substring(5));//remove true: prefix
		}
	}

</script>
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
						    <td colspan=4 align="center">
								<p><b>Application Admin Login</b></p>
							</td>
						</tr>
						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>										
										<td valign="top" align="center">
											<div id='login_body'>
												<table  id='login_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 0px 20px 60px;"> 
											 		
													<tr><td colspan=4>LoginID <span style="font-size: .95em;">(eg: jennifer.pinto.00)</span></td></tr> 		
																								
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type=text id="login_id" size=38 value='' maxlength="80" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
														</td> 
													</tr> 
												
													<tr><td colspan=4>Password</td></tr> 													
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type='password' id="login_password" size=38 value='' maxlength="20" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
														</td> 
													</tr> 					
																		 
													<tr> 
														<td colspan=4 align="left"><br/> 
															<input type=button style='cursor:pointer;' value='Login' onclick="loginConfirm();" />	
															<br/><br/>
															<p ><a onfocus='this.blur()' href="#" onclick="get_forgot_pwd_page(); return false;" >Did you forget your password?</a></p>
															<p ><a onfocus='this.blur()' href="#" onclick="get_forgot_pwd_page(); return false;" >Did you forget your loginID?</a></p>
														</td> 
													</tr> 
												 </table>
											 </div>											
											</td>
										</tr>
									</table>	
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
 	</td>
	</tr>
</table>  