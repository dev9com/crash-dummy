package com.dev9.crash;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

public class OpenConnections implements BadThing {

    static int bytesRead = 0;
    static String urlToRead = "http://localhost:8080/worst-app-ever/";

    public String badThingDescription() {
        return "Attempts to open a network connection to " + urlToRead + ". "
                + bytesRead + " lines read so far.";
    }

    public String badThingName() {
        return "Open Network Connection";
    }

    public String doBadThing() throws Exception {
        URL u;
        InputStream is = null;
        DataInputStream dis;

        u = new URL(urlToRead);

        is = u.openStream(); // throws an IOException

        dis = new DataInputStream(new BufferedInputStream(is));


        while (dis.read() != -1) {
            bytesRead++;
        }
        return null;
    }
}
