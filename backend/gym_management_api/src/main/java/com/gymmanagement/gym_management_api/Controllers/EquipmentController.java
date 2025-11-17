package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.Mappers.EquipmentMapper;
import com.gymmanagement.gym_management_api.Services.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/equipment")
@Tag(name = Tags.EquipmentTag)
public class EquipmentController {
    private final EquipmentService equipmentService;

    //-----GET-----//

    @GetMapping
    public @ResponseBody Iterable<EquipmentDTO>getEquipment(){
        return equipmentService.getEquipment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO>getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    //----POST----//

    @PostMapping
    public EquipmentDTO addEquipment(@RequestBody EquipmentDTO dto){
        return equipmentService.addEquipment(dto);
    }

}