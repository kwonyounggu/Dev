package com.sickkids.caliper.common;

public class MenuLink 
{
	////// PUBLIC MENUS ///////
	public static final String CONTEXT="/csr/controller";
	
	//Home
	public static final String home=CONTEXT+"";
	public static final String menu_public_home=CONTEXT+"";
	
	//Research
	public static final String menu_public_research_spire=CONTEXT+"?op=menu_public_research_spire";
	public static final String menu_public_research_ttt=CONTEXT+"?op=menu_public_research_ttt&sm=0";
	//public static final String menu_public_research_medication_safety=CONTEXT+"?op=menu_public_research_medication_safety&sm=0";
	public static final String menu_public_research_resident_scheduling=CONTEXT+"?op=menu_public_research_resident_scheduling&sm=0";
	public static final String menu_public_research_bpews=CONTEXT+"?op=menu_public_research_bpews&sm=0";
	public static final String menu_public_research_epoch=CONTEXT+"?op=menu_public_research_epoch&sm=0";
	
	//Publications
	public static final String menu_public_publications=CONTEXT+"?op=menu_public_publications&sm=0";
	//Collaborators
	public static final String menu_public_collaborators=CONTEXT+"?op=menu_public_collaborators&sm=0";
	//News
	public static final String menu_public_news=CONTEXT+"?op=menu_public_news&sm=0";
	//Contact us
	public static final String menu_public_contactus=CONTEXT+"?op=menu_public_contactus";
	

	////////////////TTT ONLINE EDUCATION /////////////////////////////
	public static final String TTT_CONTEXT="/ttt/controller";
	public static final String menu_ttt_home=TTT_CONTEXT+"";

	
	public static final String menu_ttt_admin_manage_hospitals=TTT_CONTEXT+"?op=menu_ttt_admin_manage_hospitals&sm=0";
	public static final String menu_ttt_admin_manage_accounts=TTT_CONTEXT+"?op=menu_ttt_admin_manage_accounts&sm=0";
	public static final String menu_ttt_admin_manage_training=TTT_CONTEXT+"?op=menu_ttt_admin_manage_training&sm=0";
	public static final String menu_ttt_admin_manage_fileupload=TTT_CONTEXT+"?op=menu_ttt_admin_manage_fileupload&sm=0";

	
	public static final String ttt_logout=TTT_CONTEXT+"?op=ttt_logout&sm=0";
}
