package com.liyt.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;   //前端传

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;   //前端传

    private String productIcon;
}
