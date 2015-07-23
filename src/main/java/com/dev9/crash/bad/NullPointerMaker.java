package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class NullPointerMaker extends AbstractBadThing {
    public void generateNullPointer() {
        new Person().getName().toString();
    }

    public String getBadThingDescription() {
        return "Generates a typical null pointer error.";
    }

    @Override
    public String getBadThingId() {
        return "generate-null-pointer-exception";
    }

    public String getBadThingName() {
        return "Generate NullPointerException";
    }

    public String doBadThing() throws Exception {
        new NullPointerMaker().generateNullPointer();
        return null;
    }

    class Person {
        String getName() {
            return null;
        }
    }
}
