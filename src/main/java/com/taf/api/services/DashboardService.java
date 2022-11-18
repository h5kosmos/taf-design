package com.taf.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taf.api.entities.CreateDashboardInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.taf.api.Endpoints.DASHBOARD_ENDPOINT;
import static java.lang.String.format;

@Slf4j
@Service
public class DashboardService extends BaseService{

    @Value("${app.url}")
    private String appUrl;

    @Value("${user.project}")
    private String project;

    public void getDashboards(String token) {
        getApiResponse(format(appUrl + DASHBOARD_ENDPOINT,project), token).extract().body().toString();
    }

    public void createNewDashboard(CreateDashboardInput input, String token) {
        ObjectMapper ow = new ObjectMapper();
        String inputString;
        try {
            inputString = ow.writeValueAsString(input);
            postApiResponse(format(appUrl + DASHBOARD_ENDPOINT,project),token, inputString).extract().body().toString();
        } catch ( Exception e) {

        }
    }
}
