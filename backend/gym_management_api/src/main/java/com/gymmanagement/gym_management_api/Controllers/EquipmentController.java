package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Equipment.ChangeEquipmentConditionRequestDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.ChangeEquipmentLocationDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.EquipmentDTO;
import com.gymmanagement.gym_management_api.DTO.Equipment.NotDetailedEquipmentDTO;
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

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedEquipmentDTO>getNotDetailedEquipment(){
        return equipmentService.getNotDetailedEquipment();
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

    //----PATCH----//

    @PatchMapping("/condition")
    public ResponseEntity<Void>changeEquipmentCondition(@RequestBody ChangeEquipmentConditionRequestDTO requestDTO){
        try{
            equipmentService.changeEquipmentCondition(requestDTO.getCondition(), requestDTO.getEquipment_id());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/location")
    public ResponseEntity<Void>changeEquipmentLocation(@RequestBody ChangeEquipmentLocationDTO requestDTO){
        try{
            equipmentService.changeEquipmentLocation(requestDTO.getLocation(), requestDTO.getEquipment_id());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}