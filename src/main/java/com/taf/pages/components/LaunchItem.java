package com.taf.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.annotations.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.taf.utils.scopes.ParametricPrototypeScope.SCOPE_ELEMENT_PROTOTYPE;
import static java.lang.String.valueOf;

@Component
@Scope(SCOPE_ELEMENT_PROTOTYPE)
public class LaunchItem {

    @Getter
    private SelenideElement root;
    private By totalCount = By.cssSelector("[statuses=\"PASSED,FAILED,SKIPPED,INTERRUPTED\"]");
    private By passedCount = By.cssSelector("[statuses=\"PASSED\"]");
    private By failedCount = By.cssSelector("[statuses=\"FAILED,INTERRUPTED\"]");
    private By skippedCount = By.cssSelector("[statuses=\"SKIPPED\"]");

    private By donutValue = By.cssSelector("[class^=\"donutChart__total\"]");
    private By productBugCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__pb-col\"]"), donutValue);
    private By autoBugCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__ab-col\"]"), donutValue);
    private By systemIssueCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__si-col\"]"), donutValue);
    private By investigateCount = new ByChained(By.cssSelector("[class^=\"launchSuiteGrid__ti-col\"]"), donutValue);

    public LaunchItem(SelenideElement root) {
        this.root = root;
    }

    @Step("Check total count: {count}")
    public LaunchItem checkTotalCount(int count) {
        checkCountValue(getRoot().$(totalCount), count);
        return this;
    }

    @Step("Check passed count: {count}")
    public LaunchItem checkPassedCount(int count) {
        checkCountValue(getRoot().$(passedCount), count);
        return this;
    }

    @Step("Check failed count: {count}")
    public LaunchItem checkFailedCount(int count) {
        checkCountValue(getRoot().$(failedCount), count);
        return this;
    }

    @Step("Check skipped count: {count}")
    public LaunchItem checkSkippedCount(int count) {
        checkCountValue(getRoot().$(skippedCount), count);
        return this;
    }

    @Step("Check product bug count: {count}")
    public LaunchItem checkProductBugCount(int count) {
        checkCountValue(getRoot().$(productBugCount), count);
        return this;
    }

    @Step("Check auto bug count: {count}")
    public LaunchItem checkAutoBugCount(int count) {
        checkCountValue(getRoot().$(autoBugCount), count);
        return this;
    }

    @Step("Check system issue count: {count}")
    public LaunchItem checkSystemIssueCount(int count) {
        checkCountValue(getRoot().$(systemIssueCount), count);
        return this;
    }

    @Step("Check investigate count: {count}")
    public LaunchItem checkInvestigateCount(int count) {
        checkCountValue(getRoot().$(investigateCount), count);
        return this;
    }

    private void checkCountValue(SelenideElement element, int value) {
        if (value == 0) {
            element.shouldNot(visible);
        } else {
            element.shouldHave(text(valueOf(value)));
        }
    }
}