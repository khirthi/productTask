package com.task.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTotalSalesInfoDto {

    private int salesPersonId;
    private String salesPersonName;
    private Long countOfSales;
    private BigDecimal sumOfOrderValue;

}
