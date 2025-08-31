package com.api.formula1.model.user;

import com.api.formula1.model.bet.Bet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class User {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Long userBalance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Bet> bets;
}
