package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityCreateDTO;
import com.gymmanagement.gym_management_api.DTO.TrainerAvailability.TrainerAvailabilityDTO;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import com.gymmanagement.gym_management_api.Mappers.TrainerAvailabilityMapper;
import com.gymmanagement.gym_management_api.Repositories.TrainerAvailabilityRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerAvailabilityService {
    private final TrainerAvailabilityRepository trainerAvailabilityRepository;
    private final TrainerRepository trainerRepository;

    //-----GET-----//

    public Iterable<TrainerAvailabilityDTO>getTrainerAvailabilities(){
        return TrainerAvailabilityMapper.listToDto(trainerAvailabilityRepository.findAll());
    }

    public TrainerAvailabilityDTO getTrainerAvailabilityById(Integer id){
        TrainerAvailability trainerAvailability = trainerAvailabilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer Availability with id: " + id + " not found."));
        return TrainerAvailabilityMapper.toDto(trainerAvailability);
    }

    //-----POST-----//

    public TrainerAvailabilityDTO addTrainerAvailability(TrainerAvailabilityCreateDTO dto){
        Integer trainer_id = dto.getTrainer_id();
        Trainer trainer = trainerRepository.findById(trainer_id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id: " + trainer_id + " not found."));

        TrainerAvailability trainerAvailability = TrainerAvailabilityMapper.toEntity(dto, trainer);
        return TrainerAvailabilityMapper.toDto(trainerAvailability);
    }
}
