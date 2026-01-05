package com.billingsystem.controller;

import com.billingsystem.model.Sale;
import com.billingsystem.repository.SaleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/billing")
    public String showBillingPage(Model model, HttpSession session) {
        String shopName = (String) session.getAttribute("currentShop");
        if (shopName == null) {
            return "redirect:/";
        }

        model.addAttribute("sale", new Sale());
        model.addAttribute("shopName", shopName);
        return "billing_form";
    }

    @PostMapping("/save-invoice")
    public String saveInvoice(@ModelAttribute Sale sale, HttpSession session) {
        String shopName = (String) session.getAttribute("currentShop");
        
        if (shopName == null) {
            return "redirect:/?error=SessionExpired";
        }

        
        sale.setShopUsername(shopName);
        
       
        if (sale.getTotalAmount() == null || sale.getTotalAmount() == 0.0) {
            double mrp = sale.getMrp() != null ? sale.getMrp() : 0.0;
            double discount = sale.getDiscount() != null ? sale.getDiscount() : 0.0;
            double gst = sale.getGst() != null ? sale.getGst() : 0.0;
            
            double discountedPrice = mrp - (mrp * (discount / 100));
            double totalAmount = discountedPrice + (discountedPrice * (gst / 100));
            
           
            totalAmount = Math.round(totalAmount * 100.0) / 100.0;
            sale.setTotalAmount(totalAmount);
            
            logger.info("Calculated total amount: {} for MRP: {}, Discount: {}%, GST: {}%", 
                totalAmount, mrp, discount, gst);
        }
        
      
        saleRepository.save(sale);
        logger.info("Sale saved successfully for shop: {}, Customer: {}, Amount: {}", 
            shopName, sale.getCustomerName(), sale.getTotalAmount());
        
        return "redirect:/employee/billing?success=true";
    }
}