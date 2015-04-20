<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>

<%!

public String adjustNull(String s)
{
	return s==null ? "":s;
}

public String adjustS(String s)
{
	return s.length()>3 ? ("<a title='"+s+"'><span style='font-size: 9px;'>"+s.substring(0, 4)+"</span></a>"):s;
}
%>

<%
	CarmO_DE6Bean db6Bean=(CarmO_DE6Bean)request.getAttribute("radiology_record");
%>

<table cellpadding='2' cellspacing="4" width='100%' style="padding: 10px 40px 20px 40px;">
   <tr>
    <td><div><label><u>Recent Radiology</u></label><span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=request.getParameter("carm_id")%></span></div>
	</td>
  </tr>
  <tr>
    <td><span class="title" style="border-color: #CCCCCC;">Chest Imaging</span> 
		<div class="box" style="border-color: #CCCCCC;">
		<p style="padding: 5px 20px 5px 20px;">
			<%=adjustNull(db6Bean.getChestImaging()) %>
		</p>
		</div>
	</td>
  </tr>
  <tr>
    <td><span class="title" style="border-color: #CCCCCC;">CNS Imaging</span> 
		<div class="box" style="border-color: #CCCCCC;">
		<p style="padding: 5px 20px 5px 20px;">
			<%=adjustNull(db6Bean.getCnsImaging()) %>
		</p>
		</div>
	</td>
  </tr>
  <tr>
    <td><span class="title" style="border-color: #CCCCCC;">Abdominal Imaging</span> 
		<div class="box" style="border-color: #CCCCCC;">
		<p style="padding: 5px 20px 5px 20px;">
			<%=adjustNull(db6Bean.getAbdominalImaging()) %>
		</p>
		</div>
	</td>
  </tr>
  <tr>
    <td><span class="title" style="border-color: #CCCCCC;">Other Imaging</span> 
		<div class="box" style="border-color: #CCCCCC;">
		<p style="padding: 5px 20px 5px 20px;">
			<%=adjustNull(db6Bean.getOtherImaging()) %>
		</p>
		</div>
	</td>
  </tr>
</table>

