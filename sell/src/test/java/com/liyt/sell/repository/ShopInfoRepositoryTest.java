package com.liyt.sell.repository;

import com.liyt.sell.dataobject.ShopInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShopInfoRepositoryTest {

    @Autowired ShopInfoRepository repository;

    @Test
    public void saveTest() throws Exception{
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId("xuezi1");
        shopInfo.setShopName("欧雅轩");
        shopInfo.setShopAddress("学子餐厅一楼");
        shopInfo.setShopStatus(0);
        shopInfo.setShopIcon("http://xxxx.jpg");
        shopInfo.setShopPhone("18882020202");
        shopInfo.setOpeningTime("8:00AM");
        shopInfo.setClosingTime("7:00PM");
        ShopInfo result = repository.save(shopInfo);
        Assert.assertNotNull(result);
    }
}