package com.example.uberprojectauthservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
public class Passenger extends BaseModel{
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "passenger")
    List<Booking> bookings;
}

