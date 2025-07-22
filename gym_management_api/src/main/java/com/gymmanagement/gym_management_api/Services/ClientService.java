package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Client.ClientCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.Membership;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Exceptions.ResourceNotFoundException;
import com.gymmanagement.gym_management_api.Mappers.ClientMapper;
import com.gymmanagement.gym_management_api.Repositories.ClientRepository;
import com.gymmanagement.gym_management_api.Repositories.MembershipRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainingGoalRepository;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final TrainingGoalRepository trainingGoalRepository;
    private final MembershipRepository membershipRepository;

    //------GET------//

    public Iterable<ClientDTO>getClients(){
        return ClientMapper.listToDto(clientRepository.findAll());
    }

    //------POST------//

    public Client addClient(ClientCreateDTO dto){
        Client client = ClientMapper.toEntityWithoutRelations(dto);

        TrainingGoal tg = trainingGoalRepository.findById(dto.getTraining_goal_id())
                .orElseThrow(() -> new ResourceNotFoundException("Training goal not found"));

        client.setTraining_goal(tg);

        Membership m = membershipRepository.findById(dto.getMembership_id())
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found"));

        client.setMembership(m);

        return userRepository.save(client);
    }
}