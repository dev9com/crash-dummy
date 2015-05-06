package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class RecursionNightmare implements BadThing {
    public int selfCaller(int i) {
        int x = i + 1;
        return selfCaller(x);
    }

    @Override
    public String badThingId() {
        return "infinite-recursion-stack-oom";
    }

    public String badThingDescription() {
        return "A method calls itself over and over until it runs out of stack memory.";
    }

    public String badThingName() {
        return "Infinite Recursion (Stack Out Of Memory)";
    }

    public String doBadThing() throws Exception {
        new RecursionNightmare().selfCaller(0);
        return null;
    }
}
