package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseDTO;
import com.gymmanagement.gym_management_api.Services.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public @ResponseBody Iterable<ExerciseDTO>getExercises(){
        return exerciseService.getExercises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO>getExerciseById(@PathVariable Integer id){
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    //-----POST-----//

    @PostMapping
    public ExerciseDTO addExercise(@RequestBody ExerciseCreateDTO dto){
        return exerciseService.addExercise(dto);
    }
}
