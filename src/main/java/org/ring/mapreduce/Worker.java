package org.ring.mapreduce;

/**
 * Worker
 * 
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 * @param <I>
 *            Input
 * @param <O>
 *            Output
 */
public interface Worker<I, O> {

	O doJob(I input);

}
