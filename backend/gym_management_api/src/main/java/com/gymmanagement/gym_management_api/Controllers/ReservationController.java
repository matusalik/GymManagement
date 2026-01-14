package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Reservation.ReservationDTO;
import com.gymmanagement.gym_management_api.Services.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
@Tag(name = Tags.ReservationsTag)
public class ReservationController {
    private final ReservationService reservationService;

    //-----GET-----//

    @GetMapping
    public ResponseEntity<Iterable<ReservationDTO>>getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO>getReservationById(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    //-----POST-----//

    @PostMapping
    public ResponseEntity<Void>addReservation(@RequestBody ReservationCreateDTO dto){
        reservationService.addReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}