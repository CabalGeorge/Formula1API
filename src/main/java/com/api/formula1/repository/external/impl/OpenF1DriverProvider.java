package com.api.formula1.repository.external.impl;

import com.api.formula1.model.driver.ExternalDriver;
import com.api.formula1.repository.external.ExternalDriverProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class OpenF1DriverProvider implements ExternalDriverProvider {

    private final static String BASE_DRIVERS_URL = "https://api.openf1.org/v1/drivers?";

    private final RestTemplate restTemplate;

    public OpenF1DriverProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ExternalDriver> getExternalDriversForSession(Long sessionKey) {
        StringBuilder urlBuilder = new StringBuilder(BASE_DRIVERS_URL);

        if (sessionKey == null) {
            throw new RuntimeException("No present session key to fetch drivers from OpenF1 API");
        }

        urlBuilder.append("session_key=").append(sessionKey);

        try {
            ResponseEntity<ExternalDriver[]> response = restTemplate.getForEntity(urlBuilder.toString(), ExternalDriver[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Arrays.asList(response.getBody());
            } else {
                throw new RuntimeException("Failed to fetch drivers from OpenF1 API");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching drivers from OpenF1: " + ex.getMessage(), ex);
        }
    }
}
