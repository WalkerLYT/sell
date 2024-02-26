package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.BuyerAddress;
import com.liyt.sell.dto.OrderDTO;
import com.liyt.sell.enums.ResultEnum;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.form.AddressForm;
import com.liyt.sell.repository.AddressRepository;
import com.liyt.sell.service.BuyerService;
import com.liyt.sell.service.OrderService;
import com.liyt.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO == null){
            log.error("【取消订单】查询不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findById(orderId);
        if(orderDTO == null){
            return null;
        }

        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public List<BuyerAddress> findAddressByOpenid(String openid) {
        return addressRepository.findByBuyerOpenid(openid);
    }

    @Override
    public BuyerAddress findAddressById(String addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public BuyerAddress createAddress(AddressForm addressForm) {
        BuyerAddress buyerAddress = new BuyerAddress();
        BeanUtils.copyProperties(addressForm, buyerAddress);
        buyerAddress.setAddressId(KeyUtil.genUniqueKey());
        return addressRepository.save(buyerAddress);
    }

    @Override
    public void deleteAddress(String addressId) {
        addressRepository.deleteById(addressId);
    }
}
