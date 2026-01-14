package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.RoleClaims;
import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Security.AuthRequestDTO;
import com.gymmanagement.gym_management_api.DTO.Security.ChangePasswordRequestDTO;
import com.gymmanagement.gym_management_api.Services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = Tags.AuthenticationTag)
public class AuthController {
    private final AuthService authService;

    //-----POST-----//

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(authService.login(authRequestDTO));
    }

    //----PATCH----//

    @PreAuthorize(RoleClaims.AllClaim)
    @PatchMapping("change_password")
    public ResponseEntity<Void>changePassword(@RequestBody ChangePasswordRequestDTO requestDTO){
        authService.changePassword(requestDTO);
        return ResponseEntity.ok().build();
    }
}
