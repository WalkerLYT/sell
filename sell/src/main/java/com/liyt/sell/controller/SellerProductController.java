package com.liyt.sell.controller;

import com.liyt.sell.dataobject.ProductCategory;
import com.liyt.sell.dataobject.ProductInfo;
import com.liyt.sell.dto.OrderDTO;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.form.ProductForm;
import com.liyt.sell.service.CategoryService;
import com.liyt.sell.service.ProductService;
import com.liyt.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家管理商品控制类（crud）
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "shopId", defaultValue = "1") String shopId,
                             Map<String, Object> map){
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAllInShop(shopId, request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("shopId", shopId);
        return new ModelAndView("/product/list", map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               @RequestParam(value = "shopId", defaultValue = "1") String shopId,
                               Map<String, Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list?shopId=" + shopId);
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list?shopId=" + shopId);
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                @RequestParam(value = "shopId", defaultValue = "1") String shopId,
                                Map<String, Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list?shopId=" + shopId);
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list?shopId=" + shopId);
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      @RequestParam(value = "shopId") String shopId,
                      Map<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findById(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        map.put("shopId", shopId);
        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        /** 复习一遍表单验证 **/
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index?shopId=" + form.getShopId());
            return new ModelAndView("/common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //若前端传来的productId是空，说明是新增；若不为空，说明是修改
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findById(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index?shopId=" + form.getShopId());
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/sell/seller/product/list?shopId=" + form.getShopId());
        return new ModelAndView("/common/success", map);
    }
}
