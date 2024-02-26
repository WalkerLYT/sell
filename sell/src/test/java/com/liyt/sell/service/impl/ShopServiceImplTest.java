package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.ProductInfo;
import com.liyt.sell.dataobject.ShopInfo;
import com.liyt.sell.enums.ShopStatusEnum;
import com.liyt.sell.service.ShopService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShopServiceImplTest {

    @Autowired
    private ShopServiceImpl shopService;

    @Test
    void save() {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId("xuezi2");
        shopInfo.setShopName("饺子馆");
        shopInfo.setShopAddress("学子餐厅二楼");
        shopInfo.setShopStatus(ShopStatusEnum.CLOSEDOWN.getCode());
        shopInfo.setShopIcon("http://xxxx.jpg");
        shopInfo.setShopPhone("18838202677");
        shopInfo.setOpeningTime("7:00AM");
        shopInfo.setClosingTime("9:00PM");
        ShopInfo result = shopService.save(shopInfo);
        Assert.assertNotNull(result);
    }

    @Test
    void findById() {
        ShopInfo shopInfo = shopService.findById("xuezi1");
        Assert.assertEquals("xuezi1", shopInfo.getShopId());
    }

    @Test
    void findOpeningAll() {
        List<ShopInfo> shopInfoList = shopService.findOpeningAll();
        Assert.assertEquals(3, shopInfoList.size());
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ShopInfo> shopInfoPage = shopService.findAll(request);
        Assert.assertNotEquals(0, shopInfoPage.getTotalElements());
    }
}