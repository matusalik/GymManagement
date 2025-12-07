package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.NotDetailedTrainerAvailabilityDTO;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityDTO;
import com.gymmanagement.gym_management_api.Services.TrainerAvailabilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trainer_availability")
@Tag(name = Tags.TrainingAvailabilitiesTag)
public class TrainerAvailabilityController {
    private final TrainerAvailabilityService trainerAvailabilityService;

    //-----GET-----//

    @GetMapping
    public @ResponseBody Iterable<TrainerAvailabilityDTO>getTrainingAvailabilities(){
        return trainerAvailabilityService.getTrainerAvailabilities();
    }

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedTrainerAvailabilityDTO>getNotDetailedTrainingAvailabilities(){
        return trainerAvailabilityService.getNotDetailedTrainerAvailabilities();
    }

    @GetMapping("/notdetailed/{trainer_id}")
    public @ResponseBody Iterable<NotDetailedTrainerAvailabilityDTO>getNotDetailedTrainerAvailabilitiesByTrainerId(@PathVariable Integer trainer_id){
        return trainerAvailabilityService.getNotDetailedTrainerAvailabilitiesByTrainerId(trainer_id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerAvailabilityDTO>getTrainerAvailabilityById(@PathVariable Integer id){
        return ResponseEntity.ok(trainerAvailabilityService.getTrainerAvailabilityById(id));
    }

    //-----POST-----//

    @PostMapping
    public ResponseEntity<String> addTrainerAvailability(@RequestBody TrainerAvailabilityCreateDTO dto){
        try{
            trainerAvailabilityService.addTrainerAvailability(dto);
            return ResponseEntity.ok().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
