package org.ring.test;

import org.ring.annotation.Background;

public interface ITestService {

	@Background
	public abstract void doIt();

}