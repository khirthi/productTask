package com.task.products.Service;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Entity.BillReceipts;

import java.util.List;
import java.util.Optional;

public interface BillReceiptsService {
    List<GetBillReceiptsForOrderIdDto> getBillReceiptOfOrder(int orderId);
}
