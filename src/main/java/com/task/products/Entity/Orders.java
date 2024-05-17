package com.task.products.Entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_placed_by_customer")
    private Customers orderPlacedByCustomer;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "sales_person_incharge")
    private SalesPerson salesPersonIncharge;

    @Column(nullable = false)
    private BigDecimal orderValue;

    @Column(nullable = false)
    private Date orderPlacedOn;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProducts;
}
