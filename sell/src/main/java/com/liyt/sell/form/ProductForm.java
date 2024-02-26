package com.liyt.sell.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 接收前端传来的表单信息
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;
    //小图
    private String productIcon;
    //类目编号，和类目对应
    private Integer categoryType;

    private String shopId;

    private Date createTime;

    private Date updateTime;
}
