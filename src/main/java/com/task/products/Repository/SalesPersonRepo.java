package com.task.products.Repository;

import com.task.products.DTO.GetTotalSalesInfoDto;
import com.task.products.Entity.SalesPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesPersonRepo extends JpaRepository<SalesPerson, Integer> {

    @Query("SELECT new com.task.products.DTO.GetTotalSalesInfoDto(" +
            "       s.id AS salesPersonId, " +
            "       s.name AS salesPersonName, " +
            "       COUNT(o.id) AS countOfSales, " +
            "       SUM(o.orderValue) AS sumOfOrderValue) " +
            "FROM SalesPerson s " +
            "LEFT JOIN Orders o ON s.id = o.salesPersonIncharge.id " +
            "GROUP BY s.id, s.name")
    List<GetTotalSalesInfoDto> getTotalSalesInfo();

    @Query("SELECT new com.task.products.DTO.GetTotalSalesInfoDto(" +
            "    s.id AS salesPersonId, " +
            "    s.name AS salesPersonName, " +
            "    COUNT(o.id) AS countOfSales, " +
            "    SUM(o.orderValue) AS sumOfOrderValue) " +
            "FROM  " +
            "    SalesPerson s " +
            "LEFT JOIN Orders o ON s.id = o.salesPersonIncharge.id " +
            "WHERE  " +
            "    s.id = :salesPersonId  " +
            "    AND MONTH(o.orderPlacedOn) = :monthNumber " +
            "GROUP BY s.id, s.name")
    List<GetTotalSalesInfoDto> getTotalSalesByIdForMonth(@Param("salesPersonId") int salesPersonId, @Param("monthNumber") int monthNumber);

}
