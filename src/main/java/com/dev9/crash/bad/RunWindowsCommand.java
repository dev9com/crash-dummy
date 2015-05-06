package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
class RunWindowsCommand implements BadThing {

    static String WindowsCommand = "cmd /c dir";

    public String badThingDescription() {
        return "Attempts to run the Windows command " + WindowsCommand + ".";
    }

    @Override
    public String badThingId() {
        return "run-windows-command";
    }

    public String badThingName() {
        return "Windows Command Execution";
    }

    public String doBadThing() throws Exception {
        return RuntimeExec.execute(WindowsCommand);
    }
}
