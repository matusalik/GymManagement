package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseDTO;
import com.gymmanagement.gym_management_api.Services.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exercises")
@Tag(name = Tags.ExercisesTag)
public class ExerciseController {
    private final ExerciseService exerciseService;

    //-----GET-----//

    @GetMapping
    public ResponseEntity<Iterable<ExerciseDTO>>getExercises(){
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO>getExerciseById(@PathVariable Integer id){
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    //-----POST-----//

    @PostMapping
    public ResponseEntity<Void>addExercise(@RequestBody ExerciseCreateDTO dto){
        exerciseService.addExercise(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
