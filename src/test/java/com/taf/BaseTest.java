package com.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.taf.core.DriverManager;

import com.taf.pages.selenium.SLaunchesPage;
import com.taf.pages.selenium.SLoginPage;
import com.taf.pages.selenium.components.SNavigation;
import com.taf.pojo.LaunchPojo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest(classes = TestApplication.class)
@ExtendWith(ReportPortalExtension.class)
public class BaseTest {
    @Autowired
    private DriverManager driverManager;
    @Autowired
    private ObjectProvider<SLoginPage> loginPageProvider;
    @Autowired
    private ObjectProvider<SNavigation> navigationProvider;
    @Autowired
    private ObjectProvider<SLaunchesPage> launchesPageProvider;
    @Value("${app.url}")
    private String url;
    @Value("${user.login}")
    private String email;
    @Value("${user.password}")
    private String password;

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("base-class-before-each");
        driver = driverManager.initDriver();
        driver.get(url);
        getLoginPage().loginAsUser(email, password);
        getNavigation().openLaunches();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
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
        getLaunchesPage().getLaunchItemByID(launch.getId())
                .checkTotalCount(launch.getTotal())
                .checkPassedCount(launch.getPassed())
                .checkFailedCount(launch.getFailed())
                .checkSkippedCount(launch.getSkipped())
                .checkProductBugCount(launch.getProductBugs())
                .checkAutoBugCount(launch.getAutoBugs())
                .checkSystemIssueCount(launch.getSystemIssues())
                .checkInvestigateCount(launch.getToInvestigate());
    }

    private SLoginPage getLoginPage() {
        return loginPageProvider.getObject(driver);
    }

    private SNavigation getNavigation() {
        return navigationProvider.getObject(driver);
    }

    private SLaunchesPage getLaunchesPage() {
        return launchesPageProvider.getObject(driver);
    }
}
