package org.red5.core;

import java.util.List;
import java.util.Map;

import org.red5.server.api.IAttributeStore;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.api.so.ISharedObjectBase;
import org.red5.server.api.so.ISharedObjectListener;
import org.red5.server.so.SharedObjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SOEventListener implements ISharedObjectListener 
{
	protected static Log log = LogFactory.getLog(SOEventListener.class.getName());
	
	/**
	 * Called when a client connects to a shared object.
	 * 
	 * @param so
	 *            the shared object
	 */
	public void onSharedObjectConnect(ISharedObjectBase so)
	{
		System.out.println("onSharedObjectConnect(.) is called in SOEventListener.java");
	}

	/**
	 * Called when a client disconnects from a shared object.
	 * 
	 * @param so
	 *            the shared object
	 */
	public void onSharedObjectDisconnect(ISharedObjectBase so)
	{
		System.out.println("onSharedObjectDisconnect(.) is called in SOEventListener.java");
	}
	/**
	 * Called when a shared object attribute is updated.
	 * 
	 * @param so
	 *            the shared object
	 * @param key
	 *            the name of the attribute
	 * @param value
	 *            the value of the attribute
	 */
	public void onSharedObjectUpdate(ISharedObject so, String key, Object value) 
	{
		System.out.println("onSharedObjectUpdate(ISharedObject so, String key, Object value) is called in SOEventListener.java");
	}

	/**
	 * Called when multiple attributes of a shared object are updated.
	 * 
	 * @param so
	 *            the shared object
	 * @param values
	 *            the new attributes of the shared object
	 */
	
	public void onSharedObjectUpdate(ISharedObjectBase so, IAttributeStore values)
	{
		System.out.println("onSharedObjectUpdate(..) is called in SOEventListener.java");
	}
	
	/**
	 * Called when a shared object attribute is updated.
	 * 
	 * @param so
	 *            the shared object
	 * @param key
	 *            the name of the attribute
	 * @param value
	 *            the value of the attribute
	 */
	public void onSharedObjectUpdate(ISharedObjectBase so, String key, Object value)
	{
		System.out.println("onSharedObjectUpdate(ISharedObjectBase so, String key, Object value) is called in SOEventListener.java");
		//The attribute <key> of the shared object <so>
		// was changed to <value>.
		// Do something....
		System.out.println("---------->"+so.toString() +"| Key:"+key+", Value:"+value);
		System.out.println("---------->SO Update has triggered the Listener's onSharedObjectUpdate method.");
	}
	
	/**
	 * Called when multiple attributes of a shared object are updated.
	 * 
	 * @param so
	 *            the shared object
	 * @param values
	 *            the new attributes of the shared object
	 */
	
	public void onSharedObjectUpdate(ISharedObjectBase so, Map<String, Object> values)
	{
		System.out.println("onSharedObjectUpdate(ISharedObjectBase so, Map<String, Object> values) is called in SOEventListener.java");
	}

	/**
	 * Called when an attribute is deleted from the shared object.
	 * 
	 * @param so
	 *            the shared object
	 * @param key
	 *            the name of the attribute to delete
	 */
	public void onSharedObjectDelete(ISharedObjectBase so, String key)
	{
		System.out.println("onSharedObjectDelete(ISharedObjectBase so, String key) is called in SOEventListener.java");
	}

	/**
	 * Called when all attributes of a shared object are removed.
	 * 
	 * @param so
	 *            the shared object
	 */
	public void onSharedObjectClear(ISharedObjectBase so)
	{
		System.out.println("onSharedObjectClear(ISharedObjectBase so) is called in SOEventListener.java");
	}

	/**
	 * Called when a shared object method call is sent.
	 * 
	 * @param so
	 *            the shared object
	 * @param method
	 *            the method name to call
	 * @param params
	 *            the arguments
	 */
	public void onSharedObjectSend(ISharedObjectBase so, String method, List params)
	{
		System.out.println("onSharedObjectSend(ISharedObjectBase so, String method, List params) is called in SOEventListener.java");
	}
}