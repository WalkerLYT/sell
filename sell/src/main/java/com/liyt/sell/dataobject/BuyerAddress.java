package com.liyt.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BuyerAddress {

    @Id
    private String addressId;

    private String buyerName;

    private String buyerPhone;

    private String buyerOpenid;

    private String buyerArea;

    private String buyerAddress;



}
