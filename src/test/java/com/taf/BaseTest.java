package com.taf;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
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

import static com.codeborne.selenide.Selenide.open;

@Slf4j
@WebAppConfiguration
@SpringBootTest(classes = TestApplication.class)
@Listeners({TextReport.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DriverManager driverManager;

    @Value("${test.property}")
    private String testProperty;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverRunner.setWebDriver(driverManager.initDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Success test")
    public void successTest() {
        open("https://www.duckduckgo.com");
        log.info("testProperty: " + testProperty);
    }
}
