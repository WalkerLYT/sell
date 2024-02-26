package com.liyt.sell.service;

import com.liyt.sell.dataobject.ProductInfo;
import com.liyt.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务层
 */
public interface ProductService {

    //按productId查询所有商品
    ProductInfo findById(String productId);
    //查询所有上架的商品
    List<ProductInfo> findUpAll();
    //查询店家所有商品
    List<ProductInfo> findShopUpAll(String shopId);

    Page<ProductInfo> findAll(Pageable pageable);

    Page<ProductInfo> findAllInShop(String shopId, Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);


}
