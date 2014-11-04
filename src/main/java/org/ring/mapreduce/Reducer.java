package org.ring.mapreduce;

import java.util.Collection;

/**
 * Reduce Function
 * 
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 * @param <S>
 *            Source
 * @param <T>
 *            Target
 */
public interface Reducer<S, T> {

	T reduce(Collection<S> source);

}
