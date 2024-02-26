package com.liyt.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liyt.sell.dataobject.OrderDetail;
import com.liyt.sell.enums.OrderStatusEnum;
import com.liyt.sell.enums.PayStatusEnum;
import com.liyt.sell.utils.EnumUtil;
import com.liyt.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderMaster的替身（目前看起来是这样）
 * 是VO和DO（？PO）的过渡，用于存放二者不包含但又有作用的数据（例如这里的orderDetailList）
 */
@Data
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;
    //订单状态，默认为新下单
    private Integer orderStatus;
    //支付状态，默认为等待支付
    private Integer payStatus;

    private String shopId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


    List<OrderDetail> orderDetailList;  //DTO独有数据

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
