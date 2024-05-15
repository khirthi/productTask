package com.task.products.Controller;

import com.task.products.Entity.Products;
import com.task.products.Service.ProductsService;
import com.task.products.Service.ProductsServiceImpl;
import com.task.products.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping()
    public ResponseEntity<?> getAllProduct() {
        List<Products> result = productsService.getAllProducts();
        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("products not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int productId) {
        Optional<Products> result = productsService.getProductById(productId);
        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found, check id");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody @Valid Products products) {
        productsService.createProduct(products);
        return ResponseEntity.noContent().build();
    }
    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody @Valid Products products){
        productsService.updateProduct(products);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id){
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
