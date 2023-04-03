package com.bowen.spring_boot_mall.constant;

public class MyTest {


    public static void main(String[] args) {

        ProductCategory productCategory=
                ProductCategory.FOOD;
        String s=productCategory.name();
        System.out.println(s);
        System.out.println(ProductCategory.FOOD);

        String s2="CAR";
        ProductCategory productCategory1=
                ProductCategory.valueOf(s2);
        System.out.println(productCategory1);

    }
}
