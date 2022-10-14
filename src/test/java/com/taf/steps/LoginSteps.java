package com.taf.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.taf.core.DriverManager;
import com.taf.steps.configurations.CucumberSpringConfiguration;
import com.taf.pages.LoginPage;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
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
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1920,1080));
        open(url);
        log.info(url);
        loginPage.loginAsUser(email, password);

    }
}
