package com.example.No21Concurrency;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by BigFaceBear on 2018.02.24
 */

public class MyBlockingQueue implements BlockingQueue<Object> {
    private Object[] mQueue;
    private int mCount = 0;  //当前队列中元素的个数
    private Lock mLock = new ReentrantLock();
    private Condition mNotEmpty = mLock.newCondition();
    private Condition mNotFull = mLock.newCondition();

    public MyBlockingQueue(int length) {
        mQueue = new Object[length];
    }

    @Override
    public int size() {
        return mQueue == null ? 0 : mQueue.length;
    }

    @Override
    public boolean isEmpty() {
        return mCount == 0;
    }

    @Override
    public void put(Object o) throws InterruptedException {
        if (o == null) throw new NullPointerException("o can't be null");

        mLock.lock();
        try {
            while (mCount == mQueue.length) {
                mNotFull.await();
            }

            mQueue[mCount++] = o;
            mNotEmpty.signal();
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public Object take() throws InterruptedException {
        mLock.lock();
        try {
            while (mCount == 0) {
                mNotEmpty.await();
            }

            Object last = mQueue[mCount - 1];
            mQueue[mCount - 1] = null;
            mCount--;
            mNotFull.signal();
            return last;
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }


    @Override
    public boolean offer(Object o, long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public Object poll(long l, TimeUnit timeUnit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int drainTo(Collection<? super Object> collection) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super Object> collection, int i) {
        return 0;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public Iterator<Object> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}
