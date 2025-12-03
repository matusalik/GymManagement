package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassCreateDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NameDateGroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NotDetailedGroupClassDTO;
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

import java.time.LocalDateTime;
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

    public Iterable<NotDetailedGroupClassDTO>getNotDetailedGroupClasses(){
        return GroupClassMapper.listToNotDetailedDto(groupClassRepository.findAll());
    }

    public GroupClassDTO getGroupClassById(Integer id){
        GroupClass groupClass = groupClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group Class with id: " + id + " not found."));
        return GroupClassMapper.toDto(groupClass);
    }

    public Long getGroupClassCountByTrainerId(Integer trainer_id){
        return groupClassRepository.countByTrainer_UserId(trainer_id);
    }

    public Long getGroupClassCount(){
        return groupClassRepository.count();
    }

    public Iterable<NotDetailedGroupClassDTO>getNotDetailedGroupClassesByTrainerId(Integer trainer_id){
        return GroupClassMapper.listToNotDetailedDto(groupClassRepository.findAllByTrainer_UserId(trainer_id));
    }

    public Iterable<NotDetailedGroupClassDTO>getNotDetailedGroupClassesByClientId(Integer client_id){
        return GroupClassMapper.listToNotDetailedDto(groupClassRepository.findAllByClientId(client_id));
    }

    public Iterable<NotDetailedGroupClassDTO>getFutureNotDetailedGroupClassesByClientId(Integer client_id){
        return GroupClassMapper.listToNotDetailedDto(groupClassRepository.findAllFutureByClientId(client_id, LocalDateTime.now()));
    }

    public Iterable<NameDateGroupClassDTO>getAvailableToSignUp(Integer client_id){
        return GroupClassMapper.listToNameDateDto(groupClassRepository.findAllFutureWhereClientNotPresent(client_id, LocalDateTime.now()));
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

    public void addClientToGroupClass(Integer group_class_id, Integer client_id){
        GroupClass groupClass = groupClassRepository.findById(group_class_id)
                .orElseThrow(() -> new EntityNotFoundException("Group Class with id: " + group_class_id + " not found."));

        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + client_id + " not found."));

        if(!groupClass.getClient_list().contains(client)){
            groupClass.getClient_list().add(client);
            groupClassRepository.save(groupClass);
        }
    }
}
