package com.sponsoredAdsProject.repositories;


import com.sponsoredAdsProject.entities.ProductsInCampaigns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsInCampaignsRepository extends JpaRepository<ProductsInCampaigns,Long> {
    ProductsInCampaigns findFirstByProduct_CategoryAndCampaign_StatusOrderByCampaign_BidDesc(String category, boolean isActive);

    ProductsInCampaigns findFirstByCampaign_StatusOrderByCampaign_BidDesc(boolean isActive);
}
