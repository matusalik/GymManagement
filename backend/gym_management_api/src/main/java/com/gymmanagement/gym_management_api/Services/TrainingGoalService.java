package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainingGoal.TrainingGoalDTO;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Mappers.TrainingGoalMapper;
import com.gymmanagement.gym_management_api.Repositories.TrainingGoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class TrainingGoalService {
    private final TrainingGoalRepository tgRepository;

    //-----GET------//

    public Iterable<TrainingGoalDTO>getTrainingGoals(){
        return TrainingGoalMapper.listToDto(tgRepository.findAll());
    }

    public TrainingGoalDTO getTrainingGoalById(@PathVariable Integer id){
        TrainingGoal tg = tgRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TrainingGoal with id: " + id + " not found."));
        return TrainingGoalMapper.toDto(tg);
    }

    //-----POST-----//

    public TrainingGoalDTO addTrainingGoal(TrainingGoalCreateDTO dto){
        TrainingGoal tg = TrainingGoalMapper.toEntity(dto);
        return TrainingGoalMapper.toDto(tgRepository.save(tg));
    }
}
