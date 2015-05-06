package com.dev9.crash;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BadThingRegistry {

    static BadThingRegistry btr = null;
    public List<BadThing> badThings = new ArrayList<BadThing>();

    public static BadThingRegistry getBadThingRegistry() {
        if (btr == null) {
            btr = new BadThingRegistry();
            btr.init();
        }
        return btr;
    }

    public void init() {
        badThings.addAll(DatabaseMislocator.getBadThings());
        badThings.add(new DivideNothing());
        badThings.addAll(MemoryMasher.getBadThings());
        badThings.add(new NullPointerMaker());
        badThings.add(new RecursionNightmare());
        badThings.add(new DeadThreadGenerator());
        badThings.add(new ThreadingNightmare());
        badThings.add(new AllocateGraphics());
        badThings.add(new AllocateGraphicsUntilCrash());
        badThings.add(new OpenConnections());
        badThings.add(new OpenAsManyConnectionsAsPossible());
        badThings.add(new AllocateLargeSession());
        badThings.add(new SimplySystemExit());
        badThings.addAll(RuntimeExec.getBadThings());
    }

    public String execute(int number, HttpServletRequest request) throws Exception {
        BadThing bad = badThings.get(number);
        if (bad instanceof WebBadThing) {
            ((WebBadThing) bad).setHttpRequest(request);
        }

        String result = bad.doBadThing();
        if (result != null)
            result = result.replaceAll("\n", "<br />");

        return result;
    }

}
