package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Receptionist.ReceptionistDTO;
import com.gymmanagement.gym_management_api.Services.ReceptionistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receptionists")
@Tag(name = Tags.ReceptionistsTag)
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    //------GET------//

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionistDTO>getReceptionistById(@PathVariable Integer id){
        ReceptionistDTO receptionistDTO = receptionistService.getReceptionistById(id);
        return ResponseEntity.ok(receptionistDTO);
    }

    //-----POST-----//

    @PostMapping
    public ReceptionistDTO addReceptionist(@RequestBody ReceptionistCreateDTO dto){
        return receptionistService.addReceptionist(dto);
    }
}
