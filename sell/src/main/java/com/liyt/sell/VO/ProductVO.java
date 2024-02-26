package com.liyt.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 商品（类目）
 */

@Data
public class ProductVO {
    /**
     * 以下注解代表后端的属性名和传给前端的不同，传给前端的json文件中属性名是括号里的
     * 为了各自看得方便
     */

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
