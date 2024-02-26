package com.liyt.sell.repository;

import com.liyt.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByUsernameAndPassword(String username, String password);
}
