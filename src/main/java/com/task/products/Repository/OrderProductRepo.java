package com.task.products.Repository;

import com.task.products.Entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Integer> {
}
