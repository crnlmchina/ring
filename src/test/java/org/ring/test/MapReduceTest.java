package org.ring.test;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.ring.mapreduce.MapReduce;
import org.ring.mapreduce.MapReduceService;
import org.ring.mapreduce.Mapper;
import org.ring.mapreduce.Reducer;
import org.ring.mapreduce.SimpleMapReduceService;
import org.ring.mapreduce.Worker;

import com.google.common.collect.Lists;

public class MapReduceTest {

	@Test
	public void test() {
		Mapper<List<String>, List<String>> mapper = new Mapper<List<String>, List<String>>() {

			@Override
			public Collection<List<String>> map(List<String> s) {
				return Lists.partition(s, 2);
			}

		};
		Reducer<Integer, List<Integer>> reducer = new Reducer<Integer, List<Integer>>() {

			@Override
			public List<Integer> reduce(Collection<Integer> source) {
				return Lists.newArrayList(source);
			}
		};
		Worker<List<String>, Integer> worker = new Worker<List<String>, Integer>() {

			@Override
			public Integer doJob(List<String> input) {
				int sum = 0;
				for (String string : input) {
					sum += Integer.parseInt(string);
				}
				return sum;
			}
		};
		MapReduce<List<String>, List<String>, Integer, List<Integer>> mapReduce = MapReduce.newInstance(mapper, reducer, worker);

		MapReduceService mapReduceService = new SimpleMapReduceService(4);

		List<Integer> result = mapReduceService.execute(mapReduce, Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8"));

		System.out.println(result);
	}

} 
