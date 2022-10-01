package com.taf.utils.reportportal;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;

@Slf4j
public class ReportPortalListener extends ReportPortalTestNGListener {

    @Override
    public void onTestStart(ITestResult testResult) {
        super.onTestStart(testResult);
        log.info("{} started", testResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        log.info("{} passed", testResult.getName());
        super.onTestSuccess(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        log.error("{} failed", testResult.getName());
        super.onTestFailure(testResult);
    }
}
