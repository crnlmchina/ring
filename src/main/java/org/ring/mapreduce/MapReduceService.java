package org.ring.mapreduce;

/**
 * Map/Reduce 服务
 * 
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 */
public interface MapReduceService {

	<SM, TM, SR, TR> TR execute(MapReduce<SM, TM, SR, TR> mapReduce, SM source);

}
