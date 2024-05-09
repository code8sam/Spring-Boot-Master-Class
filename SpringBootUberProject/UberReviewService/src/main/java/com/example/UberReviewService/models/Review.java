package com.example.UberReviewService.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name="bookingrevuew")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    String content;
    Double rating;
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    Date createdAt;
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    Date updatedAt;

}
