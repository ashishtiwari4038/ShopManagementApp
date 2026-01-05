

package com.billingsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String customerNumber;
    private String productCompany;
    private String model;
    private Double mrp;
    private Double discount;
    private Double gst;
    private Double totalAmount;
    private String shopUsername;
    
    private LocalDateTime saleDate = LocalDateTime.now();
    
   
    public Sale() {}
    
   
    public Sale(String customerName, String customerNumber, String productCompany, 
                String model, Double mrp, Double discount, Double gst, 
                Double totalAmount, String shopUsername) {
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.productCompany = productCompany;
        this.model = model;
        this.mrp = mrp;
        this.discount = discount;
        this.gst = gst;
        this.totalAmount = totalAmount;
        this.shopUsername = shopUsername;
        this.saleDate = LocalDateTime.now();
    }
    
    
    public Long getId() { 
        return id; 
    }
    
    public String getCustomerName() { 
        return customerName != null ? customerName : ""; 
    }
    
    public String getCustomerNumber() { 
        return customerNumber != null ? customerNumber : ""; 
    }
    
    public String getProductCompany() { 
        return productCompany != null ? productCompany : ""; 
    }
    
    public String getModel() { 
        return model != null ? model : ""; 
    }
    
    public Double getMrp() { 
        return mrp != null ? mrp : 0.0; 
    }
    
    public Double getDiscount() { 
        return discount != null ? discount : 0.0; 
    }
    
    public Double getGst() { 
        return gst != null ? gst : 0.0; 
    }
    
    public Double getTotalAmount() { 
        return totalAmount != null ? totalAmount : 0.0; 
    }
    
    public String getShopUsername() { 
        return shopUsername != null ? shopUsername : ""; 
    }
    
    public LocalDateTime getSaleDate() { 
        return saleDate != null ? saleDate : LocalDateTime.now(); 
    }
    
  
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public void setCustomerName(String customerName) { 
        this.customerName = customerName; 
    }
    
    public void setCustomerNumber(String customerNumber) { 
        this.customerNumber = customerNumber; 
    }
    
    public void setProductCompany(String productCompany) { 
        this.productCompany = productCompany; 
    }
    
    public void setModel(String model) { 
        this.model = model; 
    }
    
    public void setMrp(Double mrp) { 
        this.mrp = mrp != null ? mrp : 0.0; 
    }
    
    public void setDiscount(Double discount) { 
        this.discount = discount != null ? discount : 0.0; 
    }
    
    public void setGst(Double gst) { 
        this.gst = gst != null ? gst : 0.0; 
    }
    
    public void setTotalAmount(Double totalAmount) { 
        this.totalAmount = totalAmount != null ? totalAmount : 0.0; 
    }
    
    public void setShopUsername(String shopUsername) { 
        this.shopUsername = shopUsername; 
    }
    
    public void setSaleDate(LocalDateTime saleDate) { 
        this.saleDate = saleDate != null ? saleDate : LocalDateTime.now(); 
    }
    
   
    public void calculateTotalAmount() {
        if (this.totalAmount == null && this.mrp != null) {
            double mrpValue = this.mrp;
            double discountValue = this.discount != null ? this.discount : 0.0;
            double gstValue = this.gst != null ? this.gst : 0.0;
            
            double discountedPrice = mrpValue - (mrpValue * (discountValue / 100));
            this.totalAmount = discountedPrice + (discountedPrice * (gstValue / 100));
        }
    }
}