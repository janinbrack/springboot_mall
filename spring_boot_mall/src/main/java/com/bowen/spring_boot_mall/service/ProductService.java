package com.bowen.spring_boot_mall.service;

import com.bowen.spring_boot_mall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}
