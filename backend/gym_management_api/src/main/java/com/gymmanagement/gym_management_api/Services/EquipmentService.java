package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.NotDetailedEquipmentDTO;
import com.gymmanagement.gym_management_api.Entities.Equipment;
import com.gymmanagement.gym_management_api.Enums.EquipmentCondition;
import com.gymmanagement.gym_management_api.Enums.EquipmentLocation;
import com.gymmanagement.gym_management_api.Mappers.EquipmentMapper;
import com.gymmanagement.gym_management_api.Repositories.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    //-----GET-----//

    public Iterable<EquipmentDTO>getEquipment(){
        return EquipmentMapper.listToDto(equipmentRepository.findAll());
    }

    public Iterable<NotDetailedEquipmentDTO>getNotDetailedEquipment(){
        return EquipmentMapper.listToNotDetailedDto(equipmentRepository.findAll());
    }

    public EquipmentDTO getEquipmentById(Integer id){
        Equipment eq = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment with id: " + id + " not found."));
        return EquipmentMapper.toDto(eq);
    }

    //-----POST-----//

    public EquipmentDTO addEquipment(EquipmentDTO dto){
        Equipment eq = EquipmentMapper.toEntity(dto);
        return EquipmentMapper.toDto(equipmentRepository.save(eq));
    }

    //----PATCH----//

    public void changeEquipmentCondition(String condition, Integer equipment_id){
        Equipment eq = equipmentRepository.findById(equipment_id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment with id: " + equipment_id + " not found."));
        eq.setEquipment_condition(EquipmentCondition.valueOf(condition.toUpperCase()));
        equipmentRepository.save(eq);
    }

    public void changeEquipmentLocation(String location, Integer equipment_id){
        Equipment eq = equipmentRepository.findById(equipment_id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment with id: " + equipment_id + " not found."));
        eq.setEquipment_location(EquipmentLocation.valueOf(location.toUpperCase()));
        equipmentRepository.save(eq);
    }
}
