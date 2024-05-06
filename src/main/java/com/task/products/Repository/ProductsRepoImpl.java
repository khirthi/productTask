//package com.task.products.Repository;
//
//import com.task.products.DTO.CreateProductDto;
//import com.task.products.DTO.UpdateProductQuantityDto;
//import com.task.products.Entity.Products;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static com.task.products.Utils.CONST.*;
//import static com.task.products.Utils.GenericUtilities.getFirstItemFromList;
//
//
//@Repository
//public class ProductsRepoImpl implements ProductsRepo {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Override
//    public int createProduct(CreateProductDto createProductDto) {
//        return jdbcTemplate.update(INSERT_PRODUCT_INFO, createProductDto.getType(), createProductDto.getName(), createProductDto.getQuantity(), createProductDto.getPrice());
//    }
//
//    @Override
//    public int updateProductQuantity(UpdateProductQuantityDto updateProductQuantityDto) {
//        return jdbcTemplate.update(UPDATE_PRODUCT_INFO_QUANTITY, updateProductQuantityDto.getQuantity(), updateProductQuantityDto.getId());
//    }
//
//    @Override
//    public int deleteProduct(int productId) {
//        return jdbcTemplate.update(DELETE_PRODUCT, productId);
//    }
//
//    @Override
//    public List<Products> getAllProducts() {
//        return ;
//    }
//
//    @Override
//    public Products getProductById(int productId) {
//        List<Products> response = jdbcTemplate.query(GET_ALL_PRODUCTS, new BeanPropertyRowMapper<>(Products.class));
//        return getFirstItemFromList(response);
//    }
//}
