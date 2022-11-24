package com.taf.pages.selenium;

import com.taf.pages.selenium.components.SLaunchItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.taf.utils.scopes.PagePrototypeScope.SCOPE_PAGE_PROTOTYPE;
import static java.lang.String.format;

@Component
@Scope(SCOPE_PAGE_PROTOTYPE)
public class SLaunchesPage {

    private final WebDriver driver;
    @Autowired
    ObjectProvider<SLaunchItem> launchItemProvider;

    private static final String LOCATOR_TEMPLATE = "[data-id='%d']";

    public SLaunchesPage(WebDriver driver) {
        this.driver = driver;
    }

    public SLaunchItem getLaunchItemByID(int launchId) {
        By elementLocator = By.cssSelector(format(LOCATOR_TEMPLATE, launchId));
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(elementLocator));
        actions.perform();
        return launchItemProvider.getObject(element);
    }
}
