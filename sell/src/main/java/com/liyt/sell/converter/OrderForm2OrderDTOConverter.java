package com.liyt.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liyt.sell.dataobject.OrderDetail;
import com.liyt.sell.dto.OrderDTO;
import com.liyt.sell.enums.ResultEnum;
import com.liyt.sell.exception.SellException;
import com.liyt.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderForm到OrderDTO
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setShopId(orderForm.getShopId());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){
                    }.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
