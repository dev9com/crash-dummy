package com.dev9.crash;

public class RecursionNightmare implements BadThing {
    public int selfCaller(int i) {
        int x = i + 1;
        return selfCaller(x);
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
