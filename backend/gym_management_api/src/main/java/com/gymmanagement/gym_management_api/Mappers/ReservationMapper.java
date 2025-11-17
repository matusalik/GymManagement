package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationDTO;
import com.gymmanagement.gym_management_api.Entities.*;
import com.gymmanagement.gym_management_api.Enums.ReservationStatus;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {
    public static ReservationDTO toDto(Reservation reservation){
        ReservationDTO dto = new ReservationDTO();
        dto.setClient(ClientMapper.toDto(reservation.getClient()));
        dto.setEquipment(EquipmentMapper.toDto(reservation.getEquipment()));
        dto.setGroup_class(GroupClassMapper.toDto(reservation.getGroup_class()));
        dto.setTrainer(TrainerMapper.toDto(reservation.getTrainer()));
        dto.setDate_time(reservation.getDate_time());
        dto.setReservation_status(reservation.getReservation_status().name());
        return dto;
    }

    public static Reservation toEntity(ReservationCreateDTO dto, Client client, Equipment equipment, GroupClass group_class, Trainer trainer){
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setEquipment(equipment);
        reservation.setGroup_class(group_class);
        reservation.setTrainer(trainer);
        reservation.setDate_time(dto.getDate_time());
        reservation.setReservation_status(ReservationStatus.valueOf(dto.getReservation_status().toUpperCase()));
        return reservation;
    }

    public static Iterable<ReservationDTO> listToDto(Iterable<Reservation>reservations){
        List<ReservationDTO>dtos = new ArrayList<>();
        for(Reservation i : reservations){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
