package com.taf;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.taf.core.DriverManager;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


import static com.codeborne.selenide.Selenide.open;

@Slf4j
@WebAppConfiguration
@SpringBootTest(classes = TestApplication.class)
//@Listeners({TextReport.class})
public class BaseTest {
    @Autowired
    private DriverManager driverManager;

    @Value("${test.property}")
    private String testProperty;

    @BeforeEach
    public void setUp() {
        WebDriverRunner.setWebDriver(driverManager.initDriver());
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Success test")
    public void successTest() {
        open("https://www.duckduckgo.com");
        log.info("testProperty: " + testProperty);
    }
}
