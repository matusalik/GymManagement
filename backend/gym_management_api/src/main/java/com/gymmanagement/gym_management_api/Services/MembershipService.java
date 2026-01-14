package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipDTO;
import com.gymmanagement.gym_management_api.Entities.Membership;
import com.gymmanagement.gym_management_api.Mappers.MembershipMapper;
import com.gymmanagement.gym_management_api.Repositories.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MembershipRepository membershipRepository;

    //-------GET------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    public Iterable<MembershipDTO>getMemberships(){
        return MembershipMapper.listToDto(membershipRepository.findAll());
    }

    public MembershipDTO getMembershipById(Integer id){
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membership with id: " + id + " not found."));
        return MembershipMapper.toDto(membership);
    }

    //-----POST-----//

    public void addMembership(MembershipCreateDTO dto){
        Membership membership = MembershipMapper.toEntity(dto);
        membershipRepository.save(membership);
    }
}
