package com.taf.pages.selenium;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.taf.utils.scopes.PagePrototypeScope.SCOPE_PAGE_PROTOTYPE;

@Component
@Scope(SCOPE_PAGE_PROTOTYPE)
public class SLoginPage {
    private final WebDriver driver;
    private By emailInput = By.cssSelector("[class^=\"loginForm__login-field\"] input");
    private By passwordInput = By.cssSelector("[class^=\"loginForm__password-field\"] input");
    private By submitButton = By.cssSelector("[type=\"submit\"]");

    public SLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getEmailInput() {
        return driver.findElement(emailInput);
    }

    private WebElement getPasswordInput() {
        return driver.findElement(passwordInput);
    }

    private WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }

    @Step(description = "Type text in email input")
    public void typeTextInEmailInput(String email) {
        getEmailInput().sendKeys(email);
    }

    @Step(description = "Type text in password input")
    public void typeTextInPasswordInput(String password) {
        getPasswordInput().sendKeys(password);
    }

    @Step(description = "Click submit button")
    public void clickSubmitButton() {
        getSubmitButton().click();
    }

    public void loginAsUser(String email, String password) {
        typeTextInEmailInput(email);
        typeTextInPasswordInput(password);
        clickSubmitButton();
    }
}
