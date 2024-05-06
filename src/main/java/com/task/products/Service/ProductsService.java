package com.task.products.Service;

import com.task.products.Entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<Products> getAllProducts();
    Optional<Products> getProductById(int id);
    void createProduct(Products products);
    Products updateProduct(Products products);
    void deleteProduct(int id);
}
