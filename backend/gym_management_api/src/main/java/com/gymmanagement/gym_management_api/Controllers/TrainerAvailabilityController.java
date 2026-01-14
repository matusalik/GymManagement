package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.*;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import com.gymmanagement.gym_management_api.Services.TrainerAvailabilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trainer_availability")
@Tag(name = Tags.TrainingAvailabilitiesTag)
public class TrainerAvailabilityController {
    private final TrainerAvailabilityService trainerAvailabilityService;

    //-----GET-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public @ResponseBody Iterable<TrainerAvailabilityDTO>getTrainingAvailabilities(){
        return trainerAvailabilityService.getTrainerAvailabilities();
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedTrainerAvailabilityDTO>getNotDetailedTrainingAvailabilities(){
        return trainerAvailabilityService.getNotDetailedTrainerAvailabilities();
    }

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @GetMapping("/notdetailed/{trainer_id}")
    public @ResponseBody Iterable<NotDetailedTrainerAvailabilityDTO>getNotDetailedTrainerAvailabilitiesByTrainerId(@PathVariable Integer trainer_id){
        return trainerAvailabilityService.getNotDetailedTrainerAvailabilitiesByTrainerId(trainer_id);
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<TrainerAvailabilityDTO>getTrainerAvailabilityById(@PathVariable Integer id){
        TrainerAvailabilityDTO dto = trainerAvailabilityService.getTrainerAvailabilityById(id);
        return ResponseEntity.ok(dto);
    }

    //-----POST-----//

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @PostMapping
    public ResponseEntity<Void> addTrainerAvailability(@RequestBody TrainerAvailabilityCreateDTO dto){
        trainerAvailabilityService.addTrainerAvailability(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //----PATCH----//

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @PatchMapping("/startEndTime")
    public ResponseEntity<Void>changeStartEndTime(@RequestBody ChangeStartEndTimeRequest request){
        trainerAvailabilityService.changeStartEndTime(request);
        return ResponseEntity.ok().build();
    }

    //----DELETE----//

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @DeleteMapping
    public ResponseEntity<Void>deleteTrainerAvailability(@RequestBody DeleteTrainerAvailabilityRequest request){
        trainerAvailabilityService.deleteTrainerAvailability(request);
        return ResponseEntity.noContent().build();
    }
}
