package com.gymmanagement.gym_management_api.Services;

import com.gymmanagement.gym_management_api.DTO.Review.NotDetailedReviewDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewDTO;
import com.gymmanagement.gym_management_api.Entities.Client;
import com.gymmanagement.gym_management_api.Entities.Review;
import com.gymmanagement.gym_management_api.Entities.Trainer;
import com.gymmanagement.gym_management_api.Mappers.ReviewMapper;
import com.gymmanagement.gym_management_api.Repositories.ClientRepository;
import com.gymmanagement.gym_management_api.Repositories.ReviewRepository;
import com.gymmanagement.gym_management_api.Repositories.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;

    //-----GET------//

    public Iterable<ReviewDTO> getReviews(){
        return ReviewMapper.listToDto(reviewRepository.findAll());
    }

    public Iterable<NotDetailedReviewDTO>getNotDetailedReviews(){
        return ReviewMapper.listToNotDetailedDto(reviewRepository.findAll());
    }

    public ReviewDTO getReviewById(Integer id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id: " + id + " not found."));
        return ReviewMapper.toDto(review);
    }

    public Long getReviewCountByTrainerId(Integer trainer_id){
        return reviewRepository.countByTrainer_UserId(trainer_id);
    }

    public Iterable<NotDetailedReviewDTO>getNotDetailedReviewsByTrainerId(Integer trainer_id){
        return ReviewMapper.listToNotDetailedDto(reviewRepository.findAllByTrainer_UserId(trainer_id));
    }

    public Double getAverageRatingByTrainerId(Integer trainer_id){
        long reviewCount = reviewRepository.countByTrainer_UserId(trainer_id);
        if(reviewCount == 0){
            throw new EntityNotFoundException("Reviews for trainer with id "+ trainer_id + " not found.");
        }
        Iterable<Review>reviews = reviewRepository.findAllByTrainer_UserId(trainer_id);
        Double sum = 0.0;
        for(Review i : reviews){
            sum += i.getRating();
        }
        return sum/reviewCount;
    }

    //-----POST-----//

    public ReviewDTO addReview(ReviewCreateDTO dto){
        Integer client_id = dto.getClient_id();
        Client client = clientRepository.findById(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + client_id + " not found."));

        Integer trainer_id = dto.getTrainer_id();
        Trainer trainer = trainerRepository.findById(trainer_id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id: " + trainer_id + " not found."));

        Review review = ReviewMapper.toEntity(dto, client, trainer);
        return ReviewMapper.toDto(reviewRepository.save(review));
    }
}
