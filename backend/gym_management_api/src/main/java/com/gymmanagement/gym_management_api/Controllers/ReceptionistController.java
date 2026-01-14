package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistDTO;
import com.gymmanagement.gym_management_api.Services.ReceptionistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receptionists")
@Tag(name = Tags.ReceptionistsTag)
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    //------GET------//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<ReceptionistDTO>getReceptionistById(@PathVariable Integer id){
        ReceptionistDTO receptionistDTO = receptionistService.getReceptionistById(id);
        return ResponseEntity.ok(receptionistDTO);
    }

    //-----POST-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void> addReceptionist(@RequestBody ReceptionistCreateDTO dto){
        receptionistService.addReceptionist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
