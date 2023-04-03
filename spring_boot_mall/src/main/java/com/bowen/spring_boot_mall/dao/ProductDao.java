package com.bowen.spring_boot_mall.dao;

import com.bowen.spring_boot_mall.model.Product;

public interface ProductDao {
    Product getProductByID(Integer productId);
}
