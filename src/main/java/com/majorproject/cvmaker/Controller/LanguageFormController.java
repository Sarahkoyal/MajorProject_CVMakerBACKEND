package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.HobbiesFormEntity;
import com.majorproject.cvmaker.Entity.LanguageFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.HobbiesFormService;
import com.majorproject.cvmaker.Service.LanguageFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/language")
public class LanguageFormController {

    private final LanguageFormService languageFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> addLanguage(@RequestBody LanguageFormEntity languageFormEntity, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.languageFormService.addNewLanguage(languageFormEntity, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieve(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.languageFormService.retrieve(userId));
    }
}
