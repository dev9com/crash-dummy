package com.dev9.crash;

public class SimplySystemExit implements BadThing {
    public String badThingDescription() {
        return "Try to shut down the server with System.exit().  This completely skips the normal server termination and can leave the server in an inconsistent state.";
    }

    @Override
    public String badThingId() {
        return "system-exit";
    }

    public String badThingName() {
        return "Simply System Exit";
    }

    public String doBadThing() throws Exception {
        System.exit(-1);
        return null;
    }
}
