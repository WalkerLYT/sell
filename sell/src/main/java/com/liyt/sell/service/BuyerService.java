package com.liyt.sell.service;

import com.liyt.sell.dataobject.BuyerAddress;
import com.liyt.sell.dto.OrderDTO;
import com.liyt.sell.form.AddressForm;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消一个订单
    OrderDTO cancelOrder(String openid, String orderId);

    List<BuyerAddress> findAddressByOpenid(String openid);

    BuyerAddress findAddressById(String addressId);

    BuyerAddress createAddress(AddressForm addressForm);

    void deleteAddress(String addressId);
}
