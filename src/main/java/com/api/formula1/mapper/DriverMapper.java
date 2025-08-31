package com.api.formula1.mapper;

import com.api.formula1.model.driver.Driver;
import com.api.formula1.model.driver.ExternalDriver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(source = "driver_number", target = "driverNumber")
    @Mapping(source = "full_name", target = "fullName")
    Driver toDriver(ExternalDriver externalDriver);

    List<Driver> toDrivers(List<ExternalDriver> externalDrivers);
}
