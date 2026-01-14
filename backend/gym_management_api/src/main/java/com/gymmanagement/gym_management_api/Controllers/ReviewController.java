package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Review.NotDetailedReviewDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewDTO;
import com.gymmanagement.gym_management_api.Services.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
@Tag(name = Tags.ReviewsTag)
public class ReviewController {
    private final ReviewService reviewService;

    //-----GET-----//

    @GetMapping
    public ResponseEntity<Iterable<ReviewDTO>>getReviews(){
        return ResponseEntity.ok(reviewService.getReviews());
    }

    @GetMapping("/recent")
    public ResponseEntity<Iterable<NotDetailedReviewDTO>>getRecentNotDetailedReviews(){
        return ResponseEntity.ok(reviewService.getRecentNotDetailedReviews());
    }

    @GetMapping("/notdetailed")
    public ResponseEntity<Iterable<NotDetailedReviewDTO>>getNotDetailedReviews(){
        return ResponseEntity.ok(reviewService.getNotDetailedReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO>getReviewById(@PathVariable Integer id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/countByTrainer/{trainer_id}")
    public ResponseEntity<Long>getReviewCountByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(reviewService.getReviewCountByTrainerId(trainer_id));
    }

    @GetMapping("/averageByTrainer/{trainer_id}")
    public ResponseEntity<Double>getAverageRatingByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(reviewService.getAverageRatingByTrainerId(trainer_id));
    }

    @GetMapping("/getByTrainer/{trainer_id}")
    public ResponseEntity<Iterable<NotDetailedReviewDTO>>getNotDetailedReviewsByTrainerId(@PathVariable Integer trainer_id){
        return ResponseEntity.ok(reviewService.getNotDetailedReviewsByTrainerId(trainer_id));
    }


    //----POST----//

    @PostMapping
    public ResponseEntity<Void>addReview(@RequestBody ReviewCreateDTO dto){
        reviewService.addReview(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
