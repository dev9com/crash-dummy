package com.dev9.crash;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AllocateGraphicsUntilCrash implements BadThing {
    private static int count = 0;

    public String badThingDescription() {
        return "Attempts to create MANY windows - until crashing.  WARNING: May lock up your GUI.";
    }

    public String badThingName() {
        return "Create As Many Windows As Possible";
    }

    public String doBadThing() throws Exception {
        while (true) {
            CrashFrame f = new CrashFrame();
            f.setVisible(true);
        }
    }

    class CrashFrame extends Frame {
        public CrashFrame() {
            super("Crash Frame - A Server Generated Window");
            setSize(100, 20);

            add(new Label("" + count++, Label.CENTER), BorderLayout.CENTER);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    dispose();
                }
            });
        }

    }
}
