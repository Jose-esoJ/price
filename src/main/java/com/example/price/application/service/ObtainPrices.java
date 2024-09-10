package com.example.price.application.service;

import com.example.price.application.dto.PriceResponseDto;

public interface ObtainPrices {
    PriceResponseDto getPrice(Long productId, Long brandId, String applicationDate);
}