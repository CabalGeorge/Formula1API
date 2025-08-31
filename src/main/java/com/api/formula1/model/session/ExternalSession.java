package com.api.formula1.model.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalSession {

    private Long circuit_key;
    private String circuit_short_name;
    private String country_code;
    private Long country_key;
    private String country_name;
    private OffsetDateTime date_end;
    private OffsetDateTime date_start;
    private String gmt_offset;
    private String location;
    private Long meeting_key;
    private Long session_key;
    private String session_name;
    private String session_type;
    private String year;
}
