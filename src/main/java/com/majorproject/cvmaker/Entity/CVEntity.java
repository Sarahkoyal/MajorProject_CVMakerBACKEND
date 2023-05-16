package com.majorproject.cvmaker.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table
public class CVEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cvId;

    private String cvName;

    @OneToOne
    private UserEntity userId;
    private Long profileId;
    private Long educationId;
    private Long workExpId;
    private Long skillsId;
    private Long languageId;
    private Long hobbiesId;
}
