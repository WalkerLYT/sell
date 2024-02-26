package com.liyt.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情（ProductVO的data部分）
 * 为什么不就用ProductInfo呢？
 * 因为ProductInfo是传给前端的，有些数据前端不需要（防止泄露隐私之类）
 * 但是我们后台要查的数据还是得查
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
