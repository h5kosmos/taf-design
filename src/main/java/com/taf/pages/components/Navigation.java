package com.taf.pages.components;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Component
public class Navigation {

    private By rootLocator = By.cssSelector("[class^=\"layout__sidebar-container\"]");
    private By launchesLink = By.cssSelector("[href$=\"/launches\"]");

    @Step("Open launches tab")
    public void openLaunches() {
        $(rootLocator).should(visible).$(launchesLink).click();
    }
}
