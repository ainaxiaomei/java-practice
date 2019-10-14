package com.practice.java;

import java.util.Arrays;

public class Bubbo {

	public static void main(String[] args) {

		int[] keys = new int[] { 8, 6, 3, 1, 4, 8, 2, 9, 7, 4 };

		int[] b = quickSort(keys,0,9);
		System.out.println(Arrays.toString(b));

	}

	public static int[] bubbo(int[] keys) {

		if (keys == null || keys.length <= 1) {

			return keys;
		}

		for (int i = 0; i < keys.length; i++) {

			for (int j = 0; j < keys.length - 1 - i; j++) {

				if (keys[j] > keys[j + 1]) {

					int temp = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = temp;

				}

			}

		}

		return keys;
	}

	public static int[] quickSort(int[] keys,int low,int high) {
		
		if (keys == null || keys.length <= 1) {

			return keys;
		}
		
		int k = keys[low];
		
		while (low < high) {
			
			
			while (low < high && keys[high] >= k) {
				high--;
			}
			
			keys[low] = keys[high];
			
			while (low < high && keys[low] <= k) {
				low ++;
			}
			
			keys[high] = keys[low];
		}
		
		
		keys[low]=k;

		return keys;

	}

}
