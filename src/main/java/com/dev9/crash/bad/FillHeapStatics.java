package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapStatics implements BadThing {

    public String badThingDescription() {
        return "Fills up the heap using a static method and static variable. "
                + " Does not clear at the end. WARNING: This will probably "
                + "lock your GUI and kill your server.";
    }

    @Override
    public String badThingId() {
        return "fill-up-the-heap-static-no-clear-die";
    }


    public String badThingName() {
        return "Fill Up The Heap Statically and Just Die Horribly";
    }

    public String doBadThing() throws Exception {
        try {
            MemoryMasher.staticMasher();
        } catch (OutOfMemoryError e) {
            throw e;
        }
        return null;
    }
}
