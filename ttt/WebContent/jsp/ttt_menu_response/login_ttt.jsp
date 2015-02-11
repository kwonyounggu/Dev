<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>

<script language="Javascript" type="text/javascript">
	var login_email=null;
	function loginConfirm()
	{		
		var login_id=document.getElementById("login_id");
		var login_password=document.getElementById("login_password");
		var course_number=document.getElementById("course_number").value;

		login_id.value=trim(login_id.value);
	
		if(course_number=="na")
		{
			alert("Please choose the name of Seminar to attend!"); 
			document.getElementById("course_number").focus();
		}
		else if(!isLoginID(login_id))
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
		{	log("------------- 0 -----------");
			httpRequestPost("<%= MenuLink.TTT_CONTEXT %>","op=ajax_ttt_login_check&app_id=111&login_id="+login_id.value+"&login_password="+login_password.value+"&course_number="+course_number,"loginResponse");	
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
			httpRequestPost("<%= MenuLink.TTT_CONTEXT %>","op=ajax_ttt_reset_password&login_password1="+login_password1.value+"&login_password2="+login_password2.value,"resetPasswordResponse");	
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
			httpRequestPost("<%= MenuLink.TTT_CONTEXT %>","op=ajax_ttt_forgot_pwd_check&app_id=111&ttt_login_email="+login_email.value,"forgot_emailPasswordResponse");	
		}		
	}
	function forgot_emailPasswordResponse(strResponse)
	{
		if(strResponse.indexOf('session_timeout')==0) location.reload();
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			login_email.value="";
			alert(strResponse.substring(5));			
			location.replace("<%= MenuLink.TTT_CONTEXT %>");
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
	function get_forgot_pwd_page()
	{
		httpRequestPost("<%= MenuLink.TTT_CONTEXT %>","op=ajax_ttt_forgot_pwd","forgotPasswordResponse");
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
		//alert(strResponse);
		//return;
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
<%
 	//TTTSQLDao csqlDao=(TTTSQLDao)application.getAttribute("csqlDao");
 	//AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
 	
 	List<CurriculumCurrentBean> courseList=(List<CurriculumCurrentBean>)session.getAttribute("curriculum_current_list");

 %>
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
											<span style="color: #32344B font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Tele-team training Video</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:10pt; font-family: arial; color: #32344B;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;TTT Video
										</td>
									</tr>	
									<tr><td colspan=2><hr/></td></tr>				
								</table>					
							</td>
						</tr>
						<tr>
						    <td colspan=4 align="center">
								<p><b>Welcome to TTT Video</b></p>
								<p><b>--- Tele-team training Video-Software ---</b></p>
							</td>
						</tr>
						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;"><p></p>					
								<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="center" width="50%">
											<img src="images/login/ttt_flow.gif" border="0" height="340" width="400" /> 
										</td>
										<td width="50%" valign="top" align="left">
											<div id='login_body'>
												<table  id='login_table' cellpadding='2' cellspacing="4" width='100%' style="padding: 20px 0px 20px 60px;"> 
											 		<tr><td colspan=4>Online Seminar Name <span style="font-size: .95em;"></span></td></tr> 		
																								
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<select id="course_number">	
											 					<option value='na' selected></option>
											 					
									                        	<%			                        		
									                        		for(CurriculumCurrentBean tb : courseList)
																	{
									                        			//value starts from 1000
									                        			out.print("<option value='"+tb.getCourseNumber()+"' >"+tb.getCourseName()+"</option>");
																	}							 
																%>		
																<option value='-10000' >Administration Only</option>		 												 
															</select>														
														</td> 
													</tr> 
													
													<tr><td colspan=4>LoginID <span style="font-size: .95em;">(eg: jennifer.pinto.00)</span></td></tr> 		
																								
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type=text id="login_id" size=38 value='${rb.user_id}' maxlength="80" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
														</td> 
													</tr> 
												
													<tr><td colspan=4>Password</td></tr> 													
								 					<tr> 
														<td colspan=4 align="left"> 
											 				<input type='password' id="login_password" size=38 value='${rb.password}' maxlength="20" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) loginConfirm();"/> 						
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
						<tr>
						    <td colspan=4 align="left" style="padding: 0px 40px 0px 40px;">								
								<p>Interested : please contact <a href="mailto:kristen.middaugh@sickkids.ca">Kristen Middaugh</a>  or <a href="mailto:chris@sickkids.ca">Dr. Christopher Parshuram</a> </p>
								<p>Work has been supported by operational funding from Heart and Stroke Foundation of Canada, and Canadian Institutes of Health Research---</p>
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