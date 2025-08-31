package com.api.formula1.repository.internal;

import com.api.formula1.model.session.SessionOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionOutcomeRepository extends JpaRepository<SessionOutcome, Long> {
}
