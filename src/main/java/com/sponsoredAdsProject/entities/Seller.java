package com.sponsoredAdsProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Seller {
    private @Id @GeneratedValue Long sellerID;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products=new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<Campaign> campaigns=new HashSet<>();

    public Seller() {
    }

    public Long getSellerID() {
        return sellerID;
    }

    public void setSellerID(Long sellerID) {
        this.sellerID = sellerID;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
