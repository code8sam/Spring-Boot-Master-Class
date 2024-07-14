package com.example.uberprojectauthservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDTO {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
}
