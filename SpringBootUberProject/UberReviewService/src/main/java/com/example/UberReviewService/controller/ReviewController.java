package com.example.UberReviewService.controller;

import com.example.UberReviewService.adapters.CreateReviewDTOToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDTO;
import com.example.UberReviewService.dtos.ReviewDTO;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private CreateReviewDTOToReviewAdapter createReviewDTOToReviewAdapter;
    public ReviewController(ReviewService reviewService, CreateReviewDTOToReviewAdapter createReviewDTOToReviewAdapter){
        this.reviewService = reviewService;
        this.createReviewDTOToReviewAdapter=createReviewDTOToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@Validated @RequestBody CreateReviewDTO request) {
        try {
            Review incomingReview = this.createReviewDTOToReviewAdapter.convertDto(request);
            if (incomingReview == null) {
                return new ResponseEntity<>("Invalid Booking ID", HttpStatus.BAD_REQUEST);
            }
            Review review = this.reviewService.publishReview(incomingReview);
            ReviewDTO response = ReviewDTO.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .rating(review.getRating())
                    .booking(review.getBooking().getId())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
            if(!isDeleted) return new ResponseEntity<>("Unable to delete Review", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request){
        try {
            Review review = this.reviewService.updateReview(reviewId, request);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
