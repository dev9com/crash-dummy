package com.dev9.crash;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrashDummyApplication.class)
@WebAppConfiguration
public class CrashDummyApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test(expected = StackOverflowError.class)
    public void stackOverflow() {
        StackOverflow sf = new StackOverflow();
        sf.run();
    }

    @Test
    @Ignore("Technically this test will kill your build as written.")
    public void doAllTheBadThings() {
        BadThingRegistry btr = BadThingRegistry.getBadThingRegistry();

        List<String> skipList = new ArrayList<>();
        skipList.add("Fill Up The Heap");

        for (BadThing badThing : btr.badThings) {
            System.out.println("Starting: " + badThing.badThingName());

            try {
                if (!skipList.contains(badThing.badThingName()))
                    badThing.doBadThing();
                else
                    System.out.println("Skipping: " + badThing.badThingName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
