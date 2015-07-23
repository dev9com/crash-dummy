package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

@Service
class RunWindowsCommand extends AbstractBadThing {

    static String WindowsCommand = "cmd /c dir";

    public String getBadThingDescription() {
        return "Attempts to run the Windows command " + WindowsCommand + ".";
    }

    @Override
    public String getBadThingId() {
        return "run-windows-command";
    }

    public String getBadThingName() {
        return "Windows Command Execution";
    }

    public String doBadThing() throws Exception {
        return RuntimeExec.execute(WindowsCommand);
    }
}
