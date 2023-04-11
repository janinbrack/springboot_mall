package com.bowen.spring_boot_mall.dao.impl;

import com.bowen.spring_boot_mall.constant.ProductCategory;
import com.bowen.spring_boot_mall.dao.ProductDao;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.rowmapping.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
    String sql="INSERT INTO  product(product_name,category,image_url,price,stock,description,created_date,last_modified_date)" +
            "VALUES (:product_name,:category,:image_url,:price,:stock,:description,:created_date,:last_modified_date)";

        Map<String,Object> map=new HashMap<>();
        map.put("product_name",productRequest.getProductName());
        map.put("category",productRequest.getCategory().name());
        map.put("image_url",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now =new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder =new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int productId= keyHolder.getKey().intValue();

        return productId;
    }



    @Override
    public void updateProduct(Integer productID, ProductRequest productRequest) {
      String sql="UPDATE product SET product_name=:product_name,category=:category,image_url=:image_url," +
              "price=:price,stock=:stock,description=:description,last_modified_date=:last_modified_date" +
              " WHERE product_id= :producd_ID";

    Map<String,Object> map=new HashMap<>();
    map.put("product_name",productRequest.getProductName());
        map.put("category",productRequest.getCategory().name());
        map.put("image_url",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("last_modified_date",new Date());

        //收進來的id參數
        map.put("producd_ID",productID);

      namedParameterJdbcTemplate.update(sql,map);


    }


    @Override
    public void deleteProductuID(Integer productID) {
        String sql="DELETE FROM product Where product_id=:productID";
        Map<String,Object> map=new HashMap<>();
        map.put("productID",productID);
        namedParameterJdbcTemplate.update(sql,map);

    }




    /**
     * 查詢申品列表 _ 撈取所有商品
     * 2023/04/09
     */
    @Override
    public List<Product> getProducts() {
//        String sql="SELECT product_id,product_name,category,image_url,price,stock,description,created_date,last_modified_date" +
//                "FROM product";

        String sql="SELECT * FROM product";

        List<Product> productList =
                namedParameterJdbcTemplate.query(sql,new HashMap(),new ProductRowMapper());
        return productList;
    }




    /**
     * 查詢商品列表 _ 根據商品類型作為條件
     * 查詢商品列表 _ 搜尋功能
     * 2023/04/11
     */
    @Override
    public List<Product> getProducts(ProductCategory category,String search) {
        String sql="SELECT * FROM product where 1=1";
        Map<String,Object> map=new HashMap<>();

        if (category != null){
            sql+=" AND category=:category";
            map.put("category",category.name());
        }

        if (search!=null){
            sql+=" AND product_name LIKE :search";
            map.put("search","%"+search+"%");
        }


        List<Product> productList =
                namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        return productList;
    }
}
