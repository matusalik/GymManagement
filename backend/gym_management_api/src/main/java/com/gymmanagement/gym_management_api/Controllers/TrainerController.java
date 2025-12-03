package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Trainer.NotDetailedTrainerDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import com.gymmanagement.gym_management_api.Services.TrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trainers")
@Tag(name = Tags.TrainersTag)
public class TrainerController {
    private final TrainerService trainerService;

    //-----GET-----//

    @GetMapping
    public @ResponseBody Iterable<TrainerDTO>getTrainers(){
        return trainerService.getTrainers();
    }

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedTrainerDTO>getNotDetailedTrainers(){
        return trainerService.getNotDetailedTrainers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO>getTrainerById(@PathVariable Integer id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long>getTrainerCount(){
        return ResponseEntity.ok(trainerService.getTrainerCount());
    }

    @GetMapping("/bio/{trainer_id}")
    public ResponseEntity<String>getMyBio(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(trainerService.getMyBio(trainer_id));
    }

    //----POST----//

    @PostMapping
    public TrainerDTO addTrainer(@RequestBody TrainerCreateDTO dto){
        return trainerService.addTrainer(dto);
    }
}
