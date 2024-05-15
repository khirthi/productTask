package com.task.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBillReceiptsForOrderIdDto {

    private int orderId;
    private String customerName;
    private String salesPersonName;
    private BigDecimal orderValue;
}
