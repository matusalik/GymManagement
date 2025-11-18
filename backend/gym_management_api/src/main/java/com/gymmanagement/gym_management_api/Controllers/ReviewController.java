package com.gymmanagement.gym_management_api.Controllers;

import com.gymmanagement.gym_management_api.Common.Tags;
import com.gymmanagement.gym_management_api.DTO.Review.NotDetailedReviewDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewCreateDTO;
import com.gymmanagement.gym_management_api.DTO.Review.ReviewDTO;
import com.gymmanagement.gym_management_api.Services.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public @ResponseBody Iterable<ReviewDTO>getReviews(){
        return reviewService.getReviews();
    }

    @GetMapping("/recent")
    public @ResponseBody Iterable<NotDetailedReviewDTO>getNotDetailedReviews(){
        return reviewService.getNotDetailedReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO>getReviewById(@PathVariable Integer id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    //----POST----//

    @PostMapping
    public ReviewDTO addReview(@RequestBody ReviewCreateDTO dto){
        return reviewService.addReview(dto);
    }
}
