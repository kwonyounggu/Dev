package com.sickkids.caliper.service
{
	import com.sickkids.caliper.vo.UserInfoBean;
	
	import flash.net.NetConnection;
	import flash.net.Responder;

	public interface INetConnectionService
	{
		function callService(method:String, res:Responder,arg1:Object=null,arg2:Object=null, arg3:Object=null):void 
		function roomConnect(userInfo:UserInfoBean):void
		function netConnection():NetConnection
		function close():void
	}
}
