package com.taf.steps.configurations;

import com.taf.TestApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = com.taf.CucumberTest.class)
@ContextConfiguration(classes = TestApplication.class)
public class CucumberSpringConfiguration {
    // executeGet implementation
}
