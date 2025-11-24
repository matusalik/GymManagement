package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import com.gymmanagement.gym_management_api.Entities.Specialization;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Entities.TrainerAvailability;
import com.gymmanagement.gym_management_api.Mappers.TrainerMapper;
import com.gymmanagement.gym_management_api.Repositories.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    //------GET------//

    public Iterable<TrainerDTO>getTrainers(){
        return TrainerMapper.listToDto(trainerRepository.findAll());
    }

    public TrainerDTO getTrainerById(Integer id){
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id: " + id + " not found."));
        return TrainerMapper.toDto(trainer);
    }

    public Long getTrainerCount(){
        return trainerRepository.count();
    }

    public String getMyBio(Integer id){
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id: " + id + " not found."));
        return trainer.getBio();
    }


    //------POST-----//

    public TrainerDTO addTrainer(TrainerCreateDTO dto){
        Trainer trainer = TrainerMapper.toEntityWithoutRelations(dto);

        List<Specialization> s = new ArrayList<>();
        trainer.setSpecializations(s);

        List<TrainerAvailability> a = new ArrayList<>();
        trainer.setAvailability(a);

        return TrainerMapper.toDto(trainerRepository.save(trainer));
    }
}
