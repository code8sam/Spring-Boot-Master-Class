package com.example.UberReviewService.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Double rating;
    private Long booking;
}
