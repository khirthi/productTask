package com.task.products.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GetProductIdDto {
    @NotNull(message = "Product id cannot be null")
    private int id;
}
