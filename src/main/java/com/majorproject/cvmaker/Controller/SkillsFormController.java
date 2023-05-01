package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.SkillsFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.EducationFormService;
import com.majorproject.cvmaker.Service.SkillsFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/skillsForm")
public class SkillsFormController {

    private final SkillsFormService skillsFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> addSkills(@RequestBody SkillsFormEntity skillsForm, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.skillsFormService.addNewSkills(skillsForm, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieve(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.skillsFormService.retrieve(userId));
    }
}
