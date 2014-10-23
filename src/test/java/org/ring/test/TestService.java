package org.ring.test;

import org.ring.annotation.Background;


public class TestService implements ITestService {

	/* (non-Javadoc)
	 * @see org.ring.test.ITestService#doIt()
	 */
	@Background
	public void doIt(){
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Do it.");
	}
}
