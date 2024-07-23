package com.example.uberprojectauthservice.dtos;

import com.example.uberprojectauthservice.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
    private String id;
    private String name;
    private String email;
    private String password; //encrypted password
    private String phoneNumber;
    private Date createdAt;

    public static PassengerDTO from(Passenger p){
        return PassengerDTO.builder()
                .id(p.getId().toString())
                .name(p.getName())
                .createdAt(p.getCreatedAt())
                .email(p.getEmail())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .build();
    }
}
