package com.task.products.Controller;

import com.task.products.DTO.GetOrderInfoDto;
import com.task.products.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("orders")

// customers can have multiple orders,
// sales person can be in multiple orders

public class OrderController {
    //make new order by adding product. before adding product, check if product is expired or not
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid GetOrderInfoDto orderInfo) {
        orderService.addOrder(orderInfo);
        return ResponseEntity.noContent().build();
    }

}
