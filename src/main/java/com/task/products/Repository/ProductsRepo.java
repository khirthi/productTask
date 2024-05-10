package com.task.products.Repository;

import com.task.products.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

}
