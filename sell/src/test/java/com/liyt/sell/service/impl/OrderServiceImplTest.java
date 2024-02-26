package com.liyt.sell.service.impl;

import com.liyt.sell.dataobject.OrderDetail;
import com.liyt.sell.dataobject.OrderMaster;
import com.liyt.sell.dto.CartDTO;
import com.liyt.sell.dto.OrderDTO;
import com.liyt.sell.enums.OrderStatusEnum;
import com.liyt.sell.enums.PayStatusEnum;
import com.liyt.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    private final String BUYER_OPENID = "ew3euwhd7sjw9diwkq";

    private final String ORDER_ID = "1643274589837111258";

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小慕");
        orderDTO.setBuyerAddress("火币网火币网");
        orderDTO.setBuyerPhone("18650092133");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234568");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(4);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        //到时候应该是用前端传来的数据组装成一个DTO吧？
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}", result);

        Assert.assertNotNull(result);
    }

    @Test
    void findById() {
        OrderDTO result = orderService.findById(ORDER_ID);
        log.info("【查询单个订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    void findList() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    void cancel() {
        OrderDTO result = orderService.findById(ORDER_ID);
        orderService.cancel(result);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    void finish() {
        OrderDTO result = orderService.findById(ORDER_ID);
        orderService.finish(result);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO result = orderService.findById(ORDER_ID);
        orderService.paid(result);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    void list(){
        PageRequest request = PageRequest.of(0,2);
        String shopId = "xuezi1";
        Page<OrderDTO> orderDTOPage = orderService.findListInShop(shopId, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }
}