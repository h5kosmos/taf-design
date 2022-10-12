package com.taf.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.codeborne.selenide.Configuration.*;

@Service
@Slf4j
public class DriverManager {

    @Value("${selenide.browser}")
    private String browser;

    @Value("${fail.test.timeout}")
    private Long failTestTimeout;

    @Value("${page.load.timeout}")
    private Long loadTimeout;

    @Value("${page.load.strategy}")
    private String loadStrategy;

    public WebDriver initDriver() {
        log.info(browser);
        if ("chrome".equals(browser)) {
            return initChromeDriver();
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            return new SafariDriver();
        }
    }

    private WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        timeout = failTestTimeout;
        pageLoadTimeout = loadTimeout;
        pageLoadStrategy = loadStrategy;

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
}
