package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class RunUnixCommand implements BadThing {

    private static String UnixCommand = "ls";

    public String getBadThingDescription() {
        return "Attempts to run the Unix command " + UnixCommand + ".";
    }

    @Override
    public String getBadThingId() {
        return "run-unix-command";
    }

    public String getBadThingName() {
        return "Unix Command Execution";
    }

    public String doBadThing() throws Exception {
        return RuntimeExec.execute(UnixCommand);
    }
}
