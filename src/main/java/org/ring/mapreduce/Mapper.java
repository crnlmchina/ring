package org.ring.mapreduce;

import java.util.Collection;

/**
 * Map Function
 * 
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 * @param <S>
 *            Source
 * @param <T>
 *            Target
 */
public interface Mapper<S, T> {

	Collection<T> map(S s);

}
