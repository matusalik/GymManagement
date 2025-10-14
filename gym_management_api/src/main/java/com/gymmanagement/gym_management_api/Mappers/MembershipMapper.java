package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Membership.MembershipDTO;
import com.gymmanagement.gym_management_api.Entities.Membership;

import java.util.ArrayList;
import java.util.List;

public class MembershipMapper {
    public static MembershipDTO toDto(Membership membership){
        MembershipDTO dto = new MembershipDTO();
        dto.setName(membership.getName());
        dto.setPrice(membership.getPrice());
        dto.setDuration(membership.getDuration());
        dto.setDescription(membership.getDescription());
        return dto;
    }

    public static Membership toEntity(MembershipDTO dto){
        Membership membership = new Membership();
        membership.setName(dto.getName());
        membership.setPrice(dto.getPrice());
        membership.setDuration(dto.getDuration());
        membership.setDescription(dto.getDescription());
        return membership;
    }

    public static Iterable<MembershipDTO>listToDto(Iterable<Membership>memberships){
        List<MembershipDTO>dtos = new ArrayList<>();
        for(Membership i : memberships){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
