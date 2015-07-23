package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapStaticMethodStaticVariable extends AbstractBadThing {

    public String getBadThingDescription() {
        return "Fills up the heap using a static method and static variable.  Calls a clear method at the end (or else system would just die).";
    }

    @Override
    public String getBadThingId() {
        return "fill-up-the-heap-static-and-clear-oom";
    }

    public String getBadThingName() {
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
