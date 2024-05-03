package com.task.products.Service;

import com.task.products.DTO.CreateProductDto;
import com.task.products.DTO.GetProductIdDto;
import com.task.products.DTO.UpdateProductQuantityDto;
import com.task.products.Entity.Products;
import com.task.products.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepo productsRepo;

    @Override
    public int createProduct(Object requestModel) {
        CreateProductDto createProductDto = (CreateProductDto) requestModel;
        return productsRepo.createProduct(createProductDto);
    }

    @Override
    public int updateProductQuantity(Object requestModel) {
        UpdateProductQuantityDto updateProductQuantityDto = (UpdateProductQuantityDto) requestModel;
        return productsRepo.updateProductQuantity(updateProductQuantityDto);
    }

    @Override
    public int deleteProduct(Object requestModel) {
        GetProductIdDto productId = (GetProductIdDto) requestModel;
        return productsRepo.deleteProduct(productId) ;
    }

    @Override
    public List<Products> getAllProducts() {
        return (productsRepo.getAllProducts());
    }

    @Override
    public Products getProductById(Object requestModel) {
        GetProductIdDto productId = (GetProductIdDto) requestModel;
        return (productsRepo.getProductById(productId));
    }
}
