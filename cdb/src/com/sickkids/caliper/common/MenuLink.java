package com.sickkids.caliper.common;


public class MenuLink 
{
	public static final String CONTEXT="/cdb/controller";
	
	
	public static final String about_reference_intervals=CONTEXT+"?op=menu_about_reference_intervals&sm=0";
	public static final String caliper_study=CONTEXT+"?op=menu_caliper_study&sm=0";
	public static final String aboutcaliper_calipermoment=CONTEXT+"?op=menu_aboutcaliper_calipermoment&sm=3";
	
	public static final String howtoparticipate_howtoparticipate=CONTEXT+"?op=menu_howtoparticipate_howtoparticipate&sm=0";
	public static final String howtoparticipate_volunteer=CONTEXT+"?op=menu_howtoparticipate_volunteer&sm=1";
	public static final String howtoparticipate_clinics=CONTEXT+"?op=menu_howtoparticipate_clinics&sm=2";
	
	
	public static final String labpro_labpro=CONTEXT+"?op=menu_labpro_labpro&sm=0";
	public static final String labpro_samplecollection=CONTEXT+"?op=menu_labpro_samplecollection&sm=1";
	public static final String labpro_diagnostictests=CONTEXT+"?op=menu_labpro_diagnostictests&sm=2";
	public static final String labpro_studydesign=CONTEXT+"?op=menu_labpro_studydesign&sm=3";
	public static final String labpro_analysis=CONTEXT+"?op=menu_labpro_analysis&sm=4";
	public static final String labpro_publications=CONTEXT+"?op=menu_labpro_publications&sm=5";
	
	public static final String downloads=CONTEXT+"?op=menu_downloads_";
	public static final String reference_intervals=CONTEXT+"?op=menu_reference_intervals&sm=0";
	public static final String menu_print_reference_intervals=CONTEXT+"?op=menu_print_reference_intervals&sm=0";
	public static final String downloads_raw_data=CONTEXT+"?op=menu_downloads_raw_data&sm=1";
	public static final String custom_reference_intervals=CONTEXT+"?op=menu_custom_reference_intervals&sm=2";
	
	public static final String community=CONTEXT+"?op=menu_community_";	
	public static final String community_faq=CONTEXT+"?op=menu_community_faq&sm=0&search=0&stext=&scategory=0&section=1&page=1";
	public static final String community_news=CONTEXT+"?op=menu_community_news&sm=1&search=0&stext=&scategory=0&section=1&page=1";
	public static final String community_q_and_a=CONTEXT+"?op=menu_community_q_and_a&sm=2&search=0&stext=&scategory=0&section=1&page=1";
	
	public static final String home=CONTEXT+"";
	public static final String contactus=CONTEXT+"?op=contactus";
	public static final String photos=CONTEXT+"?op=photos";
	public static final String sitemap=CONTEXT+"?op=sitemap";
	public static final String signup=CONTEXT+"?op=signup&sm=0";
	public static final String jobs=CONTEXT+"?op=jobs";
	public static final String login=CONTEXT+"?op=login";
	public static final String logout=CONTEXT+"?op=logout_check";
	
	public static final String forgot_pwd=CONTEXT+"?op=forgot_pwd";
	
	public static final String whatiscaliper=CONTEXT+"?op=whatiscaliper&sm=0";
	public static final String caliper_program=CONTEXT+"?op=caliper_program&sm=0";
	public static final String caliper_publications_presentations=CONTEXT+"?op=caliper_publications_presentations&sm=0";
	
	public static final String SMARTPHONE_CONTEXT="/cdb/smartphone";
	public static final String smartphone_reference_intervals=SMARTPHONE_CONTEXT+"?op=reference_intervals&sm=0";
	
	public static String getQaLink(String search, String stext, String scategory, int section, int page)
	{
		if(search==null) search="0";
		if(stext==null) stext="";
		if(scategory==null) scategory="0";
		return CONTEXT+"?op=menu_community_q_and_a&sm=2&search="+search+"&stext="+stext+"&scategory="+scategory+"&section="+section+"&page="+page;
	}
	public static String getFaqLink(String search, String stext, String scategory, int section, int page)
	{
		if(search==null) search="0";
		if(stext==null) stext="";
		if(scategory==null) scategory="0";
		return CONTEXT+"?op=menu_community_faq&sm=0&search="+search+"&stext="+stext+"&scategory="+scategory+"&section="+section+"&page="+page;
	}
	public static String getPubLink(String search, String stext, String scategory, int section, int page)
	{
		if(search==null) search="0";
		if(stext==null) stext="";
		if(scategory==null) scategory="0";
		return CONTEXT+"?op=menu_community_news&sm=1&search="+search+"&stext="+stext+"&scategory="+scategory+"&section="+section+"&page="+page;
	}
	public static String getAllInEnglishLink(String search, String stext, String scategory, int section, int page)
	{
		if(search==null) search="0";
		if(stext==null) stext="";
		if(scategory==null) scategory="0";
		return CONTEXT+"?op=menu_community_all_in_english&sm=6&search="+search+"&stext="+stext+"&scategory="+scategory+"&section="+section+"&page="+page;
	}
	
	
}
