package com.task.products.Utils;

public interface CONST {
    String INSERT_PRODUCT_INFO = "INSERT INTO products (type, name, quantity, price) VALUES (?, ?, ?, ?)";
    String UPDATE_PRODUCT_INFO_QUANTITY = "UPDATE products SET quantity = ? where id = ?";
    String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";
    String GET_ALL_PRODUCTS = "SELECT * FROM products";
}
