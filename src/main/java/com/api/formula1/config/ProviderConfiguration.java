package com.api.formula1.config;

import com.api.formula1.repository.external.ExternalDriverProvider;
import com.api.formula1.repository.external.ExternalSessionProvider;
import com.api.formula1.repository.external.impl.OpenF1DriverProvider;
import com.api.formula1.repository.external.impl.OpenF1SessionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProviderConfiguration {

    @Value("${external.provider}")
    private String provider;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExternalDriverProvider externalDriverProvider(RestTemplate restTemplate) {
        if ("openF1".equals(provider)) {
            return new OpenF1DriverProvider(restTemplate);
        } else {
            throw new RuntimeException("No valid driver provider configured");
        }
    }

    @Bean
    public ExternalSessionProvider externalSessionProvider(RestTemplate restTemplate) {
        if ("openF1".equals(provider)) {
            return new OpenF1SessionProvider(restTemplate);
        } else {
            throw new RuntimeException("No valid session provider configured");
        }
    }
}
