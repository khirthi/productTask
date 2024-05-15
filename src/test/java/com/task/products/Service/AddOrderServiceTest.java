package com.task.products.Service;


import com.task.products.Entity.Orders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class AddOrderServiceTest {

    @InjectMocks
    OrderService orderService = new OrderServiceImpl();

    @Test
    void addOrder() {
        Orders order = new Orders();
        order.setId(1);
        order.setOrderValue(BigDecimal.valueOf(5000));
        order.setOrderPlacedOn(Date.from(Instant.now()));
        order.setStatus("OUT FOR DELIVERY");
    }
}
