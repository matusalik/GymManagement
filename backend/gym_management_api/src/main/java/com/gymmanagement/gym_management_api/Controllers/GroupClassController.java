package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassCreateDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.GroupClassDTO;
import com.gymmanagement.gym_management_api.DTO.GroupClass.NotDetailedGroupClassDTO;
import com.gymmanagement.gym_management_api.Services.GroupClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group_classes")
@Tag(name = Tags.GroupClassesTag)
public class GroupClassController {
    private final GroupClassService groupClassService;

    //----GET----//

    @GetMapping
    public @ResponseBody Iterable<GroupClassDTO>getGroupClasses(){
        return groupClassService.getGroupClasses();
    }

    @GetMapping("/notdetailed")
    public @ResponseBody Iterable<NotDetailedGroupClassDTO>getNotDetailedGroupClasses(){
        return groupClassService.getNotDetailedGroupClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupClassDTO>getGroupClassById(@PathVariable Integer id){
        return ResponseEntity.ok(groupClassService.getGroupClassById(id));
    }

    @GetMapping("/countByTrainer/{trainer_id}")
    public ResponseEntity<Long>getGroupClassCountByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(groupClassService.getGroupClassCountByTrainerId(trainer_id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long>getGroupClassCount(){
        return ResponseEntity.ok(groupClassService.getGroupClassCount());
    }

    //----POST----//

    @PostMapping
    public GroupClassDTO addGroupClass(@RequestBody GroupClassCreateDTO dto){
        return groupClassService.addGroupClass(dto);
    }
}
