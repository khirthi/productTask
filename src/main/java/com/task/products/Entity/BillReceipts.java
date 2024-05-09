package com.task.products.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BillReceipts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "orderPlacedByOrders is required")
    private int orderPlacedByOrders;

    @NotBlank(message = "salesPersonIncharge is required")
    private int salesPersonIncharge;

    @NotBlank(message = "orderValue is required")
    private String orderValue;

    @NotBlank(message = "orderPlacedOn is required")
    private Date orderPlacedOn;
}
