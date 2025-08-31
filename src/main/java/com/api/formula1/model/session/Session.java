package com.api.formula1.model.session;

import com.api.formula1.model.driver.Driver;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class Session {

    private Long circuitKey;
    private String circuitShortName;
    private String countryCode;
    private Long countryKey;
    private String countryName;
    private OffsetDateTime dateEnd;
    private OffsetDateTime dateStart;
    private String gmtOffset;
    private String location;
    private Long meetingKey;
    private Long sessionKey;
    private String sessionName;
    private String sessionType;
    private String year;
    private List<Driver> driverMarket;
}
