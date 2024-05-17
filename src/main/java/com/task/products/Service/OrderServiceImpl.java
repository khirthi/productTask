package com.task.products.Service;

import com.task.products.DTO.GetOrderInfoDto;
import com.task.products.DTO.OrderItemDto;
import com.task.products.Entity.*;
import com.task.products.Repository.BillReceiptsRepo;
import com.task.products.Repository.OrderProductRepo;
import com.task.products.Repository.OrderRepo;
import com.task.products.Utils.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    OrderProductRepo orderProductRepo;
    @Autowired
    BillReceiptsRepo billReceiptsRepo;


    @Transactional
    @Override
    public void addOrder(GetOrderInfoDto orderInfo) {

        int customerId = orderInfo.getOrderPlacedByCustomer();
        int salesPersonId = orderInfo.getSalesPersonIncharge();

        Optional<Customers> customerInfo = customerService.getCustomerById(customerId);
        Optional<SalesPerson> salesPersonInfo = salesPersonService.getSalesPersonById(salesPersonId);

        List<OrderItemDto> orderedItems = orderInfo.getOrderItems();
        int orderValue = 0;

        for (OrderItemDto orderItem : orderedItems) {
            int productId = orderItem.getProductId();
            int quantity = orderItem.getQuantity();
            Optional<Products> productInfo = productsService.getProductById(productId);
            Products product = productInfo.get();

            // Check if quantity is lesser than value in DB
            if (quantity >  product.getQuantity()) {
                throw new CustomException("Only " + product.getQuantity() + " nos of product with id "
                        + productId+" is available.", 400);
            }
            orderValue += (product.getPrice()*quantity);
        }

        if (customerInfo.isEmpty()) {
            throw new CustomException("customer with id " +customerId+ " doesn't exist.", 404);
        }
        if (salesPersonInfo.isEmpty()) {
            throw new CustomException("salesPerson with id " +salesPersonId+ " doesn't exist.", 404);
        }

        Optional<Customers> customers = customerService.getCustomerById(customerId);
        Customers customer = customers.orElse(null);

        SalesPerson salesPerson = salesPersonInfo.orElse(null);
        Orders order = new Orders();
        order.setOrderPlacedByCustomer(customer);
        order.setOrderValue(BigDecimal.valueOf(orderValue));
        order.setStatus(orderInfo.getStatus());
        order.setSalesPersonIncharge(salesPerson);
        order.setOrderPlacedOn(Date.from(Instant.now()));

        orderRepo.save(order);

        for (OrderItemDto orderItem: orderedItems) {
            int productId = orderItem.getProductId();
            int quantity = orderItem.getQuantity();

            Products product = productsService.getProductById(productId).orElse(null);
            int previousProductQuantity = product.getQuantity();
            product.setQuantity(previousProductQuantity - quantity);
            productsService.updateProduct(product);

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrders(order);
            Optional<Products> productInfo = productsService.getProductById(productId);
            orderProduct.setProducts(productInfo.get());
            orderProductRepo.save(orderProduct);
        }

        BillReceipts billReceipt = new BillReceipts();
        billReceipt.setOrderId(order);
        billReceipt.setSalesPersonIncharge(salesPerson);
        billReceipt.setPlacedByCustomer(customer);
        billReceipt.setOrderPlacedOn(Date.from(Instant.now()));
        billReceipt.setOrderValue(BigDecimal.valueOf(orderValue));

        billReceiptsRepo.save(billReceipt);
    }
}
