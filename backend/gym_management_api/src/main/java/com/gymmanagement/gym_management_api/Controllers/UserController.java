package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Security.ChangePasswordRequestDTO;
import com.gymmanagement.gym_management_api.DTO.User.NotDetailedUserDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDetailedDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management_api.DTO.User.UserDTO;
import com.gymmanagement.gym_management_api.Services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = Tags.UsersTag)
public class UserController {
    private final UserService userService;

    //-----GET-----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedDTO> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize(RoleClaims.AllClaim)
    @GetMapping("/notdetailed/{id}")
    public ResponseEntity<NotDetailedUserDTO>getNotDetailedUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getNotDetailedUserById(id));
    }

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @GetMapping
    public ResponseEntity<Iterable<UserDTO>>getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    //----PATCH----//

    @PreAuthorize(RoleClaims.ReceptionistClaim)
    @PatchMapping("/password/{id}")
    public ResponseEntity<Void>changeUserPassword(@PathVariable Integer id, @RequestBody ChangePasswordRequestDTO dto){
        userService.changeUserPassword(id, dto);
        return ResponseEntity.ok().build();
    }

    //-----PUT-----//

    @PreAuthorize(RoleClaims.AllClaim)
    @PutMapping("/{id}")
    public ResponseEntity<Void>updateUser(@PathVariable Integer id, @RequestBody NotDetailedUserDTO dto){
        userService.updateUser(id, dto);
        return ResponseEntity.ok().build();
    }
}
