package com.sponsoredAdsProject.services;

import com.sponsoredAdsProject.entities.Campaign;
import com.sponsoredAdsProject.entities.Product;
import com.sponsoredAdsProject.entities.ProductsInCampaigns;
import com.sponsoredAdsProject.entities.Seller;
import com.sponsoredAdsProject.exceptioms.SellerNotFoundException;
import com.sponsoredAdsProject.repositories.CampaignRepository;
import com.sponsoredAdsProject.repositories.ProductsInCampaignsRepository;
import com.sponsoredAdsProject.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GeneralService {
    private static final Logger logger = Logger.getLogger(GeneralService.class.getName());

    private SellerRepository sellerRepository;
    private CampaignRepository campaignRepository;
    private ProductsInCampaignsRepository productsInCampaignsRepository;

    @Autowired
    public GeneralService(SellerRepository sellerRepository, CampaignRepository campaignRepository, ProductsInCampaignsRepository productsInCampaignsRepository) {
        this.sellerRepository = sellerRepository;
        this.campaignRepository = campaignRepository;
        this.productsInCampaignsRepository = productsInCampaignsRepository;
    }

    public Campaign createCampaign(String name, int bid, long sellerID) {
        //should validate name and bid as well.
        logger.info("getting seller");
        Optional<Seller> seller;
        seller = sellerRepository.findById(sellerID);
        if (seller.isEmpty()){
            logger.warning("not such seller");
            throw new SellerNotFoundException();
        }
        logger.info("returning saved campaign seller");
        return campaignRepository.save(new Campaign(name, bid, seller.get()));
    }

    public Product serveAd(String category) {
        logger.info("serve ad service");
        ProductsInCampaigns productsInCampaigns = this.productsInCampaignsRepository.
                findFirstByProduct_CategoryAndCampaign_StatusOrderByCampaign_BidDesc(category, true);
        if (productsInCampaigns == null) {
            logger.info("no active campaigns with "+category);
            productsInCampaigns= this.productsInCampaignsRepository.findFirstByCampaign_StatusOrderByCampaign_BidDesc(true);
            logger.info("get general category");
        }
        return productsInCampaigns.getProduct();
    }
}
