package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceCreateDTO;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDTO;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDateDTO;
import com.gymmanagement.gym_management_api.Services.ClientAttendanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client_attendance")
@Tag(name = Tags.ClientAttendancesTag)
public class ClientAttendanceController {
    private final ClientAttendanceService clientAttendanceService;

    //-----GET-----//

    @GetMapping
    public ResponseEntity<Iterable<ClientAttendanceDTO>>getClientAttendances(){
        return ResponseEntity.ok(clientAttendanceService.getClientAttendances());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Iterable<ClientAttendanceDateDTO>>getClientAttendancesByClientId(@PathVariable Integer clientId){
        return ResponseEntity.ok(clientAttendanceService.getClientAttendancesByClientId(clientId));
    }

    @GetMapping("/count/{clientId}")
    public ResponseEntity<Long>getClientAttendanceCountByClientId(@PathVariable Integer clientId){
        return ResponseEntity.ok(clientAttendanceService.getClientAttendanceCountByClientId(clientId));
    }

    //----POST----//

    @PostMapping
    public ResponseEntity<ClientAttendanceDTO>addClientAttendance(@RequestBody ClientAttendanceCreateDTO dto){
        return ResponseEntity.ok(clientAttendanceService.addAttendance(dto));
    }
}
