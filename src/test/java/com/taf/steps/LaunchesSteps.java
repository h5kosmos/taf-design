package com.taf.steps;

import com.taf.pojo.LaunchPojo;
import com.taf.steps.configurations.CucumberSpringConfiguration;
import com.taf.pages.selenide.LaunchesPage;
import com.taf.pages.selenide.LoginPage;
import com.taf.pages.selenide.components.Navigation;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.lang.Integer.parseInt;

public class LaunchesSteps extends CucumberSpringConfiguration {

    @Autowired
    private LoginPage loginPage;
    @Autowired
    private Navigation navigation;
    @Autowired
    private LaunchesPage launchesPage;

    @When("User Navigate to Launches Page")
    public void userNavigateToLaunchesPage() {
        navigation.openLaunches();
    }

    @DataTableType
    public LaunchPojo launchEntry(List<String> list) {
        return new LaunchPojo(
                parseInt(list.get(0)),
                parseInt(list.get(1)),
                parseInt(list.get(2)),
                parseInt(list.get(3)),
                parseInt(list.get(4)),
                parseInt(list.get(5)),
                parseInt(list.get(6)),
                parseInt(list.get(7)),
                parseInt(list.get(8)));
    }

    @And("Launch with id: {int}  have correct information")
    public void launchWithIdHaveCorrectInformation(Integer launchId, LaunchPojo launch) {
        launchesPage.getLaunchItemByID(launchId)
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
