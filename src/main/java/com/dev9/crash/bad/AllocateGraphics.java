package com.dev9.crash.bad;

import com.dev9.crash.AbstractBadThing;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Service
public class AllocateGraphics extends AbstractBadThing {
    public String getBadThingDescription() {
        return "Attempts to create a window.";
    }

    public String getBadThingName() {
        return "Create A Window";
    }

    public String doBadThing() throws Exception {
        SingleFrame f = new SingleFrame();
        f.setVisible(true);
        return "Created Window.";
    }

    @Override
    public String getBadThingId() {
        return "create-gui-window";
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
