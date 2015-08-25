package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class DivideNothing extends AbstractBadThing {
    public float divideByZero() {
        int x = 1;
        int y = 0;
        float z = x / y;
        return z;
    }

    @Override
    public String getBadThingId() {
        return "divide-by-zero";
    }


    public String getBadThingDescription() {
        return "Attempts to divide the number one by zero.";
    }

    public String getBadThingName() {
        return "Divide By Zero";
    }

    public String doBadThing() throws Exception {
        divideByZero();
        return null;
    }
}
