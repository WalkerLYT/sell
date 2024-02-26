package com.liyt.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liyt.sell.enums.ProductStatusEnum;
import com.liyt.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;
    //属于哪个店铺
    private String shopId;
    //小图
    private String productIcon;
    //状态，0正常1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号，和类目对应
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
