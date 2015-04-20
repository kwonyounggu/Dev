<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<% if (session.isNew()) 
   {	//System.out.println("---session timed-out in write_message.jsp, please login again---"); 
   		response.sendRedirect("jsp/menu_response/session_out.jsp");
   }
%>
<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%" id="write_message_table"> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 
	<tr>  
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left"> 
			<form name='new_qa_msg_form' method='post' action='controller'>
				<input type='hidden' name='op' value='write_new_msg'>
				<input type='hidden' name='sm' value='<%=request.getParameter("sm") %>'>
				<input type='hidden' name='search' value='0'>
				<input type='hidden' name='stext' value=''>	
				<input type='hidden' name='scategory' value='0'>				
				<input type='hidden' name='section' value='1'>
				<input type='hidden' name='page' value='1'>

			<table border=0 cellspacing=2 cellpadding=4 width='100%' style="padding: 0px 20px 20px 10px;"> 
				<tr><td colspan=3>
						<table height="40" width="100%">
							<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
									<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
									<span style="font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;
									
									<%  
										int iSm=Integer.parseInt(request.getParameter("sm"));
										if(iSm==1) out.print("Write a Community News");
										else if(iSm==0) out.print("Write a FAQ");
										else out.print("TBD");
									%>
									</span>
								</td>
								<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
									<img src='images/common/location_pin.png' width=16 height=16  valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;
									<%  
										if(iSm==1) 
											out.print("Community News&nbsp;&nbsp;>&nbsp;&nbsp;Write a Community News");
										else if(iSm==0) 
											out.print("Frequently Asked Questions&nbsp;&nbsp;>&nbsp;&nbsp;Write a FAQ");
										else 
											out.print("TBD;&nbsp;>&nbsp;&nbsp;TBD");
									%>
								</td>
							</tr>	
							<tr><td colspan='2'><hr/></td></tr>				
						</table>	
					</td>
				</tr>
		 				
				<tr>
					<td class="qa_td">Title</td>
					<td width="80%" colspan=2><input id="qa_subject" name="qa_subject" type=text size=80 value='' maxlength="<%=Utils.SUBJECT_LIMIT %>" class=input_text style='imemode:inactive' /></td> 
				</tr>
				<tr>
					<td class="qa_td">Writer</td>
					<td width="25%" colspan=2><input type=text size=10 id="qa_name" name="qa_name" value='<%=(String)session.getAttribute("admin_name") %>' maxlength="80" class=input_text style='imemode:inactive; color:#616161; border: 0;' readonly /></td> 
				</tr>
				<tr>
					<td class="qa_td">E-Mail</td>
					<td width="25%"><input id="qa_email" name="qa_email" type=text size=26 value='<%=(String)session.getAttribute("admin_email") %>' maxlength="80" class=input_text style='imemode:inactive; color:#616161; border: 0;' readonly/></td> 
					<td colspan=3 align="right">Maximum letters: 2040</td>					
				</tr>
				<tr style="display:none">
					<td class="qa_td">Type</td>
					<td width="25%" colspan=2><select name="qa_class" disabled="disabled">
									  <option value="0">General</option>
									</select>
					</td> 
				</tr>
				<tr style="display:none">
					<td class="qa_td">Admin</td>
					<td width="25%" colspan=2><input id="qa_delete_password" name="qa_delete_password" type=password size=10 value='1234' maxlength="12" class=input_text style='imemode:inactive' disabled="disabled"/></td> 
				</tr>
				<tr style="display:none">
					<td class="qa_td">Security</td>
					<td width="25%" colspan=2 nowrap><input id="qa_security_code" name="qa_security_code" type=password size=10 value='12345' maxlength="5" class=input_text style='imemode:inactive' disabled="disabled"/>
								<span><b></b></span><input type='hidden' id='qa_security_code2' value='12345'>
					</td> 
				</tr>
				<tr><td colspan=3>
					<textarea id="qa_message" name="qa_message" style="width: 100%; height: 100%; font-size:9pt; font-family:arial;  border: 1;overflow-x: hidden;" maxlength="<%=Utils.MAX_LEN_FOR_TEXTAREA %>" rows="13"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=2 width="35%"></td>	
					<td width="65%" align="center">
						<input type='button' value='Submit' style="vertical-align: bottom; cursor:pointer;" onclick="sendQaMsg()"/>&nbsp;&nbsp;&nbsp;
						<input type='button' value='Cancel' style="vertical-align: bottom;cursor:pointer;" onclick="removeRowFromTable('write_message_table');mode=null;"/>
					</td>				
				</tr>
			 </table> 
			</form>	
		</td> 
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
	</tr> 
	<tr><td colspan=3 height=10></td></tr>
</table> 