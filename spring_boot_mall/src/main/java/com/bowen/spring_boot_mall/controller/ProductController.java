package com.bowen.spring_boot_mall.controller;


import com.bowen.spring_boot_mall.constant.ProductCategory;
import com.bowen.spring_boot_mall.dto.ProductQueryParams;
import com.bowen.spring_boot_mall.dto.ProductRequest;
import com.bowen.spring_boot_mall.model.Product;
import com.bowen.spring_boot_mall.service.ProductService;
import com.bowen.spring_boot_mall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
     * 2023/04/16
     */
    @GetMapping("/products/category")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false)  ProductCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
            ){

        ProductQueryParams productQueryParams=
            new ProductQueryParams();
        productQueryParams.setProductCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList= productService.getProducts(productQueryParams);
        Integer total=productService.countProduct(productQueryParams);


        Page<Product> page=new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

}
