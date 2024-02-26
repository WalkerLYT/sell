package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.ShopInfo;
import com.liyt.sell.enums.ShopStatusEnum;
import com.liyt.sell.repository.ShopInfoRepository;
import com.liyt.sell.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopInfoRepository repository;

    @Override
    public ShopInfo save(ShopInfo shopInfo) {
        return repository.save(shopInfo);
    }

    @Override
    public ShopInfo findById(String shopId) {
        return repository.findById(shopId).orElse(null);
    }

    @Override
    public List<ShopInfo> findOpeningAll() {
        return repository.findByShopStatus(ShopStatusEnum.OPENING.getCode());
    }

    @Override
    public Page<ShopInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
