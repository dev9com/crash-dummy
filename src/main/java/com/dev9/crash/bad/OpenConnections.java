package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

@Service
public class OpenConnections implements BadThing {

    static int bytesRead = 0;
    static String urlToRead = "http://localhost:8080/";

    public String badThingDescription() {
        return "Attempts to open a network connection to " + urlToRead + ". "
                + bytesRead + " lines read so far.";
    }

    @Override
    public String badThingId() {
        return "open-network-connection";
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
