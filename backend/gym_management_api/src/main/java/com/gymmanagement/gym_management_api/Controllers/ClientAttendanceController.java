package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceCreateDTO;
import com.gymmanagement.gym_management_api.DTO.ClientAttendance.ClientAttendanceDTO;
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
    public @ResponseBody Iterable<ClientAttendanceDTO>getClientAttendances(){
        return clientAttendanceService.getClientAttendances();
    }

    @GetMapping("/count/{clientId}")
    public ResponseEntity<Long>getClientAttendanceCountByClientId(@PathVariable Integer clientId){
        return ResponseEntity.ok(clientAttendanceService.getClientAttendanceCountByClientId(clientId));
    }

    //----POST----//

    @PostMapping
    public ClientAttendanceDTO addClientAttendance(@RequestBody ClientAttendanceCreateDTO dto){
        return clientAttendanceService.addAttendance(dto);
    }
}
