package com.bowen.spring_boot_mall.service.impl;

import com.bowen.spring_boot_mall.constant.ProductCategory;
import com.bowen.spring_boot_mall.dao.ProductDao;
import com.bowen.spring_boot_mall.dto.ProductQueryParams;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public void updateProduct(Integer productID, ProductRequest productRequest) {
        productDao.updateProduct(productID,productRequest);
    }

    @Override
    public void deleteProductuID(Integer productID) {
        productDao.deleteProductuID(productID);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }
}
