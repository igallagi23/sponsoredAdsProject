package com.sponsoredAdsProject.repositories;

import com.sponsoredAdsProject.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
