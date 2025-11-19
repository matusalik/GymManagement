package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Membership.MembershipDTO;
import com.gymmanagement.gym_management_api.Services.MembershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping
    public @ResponseBody Iterable<MembershipDTO>getMemberships(){
        return membershipService.getMemberships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipDTO>getMembershipById(@PathVariable Integer id){
        return ResponseEntity.ok(membershipService.getMembershipById(id));
    }

    //------POST------//

    @PostMapping
    public MembershipDTO addMembership(@RequestBody MembershipCreateDTO dto){
        return membershipService.addMembership(dto);
    }
}
