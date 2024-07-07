package com.example.UberReviewService.adapters;

import com.example.UberReviewService.dtos.CreateReviewDTO;
import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDTOToReviewAdapterImpl implements CreateReviewDTOToReviewAdapter{
    private BookingRepository bookingRepository;
    public CreateReviewDTOToReviewAdapterImpl(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDTO createReviewDTO) {
        if (createReviewDTO.getBookingId() == null) {
            throw new IllegalArgumentException("Booking ID must not be null");
        }
        Optional<Booking> booking = bookingRepository.findById(createReviewDTO.getBookingId());
        if (!booking.isPresent()) {
            throw new EntityNotFoundException("Booking not found for id: " + createReviewDTO.getBookingId());
        }
        return booking.map(value -> Review.builder()
                .rating(createReviewDTO.getRating())
                .booking(value)
                .content(createReviewDTO.getContent())
                .build())
                .orElse(null);
    }
}
