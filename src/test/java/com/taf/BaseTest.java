package com.taf;

import com.codeborne.selenide.testng.TextReport;
import com.taf.core.DriverManager;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.*;

@Slf4j
@WebAppConfiguration
@SpringBootTest(classes = TestApplication.class)
@Listeners({TextReport.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    private WebDriver driver;

    @Autowired
    private DriverManager driverManager;

    @Value("${test.property}")
    private String testProperty;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = driverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Success test")
    public void successTest() {
        driver.get("https://www.duckduckgo.com");
        log.info("testProperty: " + testProperty);
    }
}
