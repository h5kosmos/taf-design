package com.taf.pages.selenium.components;

import com.epam.reportportal.annotations.Step;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        checkCountValue(totalCount, count);
        return this;
    }

    @Step("Check passed count: {count}")
    public SLaunchItem checkPassedCount(int count) {
        checkCountValue(passedCount, count);
        return this;
    }

    @Step("Check failed count: {count}")
    public SLaunchItem checkFailedCount(int count) {
        checkCountValue(failedCount, count);
        return this;
    }

    @Step("Check skipped count: {count}")
    public SLaunchItem checkSkippedCount(int count) {
        checkCountValue(skippedCount, count);
        return this;
    }

    @Step("Check product bug count: {count}")
    public SLaunchItem checkProductBugCount(int count) {
        checkCountValue(productBugCount, count);
        return this;
    }

    @Step("Check auto bug count: {count}")
    public SLaunchItem checkAutoBugCount(int count) {
        checkCountValue(autoBugCount, count);
        return this;
    }

    @Step("Check system issue count: {count}")
    public SLaunchItem checkSystemIssueCount(int count) {
        checkCountValue(systemIssueCount, count);
        return this;
    }

    @Step("Check investigate count: {count}")
    public SLaunchItem checkInvestigateCount(int count) {
        checkCountValue(investigateCount, count);
        return this;
    }

    private void checkCountValue(By locator, int value) {
        if (value == 0) {
            Assertions.assertTrue(getRoot().findElements(locator).isEmpty(), "Element shouldn't be on the page.");
        } else {
            Assertions.assertEquals(Integer.valueOf(getRoot().findElement(locator).getText()), value);
        }
    }
}
