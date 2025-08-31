package com.api.formula1.model.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalDriver {

    private String broadcast_name;
    private String country_code;
    private Integer driver_number;
    private String first_name;
    private String full_name;
    private String headshot_url;
    private String last_name;
    private Long meeting_key;
    private String name_acronym;
    private Long session_key;
    private String team_colour;
    private String team_name;
}
