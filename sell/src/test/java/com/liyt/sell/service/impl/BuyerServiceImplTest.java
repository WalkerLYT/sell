package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.BuyerAddress;
import com.liyt.sell.form.AddressForm;
import com.liyt.sell.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BuyerServiceImplTest {

    @Autowired
    BuyerService buyerService;

    @Test
    void findOrderOne() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void findByOpenId() {
        String openid = "110110";
        List<BuyerAddress> result = buyerService.findAddressByOpenid(openid);
        Assert.assertEquals(3, result.size());
    }

    @Test
    void findById() {
        String addressId = "add20000";
        BuyerAddress result = buyerService.findAddressById(addressId);
        Assert.assertEquals(result, null);
    }

    @Test
    void saveAddress() {
        AddressForm addressForm = new AddressForm();
        String openid = "ew3euwhd7sjw9diwkq";
        String name = "张三";
        String address = "慕课网威海分部";
        String phone = "12122122112";
        String area = "中国大陆";
        addressForm.setBuyerOpenid(openid);
        addressForm.setBuyerName(name);
        addressForm.setBuyerAddress(address);
        addressForm.setBuyerPhone(phone);
        addressForm.setBuyerArea(area);
        BuyerAddress result = buyerService.createAddress(addressForm);
        Assert.assertNotNull(result);
    }

    @Test
    void deleteAddress() {
        String addressId = "add20001";
        buyerService.deleteAddress(addressId);
    }
}