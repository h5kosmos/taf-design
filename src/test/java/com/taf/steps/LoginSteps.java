package com.taf.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.taf.core.DriverManager;
import com.taf.steps.configurations.CucumberSpringConfiguration;
import com.taf.pages.LaunchesPage;
import com.taf.pages.LoginPage;
import com.taf.pages.components.Navigation;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

public class LoginSteps extends CucumberSpringConfiguration {

    @Autowired
    private DriverManager driverManager;
    @Autowired
    private LoginPage loginPage;

    @Value("${app.url}")
    private String url;
    @Value("${user.login}")
    private String email;
    @Value("${user.password}")
    private String password;

    @When("^User is on Home Page$")
    public void the_client_issues_GET_version() throws Throwable {
        WebDriverRunner.setWebDriver(driverManager.initDriver());
        open(url);
        loginPage.loginAsUser(email, password);
    }
}
