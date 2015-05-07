package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class BogusJdbcDriver implements BadThing {

    public String getBadThingDescription() {
        return "Looks for a made-up driver.";
    }

    public String getBadThingName() {
        return "Mythical JDBC Driver";
    }

    @Override
    public String getBadThingId() {
        return "bogus-jdbc-driver";
    }

    public String doBadThing() throws Exception {
        new DatabaseMislocator().testCantFindDriver();
        return null;
    }
}
