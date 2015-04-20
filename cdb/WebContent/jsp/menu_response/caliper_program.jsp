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
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">CALIPER Program</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;CALIPER Program
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
								<h3><span style="color: #3973AC;">The Goal of CALIPER project</span></h3>
								
								<p>
									The main objective of the CALIPER project is to develop a database of reference intervals for blood tests for the whole pediatric population from birth to 18 years of age. The database should contains reference intervals specific to age, sex, and will eventually be made specific to ethnic background, analytical platform, and other physiological parameters (e.g. Tanner stage, BMI), if a particular analyte is significantly affected by any of the parameters.
								</p>
							</span>
							</td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">History of CALIPER</span></h3>
								
								<p>
									The CALIPER project has been initiated by clinical chemist/medical biochemist from the Pediatric Focus Group of the Canadian Society of Clinical Chemists (CSCC). These clinical chemists and medical biochemists supervise clinical chemistry laboratories in Canada and are board certified through the Canadian Academy of Clinical Biochemistry (CACB). 
								</p>
								<p>
									The issue of the critical gaps that exist in pediatric reference intervals has been a major concern of the CSCC Pediatric Focus Group as well as pediatricians across Canada. With funding from CIHR (Institute of Child Development and Youth), three workshops were held from 2004 to 2006 to initiate discussions among pediatric hospital laboratories on the establishment of new laboratory reference interval databases for biomarkers of pediatric disease. This expert group recommended that the large pediatric institutions initiate collaborative studies to determine the effect of age, gender, and ethnic origin on pediatric reference intervals and establish a comprehensive database in Canadian children and adolescents. The expert group named this national initiative the CALIPER (Canadian Laboratory Initiative on Pediatric Reference Intervals) project. 
								</p>
								<p>
								Since these meetings, three pediatric centers in Ontario have obtained Research Ethics Board approval for the CALIPER initiative and have begun the collection phase of the project using hospital internal resources and seed funding. This collaboration across centres has allowed for the establishment of standard procedures for sample collection, sample analysis, and statistical analysis in study centers across Canada.
								</p>
							</span>
							</td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">CALIPER Milestones</span></h3>
								
								<p>
									<ol>
										<li>Development of a Study Design</li>
										<li>Research Ethics Board (REB) approvals </li>
										<li>Consent forms, questionnaires, specimen collection and handling protocols designed, standardized and harmonized among all study centres</li>
										<li>Pre-analytical, analytical, and post-analytical steps designed and harmonized</li>
										<li>Implementation of a Promotion and Recruitment Plan: initiation of major promotional campaigns and development of promotional tools (posters, banners, brochures, postcards, storybook)</li>
										<li>Establishment of the CALIPER Volunteer Team: volunteers assist with all aspects of the project, including behind-the-scenes administrative tasks, promotion and recruitment in the community, front-line work with children and families, and lab work such as sample processing </li>
										<li>Development of the CALIPER websites</li>
										<li>Completion and publication of several pilot studies on the Abbott Architect ci8200, Roche Cobas 6000, and Ortho Vitros Fusion 5.1 platforms using data from outpatient clinic samples
											<ul><li>Small pilot studies have also been initiated in collaboration with diagnostic industry partners who provided reagents and instruments to analyze pediatric samples (obtained from outpatient clinics with relatively healthy children) on specific clinical instruments. These pilot studies have allowed for determination of preliminary reference intervals for some laboratory tests, and have helped the research team gain valuable practical experience with all aspects of sample collection, testing, statistical analysis, and determination of age- and gender-specific reference intervals.</li></ul>
										</li>
										<li>Completion of analyte stability study – analyte stability was examined in serum samples from children of different age groups after the samples were stored for up to 13 months at -80oC. Most analytes showed stability within the analytical variation of the assays</li>
										<li>Completion of fasting and diurnal variation study - – analyte levels with and without fasting and at different times of the day were measured and compared. More analytes are relatively stable throughout the day regardless of fasting state or food intake. Only a few analyte levels are markedly affected by food intake or intra-individual or diurnal (circadian) variation. Therefore only fasting samples collected in early morning (8 AM) will be used to test for those analytes that are significantly affected by food intake/diurnal variation</li>
										<li>Publication of the first CALIPER database with reference intervals provided for common chemistry, protein, lipid, and enzyme biomarkers. The reference intervals are partitioned by age and sex where necessary. In addition, partitioning by selected ethnic groups is provided. The current pediatric database is based on biomarker measurements on the Abbott Architect c8000 analytical platform</li>							
									</ol>
								</p>
								
							</span>
							</td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">Future CALIPER goals</span></h3>
								
								<p>
									<ul>
										<li>Future projects include the establishment of age and sex-specific reference intervals for markers of inborn errors of metabolism, hematology and coagulation as well as testing urine for urine analytes</li>
										<li>Current reference interval partitions will be expanded to include a wider set of ethnic groups and other physiological parameters, such as Tanner stage and BMI</li>
										<li>The CALIPER project also aims to test the transference of reference intervals to all major instrument platforms in use in children’s hospitals and establish separate reference intervals if transference is not possible</li>
										<li>The CALIPER project will also establish a repository of biological samples from healthy children and adolescents for future updating of current reference intervals as well as for establishing reference intervals for new and emerging biomarkers. The reference interval database needs to be periodically updated as testing technology changes and as reference population changes due to changes in environmental and nutritional factors or immigration</li>
									</ul>
								</p>
								
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
										<li>Dr. David Colantonio, a board certified Clinical Chemist </li>
										<li>Dr. Lianna Kyriakopolou, a board certified Clinical Chemist </li>
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
										<li>Dr. Edgard Delvin, a board certified clinical biochemist and chief of clinical chemistry service at Hopital Sainte-Justine</li>
									</ul>
								</p>
								<p>
									<h3>McMaster Children Hospital, Hamilton, Ontario</h3>
									<ul>
										<li>Dr. Vijay Grey, a board certified clinical biochemist and chief of clinical chemistry service at McMaster Children's Hospital</li>
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