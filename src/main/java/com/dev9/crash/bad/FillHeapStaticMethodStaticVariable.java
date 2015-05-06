package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapStaticMethodStaticVariable implements BadThing {

    public String badThingDescription() {
        return "Fills up the heap using a static method and static variable.  Calls a clear method at the end (or else system would just die).";
    }

    @Override
    public String badThingId() {
        return "fill-up-the-heap-static-and-clear-oom";
    }

    public String badThingName() {
        return "Fill Up The Heap Statically and Clear";
    }

    public String doBadThing() throws Exception {
        try {
            MemoryMasher.staticMasher();
        } catch (OutOfMemoryError e) {
            MemoryMasher.clearStatic();
            throw e;
        }
        return null;
    }
}
