package com.task.products.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "CustomerId is required")
    private int orderPlacedByCustomer;

    @NotBlank(message = "SalesPersonId is required")
    private int salesPersonIncharge;

    @NotBlank(message = "ProductId is required")
    private int productId;

    @NotBlank(message = "orderValue is required")
    private BigDecimal orderValue;

    @NotBlank(message = "OrderPlacedOn is required")
    private Date orderPlacedOn;

    @NotBlank(message = "Status cannot be null")
    private String status;
}
