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
	
	////// CENTRAL ADMIN MENUS///////
	public static final String SUPERADMIN_CONTEXT="/carm/admin";
	
	//Home
	public static final String menu_superadmin_home=SUPERADMIN_CONTEXT+"";
	public static final String superadmin_logout=SUPERADMIN_CONTEXT+"?op=superadmin_logout&sm=0";
	

	//////////////// CARM /////////////////////////////
	public static final String CARM_CENTRAL_CONTEXT="/carm/carm_controller";
	public static final String menu_carm_central_home=CARM_CENTRAL_CONTEXT+"";
	
	public static final String menu_carm_central_admin_create_login_account=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_admin_create_login_account&sm=0";
	public static final String menu_carm_central_admin_update_login_account=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_admin_update_login_account&sm=0";
	public static final String menu_carm_central_admin_group_management=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_admin_group_management&sm=0";
	public static final String menu_carm_central_admin_map_event_group_reviewer=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_admin_map_event_group_reviewer&sm=0";
	public static final String menu_carm_central_admin_clinical_summary=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_admin_clinical_summary&sm=0";
	
	public static final String menu_carm_jqx_browse_drugs=CARM_CENTRAL_CONTEXT+"?op=menu_carm_jqx_browse_drugs&sm=0";
	public static final String menu_carm_central_create_a_new_study=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_create_a_new_study&sm=0";
	public static final String menu_carm_central_update_study=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_update_study&sm=0";
	public static final String menu_carm_central_create_sdg_table=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_create_sdg_table&sm=0";
	public static final String menu_carm_central_update_sdg_table=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_update_sdg_table&sm=0";
	public static final String menu_carm_central_create_adverse_drug_event_table=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_create_adverse_drug_event_table&sm=0";
	public static final String menu_carm_central_update_adverse_drug_event_table=CARM_CENTRAL_CONTEXT+"?op=menu_carm_central_update_adverse_drug_event_table&sm=0";
	
	public static final String carm_central_logout=CARM_CENTRAL_CONTEXT+"?op=carm_central_logout&sm=0";
}
