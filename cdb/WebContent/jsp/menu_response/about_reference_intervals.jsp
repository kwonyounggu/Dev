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
								<table height="40" width="100%">
									<tr><td align="left" valign="middle" style="padding-left: 20px; font-size:13pt;">
											<img src='images/common/blue_circle.gif' width=16 height=17 valign="middle"/>
											<span style="font-weight:bold; font-size:12pt; font-family: arial;">About New Pediatric Reference Intervals</span>
										</td>
										<td align="right" valign="middle" style="padding-right: 20px; font-size:8pt; font-family: arial;">
											<img src='images/common/location_pin.png' width=16 height=16 valign="middle"/>&nbsp;&nbsp;<a href="<%= MenuLink.home %>" style="color: #5375CB;">Home</a>&nbsp;&nbsp;>&nbsp;&nbsp;About New Pediatric Reference Intervals
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
								<h3><span style="color: #3973AC;">Pediatric Reference Intervals</span></h3>
								
								<p>
									Diagnosis and monitoring of almost all pediatric diseases require the measurement of a wide range of disease biomarkers. These biomarkers are commonly measured in clinical laboratories and the results interpreted based on established reference (or normal) intervals.
								</p>
								<p>
									In children, physiological changes associated with growth and development may necessitate separate reference intervals for different ages. In addition, the physiological changes associated with growth and development may be different for male and female children. Biomarkers may also be affected by ethnicity. As a result, age, sex and ethnic group-specific reference intervals may be necessary for different biomarkers. This allows comparison of an individual's result with a reference interval derived from the corresponding reference group. Whether age, sex, and ethnicity-based partitions are required and the type of partitions they are can vary among biomarkers. Biomarkers may also be affected by other physiological factors, such as body mass index (BMI) and sexual development (Tanner) stage. Additional partitions may therefore be informative.							
								</p>
								<p>
									Analyte measurements in different clinical laboratories are conducted on different analytical platforms. However, standardization and harmonization of measurements across all platforms for all analytes has not been achieved. As a result, adoption of reference intervals established on one analytical platform for test results obtained on another platform is not immediate but requires previous transference tests. In cases when the results obtained on different analytical platforms are not comparable and the transference test fails, separate intervals need to be established on the other platform.							
								</p>
							</span>
							</td>
						</tr>
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">Current Gaps in Pediatric Reference Intervals</span></h3>
								
								<p>
									Laboratories and clinicians have depended on scattered and incomplete data as a source of pediatric reference intervals. Comprehensive and adequately partitioned pediatric reference intervals have only been available on a limited number of disease biomarkers, while most of the available reference intervals are incomplete, cover a limited age interval and do not always cover both genders. Pediatric reference intervals have not been determined for new and emerging biomarkers. In addition, almost all available reference intervals were determined only on Caucasian populations; application to other ethnic groups may not be appropriate for some biomarkers. Many of the available reference intervals were determined decades ago on older and less accurate laboratory instruments and methodologies. Finally, reference intervals have typically been derived from samples collected from hospitalized patients and may not reflect values in healthy populations.								
								</p>
								<p>
									Accrediting and licensing organizations/regulatory bodies governing medical laboratory best practices require that individual laboratories establish or verify reference intervals for all quantitative test methods; the exception being for tests that employ decision cut-off limits (e.g. cholesterol, hemoglobin A1C). The undertaking in terms of time, resources, and costs to each laboratory is significant. This is especially difficult with pediatric populations that require age partitioning based on child development.								
								</p>							
							</span>
							</td>
						</tr>	
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">Current Initiatives</span></h3>
								
								<p>
									Several large national studies have recently been initiated in a number of different countries which aim to provide a comprehensive description of children’s health and understand the role of various determinants of health in children. 								
								</p>
								<p>
									In Canada, Statistics Canada is conducting the Canada Health Measures Survey (<a href='http://www.statcan.gc.ca'>http://www.statcan.gc.ca</a>), launched in 2007 and targeted to the entire population. The study collects health information by physical examination and interview, and collects blood and urine samples. The emphasis is on analysis of different nutritional, metabolic, cardiovascular, kidney disease, environmental toxic exposure, and infectious disease biomarkers. The data obtained from children may be used to establish pediatric reference intervals. 								
								</p>
								<p>
									National Children’s Study in the United States (<a href='http://www.nationalchildrensstudy.gov'>http://www.nationalchildrensstudy.gov</a>) is following a large number of children across the United States from birth to 21 years of age to examine children’s health and the effect of various environmental, genetic and social factors on children’s growth and development. This study also aims to establish new pediatric reference intervals. 								
								</p>
								<p>
									In Germany, a nationwide health survey targeting children, the German Health Interview and Examination Survey for Children and Adolescents (KiGGS; <a href='http://www.kiggs.de'>http://www.kiggs.de</a>) was initiated in 2003. It is a long-term comprehensive study that aims to examine different aspects of children’s physical and mental health. The study includes measurements of laboratory parameters and the establishment of reference intervals and cutoff values for analytes of interest for children aged 0 –17 years. 
								</p>
								<p>
									Children’s Health Improvement through Laboratory Diagnostics (CHILDx; <a href='http://www.childx.org'>http://www.childx.org</a>) developed by ARUP and the University of Utah Department of Pathology, is conducting a pediatric reference interval project for children aged 6 months – 17 years in Salt Lake City, Utah. 
								</p>
							</span>
							</td>
						</tr>			
						<tr><td colspan=4 style="padding-right: 40px; padding-bottom: 0px; padding-left: 40px; ">
							<span style="font-family: arial; vertical-align: middle;">
								<h3><span style="color: #3973AC;">CALIPER</span></h3>								
								<p>
								CALIPER is a multi-centre project among several children's hospitals across Canada with the specific aim of establishing an accurate and current database of pediatric reference intervals for a wide range of clinical chemistry biomarkers. The database will provide reference intervals for children and adolescents aged 0 – 18 years. The reference intervals will be partitioned according to the factors with significant effect on the particular biomarker, including age, sex, ethnic background, Tanner stage, and BMI. The reference intervals will also be provided for different analytical platforms currently in wide use internationally in pediatric healthcare centres.								
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