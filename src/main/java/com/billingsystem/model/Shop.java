package com.billingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(unique = true)
    private String shopCustomId; 
    
    private String shopUsername;
    private String password;
    private String city;
    private String address;
    private String phoneNumber;

    
    public Shop() {}

   
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getShopCustomId() { return shopCustomId; }
    public void setShopCustomId(String shopCustomId) { this.shopCustomId = shopCustomId; }

    public String getShopUsername() { return shopUsername; }
    public void setShopUsername(String shopUsername) { this.shopUsername = shopUsername; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}