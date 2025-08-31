package com.api.formula1.model.bet;

import lombok.Data;

@Data
public class BetDTO {

    private Long userId;
    private Long sessionKey;
    private Integer driverNumber;
    private Long amount;
}
