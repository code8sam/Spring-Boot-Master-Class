package com.example.UberReviewService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel{
    private String name;
    @Column(nullable = false, unique = true)
    private String licenseNumber;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "driver")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Booking> bookings = new ArrayList<>();
}
