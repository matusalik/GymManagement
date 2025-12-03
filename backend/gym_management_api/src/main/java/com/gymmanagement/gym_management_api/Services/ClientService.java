package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Client.ClientCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDetailedDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.Membership;
import com.gymmanagement.gym_management_api.Entities.TrainingGoal;
import com.gymmanagement.gym_management_api.Exceptions.ResourceNotFoundException;
import com.gymmanagement.gym_management_api.Mappers.ClientMapper;
import com.gymmanagement.gym_management_api.Mappers.UserMapper;
import com.gymmanagement.gym_management_api.Repositories.ClientRepository;
import com.gymmanagement.gym_management_api.Repositories.MembershipRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainingGoalRepository;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public ClientDetailedDTO getClientById(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
        return ClientMapper.toDetailedDto(client);
    }

    public String getClientStatus(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
        return client.getStatus().name();
    }

    public String getClientMembershipName(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
        return client.getMembership().getName();
    }

    public String getClientTrainingGoalName(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
        return client.getTraining_goal().getName();
    }

    public Long getClientCount(){
        return clientRepository.count();
    }

    public Double getClientRevenue(){
        Double sum = 0D;
        for(Client i : clientRepository.findAll()){
            sum += i.getMembership().getPrice();
        }
        return sum;
    }

    //------POST------//

    public ClientDTO addClient(ClientCreateDTO dto){
        Client client = ClientMapper.toEntityWithoutRelations(dto);

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already in use");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }


        TrainingGoal tg = trainingGoalRepository.findById(dto.getTraining_goal_id())
                .orElseThrow(() -> new ResourceNotFoundException("Training goal not found"));

        client.setTraining_goal(tg);

        Membership m = membershipRepository.findById(dto.getMembership_id())
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found"));

        client.setMembership(m);

        return ClientMapper.toDto(userRepository.save(client));
    }

    //----DELETE----//

    public void deleteClient(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found."));
        clientRepository.delete(client);
    }
}