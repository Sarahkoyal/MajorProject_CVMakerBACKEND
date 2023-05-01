package com.majorproject.cvmaker.Service;


import com.majorproject.cvmaker.Entity.UserEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserJpaRepository userJpaRepository;

    public SuccessReponseModel registerNewUser(UserEntity userEntity) {
        Map<String, Object> userData = new HashMap<>();
        if (this.userJpaRepository.existsByEmailIgnoreCase(userEntity.getEmail())) {
            userData.put("msg", "Email Already used");
            return SuccessReponseModel.builder().body(userData)
                    .statusCode(HttpStatus.CONFLICT.value()).timeStamp(Instant.now())
                    .httpStatus(HttpStatus.CONFLICT).build();
        }

        UserEntity newUser = UserEntity.builder().name(userEntity.getName())
                .email(userEntity.getEmail()).phnNumber(userEntity.getPhnNumber())
                .createdAt(Instant.now()).modifiedAt(Instant.now())
                .build();

        this.userJpaRepository.save(newUser);

        userData.put("name", userEntity.getName());
        userData.put("email", userEntity.getEmail());
        userData.put("phone", userEntity.getPhnNumber());
        userData.put("linkedInUrl", userEntity.getLinkedInUrl());
        userData.put("id", userEntity.getId());
        return SuccessReponseModel.builder().body(userData)
                .statusCode(HttpStatus.OK.value()).timeStamp(Instant.now())
                .httpStatus(HttpStatus.OK).build();
    }

    public SuccessReponseModel doLogin(String email) {
        UserEntity userEntity = this.userJpaRepository.findByEmail(email);
        Map<String, Object> userData = new HashMap<>();
        if(userEntity != null) {
            userData.put("name", userEntity.getName());
            userData.put("email", userEntity.getEmail());
            userData.put("phone", userEntity.getPhnNumber());
            userData.put("linkedInUrl", userEntity.getLinkedInUrl());
            userData.put("id", userEntity.getId());
            return SuccessReponseModel.builder()
                    .httpStatus(HttpStatus.OK)
                    .timeStamp(Instant.now())
                    .statusCode(HttpStatus.OK.value())
                    .body(userData)
                    .build();
        }
        userData.put("msg", "No User Found");
        return SuccessReponseModel.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(Instant.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(userData)
                .build();
    }
}
