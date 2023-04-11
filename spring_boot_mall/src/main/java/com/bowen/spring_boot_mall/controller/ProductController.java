package com.bowen.spring_boot_mall.controller;


import com.bowen.spring_boot_mall.constant.ProductCategory;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.List;

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



@PostMapping("/products")
  public  ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
       Integer productId= productService.createProduct(productRequest);
       Product product=productService.getProductById(productId);

    return  ResponseEntity.status(HttpStatus.CREATED).body(product);
}

    @PutMapping("/products/{productID}")
  public  ResponseEntity<Product> updateProduct(@PathVariable Integer productID,
                                                @RequestBody @Valid ProductRequest productRequest){
    //檢查商品是否存在
    if(productService.getProductById(productID)==null){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }else{
        //修改商品數據
        productService.updateProduct(productID,productRequest);
        Product updatedProduct=productService.getProductById(productID);
        return  ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
  }

  @DeleteMapping("/products/{productID}")
  public ResponseEntity<?> deleteProduct(@PathVariable Integer productID){
    productService.deleteProductuID(productID);
    return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }





  /**
   * 查詢申品列表 _ 撈取所有商品
   * 2023/04/08
   */
  @GetMapping("/products")
  public ResponseEntity<List<Product>> getProducts(){
    List<Product> productList =
            productService.getProducts();
    return ResponseEntity.status(HttpStatus.OK).body(productList);
  }


    /**
     * 查詢商品列表 _ 根據商品類型作為條件
     * 查詢商品列表 _ 搜尋功能
     * 2023/04/08
     */
    @GetMapping("/products/category")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false)  ProductCategory category,
            @RequestParam(required = false) String search){
            List<Product> productList= productService.getProducts(category,search);

             return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

}
