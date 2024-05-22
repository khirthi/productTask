package com.task.products.Repository;

import com.task.products.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {

    Optional<Customers> findByEmail(String email);
}
