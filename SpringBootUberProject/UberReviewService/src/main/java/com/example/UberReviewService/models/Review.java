package com.example.UberReviewService.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name="bookingrevuew")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String content;
    Double rating;
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
