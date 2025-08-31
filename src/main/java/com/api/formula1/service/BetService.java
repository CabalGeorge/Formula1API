package com.api.formula1.service;

import com.api.formula1.model.bet.Bet;
import com.api.formula1.model.bet.BetDTO;
import com.api.formula1.model.bet.BetStatus;
import com.api.formula1.model.session.Session;
import com.api.formula1.model.user.User;
import com.api.formula1.repository.internal.BetRepository;
import com.api.formula1.repository.internal.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final SessionService sessionService;

    public BetService(BetRepository betRepository, UserRepository userRepository, SessionService sessionService) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    @Transactional
    public Bet placeBet(BetDTO betDTO) {
        User user = userRepository.findById(betDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for id" + betDTO.getUserId()));
        Session session = sessionService.getSessionBySessionKey(betDTO.getSessionKey());

        if (session == null) {
            throw new RuntimeException("No existing session for key " + betDTO.getSessionKey());
        }
        if (session.getDriverMarket().stream().noneMatch(driver -> driver.getDriverNumber().equals(betDTO.getDriverNumber()))) {
            throw new RuntimeException("No existing driver in session for driver number " + betDTO.getDriverNumber());
        }
        if (user.getUserBalance() - betDTO.getAmount() < 0) {
            throw new RuntimeException("Insufficient money in balance");
        }
        Bet bet = new Bet();
        bet.setUser(user);
        bet.setSessionKey(betDTO.getSessionKey());
        bet.setDriverNumber(betDTO.getDriverNumber());
        bet.setAmount(betDTO.getAmount());
        bet.setOdds(session.getDriverMarket().stream().filter(driver -> driver.getDriverNumber().equals(betDTO.getDriverNumber())).findFirst().get().getOdds());
        bet.setStatus(BetStatus.PLACED);
        user.setUserBalance(user.getUserBalance() - betDTO.getAmount());

        userRepository.save(user);

        return betRepository.save(bet);
    }
}
