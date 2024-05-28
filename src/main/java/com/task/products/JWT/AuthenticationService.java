package com.task.products.JWT;

import com.task.products.DTO.LoginUserDto;
import com.task.products.DTO.RegisterUserDto;
import com.task.products.Entity.Customers;
import com.task.products.Repository.CustomerRepo;
import com.task.products.Utils.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomerRepo customerRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Customers signup(RegisterUserDto input) {
        Customers customer = new Customers();
        customer.setName(input.getName());
        customer.setEmail(input.getEmail());
        customer.setPassword(passwordEncoder.encode(input.getPassword()));
        customer.setAddress(input.getAddress());
        customer.setPhoneNumber(input.getPhoneNumber());
        customer.setRole(Role.valueOf(input.getRole()));
        return customerRepo.save(customer);
    }

    public Customers authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );
        return customerRepo.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
