package com.bowen.spring_boot_mall.service.impl;

import com.bowen.spring_boot_mall.dao.ProductDao;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductByID(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }
}
