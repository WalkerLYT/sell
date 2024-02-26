package com.liyt.sell.service;

import com.liyt.sell.dataobject.ShopInfo;
import com.liyt.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 店铺服务层
 */
public interface ShopService {

    //创建或修改店铺信息
    ShopInfo save(ShopInfo shopInfo);

    //查询单个店铺
    ShopInfo findById(String shopId);

    //查询营业店铺列表
    List<ShopInfo> findOpeningAll();

    //查询所有店铺列表
    Page<ShopInfo> findAll(Pageable pageable);
}
