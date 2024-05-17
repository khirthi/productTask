package com.task.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderInfoDto {

    private int orderPlacedByCustomer;
    private int salesPersonIncharge;
    private String status;
    private List<OrderItemDto> orderItems;
}
