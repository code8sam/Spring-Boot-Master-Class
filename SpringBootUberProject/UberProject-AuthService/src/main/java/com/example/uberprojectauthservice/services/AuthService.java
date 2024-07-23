package com.example.uberprojectauthservice.services;

import com.example.uberprojectauthservice.dtos.PassengerDTO;
import com.example.uberprojectauthservice.dtos.PassengerSignupRequestDTO;
import com.example.uberprojectauthservice.models.Passenger;
import com.example.uberprojectauthservice.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bcryptPasswordEncoder){
        this.passengerRepository=passengerRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public PassengerDTO signupPassenger(PassengerSignupRequestDTO passengerSignupRequestDTO){
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDTO.getEmail())
                .name(passengerSignupRequestDTO.getName())
                .password(bcryptPasswordEncoder.encode(passengerSignupRequestDTO.getPassword())) // encrypt password
                .phoneNumber(passengerSignupRequestDTO.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);

        PassengerDTO response = PassengerDTO.from(newPassenger);
        return response;
    }
}
