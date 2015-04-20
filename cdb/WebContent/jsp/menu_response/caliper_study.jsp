<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:"Table Normal";
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-qformat:yes;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin-top:0cm;
	mso-para-margin-right:0cm;
	mso-para-margin-bottom:10.0pt;
	mso-para-margin-left:0cm;
	line-height:115%;
	mso-pagination:widow-orphan;
	font-size:11.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;}
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
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">CALIPER Study</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Study
										</td>
									</tr>	
									<tr><td colspan='2'><hr/></td></tr>				
								</table>
							</td>
						</tr>
						<tr height=10><td colspan=4></td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">About Caliper</span></h3>
								
								<p>
									CALIPER = Canadian Laboratory Initiative on PEdiatric Reference Intervals
								</p>
								<p>
									CALIPER is a multi-centre project among several children's hospitals across Canada. The CALIPER project has been initiated by clinical chemists associated with Pediatric Focus Group of the Canadian Society of Clinical Chemists (CSCC). 						
								</p>
								<p>
									The goal of the CALIPER project is to analyze blood samples and establish a comprehensive database of normal reference values for children 0 – 18 years of age for a wide range of blood tests of clinical value in pediatric medicine. The reference intervals for a particular analyte will be provided specific for age, sex, ethnic background, and other physiological parameters (e.g. Tanner stage, BMI) if a particular analyte is significantly affected by any of the parameters. The results for common clinical chemistry analytes partitioned according to age, sex, and selected ethnic groups are available already. The CALIPER project is ongoing and the database will be periodically updated.					
								</p>
							</span>
							</td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">Study Design</span></h3>
								<h3>Overview</h3>
								<p>
									The establishment of the reference intervals is carried out in a systematic manner using well-defined protocols. The guideline published by the Clinical Laboratory Standards Institute (CLSI) – Defining, Establishing, and Verifying Reference Intervals in the Clinical Laboratory; Approved guideline – Third Edition (C28-A3)  (<a href='http://www.clsi.org/source/orders/free/c28-a3.pdf'>http://www.clsi.org/source/orders/free/c28-a3.pdf</a>) is the main reference document for the study design.							
								</p>
								<p>
									Testing will be performed on several key instruments that are commonly used in pediatric laboratories in Canada and worldwide including Abbott Architect, Ortho Vitros, Roche Modular/Cobas, Beckman, and Siemens Immulite systems. Some assays will employ high performance liquid chromatography (HPLC) and tandem mass spectrometry. This strategy will ensure applicability of the reference intervals to over 90% of methodologies employed in clinical chemistry laboratories in pediatric centres across Canada. All of the analytical methods are rigorously controlled according to the current standards for clinical laboratory operations. To achieve harmonization and standardization across CALIPER study centres, the pediatric centres in Canada participating in the CALIPER project participate in a quality assurance/standardization program through CEQAL and Digital PT (see http://www.ceqal.com). CEQAL provides reference materials for a number of clinical chemistry assays. These reference materials are being tested by all CALIPER sites to assess analytical performance and comparability of laboratory assays on various chemistry and immunoassay platforms. 								
								</p>	
								<h3>Participants</h3>
								<p>Children from birth to 18 years of age, are recruited to take part in the CALIPER study. The recruitment involves schools, universities, community centers, neighborhood groups and festivals, summer camps, childcare centers, and arts and cultural groups in Canada. Participants complete a short questionnaire, provide written informed consent, and donate a blood sample. </p>
								<p>Inclusion criteria: healthy children (determined based on completed questionnaires) of all ages and both sexes (0-18 years of age). </p>		
								<p>Exclusion criteria: history of chronic illness or metabolic disease, acute illness within the past month, or prescribed medication over the past month. Demographic data collected include previous and current health status, diet, exercise status, ethnicity, BMI parameters and Tanner stage (where appropriate). </p>		

								<h3>Sample collection and analysis</h3>
								<p>Depending on age, 2-10 mL of whole blood will be collected by trained pediatric phlebotomists using standardized conditions (using SST (serum separated tube) tubes (BD) which allow the collection of serum samples following centrifugation at 5000 g for 10 min). Blood samples are separated within 5 hours of collection and serum aliquoted in 0.5 mL aliquots in cryovials and stored at – 80℃. Samples are analyzed on several analytical platforms, including the Abbott Architect c8000, Ortho Vitros 5,1 FS Chemistry System, Roche Modular/Cobas system, Beckman Coulter Lx20/Dx800, and Siemens Immulite 2500. Analytical methods are controlled according to the manufacturer’s instructions by preventive maintenance and function checks, calibration and quality control.  All specimens tested are subjected to automated interference analysis for hemolysis, icterus and turbidity. </p>
								<p>A Microsoft Access database is created to store all demographic and laboratory parameter data for each participant. Participant data are screened prior to entry into the database to ensure only healthy subjects are used in the analysis. Following database entry, data are screened to correct for any missing parameters and transcription errors.</p>	
			
								<h3>Blood Samples</h3>
								<p>
									<table Table border=1 cellspacing=0 cellpadding=0 width=700
									 style='width:525.0pt;margin-left:7.5pt;background:white;border-collapse:collapse;
									 border:none;mso-border-alt:solid #CCCCCC .75pt;mso-yfti-tbllook:1184;
									 mso-padding-alt:0cm 0cm 0cm 0cm'>
									 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;mso-border-alt:solid #BBBBBB .75pt;
									  background:#DDDDDD;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><b><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Age<o:p></o:p></span></b></p>
									  </td>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-left:none;mso-border-left-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;background:#DDDDDD;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><b><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Sample<o:p></o:p></span></b></p>
									  </td>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-left:none;mso-border-left-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;background:#DDDDDD;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><b><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Minimum and Maximum<o:p></o:p></span></b></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:1'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>birth-1 month<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>1.0 <span class=SpellE>mL</span><o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>a minimum donation of 1 <span class=SpellE>mL</span>
									  is required with no more than 1 <span class=SpellE>mL</span> being collected<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:2'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>2-12 months<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>1-2 <span class=SpellE>mL</span><o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>a minimum donation of 1 <span class=SpellE>mL</span>
									  is required with no more than 2 <span class=SpellE>mL</span> being collected<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:3'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>1-5 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>3-5 <span class=SpellE>mL</span><o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>a minimum donation of 3 <span class=SpellE>mL</span>
									  is required with no more than 5 <span class=SpellE>mL</span> being collected<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:4'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>6-10 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>5 <span class=SpellE>mL</span><o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>a minimum donation of 5 <span class=SpellE>mL</span>
									  is required with no more than 5 <span class=SpellE>mL</span> being collected<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:5;mso-yfti-lastrow:yes'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>11-18 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>5-10 <span class=SpellE>mL</span><o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>a minimum donation of 5 <span class=SpellE>mL</span>
									  is required with no more than 10 <span class=SpellE>mL</span> being collected<o:p></o:p></span></p>
									  </td>
									 </tr>
									</table>
								</p>
		
								<h3>Fasting Times</h3>
								<p>
									<table Table border=1 cellspacing=0 cellpadding=0 width=700
									 style='width:525.0pt;margin-left:7.5pt;background:white;border-collapse:collapse;
									 border:none;mso-border-alt:solid #CCCCCC .75pt;mso-yfti-tbllook:1184;
									 mso-padding-alt:0cm 0cm 0cm 0cm'>
									 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;mso-border-alt:solid #BBBBBB .75pt;
									  background:#DDDDDD;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><b><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Age&nbsp;<o:p></o:p></span></b></p>
									  </td>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-left:none;mso-border-left-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;background:#DDDDDD;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><b><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Fasting<o:p></o:p></span></b></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:1'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>Less than 2 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>before next feeding<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:2'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>‘2 years + a day’ - 12 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>4-6 hours (ideally before next meal)<o:p></o:p></span></p>
									  </td>
									 </tr>
									 <tr style='mso-yfti-irow:3;mso-yfti-lastrow:yes'>
									  <td valign=top style='border:solid #BBBBBB 1.0pt;border-top:none;mso-border-top-alt:
									  solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>13-18 years<o:p></o:p></span></p>
									  </td>
									  <td valign=top style='border-top:none;border-left:none;border-bottom:solid #BBBBBB 1.0pt;
									  border-right:solid #BBBBBB 1.0pt;mso-border-top-alt:solid #BBBBBB .75pt;
									  mso-border-left-alt:solid #BBBBBB .75pt;mso-border-alt:solid #BBBBBB .75pt;
									  padding:0cm 0cm 0cm 0cm'>
									  <p  style='margin:7.5pt;line-height:12.0pt'><span
									  style='font-size:9.0pt;font-family:"Helvetica","sans-serif";mso-fareast-font-family:
									  "Times New Roman";color:#3B3B3B'>minimum 8 hours (ideally before breakfast)<o:p></o:p></span></p>
									  </td>
									 </tr>
									</table>
								</p>

								<h3>Data analysis and statistics</h3>
								<p>Preliminary analysis using visual graphical (scatter and distribution plots) and statistical approaches (Dixon test) is performed to examine the data for possible outliers and influential observations.  Significant outliers are removed. </p>
								<p>The data is then examined to determine appropriate age and sex partitions. Partitions are determined through consensus among three clinical biochemists by visual inspection of scatter plots and distribution plots, and examination of the overall trend in the analyte concentration across age groups. The partitions are assessed for normality of distribution using the skewness statistic and non-Gaussian distributed partitions are log-transformed. The partitions are then statistically evaluated using the Harris and Boyd method, which uses the standard deviation and the z statistic between two groups to determine if each group is significantly different to warrant its own reference interval. </p>	
								<p>Non-parametric or robust methods are used to determine a reference interval. For partitions with a sample size greater than 120, the non-parametric rank method is used, while for partitions with a sample size less than 120, the robust statistical method is employed. 90% confidence intervals around the lower and upper reference limits are calculated.</p>	
								<p>Statistical analysis is performed using MS Excel and R software. </p>	
							</span>
							</td>
						</tr>	
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">CALIPER Study Centres/Co-Investigators/Collaborators</span></h3>
								
								<p>
									<h3>The Hospital for Sick Children (Sickkids), Toronto, Ontario</h3>
									<ul>
										<li>Dr. Khosrow Adeli, Principal Investigator of the CALIPER project and at Sickkids with expertise in basic and clinical laboratory research and over 22 years of clinical laboratory experience</li>
										<li>Dr. David Colantonio, a board certified clinical chemist </li>
										<li>Dr. Lianna Kyriakopolou, a board certified clinical chemist </li>
									</ul>																
								</p>
								<p>
									<h3>Eastern Health, St. John’s, Newfoundland</h3>
									<ul>
										<li>Dr. Edward Randell, a board certified clinical biochemist and chief of clinical chemistry service at Eastern Health</li>
									</ul>							
								</p>
								<p>
									<h3>Sainte-Justine Hospital, Montreal, Quebec</h3>
									<ul>
										<li>Dr. Edgard Delvin, a board certified clinical biochemist chief of clinical chemistry service at Hopital Sainte-Justine</li>
									</ul>
								</p>
								<p>
									<h3>McMaster Children Hospital, Hamilton, Ontario</h3>
									<ul>
										<li>Dr. Vijay Grey, a board certified clinical biochemist and chief of clinical chemistry service at McMaster Children Hospital</li>
										<li>Dr. Murray Potter, a medical biochemist </li>
									</ul>																
								</p>
								<p>
									<h3>Children’s Hospital of Eastern Ontario (CHEO), Ottawa, Ontario</h3>
									<ul>
										<li>Dr. Nathalie Lepage, a board certified clinical biochemist and chief of clinical chemistry service at Children’s Hospital of Eastern Ontario</li>
										<li>Dr. Yanping Gong, a board certified clinical biochemist </li>
									</ul>																
								</p>
								<p>
									<h3>University Hospital, Saskatoon, Saskatchewan</h3>
									<ul>
										<li>Dr. Qing Meng, a board certified clinical biochemist at the University Hospital in Saskatoon</li>										
									</ul>																
								</p>
								<p>
									<h3>BC Children’s Hospital, Vancouver, British Columbia</h3>
									<ul>
										<li>Dr. Jalaluddin Bhuiyan, a board certified clinical chemist </li>
										<li>Dr. Benjamin Jung, a board certified clinical biochemist at B.C. Children’s Hospital, Vancouver</li>
									</ul>																
								</p>
								<p>
									<h3>Medical Advisors/Pediatricians</h3>
									<ul>
										<li>Dr. Patricia Parkins, Pediatrician/clinician scientist, The Hospital for Sick Children, Toronto, Ontario  </li>
										<li>Dr. Catherine Birken, Pediatrician/clinician scientist, The Hospital for Sick Children, Toronto, Ontario</li>
									</ul>																
								</p>
								<p>
									<h3>CEQAL (Canadian External Quality Assessment Laboratory), Vancouver, British Columbia</h3>
									<ul>
										<li>Dr. David Seccombe, CEQAL president</li>
									</ul>																
								</p>
							</span>
							</td>
						</tr>		
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-size:11pt; font-family: arial; vertical-align: middle;">							
							<h3><span style="color: #3973AC;">CALIPER purpose</span></h3>
							<p>The purpose of the project is to establish a current and accurate database of reference intervals (normal values) that represent Canada’s children and youth - multi-ethnic males and females from birth to 18 years. The database will be used by physicians for interpretation of common blood tests.</p>
							<h3><span style="color: #3973AC;">CALIPER: A Project of the Pediatric Focus Group of the CSCC</span></h3>
							<p>A Canadian team of investigators have assembled to lead the establishment of a reliable database
							across the country. This national research initiative, working under the name of Canadian Laboratory
							Initiative on Paediatric Reference Interval (CALIPER), is a key project of the
							Paediatric Focus Group of the CSCC (Canadian Society of Clinical Chemists). The project has been
							endorsed by the CSCC and the Research Ethics Board at several Canadian hospitals.
							</p>
							<p>For more information, please <a href="<%= MenuLink.contactus %>" onfocus="this.blur()" title="" style="color: #519DC7;">Contact Us</a></p>
							</span>
							</td>					
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