package com.example.uberprojectauthservice.dtos;

import lombok.*;

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
}
