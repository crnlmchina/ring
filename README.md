ring
====

Simplify asynchronous method invocation

	<!-- Factory to create delegate objects who execute asynchronous -->
	<bean id="ringFactory" class="org.ring.proxy.multithread.MultiThreadRingFactory">
		<constructor-arg name="fixPoolSize" value="50" />
	</bean>
	
	<bean id="newCache" factory-bean="ringFactory" factory-method="delegate">
		<constructor-arg name="obj" ref="xxBean" />
		<constructor-arg name="inter" value="com.xxx.XXInterface" />
	</bean>


And then, mark the methods to be invoked asynchronous of xxBean with @Background.

	@Background
	public void someMethod()
