package com.dev9.crash;

import javax.servlet.http.HttpServletRequest;

public class AllocateLargeSession implements WebBadThing {

    private static long attributeCount = 0;
    HttpServletRequest request;

    public String badThingDescription() {
        return "Places a very large lump of data in the user session.  Currently " + attributeCount
                + " lumps have been associated with sessions.";
    }

    public String badThingName() {
        return "Allocate Lots of Session Data";
    }

    public String doBadThing() throws Exception {
        // The char data type is a single 16-bit Unicode character
        char[] data = new char[10000000];
        request.setAttribute("large" + attributeCount++, data);
        return "Added session data " + (attributeCount - 1);
    }

    public void setHttpRequest(HttpServletRequest request) {
        this.request = request;
    }
}
