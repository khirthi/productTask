package com.task.products.Service;

import com.task.products.Entity.Products;
import com.task.products.Repository.ProductsRepo;
import com.task.products.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepo productsRepo;

    @Override
    public List<Products> getAllProducts() {
        return productsRepo.findAll();
    }
    @Override
    public Optional<Products> getProductById(Object requestModel) {
        int productId = (int) requestModel;
        return productsRepo.findById(productId);
    }
    @Override
    public Products createProduct(Object requestModel) {
        Products products = (Products) requestModel;
        Products result;
        result = productsRepo.save(products);
        return result;
    }

    @Override
    public Products updateProduct(Object requestModel) {
        Products products = (Products) requestModel;
        return productsRepo.save(products);
    }
    @Override
    public void deleteProduct(Object requestModel) {
        int productId = (int) requestModel;
        productsRepo.deleteById(productId);
    }


}
