package com.practice.java.concurrent.unsafe;

import sun.misc.Unsafe;


/**
 * 
    * @ClassName: UnSafeTest  
    * @Description: 使用unsafe直接分配内存，实现动态数组
    * @author win  
    * @date 2019年2月25日  
    *
 */
public class UnSafeArrayTest {
	
	public static void main(String[] args) {
		
		Unsafe unsafe = Unsafe.getUnsafe();
		
		long p = unsafe.allocateMemory(4);
		unsafe.setMemory(p, 4, (byte) 0);
		
		unsafe.putInt(p, 100);
		unsafe.putInt(p + 4, 101);
		
		System.out.println(p);
		System.out.println(unsafe.getInt(p));
		System.out.println(unsafe.getInt(p + 4));
		
		unsafe.freeMemory(p);
		
		DynamicArray darray = new DynamicArray(100);
		
		for (int i = 0 ;i < 100 ; i ++) {
			darray.put(i, i + 10000);
		}
		
		for (int i = 0 ;i < 100 ; i ++) {
			System.out.println(darray.get(i));
		}
		
	}

}

class DynamicArray {
	
	private int size;
	
	private final int SIZE_INT = 4;
	
	private Unsafe unsafe;
	
	private long address;

	public DynamicArray(int size) {
		
		this.size = size;
		this.unsafe = Unsafe.getUnsafe();
		address = unsafe.allocateMemory(size * SIZE_INT);
		unsafe.setMemory(address, size, (byte) 0);
	}
	
	public void put(int offset,int value) {
		
		unsafe.putInt(address + offset * SIZE_INT, value);
		
	}
	
	public int get(int offset) {
		
		return unsafe.getInt(address + offset * SIZE_INT);
		
	}
	
	
	
	
}
