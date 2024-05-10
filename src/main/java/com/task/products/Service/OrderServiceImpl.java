package com.task.products.Service;

import com.task.products.Entity.Customers;
import com.task.products.Entity.Orders;
import com.task.products.Entity.Products;
import com.task.products.Entity.SalesPerson;
import com.task.products.Repository.OrderRepo;
import com.task.products.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements  OrderService{

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CustomerService customerService;
    @Autowired
    SalesPersonService salesPersonService;
    @Autowired
    ProductsService productsService;

    @Override
    public void addOrder(Orders order) {

        int customerId = order.getOrderPlacedByCustomer();
        int salesPersonId = order.getSalesPersonIncharge();
        int productId = order.getProductId();

        Optional<Customers> customerInfo = customerService.getCustomerById(customerId);
        Optional<SalesPerson> salesPersonInfo = salesPersonService.getSalesPersonById(salesPersonId);
        Optional<Products> productsInfo = productsService.getProductById(productId);
        Date currentDate = new Date();

        if (customerInfo.isEmpty()) {
            throw new CustomException("customer with id " +customerId+ " doesn't exist.", 404);
        }
        if (salesPersonInfo.isEmpty()) {
            throw new CustomException("salesPerson with id " +salesPersonId+ " doesn't exist.", 404);
        }
        if (productsInfo.isEmpty()) {
            throw new CustomException("product with id " +productId+ " doesn't exist.", 404);
        }

        Date productExpiryDate = productsInfo.get().getProductExpiry();
        if (productExpiryDate.before(currentDate)){
            throw new CustomException("product expired, cannot order", 400);
        }
        orderRepo.save(order);

    }
}
