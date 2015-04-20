<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*;" %>

<script language="Javascript" type="text/javascript">
	var mode="";
	var community_index=0;
	var in_response_to="";
	var _topic_number=0;
	//var _subject="";
	function replyQaMessage(c_index,qa_class,name,topic_number,subject)
	{
		community_index=c_index;
		in_response_to=name;
		_topic_number=topic_number;
		//_subject=subject;
		
		if(mode!="qa_write")
		{
			var paramString="&search=<%=request.getParameter("search")%>"+
							"&stext=<%=request.getParameter("stext")%>"+
							"&scategory=<%=request.getParameter("scategory")%>"+
							"&section=<%=request.getParameter("section")%>"+
							"&page=<%=request.getParameter("page")%>";
			httpRequestPost("<%= MenuLink.CONTEXT %>","op=get_reply_page&sm=1&subject=Re: "+subject+"&qa_class="+qa_class+paramString,"replyQaResponse");
		}
		else alert("<%= Message.info_qa_writting_mode%>");
	}
	function replyQaResponse(strResponse)
	{
		if(strResponse.indexOf("session_out")!=-1)
		{
			logout_button();
			alert("<%=Message.session_out%>");
		}
		else if(strResponse.indexOf('false:')==-1)//not found
		{
			document.getElementById('qa_write_div').innerHTML = strResponse;
			mode="qa_write";
		}
		else 
		{
			alert(strResponse.substring(6));
		}
	}
	function sendQaMsg()
	{
		document.getElementById("qa_subject").value=removeSpecialChars(document.getElementById("qa_subject").value);
		document.getElementById("qa_subject").value=trim(document.getElementById("qa_subject").value);
		document.getElementById("qa_delete_password").value=trim(document.getElementById("qa_delete_password").value);
		document.getElementById("qa_security_code").value=trim(document.getElementById("qa_security_code").value);
		
		if(document.getElementById("qa_subject").value.length<1) alert("<%= Message.check_subject %>");
		else if(!isAlphaNumeric(document.getElementById("qa_delete_password"))) alert("<%= Message.incorrect_delete_password%>");
		else if(document.getElementById("qa_security_code").value.toUpperCase()!=document.getElementById("qa_security_code2").value) alert("<%= Message.check_security_code%>");
		else 
		{
			document.getElementById("qa_message").value=trim(document.getElementById("qa_message").value);
			if(document.getElementById("qa_message").value.length<2) alert("<%= Message.check_text_msg %>");
			else 
			{
				this.document.new_qa_reply_msg_form.community_index.value=community_index;
				this.document.new_qa_reply_msg_form.in_response_to.value=in_response_to;
				this.document.new_qa_reply_msg_form.topic_number.value=_topic_number;
				this.document.new_qa_reply_msg_form.submit();		
			}
		}
		

	}
</script>
<div id="qa_body_div">
<table cellpadding="0" cellspacing="0" width="100%"> 
	<tr><td height="10"></td> </tr>
	<tr><td align="center">
		<div id="qa_write_div"></div>
		<div id="qa_table_div">
		<table border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff width="100%"> 
			<tr> 
				<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
				<td height=10 background='images/table/horizontal_top.gif'></td> 
				<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
			</tr> 
			<tr> 
				<td width=10 background='images/table/vertical_left.gif'></td> 
				<td  bgcolor=#ffffff valign="top" align="left"> 
					<table border=0 cellspacing=2 cellpadding=4 width='100%' style="padding: 0px 10px 0px 10px;"> 
						<tr><td colspan=2>
							<table height="40" width="710" background="images/common/title.gif">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<span style="color: #ffffff; font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;Community News</span>										
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; color: #ffffff;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;Community News
										</td>
									</tr>					
								</table>	
							</td>
						</tr>
						<tr>
							<td colspan=2 width='100%' align='right'><a onfocus=this.blur() href='<%=MenuLink.getPubLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),Integer.parseInt(request.getParameter("section")),Integer.parseInt(request.getParameter("page"))) %>'><img src="images/common/arrow_left.gif" style="vertical-align: middle;"/>
								Back to the list</a>
							</td>
						</tr>
						<tr><td colspan=2 height=1 class=dv_line></td></tr> 
						<tr><td colspan=2>
	 					<% 
	 						List qa_contents_list=(List)request.getAttribute("qa_contents_list");
	 						for(int i=0;i<qa_contents_list.size();i++)
	 						{
	 							out.print("<table border=1 cellspacing=0 cellpadding=0 width='100%' style='border-collapse: collapse; border-color: #CBCCC6; border-style: dotted;'>");
	 							out.print("<tr><td height=26 style='border-style: dotted;border-width: 1px;border-color: #CBCCC6;'>");
	 								out.print("<table border=0 cellspacing=0 cellpadding=4 width='100%' >");
	 									out.print("<tr bgcolor='#fafafa'><td width=16><img src='images/common/subject2.png' style='vertical-align: middle;'/></td>");
	 									out.print("    <td width='65%' style='font-weight: bold;'>");
	 									if(((CommunityBean)qa_contents_list.get(i)).getTopic_subject().length()>(Utils.SUBJECT_LIMIT))
	 										out.print(((CommunityBean)qa_contents_list.get(i)).getTopic_subject().substring(0,(Utils.SUBJECT_LIMIT-3))+"...</td>");
	 									else
	 										out.print(((CommunityBean)qa_contents_list.get(i)).getTopic_subject()+"</td>");
	 									out.print("    <td align='right' width='32%' style='font-size:8pt;'>"+Utils.getDateTime(((CommunityBean)qa_contents_list.get(i)).getWritten_date())+"&nbsp;");
	 									
										//the following is not implemented because caliperproject.ca is not interactive
	 									String login_email=(String)session.getAttribute("login_email"); 
	 									if(login_email!=null && login_email.equals(Utils.caliper_email_address))
	 										out.print("|&nbsp;<a onfocus=this.blur() href='#' onclick=\"if(check_login()) replyQaMessage("+((CommunityBean)qa_contents_list.get(i)).getCommunity_index()+","+
	 												((CommunityBean)qa_contents_list.get(i)).getQa_class()+",'"+
	 												((CommunityBean)qa_contents_list.get(i)).getPerson_name()+"',"+
	 												((CommunityBean)qa_contents_list.get(i)).getTopic_number()+",'"+
	 												((CommunityBean)qa_contents_list.get(i)).getTopic_subject()+"'); return false;\">"+
													   "<img src='images/common/reply.gif' style='vertical-align: middle;'>Re</a>");
	 									out.println("</td></tr>");
	 								out.print("</table></td></tr>");
 								out.print("<tr><td style='padding: 25px 25px 25px 27px;'>");

	 							out.print(((CommunityBean)qa_contents_list.get(i)).getContents()+"</td></tr>");
	 							out.print("</table><br>");
	 						}
	 					%> 
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
		</div>
 	</td>
	</tr>
</table>  
</div>