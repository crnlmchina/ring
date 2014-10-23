package org.ring.proxy.multithread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

import org.ring.annotation.Background;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle asynchronous invoke with thread pool
 * 
 * @author yuxuan.wang
 * 
 */
public class MultiThreadInvocationHandler implements InvocationHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultiThreadInvocationHandler.class);

	private ExecutorService executorService;

	private Object realObj;

	public MultiThreadInvocationHandler(Object realObj, ExecutorService executorService) {
		this.realObj = realObj;
		this.executorService = executorService;
	}

	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		Background background = method.getAnnotation(Background.class);
		if (background != null) {
			executorService.execute(new Runnable() {

				public void run() {
					try {
						method.invoke(realObj, args);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			});
			return null;
		}
		return method.invoke(realObj, args);
	}

}
