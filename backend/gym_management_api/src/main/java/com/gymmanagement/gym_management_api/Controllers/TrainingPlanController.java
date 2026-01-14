package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.NotDetailedTrainingPlanDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanDTO;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Services.TrainingPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/training_plans")
@Tag(name = Tags.TrainingPlansTag)
public class TrainingPlanController {
    private final TrainingPlanService tpService;

    //------GET------//

    @GetMapping
    public ResponseEntity<Iterable<TrainingPlanDTO>>getTrainingPlans(){
        return ResponseEntity.ok(tpService.getTrainingPlans());
    }

    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedTrainingPlanDTO>>getNotDetailedTrainingPlans(){
        return ResponseEntity.ok(tpService.getNotDetailedTrainingPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDTO>getTrainingPlanById(@PathVariable Integer id){
        return ResponseEntity.ok(tpService.getTrainingPlanById(id));
    }

    //-----POST-----//

    @PostMapping
    public ResponseEntity<Void> addTrainingPlan(@RequestBody TrainingPlanCreateDTO dto){
        tpService.addTrainingPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
