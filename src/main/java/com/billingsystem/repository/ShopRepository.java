package com.billingsystem.repository;

import com.billingsystem.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
  
    Shop findByShopUsername(String shopUsername); 
}