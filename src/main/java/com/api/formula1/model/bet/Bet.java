package com.api.formula1.model.bet;

import com.api.formula1.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column
    private Long sessionKey;

    @Column
    private Integer driverNumber;

    @Column
    private Long amount;

    @Column
    private Integer odds;

    @Enumerated
    private BetStatus status;
}
