package com.dev9.crash;

import java.util.ArrayList;
import java.util.List;

public class MemoryMasher {
    public static List<char[]> staticList = new ArrayList<char[]>();
    public List<char[]> objectHeapList = new ArrayList<char[]>();

    static public List<BadThing> getBadThings() {
        BadThing bt1 = new BadThing() {

            public String badThingDescription() {
                return "Fills up the heap using an object with a local member variable that grows until OOM.";
            }

            public String badThingName() {
                return "Fill Up The Heap";
            }

            public String doBadThing() throws Exception {
                MemoryMasher mm = new MemoryMasher();
                mm.objectHeapMasher();
                return null;
            }
        };

        BadThing bt2 = new BadThing() {

            public String badThingDescription() {
                return "Fills up the heap using an object with a local method. This method allocates an object on the stack which grows until OOM.";
            }

            public String badThingName() {
                return "Fill Up The Heap On The Stack";
            }

            public String doBadThing() throws Exception {
                MemoryMasher mm = new MemoryMasher();
                mm.stackHeapMasher();
                return null;
            }
        };
        BadThing bt3 = new BadThing() {

            public String badThingDescription() {
                return "Fills up the heap using a static method and static variable.  Calls a clear method at the end (or else system would just die).";
            }

            public String badThingName() {
                return "Fill Up The Heap Statically and Clear";
            }

            public String doBadThing() throws Exception {
                try {
                    MemoryMasher.staticMasher();
                } catch (OutOfMemoryError e) {
                    MemoryMasher.clearStatic();
                    throw e;
                }
                return null;
            }
        };
        BadThing bt4 = new BadThing() {

            public String badThingDescription() {
                return "Fills up the heap using a static method and static variable. "
                        + " Does not clear at the end. WARNING: This will probably "
                        + "lock your GUI and kill your server.";
            }

            public String badThingName() {
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
        };

        List<BadThing> result = new ArrayList<BadThing>();
        result.add(bt1);
        result.add(bt2);
        result.add(bt3);
        result.add(bt4);

        return result;
    }

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
