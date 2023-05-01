package com.majorproject.cvmaker.Service;


import com.majorproject.cvmaker.Entity.OtpEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.OtpJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServices {


    private final OtpJpaRepository otpJpaRepository;


    public SuccessReponseModel generateOtp(String email) {
        Random random = new Random();
        int otp = random.nextInt(1111, 9999);
        OtpEntity newOtp = OtpEntity.builder()
                .otp(otp)
                .createdAt(Instant.now())
                .validTill(Instant.now().plusSeconds(120))
                .email(email)
                .status(1)
                .build();

        this.otpJpaRepository.save(newOtp);
        HashMap<String, Object> data = new HashMap<>();
        data.put("OTP", otp);
        return SuccessReponseModel.builder()
                .body(data)
                .timeStamp(Instant.now())
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public SuccessReponseModel validateOtp(String email, String otp) {
        OtpEntity fetchedOtpByEmail = this.otpJpaRepository.findByEmailAndOtp(email, otp);
        HashMap<String, Object> data = new HashMap<>();
        if(fetchedOtpByEmail.getValidTill().isAfter(Instant.now())){

            fetchedOtpByEmail.builder()
                    .id(fetchedOtpByEmail.getId())
                    .status(2)
                    .build();
            this.otpJpaRepository.save(fetchedOtpByEmail);


            data.put("msg", "Otp validated successfully");
            return SuccessReponseModel.builder()
                    .body(data)
                    .timeStamp(Instant.now())
                    .httpStatus(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }
        data.put("msg", "OTP not valid");
        return SuccessReponseModel.builder()
                .body(data)
                .timeStamp(Instant.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
