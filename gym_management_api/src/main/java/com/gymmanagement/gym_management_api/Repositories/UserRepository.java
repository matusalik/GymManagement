package com.gymmanagement.gym_management_api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymmanagement.gym_management_api.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
