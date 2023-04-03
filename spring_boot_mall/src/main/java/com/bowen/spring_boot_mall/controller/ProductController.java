package com.bowen.spring_boot_mall.controller;


import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyDescriptor;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;



@GetMapping("product/{productId}")
public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
    Product product = productService.getProductById(productId);

    if(product != null){
        return  ResponseEntity.status(HttpStatus.OK).body(product);

    }else{
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}

}
