<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sickkids.caliper.common.*" %>	
 
<table cellspacing="0" cellpadding="0" width="1000">
	<tr>
		<td id="main-nav" class="ddsmoothmenu" align="left">		
			<ul>
				<li><a href="<%= MenuLink.menu_public_home %>" onfocus="this.blur()">Home</a></li>
				<li><a href="" onfocus="this.blur()">Research</a>
					<ul>
					   <li><a href="<%= MenuLink.menu_public_research_spire%>" onfocus="this.blur()">SPIRE</a></li>
<!--                   <li><a href="<%= MenuLink.menu_public_research_ttt%>" onfocus="this.blur()">TTT Education</a></li>-->
                       <li><a href="http://www.centerforsafetyresearch.com/exemplar" onfocus="this.blur()">EXEMPLAR</a></li>
                       <li><a href="http://deciduous.research.sickkids.ca" onfocus="this.blur()">DECIDUOUS</a></li>
<!--                        <li><a href="https://www.centerforsafetyresearch.com/carm" onfocus="this.blur()">Cardiac Arrest Preventability</a></li> -->
                       <li><a href="/carm" onfocus="this.blur()">Cardiac Arrest Preventability</a></li>
                       <li><a href="<%= MenuLink.menu_public_research_resident_scheduling %>" onfocus="this.blur()">Fatigue and Performance</a></li>
                       <li><a href="<%= MenuLink.menu_public_research_bpews %>" onfocus="this.blur()">BPEWS</a></li>
                       <li><a href="<%= MenuLink.menu_public_research_epoch %>" onfocus="this.blur()">EPOCH</a></li>
				  	</ul>
				</li>
				<li><a href="<%= MenuLink.menu_public_publications %>" onfocus="this.blur()">Publications</a>
				</li>
				<li><a href="<%= MenuLink.menu_public_collaborators %>" onfocus="this.blur()">Collaborators</a>
					
				</li>
				<li><a href="<%= MenuLink.menu_public_news %>" onfocus="this.blur()">News</a>
				</li>			
				<li><a href="<%= MenuLink.menu_public_contactus %>" onfocus="this.blur()">Contact Us</a></li>
			  </ul>	
		</td>
	</tr> 
</table>