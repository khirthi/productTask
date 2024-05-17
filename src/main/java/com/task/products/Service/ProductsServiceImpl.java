package com.task.products.Service;

import com.task.products.DTO.ProductDto;
import com.task.products.Entity.Products;
import com.task.products.Mappers.ProductMapper;
import com.task.products.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepo productsRepo;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Products> products = productsRepo.findAll();
        return products.stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Products> getProductById(int productId) {
        return productsRepo.findById(productId);
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
