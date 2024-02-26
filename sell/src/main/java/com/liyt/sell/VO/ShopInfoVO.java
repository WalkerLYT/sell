package com.liyt.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopInfoVO {

    private String shopId;

    private String shopName;

    private String shopAddress;

    private String openingTime;

    private String closingTime;

    private String shopPhone;

    private String shopIcon;
}
