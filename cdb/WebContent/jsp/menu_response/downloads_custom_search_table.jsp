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
							<input onfocus=this.blur() type="radio" name="rf_chart_or_table" value="rfi_chart" onclick="onChartOrTable(this.value);" />Reference Interval Charts (Scattered Graphs)	
						</span>														   		
					</td>
					<td colspan=2 align="left">
						<span style="color: #616161; font-size:10pt; font-family: times, 'Times New Roman', serif; vertical-align: middle;">
							<input onfocus=this.blur() type="radio" name="rf_chart_or_table" value="rfi_table" onclick="onChartOrTable(this.value);" checked/>Reference Interval Custom Tables	
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
					<td><input type="radio" name="age_group" value="0" onclick="onSelectAge(this.value);"/>0-30Days</td>
					<td><input type="radio" name="age_group" value="1" onclick="onSelectAge(this.value);"/>31D-12M</td>
					<td><input type="radio" name="age_group" value="2" onclick="onSelectAge(this.value);"/>1 Year</td>
					<td><input type="radio" name="age_group" value="3" onclick="onSelectAge(this.value);"/>2 Years</td>
					<td><input type="radio" name="age_group" value="4" onclick="onSelectAge(this.value);"/>3 Year</td>
					<td><input type="radio" name="age_group" value="5" onclick="onSelectAge(this.value);"/>4 Years</td>
					<td><input type="radio" name="age_group" value="6" onclick="onSelectAge(this.value);"/>5 Years</td>
				</tr>
				<tr>
					<td><input type="radio" name="age_group" value="7" onclick="onSelectAge(this.value);"/>7 Years</td>
					<td><input type="radio" name="age_group" value="8" onclick="onSelectAge(this.value);"/>7 Years</td>
					<td><input type="radio" name="age_group" value="9" onclick="onSelectAge(this.value);"/>8 Year</td>
					<td><input type="radio" name="age_group" value="10" onclick="onSelectAge(this.value);"/>9 Years</td>
					<td><input type="radio" name="age_group" value="11" onclick="onSelectAge(this.value);"/>10 Year</td>
					<td><input type="radio" name="age_group" value="12" onclick="onSelectAge(this.value);"/>11 Years</td>
					<td><input type="radio" name="age_group" value="13" onclick="onSelectAge(this.value);"/>12 Years</td>
				</tr>
				<tr>
					<td><input type="radio" name="age_group" value="14" onclick="onSelectAge(this.value);"/>13 Years</td>
					<td><input type="radio" name="age_group" value="15" onclick="onSelectAge(this.value);"/>14 Years</td>
					<td><input type="radio" name="age_group" value="16" onclick="onSelectAge(this.value);"/>15 Year</td>
					<td><input type="radio" name="age_group" value="17" onclick="onSelectAge(this.value);"/>16 Years</td>
					<td><input type="radio" name="age_group" value="18" onclick="onSelectAge(this.value);"/>17 Year</td>
					<td><input type="radio" name="age_group"  value="19" onclick="onSelectAge(this.value);" checked/>18 Years</td>
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
					<td colspan=2><input type="radio" name="ethnic_group" value="4" onclick="onSelectEthnic(this.value);"/>Chinese</td>
					<td colspan=2><input type="radio" name="ethnic_group" value="9" onclick="onSelectEthnic(this.value);"/>South Asian</td>
					<td colspan=2><input type="radio" name="ethnic_group" value="11" onclick="onSelectEthnic(this.value);"/>Caucasian</td>
					<td><input type="radio" name="ethnic_group"  value="12" onclick="onSelectEthnic(this.value);" checked/>All(*)</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="4"><input type="submit" value="Submit" onclick="javascript:onClickSubmit();"></td></tr>
</table> 	  