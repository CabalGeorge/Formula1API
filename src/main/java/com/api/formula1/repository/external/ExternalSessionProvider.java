package com.api.formula1.repository.external;

import com.api.formula1.model.session.ExternalSession;

import java.util.List;

public interface ExternalSessionProvider {

    List<ExternalSession> getExternalSessions(String sessionType, Integer year, String country);

    ExternalSession getExternalSessionBySessionKey(Long sessionKey);
}
