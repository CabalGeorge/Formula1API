package com.api.formula1.repository.internal;

import com.api.formula1.model.bet.Bet;
import com.api.formula1.model.bet.BetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findBySessionKeyAndStatus(Long sessionKey, BetStatus status);

}
