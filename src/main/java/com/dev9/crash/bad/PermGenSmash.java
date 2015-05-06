package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * http://cojen.sourceforge.net/example.html
 *
 * @author wiverson
 */
@Service
public class PermGenSmash {
    public static List<char[]> staticList = new ArrayList<char[]>();
    public List<char[]> objectHeapList = new ArrayList<char[]>();

    static public List<BadThing> getBadThings() {
        BadThing bt1 = new FillHeapObjectLocalMember();

        BadThing bt2 = new FillHeapLocalMethodOnStack();
        BadThing bt3 = new FillHeapStaticMethodStaticVariable();
        BadThing bt4 = new FillHeapStatics();

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
