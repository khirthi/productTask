package com.task.products.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Products products;

}