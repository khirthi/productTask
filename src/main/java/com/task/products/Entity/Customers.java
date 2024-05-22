package com.task.products.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "name must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email must be less than or equal to 50 characters")
    private String email;

    @NotBlank(message = "password is required")
    @Size(max = 50, message = "Password must be less than or equal to 50 characters")
    private String password;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @NotBlank(message = "address is required")
    private String address;

    @OneToMany(mappedBy = "orderPlacedByCustomer")
    private List<Orders> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
