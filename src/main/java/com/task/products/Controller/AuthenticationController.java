package com.task.products.Controller;

import com.task.products.DTO.LoginUserDto;
import com.task.products.DTO.RegisterUserDto;
import com.task.products.Entity.Customers;
import com.task.products.JWT.AuthenticationService;
import com.task.products.JWT.JwtService;
import com.task.products.JWT.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customers> register(@RequestBody RegisterUserDto registerUserDto) {
        Customers registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customers authenticatedUser = (Customers) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return ResponseEntity.ok(loginResponse);
    }
}
