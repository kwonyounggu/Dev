<table id="custom_search_initial_table" border=0 cellspacing=0 cellpadding=4 width='100%' style="padding: 0 0 0 20px;" align="left" >
	<tr>
	    <td colspan=4 align="left">
			<img src="images/common/database_icon_24x24.gif" style="vertical-align: middle;"/><span style="color: #519DC7; font-weight:bold; font-size:12pt; font-family: times, 'Times New Roman', serif;">&nbsp;&nbsp;CALIPER Database Custom Search</span>
		</td>
	</tr>
	<tr><td colspan=4 align="left"><p><span style="margin-left:5em; color: #616161; font-weight:normal; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">Description: this section is to provide ...</span></p></td></tr> 
	<tr>
	    <td  colspan=4 align="left">
			<table border=0 cellspacing=1 cellpadding=0 width='98%' align="left" style="padding: 0 0 0 0px;" > 
				<tr><td colspan=4>&nbsp;&nbsp;<img src="images/common/ico_1.gif" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Select either a custom chart or a custom table to be displayed</span></td></tr>
				<tr><td colspan=4 height=1 class=dv_line3></td></tr> 
				<tr><td colspan=2 align="left">
						<span style="color: #616161; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">
							<input onfocus=this.blur() type="radio" name="rf_chart_or_table" value="rfi_chart" onclick="onChartOrTable(this.value);" checked/>Reference Interval Charts (Scattered Graphs)	
						</span>														   		
					</td>
					<td colspan=2 align="left">
						<span style="color: #616161; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">
							<input onfocus=this.blur() type="radio" name="rf_chart_or_table" value="rfi_table" onclick="onChartOrTable(this.value);"/>Reference Interval Custom Tables	
						</span>														   		
					</td>
				</tr>
				<tr><td colspan=4 height=10></td></tr> 
				<tr><td colspan=4>&nbsp;&nbsp;<img src="images/common/ico_2.gif" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Select one of analytes you are interested in</span>

					</td>
				</tr>
		 		<tr><td colspan=4 height=1 class=dv_line3></td></tr> 
					<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="albumin_g" onclick="onSelectAnalyte(this.value);" checked/>Albumin G</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="albumin_p" onclick="onSelectAnalyte(this.value);"/>Albumin P</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="alkaline_phosphatase" onclick="onSelectAnalyte(this.value);"/>Alkaline Phosphatase</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="alt" onclick="onSelectAnalyte(this.value);"/>ALT</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="alt_act" onclick="onSelectAnalyte(this.value);"/>ALT (ACT)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="amylase" onclick="onSelectAnalyte(this.value);"/>Amylase</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="apo_a1" onclick="onSelectAnalyte(this.value);"/>Apo A1</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="apo_b" onclick="onSelectAnalyte(this.value);"/>Apo B</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="aso" onclick="onSelectAnalyte(this.value);"/>ASO</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="ast" onclick="onSelectAnalyte(this.value);"/>AST</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="ast_act" onclick="onSelectAnalyte(this.value);"/>AST (ACT)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="bilirubin_direct" onclick="onSelectAnalyte(this.value);"/>Bilirubin-Direct (D)</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="bilirubin_total" onclick="onSelectAnalyte(this.value);"/>Bilirubin-Total (T)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="c3" onclick="onSelectAnalyte(this.value);"/>C3</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="c4" onclick="onSelectAnalyte(this.value);"/>C4</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="calcium" onclick="onSelectAnalyte(this.value);"/>Calcium</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="che" onclick="onSelectAnalyte(this.value);"/>ChE</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="cholesterol" onclick="onSelectAnalyte(this.value);"/>Cholesterol</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="co2" onclick="onSelectAnalyte(this.value);"/>CO2 (carbon dioxide)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="creatinine_enzymatic" onclick="onSelectAnalyte(this.value);"/>Creatinine (enzymatic)</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="creatinine_jaffee" onclick="onSelectAnalyte(this.value);"/>Creatinine (Jaffee)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="crp" onclick="onSelectAnalyte(this.value);"/>CRP</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="ggt" onclick="onSelectAnalyte(this.value);"/>GGT</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="haptoglobin" onclick="onSelectAnalyte(this.value);"/>Haptoglobin</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="iga" onclick="onSelectAnalyte(this.value);"/>IgA</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="igg" onclick="onSelectAnalyte(this.value);"/>IgG</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="igm" onclick="onSelectAnalyte(this.value);"/>IgM</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="iron" onclick="onSelectAnalyte(this.value);"/>Iron</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="iron_plasma" onclick="onSelectAnalyte(this.value);"/>Iron (plasma)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="ldh_ld" onclick="onSelectAnalyte(this.value);"/>LDH (LD)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="lipase_lip" onclick="onSelectAnalyte(this.value);"/>Lipase (lip)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="magnesium" onclick="onSelectAnalyte(this.value);"/>Magnesium</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="phosphorus" onclick="onSelectAnalyte(this.value);"/>Phosphorus</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="prealbumin" onclick="onSelectAnalyte(this.value);"/>Prealbumin</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="rf_rheumatoid_factor" onclick="onSelectAnalyte(this.value);"/>RF (rheumatoid factor)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="total_protein" onclick="onSelectAnalyte(this.value);"/>Total Protein</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="transferrin_trf" onclick="onSelectAnalyte(this.value);"/>Transferrin (TRF)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="triglycerides" onclick="onSelectAnalyte(this.value);"/>Triglycerides</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="uhdl_ultra_hdl" onclick="onSelectAnalyte(this.value);"/>UHDL (Ultra HDL)</td>
					<td><input type="radio" name="caliper_database_custom_search"  value="urea" onclick="onSelectAnalyte(this.value);"/>Urea</td>
				</tr>
				<tr style='background-color:#ffffff;'>
					<td><input type="radio" name="caliper_database_custom_search"  value="uric_acid" onclick="onSelectAnalyte(this.value);"/>Uric Acid</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr><td colspan=4 height="10"></td></tr>  
			 </table> 	
	    </td>						
	</tr>
	<tr>
	    <td  colspan=4 align="left">
			<span id="age_gender_ethnic_table_span"></span>
			<table border=0 cellspacing=0 cellpadding=0 width='98%' style="padding: 0 0 0 0px;" id="age_gender_ethnic_table"> 
				<tr><td colspan=7>&nbsp;&nbsp;<img src="images/common/ico_3.gif" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Select Age Preference</span></td></tr>
				<tr><td colspan=7 height=1 class=dv_line3></td></tr> 								
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;Minimum:&nbsp;&nbsp;
						<select id="minAge">
						  <option value="0" selected>0-30Days
						  <option value="1">31D-12M
						  <option value="2">1 Year
						  <option value="3">2 Years
						  <option value="4">3 Years
						  <option value="5">4 Years
						  <option value="6">5 Years
						  <option value="7">6 Years
						  <option value="8">7 Years
						  <option value="9">8 Years
						  <option value="10">9 Years
						  <option value="11">10 Years
						  <option value="12">11 Year
						  <option value="13">12 Years
						  <option value="14">13 Years
						  <option value="15">14 Years
						  <option value="16">15 Years
						  <option value="17">16 Years
						  <option value="18">17 Years
						  <option value="19">18 Years
						</select></td>																		
					<td align="left">Maximum:&nbsp;&nbsp;
						<select id="maxAge">
						  <option value="0">0-30Days
						  <option value="1">31D-12M
						  <option value="2">1 Year
						  <option value="3">2 Years
						  <option value="4">3 Years
						  <option value="5">4 Years
						  <option value="6">5 Years
						  <option value="7">6 Years
						  <option value="8">7 Years
						  <option value="9">8 Years
						  <option value="10">9 Years
						  <option value="11">10 Years
						  <option value="12">11 Year
						  <option value="13">12 Years
						  <option value="14">13 Years
						  <option value="15">14 Years
						  <option value="16">15 Years
						  <option value="17">16 Years
						  <option value="18">17 Years
						  <option value="19" selected>18 Years
						</select></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>

				<tr><td colspan=7 height="10"></td></tr> 
				<tr><td colspan=7>&nbsp;&nbsp;<img src="images/common/ico_4.gif" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Select Gender Preference</span></td></tr>
				<tr><td colspan=7 height=1 class=dv_line3></td></tr> 
				<tr>
					<td><input type="radio" name="gender_group" value="1" onclick="onSelectGender(this.value);"/>Male</td>
					<td><input type="radio" name="gender_group" value="2" onclick="onSelectGender(this.value);"/>Female</td>
					<td><input type="radio" name="gender_group" value="3" onclick="onSelectGender(this.value);" checked/>Both</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr><td colspan=7 height="10"></td></tr> 
				<tr><td colspan=7>&nbsp;&nbsp;<img src="images/common/ico_5.gif" style="vertical-align: middle;"/><span style="color: #616161; font-weight:bold; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">&nbsp;&nbsp;Select Ethnic Group (*: all ethnic groups)</span></td></tr>
				<tr><td colspan=7 height=1 class=dv_line3></td></tr> 
				<tr>
					<td><input type="radio" name="ethnic_group" value="4" onclick="onSelectEthnic(this.value);"/>Chinese</td>
					<td><input type="radio" name="ethnic_group" value="9" onclick="onSelectEthnic(this.value);"/>South Asian</td>
					<td><input type="radio" name="ethnic_group" value="11" onclick="onSelectEthnic(this.value);"/>Caucasian</td>
					<td><input type="radio" name="ethnic_group"  value="12" onclick="onSelectEthnic(this.value);" checked/>All(*)</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="4"><input type="submit" value="Submit" onclick="javascript:onClickSubmit();"></td></tr>
</table> 	  