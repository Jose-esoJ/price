package com.example.price.application.service;

import com.example.price.application.dto.PriceResponseDto;

import java.util.List;

public interface PriceService {
    List<PriceResponseDto> getPrice(Long productId, Long brandId, String applicationDate);
}