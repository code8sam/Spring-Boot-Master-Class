package com.example.uberprojectauthservice.controllers;

import com.example.uberprojectauthservice.dtos.PassengerDTO;
import com.example.uberprojectauthservice.dtos.PassengerSignupRequestDTO;
import com.example.uberprojectauthservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> singUp(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){
        PassengerDTO response = authService.signupPassenger(passengerSignupRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signin(){
        return new ResponseEntity<>(10, HttpStatus.CREATED);
    }
}
