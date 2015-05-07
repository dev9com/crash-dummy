package com.dev9.crash;

import com.dev9.crash.bad.StackOverflow;
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
import java.util.Map;

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
    @Ignore("Technically this test will kill your build (or worse) as written.")
    public void doAllTheBadThings() {
        Map<String, BadThing> beansOfType = context.getBeansOfType(BadThing.class);

        List<String> skipList = new ArrayList<>();
        skipList.add("Fill Up The Heap");

        for (BadThing badThing : beansOfType.values()) {
            System.out.println("Starting: " + badThing.getBadThingName());

            try {
                if (!skipList.contains(badThing.getBadThingName()))
                    badThing.doBadThing();
                else
                    System.out.println("Skipping: " + badThing.getBadThingName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
