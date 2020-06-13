package com.sponsoredAdsProject.entities;

import javax.persistence.*;


@Entity(name = "Products_In_Campaigns")
public class ProductsInCampaigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="productSerial")
    private Product product;

    @ManyToOne
    @JoinColumn(name="campaginID")
    private Campaign campaign;

    public ProductsInCampaigns() {
    }

    public ProductsInCampaigns(Product product, Campaign campaign) {
        this.campaign=campaign;
        this.product=product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
