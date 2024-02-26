package com.liyt.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ShopInfo {

    @Id
    private String shopId;

    private String shopName;

    private String shopAddress;

    private String openingTime;

    private String closingTime;

    private Integer shopStatus; //就这个没传到前端去

    private String shopPhone;

    private String shopIcon;
}
