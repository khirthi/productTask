package com.task.products.Controller;

import com.task.products.Entity.Orders;
import com.task.products.Service.AddOrderServiceTest;
import com.task.products.Service.OrderService;
import com.task.products.Service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddOrderControllerTest {

    @InjectMocks
    OrderService orderService = new OrderServiceImpl();

    @Test
    void addOrderTest() {
        Orders order = new Orders();
        order.setId(1);
        order.setOrderPlacedByCustomer(1);
        order.setSalesPersonIncharge(1);
        order.setProductId(1);
        order.setOrderValue(BigDecimal.valueOf(5000));
        order.setOrderPlacedOn(Date.from(Instant.now()));
        order.setStatus("OUT FOR DELIVERY");
        assertEquals(1, order.getId());
    }

}
