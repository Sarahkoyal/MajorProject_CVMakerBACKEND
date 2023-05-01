package com.majorproject.cvmaker.Service;

import com.majorproject.cvmaker.Entity.ProfileFormEntity;
import com.majorproject.cvmaker.Entity.UserEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.ProfileFormRepository;
import com.majorproject.cvmaker.Repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProfileFormService {
    private final UserJpaRepository userJpaRepository;

    private final ProfileFormRepository profileFormRepository;
    public SuccessReponseModel addNewProfile(ProfileFormEntity profileForm, Long userId){
        Optional<UserEntity> user = this.userJpaRepository.findById(userId);
        Map<String, Object> map = new HashMap<>();
        if(user.isPresent()){
            ProfileFormEntity newProfile = ProfileFormEntity.builder()
                    .userGivenString(profileForm.getUserGivenString())
                    .aiGeneratedText(profileForm.getAiGeneratedText())
                    .user(user.get())
                    .createdAt(new Date())
                    .build();

            this.profileFormRepository.save(newProfile);

            map.put("msg", "CREATED");
            return SuccessReponseModel.builder()
                    .body(map)
                    .statusCode(HttpStatus.CREATED.value())
                    .httpStatus(HttpStatus.CREATED)
                    .timeStamp(Instant.now())
                    .build();
        }
        map.put("msg", "NOT CREATED");
        return SuccessReponseModel.builder()
                .body(map)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(Instant.now())
                .build();
    }
}
