package com.dev9.crash.bad;


import java.util.ArrayList;
import java.util.List;

public class MemoryMasher {
    public static List<char[]> staticList = new ArrayList<char[]>();
    public List<char[]> objectHeapList = new ArrayList<char[]>();

    public static void filler(List<char[]> listToFill) {
        while (true) {
            listToFill.add(Long.toString(System.currentTimeMillis())
                    .toCharArray());
        }

    }

    public static void clearStatic() {
        staticList.clear();
    }

    public static void staticMasher() {
        filler(staticList);
    }

    public void objectHeapMasher() {
        filler(objectHeapList);
    }

    public void stackHeapMasher() {
        List<char[]> stackHeapList = new ArrayList<char[]>();
        filler(stackHeapList);

    }

}
