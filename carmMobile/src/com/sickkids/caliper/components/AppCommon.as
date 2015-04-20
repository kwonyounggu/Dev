package com.sickkids.caliper.components
{
	import com.ericfeminella.collections.HashMap;
	
	import flash.net.SharedObject;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	public class AppCommon
	{
		public function AppCommon()
		{
		}
		private static var _cap_sharedLoginObject:SharedObject;
		public static function get cap_sharedLoginObject():SharedObject 
		{
			if(!_cap_sharedLoginObject)
				_cap_sharedLoginObject = SharedObject.getLocal( "com.sickkids.caliper.model.application.capLoginObject" );
			
			return _cap_sharedLoginObject;
		}
		public static function cap_setSharedLoginObject(loginId:String, password:String, bChecked:Boolean):void
		{
			cap_sharedLoginObject.data.loginId=loginId;
			cap_sharedLoginObject.data.password=password;
			cap_sharedLoginObject.data.bRememberCheckBox=bChecked;
			cap_sharedLoginObject.flush();
		}
		private static var _cap_sharedItemListObject:SharedObject;
		public static function get cap_sharedItemListObject():SharedObject 
		{
			if(!_cap_sharedItemListObject)
				_cap_sharedItemListObject = SharedObject.getLocal( "com.sickkids.caliper.model.application.capItemListObject" );
			
			return _cap_sharedItemListObject;
		}
		public static function cap_setSharedItemListObject(itemList:ArrayCollection):void
		{
			cap_sharedItemListObject.data.date=new Date();
			cap_sharedItemListObject.data.itemList=itemList;
			cap_sharedItemListObject.flush();
		}
		
		private static var _app_sharedAppListObject:SharedObject;
		public static function get app_sharedAppListObject():SharedObject 
		{
			if(!_app_sharedAppListObject)
				_app_sharedAppListObject = SharedObject.getLocal( "com.sickkids.caliper.model.application.appListObject" );
			
			return _app_sharedAppListObject;
		}
		/*public static function app_setSharedAppListObject(appMap:HashMap):void
		{
			registerClassAlias("com.ericfeminella.collections.HashMap", HashMap);
			app_sharedAppListObject.data.appMap=appMap;
			app_sharedAppListObject.flush();
		}*/
		public static function app_setSharedAppListObject(appList:ArrayCollection):void
		{
			app_sharedAppListObject.data.appList=appList;
			app_sharedAppListObject.flush();
		}
		public static function contains(str1:String, str2:String):Boolean
		{
			return str1.indexOf(str2)>0;
		}
		
		//validation
		public static function validatePassword(pwd:String):Array
		{	
			var re:RegExp = /^[A-Za-z0-9]\w{3,}[A-Za-z0-9]$/;//alpha-numeric combinations without any order
			return pwd.match(re);
		}
		public static function verify2Passwords(pwd:String,cpwd:String):Boolean
		{	
			var is_ok:Boolean=true;
			
			if(pwd==null || cpwd==null || !validatePassword(pwd) || !validatePassword(cpwd) ||pwd!=cpwd)
			{	
				is_ok=false;
			}
			return is_ok;		
		}
	}
}