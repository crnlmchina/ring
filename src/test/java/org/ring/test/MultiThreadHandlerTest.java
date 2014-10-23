package org.ring.test;

import java.util.concurrent.Executors;

import org.junit.Test;
import org.ring.proxy.RingFactory;
import org.ring.proxy.multithread.MultiThreadRingFactory;

public class MultiThreadHandlerTest {

	@Test
	public void testInvoke(){
		RingFactory factory = new MultiThreadRingFactory(Executors.newFixedThreadPool(1));
		ITestService ts = factory.delegate(new TestService(), ITestService.class);
		ts.doIt();
		System.out.println("complete.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
