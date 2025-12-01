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
    public @ResponseBody Iterable<TrainingPlanDTO>getTrainingPlans(){
        return tpService.getTrainingPlans();
    }

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedTrainingPlanDTO>getNotDetailedTrainingPlans(){
        return tpService.getNotDetailedTrainingPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDTO>getTrainingPlanById(@PathVariable Integer id){
        return ResponseEntity.ok(tpService.getTrainingPlanById(id));
    }

    //-----POST-----//

    @PostMapping
    public TrainingPlanDTO addTrainingPlan(@RequestBody TrainingPlanCreateDTO dto){
        return tpService.addTrainingPlan(dto);
    }
}
