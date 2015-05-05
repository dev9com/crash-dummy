package com.dev9.crash;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    /** Interestingly, does not cause failure on Java 8? */
    @Ignore
    @Test(expected = StackOverflowError.class)
    public void heapOnStack() {
        HeapOnStack sf = new HeapOnStack();
        sf.run();
    }

}
