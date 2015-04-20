<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*;" %>

<script language="Javascript" type="text/javascript">
	var mode="";
	function writeQaMessage()
	{
		if(mode!="qa_write")
			httpRequestPost("<%= MenuLink.CONTEXT %>","op=get_write_page&sm=2","writeQaResponse");
		else alert("<%= Message.info_qa_writting_mode%>");
	}
	function writeQaResponse(strResponse)
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
			else this.document.new_qa_msg_form.submit();		
		}
	}
	function searchButton()
	{
		this.document.qa_search_form.stext.value=removeSpecialChars(this.document.qa_search_form.stext.value);
		this.document.qa_search_form.stext.value=trim(this.document.qa_search_form.stext.value);
		if(this.document.qa_search_form.stext.value.length<1) 
		{
			this.document.qa_search_form.search.value=0;
			alert("<%=Message.check_search_word%>");
		}
		else this.document.qa_search_form.submit();
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
					<table border=0 cellspacing=2 cellpadding=4 width='100%' style="padding: 0 0 0 10px;"> 
						<tr>
						    <td colspan=5>
								<img src="images/common/blue_circle.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">Questions and Answers</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  <img src="images/common/question.gif" style="vertical-align: middle;"/>&nbsp;No Answer&nbsp;&nbsp;&nbsp;
										  <img src="images/common/question_resolved.gif" style="vertical-align: middle;"/>&nbsp;Replied
							</td>
						</tr>
						<tr><td colspan=5 height=1 class=dv_line></td></tr>
	 					<tr> 
							<th class=header2_td width="56%">Subject</th>
							<th class=header2_td width="15%">Writer</th>
							<th class=header2_td width="8%">See</th>
							<th class=header2_td width="6%">Resp</th>
							<th class=header2_td width="15%">Date</th>
						</tr> 
						
						<%  //do error handler
							int total_count=((Integer)request.getAttribute("total_count")).intValue();
							int iPage=Integer.parseInt((String)request.getParameter("page"));
							int iSection=Integer.parseInt((String)request.getParameter("section"));
							
							if(iPage>(total_count/Utils.displayLimit+1) || iSection>((total_count/Utils.displayLimit)/10+1)) iPage=iSection=1;// due to hacking
							
							//System.out.println(">>>>>< page="+iPage+" total count="+total_count+", section="+iSection);
							List qa_msg_list=(List)request.getAttribute("qa_msg_list");
							for(int j=0;j<qa_msg_list.size();j++)
							{
								if((j+1)%2==0) out.print("<tr style='background-color:#F0F0DC;'>");
								else out.print("<tr style='background-color:#FFEED6;'>");
								out.print("<td width='56%'>");
								if(((CommunityBean)qa_msg_list.get(j)).getReplies()>0) out.print("<img src='images/common/question_resolved.gif' style='vertical-align: middle;'/>&nbsp;");
								else out.print("<img src='images/common/question.gif' style='vertical-align: middle;'/>&nbsp;");
								out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),iSection,iPage)+
																	"&community_index="+((CommunityBean)qa_msg_list.get(j)).getCommunity_index()+
																	"&topic_number="+((CommunityBean)qa_msg_list.get(j)).getTopic_number()+"'>");																	
								if(((CommunityBean)qa_msg_list.get(j)).getTopic_subject().length()>Utils.SUBJECT_LIMIT)
									out.print(((CommunityBean)qa_msg_list.get(j)).getTopic_subject().substring(0,Utils.SUBJECT_LIMIT)+"...</a></td>");
								else
									out.print(((CommunityBean)qa_msg_list.get(j)).getTopic_subject()+"</a></td>");
								out.print("<td align='center' width='15%'>"+((CommunityBean)qa_msg_list.get(j)).getPerson_name()+"</td>");
								out.print("<td align='center' width='8%'>"+((CommunityBean)qa_msg_list.get(j)).getAccess_number()+"</td>");
								out.print("<td align='center' width='6%'>"+((CommunityBean)qa_msg_list.get(j)).getReplies()+"</td>");
								out.print("<td align='center' width='15%'>"+Utils.getDate(((CommunityBean)qa_msg_list.get(j)).getWritten_date())+"</td></tr>");
							}
							out.print("<tr><td colspan=5><p style='text-indent:0; line-height:100%; margin:0;' align='center'><img src='images/join/foot_line.gif' width='100%' height='13' border='0'></p></td></tr>");
							//out.print("<tr><td colspan=5 height=1 class=dv_line></td></tr> ");
							out.print("<tr>");
									out.print("<td colspan=5 >");
									out.print("<p style='line-height:100%; margin-top:0; margin-bottom:0;' align='center'>");
									out.print("<span style='font-size:9pt;'>");	
									if(iPage>1)
									{
										out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),1,1)+"'>");
										out.print("<img src='images/common/tostart.gif' border='0' style='vertical-align: middle;'/>");
										out.println("</a>");
									}
									else out.print("<img src='images/common/tostart.gif' border='0' style='vertical-align: middle;'/>");
									
									if(iSection>1)
									{
										out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),(iSection-1),((iSection-1)*10-9))+"'>");
										out.print("<img src='images/common/backward.gif' border='0' style='vertical-align: middle;'/>&nbsp;");
										out.println("</a>");
									}
									else out.print("<img src='images/common/backward.gif' border='0' style='vertical-align: middle;'/>&nbsp;");
									
									for(int i=((iSection-1)*10+1), j=((iSection-1)*10+10);i<=j;i++)
									{
										if((i-1)*Utils.displayLimit<total_count)
										{
											out.print("<a onfocus=this.blur() style='text-decoration: underline;' href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),iSection,i)+"'>");
											if(i==iPage) out.print("<b>"+i+"</b></a> .");
											else if(i==j)out.print(i+"</a>&nbsp;");
											else out.print(i+"</a> .");
										}
										else
										{
											if(i!=j) out.print(i+" .");
											else out.print(i+"&nbsp;");
										}
									}
									
									if(total_count>(iSection*Utils.displayLimit*10))
									{
										out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),(iSection+1),((iSection+1)*10-9))+"'>");
										out.print("<img src='images/common/foward.gif' border='0' style='vertical-align: middle;'/>");
										out.println("</a>");
									}
									else out.print("<img src='images/common/foward.gif' border='0' style='vertical-align: middle;'/>");
									
									int d=total_count/(Utils.displayLimit*10);

									if(d>0)
									{
										if((total_count%(Utils.displayLimit*10*d))>0) //next section is d+1
										{
											if(iSection<(d+1))
											{
												out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),(d+1),((d+1)*10-9))+"'>");
												out.print("<img src='images/common/toend.gif' border='0' style='vertical-align: middle;'/>");
												out.println("</a>");
											}
											else out.print("<img src='images/common/toend.gif' border='0' style='vertical-align: middle;'/>");
										}
										else if(iSection<d)//next section is d
										{
											out.print("<a onfocus=this.blur() href='"+MenuLink.getQaLink(request.getParameter("search"),request.getParameter("stext"),request.getParameter("scategory"),d,(d*10-9))+"'>");
											out.print("<img src='images/common/toend.gif' border='0' style='vertical-align: middle;'/>");
											out.println("</a>");
										}
										else out.print("<img src='images/common/toend.gif' border='0' style='vertical-align: middle;'/>");
									}
									else //no move
									{
										out.print("<img src='images/common/toend.gif' border='0' style='vertical-align: middle;'/>");
									}
									
									out.print("</span>");
									out.print("</p>"); 						 
									out.print("</td>"); 
							out.print("</tr>");
						
						%>

						<tr><td colspan=5 height=1 class=dv_line></td></tr>
						<tr>
							<form name='qa_search_form' method='post' action='controller'>
							<td width='56%' >
										<img src="images/common/blue_circle.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">By</span>								
										<input type='hidden' name='op' value='menu_community_q_and_a'>
										<input type='hidden' name='sm' value='2'>
										<input type='hidden' name='section' value='1'>
										<input type='hidden' name='page' value='1'>
										<input type='hidden' name='search' value='1'>
									<select name="scategory"><option value=0 <%=request.getParameter("scategory").equals("0")? "selected": "" %>>Subject</option>
											<option value=1 <%=request.getParameter("scategory").equals("1")? "selected": "" %>>Contents</option>
											<option value=2 <%=request.getParameter("scategory").equals("2")? "selected": "" %>>Writer</option>
									</select>&nbsp;
									<input type=text name="stext" size=20 value='<%=request.getParameter("stext") %>' maxlength="50" class=input_text style='imemode:inactive' onKeyDown="if(event.keyCode==13) {searchButton(); return false;}"/> 
									<a onfocus=this.blur() href="#" onclick="javascript:searchButton(); return false;"><img src="images/common/search_16.gif" style="vertical-align: middle; cursor:pointer;" />Search Now</a>
									<% 
										String search=request.getParameter("search");
										if(search!=null && search.equals("1") && request.getParameter("stext")!=null)
											out.print("<a onfocus=this.blur() href='"+MenuLink.community_q_and_a+"'><img src='images/common/arrow_left.gif' style='vertical-align: middle; cursor:pointer;' />Back</a>");
									%>
								
							</td>
							</form>							
							<td colspan=4 align='right'><button type="button" style='cursor:pointer;' onclick="if(check_login()) writeQaMessage();">Write</button>
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