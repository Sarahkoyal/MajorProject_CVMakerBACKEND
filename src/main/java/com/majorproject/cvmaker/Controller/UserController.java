package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.UserEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.OtpServices;
import com.majorproject.cvmaker.Service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/user")
public class UserController {

    private final UserServices userServices;

    private final OtpServices otpServices;

    @PostMapping(value = "/register")
    public ResponseEntity<SuccessReponseModel> register(@RequestBody UserEntity user, @RequestParam String otp) {
        SuccessReponseModel otpValidationResp = this.otpServices.validateOtp(user.getEmail(), otp);
        if(otpValidationResp.getHttpStatus() == HttpStatus.OK){
            return ResponseEntity.ok().body(this.userServices.registerNewUser(user));
        }
        return ResponseEntity.ok().body(otpValidationResp);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SuccessReponseModel> login(@RequestParam String email, @RequestParam String otp) {
        SuccessReponseModel validateOtp = this.otpServices.validateOtp(email, otp);
        if(validateOtp.getStatusCode() == 200) {
            return ResponseEntity.ok().body(this.userServices.doLogin(email));
        }
        return ResponseEntity.ok().body(validateOtp);
    }
}
