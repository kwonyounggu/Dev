<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>	

<script language="Javascript" type="text/javascript">
	function getAgreement()
	{
		httpRequestPost("<%= MenuLink.CONTEXT %>","op=get_agreement","agreementResponse");
	}
	function agreementResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			document.getElementById('agreement_box').innerHTML = strResponse;
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
	function verifyFromServer(op,value,reponseFunction)
	{
		httpRequestPost("<%= MenuLink.CONTEXT %>","op="+op+"&value="+value,reponseFunction);
	}
	function emailResponse(strResponse)
	{
		if(strResponse.indexOf('false:')==-1)//not found
		{
			this.document.reg_form.reg_email_hidden.value=this.document.reg_form.reg_email.value;
			alert(strResponse);
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}

	function verifyEmail()
	{	
		var form = this.document.reg_form;
		this.document.reg_form.reg_email.value=trim(this.document.reg_form.reg_email.value);		
		if(!validateEmail(form.reg_email.value))
		{	
			alert("<%= Message.incorrect_email %>");
			form.reg_email.focus();
		}
		else
		{
			verifyFromServer("verify_email",form.reg_email.value,"emailResponse");
		}
	}

	function signupConfirm()
	{
		this.document.reg_form.reg_address.value=removeSpecialChars(this.document.reg_form.reg_address.value);	
		this.document.reg_form.reg_address.value=trim(this.document.reg_form.reg_address.value);
		this.document.reg_form.reg_eng_name.value=trim(this.document.reg_form.reg_eng_name.value);

		if(!this.document.reg_form.reg_agreement_check.checked) alert("<%= Message.check_usage_agreement %>");
		else if(!isSame(this.document.reg_form.reg_email_hidden,this.document.reg_form.reg_email)) alert("<%= Message.check_email %>");
		else if(!verify2Passwords(this.document.reg_form.reg_password1.value,this.document.reg_form.reg_password2.value)) alert("<%= Message.check_password %>");
		else if(this.document.reg_form.reg_eng_name.value.length<2) alert("<%= Message.incorrect_eng_name %>");
		else if(this.document.reg_form.reg_address.value.length<10) alert("<%= Message.check_address %>");		
		else this.document.reg_form.submit();		

		
	}
	function signupResponse(strResponse)
	{
		//if true for all validation then call form.submit();
	}
</script>
<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td valign="top"></td></tr>
	<tr><td height="10"></td> </tr>
	<tr><td align="center">
	  <form name='reg_form' method='post' action='controller '><input type='hidden' name='op' value='reg_signup_complete' />
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top" align="left"> 
					<table cellpadding="0" cellspacing="0" width="100%"> 
						<tr><td valign="top" align="left" style="padding: 0 0 0 20px;">
								<img src="images/common/blue_circle.gif" style="vertical-align: middle;" onload="getAgreement()"/>
								<span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">Agreement</span>
							</td></tr>
			        	<tr><td width="100%" style="padding: 0 20px;" id="agreement_box"></td></tr> 
			             <tr>
							<td width="100%" height="26"> 
			                 	<p style="text-indent:0; line-height:100%; margin:0;" align="center">
									<img src="images/join/foot_line.gif" width="550" height="13" border="0">
								</p> 
			                    <p style="line-height:100%; margin-top:0; margin-bottom:0;" align="center">
									<span style="font-size:9pt;">
										<font color="#216CA0">I confirm the above terms and conditions, and approve it.</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input onfocus=this.blur() type="checkbox" name="reg_agreement_check" /><font color="#333333">&nbsp;I sign up the agreement.</font>
									</span>
								</p> 
			              	</td> 
			               </tr> 
						   <tr><td height="10"></td> </tr>
						   <tr><td valign="top" align="left" style="padding: 0 0 0 20px;">
									<img src="images/common/blue_circle.gif" style="vertical-align: middle;"/>
			<span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">Register/Sign Up</span>									
							   </td>
							</tr>
			        </table> 
					<!-- Registration Table Form -->
					<table border=0 cellspacing=0 cellpadding=4 width='95%' style="padding: 0 0 0 20px;"> 
				 		<tr><td colspan=4 height=1 class=dv_line></td></tr> 
	 					<tr> 
							<td class=left_td><span style="color: #ff0000;">*</span>&nbsp;&nbsp;Login Email</td> 
							<td class=check_td></td> 
							<td colspan=2 align="left"> 
				 				<input type=text name="reg_email" size=50 value='' maxlength="80" class=input_text style='imemode:inactive' /> 
								<input type='hidden' name='reg_email_hidden'/>							
								<input type="button" value="Check ID" onclick="verifyEmail();" onfocus='this.blur()' />
							</td> 
						</tr> 
						<tr><td colspan=4 height=1 class=dv_line></td></tr> 
						<tr> 
							<td class=left_td><span style="color: #ff0000;">*</span>&nbsp;&nbsp;Password</td> 
							<td class=check_td></td> 
							<td colspan=2 align="left">
								<input type=password name="reg_password1" size=18 maxlength=12 class="input_text" style='imemode:inactive'/>→Confirm
								<input type=password name="reg_password2" size=18 maxlength=12 class="input_text" style='imemode:inactive'/>&nbsp;eg) P1234 (5-12 characters )
							</td> 
						</tr> 
						<tr><td colspan=4 height=1 class=dv_line></td></tr> 	
						<tr> 
						
							<td class=left_td><span style="color: #ff0000;">*</span>&nbsp;&nbsp;Your Name</td> 
							<td class=check_td></td> 
							<td colspan=2 align="left"> 
								<input type=text name="reg_eng_name" size="25" maxlength="20" class=input_text /> 
								<input type='hidden' name='reg_eng_name_hidden'/>	
						</td> 
						</tr> 						
						<tr><td colspan=4 height=1 class=dv_line></td></tr> 
					
						<tr> 
							<td class=left_td>&nbsp;&nbsp;&nbsp;&nbsp;Hospital Name</td> 
							<td class=check_td></td> 
							<td colspan=2 > 
								<input type=text name="reg_org_name" size=30  maxlength=30 class=input_text />&nbsp;&nbsp;eg) SickKids
							</td> 
						</tr> 
						<tr><td colspan=4 height=1 class=dv_line></td></tr> 
						<tr> 
							<td class="left_td"><span style="color: #ff0000;">*</span>&nbsp;&nbsp;Address</td> 
							<td class=check_td></td> 
							<td colspan=2 > 
								<input type=text name="reg_address" size=43 value="" maxlength=200 class=input_text style='letter-spacing:-1;' />
								&nbsp;eg) 555 University Ave, Toronto, ON, Canada 
							</td>  
						</tr> 

						<tr style='display:none' height=0> 
							<td colspan=4> 
								<input type=text name='homepage' value='1' /><input type=text name='name' value='1' /><input type=text name='mail' value='1' /> 
								<input type=text name='tel' value='1' /><input type=text name='id' value='1' /><input type=text name='password' value='1' /> 
							</td> 
						</tr> 						 
						<tr> 
							<td colspan=4 align=center><br> 
								<input type=button style='cursor:pointer;' value='Submit' onclick="signupConfirm();" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<span style="color: #ff0000;">*</span>:&nbsp;&nbsp;<span>You must fill in.)</span>
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
	  </form>					
 	</td>
	</tr>
</table>      
