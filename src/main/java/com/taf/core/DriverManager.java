package com.taf.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DriverManager {

    @Value("${selenide.browser}")
    private String browser;


    public WebDriver initDriver() {
        log.info(browser);
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            return new SafariDriver();
        }
    }
}
