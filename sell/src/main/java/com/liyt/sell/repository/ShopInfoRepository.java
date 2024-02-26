package com.liyt.sell.repository;

import com.liyt.sell.dataobject.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopInfoRepository  extends JpaRepository<ShopInfo, String> {
    List<ShopInfo> findByShopStatus(Integer shopStatus);
}
