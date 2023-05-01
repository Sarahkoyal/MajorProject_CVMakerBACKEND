package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.ProfileFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.ProfileFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/profileForm")
public class ProfileFormController {

    private final ProfileFormService profileFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> createProfile(@RequestBody ProfileFormEntity profileForm, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.profileFormService.addNewProfile(profileForm, userId));
    }
}
