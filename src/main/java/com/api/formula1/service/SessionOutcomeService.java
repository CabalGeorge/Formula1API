package com.api.formula1.service;

import com.api.formula1.model.bet.Bet;
import com.api.formula1.model.bet.BetStatus;
import com.api.formula1.model.session.SessionOutcome;
import com.api.formula1.model.session.SessionOutcomeDTO;
import com.api.formula1.model.user.User;
import com.api.formula1.repository.internal.BetRepository;
import com.api.formula1.repository.internal.SessionOutcomeRepository;
import com.api.formula1.repository.internal.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionOutcomeService {

    private final SessionOutcomeRepository sessionOutcomeRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;

    public SessionOutcomeService(SessionOutcomeRepository sessionOutcomeRepository, BetRepository betRepository, UserRepository userRepository) {
        this.sessionOutcomeRepository = sessionOutcomeRepository;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SessionOutcome simulateSession(SessionOutcomeDTO sessionOutcomeDTO) {
        SessionOutcome sessionOutcome = new SessionOutcome();
        sessionOutcome.setSessionKey(sessionOutcomeDTO.getSessionKey());
        sessionOutcome.setWinningDriverNumber(sessionOutcomeDTO.getWinningDriverNumber());

        List<Bet> sessionBets = betRepository.findBySessionKeyAndStatus(sessionOutcome.getSessionKey(), BetStatus.PLACED);
        List<Bet> winningBets = sessionBets.stream().filter(bet -> bet.getDriverNumber().equals(sessionOutcomeDTO.getWinningDriverNumber())).toList();
        List<Bet> losingBets = sessionBets.stream().filter(bet -> !bet.getDriverNumber().equals(sessionOutcomeDTO.getWinningDriverNumber())).toList();
        handleWinningBets(winningBets);
        handleLosingBets(losingBets);

        return sessionOutcomeRepository.save(sessionOutcome);
    }

    private void handleWinningBets(List<Bet> winningBets) {
        winningBets.forEach(bet -> {
            User user = bet.getUser();
            user.setUserBalance(user.getUserBalance() + bet.getAmount() * bet.getOdds());
            bet.setStatus(BetStatus.WON);

            userRepository.save(user);
            betRepository.save(bet);
        });
    }

    private void handleLosingBets(List<Bet> losingBets) {
        losingBets.forEach(bet -> {
            bet.setStatus(BetStatus.LOST);
            betRepository.save(bet);
        });
    }


}
