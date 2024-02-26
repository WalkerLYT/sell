package com.liyt.sell.controller;

import com.liyt.sell.VO.ProductInfoVO;
import com.liyt.sell.VO.ProductVO;
import com.liyt.sell.VO.ResultVO;
import com.liyt.sell.dataobject.ProductCategory;
import com.liyt.sell.dataobject.ProductInfo;
import com.liyt.sell.service.CategoryService;
import com.liyt.sell.service.ProductService;
import com.liyt.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品控制类（显示店铺中商品）
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allList")
    public ResultVO list(){
        //1. 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 查询类目（一次性查询）
        List<Integer> categoryTypeList = new ArrayList<>();     //categoryType实际上是类目编号，别忘了
        //传统方法
        for(ProductInfo productInfo: productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        //精简方法(java8, lambda表达式)
//        List<Integer> categoryTypeList = productInfoList.stream().
//                map(e -> e.getCategoryType()).
//                collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);     //相同编号的会算作一种？

        //3. 数据拼装（一层一层的嵌套，让其格式符合前端所需要求）
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);    //直接拷贝属性，简化繁琐的set方法
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);     //success方法用了static，甚至不需要创建对象
    }

    @GetMapping("/list")
    public ResultVO proList(@RequestParam("shopId") String shopId){
        List<ProductInfo> productInfoList = productService.findShopUpAll(shopId);

        List<Integer> categoryTypeList = new ArrayList<>();     //categoryType实际上是类目编号，别忘了
        //传统方法
        for(ProductInfo productInfo: productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);     //相同编号的会算作一种？

        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);    //直接拷贝属性，简化繁琐的set方法
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);     //success方法用了static，甚至不需要创建对象
    }
}
