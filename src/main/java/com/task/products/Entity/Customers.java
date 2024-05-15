package com.task.products.Entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "name must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @NotBlank(message = "address is required")
    private String address;

    @OneToMany(mappedBy = "orderPlacedByCustomer")
    private List<Orders> orders;

}
