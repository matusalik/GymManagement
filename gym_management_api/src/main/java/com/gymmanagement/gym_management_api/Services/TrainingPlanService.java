package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingPlan.TrainingPlanDTO;
import com.gymmanagement.gym_management_api.Entities.Exercise;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Entities.TrainingPlan;
import com.gymmanagement.gym_management_api.Mappers.TrainingGoalMapper;
import com.gymmanagement.gym_management_api.Mappers.TrainingPlanMapper;
import com.gymmanagement.gym_management_api.Repositories.ExerciseRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainingGoalRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainingPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingPlanService {
    private final TrainingPlanRepository tpRepository;
    private final TrainingGoalRepository tgRepository;
    private final ExerciseRepository exerciseRepository;

    //-----GET------//

    public Iterable<TrainingPlanDTO>getTrainingPlans(){
        return TrainingPlanMapper.listToDto(tpRepository.findAll());
    }

    public TrainingPlanDTO getTrainingPlanById(Integer id){
        TrainingPlan tp = tpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training Plan with id: " + id + " not found."));
        return TrainingPlanMapper.toDto(tp);
    }

    //----POST----//

    public TrainingPlanDTO addTrainingPlan(TrainingPlanCreateDTO dto){
        Integer tgId = dto.getTraining_goal_id();
        TrainingGoal tg = tgRepository.findById(tgId)
                .orElseThrow(() -> new EntityNotFoundException("TrainingGoal with id: " + tgId + " not found."));
        List<Exercise> exercises = exerciseRepository.findAllById(dto.getExercises_ids());
        TrainingPlan tp = TrainingPlanMapper.toEntity(dto, tg, exercises);
        return TrainingPlanMapper.toDto(tpRepository.save(tp));
    }
}
