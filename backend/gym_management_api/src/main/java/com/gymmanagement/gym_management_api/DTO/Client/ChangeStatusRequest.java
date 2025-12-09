package com.gymmanagement.gym_management_api.DTO.Client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusRequest {
    private Integer client_id;
    private String status;
}
