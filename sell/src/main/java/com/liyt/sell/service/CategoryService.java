package com.liyt.sell.service;

import com.liyt.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findById(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);

}
