package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class RecursionNightmare extends AbstractBadThing {
    public int selfCaller(int i) {
        int x = i + 1;
        return selfCaller(x);
    }

    @Override
    public String getBadThingId() {
        return "infinite-recursion-stack-oom";
    }

    public String getBadThingDescription() {
        return "A method calls itself over and over until it runs out of stack memory.";
    }

    public String getBadThingName() {
        return "Infinite Recursion (Stack Out Of Memory)";
    }

    public String doBadThing() throws Exception {
        new RecursionNightmare().selfCaller(0);
        return null;
    }
}
