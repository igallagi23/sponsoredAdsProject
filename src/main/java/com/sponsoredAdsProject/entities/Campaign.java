package com.sponsoredAdsProject.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CampaignID;
    private boolean status;
    private String name;
    private int bid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerID", nullable = false)
    private Seller seller;

    @OneToMany(mappedBy = "campaign")
    private Set<ProductsInCampaigns> productsInCampaigns;

    public Campaign() {
    }


    //initial Campaign
    public Campaign(String name, int bid, Seller seller) {

        this(true, name, bid, seller, new HashSet<ProductsInCampaigns>());
    }

    public Campaign(boolean status, String name, int bid, Seller seller, Set<ProductsInCampaigns> productsInCampaigns) {
        this.status = status;
        this.name = name;
        this.bid = bid;
        this.seller = seller;
        this.productsInCampaigns = productsInCampaigns;
    }

    public Long getCampaignID() {
        return CampaignID;
    }

    public void setCampaignID(Long campaignID) {
        CampaignID = campaignID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @JsonIgnore
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<ProductsInCampaigns> getProductsInCampaigns() {
        return productsInCampaigns;
    }

    public void setProductsInCampaigns(Set<ProductsInCampaigns> productsInCampaigns) {
        this.productsInCampaigns = productsInCampaigns;
    }
}
