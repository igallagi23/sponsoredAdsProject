package com.sponsoredAdsProject.controller;


import com.sponsoredAdsProject.entities.Campaign;
import com.sponsoredAdsProject.entities.Product;
import com.sponsoredAdsProject.exceptioms.SellerNotFoundException;
import com.sponsoredAdsProject.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class SponsoredAdsAPI {
    private static final Logger logger = Logger.getLogger(SponsoredAdsAPI.class.getName());

    private GeneralService generalService;

    @Autowired
    public SponsoredAdsAPI(GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/")
    public ResponseEntity<String> isAlive() {
        try {
            logger.info("alive check");
            return new ResponseEntity<>("alive", HttpStatus.OK);
        } catch (Exception e) {
            logger.warning("alive check failed");
            return new ResponseEntity<>("alive failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCampaign")
    @ResponseBody
    public ResponseEntity<?> createCampaign(@RequestBody Map<String, Object> body) {
        logger.info("creating campaign");
        Campaign createdCampaign;
        try {
            createdCampaign = this.generalService.createCampaign(body.get("name").toString(), (int) body.get("bid"), (int) body.get("sellerID"));
        } catch (SellerNotFoundException e) {
            logger.info("not found seller, error creating campaign");
            return new ResponseEntity<>("not found seller", HttpStatus.BAD_REQUEST);
        } catch (
                Exception e) {
            logger.warning("error creating campaign");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(createdCampaign, HttpStatus.CREATED);
    }

    @GetMapping("/serveAd")
    @ResponseBody
    public ResponseEntity<?> serveAd(@RequestBody Map<String, String> body) {
        logger.info("serve ad");
        Product product;
        try {
            product = generalService.serveAd(body.get("category"));
            if(product==null)
                return new ResponseEntity<>("no active campaigns", HttpStatus.OK);

        } catch (
                Exception e) {
            logger.warning("error serve ad");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping("/*")
    public ResponseEntity<String> notFound() {
        logger.info("not found");
        return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
    }
}
