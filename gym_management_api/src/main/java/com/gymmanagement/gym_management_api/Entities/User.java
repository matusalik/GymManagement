package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.UserType;

import com.gymmanagement.gym_management_api.Security.Password;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    @Column(nullable = false)
    private Password password;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserType user_type;

    public boolean checkPassword(String input){
        return password.matches(input);
    }

    public String getPasswordHash(){
        return password.getHashed();
    }
}
