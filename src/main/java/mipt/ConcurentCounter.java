package mipt;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurentCounter implements Counter {
    private final AtomicLong cnt = new AtomicLong();

    @Override
    public void increment() {
        cnt.incrementAndGet();
    }

    @Override
    public long getValue() {
        return cnt.get();
    }
}

