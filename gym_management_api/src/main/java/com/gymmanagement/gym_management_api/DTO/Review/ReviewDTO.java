package com.gymmanagement.gym_management_api.DTO.Review;

import com.gymmanagement.gym_management_api.DTO.Client.ClientDTO;
import com.gymmanagement.gym_management_api.DTO.Trainer.TrainerDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDTO {
    public ClientDTO client;
    public TrainerDTO trainer;
    public Integer rating;
    public String comment;
    public LocalDate date;
}
