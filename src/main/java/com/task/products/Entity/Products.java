package com.task.products.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    //id (primary key), type, name, quantity, price
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "name must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "quantity is required")
    private int quantity;

    @NotBlank(message = "price is required")
    private int price;
}
