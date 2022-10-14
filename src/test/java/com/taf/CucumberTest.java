package com.taf;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest(classes = TestApplication.class)
@CucumberOptions(
        features = "src/test/resources/Feature",
        glue = {"com.taf.steps", "com.taf.configurations"}
)
public class CucumberTest {
}
