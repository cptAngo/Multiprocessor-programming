package mipt;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static mipt.CounterTest.NUMBER_OF_THREADS;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
public class CounterBenchmarkTest {

    @State(Scope.Benchmark)
    public static class CounterState {
        final Counter concurentCounter = new ConcurentCounter();
        final Counter lockCounter = new LockCounter();
        final Counter mutexCounter = new MutexCounter();
        final Counter magicCounter = new MagicCounter();
    }

    @Benchmark
    @Group("ConcurentCounter")
    @GroupThreads(16)
    public void testConcurentCounterIncrement(final CounterState state) {
        state.concurentCounter.increment();
    }

    @Benchmark
    @Group("ConcurentCounter")
    @GroupThreads(16)
    public long testConcurentCounterGetValue(final CounterState state) {
        return state.concurentCounter.getValue();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(16)
    public void testLockCounterIncrement(final CounterState state) {
        state.lockCounter.increment();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(16)
    public long testLockCounterGetValue(final CounterState state) {
        return state.lockCounter.getValue();
    }

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(16)
    public void testMutexCounterIncrement(final CounterState state) {
        state.mutexCounter.increment();
    }

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(16)
    public long testMutexCounterGetValue(final CounterState state) {
        return state.mutexCounter.getValue();

    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(16)
    public void testMagicCounterIncrement(final CounterState state) {
        state.magicCounter.increment();
    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(16)
    public long testMagicCounterGetValue(final CounterState state) {
        return state.magicCounter.getValue();

    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(CounterBenchmarkTest.class.getName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
