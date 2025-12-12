package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Specialization.NotDetailedSpecializationDTO;
import com.gymmanagement.gym_management_api.DTO.Specialization.SpecializationDTO;
import com.gymmanagement.gym_management_api.Entities.Specialization;
import com.gymmanagement.gym_management_api.Mappers.SpecializationMapper;
import com.gymmanagement.gym_management_api.Repositories.SpecializationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    //------GET------//

    public Iterable<SpecializationDTO>getSpecializations(){
        return SpecializationMapper.listToDto(specializationRepository.findAll());
    }

    public Iterable<NotDetailedSpecializationDTO> getNotDetailedSpecializations(){
        return SpecializationMapper.listToNotDetailedDto(specializationRepository.findAll());
    }

    public SpecializationDTO getSpecializationById(Integer id){
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membership with id: " + id + " not found."));
        return SpecializationMapper.toDto(specialization);
    }

    //-----POST-----//

    public SpecializationDTO addSpecialization(SpecializationDTO dto){
        Specialization specialization = SpecializationMapper.toEntity(dto);
        return SpecializationMapper.toDto(specializationRepository.save(specialization));
    }
}
