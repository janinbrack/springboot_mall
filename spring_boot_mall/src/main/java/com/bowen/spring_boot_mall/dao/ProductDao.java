package com.bowen.spring_boot_mall.dao;

import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductByID(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productID, ProductRequest productRequest);

    void deleteProductuID(Integer productID);

    List<Product> getProducts();
}
