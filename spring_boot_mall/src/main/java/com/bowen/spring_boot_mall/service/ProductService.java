package com.bowen.spring_boot_mall.service;

import com.bowen.spring_boot_mall.constant.ProductCategory;
import com.bowen.spring_boot_mall.dto.ProductQueryParams;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productID, ProductRequest productRequest);

    void deleteProductuID(Integer productID);


    List<Product> getProducts();

    List<Product> getProducts(ProductQueryParams productQueryParams);


}
