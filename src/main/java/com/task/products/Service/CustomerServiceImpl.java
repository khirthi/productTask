package com.task.products.Service;

import com.task.products.Entity.Customers;
import com.task.products.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Optional<Customers> getCustomerById(int customerId) {
        Optional<Customers> response;
        response = customerRepo.findById(customerId);
        return response;
    }
}
