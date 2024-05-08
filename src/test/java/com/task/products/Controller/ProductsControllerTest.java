package com.task.products.Controller;

import com.task.products.Entity.Products;
import com.task.products.Service.ProductsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsControllerTest {

    @Mock
    private ProductsServiceImpl productsServiceImplMock;
    @InjectMocks
    private ProductsController productsController;

    @Test
    public void returnAllProducts() {

        Products product1 = new Products();
        product1.setId(1);
        product1.setType("TOYS");
        product1.setName("Bat");
        product1.setQuantity(50);
        product1.setPrice(400);

        Products product2 = new Products();
        product2.setId(2);
        product2.setType("ELECTRONICS");
        product2.setName("Mobile");
        product2.setQuantity(50);
        product2.setPrice(400);

        Products[] allProducts = new Products[]{product1, product2};

        when(productsServiceImplMock.getAllProducts()).thenReturn(List.of(allProducts));
        assertEquals(2, productsServiceImplMock.getAllProducts().size());

        given(productsController.getAllProduct()).isSameAs(allProducts);

        ResponseEntity<?> response = productsController.getAllProduct();

        assertNotNull(response);
        assertNotNull(allProducts);
        assertEquals(2, allProducts.length);
    }

    @Test
    void testGetProductById() {

        Products product1 = new Products();
        product1.setId(1);
        product1.setType("TOYS");
        product1.setName("Bat");
        product1.setQuantity(50);
        product1.setPrice(400);



        ResponseEntity<?> product = productsController.getProductById(product1.getId());
        System.out.println(product.getStatusCode());
        System.out.println(product.getHeaders());
        System.out.println(product.getBody());

        //assertThat(product.getBody()).isEqualTo(product1.getName());
    }

}