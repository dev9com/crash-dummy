package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class DeadThreadGenerator extends AbstractBadThing {

    static int count = 0;
    Friend alpha = new Friend("Alpha");
    Friend omega = new Friend("Omega");
    ThreadGroup tg = new ThreadGroup("Deadlock Generator Group");

    public void deadlockThreadGenerator() {
        long time = System.currentTimeMillis();

        new Thread(tg, new Runnable() {
            public void run() {
                alpha.bow(omega);
            }
        }, "Deadlock Generator Alpha " + time).start();
        new Thread(tg, new Runnable() {
            public void run() {
                omega.bow(alpha);
            }
        }, "Deadlock Generator Omega " + time).start();

        count++;
    }

    @Override
    public String getBadThingId() {
        return "generate-deadlock-threads";
    }


    public String getBadThingDescription() {
        return "Each time this bad thing is run, it generates a new pair of mutually deadlocked threads. This has been done "
                + count + " times on this JVM already.";
    }

    public String getBadThingName() {
        return "Deadlocked Thread Generator";
    }

    public String doBadThing() throws Exception {
        new DeadThreadGenerator().deadlockThreadGenerator();
        return null;
    }

    /**
     * This example of a deadlock taken from
     * <p/>
     * http://java.sun.com/docs/books/tutorial/essential/concurrency/deadlock.html
     */
    static class Friend {
        private final String name;
        String currentBower = null;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bower.bowBack(this);
            currentBower = this.getName();
        }

        public synchronized void bowBack(Friend bower) {
            try {
                this.wait(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentBower = bower.getName();
        }
    }

}
