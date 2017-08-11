package com.practice.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JAVAStream {

	public static void main(String[] args) {
		// Arrays.asList("1231", "3415", "3914").stream().map((item) -> {
		// return item.replace("1", "one");
		// }).forEach((item) -> {
		// System.out.println(item);
		// });

		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(Arrays.asList(i, i + 1, i + 2, i + 3, i + 4));
		}

		// System.out.println(list);

		// list.stream().flatMap((subList)->{
		// return subList.stream();
		// }).forEach((item)->{
		// System.out.println(item);
		// });

		System.out.println(list.stream().count());

		list.stream().flatMap((subList) -> {
			return subList.stream();
		}).distinct().forEach((item) -> {
			System.out.println(item);
		});
		
	}

}
