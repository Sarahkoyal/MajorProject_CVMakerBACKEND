package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.WorkExpFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.WorkExpFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/workExp")
public class WorkExpFormController {

    private final WorkExpFormService workExpFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> addWorkExp(@RequestBody WorkExpFormEntity educationForm, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.workExpFormService.addNewWorkExp(educationForm, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieveData(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.workExpFormService.retrieve(userId));
    }
}
