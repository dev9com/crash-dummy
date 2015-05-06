package com.dev9.crash;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

public class OpenAsManyConnectionsAsPossible implements BadThing {

    static ThreadGroup tooManyGroup = new ThreadGroup(
            "Open As Many Connections As Possible Group");
    public String urlToRead = "http://localhost:8080/worst-app-ever/";
    int bytesRead = 0;

    public String badThingDescription() {
        return "Attempts to open as many network connections as possible to "
                + urlToRead + ". " + bytesRead
                + " lines read so far. WARNING: May lock up your GUI.";
    }

    public String badThingName() {
        return "Open As Many Network Connections As Possible";
    }

    public String doBadThing() throws Exception {
        int i = 0;
        while (true) {
            Thread t = new Thread(tooManyGroup, new Runnable() {
                NetworkWorker worker = new NetworkWorker();

                public void run() {
                    try {
                        worker.doWork();
                    } catch (Exception e) {
                        // Ignore exceptions. >:)
                    }
                }
            }, "Network Connection Generator #" + i++);

            t.setDaemon(false);

            t.start();
        }

    }

    class NetworkWorker {
        public void doWork() throws Exception {
            while (true) {
                URL u;
                InputStream is = null;
                DataInputStream dis;

                u = new URL(urlToRead);

                is = u.openStream(); // throws an IOException

                dis = new DataInputStream(new BufferedInputStream(is));

                while (dis.read() != -1) {
                    bytesRead++;
                }
            }
        }
    }
}
