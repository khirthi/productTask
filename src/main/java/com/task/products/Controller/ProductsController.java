package com.task.products.Controller;

import com.task.products.DTO.CreateProductDto;
import com.task.products.DTO.GetProductIdDto;
import com.task.products.DTO.UpdateProductQuantityDto;
import com.task.products.Entity.Products;
import com.task.products.Service.ProductsService;
import com.task.products.Utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping()
    public int createProduct(@RequestBody CreateProductDto createProductDto) {
        return productsService.createProduct(createProductDto);
    }

    @PutMapping()
    public int updateProductQuantity(@RequestBody UpdateProductQuantityDto updateProductQuantityDto){
        return productsService.updateProductQuantity(updateProductQuantityDto);
    }

    @DeleteMapping()
    public int deleteProduct(@RequestBody GetProductIdDto getProductIdDto){
        return productsService.deleteProduct(getProductIdDto);
    }

    @GetMapping()
    public ResponseModel getAllProduct() {
        List<Products> response = productsService.getAllProducts();
        return new ResponseModel(response);
    }

    @GetMapping("/id")
    public ResponseModel getProductById(@RequestBody GetProductIdDto getProductIdDto) {
        Products response = productsService.getProductById(getProductIdDto);
        return new ResponseModel(response);
    }
}
