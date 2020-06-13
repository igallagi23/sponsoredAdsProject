package com.sponsoredAdsProject.services;

import com.sponsoredAdsProject.entities.Campaign;
import com.sponsoredAdsProject.entities.Product;
import com.sponsoredAdsProject.entities.ProductsInCampaigns;
import com.sponsoredAdsProject.entities.Seller;
import com.sponsoredAdsProject.repositories.CampaignRepository;
import com.sponsoredAdsProject.repositories.ProductRepository;
import com.sponsoredAdsProject.repositories.ProductsInCampaignsRepository;
import com.sponsoredAdsProject.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service
public class CreateMockDataService {
    private static final Logger logger = Logger.getLogger(CreateMockDataService.class.getName());

    private SellerRepository sellerRepository;
    private ProductRepository productRepository;
    private CampaignRepository campaignRepository;
    private ProductsInCampaignsRepository productsInCampaignsRepository;

    @Autowired
    public CreateMockDataService(SellerRepository sellerRepository, ProductRepository productRepository ,
                                 CampaignRepository campaignRepository, ProductsInCampaignsRepository productsInCampaignsRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.productsInCampaignsRepository=productsInCampaignsRepository;
    }

    @PostConstruct
    public void mockData(){
        logger.info("mocking data");
        Seller seller1=sellerRepository.save(new Seller());
        Seller seller2=sellerRepository.save(new Seller());
        Seller seller3=sellerRepository.save(new Seller());
        Seller seller4=sellerRepository.save(new Seller());
        Product product1=productRepository.save(new Product("bread","carbs",1,seller1));
        Product product2=productRepository.save(new Product("milk","dairy",2,seller2));
        Product product3=productRepository.save(new Product("steak","meat",4,seller3));
        Product product4=productRepository.save(new Product("dennis","fish",3244,seller3));
        Product product5=productRepository.save(new Product("buns","carbs",32434,seller4));
        Campaign campaign1= campaignRepository.save(new Campaign("Best camp",10,seller1));
        Campaign campaign2=campaignRepository.save(new Campaign("Best camp2",43,seller2));
//        campaign2.setStatus(false);
//        campaignRepository.save(campaign2);
        Campaign campaign3=campaignRepository.save(new Campaign("Best camp3",30,seller3));
        ProductsInCampaigns productsInCampaigns1= new ProductsInCampaigns(product1,campaign1);
        ProductsInCampaigns productsInCampaigns2= new ProductsInCampaigns(product5,campaign2);
        ProductsInCampaigns productsInCampaigns3= new ProductsInCampaigns(product4,campaign3);
        productsInCampaignsRepository.save(productsInCampaigns1);
        productsInCampaignsRepository.save(productsInCampaigns2);
        productsInCampaignsRepository.save(productsInCampaigns3);
//        campaign2.setStatus(false);
//        campaignRepository.save(campaign2);
    }
}
