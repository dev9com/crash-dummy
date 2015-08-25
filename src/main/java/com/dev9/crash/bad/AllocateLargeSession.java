package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AllocateLargeSession extends AbstractBadThing {

    private static long attributeCount = 0;

    public String getBadThingDescription() {
        return "Places a very large lump of data in the user session.  Currently " + attributeCount
                + " lumps have been associated with sessions.";
    }

    @Override
    public String getBadThingId() {
        return "put-data-in-user-session";
    }

    public String getBadThingName() {
        return "Allocate Lots of Session Data";
    }

    public String doBadThing() throws Exception {
        // The char data type is a single 16-bit Unicode character
        char[] data = new char[10000000];
        request.setAttribute("large" + attributeCount++, data);
        return "Added session data " + (attributeCount - 1);
    }
}
