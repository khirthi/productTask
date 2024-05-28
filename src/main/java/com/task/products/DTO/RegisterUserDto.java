package com.task.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String role;
}
