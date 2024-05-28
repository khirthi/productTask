package com.task.products.Entity;

import com.task.products.Utils.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    @Size(max = 100, message = "Name must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email must be less than or equal to 50 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max = 50, message = "Password must be less than or equal to 50 characters")
    private String password;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @OneToMany(mappedBy = "orderPlacedByCustomer")
    private List<Orders> orders;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
