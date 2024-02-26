package com.liyt.sell.controller;

import com.liyt.sell.VO.ResultVO;
import com.liyt.sell.dataobject.ShopInfo;
import com.liyt.sell.service.ShopService;
import com.liyt.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 买家店铺控制类（显示店铺）
 */
@RestController
@RequestMapping("/buyer/shop")
public class BuyerShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/list")
    public ResultVO list(){
        List<ShopInfo> shopInfoList = shopService.findOpeningAll();
        return ResultVOUtil.success(shopInfoList);
    }
}
