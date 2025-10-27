package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassCreateDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.GroupClass;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Mappers.GroupClassMapper;
import com.gymmanagement.gym_management_api.Repositories.ClientRepository;
import com.gymmanagement.gym_management_api.Repositories.GroupClassRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupClassService {
    private final GroupClassRepository groupClassRepository;
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;

    //-----GET-----//

    public Iterable<GroupClassDTO>getGroupClasses(){
        return GroupClassMapper.listToDto(groupClassRepository.findAll());
    }

    public GroupClassDTO getGroupClassById(Integer id){
        GroupClass groupClass = groupClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group Class with id: " + id + " not found."));
        return GroupClassMapper.toDto(groupClass);
    }

    //----POST----//

    public GroupClassDTO addGroupClass(GroupClassCreateDTO dto){
        Integer trainer_id = dto.getTrainer_id();
        Trainer trainer = trainerRepository.findById(trainer_id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id: " + trainer_id + " not found."));

        List<Client> clients = clientRepository.findAllById(dto.getClients_ids());

        GroupClass groupClass = GroupClassMapper.toEntity(dto, trainer, clients);
        return GroupClassMapper.toDto(groupClassRepository.save(groupClass));
    }
}
