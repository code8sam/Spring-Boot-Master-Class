package com.example.UberReviewService.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CreateReviewDTO {
    private Long id;
    private String content;
    private double rating;
    private Long bookingId;
}
