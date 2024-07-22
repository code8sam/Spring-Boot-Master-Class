package com.example.uberprojectauthservice.controllers;

import com.example.uberprojectauthservice.dtos.PassengerDTO;
import com.example.uberprojectauthservice.dtos.PassengerSignupRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> singUp(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){
        return null;
    }
}
