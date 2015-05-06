package com.dev9.crash;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RuntimeExec {
    static String UnixCommand = "ls";
    static String WindowsCommand = "cmd /c dir";

    static public String execute(String command) throws Exception {
        StringBuffer out = new StringBuffer();

        Process process = Runtime.getRuntime().exec(command);
        InputStream output = process.getInputStream();
        process.waitFor();
        int character = 0;
        int count = 0;
        while (character != -1) {
            character = output.read();
            if (character != -1)
                out.append(Character.toString((char) character));
            count++;
            if (count == 255)
                character = -1;
        }

        return out.toString();
    }

    static public List<BadThing> getBadThings() {
        BadThing bt1 = new BadThing() {

            public String badThingDescription() {
                return "Attempts to run the Unix command " + UnixCommand + ".";
            }

            public String badThingName() {
                return "Unix Command Execution";
            }

            public String doBadThing() throws Exception {
                return execute(UnixCommand);
            }
        };

        BadThing bt2 = new BadThing() {

            public String badThingDescription() {
                return "Attempts to run the Windows command " + WindowsCommand + ".";
            }

            public String badThingName() {
                return "Windows Command Execution";
            }

            public String doBadThing() throws Exception {
                return execute(WindowsCommand);
            }
        };

        List<BadThing> result = new ArrayList<BadThing>();
        result.add(bt1);
        result.add(bt2);

        return result;
    }
}
