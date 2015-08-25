package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapStatics extends AbstractBadThing {

    public String getBadThingDescription() {
        return "Fills up the heap using a static method and static variable. "
                + " Does not clear at the end. WARNING: This will probably "
                + "lock your GUI and kill your server.";
    }

    @Override
    public String getBadThingId() {
        return "fill-up-the-heap-static-no-clear-die";
    }


    public String getBadThingName() {
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
