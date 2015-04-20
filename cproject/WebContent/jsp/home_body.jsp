<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sickkids.caliper.database.ConnectionPool" %>

<table height="100%" width="970" border=0 cellpadding=0 cellspacing=0 bgcolor=#ffffff> 
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
		<td  bgcolor=#ffffff valign="top" align="center" > 
		  <table border=0 cellspacing=0 cellpadding=0 width='100%' > 
			<tr height="25"><td colspan=3></td></tr>
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="left" width='33%'> 
					<table border=0 cellspacing=0 cellpadding=0 width='100%'> 						 									
						<tr><td style="padding-left: 60px;"><img src="images/common/little_earth.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:11pt; font-family: arial; vertical-align: middle;">&nbsp;&nbsp;<a href="<%= MenuLink.whatiscaliper %>" style="color: #3973AC;" onfocus="this.blur()">What is CALIPER?</a></span></td></tr>
						<tr><td height=10></td></tr>
						<tr><td align="center">
							   <a href="<%= MenuLink.whatiscaliper %>" onfocus="this.blur()" >
								<img src="images/main_body/what_is_caliper_247x141.gif" style="vertical-align: middle;"  width="247" height="141"/>	
							   </a>				
							</td>
						</tr>
					 </table> 
				</td>
				<td  bgcolor=#ffffff valign="top" align="left"  width='33%'> 
					<table border=0 cellspacing=0 cellpadding=0 width='100%'> 		 									
						<tr><td style="padding-left: 40px;"><img src="images/common/little_earth.gif" style="vertical-align: middle;"/><span style="color: #3973AC; font-weight:bold; font-size:11pt; font-family: arial; vertical-align: middle;">&nbsp;&nbsp;<a href="<%= MenuLink.children_and_families %>" style="color: #3973AC;" onfocus="this.blur()">Children and Families</a></span></td></tr>
						<tr><td height=10></td></tr>
						<tr><td>
								<a href="<%= MenuLink.children_and_families %>" onfocus="this.blur()">
									<img src="images/main_body/children_and_family.gif" style="vertical-align: middle;" width="247" height="141"/>
								</a>				
							</td>
						</tr>
					 </table> 
				</td>
				<td  bgcolor=#ffffff valign="top" align="left"  width='33%'> 
					<table border=0 cellspacing=0 cellpadding=0 width='100%' style="padding: 0 0px 0 0px; table-layout:fixed; overflow: hidden;" align="center"  > 		 									
						<tr><td style="padding-left: 40px;"><img src="images/common/little_earth.gif" style="vertical-align: middle;"/>&nbsp;&nbsp;<a onfocus=this.blur() href="<%= MenuLink.aboutcaliper_calipermoment %>" style="color: #3973AC; font-weight:bold; font-family: arial; vertical-align: middle;">CALIPER Moments</a></td></tr>
						<tr><td height=10></td></tr>
						
						<tr><td>
								<a onfocus=this.blur() href="<%= MenuLink.aboutcaliper_calipermoment %>"><img src="images/main_body/calipermoment.png" style="vertical-align: middle;" width="247" height="141"/></a>					
							</td>
						</tr>
						<tr ><td style="padding-right:25px; padding-left:25px;" >
								<!-- removed the community news contents Aug-14-2014 -->
							 </td>
						</tr>
					 </table> 
				</td> 
			</tr> 
		
			<tr height="40"><td colspan=3></td></tr>
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 
						<img src="images/main_body/caliper_across_canada.png" height="247" width="240" align="center"  style="vertical-align: middle;" usemap="#activity_map"/>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 
					<table height="240" width="240" border=0 cellpadding='0' cellspacing='0' background="images/main_body/caliper_event.png" >						
						<% 
							/*SQLDao sqlDao=(SQLDao)application.getAttribute("sqlDao");
							
							if(sqlDao==null)
							{
								System.out.println("ERROR: application object sqlDao is null in home_body.jsp.");
								throw new Exception("ERROR: application object sqlDao is null in home_body.jsp.");
							}
							List<EventManagementBean> list=(List<EventManagementBean>)sqlDao.getEventList();
							if(list.size()>0)
							{
								EventManagementBean eb=list.get(0);
								out.println("<tr height='35'><td></td><td align='right' valign='bottom' ><a href='/cproject/controller?op=display_event&pdf_file_path="+eb.getEvent_file_location()+"' style='font-size:8.5pt; font-weight: bold; color: #cc003a'>MORE INFO ></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
								out.println("<tr><td width='15%' align='left' style='font-size:8.5pt; font-weight: bold;'>&nbsp;&nbsp;&nbsp;&nbsp;Title: </td><td align='left' width='85%' style='font-size:8.5pt;'><a href='/cproject/controller?op=display_event&pdf_file_path="+eb.getEvent_file_location()+"' title='"+eb.getEvent_subject()+"'>"+eb.getShortEventTitle()+"</a></td></tr>");
								out.println("<tr><td colspan='2' align='left' style='font-size:8.5pt; font-weight: bold;'>&nbsp;&nbsp;&nbsp;&nbsp;Summary:</td></tr>");
								out.println("<tr><td colspan='2' align='left' style='font-size:8.5pt; padding-left: 10px; padding-right:8px;'>&nbsp;&nbsp;<a href='/cproject/controller?op=display_event&pdf_file_path="+eb.getEvent_file_location()+"' title='"+eb.getEvent_summary()+"'>"+eb.getShortEventSummary()+"</a></td></tr>");
								out.println("<tr><td width='15%' align='left' style='font-size:8.5pt; font-weight: bold;'>&nbsp;&nbsp;&nbsp;&nbsp;Location:</td><td align='left' style='font-size:8.5pt;'>&nbsp;&nbsp;<a title='"+eb.getEvent_location()+"' >"+eb.getShortEventLocation()+"</a></td></tr>");
								out.println("<tr><td width='15%' align='left' style='font-size:8.5pt; font-weight: bold;'>&nbsp;&nbsp;&nbsp;&nbsp;Date:</td><td align='left' style='font-size:8pt;'>&nbsp;&nbsp;"+Utils.getCADate(eb.getEvent_date())+"</td></tr>");
							}
							else
							{
								out.println("<tr><td colspan='2' align='center' valign='middle'><img src='images/admin/no_events.png'/></td></tr>");
							}*/
						%>
						<tr height='65'><td></td><td></td></tr>
						<tr><td colspan='2' style="padding-left: 10px;font-size: 10px;">
								<ul style='padding-left: 20px;'><span style='font-weight: bold;'>Caliper Promotions</span><li style='font-size: 9px;line-height: 12px;  '>Aug 17 2013: Burlington’s Children’s Festival</li>
									<li style='font-size: 9px;line-height: 12px;  '>Jun 1 2013: MacKids Walk & Wheel event</li>
									<li style='font-size: 9px;line-height: 12px;  '>May 31 2014: MacKids Walk & Wheel event</li>
								</ul>
							</td>
						</tr>
						<tr><td colspan='2' style="padding-left: 10px;font-size: 10px;">
								<ul style='padding-left: 20px;'><span style='font-weight: bold;'>Caliper Clinics</span>
									<li style='font-size: 9px;line-height: 12px;  '><a onfocus=this.blur() href="pdf/events_hamilton_toronto.pdf" style="color: #3973AC; font-weight:normal; font-family: arial; vertical-align: middle;">Caliper Hamilton + GTA</a></li>
									<li style='font-size: 9px;line-height: 12px;  '><a onfocus=this.blur() href="pdf/events_hamilton.pdf" style="color: #3973AC; font-weight:normal; font-family: arial; vertical-align: middle;">Caliper McMaster-Hamilton Site</a></li>
									<li style='font-size: 9px;line-height: 12px;  '><a onfocus=this.blur() href="pdf/events_hwcdsb.pdf" style="color: #3973AC; font-weight:normal; font-family: arial; vertical-align: middle;">Caliper Hamilton CDSB</a></li>
								</ul>
							</td>
						</tr>
					</table>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 
					<a href="<%= MenuLink.howtoparticipate_register %>" onfocus="this.blur()" >
						<img src="images/main_body/register_to_participate.png" height="240" width="240" align="center"  style="vertical-align: middle;" />
					</a>
				</td> 
			</tr>
		
			<tr height="40"><td colspan=3></td></tr>
			<tr> 
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 				
						<img src="images/main_body/donate.png" height="100" width="240" align="center"  style="vertical-align: middle;"/>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 
				  <a href="<%= MenuLink.volunteering_program %>" onfocus="this.blur()" >
					<img src="images/main_body/volunteering_program.png" height="100" width="240" align="center"  style="vertical-align: middle;"/>
				  </a>
				</td>
				<td  bgcolor=#ffffff valign="top" align="center"  width='33%'> 
				   <img src="images/main_body/new_social_media.png" height="100" width="240" align="center"  style="vertical-align: middle;" usemap="#social_media_map"/>
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
		<AREA SHAPE="rect"  COORDS="0,130,52,147" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_vancouver_childrens %>" TARGET="_self" title="Vancouver">
		<AREA SHAPE="rect"  COORDS="56,128,108,145" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_saskatoon_healthregion %>" TARGET="_self" title="Saskatoon">
		<AREA SHAPE="rect"  COORDS="152,151,204,168" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_montreal_ste_justine %>" TARGET="_self" title="Montreal">
		<AREA SHAPE="rect"  COORDS="135,166,175,183" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_ottawa_childrens %>" TARGET="_self" title="Ottawa">
		<AREA SHAPE="rect"  COORDS="172,168,212,185" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_toronto_sickkids %>" TARGET="_self" title="Toronto">
		<AREA SHAPE="rect"  COORDS="156,181,208,198" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_hamilton_health_sciences %>" TARGET="_self" title="Hamilton">
		<AREA SHAPE="rect"  COORDS="200,108,244,125" onfocus="this.blur()" HREF="<%=MenuLink.caliperacrosscanada_hospital_newfoundland_stjohns %>" TARGET="_self" title="St.John's">
	</MAP>
	<MAP NAME="social_media_map">
		<AREA SHAPE="rect"  COORDS="163,35,225,97" onfocus="this.blur()" HREF="<%=MenuLink.facebook %>" TARGET="_blank" title="Facebook">
		<AREA SHAPE="rect"  COORDS="89,35,151,97" onfocus="this.blur()" HREF="<%=MenuLink.twitter %>" TARGET="_blank" title="Twitter">
		<AREA SHAPE="rect"  COORDS="18,39,75,96" onfocus="this.blur()" HREF="<%=MenuLink.youtube %>" TARGET="_self" title="Youtube">
	</MAP>			