package com.task.products.Repository;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.DTO.GetTotalSalesInfoDto;
import com.task.products.Entity.BillReceipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillReceiptsRepo extends JpaRepository<BillReceipts, Integer> {

    @Query("SELECT new com.task.products.DTO.GetBillReceiptsForOrderIdDto(" +
            "    o.id AS orderId, " +
            "    c.name AS customerName, " +
            "    sp.name AS salesPersonName, " +
            "    o.orderValue) " +
            "FROM " +
            "    Orders o " +
            "JOIN " +
            "    Customers c ON o.orderPlacedByCustomer.id = c.id " +
            "JOIN " +
            "    SalesPerson sp ON o.salesPersonIncharge.id = sp.id " +
            "WHERE " +
            "    o.id = :orderId")
    Optional<GetBillReceiptsForOrderIdDto> getBillReceiptOfOrder(@Param("orderId") int orderId);

}
