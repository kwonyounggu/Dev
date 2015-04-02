package com.sickkids.caliper.model
{			
	import com.sickkids.caliper.controller.SequentialAction;
	import com.sickkids.caliper.vo.UserInfoBean;
	
	import flash.media.Camera;
	import flash.media.Microphone;
	
	import mx.collections.ArrayCollection;
	
	import org.robotlegs.mvcs.Actor;
	
	public class TttModel extends Actor
	{	
		public var localMic:Microphone=null;
		public var localCamera:Camera=null;
		[Bindable] public var userInfo:UserInfoBean=new UserInfoBean();
		public var synchObj:SequentialAction=null;
				
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
			trace("INFO: initialize() of TttModel.as is called ...");
			userInfo.setExternalVars();
			
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