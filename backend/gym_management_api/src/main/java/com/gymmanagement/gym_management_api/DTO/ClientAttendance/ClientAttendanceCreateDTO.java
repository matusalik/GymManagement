package com.gymmanagement.gym_management_api.DTO.ClientAttendance;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientAttendanceCreateDTO {
    private Integer client_id;
    private LocalDateTime date_time;
}
