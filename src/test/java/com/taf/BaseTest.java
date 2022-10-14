package com.taf;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.junit5.ReportPortalExtension;
import com.taf.core.DriverManager;
import com.taf.pages.LaunchesPage;
import com.taf.pages.LoginPage;
import com.taf.pages.components.Navigation;

import com.taf.pojo.LaunchPojo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = TestApplication.class)
@ExtendWith(ReportPortalExtension.class)
public class BaseTest {
    @Autowired
    private DriverManager driverManager;
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private Navigation navigation;
    @Autowired
    private LaunchesPage launchesPage;
    @Value("${app.url}")
    private String url;
    @Value("${user.login}")
    private String email;
    @Value("${user.password}")
    private String password;

    @BeforeEach
    public void setUp() {
        System.out.println("base-class-before-each");
        WebDriverRunner.setWebDriver(driverManager.initDriver());
        open(url);
        loginPage.loginAsUser(email, password);
        navigation.openLaunches();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Success test")
    public void successTest() {

    }

    static class LaunchesProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new LaunchPojo(3938640, 30, 30, 0, 0, 0, 0, 0, 0)),
                    Arguments.of(new LaunchPojo(3938635, 25, 20, 5, 0, 4, 1, 0, 2)),
                    Arguments.of(new LaunchPojo(3938628, 20, 10, 8, 2, 4, 4, 0, 10)),
                    Arguments.of(new LaunchPojo(3938626, 15, 5, 9, 1, 1, 5, 4, 8)),
                    Arguments.of(new LaunchPojo(3938625, 10, 1, 9, 0, 0, 1, 8, 5)),
                    Arguments.of(new LaunchPojo(3938623, 30, 30, 0, 0, 0, 0, 0, 0)),
                    Arguments.of(new LaunchPojo(3938622, 25, 20, 5, 0, 4, 1, 0, 2)),
                    Arguments.of(new LaunchPojo(3938621, 20, 10, 8, 2, 4, 4, 0, 10)),
                    Arguments.of(new LaunchPojo(3938620, 15, 5, 9, 1, 1, 5, 4, 8)),
                    Arguments.of(new LaunchPojo(3938619, 10, 1, 9, 0, 0, 1, 8, 5))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(LaunchesProvider.class)
    void testLaunchesData(LaunchPojo launch) {
        launchesPage.getLaunchItemByID(launch.getId())
                .checkTotalCount(launch.getTotal())
                .checkPassedCount(launch.getPassed())
                .checkFailedCount(launch.getFailed())
                .checkSkippedCount(launch.getSkipped())
                .checkProductBugCount(launch.getProductBugs())
                .checkAutoBugCount(launch.getAutoBugs())
                .checkSystemIssueCount(launch.getSystemIssues())
                .checkInvestigateCount(launch.getToInvestigate());
    }
}
