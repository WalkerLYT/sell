package com.liyt.sell.controller;

import com.liyt.sell.dataobject.SellerInfo;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.form.ProductForm;
import com.liyt.sell.form.SellerForm;
import com.liyt.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * 卖家功能控制类（登录注销）
 */
@Controller
@RequestMapping("/seller/function")
@Slf4j
public class SellerFunctionController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map){
        return new ModelAndView("/common/login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid SellerForm form,
                              BindingResult bindingResult,
                              Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/function/index");
            return new ModelAndView("/common/error", map);
        }
        try {
            SellerInfo result = sellerService.login(form.getUsername(), form.getPassword());
            String shopId = result.getShopId();
            map.put("url", "/sell/seller/product/list?shopId=" + shopId);
            return new ModelAndView("/common/success", map);

        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/function/index");
            return new ModelAndView("/common/error", map);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(Map<String, Object> map){
        return new ModelAndView("/common/login", map);
    }
}
