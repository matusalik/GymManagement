package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Client.ClientCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.Mappers.ClientMapper;
import com.gymmanagement.gym_management_api.Mappers.UserMapper;
import com.gymmanagement.gym_management_api.Services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
@Tag(name = Tags.ClientsTag)
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public @ResponseBody Iterable<ClientDTO>getClients(){
        return clientService.getClients();
    }

    @PostMapping
    public ClientDTO addClient(@RequestBody ClientCreateDTO dto){
        return ClientMapper.toDto(clientService.addClient(dto));
    }
}
