package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.User.NotDetailedUserDTO;
import com.gymmanagement.gym_management_api.DTO.User.UserDetailedDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import com.gymmanagement.gym_management_api.Repositories.UserRepository;
import com.gymmanagement.gym_management_api.Security.Password;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void shouldReturnDetailedUserIfExists(){
        User user = new User();
        user.setUserId(1);
        user.setFirst_name("Anna");
        user.setPassword(Password.ofRaw("TESTTESTTEST"));

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        UserDetailedDTO res = userService.getUserById(1);

        assertEquals("Anna", res.getFirst_name());
    }

    @Test
    void shouldReturnNotDetailedUserIfExists(){
        User user = new User();
        user.setUserId(1);
        user.setFirst_name("Anna");
        user.setPassword(Password.ofRaw("TESTTESTTEST"));

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        NotDetailedUserDTO res = userService.getNotDetailedUserById(1);

        assertEquals("Anna", res.getFirst_name());
    }

    @Test
    void shouldThrowWhenUserNotFound(){
        when(userRepository.findById(1))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    void shouldThrowWhenNotDetailedUserNotFound(){
        when(userRepository.findById(1))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getNotDetailedUserById(1));
    }
}
