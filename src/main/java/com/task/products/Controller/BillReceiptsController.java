package com.task.products.Controller;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Entity.BillReceipts;
import com.task.products.Entity.Products;
import com.task.products.Mappers.BillReceiptsMapper;
import com.task.products.Service.BillReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bills")
public class BillReceiptsController {

    @Autowired
    BillReceiptsService billReceiptsService;
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getBillReceiptOfOrder(@PathVariable("orderId") int orderId) {
        Optional<GetBillReceiptsForOrderIdDto> result = billReceiptsService.getBillReceiptOfOrder(orderId);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order not found, check order_id");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
