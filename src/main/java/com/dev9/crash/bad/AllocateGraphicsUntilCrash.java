package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Service
public class AllocateGraphicsUntilCrash implements BadThing {
    private static int count = 0;

    public String getBadThingDescription() {
        return "Attempts to create MANY windows - until crashing.  WARNING: May lock up your GUI.";
    }

    @Override
    public String getBadThingId() {
        return "create-windows-until-death";
    }

    public String getBadThingName() {
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
