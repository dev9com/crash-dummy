package com.dev9.crash.bad;

public class StackOverflow implements Runnable {

    private int counter = 0;

    public int addThis(int x) {
        counter = counter + x;
        addThis(1);
        return counter;
    }

    @Override
    public void run() {
        addThis(1);
    }
}
