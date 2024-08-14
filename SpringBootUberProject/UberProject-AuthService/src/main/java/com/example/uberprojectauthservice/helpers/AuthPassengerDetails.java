package com.example.uberprojectauthservice.helpers;

import com.example.uberprojectauthservice.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// Why we need this class :
// we need this class because we want Passenger Object converted to polymorphic type of UserDetails Object. Because Spring Security works on UserDetails polymorphic type of Auth
public class AuthPassengerDetails extends Passenger implements UserDetails {
    private String username;
    private String password;

    public AuthPassengerDetails(Passenger passenger){
        this.username=passenger.getEmail();
        this.password=passenger.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
