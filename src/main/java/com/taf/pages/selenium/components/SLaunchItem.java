package com.taf.pages.selenium.components;

import com.epam.reportportal.annotations.Step;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.taf.utils.scopes.SeleniumPrototypeScope.SCOPE_SELENIUM_ELEMENT_PROTOTYPE;

@Component
@Scope(SCOPE_SELENIUM_ELEMENT_PROTOTYPE)
public class SLaunchItem {

    @Getter
    private final WebElement root;
    private final By totalCount = By.cssSelector("[statuses=\"PASSED,FAILED,SKIPPED,INTERRUPTED\"]");
    private final By passedCount = By.cssSelector("[statuses=\"PASSED\"]");
    private final By failedCount = By.cssSelector("[statuses=\"FAILED,INTERRUPTED\"]");
    private final By skippedCount = By.cssSelector("[statuses=\"SKIPPED\"]");

    private final By donutValue = By.cssSelector("[class^=\"donutChart__total\"]");
    private final By productBugCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__pb-col\"]"), donutValue);
    private final By autoBugCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__ab-col\"]"), donutValue);
    private final By systemIssueCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__si-col\"]"), donutValue);
    private final By investigateCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__ti-col\"]"), donutValue);

    public SLaunchItem(WebElement root) {
        this.root = root;
    }

    @Step("Check total count: {count}")
    public SLaunchItem checkTotalCount(int count) {
        checkCountValue(getRoot().findElement(totalCount), count);
        return this;
    }

    @Step("Check passed count: {count}")
    public SLaunchItem checkPassedCount(int count) {
        checkCountValue(getRoot().findElement(passedCount), count);
        return this;
    }

    @Step("Check failed count: {count}")
    public SLaunchItem checkFailedCount(int count) {
        checkCountValue(getRoot().findElement(failedCount), count);
        return this;
    }

    @Step("Check skipped count: {count}")
    public SLaunchItem checkSkippedCount(int count) {
        checkCountValue(getRoot().findElement(skippedCount), count);
        return this;
    }

    @Step("Check product bug count: {count}")
    public SLaunchItem checkProductBugCount(int count) {
        checkCountValue(getRoot().findElement(productBugCount), count);
        return this;
    }

    @Step("Check auto bug count: {count}")
    public SLaunchItem checkAutoBugCount(int count) {
        checkCountValue(getRoot().findElement(autoBugCount), count);
        return this;
    }

    @Step("Check system issue count: {count}")
    public SLaunchItem checkSystemIssueCount(int count) {
        checkCountValue(getRoot().findElement(systemIssueCount), count);
        return this;
    }

    @Step("Check investigate count: {count}")
    public SLaunchItem checkInvestigateCount(int count) {
        checkCountValue(getRoot().findElement(investigateCount), count);
        return this;
    }

    private void checkCountValue(WebElement element, int value) {
        Assertions.assertEquals(Integer.valueOf(element.getText()), value);
    }
}