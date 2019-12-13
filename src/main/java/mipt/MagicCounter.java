package mipt;

import java.util.HashMap;

public class MagicCounter implements Counter {

    public volatile HashMap<Long, Integer> threadsCount = new HashMap<>();

    @Override
    public void increment() {
        Long threadId = Thread.currentThread().getId();
        if (threadsCount.containsKey(threadId)) {
            threadsCount.put(threadId, threadsCount.get(threadId) + 1);
        }
        else {
            threadsCount.put(threadId, 1);
        }
    }

    @Override
    public long getValue() {
        int sum = threadsCount.values().stream().mapToInt(Integer::intValue).sum();
        return sum;
    }
}