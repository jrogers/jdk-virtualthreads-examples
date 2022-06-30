package dev.jrogers.loom;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ManyVirtualThreads {

  public static void main(String[] args) throws InterruptedException {
    var counter = new AtomicInteger();
    var barrier = new CyclicBarrier(100_000);
    var startTime = System.nanoTime();
    var threads = IntStream.range(0, 100_000)
        .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
          try {
            barrier.await();
          } catch (InterruptedException | BrokenBarrierException e) {
            throw new AssertionError(e);
          }
          counter.incrementAndGet();
        }))
        .toList();
    for (var thread : threads) {
      thread.start();
    }

    for (var thread : threads) {
      thread.join();
    }

    var elapsedTime = System.nanoTime() - startTime;
    System.out.println("Number of threads started: " + counter);
    System.out.println("Duration: " + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " seconds");
  }
}
