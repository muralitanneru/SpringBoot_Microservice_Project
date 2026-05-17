package com.example.security_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security_service.Entity.UserCredentials;
import com.example.security_service.Service.AuthenticationSerivce;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationSerivce authenticationSerivce;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register User
    @PostMapping("/register")
    public String addUser(@RequestBody UserCredentials userCredentials) {

        return authenticationSerivce.saveUser(userCredentials);
    }

    // Generate JWT Token
    @PostMapping("/token")
    public String getToken(
            @RequestBody UserCredentials userCredentials) {

        Authentication authenticate =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userCredentials.getUsername(),
                                userCredentials.getPassword()
                        )
                );

        if (authenticate.isAuthenticated()) {

            return authenticationSerivce
                    .generateToken(userCredentials.getUsername());

        } else {

            throw new UsernameNotFoundException(
                    "Invalid user request!");
        }
    }

    // Validate Token
    @GetMapping("/validate")
    public String validateToken(
            @RequestParam("token") String token) {

        if (authenticationSerivce.validateToken(token)) {

            return "Token is valid";

        } else {

            return "Token is invalid";
        }
    }
}