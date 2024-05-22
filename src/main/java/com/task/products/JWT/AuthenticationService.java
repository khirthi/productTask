package com.task.products.JWT;

import com.task.products.DTO.LoginUserDto;
import com.task.products.DTO.RegisterUserDto;
import com.task.products.Entity.Customers;
import com.task.products.Repository.CustomerRepo;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            CustomerRepo customerRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Customers signup(RegisterUserDto input) {
        Customers customers = new Customers();
        customers.setName(input.getName());
        customers.setEmail(input.getEmail());
        customers.setPassword(passwordEncoder.encode(input.getPassword()));
        customers.setAddress(input.getAddress());
        customers.setPhoneNumber(input.getPhoneNumber());
        return customerRepo.save(customers);
    }

    public Customers authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return customerRepo.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
