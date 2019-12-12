package mipt;

public class MagicCounter implements Counter{

    private volatile long value = 0;
    private volatile boolean ifLocked = false;

    @Override
    public void increment() {
        while(ifLocked) {
            Thread.yield();
        }
        ifLocked = true;
        value++;
        ifLocked = false;
    }

    @Override
    public long getValue() {
        return value;
    }
}
