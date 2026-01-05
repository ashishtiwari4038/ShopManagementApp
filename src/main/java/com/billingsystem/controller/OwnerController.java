package com.billingsystem.controller;

import com.billingsystem.model.Shop;
import com.billingsystem.model.Sale;
import com.billingsystem.repository.ShopRepository;
import com.billingsystem.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/dashboard")
    public String ownerDashboard() {
        return "owner_dashboard";
    }

    
    @GetMapping("/manage-shops")
    public String manageShops(Model model) {
        List<Shop> allShops = shopRepository.findAll();
        model.addAttribute("shops", allShops);
        model.addAttribute("newShop", new Shop());
        return "manage_shops";
    }

    @PostMapping("/add-shop")
    public String addShop(@ModelAttribute Shop shop) {
        shopRepository.save(shop);
        return "redirect:/owner/manage-shops";
    }

    @GetMapping("/delete-shop/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopRepository.deleteById(id);
        return "redirect:/owner/manage-shops";
    }

   
    @GetMapping("/view-sales")
    public String viewSales(Model model) {
        List<Sale> allSales = saleRepository.findAll();
        model.addAttribute("sales", allSales);
        return "view_sales";
    }

    @GetMapping("/delete-sale/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleRepository.deleteById(id);
        return "redirect:/owner/view-sales";
    }

    @GetMapping("/reports")
    public String viewReports(Model model) {
        List<Sale> allSales = saleRepository.findAll();
        
        System.out.println("Total sales found: " + allSales.size());
        
        double totalSalesAmount = 0.0;
        double highestSale = 0.0;
        double lowestSale = 0.0;
        double averageSale = 0.0;
        int salesCount = 0;
        
        if (allSales != null && !allSales.isEmpty()) {
           
            List<Sale> validSales = allSales.stream()
                .filter(sale -> sale.getTotalAmount() != null)
                .toList();
            
            salesCount = validSales.size();
            System.out.println("Valid sales with totalAmount: " + salesCount);
            
            if (salesCount > 0) {
               
                for (Sale sale : validSales) {
                    totalSalesAmount += sale.getTotalAmount();
                }
                
                highestSale = validSales.get(0).getTotalAmount();
                lowestSale = validSales.get(0).getTotalAmount();
                
                for (Sale sale : validSales) {
                    double amount = sale.getTotalAmount();
                    if (amount > highestSale) {
                        highestSale = amount;
                    }
                    if (amount < lowestSale) {
                        lowestSale = amount;
                    }
                }
                
                
                averageSale = totalSalesAmount / salesCount;
            }
        }
      
        model.addAttribute("totalSalesAmount", totalSalesAmount);
        model.addAttribute("highestSale", highestSale);
        model.addAttribute("lowestSale", lowestSale);
        model.addAttribute("averageSale", averageSale);
        model.addAttribute("salesCount", salesCount);
        
        return "reports";
    }
}