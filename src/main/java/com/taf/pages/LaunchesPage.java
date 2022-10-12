package com.taf.pages;

import com.taf.pages.components.LaunchItem;
import org.openqa.selenium.By;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

@Component
public class LaunchesPage {

    @Autowired
    ObjectProvider<LaunchItem> launchItemProvider;

    private static final String LOCATOR_TEMPLATE = "[data-id='%d']";

    public LaunchItem getLaunchItemByID(int launchId) {
        By elementLocator = By.cssSelector(format(LOCATOR_TEMPLATE, launchId));
        $(elementLocator).should(exist).scrollIntoView(true);
        return launchItemProvider.getObject($(elementLocator));
    }
}
