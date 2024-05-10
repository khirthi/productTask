package com.task.products.Service;

import com.task.products.Entity.Customers;

import java.util.Optional;

public interface CustomerService {

    Optional<Customers> getCustomerById(int customerId);
}
