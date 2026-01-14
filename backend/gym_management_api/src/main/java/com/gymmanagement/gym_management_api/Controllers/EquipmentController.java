package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Equipment.ChangeEquipmentConditionRequestDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.ChangeEquipmentLocationDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.NotDetailedEquipmentDTO;
import com.gymmanagement.gym_management_api.Mappers.EquipmentMapper;
import com.gymmanagement.gym_management_api.Services.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/equipment")
@Tag(name = Tags.EquipmentTag)
public class EquipmentController {
    private final EquipmentService equipmentService;

    //-----GET-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public @ResponseBody Iterable<EquipmentDTO>getEquipment(){
        return equipmentService.getEquipment();
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedEquipmentDTO>>getNotDetailedEquipment(){
        return ResponseEntity.ok(equipmentService.getNotDetailedEquipment());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO>getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    //----POST----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PostMapping
    public ResponseEntity<Void>addEquipment(@RequestBody EquipmentDTO dto){
        equipmentService.addEquipment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //----PATCH----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PatchMapping("/condition")
    public ResponseEntity<Void>changeEquipmentCondition(@RequestBody ChangeEquipmentConditionRequestDTO requestDTO){
        equipmentService.changeEquipmentCondition(requestDTO.getCondition(), requestDTO.getEquipment_id());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PatchMapping("/location")
    public ResponseEntity<Void>changeEquipmentLocation(@RequestBody ChangeEquipmentLocationDTO requestDTO){
        equipmentService.changeEquipmentLocation(requestDTO.getLocation(), requestDTO.getEquipment_id());
        return ResponseEntity.ok().build();
    }
}