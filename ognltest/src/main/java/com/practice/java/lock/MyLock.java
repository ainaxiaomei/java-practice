package com.practice.java.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock extends AbstractQueuedSynchronizer {

	@Override
	protected boolean tryAcquire(int arg) {
		if(compareAndSetState(0, 1)){
			setExclusiveOwnerThread(Thread.currentThread());
			return true;
		}
		return false;
	}

	@Override
	protected boolean tryRelease(int arg) {
		setState(arg);
		setExclusiveOwnerThread(null);
		return true;
	}
	
	public void lock(){acquire(1);}
	public void unlock(){acquire(0);}
	
}
