package com.taf.api.services;

import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public abstract class BaseService {

    public ValidatableResponse postApiResponse(String endpoint, Map<String, Object> params, Header header) {
        return getApiRequestSpecification(params, header)
                .log().all()
                .post(endpoint)
                .then();
    }

    public ValidatableResponse postApiResponse(String endpoint, String token, Map<String, String> body) {
        return getApiRequestSpecification(token)
                .log().all()
                .body(body)
                .post(endpoint)
                .then();
    }

    public ValidatableResponse postApiResponse(String endpoint, String token, String body) {
        return getApiRequestSpecification(token)
                .log().all()
                .body(body)
                .post(endpoint)
                .then();
    }

    public ValidatableResponse getApiResponse(String endpoint, String token) {
        return getApiRequestSpecification(token)
                .log().all()
                .get(endpoint)
                .then();
    }

    public ValidatableResponse deleteApiResponse(String endpoint, String token) {
        return getApiRequestSpecification(token)
                .log().all()
                .delete(endpoint)
                .then();
    }

    private RequestSpecification getApiRequestSpecification(String token) {
        return given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .auth().oauth2(token)
                .when();
    }

    private RequestSpecification getApiRequestSpecification(Map<String, Object> params, Header header) {
        return given()
                .contentType(ContentType.APPLICATION_FORM_URLENCODED.toString())
                .header(header)
                .formParams(params)
                .when();
    }

}
