package com.task.products.Service;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Repository.BillReceiptsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillReceiptsServiceImpl implements BillReceiptsService{

    @Autowired
    BillReceiptsRepo billReceiptsRepo;

    @Override
    public List<GetBillReceiptsForOrderIdDto> getBillReceiptOfOrder(int orderId) {
        List<GetBillReceiptsForOrderIdDto> response = null;
        response = billReceiptsRepo.getBillReceiptOfOrder(orderId);
        return response;
    }
}
