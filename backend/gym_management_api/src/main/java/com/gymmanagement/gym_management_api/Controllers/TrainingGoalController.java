package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.NotDetailedTrainingGoalDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import com.gymmanagement.gym_management_api.Services.TrainingGoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/training_goals")
@Tag(name = Tags.TrainingGoalsTag)
public class TrainingGoalController {
    private final TrainingGoalService tgService;

    //------GET------//

    @PreAuthorize(RoleClaims.AllClaim)
    @GetMapping
    public ResponseEntity<Iterable<TrainingGoalDTO>>getTrainingGoals(){
        return ResponseEntity.ok(tgService.getTrainingGoals());
    }

    @PreAuthorize(RoleClaims.AllClaim)
    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedTrainingGoalDTO>>getNotDetailedTrainingGoals(){
        return ResponseEntity.ok(tgService.getNotDetailedTrainingGoals());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<TrainingGoalDTO>getTrainingGoalById(@PathVariable Integer id){
        return ResponseEntity.ok(tgService.getTrainingGoalById(id));
    }

    //-----POST-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void> addTrainingGoal(@RequestBody TrainingGoalCreateDTO dto){
        tgService.addTrainingGoal(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
