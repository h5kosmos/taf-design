package com.taf.api.services;

import io.restassured.http.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService extends BaseService {

    @Value("${app.url}")
    private String url;

    @Value("${user.login}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Value("${app.url}")
    private String appUrl;

    public String getUserToken(String username, String password) {
        Header header = new Header("Authorization", "Basic dWk6dWltYW4=");
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        return postApiResponse(appUrl + "/uat/sso/oauth/token", params, header)
                .extract()
                .body()
                .jsonPath()
                .get("access_token");
    }

    public String getDefaultUserToken() {
        return getUserToken(email, password);
    }
}
