package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class MeaninglessJdbcConnectionString implements BadThing {

    public String getBadThingDescription() {
        return "Looks for a meaningless JDBC connection string for a valid (hsqldb) driver.";
    }

    @Override
    public String getBadThingId() {
        return "bogus-jdbc-connection-string";
    }

    public String getBadThingName() {
        return "Gibberish JDBC URL";
    }

    public String doBadThing() throws Exception {
        new DatabaseMislocator().testCantFindURL();
        return null;
    }
}
