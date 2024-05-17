package com.task.products.Mappers;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Entity.BillReceipts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillReceiptsMapper {

    BillReceiptsMapper INSTANCE = Mappers.getMapper(BillReceiptsMapper.class);

    @Mapping(source = "orderId.id", target = "orderId")
    @Mapping(source = "placedByCustomer.name", target = "customerName")
    @Mapping(source = "salesPersonIncharge.name", target = "salesPersonName")
    @Mapping(source = "orderValue", target = "orderValue")
    GetBillReceiptsForOrderIdDto toDto(BillReceipts billReceipts);

}
