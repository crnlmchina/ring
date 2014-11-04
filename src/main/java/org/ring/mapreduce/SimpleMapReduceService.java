package org.ring.mapreduce;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

/**
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 */
public class SimpleMapReduceService implements MapReduceService {

	private ExecutorService executorService;

	public SimpleMapReduceService(ExecutorService executorService) {
		super();
		this.executorService = Preconditions.checkNotNull(executorService);
	}

	public SimpleMapReduceService(int fixThreadSize) {
		this(Executors.newFixedThreadPool(fixThreadSize));
	}

	@Override
	public <SM, TM, SR, TR> TR execute(final MapReduce<SM, TM, SR, TR> mapReduce, SM source) {
		final Collection<TM> slices = mapReduce.getMapper().map(source);

		TR result = null;
		if (slices != null && !slices.isEmpty()) {
			final List<Future<SR>> futures = Lists.newArrayList();
			final CountDownLatch countDownLatch = new CountDownLatch(slices.size());
			for (final TM slice : slices) {
				final Future<SR> future = executorService.submit(new Callable<SR>() {
					@Override
					public SR call() {
						try {
							return mapReduce.getWorker().doJob(slice);
						} finally {
							countDownLatch.countDown();
						}
					}
				});
				futures.add(future);
			}

			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// JUST IGNORE
			}

			final List<SR> futureResults = Lists.newArrayList();
			for (Future<SR> future : futures) {
				try {
					futureResults.add(future.get());
				} catch (InterruptedException e) {
					// JUST IGNORE
				} catch (ExecutionException e) {
					throw Throwables.propagate(e);
				}
			}

			if (!futureResults.isEmpty()) {
				result = mapReduce.getReducer().reduce(futureResults);
			}

		}

		return result;
	}
}
