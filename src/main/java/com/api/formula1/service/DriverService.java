package com.api.formula1.service;

import com.api.formula1.mapper.DriverMapper;
import com.api.formula1.model.driver.Driver;
import com.api.formula1.model.driver.ExternalDriver;
import com.api.formula1.repository.external.ExternalDriverProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DriverService {

    private final ExternalDriverProvider externalDriverProvider;
    private final DriverMapper driverMapper;

    public DriverService(ExternalDriverProvider externalDriverProvider, DriverMapper driverMapper) {
        this.externalDriverProvider = externalDriverProvider;
        this.driverMapper = driverMapper;
    }

    public List<Driver> getDriversForSession(Long sessionKey) {
        List<ExternalDriver> externalDrivers = externalDriverProvider.getExternalDriversForSession(sessionKey);
        List<Driver> drivers = driverMapper.toDrivers(externalDrivers);
        drivers.forEach(this::setDriverOdds);
        return drivers;
    }

    private void setDriverOdds(Driver driver) {
        Random random = new Random();
        driver.setOdds(2 + random.nextInt(3)); // odds random between 2 and 4
    }
}
