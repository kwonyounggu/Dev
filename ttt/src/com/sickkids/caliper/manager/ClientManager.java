package com.sickkids.caliper.manager;

import org.red5.server.api.IClient;

import org.red5.server.api.scope.IScope;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.api.so.ISharedObjectService;
import org.red5.server.util.ScopeUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;
import com.sickkids.caliper.vo.ClientVO;

/**
 * Class that keeps a list of client names in a SharedObject.
 * 
 * @author 

 */
public class ClientManager
{

	private static final Logger log = Utils.logger;

	/** Stores the name of the SharedObject to use. */
	private static String NAME = "user_manager";

	private IScope scope;

	/**
	 * Create a new instance of the client manager.
	 * 
	 * @param name
	 *                      name of the shared object to use
	 * @param persistent
	 *                      should the shared object be persistent
	 */
	public ClientManager(IScope scope)
	{
		this.scope = scope;
		ISharedObjectService service = (ISharedObjectService) ScopeUtils.getScopeService(scope, ISharedObjectService.class, false);
		service.createSharedObject(scope, NAME, false);
	}

	/**
	 * Return the shared object to use for the given scope.
	 * 
	 * @param scope
	 *                      the scope to return the shared object for
	 * @return the shared object to use
	 */
	private ISharedObject getSharedObject()
	{
		ISharedObjectService service = (ISharedObjectService) ScopeUtils.getScopeService(scope, ISharedObjectService.class, false);
		return service.getSharedObject(scope, NAME, false);
	}

	/**
	 * A new client connected. This adds the username to
	 * the shared object of the passed scope. 
	 * 
	 */
	public void addClient(IClient client)
	{
		ISharedObject so = getSharedObject();
		String username = (String) client.getAttribute("username");
		String uid = client.getId();
		ClientVO clientVO = new ClientVO(uid, username);
		so.setAttribute(uid, clientVO);
		log.info("addClient:" + username);
	}

	/**
	 * A client disconnected. This removes the username from
	 * the shared object of the passed scope.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String removeClient(IClient client)
	{
		
		
		ISharedObject so = getSharedObject();
		
		Map<String, Object> map=so.getAttributes();
		Iterator it=map.entrySet().iterator();
		while(it.hasNext())
		{	
			Map.Entry<String, Object> pairs=(Map.Entry<String, Object>)it.next();
			log.info(pairs.getKey()+": "+((ClientVO)pairs.getValue()).username);		
		}
		String uid = client.getId();
		if (!so.hasAttribute(uid))
		{
			log.info("uid="+uid+" has no attribute!!!");
			// SharedObject is empty. This happes when the last client
			// disconnects. In fact, it didn't happen even in the last client disconnection
			return null;
		}

		ClientVO clientVO = (ClientVO)so.getAttribute(uid);
		log.info("removeClient:" + clientVO.username);
		so.removeAttribute(uid);
		
		return clientVO.username;
	}

}

