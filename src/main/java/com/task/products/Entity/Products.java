package com.task.products.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Products {

    //id (primary key), type, name, quantity, price
    private int id;
    private String type;
    private String name;
    private int quantity;
    private int price;
}
