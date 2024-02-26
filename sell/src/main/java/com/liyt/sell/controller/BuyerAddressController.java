package com.liyt.sell.controller;

import com.liyt.sell.VO.ResultVO;
import com.liyt.sell.dataobject.BuyerAddress;
import com.liyt.sell.enums.ResultEnum;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.form.AddressForm;
import com.liyt.sell.service.BuyerService;
import com.liyt.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家端地址管理控制类
 */
@RestController
@RequestMapping("/buyer/address")
@Slf4j
public class BuyerAddressController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping(value = "/list")
    public ResultVO<List<BuyerAddress>> checkAddress(@RequestParam("openid") String openid){

        if(StringUtils.isEmpty(openid)){
            log.error("【查询地址列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        List<BuyerAddress> addressList = buyerService.findAddressByOpenid(openid);
        return ResultVOUtil.success(addressList);
    }

    @PostMapping(value = "/create")
    public ResultVO<BuyerAddress> createAddress(@Valid AddressForm addressForm,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, addressForm={}", addressForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        BuyerAddress createResult = buyerService.createAddress(addressForm);

        Map<String, String> map = new HashMap<>();
        map.put("addressId", createResult.getAddressId());

        return ResultVOUtil.success(map);
    }

    @PostMapping(value = "/delete")
    public ResultVO<Map<String, String>> deleteAddress(@RequestParam("addressId") String addressId){

        if(buyerService.findAddressById(addressId) == null){
            log.error("【删除用户地址】 address不存在");
            throw new SellException(ResultEnum.ADDRESS_NOT_EXIST);
        }

        buyerService.deleteAddress(addressId);
        return ResultVOUtil.success();
    }

}
