package com.example.uberprojectauthservice.controllers;

import com.example.uberprojectauthservice.dtos.AuthRequestDTO;
import com.example.uberprojectauthservice.dtos.AuthResponseDTO;
import com.example.uberprojectauthservice.dtos.PassengerDTO;
import com.example.uberprojectauthservice.dtos.PassengerSignupRequestDTO;
import com.example.uberprojectauthservice.services.AuthService;
import com.example.uberprojectauthservice.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${cookie.expiry}")
    private int cookieExpiry;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService){
        this.authService=authService;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> singUp(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){
        PassengerDTO response = authService.signupPassenger(passengerSignupRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response){
        System.out.println("Request Received : " + authRequestDTO.getEmail() + " " + authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
//            Map<String, Object> payload = new HashMap<>();
//            payload.put("email", authRequestDTO.getEmail());
            String jwtToken = jwtService.createToken(authRequestDTO.getEmail());
            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true).secure(false).path("/")
                    .maxAge(cookieExpiry).build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return new ResponseEntity<>(AuthResponseDTO.builder().success(true).build(), HttpStatus.OK);
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
