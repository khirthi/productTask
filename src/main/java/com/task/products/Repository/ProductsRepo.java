package com.task.products.Repository;

import com.task.products.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

//    int createProduct(CreateProductDto createProductDto);
//
//    int updateProductQuantity(UpdateProductQuantityDto updateProductQuantityDto);
//
//    int deleteProduct(int productId);
//    Products getProductById(int productId);
}
