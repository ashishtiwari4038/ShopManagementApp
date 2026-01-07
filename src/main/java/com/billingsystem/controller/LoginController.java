package com.billingsystem.controller;

import com.billingsystem.model.Shop;
import com.billingsystem.repository.ShopRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private ShopRepository shopRepository;

    
    @GetMapping("/")
    public String showLoginPage(Model model, @RequestParam(required = false) String error) {
       
        if ("SessionExpired".equals(error)) {
            model.addAttribute("errorMessage", "Session expired. Please log in again.");
        }
        return "login";
    }

  
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                               @RequestParam String password, 
                               HttpSession session, 
                               Model model) {

        if (username.equals("admin") && password.equals("admin123")) {
            session.setAttribute("userRole", "OWNER");
            return "redirect:/owner/dashboard";
        }

       
        Shop foundShop = shopRepository.findByShopUsername(username);

        if (foundShop != null) {
         
            if (foundShop.getPassword().equals(password)) {
                
                session.setAttribute("currentShop", foundShop.getShopUsername());
                session.setAttribute("userRole", "EMPLOYEE");
                session.setAttribute("shopId", foundShop.getId());

                return "redirect:/employee/billing";
            } else {
              
                model.addAttribute("errorMessage", "Incorrect password.");
                return "login";
            }
        } else {
            
            model.addAttribute("errorMessage", "Username not found.");
            return "login";
        }
    }

   
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }
}
