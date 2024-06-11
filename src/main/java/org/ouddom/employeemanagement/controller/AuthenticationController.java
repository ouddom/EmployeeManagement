package org.ouddom.employeemanagement.controller;

import org.ouddom.employeemanagement.jwt.JwtService;
import org.ouddom.employeemanagement.common.LoginResponse;
import org.ouddom.employeemanagement.domain.dto.LoginUserDto;
import org.ouddom.employeemanagement.domain.dto.RegisterUserDto;
import org.ouddom.employeemanagement.domain.entity.User;
import org.ouddom.employeemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User registeredUser = authenticationService.signup(registerUserDto);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            // Handle exception and return appropriate error response
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

            // Assuming authenticate returns a User object after validating credentials
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            // Generate JWT token for the authenticated user
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Create response object with token and expiration time
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            // Return the response entity with OK status
            return ResponseEntity.ok(loginResponse);

    }
}
