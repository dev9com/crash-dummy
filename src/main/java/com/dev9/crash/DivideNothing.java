package com.dev9.crash;

public class DivideNothing implements BadThing {
    public float divideByZero() {
        int x = 1;
        int y = 0;
        float z = x / y;
        return z;
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
