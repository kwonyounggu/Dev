<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>

<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_top.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_top.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_top.gif' width=10 height=10></td> 
	</tr> 

	<tr height="15">
		<td width=10 background='images/table/vertical_left.gif'></td>	
		<td></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left" > 
		  <table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 20px 60px 20px 60px; "> 
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="left" > 
					<table border=0 cellspacing=2 cellpadding=0 width='100%' style="padding: 0 0 0 0px;"> 						 									
						<tr><td><img src="images/common/little_earth.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:10pt; font-family: arial; vertical-align: middle;">&nbsp;&nbsp;<a href="<%= MenuLink.caliperacrosscanada %>" style="color: #3973AC;" onfocus="this.blur()">CALIPER Across Canada</a></span></td></tr>
						<tr><td height=10></td></tr>
						<tr><td>
								<img src="images/main_body/canadamap.jpg" style="vertical-align: middle;" width="247" height="141" usemap="#activity_map"/>					
							</td>
						</tr>
					 </table> 
				</td>
				<td  bgcolor=#ffffff valign="top" align="left"  > 
					<table border=0 cellspacing=2 cellpadding=0 width='100%' style="padding: 0 0px 0 0px;"> 		 									
						<tr><td><img src="images/common/little_earth.gif" style="vertical-align: middle;"/><span style="color: #3973AC; font-weight:bold; font-size:10pt; font-family: arial; vertical-align: middle;">&nbsp;&nbsp;<a href="<%= MenuLink.children_and_families %>" style="color: #3973AC;" onfocus="this.blur()">Children and Families</a></span></td></tr>
						<tr><td height=10></td></tr>
						<tr><td>
								<a href="<%= MenuLink.children_and_families %>" onfocus="this.blur()">
									<img src="images/main_body/children_and_family.gif" style="vertical-align: middle;" width="247" height="141"/>
								</a>				
							</td>
						</tr>
					 </table> 
				</td>
				<td  bgcolor=#ffffff valign="top" align="left"  > 
					<table border=0 cellspacing=2 cellpadding=0 width='100%' style="padding: 0 0px 0 0px; table-layout:fixed; overflow: hidden;"> 		 									
						<tr><td><img src="images/common/little_earth.gif" style="vertical-align: middle;"/>&nbsp;&nbsp;<a onfocus=this.blur() href="<%= MenuLink.community_news %>" style="color: #3973AC; font-weight:bold; font-family: arial; vertical-align: middle;">CALIPER Community News</a></td></tr>
						<tr><td height=10></td></tr>
						
						<tr><td>
								<a onfocus=this.blur() href="<%= MenuLink.community_news %>"><img src="images/main_body/communitynews.gif" style="vertical-align: middle;" width="247" height="116"/></a>					
							</td>
						</tr>
						<tr ><td style="padding-right:25px; padding-left:25px;" >
								<span style="font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;
									<marquee behavior="scroll" direction="left" scrollamount="5" onmouseover="this.stop();" onmouseout="this.start();">
									<% 
									ConnectionPool cp=(ConnectionPool)application.getAttribute("connectionPool");
									CommunityDao cDao=new CommunityDao(cp);
									//request.setAttribute("total_count", new Integer(cDao.getCount("select count(distinct topic_number) from community where community_class=1")));
									int pageNo=1;
									int offset=(pageNo-1)*Utils.displayLimit;
									
									List<CommunityBean> top10NewsList= (List<CommunityBean>) cDao.getCommunityList2("where community_class=1 group by topic_number order by written_date desc limit "+Utils.displayLimit+" offset "+offset);
									
									for(int i=0;i<top10NewsList.size();i++)
									{
										CommunityBean cb=(CommunityBean)top10NewsList.get(i);
										out.print("<a onfocus=this.blur() href='"+MenuLink.getPubLink("0","","0",1,1)+
												"&community_index="+((CommunityBean)top10NewsList.get(i)).getCommunity_index()+
												"&topic_number="+((CommunityBean)top10NewsList.get(i)).getTopic_number()+"'>");	
										if(((CommunityBean)top10NewsList.get(i)).getTopic_subject().length()>Utils.SUBJECT_LIMIT)
											out.print(((CommunityBean)top10NewsList.get(i)).getTopic_subject().substring(0,Utils.SUBJECT_LIMIT-3)+"...</a>&nbsp;|&nbsp;");
										else
											out.print(((CommunityBean)top10NewsList.get(i)).getTopic_subject()+"</a>&nbsp;|&nbsp;");
									}
									
									%>
									</marquee>
								</span>
							 </td>
						</tr>
					 </table> 
				</td> 
			</tr> 
		
			<tr height="25"><td colspan=3></td></tr>
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="center"  > 
						<img src="images/main_body/new_donation.gif" height="240" width="240" style="vertical-align: middle;"/>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  > 
				  <a href="<%= MenuLink.volunteering_program %>" onfocus="this.blur()" >
					<img src="images/main_body/volunteering_program.gif" height="240" width="240" style="vertical-align: middle;"/>
				  </a>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  > 
					<img src="images/main_body/new_social_media.gif" height="240" width="240" style="vertical-align: middle;" usemap="#social_media_map"/>
				</td> 
			</tr>
		
			<tr height="25"><td colspan=3></td></tr>
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="center"  > 
					<a href="<%= MenuLink.howtoparticipate_register %>" onfocus="this.blur()" >
						<img src="images/main_body/register_to_participate.gif" height="100" width="240" style="vertical-align: middle;"/>
					</a>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  > 
				  <a href="<%= MenuLink.whatiscaliper %>" onfocus="this.blur()" >
					<img src="images/main_body/what_is_caliper.gif" height="100" width="240" style="vertical-align: middle;"/>
				  </a>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  > 
				   <a href="<%= MenuLink.storybook %>" onfocus="this.blur()" >
					<img src="images/main_body/storybook.gif" height="100" width="240" style="vertical-align: middle;"/>
				   </a>
				</td> 
			</tr>
			</table>
		</td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr>
	<tr> 
		<td width=10 background='images/table/vertical_left.gif'></td> 
		<td  bgcolor=#ffffff valign="top" align="left"  height="10" ></td>
		<td width=10 background='images/table/vertical_right.gif'></td> 
	</tr> 
	<tr> 
		<td width=10 height=10><img src='images/table/round_lhs_bottom.gif' width=10 height=10></td> 
		<td height=10 background='images/table/horizontal_bottom.gif'></td> 
		<td width=10 height=10><img src='images/table/round_rhs_bottom.gif' width=10 height=10></td> 
	</tr> 
</table> 
<MAP NAME="activity_map">
		<AREA SHAPE="rect"  COORDS="6,71,47,87" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_vancouver_childrens %>" TARGET="_self" title="Vancouver">
		<AREA SHAPE="rect"  COORDS="54,67,87,83" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_saskatoon_healthregion %>" TARGET="_self" title="Saskatoon">
		<AREA SHAPE="rect"  COORDS="151,96,181,110" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_montreal_ste_justine %>" TARGET="_self" title="Montreal">
		<AREA SHAPE="rect"  COORDS="142,105,172,118" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_ottawa_childrens %>" TARGET="_self" title="Ottawa">
		<AREA SHAPE="rect"  COORDS="158,113,188,126" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_toronto_sickkids %>" TARGET="_self" title="Toronto">
		<AREA SHAPE="rect"  COORDS="156,120,186,133" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_hamilton_health_sciences %>" TARGET="_self" title="Hamilton">
		<AREA SHAPE="rect"  COORDS="219,49,244,64" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_newfoundland_stjohns %>" TARGET="_self" title="St.John's">
	</MAP>
	<MAP NAME="social_media_map">
		<AREA SHAPE="rect"  COORDS="58,60,218,105" onfocus="this.blur()" HREF="<%=MenuLink.facebook %>" TARGET="_blank" title="Facebook">
		<AREA SHAPE="rect"  COORDS="58,123,218,166" onfocus="this.blur()" HREF="<%=MenuLink.twitter %>" TARGET="_blank" title="Twitter">
		<AREA SHAPE="rect"  COORDS="58,182,218,223" onfocus="this.blur()" HREF="<%=MenuLink.youtube %>" TARGET="_self" title="Youtube">
	</MAP>			