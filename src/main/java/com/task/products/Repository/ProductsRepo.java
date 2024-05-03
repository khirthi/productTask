package com.task.products.Repository;

import com.task.products.DTO.CreateProductDto;
import com.task.products.DTO.GetProductIdDto;
import com.task.products.DTO.UpdateProductQuantityDto;
import com.task.products.Entity.Products;

import java.util.List;

public interface ProductsRepo {
    int createProduct(CreateProductDto createProductDto);

    int updateProductQuantity(UpdateProductQuantityDto updateProductQuantityDto);

    int deleteProduct(GetProductIdDto getProductIdDto);

    List<Products> getAllProducts();

    Products getProductById(GetProductIdDto productId);
}
