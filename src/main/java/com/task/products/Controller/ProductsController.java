package com.task.products.Controller;

import com.task.products.DTO.ProductDto;
import com.task.products.Entity.Products;
import com.task.products.Mappers.ProductMapper;
import com.task.products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> result = productsService.getAllProducts();
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDOR', 'CUSTOMER')")
    public ResponseEntity<?> getProductById(@PathVariable("id") int productId) {
        Optional<Products> product = productsService.getProductById(productId);
        if (product.isPresent()) {
            ProductDto result = ProductMapper.INSTANCE.toDto(product.get());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found, check id");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid Products products) {
        productsService.createProduct(products);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid Products products) {
        productsService.updateProduct(products);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
