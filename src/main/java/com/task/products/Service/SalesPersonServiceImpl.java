package com.task.products.Service;

import com.task.products.DTO.GetTotalSalesInfoDto;
import com.task.products.Entity.SalesPerson;
import com.task.products.Repository.SalesPersonRepo;
import com.task.products.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class SalesPersonServiceImpl implements SalesPersonService{



    @Autowired
    SalesPersonRepo salesPersonRepo;

    @Override
    public Optional<SalesPerson> getSalesPersonById(int customerId) {
        Optional<SalesPerson> response;
        response = salesPersonRepo.findById(customerId);
        return response;
    }

    @Override
    public List<GetTotalSalesInfoDto> getTotalSalesInfo() {
        List<GetTotalSalesInfoDto> response;
        response = salesPersonRepo.getTotalSalesInfo();
        return response;
    }

    @Override
    public List<GetTotalSalesInfoDto> getTotalSalesByIdForMonth(String monthName, int salesPersonId) {
        List<GetTotalSalesInfoDto> response;
        try {
            Month month = Month.valueOf(monthName.toUpperCase());
            int monthNumber = month.getValue();
            response = salesPersonRepo.getTotalSalesByIdForMonth(salesPersonId, monthNumber);
        }
        catch (IllegalArgumentException e) {
            throw new CustomException("Please enter valid month name", 400);
        }
        return response;
    }
}
