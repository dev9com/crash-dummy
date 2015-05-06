package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class OpenJdbcConnUntilDie implements BadThing {

    public String badThingDescription() {
        String result = "Looks for a valid driver ("
                + DatabaseMislocator.driver
                + ") and connection string ("
                + DatabaseMislocator.url
                + ") using username/password ("
                + DatabaseMislocator.username
                + ","
                + DatabaseMislocator.password
                + ") and opens as many connections as possible. "
                + "WARNING: May lock up your GUI.";
        return result;
    }

    @Override
    public String badThingId() {
        return "open-as-many-jdbc-connections-as-possible";
    }


    public String badThingName() {
        return "Opens connections until death";
    }

    public String doBadThing() throws Exception {
        new DatabaseMislocator().openDBConnectionsTillCrash();
        return null;
    }
}
