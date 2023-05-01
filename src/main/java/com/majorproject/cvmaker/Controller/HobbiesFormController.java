package com.majorproject.cvmaker.Controller;


import com.majorproject.cvmaker.Entity.HobbiesFormEntity;
import com.majorproject.cvmaker.Entity.WorkExpFormEntity;
import com.majorproject.cvmaker.Models.SuccessReponseModel;
import com.majorproject.cvmaker.Service.HobbiesFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/hobbies")
public class HobbiesFormController {

    private final HobbiesFormService hobbiesFormService;


    @PostMapping(value = "/create")
    public ResponseEntity<SuccessReponseModel> addHobbies(@RequestBody HobbiesFormEntity hobbiesFormEntity, @RequestParam Long userId) {
        return ResponseEntity.ok().body(this.hobbiesFormService.addNewHobbies(hobbiesFormEntity, userId));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<SuccessReponseModel> retrieve(@RequestParam Long userId){
        return ResponseEntity.ok().body(this.hobbiesFormService.retrieve(userId));
    }
}
