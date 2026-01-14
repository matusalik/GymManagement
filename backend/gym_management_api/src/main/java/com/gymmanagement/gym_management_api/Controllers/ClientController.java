package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Client.ChangeStatusRequest;
import com.gymmanagement.gym_management_api.DTO.Client.ClientCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDetailedDTO;
import com.gymmanagement.gym_management_api.Mappers.ClientMapper;
import com.gymmanagement.gym_management_api.Mappers.UserMapper;
import com.gymmanagement.gym_management_api.Services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
@Tag(name = Tags.ClientsTag)
public class ClientController {
    private final ClientService clientService;

    //-----GET------//

    @GetMapping
    public ResponseEntity<Iterable<ClientDTO>>getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDetailedDTO> getClientById(@PathVariable Integer id) {
        ClientDetailedDTO clientDto = clientService.getClientById(id);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getClientStatus(@PathVariable Integer id) {
        String status = clientService.getClientStatus(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/membership_name/{id}")
    public ResponseEntity<String> getClientMembershipName(@PathVariable Integer id) {
        String status = clientService.getClientMembershipName(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/training_goal_name/{id}")
    public ResponseEntity<String>getClientTrainingGoalName(@PathVariable Integer id){
        String training_goal = clientService.getClientTrainingGoalName(id);
        return ResponseEntity.ok(training_goal);
    }

    @GetMapping("/count")
    public ResponseEntity<Long>getClientCount(){
        return ResponseEntity.ok(clientService.getClientCount());
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double>getClientRevenue(){
        return ResponseEntity.ok(clientService.getClientRevenue());
    }

    //-----POST-----//

    @PostMapping
    public ResponseEntity<Void>addClient(@RequestBody ClientCreateDTO dto) {
        clientService.addClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //----PATCH----//

    @PatchMapping("/training_goal/{client_id}/{training_goal_id}")
    public ResponseEntity<Void>changeTrainingGoal(@PathVariable Integer client_id, @PathVariable Integer training_goal_id){
        clientService.changeTrainingGoal(client_id, training_goal_id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/status")
    public ResponseEntity<Void>changeStatus(@RequestBody ChangeStatusRequest request){
        clientService.changeStatus(request);
        return ResponseEntity.ok().build();
    }

    //----DELETE----//

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}