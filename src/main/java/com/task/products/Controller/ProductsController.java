package com.task.products.Controller;

import com.task.products.Entity.Products;
import com.task.products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping()
    public List<Products> getAllProduct() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> getProductById(@PathVariable("id") int id) {
        return productsService.getProductById(id);
    }

    @PostMapping()
    public Products createProduct(@RequestBody Products products) throws SQLException {
        return productsService.createProduct(products);
    }
    @PutMapping()
    //make it dynamic
    public Products updateProduct(@RequestBody Products products){
        return productsService.updateProduct(products);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productsService.deleteProduct(id);
    }
}
