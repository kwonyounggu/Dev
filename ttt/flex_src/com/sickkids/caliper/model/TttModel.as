package com.sickkids.caliper.model
{			
	import com.sickkids.caliper.components.AppCommon;
	import com.sickkids.caliper.vo.AllRegisteredUserBean;
	import com.sickkids.caliper.vo.CarmDeMXBeanM;
	import com.sickkids.caliper.vo.CarmO_DE6Bean;
	import com.sickkids.caliper.vo.UserInfoBean;
	
	import flash.media.Camera;
	
	import mx.collections.ArrayCollection;
	
	import org.robotlegs.mvcs.Actor;
	
	[Bindable]
	public class TttModel extends Actor
	{	
		
		public var localCamera:Camera=null;
		public var userInfo:UserInfoBean=new UserInfoBean();
		
		public var loginId:String="";
		public var password:String="";
		public var bRememberCheckBox:Boolean=false;
		public var appId:int=1;
		public var crbm:AllRegisteredUserBean=new AllRegisteredUserBean();
		public var itemList:ArrayCollection=null;
		public var selectedItemBean:CarmDeMXBeanM=null;
		public var vatalSignList:ArrayCollection=null;
		public var medicationList:ArrayCollection=null;
		public var radiologyRecord:CarmO_DE6Bean=null;
		public var qPointsList:ArrayCollection=null;
		
		
		//controlToken
		//onAir when the scheduled time is on
		
		public static const MAX_COMMENTS_LENGTH:int=2500;

		public function TttModel()
		{		
			trace("INFO: TttModel() is called in TttModel.as");
			super();
			initialize();
		}
		
		private function initialize():void
		{
			userInfo.setExternalVars();
			/*
			trace("INFO: initialize() of TttModel.as is called ...");
			loginId=AppCommon.cap_sharedLoginObject.data.loginId;
			password=AppCommon.cap_sharedLoginObject.data.password;
			bRememberCheckBox=AppCommon.cap_sharedLoginObject.data.bRememberCheckBox;
			itemList=AppCommon.cap_sharedItemListObject.data.itemList;
			trace("loginId="+loginId+", password="+password+", checkbox="+bRememberCheckBox);	
			*/
		}
		
		//The function is called from onRemove() in ReveiwMediator.as
		public function resetReviewViewData():void
		{
			trace("INFO: resetReviewViewData() of TttModel.as is called ...");
			
			//removeContents(vatalSignList);
			//removeContents(medicationList);
			//removeContents(qPointsList);
		}
		public function removeContents(aList:ArrayCollection):void
		{
			if(aList!=null) 
			{
				trace("INFO: removeContents() of TttModel.as is called ...");
				aList.removeAll()
				aList=null;
			}
		}
	}
}