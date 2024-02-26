package com.liyt.sell.form;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SellerForm {

    @Id
    private String username;

    private String password;
}
