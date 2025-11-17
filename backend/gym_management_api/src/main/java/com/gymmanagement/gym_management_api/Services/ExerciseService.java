package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Exercise.ExerciseDTO;
import com.gymmanagement.gym_management_api.Entities.Equipment;
import com.gymmanagement.gym_management_api.Entities.Exercise;
import com.gymmanagement.gym_management_api.Mappers.EquipmentMapper;
import com.gymmanagement.gym_management_api.Mappers.ExerciseMapper;
import com.gymmanagement.gym_management_api.Repositories.EquipmentRepository;
import com.gymmanagement.gym_management_api.Repositories.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final EquipmentRepository equipmentRepository;

    //------GET------//

    public Iterable<ExerciseDTO>getExercises(){
        return ExerciseMapper.listToDto(exerciseRepository.findAll());
    }

    public ExerciseDTO getExerciseById(Integer id){
        Exercise ex = exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id: " + id + " not found."));
        return ExerciseMapper.toDto(ex);
    }

    //-----POST-----//

    public ExerciseDTO addExercise(ExerciseCreateDTO dto){
        Equipment eq = equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id: " + dto.getEquipmentId() + " not found."));
        Exercise ex = ExerciseMapper.toEntity(dto, eq);
        return ExerciseMapper.toDto(exerciseRepository.save(ex));
    }
}
