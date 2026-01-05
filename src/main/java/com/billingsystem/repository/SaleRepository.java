package com.billingsystem.repository;

import com.billingsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
        List<Sale> findByShopUsername(String shopUsername);
}