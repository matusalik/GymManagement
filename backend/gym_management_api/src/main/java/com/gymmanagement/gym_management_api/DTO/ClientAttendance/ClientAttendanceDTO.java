package com.gymmanagement.gym_management_api.DTO.ClientAttendance;

import com.gymmanagement.gym_management_api.DTO.Client.NotDetailedClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientAttendanceDTO {
    private NotDetailedClientDTO client;
    private LocalDateTime date_time;
}
