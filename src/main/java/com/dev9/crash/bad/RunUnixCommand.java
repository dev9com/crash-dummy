package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
public class RunUnixCommand extends AbstractBadThing {

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
