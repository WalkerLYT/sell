package com.liyt.sell.service;

import com.liyt.sell.dataobject.SellerInfo;

/**
 * 卖家端Service
 */
public interface SellerService {
    SellerInfo login(String username, String password);
}
