package com.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.taf.api.entities.CreateDashboardInput;
import com.taf.api.services.AuthService;
import com.taf.api.services.BaseService;

import com.taf.api.services.DashboardService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
@ExtendWith(ReportPortalExtension.class)
public class ReportPortalApiTests {

    @BeforeEach
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Autowired
    private AuthService authService;
    @Autowired
    private DashboardService dashboardService;

    @Test
    public void apiCall() {
        String token = authService.getDefaultUserToken();
        CreateDashboardInput dashboardInput = CreateDashboardInput.builder().description("blablabla").name("bla").share(false).build();
        dashboardService.createNewDashboard(dashboardInput, token);


    }
}
