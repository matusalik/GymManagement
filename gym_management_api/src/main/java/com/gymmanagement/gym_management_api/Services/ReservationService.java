package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationDTO;
import com.gymmanagement.gym_management_api.Entities.*;
import com.gymmanagement.gym_management_api.Mappers.ReservationMapper;
import com.gymmanagement.gym_management_api.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final EquipmentRepository equipmentRepository;
    private final GroupClassRepository groupClassRepository;
    private final TrainerRepository trainerRepository;

    //------GET------//

    public Iterable<ReservationDTO>getReservations(){
        return ReservationMapper.listToDto(reservationRepository.findAll());
    }

    public ReservationDTO getReservationById(Integer id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with id: " + id + " not found."));
        return ReservationMapper.toDto(reservation);
    }

    //-----POST-----//

    public ReservationDTO addReservation(ReservationCreateDTO dto){
        Integer client_id = dto.getClient_id();

        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + client_id + " not found."));

        Integer equipment_id = dto.getEquipment_id();

        Equipment equipment = equipmentRepository.findById(equipment_id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment with id: " + equipment_id + " not found."));

        Integer group_class_id = dto.getGroup_class_id();

        GroupClass group_class = groupClassRepository.findById(group_class_id)
                .orElseThrow(() -> new EntityNotFoundException("Group Class with id: " + group_class_id + " not found."));

        Integer trainer_id = dto.getTrainer_id();

        Trainer trainer = trainerRepository.findById(trainer_id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id: " + trainer_id + " not found."));

        Reservation reservation = ReservationMapper.toEntity(dto, client, equipment, group_class, trainer);
        return ReservationMapper.toDto(reservationRepository.save(reservation));
    }
}












