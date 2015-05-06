package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapLocalMethodOnStack implements BadThing {

    public String badThingDescription() {
        return "Fills up the heap using an object with a local method. This method allocates an object on the stack which grows until OOM.";
    }

    public String badThingName() {
        return "Fill Up The Heap On The Stack";
    }

    @Override
    public String badThingId() {
        return "fill-up-the-heap-object-local-method-stack-oom";
    }

    public String doBadThing() throws Exception {
        MemoryMasher mm = new MemoryMasher();
        mm.stackHeapMasher();
        return null;
    }
}
