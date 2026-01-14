package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Specialization.NotDetailedSpecializationDTO;
import com.gymmanagement.gym_management_api.DTO.Specialization.SpecializationDTO;
import com.gymmanagement.gym_management_api.Services.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/specializations")
@Tag(name = Tags.SpecializationsTag)
public class SpecializationController {
    private final SpecializationService specializationService;

    //------GET------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public ResponseEntity<Iterable<SpecializationDTO>>getSpecializations(){
        return ResponseEntity.ok(specializationService.getSpecializations());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedSpecializationDTO>>getNotDetailedSpecializations(){
        return ResponseEntity.ok(specializationService.getNotDetailedSpecializations());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDTO>getSpecializationById(@PathVariable Integer id){
        return ResponseEntity.ok(specializationService.getSpecializationById(id));
    }

    //-----POST------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void>addSpecialization(@RequestBody SpecializationDTO dto){
        specializationService.addSpecialization(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
