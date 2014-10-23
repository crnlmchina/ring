package org.ring.proxy;

/**
 * The factory will delegate the objects that has method to be invoked
 * asynchronous, the method marked with @Background will return null.
 * 
 * @author yuxuan.wang
 * 
 */
public interface RingFactory {

	/**
	 * Delegate some object
	 * 
	 * @param obj
	 *            Object to be delegated
	 * @param inter
	 *            The object's interface to be opened in proxy object
	 * @return
	 */
	<T> T delegate(Object obj, Class<T> inter);

}
