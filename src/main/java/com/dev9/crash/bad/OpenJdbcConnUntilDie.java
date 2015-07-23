package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class OpenJdbcConnUntilDie extends AbstractBadThing {

    public String getBadThingDescription() {
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
    public String getBadThingId() {
        return "open-as-many-jdbc-connections-as-possible";
    }


    public String getBadThingName() {
        return "Opens connections until death";
    }

    public String doBadThing() throws Exception {
        new DatabaseMislocator().openDBConnectionsTillCrash();
        return null;
    }
}
