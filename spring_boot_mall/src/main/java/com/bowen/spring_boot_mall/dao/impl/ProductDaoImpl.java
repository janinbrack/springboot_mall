package com.bowen.spring_boot_mall.dao.impl;

import com.bowen.spring_boot_mall.dao.ProductDao;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.rowmapping.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductByID(Integer productId) {


        String sql="SELECT * FROM product WHERE product_id= :productid";

    Map<String,Object> map=new HashMap<>();
    map.put("productid",productId);

        List<Product> productList =
                namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size()>0){
            return  productList.get(0);
        }else{

            return null;
        }

    }
}
