package org.thiefspin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class Workers {

    private final ForkJoinPool forkJoinPool;

    private final List<Future<?>> jobs = new ArrayList<>();

    Workers(int numWorkers) {
        this.forkJoinPool = new ForkJoinPool(numWorkers);
    }

    void addWork(RecursiveAction job) {
        jobs.add(forkJoinPool.submit(job));
    }

    Future<?> whenDone(Runnable andThen) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            while (!containsCompletedJob()) {

            }
            andThen.run();
        });
    }

    private boolean containsCompletedJob() {
        return jobs.stream().anyMatch(Future::isDone);
    }
}
