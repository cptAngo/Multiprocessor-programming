package mipt;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter {
    private volatile long value = 0;

    private  final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return value;
    }
}

