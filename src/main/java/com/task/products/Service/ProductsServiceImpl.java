package com.task.products.Service;

import com.task.products.Entity.Products;
import com.task.products.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepo productsRepo;

    @Override
    public List<Products> getAllProducts() {
        List<Products> response = productsRepo.findAll();
        return productsRepo.findAll();
    }
    @Override
    public Optional<Products> getProductById(int productId) {
        Optional<Products> response = Optional.empty();
        response = productsRepo.findById(productId);
        return response;
    }
    @Override
    public void createProduct(Products products) {
        productsRepo.save(products);
    }

    @Override
    public Products updateProduct(Products products) {
        return productsRepo.save(products);
    }
    @Override
    public void deleteProduct(int productId) {
        productsRepo.deleteById(productId);
    }


}
