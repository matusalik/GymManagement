package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Trainer.NotDetailedTrainerDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import com.gymmanagement.gym_management_api.Enums.Role;
import com.gymmanagement.gym_management_api.Services.TrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trainers")
@Tag(name = Tags.TrainersTag)
public class TrainerController {
    private final TrainerService trainerService;

    //-----GET-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public ResponseEntity<Iterable<TrainerDTO>>getTrainers(){
        return ResponseEntity.ok(trainerService.getTrainers());
    }

    @PreAuthorize(RoleClaims.AllClaim)
    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedTrainerDTO>>getNotDetailedTrainers(){
        return ResponseEntity.ok(trainerService.getNotDetailedTrainers());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO>getTrainerById(@PathVariable Integer id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/count")
    public ResponseEntity<Long>getTrainerCount(){
        return ResponseEntity.ok(trainerService.getTrainerCount());
    }

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @GetMapping("/bio/{trainer_id}")
    public ResponseEntity<String>getMyBio(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(trainerService.getMyBio(trainer_id));
    }

    //----POST----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void> addTrainer(@RequestBody TrainerCreateDTO dto){
        trainerService.addTrainer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
