package com.dev9.crash;

import java.util.HashSet;
import java.util.Set;

public class HeapOnStack implements Runnable {

    private int counter = 1;

    public int addThis(int x) {

        Set<Integer[]> mySet = new HashSet<>();
        for(int i = 0; i < counter * 1024; i++) {
            mySet.add(new Integer[1024]);
        }

        counter = counter + x;
        addThis(1);
        return counter;
    }

    @Override
    public void run() {
        addThis(1);
    }
}
