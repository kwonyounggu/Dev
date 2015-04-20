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
											<span style="color: #ffffff; font-weight:bold; font-size:12pt; font-family: arial;">&nbsp;&nbsp;CALIPER across Canada</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial; color: #ffffff;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #FCD209;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER across Canada
										</td>
									</tr>					
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4>
							
								<span style="font-family: arial; vertical-align: middle;">
									<table border=0 cellspacing=0 cellpadding=10 width='100%'>
										<tr><td colspan="2" width="715">
												<img alt="CALIPER across Canada" src="images/main_body/17768-CALIPERmapA.gif" title="CALIPER across Canada" usemap="#Map" /> 
												<map id="Map" name="Map">
													<area alt="Royal University Hospital" coords="165,186,232,215" href="#saskatoon" shape="rect" target="_top" />
													<area alt="Ste-Justine Hospital" coords="407,258,474,278" href="#montreal" shape="rect" target="_top" />
													<area alt="Janeway Children's Health and Rehabilitation Centre " coords="586,135,644,137,648,152,615,163,583,152,583,131" href="#Stjohns" shape="poly" target="_top" />
													<area alt="Hamilton Health Sciences Corporation" coords="417,320,482,339" href="#hamilton" shape="rect" target="_top" />
													<area alt="BC Children’s Hospital" coords="42,192,123,221" href="#vancouver" shape="rect" target="_top" />
													<area alt="Children's Hospital of Eastern Ontario " coords="384,281,451,298" href="#ottawa" shape="rect" target="_top" />
													<area alt="SickKids" coords="418,297,498,318" href="#toronto" shape="rect" target="_top" />
												</map>
											</td>
										</tr>
										<tr>
											<td bgcolor="#fff0f0" colspan="2" scope="col" style="padding: 10px 0px 0px 0px;" align="center"><h2><b><font color="#3a73c2">Ontario</font></b></h2>
											</td>
										</tr>
										<tr color="#3a73c2">
											<td rowspan="2" scope="col" valign="top" width="50%"><h3><a id="toronto" name="toronto">  </a><strong><font color="#e35670">Toronto</font></strong></h3>
												<p>The Hospital for Sick Children<br />Department of Paediatric Laboratory Medicine<br />Division of Clinical Biochemistry<br />555 University Avenue<br />Toronto, ON, M5G 1X8<br /><br /><br /><br />
												<img alt="The Hospital for Sick Children" src="images/main_body/22351-DPLMNewLogo.gif" title="The Hospital for Sick Children" /><br /> 
												</p>
											</td>
											<td scope="col" style=" vertical-align: top; width: 50%;">
												<p dir="ltr">
													<img alt="Jennifer Clarke" src="images/main_body/23249-wJennifer1.jpg" style=" border: 1px solid blue; padding: 2px 2px 2px 2px; float: right;" />Jennifer Clarke<br />Project Coordinator<br /><br />phone: (416) 813-7654 ext. 2673<br />e-mail: <a href="mailto:jennifer.clarke@sickkids.ca">jennifer.clarke@sickkids.ca</a>
												</p>
											</td>
										</tr>
										<tr><td colspan="2"><p><img alt="Taran Malhi" src="images/main_body/24135-wTaran.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" /><font class="txt">Taran Malhi</font><br />Phlebotomist</p><p>phone: (416) 813-6212<br />e-mail: <a href="mailto:tarandeep.malhi@sickkids.ca">tarandeep.malhi@sickkids.ca</a></p></td></tr><tr><td><p><img alt="Dr. Khosrow Adeli" src="images/main_body/22348-wAdeli.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" title="Dr. Khosrow Adeli" />Dr. Khosrow Adeli<br />Principal Investigator<br /><br />phone: (416) 813-8682<br />e-mail: <a href="mailto:khosrow.adeli@sickkids.ca">khosrow.adeli@sickkids.ca</a></p></td><td><p><img alt="Man Khun Chan" src="images/main_body/23105-wManKhun.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" /> Man Khun Chan<br />Technical and Lab Coordinator<br /><br />phone: (416) 813-5904<br />e-mail: <a href="mailto:mankhun.chan@sickkids.ca">mankhun.chan@sickkids.ca</a></p></td></tr><tr><td><p><img alt="Dr. David Colantonio" src="images/main_body/23003-wColantonio.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" />Dr. David Colantonio<br />Co-Investigator<br /><br />Phone: (416) 813-6817<br />e-mail: <a href="mailto:david.colantonio@sickkids.ca">david.colantonio@sickkids.ca</a></p></td><td><p><img alt="Dr. Lianna Kyriakopoulou" src="images/main_body/23104-wLianna.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" />Dr. Lianna Kyriakopoulou<br />Co-Investigator<br /><br />Phone: (416) 813-8066<br />e-mail: <a href="mailto:lianna.kyriakopoulou@sickkids.ca">lianna.kyriakopoulou@sickkids.ca</a></p><p> </p></td></tr><tr><td colspan="2" scope="row" valign="top"><hr color="#ffecec" /></td></tr><tr><td rowspan="2" scope="row" valign="top"><h3><a id="hamilton" name="hamilton"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">Hamilton</font></strong></h3><p>Hamilton Health Sciences Corporation<br />McMaster Site<br />1200 Main Street W<br />Hamilton, ON, L8N 3Z5</p></td><td valign="top"><p>Esther Eapen<br />Research Coordinator<br /><br />phone: (905) 521-2100 ext. 76294<br />e-mail: <a href="mailto:eapen@hhsc.ca">eapen@hhsc.ca</a></p></td></tr><tr><td><p>Dr. Vijaylaxmi Grey<br />Co-investigator<br /><br />phone: (905) 521-2100 ext. 76595<br />e-mail: <a href="mailto:grey@hhsc.ca">grey@hhsc.ca</a></p></td></tr><tr><td colspan="2" scope="row" valign="top"><hr color="#ffecec" /></td></tr><tr><td rowspan="2" scope="row" valign="top"><h3><a id="ottawa" name="ottawa"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">Ottawa</font></strong></h3><p>Children’s Hospital of Eastern Ontario<br />401 Smyth Road<br />Ottawa, ON, K1H 8L1</p><p><img alt="Children’s Hospital of Eastern Ontario" src="images/main_body/22352-CHEO-logo.jpg" title="Children’s Hospital of Eastern Ontario" /></p></td><td valign="top"><p>Chantal Horth<br />Research Coordinator<br /><br />phone: (613) 738-3952<br />fax: (613) 738-4800<br />e-mail: <a href="mailto:chorth@cheo.on.ca">chorth@cheo.on.ca</a></p></td></tr><tr><td><p><img alt="Dr.Nathalie Lepage" src="images/main_body/22350-wLepage.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" title="Dr. Nathalie Lepage" />Dr. Nathalie Lepage<br />Co-Investigator<br /><br />phone: (613) 737-7600 ext. 2439<br />e-mail: <a href="mailto:nlepage@cheo.on.ca">nlepage@cheo.on.ca</a></p></td></tr><tr valign="top"><td bgcolor="#fff0f0" colspan="2" scope="col" style="padding: 10px 0px 0px 0px;"><center><h2><b><font color="#3a73c2">British Columbia</font></b></h2></center></td></tr><tr color="#3a73c2"><td rowspan="2" scope="col" valign="top"><h3><a id="Vancouver" name="Vancouver"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">Vancouver</font></strong></h3><p>BC Children’s Hospital<br />4480 Oak Street<br />Vancouver BC V6H 3N1</p><p><img alt="BC Children’s Hospital " src="images/main_body/30964-BC-CH-Logo.jpg" /></p></td><td scope="col" valign="top"><p> Dr. Jallaluddin Bhuiyan<br />Co-Investigator</p><p>e-mail: <a href="Bhuiyan@cw.bc.ca">Bhuiyan@cw.bc.ca<br /></a>phone: (604) 875-2000</p></td></tr><tr><td><p>Dr. Benjamin Jung<br />Co-Investigator</p><p>e-mail: <a href="BJung-02@cw.bc.ca">BJung-02@cw.bc.ca<br /></a>phone: (604) 875-2000</p></td></tr><tr valign="top"><td bgcolor="#fff0f0" colspan="2" style="padding: 10px 0px 0px 0px;" scope="col"><center><h2><b><font color="#3a73c2">Quebec</font></b></h2></center></td></tr><tr color="#3a73c2"><td scope="row" valign="top"><h3><a id="montreal" name="montreal"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">Montreal</font></strong></h3><p>Ste-Justine Hospital<br />3175 Cote Ste-Catherine<br />Montreal, QC, H3T 1C5</p></td><td valign="top"><p>Dr. Edgard Delvin<br />Principal Investigator<br /><br />phone: (514) 345-4690<br />e-mail: <a href="mailto:delvine@justine.umontreal.ca">delvine@justine.umontreal.ca</a></p></td></tr><tr valign="top"><td bgcolor="#fff0f0" colspan="2" scope="col" style="padding: 10px 0px 0px 0px;"><center><h2><b><font color="#3a73c2">Saskatchewan</font></b></h2></center></td></tr><tr color="#3a73c2"><td valign="top"><h3><a id="saskatoon" name="saskatoon"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">Saskatoon</font></strong></h3><p>Royal University Hospital<br />103 Hospital Drive<br />Saskatoon, SK, S7N 0W8</p><p><img alt="U of Saskatchewan" src="images/main_body/22353-UofSaskatchewanLogo.jpg" /> <img alt="Saskatoon Health" src="images/main_body/22354-SaskatoonHealthRegionLogo.jpg" /></p></td><td valign="top"><p><img alt="Dr.Qing Meng" src="images/main_body/22349-wMeng.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" /><br /><br />Dr. Qing Meng<br />Co-Investigator<br /><br />phone: (306) 655-2165<br />e-mail: <a href="mailto:qing.meng@usask.ca">qing.meng@usask.ca</a></p></td></tr><tr valign="top"><td bgcolor="#fff0f0" colspan="2" scope="col" style="padding: 10px 0px 0px 0px;"><center><h2><b><font color="#3a73c2">Newfoundland and Labrador</font></b></h2></center></td></tr><tr color="#3a73c2"><td scope="row" valign="top"><h3><a id="stjohns" name="stjohns"><strong><font color="#e35670"> </font></strong></a><strong><font color="#e35670">St. John’s</font></strong></h3><p>Janeway Children's Health and<br />Rehabilitation Centre<br />300 Prince Phillip Dr.,<br />St. John’s, NL, A1B 3V6<br /><img alt="Eastern Health logo" src="images/main_body/23814-eastern_health.gif" style=" padding-left: 2px; float: right; padding-right: 2px;" /></p></td><td valign="top"><p><img alt="Dr. Edward Randell" src="images/main_body/23806-wRandell.jpg" style=" border: 1px solid blue; padding-left: 2px; float: right; padding-right: 2px; padding-top: 2px; padding-bottom: 2px;" /><br /><br />Dr. Edward Randell<br />Co-Investigator<br /><br />phone: (709) 777-6375<br />e-mail: <a href="mailto:Ed.Randell@easternhealth.ca">Ed.Randell@easternhealth.ca</a></p></td></tr><tr><td colspan="2" scope="row" valign="top"><hr color="#ffecec" /><hr color="#3a73c2" /><p> </p></td></tr></table>	
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