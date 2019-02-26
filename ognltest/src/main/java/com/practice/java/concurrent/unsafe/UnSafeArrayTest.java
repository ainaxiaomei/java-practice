package com.practice.java.concurrent.unsafe;

import sun.misc.Unsafe;

/**
 * 
 * @ClassName: UnSafeTest
 * @Description: 使用unsafe直接分配内存，实现动态数组
 * 
 *               1.在windows下reallocateMemory会重新分配地址空间，在linux下旧的地址和新的地址相同
 * @author win
 * @date 2019年2月25日
 *
 */
public class UnSafeArrayTest {

	public static void main(String[] args) {

		long address = Unsafe.getUnsafe().allocateMemory(4 * 2);
		Unsafe.getUnsafe().putInt(address, 100);

		Unsafe.getUnsafe().putInt(address + 4, 101);
		System.out.println("--- address " + address + " value1 " + Unsafe.getUnsafe().getInt(address));
		System.out.println("--- address " + address + " value2 " + Unsafe.getUnsafe().getInt(address + 4));

		long newAddress = Unsafe.getUnsafe().reallocateMemory(address, 4 * 1);
		System.out.println("--- newAddress " + newAddress + " newvalue1 " + Unsafe.getUnsafe().getInt(newAddress));
		System.out.println("--- newAddress " + newAddress + " newvalue2 " + Unsafe.getUnsafe().getInt(newAddress + 4));

		System.out.println("--- oldAddress " + address + " oldValue1 " + Unsafe.getUnsafe().getInt(address));
		System.out.println("--- oldAddress " + address + " oldValue2 " + Unsafe.getUnsafe().getInt(address + 4));

		DynamicArray darray = new DynamicArray(10);

		for (int i = 0; i < 15; i++) {
			darray.put(i, i + 10000);
		}

		for (int i = 0; i < 15; i++) {
			System.out.println(darray.get(i));
		}

	}

}

class DynamicArray {

	private int size;

	private final int SIZE_INT = 4;

	private Unsafe unsafe;

	private long address;

	private int capacity;

	private int total;

	public DynamicArray(int size) {

		this.size = size;
		this.unsafe = Unsafe.getUnsafe();
		this.capacity = 0;
		this.total = size * SIZE_INT;
		address = unsafe.allocateMemory(size * SIZE_INT);
		unsafe.setMemory(address, size, (byte) 0);
	}

	public void put(int offset, int value) {
		if (offset * SIZE_INT >= total) {

			long newAddress = unsafe.reallocateMemory(address, size * SIZE_INT * 2);

			System.out.println(" --- double size , offset is " + offset + " new address " + newAddress + " old address " + address);
			total = total + size * SIZE_INT * 2;
			this.address = newAddress;
		}

		unsafe.putInt(address + offset * SIZE_INT, value);
		this.capacity += 1;

	}

	public int get(int offset) {

		return unsafe.getInt(address + offset * SIZE_INT);

	}

}
