package com.dev9.crash.bad;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RuntimeExec {

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

}
