package com.task.products.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.task.products.DTO.ProductDto;
import com.task.products.Entity.Products;
import com.task.products.Mappers.ProductMapper;
import com.task.products.Service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@DisplayName("Products Controller Tests")
public class ProductsControllerTest {

    //set up
    //action
    //assertion

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsController productsController;

    private Products product;
    private ProductDto productDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Products();
        product.setId(1);
        product.setType("Electronics");
        product.setName("Laptop");
        product.setQuantity(10);
        product.setPrice(1000);
        product.setProductExpiry(new Date());

        productDto = ProductMapper.INSTANCE.toDto(product);

        mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }

    @Nested
    @DisplayName("GET /product")
    class GetAllProductsTests {

        @Test
        @DisplayName("Test getting all products successfully")
        public void testGetAllProduct() throws Exception {
            when(productsService.getAllProducts()).thenReturn(Arrays.asList(productDto));

            mockMvc.perform(get("/product"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].name").value("Laptop"));
        }

        @Test
        @DisplayName("Test getting all products when no products found")
        public void testGetAllProductsThrowsErrorWhenNoProducts() throws Exception {
            when(productsService.getAllProducts()).thenReturn(Collections.emptyList());

            mockMvc.perform(get("/product"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("products not found"));
        }
    }

    @Nested
    @DisplayName("GET /product/{id}")
    class GetProductByIdTests {

        @Test
        @DisplayName("Test getting a product by ID successfully")
        public void testGetProductById() throws Exception {
            when(productsService.getProductById(1)).thenReturn(Optional.of(product));

            mockMvc.perform(get("/product/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Laptop"));
        }

        @Test
        @DisplayName("Test getting a product by ID when not found")
        public void testGetProductByIdNotFound() throws Exception {
            when(productsService.getProductById(1)).thenReturn(Optional.empty());

            mockMvc.perform(get("/product/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("Product not found, check id"));
        }
    }


    @Nested
    @DisplayName("POST /product")
    class CreateProductTests {

        @Test
        @DisplayName("Test creating a product successfully")
        public void testCreateProduct() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = objectMapper.writeValueAsString(product);

            doNothing().when(productsService).createProduct(any(Products.class));

            mockMvc.perform(post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(productJson))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("PUT /product")
    class UpdateProductTests {

        @Test
        @DisplayName("Test updating a product successfully")
        public void testUpdateProduct() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = objectMapper.writeValueAsString(product);

            when(productsService.updateProduct(any(Products.class))).thenReturn(product);

            mockMvc.perform(put("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(productJson))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("DELETE /product/{id}")
    class DeleteProductTests {

        @Test
        @DisplayName("Test deleting a product successfully")
        public void testDeleteProduct() throws Exception {
            doNothing().when(productsService).deleteProduct(1);

            mockMvc.perform(delete("/product/1"))
                    .andExpect(status().isNoContent());
        }
    }
}
