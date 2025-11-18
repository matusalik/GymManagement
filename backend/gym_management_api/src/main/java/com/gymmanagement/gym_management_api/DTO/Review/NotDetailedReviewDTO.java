package com.gymmanagement.gym_management_api.DTO.Review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotDetailedReviewDTO {
    public String client_name;
    public String client_surname;
    public String trainer_name;
    public String trainer_surname;
    public Integer Rating;
    public String comment;
    public LocalDate date;
}
