package com.api.formula1.service;

import com.api.formula1.mapper.SessionMapper;
import com.api.formula1.model.session.ExternalSession;
import com.api.formula1.model.session.Session;
import com.api.formula1.repository.external.ExternalSessionProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final ExternalSessionProvider externalSessionProvider;
    private final SessionMapper sessionMapper;
    private final DriverService driverService;

    public SessionService(ExternalSessionProvider externalSessionProvider, SessionMapper sessionMapper, DriverService driverService) {
        this.externalSessionProvider = externalSessionProvider;
        this.sessionMapper = sessionMapper;
        this.driverService = driverService;
    }

    public List<Session> getSessions(String sessionType, Integer year, String country) {
        List<ExternalSession> externalSessions = externalSessionProvider.getExternalSessions(sessionType, year, country);
        List<Session> sessions = sessionMapper.toSessions(externalSessions);
        sessions.forEach(this::setDriverMarketForSession);
        return sessions;
    }

    public Session getSessionBySessionKey(Long sessionKey) {
        ExternalSession externalSession = externalSessionProvider.getExternalSessionBySessionKey(sessionKey);
        Session session = sessionMapper.toSession(externalSession);
        setDriverMarketForSession(session);
        return session;
    }

    private void setDriverMarketForSession(Session session) {
        session.setDriverMarket(driverService.getDriversForSession(session.getSessionKey()));
    }
}
