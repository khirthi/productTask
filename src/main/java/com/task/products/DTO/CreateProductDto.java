package com.task.products.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductDto {

    @NotNull(message = "Product info cannot be null")
    private String type;
    private String name;
    private int quantity;
    private int price;
}
