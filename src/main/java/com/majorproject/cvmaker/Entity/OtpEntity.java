package com.majorproject.cvmaker.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int otp;
    private String email;
    private int status;
    private Instant createdAt;
    private Instant
            validTill;
}
