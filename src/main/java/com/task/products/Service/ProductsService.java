package com.task.products.Service;

import com.task.products.DTO.GetProductIdDto;
import com.task.products.Entity.Products;

import java.util.List;

public interface ProductsService {
    int createProduct(Object requestModel);

    int updateProductQuantity(Object requestModel);

    int deleteProduct(Object requestModel);

    List<Products> getAllProducts();

    Products getProductById(Object requestModel);
}
