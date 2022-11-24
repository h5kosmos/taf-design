package com.taf.pages.selenium.components;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.taf.utils.scopes.PagePrototypeScope.SCOPE_PAGE_PROTOTYPE;

@Component
@Scope(SCOPE_PAGE_PROTOTYPE)
public class SNavigation {

    private final By rootLocator = By.cssSelector("[class^=\"layout__sidebar-container\"]");
    private final By launchesLink = By.cssSelector("[href$=\"/launches\"]");
    private final WebDriver driver;

    public SNavigation(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open launches tab")
    public void openLaunches() {
        WebElement navigation = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(rootLocator));
        navigation.findElement(launchesLink).click();
    }
}
