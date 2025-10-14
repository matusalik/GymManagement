package com.gymmanagement.gym_management_api.DTO.Membership;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipDTO {
    public String name;
    public Double price;
    private Integer duration;
    private String description;
}
