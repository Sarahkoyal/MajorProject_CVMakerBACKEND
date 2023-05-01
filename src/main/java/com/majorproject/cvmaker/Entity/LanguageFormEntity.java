package com.majorproject.cvmaker.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table
public class LanguageFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long profileId;
    private String userGivenString;
    private String aiGeneratedText;
    private Date createdAt;
    private Date lastModifiedOn;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
}
