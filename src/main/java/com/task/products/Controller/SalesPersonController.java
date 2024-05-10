package com.task.products.Controller;

import com.task.products.DTO.GetTotalSalesInfoDto;
import com.task.products.Service.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("salesperson")
public class SalesPersonController {

    @Autowired
    SalesPersonService salesPersonService;

    @GetMapping()
    public ResponseEntity<?> getTotalSalesInfo() {
        List<GetTotalSalesInfoDto> result = salesPersonService.getTotalSalesInfo();
        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sales info not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTotalSalesByIdForMonth(@RequestParam String monthName, @PathVariable("id") int salesPersonId) {
        List<GetTotalSalesInfoDto> result = salesPersonService.getTotalSalesByIdForMonth(monthName, salesPersonId);
        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
