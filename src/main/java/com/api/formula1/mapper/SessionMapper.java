package com.api.formula1.mapper;

import com.api.formula1.model.session.ExternalSession;
import com.api.formula1.model.session.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(source = "circuit_key", target = "circuitKey")
    @Mapping(source = "circuit_short_name", target = "circuitShortName")
    @Mapping(source = "country_code", target = "countryCode")
    @Mapping(source = "country_key", target = "countryKey")
    @Mapping(source = "country_name", target = "countryName")
    @Mapping(source = "date_end", target = "dateEnd")
    @Mapping(source = "date_start", target = "dateStart")
    @Mapping(source = "gmt_offset", target = "gmtOffset")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "meeting_key", target = "meetingKey")
    @Mapping(source = "session_key", target = "sessionKey")
    @Mapping(source = "session_name", target = "sessionName")
    @Mapping(source = "session_type", target = "sessionType")
    @Mapping(source = "year", target = "year")
    Session toSession(ExternalSession externalSession);

    List<Session> toSessions(List<ExternalSession> externalSessions);
}
