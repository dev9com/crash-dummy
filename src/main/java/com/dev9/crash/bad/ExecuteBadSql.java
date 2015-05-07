package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class ExecuteBadSql implements BadThing {

    public String getBadThingDescription() {
        String result = "Looks for a valid driver (" + DatabaseMislocator.driver
                + ") and connection string (" + DatabaseMislocator.url
                + ") using username/password (" + DatabaseMislocator.username + ","
                + DatabaseMislocator.password + ") and execute meaningless SQL. ";
        return result;
    }

    @Override
    public String getBadThingId() {
        return "bad-sql";
    }


    public String getBadThingName() {
        return "Execute Meaningless SQL (SELECT asdf)";
    }

    public String doBadThing() throws Exception {
        new DatabaseMislocator().nonsensicalSQL();
        return null;
    }
}
