package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class DivideNothing implements BadThing {
    public float divideByZero() {
        int x = 1;
        int y = 0;
        float z = x / y;
        return z;
    }

    @Override
    public String badThingId() {
        return "divide-by-zero";
    }


    public String badThingDescription() {
        return "Attempts to divide the number one by zero.";
    }

    public String badThingName() {
        return "Divide By Zero";
    }

    public String doBadThing() throws Exception {
        divideByZero();
        return null;
    }
}
