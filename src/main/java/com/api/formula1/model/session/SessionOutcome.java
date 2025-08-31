package com.api.formula1.model.session;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SessionOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long sessionKey;

    @Column
    private Integer winningDriverNumber;
}
