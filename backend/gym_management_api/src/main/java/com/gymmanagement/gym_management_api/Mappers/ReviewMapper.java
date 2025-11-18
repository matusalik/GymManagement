package com.gymmanagement.gym_management_api.Mappers;

import com.gymmanagement.gym_management_api.DTO.Review.NotDetailedReviewDTO;
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

    public static NotDetailedReviewDTO toNotDetailedDto(Review review){
        NotDetailedReviewDTO dto = new NotDetailedReviewDTO();
        dto.setClient_name(review.getClient().getFirst_name());
        dto.setClient_surname(review.getClient().getLast_name());
        dto.setTrainer_name(review.getTrainer().getFirst_name());
        dto.setTrainer_surname(review.getTrainer().getLast_name());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setDate(review.getDate());
        return dto;
    }

    public static Iterable<NotDetailedReviewDTO>listToNotDetailedDto(Iterable<Review>reviews){
        List<NotDetailedReviewDTO>dtos = new ArrayList<>();
        for(Review i : reviews){
            dtos.add(toNotDetailedDto(i));
        }
        return dtos;
    }
}
