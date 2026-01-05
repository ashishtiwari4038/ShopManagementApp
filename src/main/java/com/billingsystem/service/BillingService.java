package com.billingsystem.service;

import org.springframework.stereotype.Service;

@Service
public class BillingService {

    
    public Double calculateTotal(Double mrp, Double discountPercentage, Double gstPercentage) {
        Double discountedPrice = mrp - (mrp * (discountPercentage / 100));
        Double finalAmount = discountedPrice + (discountedPrice * (gstPercentage / 100));
        return finalAmount;
    }
}