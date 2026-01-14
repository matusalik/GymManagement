package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassCreateDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NameDateGroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NotDetailedGroupClassDTO;
import com.gymmanagement.gym_management_api.Services.GroupClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group_classes")
@Tag(name = Tags.GroupClassesTag)
public class GroupClassController {
    private final GroupClassService groupClassService;

    //----GET----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public ResponseEntity<Iterable<GroupClassDTO>>getGroupClasses(){
        return ResponseEntity.ok(groupClassService.getGroupClasses());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedGroupClassDTO>>getNotDetailedGroupClasses(){
        return ResponseEntity.ok(groupClassService.getNotDetailedGroupClasses());
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<GroupClassDTO>getGroupClassById(@PathVariable Integer id){
        return ResponseEntity.ok(groupClassService.getGroupClassById(id));
    }

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @GetMapping("/countByTrainer/{trainer_id}")
    public ResponseEntity<Long>getGroupClassCountByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(groupClassService.getGroupClassCountByTrainerId(trainer_id));
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/count")
    public ResponseEntity<Long>getGroupClassCount(){
        return ResponseEntity.ok(groupClassService.getGroupClassCount());
    }

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @GetMapping("/notdetailed/{trainer_id}")
    public ResponseEntity<Iterable<NotDetailedGroupClassDTO>>getNotDetailedGroupClassesByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(groupClassService.getNotDetailedGroupClassesByTrainerId(trainer_id));
    }

    @PreAuthorize(RoleClaims.ReceptionistClientClaim)
    @GetMapping("/notdetailed_by_client/{client_id}")
    public ResponseEntity<Iterable<NotDetailedGroupClassDTO>>getNotDetailedGroupClassesByClientId(@PathVariable Integer client_id){
        return ResponseEntity.ok(groupClassService.getNotDetailedGroupClassesByClientId(client_id));
    }

    @PreAuthorize(RoleClaims.ReceptionistClientClaim)
    @GetMapping("/notdetailed_future_by_client/{client_id}")
    public ResponseEntity<Iterable<NotDetailedGroupClassDTO>>getFutureNotDetailedGroupClassesByClientId(@PathVariable Integer client_id){
        return ResponseEntity.ok(groupClassService.getFutureNotDetailedGroupClassesByClientId(client_id));
    }

    @PreAuthorize(RoleClaims.ClientClaim)
    @GetMapping("available/{client_id}")
    public ResponseEntity<Iterable<NameDateGroupClassDTO>>getAvailableToSignUp(@PathVariable Integer client_id){
        return ResponseEntity.ok(groupClassService.getAvailableToSignUp(client_id));
    }

    //----POST----//

    @PreAuthorize(RoleClaims.ReceptionistTrainerClaim)
    @PostMapping
    public ResponseEntity<Void>addGroupClass(@RequestBody GroupClassCreateDTO dto){
        groupClassService.addGroupClass(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize(RoleClaims.ClientClaim)
    @PostMapping("/{group_class_id}/{client_id}")
    public ResponseEntity<Void>addClientToGroupClass(@PathVariable Integer group_class_id, @PathVariable Integer client_id){
        groupClassService.addClientToGroupClass(group_class_id, client_id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
