package com.task.products.Service;

import com.task.products.Entity.Products;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<Products> getAllProducts();
    Optional<Products> getProductById(Object requestModel);
    Products createProduct(Object requestModel) throws SQLException;
    Products updateProduct(Object requestModel);
    void deleteProduct(Object requestModel);
}
