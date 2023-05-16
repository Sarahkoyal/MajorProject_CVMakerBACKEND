package com.majorproject.cvmaker.Service;

import com.majorproject.cvmaker.Entity.CVEntity;
import com.majorproject.cvmaker.Entity.UserEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CVService {
    private final UserJpaRepository userJpaRepository;

    private final CVRepository cvRepository;
    private final ProfileFormRepository profileFormRepository;
    private final EducationFormRepository educationFormRepository;
    private final WorkExpFormRepository workExpFormRepository;
    private final SkillsFormRepository skillsFormRepository;
    private final LanguageFormRepository languageFormRepository;
    private final HobbiesFormRepository hobbiesFormRepository;

    public SuccessReponseModel save(CVEntity cv, Long userId) {
        Optional<UserEntity> user = this.userJpaRepository.findById(userId);
        Map<String, Object> map = new HashMap<>();
        if (user.isPresent()) {
            CVEntity newCv = CVEntity.builder()
                    .cvName(cv.getCvName())
                    .profileId(cv.getProfileId())
                    .educationId(cv.getEducationId())
                    .workExpId(cv.getWorkExpId())
                    .hobbiesId(cv.getHobbiesId())
                    .languageId(cv.getLanguageId())
                    .skillsId(cv.getSkillsId())
                    .userId(user.get())
                    .build();

            this.cvRepository.save(newCv);

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

    public SuccessReponseModel retrieve(Long cvId) {
        Optional<CVEntity> byId = this.cvRepository.findById(cvId);
        Map<String, Object> data = new HashMap<>();
        if (byId.isPresent()) {
            data.put("profile", this.profileFormRepository.findById(byId.get().getProfileId()).get().getAiGeneratedText());
            data.put("education", this.educationFormRepository.findById(byId.get().getEducationId()).get().getAiGeneratedText());
            data.put("workExp", this.workExpFormRepository.findById(byId.get().getWorkExpId()).get().getAiGeneratedText());
            data.put("skills", this.skillsFormRepository.findById(byId.get().getSkillsId()).get().getAiGeneratedText());
            data.put("languages", this.languageFormRepository.findById(byId.get().getLanguageId()).get().getAiGeneratedText());
            data.put("hobbies", this.hobbiesFormRepository.findById(byId.get().getHobbiesId()).get().getAiGeneratedText());
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

    public SuccessReponseModel fetchAllCV(Long userId) {
        List<CVEntity> byUserIdId = this.cvRepository.findByUserId_Id(userId);
        Map<String, Object> map = new HashMap<>();
        if(byUserIdId != null) {
            map.put("data", byUserIdId);
            return SuccessReponseModel.builder()
                    .body(map)
                    .statusCode(200)
                    .httpStatus(HttpStatus.OK)
                    .timeStamp(Instant.now())
                    .build();
        }
        map.put("msg", "Error Occurred");
        return SuccessReponseModel.builder()
                .body(map)
                .statusCode(400)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(Instant.now())
                .build();
    }
}
