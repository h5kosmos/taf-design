package com.taf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Value("${test.property}")
    private String testProperty;

    @Test(description = "Success test")
    public void successTest() {
        log.info("testProperty: " + testProperty);
    }
}
