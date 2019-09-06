package com.practice.java.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LockTest extends AbstractQueuedSynchronizer {

	@Override
	protected boolean tryAcquire(int arg) {
		if (compareAndSetState(0, arg)) {
			setExclusiveOwnerThread(Thread.currentThread());
			return true;
		} else {
			return false;
		}
	}

	
	

}
