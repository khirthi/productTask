package com.task.products.Service;

import com.task.products.DTO.ProductDto;
import com.task.products.Entity.Products;
import com.task.products.Mappers.ProductMapper;
import com.task.products.Repository.ProductsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Products Service Implementation Tests")
public class ProductsServiceImplTest {

    //set up
    //action
    //assertion

    @Mock
    private ProductsRepo productsRepo;

    @InjectMocks
    private ProductsServiceImpl productsService;

    private Products product;
    private ProductDto productDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Products();
        product.setId(1);
        product.setType("ELECTRONICS");
        product.setName("Laptop");
        product.setQuantity(10);
        product.setPrice(1000);
        product.setProductExpiry(new Date());

        productDto = ProductMapper.INSTANCE.toDto(product);
    }

    @Nested
    @DisplayName("Get Products Tests")
    class GetProductsTests {

        @Test
        @DisplayName("Test getting all products")
        public void testGetAllProducts() {
            List<Products> products = Arrays.asList(product);

            when(productsRepo.findAll()).thenReturn(products);

            List<ProductDto> result = productsService.getAllProducts();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(productDto.getId(), result.get(0).getId());
        }

        @Test
        @DisplayName("Test getting product by ID")
        public void testGetProductById() {
            when(productsRepo.findById(1)).thenReturn(Optional.of(product));

            Optional<Products> result = productsService.getProductById(1);

            assertTrue(result.isPresent());
            assertEquals(product, result.get());
        }

        @Test
        @DisplayName("Test getting all products when no products are found")
        public void testGetAllProductsThrowsErrorWhenNoProducts() {
            when(productsRepo.findAll()).thenReturn(Collections.emptyList());

            List<ProductDto> result = productsService.getAllProducts();

            assertTrue(result.isEmpty(), "Expected no products to be returned");
        }
    }

    @Nested
    @DisplayName("Create, Update, Delete Products Tests")
    class CreateUpdateDeleteProductsTests {

        @Test
        @DisplayName("Test creating a product")
        public void testCreateProduct() {
            when(productsRepo.save(product)).thenReturn(product);

            productsService.createProduct(product);

            verify(productsRepo, times(1)).save(product);
        }

        @Test
        @DisplayName("Test updating a product")
        public void testUpdateProduct() {
            when(productsRepo.save(product)).thenReturn(product);

            Products result = productsService.updateProduct(product);

            assertNotNull(result);
            assertEquals(product, result);
        }

        @Test
        @DisplayName("Test deleting a product")
        public void testDeleteProduct() {
            doNothing().when(productsRepo).deleteById(1);

            productsService.deleteProduct(1);

            verify(productsRepo, times(1)).deleteById(1);
        }
    }
}