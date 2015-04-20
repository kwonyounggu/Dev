<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<% if (session.isNew()) 
   {	//System.out.println("---session timed-out in write_message.jsp, please login again---"); 
   		response.sendRedirect("jsp/menu_response/session_out.jsp");
   }
%>
<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%" id="reply_message_table"> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left"> 
			<form name='new_qa_reply_msg_form' method='post' action='controller'>
			<input type='hidden' name='op' value='write_reply_msg'>
			<input type='hidden' name='sm' value='<%=request.getParameter("sm") %>'>
			<input type='hidden' name='search' value='<%=request.getParameter("search") %>'>
			<input type='hidden' name='stext' value='<%=request.getParameter("stext") %>'>	
			<input type='hidden' name='scategory' value='<%=request.getParameter("scategory") %>'>				
			<input type='hidden' name='section' value='<%=request.getParameter("section") %>'>
			<input type='hidden' name='page' value='<%=request.getParameter("page") %>'>
			<input type='hidden' name='community_index' value=''>
			<input type='hidden' name='in_response_to' value=''>
			<input type='hidden' name='topic_number' value=''>
			<table border=0 cellspacing=2 cellpadding=4 width='100%' style="padding: 0 0 0 10px;"> 
				<tr>
					<td class="qa_td">Subject</td>
					<td width="80%" colspan=2>
						<input id="qa_subject" name="qa_subject" type=text size=80 value='<%=request.getParameter("subject") %>' maxlength="<%=Utils.SUBJECT_LIMIT %>" class=input_text style='imemode:inactive' />
					</td> 
				</tr>
				<tr>
					<td class="qa_td">Writer</td>
					<td width="25%" colspan=2><input type=text size=10 id="qa_name" name="qa_name" value='<%=(String)session.getAttribute("login_name") %>' maxlength="80" class=input_text style='imemode:inactive; color:#616161; border: 0;' readonly /></td> 
				</tr>
				<tr>
					<td class="qa_td">E-Mail</td>
					<td width="25%"><input id="qa_email" name="qa_email" type=text size=26 value='<%=(String)session.getAttribute("login_email") %>' maxlength="80" class=input_text style='imemode:inactive; color:#616161; border: 0;' readonly/></td> 
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
					<td width="25%" colspan=2 nowrap><input id="qa_security_code" name="qa_security_code" type=password size=10 value='${security_code}' maxlength="5" class=input_text style='imemode:inactive' disabled="disabled"/>
								<span><b></b></span><input type='hidden' id='qa_security_code2' value='${security_code}'>
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
						<input type='button' value='Cancel' style="vertical-align: bottom;cursor:pointer;" onclick="removeRowFromTable('reply_message_table');mode=null;"/>
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