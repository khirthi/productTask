package com.task.products.Service;

import com.task.products.DTO.GetTotalSalesInfoDto;
import com.task.products.Entity.SalesPerson;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SalesPersonService {

    Optional<SalesPerson> getSalesPersonById(int customerId);
    List<GetTotalSalesInfoDto> getTotalSalesInfo();
    List<GetTotalSalesInfoDto> getTotalSalesByIdForMonth(String monthName, int salesPersonId);
}
