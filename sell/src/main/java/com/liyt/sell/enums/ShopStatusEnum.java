package com.liyt.sell.enums;

import lombok.Getter;

@Getter
public enum ShopStatusEnum implements CodeEnum{
    OPENING(0,"营业中"),
    CLOSING(1,"休息中"),
    CLOSEDOWN(2,"已倒闭"),
    ;

    private Integer code;

    private String message;

    ShopStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
