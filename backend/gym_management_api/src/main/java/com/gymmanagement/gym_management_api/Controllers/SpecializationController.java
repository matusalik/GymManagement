package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Specialization.NotDetailedSpecializationDTO;
import com.gymmanagement.gym_management_api.DTO.Specialization.SpecializationDTO;
import com.gymmanagement.gym_management_api.Services.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/specializations")
@Tag(name = Tags.SpecializationsTag)
public class SpecializationController {
    private final SpecializationService specializationService;

    //------GET------//

    @GetMapping
    public @ResponseBody Iterable<SpecializationDTO>getSpecializations(){
        return specializationService.getSpecializations();
    }

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedSpecializationDTO>getNotDetailedSpecializations(){
        return specializationService.getNotDetailedSpecializations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDTO>getSpecializationById(@PathVariable Integer id){
        return ResponseEntity.ok(specializationService.getSpecializationById(id));
    }

    //-----POSt------//

    @PostMapping
    public SpecializationDTO addSpecialization(@RequestBody SpecializationDTO dto){
        return specializationService.addSpecialization(dto);
    }
}
