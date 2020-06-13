package com.sponsoredAdsProject.exceptioms;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No such seller")
public class SellerNotFoundException extends RuntimeException {
}
