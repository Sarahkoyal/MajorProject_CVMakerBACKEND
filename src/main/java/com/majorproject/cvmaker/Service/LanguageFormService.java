package com.majorproject.cvmaker.Service;

import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.LanguageFormEntity;
import com.majorproject.cvmaker.Entity.UserEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.LanguageFormRepository;
import com.majorproject.cvmaker.Repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LanguageFormService {
    private final UserJpaRepository userJpaRepository;

    private final LanguageFormRepository languageFormRepository;

    public SuccessReponseModel addNewLanguage(LanguageFormEntity languageForm, Long userId) {
        Optional<UserEntity> user = this.userJpaRepository.findById(userId);
        Map<String, Object> map = new HashMap<>();
        if (user.isPresent()) {
            LanguageFormEntity newProfile = LanguageFormEntity.builder()
                    .userGivenString(languageForm.getUserGivenString())
                    .aiGeneratedText(languageForm.getAiGeneratedText())
                    .user(user.get())
                    .createdAt(new Date())
                    .build();

            LanguageFormEntity savedData = this.languageFormRepository.save(newProfile);

            map.put("data", savedData);
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
    public SuccessReponseModel retrieve(Long userId) {
        LanguageFormEntity byId = this.languageFormRepository.findRecentData(userId);
        Map<String, Object> data = new HashMap<>();
        if(byId != null){
            data.put("userGivenData", byId.getUserGivenString());
            data.put("aiGeneratedText", byId.getAiGeneratedText());
            data.put("id", byId.getProfileId().toString());
            return SuccessReponseModel.builder()
                    .timeStamp(Instant.now())
                    .httpStatus(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .body(data)
                    .build();
        }
        data.put("msg", "No Data found");
        return SuccessReponseModel.builder()
                .timeStamp(Instant.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(data)
                .build();
    }
}
