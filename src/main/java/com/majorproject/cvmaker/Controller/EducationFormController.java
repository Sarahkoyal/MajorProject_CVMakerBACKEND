package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.ProfileFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.EducationFormService;
import com.majorproject.cvmaker.Service.ProfileFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/educationForm")
public class EducationFormController {

    private final EducationFormService educationFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> addEducation(@RequestBody EducationFormEntity educationForm, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.educationFormService.addNewEducation(educationForm, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieve(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.educationFormService.retrieve(userId));
    }
}
