package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query
    Integer countAllByRatingIsLessThanEqual(Integer givenRating);
    @Query
    List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);
    @Query
    List<Review> findAllByCreatedAtBefore(Date date);
//    @Query("SELECT r FROM Booking b INNER JOIN Review r ON b.review = r WHERE b.id = :bookingId")
//    Review findReviewByBookingId(Long bookingId);

}
