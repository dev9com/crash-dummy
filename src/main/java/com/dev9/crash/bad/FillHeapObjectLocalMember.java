package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class FillHeapObjectLocalMember implements BadThing {

    public String getBadThingDescription() {
        return "Fills up the heap using an object with a local member variable that grows until OOM.";
    }

    @Override
    public String getBadThingId() {
        return "fill-up-the-heap-object-local-member-oom";
    }

    public String getBadThingName() {
        return "Fill Up The Heap";
    }

    public String doBadThing() throws Exception {
        MemoryMasher mm = new MemoryMasher();
        mm.objectHeapMasher();
        return null;
    }
}
