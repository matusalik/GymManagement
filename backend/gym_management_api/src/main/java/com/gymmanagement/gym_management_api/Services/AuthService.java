package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Security.AuthRequestDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;
import com.gymmanagement.gym_management_api.Security.JWT.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //-----POST-----//

    public Map<String, String> login(AuthRequestDTO requestDTO){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getUsername(),
                            requestDTO.getPassword()
                    )
            );
        } catch(Exception e){
            System.out.println("Auth failed: " + e.getMessage());
            throw new RuntimeException("Invalid username or password");
        }

        User user = userRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return Map.of("token", token);
    }
}
