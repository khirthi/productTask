package com.task.products.Entity;

import jakarta.persistence.*;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orderId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "placed_by_customer", nullable = false)
    private Customers placedByCustomer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sales_person_incharge", nullable = false)
    private SalesPerson salesPersonIncharge;

    @Column(nullable = false)
    private BigDecimal orderValue;

    @Column(nullable = false)
    private Date orderPlacedOn;
}
