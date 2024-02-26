package com.liyt.sell.repository;

import com.liyt.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByShopIdAndProductStatus(String shopId, Integer productStatus);
    Page<ProductInfo> findByShopId(String shopId, Pageable pageable);
}
