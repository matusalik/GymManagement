package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistDTO;
import com.gymmanagement.gym_management_api.Entities.Receptionist;
import com.gymmanagement.gym_management_api.Mappers.ReceptionistMapper;
import com.gymmanagement.gym_management_api.Repositories.ReceptionistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceptionistService {
    private final ReceptionistRepository receptionistRepository;

    //-----GET-----//

    public ReceptionistDTO getReceptionistById(Integer id){
        Receptionist receptionist = receptionistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Receptionist with id: " + id + " not found."));
        return ReceptionistMapper.toDto(receptionist);
    }

    //-----POST-----//

    public void addReceptionist(ReceptionistCreateDTO dto){
        Receptionist receptionist = ReceptionistMapper.toEntity(dto);
        receptionistRepository.save(receptionist);
    }
}
