package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipDTO;
import com.gymmanagement.gym_management_api.Services.MembershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memberships")
@Tag(name = Tags.MembershipsTag)
public class MembershipController {
    private final MembershipService membershipService;

    //------GET------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public ResponseEntity<Iterable<MembershipDTO>>getMemberships(){
        return ResponseEntity.ok(membershipService.getMemberships());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<MembershipDTO>getMembershipById(@PathVariable Integer id){
        return ResponseEntity.ok(membershipService.getMembershipById(id));
    }

    //------POST------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void>addMembership(@RequestBody MembershipCreateDTO dto){
        membershipService.addMembership(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
