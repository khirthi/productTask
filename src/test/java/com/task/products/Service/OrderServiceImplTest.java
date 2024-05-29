package com.task.products.Service;

import com.task.products.DTO.GetOrderInfoDto;
import com.task.products.DTO.OrderItemDto;
import com.task.products.Entity.*;
import com.task.products.Repository.BillReceiptsRepo;
import com.task.products.Repository.OrderProductRepo;
import com.task.products.Repository.OrderRepo;
import com.task.products.Utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Order ServiceImpl Controller Tests")
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepo orderRepo;
    @Mock
    private CustomerService customerService;
    @Mock
    private SalesPersonService salesPersonService;
    @Mock
    private ProductsService productsService;
    @Mock
    private OrderProductRepo orderProductRepo;
    @Mock
    private BillReceiptsRepo billReceiptsRepo;

    public OrderServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Add Order Tests")
    class AddOrderTests {

        @Test
        @DisplayName("Should Add Order Successfully")
        void shouldAddOrderSuccessfully() {
            GetOrderInfoDto orderInfo = new GetOrderInfoDto();
            orderInfo.setOrderPlacedByCustomer(1);
            orderInfo.setSalesPersonIncharge(1);
            orderInfo.setOrderItems(Collections.singletonList(new OrderItemDto(1, 2)));
            orderInfo.setStatus("NEW");

            Customers customer = new Customers();
            customer.setId(1);
            SalesPerson salesPerson = new SalesPerson();
            salesPerson.setId(1);
            Products product = new Products();
            product.setId(1);
            product.setQuantity(10);
            product.setPrice(100);

            when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
            when(salesPersonService.getSalesPersonById(1)).thenReturn(Optional.of(salesPerson));
            when(productsService.getProductById(1)).thenReturn(Optional.of(product));

            orderService.addOrder(orderInfo);

            verify(orderRepo).save(any(Orders.class));
            verify(orderProductRepo).save(any(OrderProduct.class));
            verify(billReceiptsRepo).save(any(BillReceipts.class));
        }

        @Test
        @DisplayName("Should Throw Exception When Customer Does Not Exist")
        void shouldThrowExceptionWhenCustomerDoesNotExist() {
            GetOrderInfoDto orderInfo = new GetOrderInfoDto();
            orderInfo.setOrderPlacedByCustomer(1);
            orderInfo.setSalesPersonIncharge(1);

            when(customerService.getCustomerById(1)).thenReturn(Optional.empty());

            assertThrows(NullPointerException.class, () -> orderService.addOrder(orderInfo));
        }

        @Test
        @DisplayName("Should Throw Exception When SalesPerson Does Not Exist")
        void shouldThrowExceptionWhenSalesPersonDoesNotExist() {
            GetOrderInfoDto orderInfo = new GetOrderInfoDto();
            orderInfo.setOrderPlacedByCustomer(1);
            orderInfo.setSalesPersonIncharge(1);

            Customers customer = new Customers();
            customer.setId(1);

            when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
            when(salesPersonService.getSalesPersonById(1)).thenReturn(Optional.empty());

            assertThrows(NullPointerException.class , () -> orderService.addOrder(orderInfo));
        }

        @Test
        @DisplayName("Should Throw Exception When Product Quantity Is Insufficient")
        void shouldThrowExceptionWhenProductQuantityIsInsufficient() {
            GetOrderInfoDto orderInfo = new GetOrderInfoDto();
            orderInfo.setOrderPlacedByCustomer(1);
            orderInfo.setSalesPersonIncharge(1);
            orderInfo.setOrderItems(Collections.singletonList(new OrderItemDto(1, 20)));

            Customers customer = new Customers();
            customer.setId(1);
            SalesPerson salesPerson = new SalesPerson();
            salesPerson.setId(1);
            Products product = new Products();
            product.setId(1);
            product.setQuantity(10);

            when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
            when(salesPersonService.getSalesPersonById(1)).thenReturn(Optional.of(salesPerson));
            when(productsService.getProductById(1)).thenReturn(Optional.of(product));

            assertThrows(CustomException.class, () -> orderService.addOrder(orderInfo));
        }
    }
}
