package com.gymmanagement.gym_management_api.DTO.Review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewCreateDTO {
    public Integer client_id;
    public Integer trainer_id;
    public Integer rating;
    public String comment;
    public LocalDate date;
}
