package com.task.products.Service;

import com.task.products.DTO.GetOrderInfoDto;
import com.task.products.Entity.*;
import com.task.products.Repository.BillReceiptsRepo;
import com.task.products.Repository.OrderProductRepo;
import com.task.products.Repository.OrderRepo;
import com.task.products.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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


    @Override
    public void addOrder(GetOrderInfoDto orderInfo) {

        int customerId = orderInfo.getOrderPlacedByCustomer();
        int salesPersonId = orderInfo.getSalesPersonIncharge();

        Optional<Customers> customerInfo = customerService.getCustomerById(customerId);
        Optional<SalesPerson> salesPersonInfo = salesPersonService.getSalesPersonById(salesPersonId);

        String[] productIdArray = orderInfo.getProductId().split(",");

        List<Integer> productIdList = new ArrayList<>();
        for (String productIdStr : productIdArray) {
            productIdList.add(Integer.parseInt(productIdStr));
        }

        int orderValue = 0;

        for (int i: productIdList) {
            Optional<Products> productInfo = productsService.getProductById(i);
            if (productInfo.isEmpty()) {
                throw new CustomException("product with id " +i+ " doesn't exist.", 404);
            }
            orderValue += productInfo.get().getPrice();
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
        order.setOrderPlacedOn(orderInfo.getOrderPlacedOn());

        orderRepo.save(order);
        for (int i: productIdList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrders(order);
            Optional<Products> productInfo = productsService.getProductById(i);
            orderProduct.setProducts(productInfo.get());
            orderProductRepo.save(orderProduct);
        }

        BillReceipts billReceipt = new BillReceipts();
        billReceipt.setOrderId(order.getId());
        billReceipt.setSalesPersonIncharge(salesPersonId);
        billReceipt.setPlacedByCustomer(customerId);
        billReceipt.setOrderPlacedOn(orderInfo.getOrderPlacedOn());
        billReceipt.setOrderValue(BigDecimal.valueOf(orderValue));

        billReceiptsRepo.save(billReceipt);
    }
}
