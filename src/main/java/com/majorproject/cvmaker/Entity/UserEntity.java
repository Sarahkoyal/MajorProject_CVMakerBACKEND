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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String phnNumber;
    private String email;
    private String linkedInUrl;
    private Instant createdAt;
    private Instant modifiedAt;
}
