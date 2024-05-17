package com.task.products.Service;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Entity.BillReceipts;
import com.task.products.Mappers.BillReceiptsMapper;
import com.task.products.Repository.BillReceiptsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillReceiptsServiceImpl implements BillReceiptsService{

    @Autowired
    BillReceiptsRepo billReceiptsRepo;

    @Override
    public Optional<GetBillReceiptsForOrderIdDto> getBillReceiptOfOrder(int orderId) {
        return billReceiptsRepo.getBillReceiptOfOrder(orderId);
    }
}
