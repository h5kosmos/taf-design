package com.taf.pages;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class LoginPage {

    private By emailInput = By.cssSelector("[class^=\"loginForm__login-field\"] input");
    private By passwordInput = By.cssSelector("[class^=\"loginForm__password-field\"] input");
    private By submitButton = By.cssSelector("[type=\"submit\"]");

    @Step(description = "Type text in email input")
    public void typeTextInEmailInput(String email) {
        $(emailInput).sendKeys(email);
    }

    @Step(description = "Type text in password input")
    public void typeTextInPasswordInput(String password) {
        $(passwordInput).sendKeys(password);
    }

    @Step(description = "Click submit button")
    public void clickSubmitButton() {
        $(submitButton).click();
    }

    public void loginAsUser(String email, String password) {
        typeTextInEmailInput(email);
        typeTextInPasswordInput(password);
        clickSubmitButton();
    }
}
