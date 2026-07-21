package com.springboot.taskmanagement.controller;

import com.springboot.taskmanagement.dto.response.TokenDto;
import com.springboot.taskmanagement.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtility jwtUtility;

    // Basic Auth checks credentials before hitting this method
    @GetMapping("/login")
    public TokenDto login(Principal principal) {
        String loggedInUsername = principal.getName();
        String token = jwtUtility.generateToken(loggedInUsername);
        return new TokenDto(token, jwtUtility.extractExpiration(token).toString());
    }
}
