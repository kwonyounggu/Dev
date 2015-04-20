<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>


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
								<table height="40" width="710" background="images/common/title.gif">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<span style="color: #ffffff; font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;CALIPER Clinics</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; color: #ffffff;">
											<img src='images/common/location_pin.png' width=16 height=16>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Clinics
										</td>
									</tr>					
								</table>	
							</td>
						</tr>

						<tr><td colspan=4 style="font-size:10pt; font-family: arial; vertical-align: middle; word-wrap: break-word;">						
								<p><p style=" margin-left: 10.0px;"><b>To schedule your CALIPER appointment</b>, please contact Jennifer Clarke:</p>
								   <ul><li>phone: 416-813-7654 ext. 2673,  or</li><li>e-mail: jennifer.clarke@sickkids.ca</li></ul>
								   <p style=" margin-left: 10.0px;"><b>Options for CALIPER Appointments:</b></p>
								   <table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tbody>
										<tr bgcolor="#ff6699" style=" color: #ffffff;" height=28>
											<td align="left" width="180"><span style=" color: #ffffff;font-weight:bold;">&nbsp;&nbsp;Location</span></td>
											<td align="left" width="269"><span style=" color: #ffffff;font-weight:bold;">Address</span></td>
											<td align="left" width="274"><span style=" color: #ffffff;font-weight:bold;">Days &amp; Time</span></td>
										</tr>
										<tr valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;SickKids</span></td>
											<td align="left" width="269"><p><span>555 University  Ave, Toronto</span></p></td>
											<td align="left" width="274"><p><b>Monday - Friday<br /></b><span>9:30 a.m. to 4:30 p.m.</span></p></td>
										</tr>
										<tr bgcolor="#fff0f0" valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;Gamma - Dynacare</span></td>
											<td align="left" width="269"><p><span>1849 Yonge Street, LF #1, Toronto</span><br /><span>Intersection: Yonge &amp; Davisville</span></p></td>
											<td align="left" width="274"><p><b>Wednesday</b><span>: 2:00 p.m. to 5:00 p.m.</span><br /><b>Friday</b><span>: 1:30 p.m. to 3:30 p.m.</span></p></td>
										</tr>
										<tr valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;Gamma - Dynacare</span></td>
											<td align="left" width="269"><p><span>2401 Yonge Street, LL #9, Toronto</span><br /><span>Intersection: Yonge &amp; Eglinton</span></p></td>
											<td align="left" width="274"><p><b>Wednesday</b><span>: 2:00 p.m. to 5:00 p.m.</span><br /><b>Friday</b><span>: 1:30 p.m. to 3:30 p.m.</span></p></td>
										</tr>
										<tr bgcolor="#fff0f0" valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;Gamma - Dynacare</span></td>
											<td align="left" width="269"><p><span>4040 Finch Ave E, LL #5, Scarborough</span><br /><span>Intersection: Finch &amp; Kenedy</span></p></td>
											<td align="left" width="274"><p><b>Wednesday</b><span>: 2:00 p.m. to 5:00 p.m.</span><br /><b>Friday</b><span>: 1:30 p.m. to 3:30 p.m.</span></p></td>
										</tr>
										<tr valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;Gamma - Dynacare</span></td>
											<td align="left" width="269"><p><span>2000 Credit Valley Rd, #107, Mississauga</span><br /><span>Intersection: Credit Valley &amp; Eglinton</span></p></td>
											<td align="left" width="274"><p><b>Tuesday - Thursday</b><span>: 2:00 p.m. to 6:00 p.m.</span><br /><b>Friday</b><span>: 1:30 p.m. to 4:00 p.m.</span></p></td>
										</tr>
										<tr bgcolor="#fff0f0" valign="middle">
											<td align="left" width="180"><span style=" color: #519DC7;font-weight:bold;">&nbsp;&nbsp;Gamma - Dynacare</span></td>
											<td align="left" width="269"><p><span>4040 Bayview Ave, #203, Toronto</span><br /><span>Intersection: Bayview &amp;Finch</span></p></td>
											<td align="left" width="274"><p><b>Wednesday</b><span>: 2:00 p.m. to 5:00 p.m.</span><br /><b>Friday</b><span>: 1:30 p.m. to 3:00 p.m.</span></p></td>
										</tr>
									</tbody>
									</table>
								</p>	
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