package com.api.formula1.controller;

import com.api.formula1.model.bet.Bet;
import com.api.formula1.model.bet.BetDTO;
import com.api.formula1.model.session.Session;
import com.api.formula1.model.session.SessionOutcome;
import com.api.formula1.model.session.SessionOutcomeDTO;
import com.api.formula1.service.BetService;
import com.api.formula1.service.SessionOutcomeService;
import com.api.formula1.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/f1")
public class F1Controller {

    private final SessionService sessionService;
    private final BetService betService;
    private final SessionOutcomeService sessionOutcomeService;

    public F1Controller(SessionService sessionService, BetService betService, SessionOutcomeService sessionOutcomeService) {
        this.sessionService = sessionService;
        this.betService = betService;
        this.sessionOutcomeService = sessionOutcomeService;
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions(
            @RequestParam(value = "sessionType", required = false) String sessionType,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "country", required = false) String country) {

        List<Session> sessions = sessionService.getSessions(sessionType, year, country);
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/bets")
    public ResponseEntity<Bet> placeBet(@RequestBody BetDTO betDTO) {
        Bet bet = betService.placeBet(betDTO);
        return new ResponseEntity<>(bet, HttpStatus.CREATED);
    }

    @PostMapping("/outcome")
    public ResponseEntity<SessionOutcome> simulateSession(@RequestBody SessionOutcomeDTO sessionOutcomeDTO) {
        SessionOutcome sessionOutcome = sessionOutcomeService.simulateSession(sessionOutcomeDTO);
        return new ResponseEntity<>(sessionOutcome, HttpStatus.CREATED);
    }
}
