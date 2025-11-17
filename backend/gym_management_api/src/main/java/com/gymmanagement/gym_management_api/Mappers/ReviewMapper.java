package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Review.ReviewCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.Review;
import com.gymmanagement.gym_management_api.Entities.Trainer;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
    public static ReviewDTO toDto(Review review){
        ReviewDTO dto = new ReviewDTO();
        dto.setClient(ClientMapper.toDto(review.getClient()));
        dto.setTrainer(TrainerMapper.toDto(review.getTrainer()));
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setDate(review.getDate());
        return dto;
    }

    public static Review toEntity(ReviewCreateDTO dto, Client client, Trainer trainer){
        Review review = new Review();
        review.setClient(client);
        review.setTrainer(trainer);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setDate(dto.getDate());
        return review;
    }

    public static Iterable<ReviewDTO>listToDto(Iterable<Review>reviews){
        List<ReviewDTO>dtos = new ArrayList<>();
        for(Review i : reviews){
            dtos.add(toDto(i));
        }
        return dtos;
    }
}
