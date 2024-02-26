package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.SellerInfo;
import com.liyt.sell.enums.ResultEnum;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.repository.SellerInfoRepository;
import com.liyt.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;


    @Override
    public SellerInfo login(String username, String password) {
        SellerInfo sellerInfo = repository.findByUsernameAndPassword(username, password);
        if (sellerInfo == null){
            throw new SellException(ResultEnum.LOGIN_FAILED);
        } else {
            return sellerInfo;
        }
    }
}
