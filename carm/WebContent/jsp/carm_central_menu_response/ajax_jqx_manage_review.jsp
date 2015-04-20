<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/jsp/error/jsp_error_page.jsp" %>
<%@ page import="com.sickkids.caliper.common.*" %>
<%@ page import="com.sickkids.caliper.database.*" %>
<%@ page import="com.sickkids.caliper.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored ="false" %>
<style>
<!--
	.review_table
	{
	    position: relative;
	    background-color: #FFFFFF;
	    padding: 20px 20px 20px 20px;
	    -moz-border-radius: 4px;
	    -webkit-border-radius: 4px;
	    border-radius: 4px;
	}
	
	
	.review_table table td 
	{
	    vertical-align: top;
	    border: 1px solid #CCCCCC;
	    font-size: 12px;
	    padding-top: 5px;
	}
	
	.inner_review_table
	{
	}
	.inner_review_table table td 
	{
	    vertical-align: top;
	    border: 0px solid #CCCCCC;
	    font-size: 12px;
	    padding-top: 0px;
	}
	/*Popup Window*/
	.popbox 
	{
    display: none;
    position: absolute;
    z-index: 99999;
    width: 400px;
    padding: 10px;
    background: #EEEFEB;
    color: #000000;
    border: 1px solid #4D4F53;
    margin: 0px;
    -webkit-box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
    box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
	}
	.popbox h2 
	{
	    background-color: #4D4F53;
	    color: #E3E5DD;
	    font-size: 14px;
	    display: block;
	    width: 100%;
	    margin: -10px 0px 8px -10px;
	    padding: 5px 10px;
	}
-->
</style>
<%!
	//provide a list of reviewed resutls from peers in a given item
	public String peersStatus()
	{
		return "agree";//agree, disagree, not applicable yet if no information of myself
	}
	
%>

<%
	CarmSQLDao csqlDao=(CarmSQLDao)application.getAttribute("csqlDao");
	List<AllPointDescBean> pointDescList=(List<AllPointDescBean>)request.getAttribute("point_desc_list");	
	AllRegisteredUserBean crb=(AllRegisteredUserBean)session.getAttribute("crb");
	int item_id=Integer.parseInt(request.getParameter("carm_id"));
	List<String> peerList=csqlDao.getGenericStringList("select reviewer_id from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id+" and reviewer_id!='"+crb.getUser_id()+"'");
	System.out.println("peerList.length="+peerList.size());
	//List<AllReviewDataBean> peerReviewList=csqlDao.getListFromAllReviewDataTable(arg)
	//The peerList can be from String list using genericStringList in two combined tables
	String reviewStatus="";
	if(crb.getLogin_level()==4)//reviewer
		reviewStatus=csqlDao.getGenericString("select reviewer_action_status from all_item_reviewer_mx where app_id=1 and group_id=1 and item_id="+item_id+" and reviewer_id='"+crb.getUser_id()+"'");
%>

<table cellpadding='0' cellspacing='0' width='100%' style='padding: 10px 10px 10px 10px;'>
   <tr>
    <td colspan='5'><div><label><u>Review</u></label><span style='color: #888BFB; font-size: 9.5px; margin-left: 5px;'><img src='images/common/item_icon.png' style='margin-left: 5px; margin-right: 5px; vertical-align: bottom;'/><%=item_id%> [review status: <%=AllItemReviewerBean.reviewStatus(reviewStatus) %>]</span> </div>
	</td>
  </tr>
  
  <tr>
    <td colspan='5'>
       <div class="review_table">
    	<table cellpadding='2' cellspacing='2' width='100%' style='padding: 10px 10px 10px 10px;'>
    		<thead>
			<tr>
				<td bgcolor="#E4E4C0" align='center' width="5%"><div>No</div></td> 
				<td bgcolor="#E4E4C0" align='center' width="40%"><div>Please describe your ...</div></td>
				<td bgcolor="#E4E4C0" align='center' width="55%"><div>Rate/Comments</div></td>		
			</tr>
			</thead>
			<tbody>
			<%
				AllReviewDataBean ab=null;
			    String sInfo="";
				for(int i=0;i<pointDescList.size();i++)
				{
					AllPointDescBean pb=pointDescList.get(i);
					if(i%2==0) out.print("<tr style='background-color:#E8E8E8;'>");
					else out.print("<tr style='background-color:#ffffff;'>");
					
					out.print("<td align='center' valign='top' width='5%'>"+(i+1)+"</td>");
					if(i==2) //Append a popup window to show the detailed meanings of each scale
						out.print("<td align='left' valign='top' width='40%' style='padding-left: 5px;'>"+pb.getPoint_explanation()+"<br /><a href='#' class='popper' data-popbox='pop1'>[mouse-over here for details]</a>"+"</td>");
					else
						out.print("<td align='left' valign='top' width='40%' style='padding-left: 5px;'>"+pb.getPoint_explanation()+"</td>");
					out.print("<td align='left' valign='top' width='55%' style='padding-left: 5px; padding-right: 5px;'>");
					ab=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+item_id+" and point_id="+(i+1)+" and reviewer_id='"+crb.getUser_id()+"'");//if review_time is not null, then there is a record
					if(i<9)
					{
						out.print("<div class='inner_review_table'><table width='100%' style='border-style:hidden;'>");
						out.print("<tr>");
						out.print("<td colspan='2' > ");
					}
					switch((i+1))
					{
					case 1: //Concordance														
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>very Weak");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q1RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("&nbsp;very Strong</span>");
							
							break;
					case 2: //Causation							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>very Weak");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q2RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");
							out.print("&nbsp;very Strong</span>");
							
							break;
					case 3: //Potential preventability							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>very Weak");
							for(int j=1;j<7;j++)
							{
								switch(j)
								{
									case 1: sInfo="Virtually no evidence of preventability"; break;
									case 2: sInfo="Slight to modest evidence of preventability"; break;
									case 3: sInfo="Preventability not quite likely (less than 50/50, but \"close call\")"; break;
									case 4: sInfo="Preventability more than likely (more than 50/50, but \"close call\")"; break;
									case 5: sInfo="Strong evidence of preventability"; break;
									case 6: sInfo="Virtually certain evidence of preventability"; break;
									default: sInfo=""; break;
								}
								out.print("<a href='#' title='"+sInfo+"'><input type='radio' name='q3RadioGroup' style='vertical-align: bottom;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input></a>");
							}
							out.print("&nbsp;very Strong</span>");
							
							break;
					case 4: //confidence							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>very Weak");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q4RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("&nbsp;very Strong</span>");
							
							break;
					case 5: //estimate of completeness							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>very Weak");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q5RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("&nbsp;very Strong</span>");
							
							break;
					case 6: //							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q6RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("<input type='radio' name='q6RadioGroup' style='vertical-align: middle;' value='11' "+((int)ab.getPoint_value_number()==11? "checked":"")+">&gt;10 Hours</input></span>");
							
							break;							
					case 7: //							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q7RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");
							out.print("<input type='radio' name='q7RadioGroup' style='vertical-align: middle;' value='11' "+((int)ab.getPoint_value_number()==11? "checked":"")+">&gt;10 Hours</input></span>");
							
							break;				
					case 8: //							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q8RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("<input type='radio' name='q8RadioGroup' style='vertical-align: middle;' value='11' "+((int)ab.getPoint_value_number()==11? "checked":"")+">&gt;10 Hours</input></span>");
							
							break;		
					case 9: //							
							out.print("<span style='padding-left: 5px; vertical-align: bottom;'>");
							for(int j=0;j<11;j++)
								out.print("<input type='radio' name='q9RadioGroup' style='vertical-align: middle;' value='"+j+"' "+((int)ab.getPoint_value_number()==j? "checked":"")+">"+j+"</input>");	
							out.print("<input type='radio' name='q9RadioGroup' style='vertical-align: middle;' value='11' "+((int)ab.getPoint_value_number()==11? "checked":"")+">&gt;10 Hours</input></span>");
							
							break;		
					case 10: //
							out.print("<div class='textwrapper'><textarea  rows='5' id='q10'>"+ab.getPoint_value_string()+"</textarea></div>");
							break;
					default: break;
					}
					if(i<9)
					{
						out.print("</td>");
						out.print("</tr>");
						out.print("<tr><td colspan=2 height=1 align='right'><hr/></td></tr>");
						out.print("<tr><td width='50%'></td><td width='50%' align='right' style='font-size: 9.5px; color: #888BFB; vertical-align: middle; padding-right: 12px;'>");
						if(peerList.size()<1 || ab.getReview_time()==null) 
						{
							if(crb.getLogin_level()==3 || crb.getLogin_level()==4) out.print("Peer(s): Invisible");
						}
						else
						{
							out.print("Peer(s): ");
							for(int p=0;p<peerList.size();p++)
							{
								AllReviewDataBean peerBean=csqlDao.getOneRecordFromAllReviewDataTable("where app_id=1 and group_id=1 and item_id="+item_id+" and point_id="+(i+1)+" and reviewer_id='"+peerList.get(p)+"'");
								out.print((peerBean.getReview_time()==null? "Invisible":(peerBean.getPoint_value_number()==ab.getPoint_value_number()? "Agree":"Disagree")));
								if((p+1)<peerList.size()) out.print(" | ");
							}
						}
						out.print("</td></tr>");
						out.print("</table></div>");
					}
					out.print("</td>");

					out.print("</tr>");
				}
				
			%>			
			</tbody>
    	</table>
       </div>
       <div style='text-align: right; padding-right: 30px;'>
       		<img id='spinner_img' src='images/common/spinner.gif' width=32 height=32  class="spinner_hidden"/>
            <input style="margin-right: 5px;" type="button" id="submitReviewButton" value="Submit" />	    
       </div>
       <div id="pop1" class="popbox">
		    <h2>The Preventability Scale</h2> 
		    <p>1: Virtually no evidence of preventability</p>
		    <p>2: Slight to modest evidence of preventability</p>
		    <p>3: Preventability not quite likely (less than 50/50, but "close call")</p>
		    <p>4: Preventability more than likely (more than 50/50, but "close call")</p>
		    <p>5: Strong evidence of preventability</p>
		    <p>6: Virtually certain evidence of preventability</p>
		</div>
	</td>
  </tr>
</table>

<script language="Javascript" type="text/javascript">
	$(document).ready(function () 
	{		
        $("#submitReviewButton").on('click', function () 
        {
        	var login_level=parseInt("${crb.login_level}");
    		var seniority=parseInt("${crb.seniority}");
    		var reviewStatus="<%=reviewStatus%>";
    		var itemID="<%=item_id%>";
    		
    		if(login_level!=4)		
    		{
    			alert("You are not allowed to review this!");
    			return;
    		}
    		else if(reviewStatus.indexOf("NOT_EDITABLE")!=-1 || "<%=AllItemReviewerBean.reviewStatus(reviewStatus) %>".indexOf("Not Applicable")!=-1)//found
    		{
    			alert("You are not allowed to review this anymore!");//this is to prevent editing from COMPLETE_NOT_EDTIABLE or INCOMPLETE_NOT_EDITABLE
    			return;
    		}
    		else
    		{
    			var ans=[];
    			for(var q=1; q<10; q++)
    				if((ans[q]=getRadioValue("q"+q+"RadioGroup"))=="") 
    				{
    					alert("Question "+q+" is not answered!");
    					return;
    				}
    			var sAns=ans.toString();
    			sAns=sAns.substring(1, sAns.length);
    				
    			//log("["+ans.toString()+"]|"+ans.valueOf()+"|"+sAns+"|"+jQuery("#q10").val());
    			document.getElementById("spinner_img").className="spinner_unhidden";
    			httpRequestPost("<%= MenuLink.CARM_CENTRAL_CONTEXT %>","op=ajax_carm_central_save_review_data"+
    																	 "&app_id=${crb.app_id}"+
    																	 "&group_id=1"+
    																	 "&item_id="+itemID+
    																	 "&q1to9="+sAns+
    																	 "&review_status="+reviewStatus+
    																	 "&q10="+escape(jQuery("#q10").val()), "saveReviewDataResponse");

    		}
    		
        });
    	var moveLeft = 0;
    	var moveDown = 0;
    	$('a.popper').hover(function (e) 
    	{
    	
    	    var target = '#' + ($(this).attr('data-popbox'));
    	    $(target).show();
    	    moveLeft = $(this).outerWidth();
    	    moveDown = ($(target).outerHeight() / 2);
    	}, function () 
    	{
    	    var target = '#' + ($(this).attr('data-popbox'));
    	    if (!($("a.popper").hasClass("show"))) {
    	        $(target).hide();
    	    }
    	});
    	
    	$('a.popper').mousemove(function (e) 
    	{
    	    var target = '#' + ($(this).attr('data-popbox'));
    	
    	    leftD = e.pageX + parseInt(moveLeft);
    	    maxRight = leftD + $(target).outerWidth();
    	    windowLeft = $(window).width() - 40;
    	    windowRight = 0;
    	    maxLeft = e.pageX - (parseInt(moveLeft) + $(target).outerWidth() + 20);
    	
    	    if (maxRight > windowLeft && maxLeft > windowRight) {
    	        leftD = maxLeft;
    	    }
    	
    	    topD = e.pageY - parseInt(moveDown);
    	    maxBottom = parseInt(e.pageY + parseInt(moveDown) + 20);
    	    windowBottom = parseInt(parseInt($(document).scrollTop()) + parseInt($(window).height()));
    	    maxTop = topD;
    	    windowTop = parseInt($(document).scrollTop());
    	    if (maxBottom > windowBottom) {
    	        topD = windowBottom - $(target).outerHeight() - 20;
    	    } else if (maxTop < windowTop) {
    	        topD = windowTop + 20;
    	    }
    	
    	    $(target).css('top', topD).css('left', leftD);
    	});
    	$('a.popper').click(function (e) 
    	{
    	    var target = '#' + ($(this).attr('data-popbox'));
    	    if (!($(this).hasClass("show"))) {
    	        $(target).show();
    	    }
    	    $(this).toggleClass("show");
    	});
	});
	function saveReviewDataResponse(strResponse)
	{
		document.getElementById("spinner_img").className="spinner_hidden";
		if(strResponse.indexOf('session_timeout')==0) 
		{
			alert("Your session is expired. Please login again.");
			location.reload();
		}
		else if(strResponse.indexOf('false_ajax_carm_central_save_review_data:')==-1)//not found, means true
		{
			alert("Successfully submitted!");
			callAjaxAccordingly($('#jqxReviewTabs').jqxTabs('selectedItem'), $("#jqxEventList").jqxDropDownList('getSelectedItem'));//this will reload the same review item.
		}
		else 
		{
			alert(strResponse.substring("false_ajax_carm_central_save_review_data:".length));//false, then display			
		}
	}
</script>