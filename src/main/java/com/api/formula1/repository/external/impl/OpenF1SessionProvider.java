package com.api.formula1.repository.external.impl;

import com.api.formula1.model.session.ExternalSession;
import com.api.formula1.repository.external.ExternalSessionProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class OpenF1SessionProvider implements ExternalSessionProvider {

    private final static String BASE_SESSIONS_URL = "https://api.openf1.org/v1/sessions?";

    private final RestTemplate restTemplate;

    public OpenF1SessionProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ExternalSession> getExternalSessions(String sessionType, Integer year, String country) {
        StringBuilder urlBuilder = new StringBuilder(BASE_SESSIONS_URL);

        if (StringUtils.hasText(sessionType)) {
            urlBuilder.append("session_type=").append(sessionType).append("&");
        }
        if (year != null) {
            urlBuilder.append("year=").append(year).append("&");
        }
        if (StringUtils.hasText(country)) {
            urlBuilder.append("country_name=").append(country).append("&");
        }

        String url = urlBuilder.toString().replaceAll("&$", "");

        try {
            ResponseEntity<ExternalSession[]> response = restTemplate.getForEntity(url, ExternalSession[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Arrays.asList(response.getBody());
            } else {
                throw new RuntimeException("Failed to fetch sessions from OpenF1 API");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching sessions from OpenF1: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ExternalSession getExternalSessionBySessionKey(Long sessionKey) {
        StringBuilder urlBuilder = new StringBuilder(BASE_SESSIONS_URL);

        if (sessionKey == null) {
            throw new RuntimeException("No present session key to fetch session from OpenF1 API");
        }

        urlBuilder.append("session_key=").append(sessionKey);

        try {
            ResponseEntity<ExternalSession[]> response = restTemplate.getForEntity(urlBuilder.toString(), ExternalSession[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Arrays.asList(response.getBody()).getFirst();
            } else {
                throw new RuntimeException("Failed to fetch session from OpenF1 API");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching session from OpenF1: " + ex.getMessage(), ex);
        }
    }
}
