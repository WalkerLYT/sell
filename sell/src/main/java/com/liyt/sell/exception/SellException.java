package com.liyt.sell.exception;

import com.liyt.sell.enums.ResultEnum;

/**
 * 自定义异常类
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
