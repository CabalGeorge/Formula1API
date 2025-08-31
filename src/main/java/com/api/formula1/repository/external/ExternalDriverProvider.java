package com.api.formula1.repository.external;

import com.api.formula1.model.driver.ExternalDriver;

import java.util.List;

public interface ExternalDriverProvider {

    List<ExternalDriver> getExternalDriversForSession(Long sessionKey);
}
