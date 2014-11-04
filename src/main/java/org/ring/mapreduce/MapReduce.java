package org.ring.mapreduce;

import com.google.common.base.Preconditions;

/**
 * Map Reduce
 * 
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 * @param <SM>
 *            Mapper Source
 * @param <TM>
 *            Mapper Target
 * @param <SR>
 *            Reducer Source
 * @param <TR>
 *            Reducer Target
 */
public class MapReduce<SM, TM, SR, TR> {

	private Mapper<SM, TM> mapper;

	private Reducer<SR, TR> reducer;

	private Worker<TM, SR> worker;

	private MapReduce(Mapper<SM, TM> mapper, Reducer<SR, TR> reducer, Worker<TM, SR> worker) {
		super();
		this.mapper = Preconditions.checkNotNull(mapper);
		this.reducer = Preconditions.checkNotNull(reducer);
		this.worker = Preconditions.checkNotNull(worker);
	}

	public static final <SM, TM, SR, TR> MapReduce<SM, TM, SR, TR> newInstance(Mapper<SM, TM> mapper, Reducer<SR, TR> reducer, Worker<TM, SR> worker) {
		return new MapReduce<SM, TM, SR, TR>(mapper, reducer, worker);
	}

	public Mapper<SM, TM> getMapper() {
		return mapper;
	}

	public Reducer<SR, TR> getReducer() {
		return reducer;
	}

	public Worker<TM, SR> getWorker() {
		return worker;
	}

}
