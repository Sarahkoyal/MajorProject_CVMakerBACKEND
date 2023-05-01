package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Repository.OtpJpaRepository;
import com.majorproject.cvmaker.Service.OtpServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/otp")
public class OtpController {

    private final OtpServices otpServices;


    @PostMapping(value = "/generate")
    public ResponseEntity<SuccessReponseModel> generateOtp(@RequestParam String email){
       return ResponseEntity.ok().body(this.otpServices.generateOtp(email));
    }


    @PostMapping(value = "/validate")
    public ResponseEntity<SuccessReponseModel> validateOtp(@RequestParam String email, @RequestParam String otp){
        return ResponseEntity.ok().body(this.otpServices.validateOtp(email, otp));
    }
}
