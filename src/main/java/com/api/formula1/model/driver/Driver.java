package com.api.formula1.model.driver;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    private String fullName;
    private Integer driverNumber;
    private Integer odds;

}
