package com.dev9.crash;

public class ThreadingNightmare implements BadThing {

    static ThreadGroup tooManyGroup = new ThreadGroup(
            "Too Many Threads Generator Group");

    static int numberOfThreads = 10000;
    static int workRuns = 10;
    static int workDuration = 10;
    static int workSleeper = 10;

    static int maxThreadsActuallyRun = 0;

    public void tooManyThreads() throws Exception {
        for (int i = 0; i < numberOfThreads; i++) {
            Thread t = new Thread(tooManyGroup, new Runnable() {
                WorkerBee worker = new WorkerBee();

                public void run() {
                    worker.doWork();
                }
            }, "Deadlock Generator #" + i);

            t.setDaemon(false);

            t.start();
        }

        while (tooManyGroup.activeCount() > 0) {
            try {
                Thread.sleep(100);
                System.out.println("Max Threads: " + maxThreadsActuallyRun);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ThreadGroup getTooManyGroup() {
        return tooManyGroup;
    }

    @Override
    public String badThingId() {
        return "generate-n-threads";
    }


    public String badThingDescription() {
        try {
            String result = "Generate and run "
                    + numberOfThreads
                    + " threads, with "
                    + workRuns
                    + " runs of work executing "
                    + workDuration
                    + " sleeps with "
                    + workSleeper
                    + "ms sleep time.  This may "
                    + "not actually kill your JVM, but will lead to extensive lag.";

            if (maxThreadsActuallyRun > 0)
                result = result + " Last time this was done a max of "
                        + maxThreadsActuallyRun
                        + " threads were running at the same time.";

            return result;
        } catch (Exception e) {
            return "Generate and run many threads (Can't Load Properties)";
        }
    }

    public String badThingName() {
        try {
            return "Generate and Run Many Threads";
        } catch (Exception e) {
            return "Generate and Run Many Threads (Can't Load Properties)";
        }
    }

    public String doBadThing() throws Exception {
        this.tooManyThreads();
        return null;
    }

    static class WorkerBee {
        public void doWork() {
            long total = 0;
            for (int i = 0; i < workRuns; i++) {
                try {
                    Thread.sleep(workSleeper);
                } catch (InterruptedException e) {
                    System.out.println("dead!");
                    return;
                }
                total = total + System.currentTimeMillis();
                for (int x = 0; x < workDuration; x++) {
                    total = x + System.currentTimeMillis();
                }
            }

            int max = tooManyGroup.activeCount();
            if (max > maxThreadsActuallyRun) {
                maxThreadsActuallyRun = max;
            }
        }
    }
}
