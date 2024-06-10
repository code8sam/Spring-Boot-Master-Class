package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    //    List<Booking>  findAllByDriverId(Long driverId);
//    List<Booking> findAllByDriverIn(List<Driver> drivers);
    @Query("SELECT r FROM Booking b INNER JOIN Review r ON b.review = r WHERE b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);
}
