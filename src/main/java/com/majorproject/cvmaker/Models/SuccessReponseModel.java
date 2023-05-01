package com.majorproject.cvmaker.Models;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessReponseModel {
    private Map<String, Object> body;
    private Instant timeStamp;
    private HttpStatus httpStatus;
    private int statusCode;
}
