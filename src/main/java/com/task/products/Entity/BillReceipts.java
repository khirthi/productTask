package com.task.products.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BillReceipts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "orderId is required")
    private int orderId;

    @NotBlank(message = "placedByCustomer is required")
    private int placedByCustomer;

    @NotBlank(message = "salesPersonIncharge is required")
    private int salesPersonIncharge;

    @NotBlank(message = "orderValue is required")
    private BigDecimal orderValue;

    @NotBlank(message = "orderPlacedOn is required")
    private Date orderPlacedOn;
}
