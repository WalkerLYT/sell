package com.liyt.sell.repository;

import com.liyt.sell.dataobject.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<BuyerAddress, String> {
    List<BuyerAddress> findByBuyerOpenid(String openid);
}
