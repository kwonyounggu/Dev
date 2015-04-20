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
								<table height="40" width="100%" border=0 cellspacing=0 cellpadding=0>
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">CALIPER via Youtube</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER via Youtube
										</td>
									</tr>
									<tr><td colspan='2'><hr/></td></tr>						
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td>
						</tr>					
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-size:10pt; font-family: arial; vertical-align: middle;">
							<h3><span style="color: #3973AC;">CALIPER purpose</span></h3>
							<p>The purpose of the project is to establish a current and accurate database of reference intervals (normal values) that represent Canada’s children and youth - multi-ethnic, males and females, from birth to 18 years.</p>
							<p>CALIPER is a multi-centre project among several children's hospitals across Canada. Its goal is to establish a database of pediatric reference intervals for common blood tests ordered by physicians. </p>
							<h3><span style="color: #3973AC;">CALIPER participant</span></h3>
							<p>What will happen at my appointment? What do I have to do to participate? Will the needle hurt? Why should I participate in CALIPER? Follow Inika as she participates in CALIPER, helps sick children all across Canada and answers all your questions!</p>
							<p align="center">
								<object style="height: 390px; width: 640px"><param name="movie" value="http://www.youtube.com/v/N1a2mDeqvHA?version=3"><param name="allowFullScreen" value="true"><param name="allowScriptAccess" value="always"><embed src="http://www.youtube.com/v/N1a2mDeqvHA?version=3" type="application/x-shockwave-flash" allowfullscreen="true" allowScriptAccess="always" width="640" height="390"></object>
							</p>
							<p>For more information, please <a href="<%= MenuLink.contactus %>" onfocus="this.blur()" title="" style="color: #519DC7;">Contact Us</a></p>
							</span>
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