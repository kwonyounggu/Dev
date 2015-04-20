<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<style>
div.img 
{
    margin: 5px;
    padding: 5px;
    border: 1px solid #0000ff;
    height: 400px;
    width: auto;
    float: left;
    text-align: center;
}

div.img img 
{
    display: inline;
    margin: 5px;
    border: 1px solid #ffffff;
}

div.img a:hover img 
{
    border:1px solid #0000ff;    
}

div.desc 
{
    text-align: left;
    font-weight: normal;
    width: 360px;
    margin: 5px;
}
</style>

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
											<span style="font-weight:bold; font-size:12pt;">CALIPER Moments</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Moments
										</td>
									</tr>			
									<tr><td colspan='2'><hr/></td></tr>			
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4  style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">								
									<tr><td style=" width: 34px;"> </td>
										<td colspan="5"> </td>
									</tr>
									<tr><td rowspan="2" style=" width: 34px;">
											<img alt="CALIPER: Banner" src="images/main_body/29334-Caliper-Banner.png" style=" padding-bottom: 5px; padding-right: 5px; padding-top: 5px; padding-left: 5px;" />
										</td>
										<td colspan="5" style=" vertical-align: top; width: 978px; word-wrap: break-word;font-family: arial; padding: 0 0 25px 15px;">
											<p>Thanks to the dedication of our volunteers and the generosity of our participants, CALIPER will
											   help children and adolescents with medical concerns all across Canada. </p>
											<p>Over the past two years, we have held numerous successful clinics throughout the GTA and 
											   surrounding areas. </p>
											<p>Please enjoy with us the first albums of our CALIPER team and participants. Check back soon
											   for new album posts.</p>
										</td>
									</tr>
									<tr><td style=" vertical-align: top;"></td>
										<td style=" vertical-align: top;font-family: arial; padding: 0 0 0 20px;">
											<p><a href="pdf/29031-CALIPER-Album.pdf">
												<img alt="CALIPER Album 1" src="images/main_body/29335-Caliper-Image-Album1.png" style=" border: 1px solid black; padding-bottom: 0; padding-right: 0; padding-top: 0; padding-left: 0;" />
												</a>
											</p>
											<p>CALIPER: Album 1 (pdf)</p>
										</td>
										<td style=" vertical-align: top;font-family: arial;padding: 0 0 0 20px;">
											<p><a href="pdf/29317-CALIPER-Album2.pdf">
												<img alt="CALIPER Album 2" src="images/main_body/29336-Caliper-Image-Album2.png" style=" border: 1px solid black; padding-right: 0; padding-left: 0;" />
												</a>
											</p>
											<p>CALIPER: Album 2 (pdf)</p>
										</td>
										<td style=" vertical-align: top;"> </td>
										<td style=" vertical-align: top;"> </td>
									</tr>
									<tr><td style=" width: 34px;"> </td>
										<td colspan="5"> </td>
									</tr>
									
								</table>
								<br/>
								<table border=0 cellspacing=0 cellpadding=0 width='100%' height='100%'> 	
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos1_1.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Research Students and CALIPER Project Coordinator promoting CALIPER at McMaster Children’s Hospital Walk and Wheel event in Hamilton</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos1_2.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Research Students Petra, Jacalyn, Tara and Kimiya running the CALIPER tent at McMaster Children’s Hospital Walk and Wheel event in Hamilton</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos1_3.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Project Coordinator Olivia and a Walk and Wheel participant enjoying the event</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos2_1.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Research Students Jacalyn, Petra and Kimiya conducting a CALIPER assembly at St. Paul Catholic Elementary School in Stoney Creek</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos2_2.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Research Student Jacalyn and volunteer Sara at a CALIPER clinic at St. Vincent de Paul Catholic Elementary School in Hamilton</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos2_3.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Research Student Alex and volunteer Jeff at a CALIPER clinic at St. Vincent de Paul Catholic Elementary School in Hamilton</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/caliper_photos2_4.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER phlebotomists at a CALIPER clinic at St. Vincent de Paul Catholic Elementary School in Hamilton</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic1.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">Young boy participating in CALIPER to help sick children all across CANADA!</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic2.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">School-aged boy and CALIPER phlebotomist at a CALIPER clinic</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic3.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">Young girl gets weighed for Step 2 of the CALIPER process</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic4.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">Young girl sits on her mom’s lap as she is about to donate to CALIPER!</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic5.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">Children wait for their turn at the measuring station at a CALIPER clinic at their elementary school</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic6.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers managing Step 4 of the CALIPER process: sign out and gifts!</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic7.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers walk through questionnaires with children participating in CALIPER at a CALIPER clinic at their elementary school</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic8_1.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER Project Coordinator Olivia face paints for children at McMaster Children’s Hospital Walk and Wheel event</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic8_2.png"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers walk through questionnaires with children participating in CALIPER at a CALIPER clinic at St. John’s Elementary School</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic9.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers promoting CALIPER at the Children’s Festival at Beachway Park in Burlington</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic10.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER tent at the Children’s Festival at Beachway Park in Burlington</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic11.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers running a CALIPER info booth at the Hamilton Children’s Museum!</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic12.jpg"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER registration table at CALIPER clinic at McMaster </div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic13.png"  width="360" height="300">
											  </a>
											  <div class="desc">Jeff, Olivia, Megan and Marisa promoting CALIPER at the Healthy Living Event</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic14.png"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers running a CALIPER info and registration table at Paradise Soccer!</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic15.png"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers running Step 4 of CALIPER: sign out and gifts!</div>
											</div>
										</td>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic16.png"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER phlebotomists and Project Coordinator Megan are all smiles at CALIPER clinic</div>
											</div>
										</td>										
									</tr>
									<tr>
										<td>
											<div class="img">
											  <a target="_blank">
											    <img src="images/photos/kelly/CALIPER_pic17.png"  width="360" height="300">
											  </a>
											  <div class="desc">CALIPER volunteers walk through questionnaires with children participating in CALIPER at a CALIPER clinic at their elementary school</div>
											</div>
										</td>
										<td>
											
										</td>										
									</tr>
								</table>								
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