package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceCreateDTO;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDTO;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDateDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.ClientAttendance;
import com.gymmanagement.gym_management_api.Mappers.ClientAttendanceMapper;
import com.gymmanagement.gym_management_api.Repositories.ClientAttendanceRepository;
import com.gymmanagement.gym_management_api.Repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientAttendanceService {
    private final ClientAttendanceRepository clientAttendanceRepository;
    private final ClientRepository clientRepository;

    //------GET------//

    public Iterable<ClientAttendanceDTO>getClientAttendances(){
        return ClientAttendanceMapper.listToDto(clientAttendanceRepository.findAll());
    }

    public Iterable<ClientAttendanceDateDTO>getClientAttendancesByClientId(Integer client_id){
        return ClientAttendanceMapper.listToDateDto(clientAttendanceRepository.findAllByClient_UserId(client_id));
    }

    public Long getClientAttendanceCountByClientId(Integer clientId){
        return clientAttendanceRepository.countByClient_UserId(clientId);
    }

    //-----POST-----//

    public ClientAttendanceDTO addAttendance(ClientAttendanceCreateDTO dto){
        Integer client_id = dto.getClient_id();
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + client_id + " not found."));

        ClientAttendance clientAttendance = ClientAttendanceMapper.toEntity(client, dto.getDate_time());

        return ClientAttendanceMapper.toDto(clientAttendanceRepository.save(clientAttendance));
    }

}
