package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.User.UserCreateDTO;
import com.gymmanagement.gym_management_api.Entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public @ResponseBody Iterable<UserDTO>getUsers(){
        return userService.getUsers();
    }

    //----POST----//

    @PostMapping
    public User addUser(@RequestBody UserCreateDTO dto){
        return userService.addUser(dto);
    }

}
