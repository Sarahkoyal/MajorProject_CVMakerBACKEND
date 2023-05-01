package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.CVEntity;
import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.CVService;
import com.majorproject.cvmaker.Service.EducationFormService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/cv")
public class CVController {

    private final CVService cvService;


    @PostMapping(value = "/save")
    public ResponseEntity<SuccessReponseModel> saveCv(@RequestBody CVEntity cvEntity, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.cvService.save(cvEntity, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieve(@RequestParam Long cvId){
        return ResponseEntity.ok().body(this.cvService.retrieve(cvId));
    }

    @GetMapping(value = "/fetchAllCV")
    public ResponseEntity<SuccessReponseModel> fetchAll(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.cvService.fetchAllCV(userId));
    }
}
