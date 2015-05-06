package com.dev9.crash;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AllocateGraphics implements BadThing {
    public String badThingDescription() {
        return "Attempts to create a window.";
    }

    public String badThingName() {
        return "Create A Window";
    }

    public String doBadThing() throws Exception {
        SingleFrame f = new SingleFrame();
        f.setVisible(true);
        return "Created Window.";
    }

    class SingleFrame extends Frame {
        public SingleFrame() {
            super("Single Frame - A Server Generated Window");
            setSize(100, 100);

            add(new Label("Odd.", Label.CENTER), BorderLayout.CENTER);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    dispose();
                }
            });
        }

    }
}
