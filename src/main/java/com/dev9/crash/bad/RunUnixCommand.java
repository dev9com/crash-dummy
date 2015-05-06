package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class RunUnixCommand implements BadThing {

    private static String UnixCommand = "ls";

    public String badThingDescription() {
        return "Attempts to run the Unix command " + UnixCommand + ".";
    }

    @Override
    public String badThingId() {
        return "run-unix-command";
    }

    public String badThingName() {
        return "Unix Command Execution";
    }

    public String doBadThing() throws Exception {
        return RuntimeExec.execute(UnixCommand);
    }
}
